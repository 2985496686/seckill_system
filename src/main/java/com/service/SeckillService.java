package com.service;

import com.dto.Exposer;
import com.exeception.ExecuteSeckillException;
import com.exeception.RepeatSeckillException;
import com.exeception.SeckillCloseException;
import com.pojo.Seckill;

import java.util.List;

public interface SeckillService {

    /**
     * 返回所有秒杀的商品列表
     * @return
     */
    List<Seckill> getSeckillList();


    /**
     * 根据id查询某一个商品的秒杀信息
     * @return
     */
    Seckill getSeckillById(Long seckillId);


    /**
     * 若执行秒杀暴露秒杀接口，不执行秒杀返回系统当前时间，以及秒杀开始和结束的时间
     * @param seckillId
     */
    Exposer exposeSeckillUrl(Long seckillId);


    /**
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @throws ExecuteSeckillException
     * @throws SeckillCloseException
     * @throws RepeatSeckillException
     */
    void executeSeckill(Long seckillId,Long userPhone,String md5)
            throws ExecuteSeckillException, SeckillCloseException, RepeatSeckillException;
}
