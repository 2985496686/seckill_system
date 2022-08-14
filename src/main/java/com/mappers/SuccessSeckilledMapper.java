package com.mappers;

import com.pojo.SuccessSeckilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessSeckilledMapper {
    /**
     * 插入购买明细，可过滤重复
     *
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 插入的行数，如果返回值<1则表示插入失败
     */
    int insertSuccessSeckilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀商品对象实体
     *
     * @param seckillId 秒杀商品id
     * @return  SuccessKilled并携带秒杀商品对象实体
     */
    SuccessSeckilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}

