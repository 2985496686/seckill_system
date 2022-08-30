
package com.service.impl;
import com.DAO.RedisDao;
import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.enums.SeckillStateEnum;
import com.exeception.ExecuteSeckillException;
import com.exeception.RepeatSeckillException;
import com.exeception.SeckillCloseException;
import com.mappers.SeckillMapper;
import com.mappers.SuccessSeckilledMapper;
import com.pojo.Seckill;
import com.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //用于混淆加密的盐值
    public final String salt = "$233423$@#$@#$@Q##@$@!#$%$*&%^R^@#$@%@#$";

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private RedisDao redisDao;

    public SeckillMapper getSeckillMapper() {
        return seckillMapper;
    }

    public void setSeckillMapper(SeckillMapper seckillMapper) {
        this.seckillMapper = seckillMapper;
    }

    @Autowired
    private SuccessSeckilledMapper successSeckilledMapper;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillMapper.queryAll(0, 4);
    }

    @Override
    public Seckill getSeckillById(Long seckillId) {
        return seckillMapper.queryById(seckillId);
    }

    @Override
    public Exposer exposeSeckillUrl(Long seckillId) {
        //秒杀商品id不存在
        if (seckillId == null) {
            return new Exposer(false, seckillId);
        }
        //获取秒杀商品的相关信息
        Seckill seckill = redisDao.getSeckill(seckillId);
        //Redis缓存中没有该对象
        if(seckill == null){
            seckill = seckillMapper.queryById(seckillId);
            //存入redis缓存
            redisDao.putSeckill(seckill);
        }
        long startTime = seckill.getStartTime().getTime();
        long endTime = seckill.getEndTime().getTime();
        long nowTime = new Date().getTime();
        //秒杀未开始
        if (nowTime < startTime || nowTime > endTime) {
            return new Exposer(false, seckillId, nowTime, startTime, endTime);
        }
        //秒杀开启
        return new Exposer(true, this.getMd5(seckillId), seckillId);
    }
    //获取url的md5加密值
    public String getMd5(Long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    @Override
    @Transactional
    public SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5) throws ExecuteSeckillException, SeckillCloseException, RepeatSeckillException {
        try {
            //访问秒杀接口的渠道错误
            if (seckillId == null || !getMd5(seckillId).equals(md5)) {
                throw new ExecuteSeckillException("seckillUrl rewrite");
            }
            //执行秒杀：减少商品 + 插入购买明细
            //减少商品
            //获取秒杀时间
            Date nowTime = new Date();
            int reduceNumber = seckillMapper.reduceNumber(nowTime, seckillId);
            if (reduceNumber <= 0) {
                //秒杀时间未开始或者已经结束或者商品数量不足等原因导致秒杀关闭
                throw new SeckillCloseException("seckill close");
            }
            //插入购买明细
            int insertNumber = successSeckilledMapper.insertSuccessSeckilled(seckillId, userPhone);
            //重复秒杀
            if (insertNumber <= 0) {
                throw new RepeatSeckillException("repeat  seckill");
            }
        } catch (SeckillCloseException | RepeatSeckillException s) {
            throw s;
        } catch (Exception e) {
            throw new ExecuteSeckillException("seckill inner exception:" + e.getMessage());
        }
        return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successSeckilledMapper.queryByIdWithSeckill(seckillId,userPhone));
    }
}
