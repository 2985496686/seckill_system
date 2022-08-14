package com.mappers;

import com.pojo.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillMapper {

    /**
     * 用户秒杀商品
     * @param seckillTime 用户秒杀时间
     * @param seckillId 秒杀商品Id
     * @return 秒删成功返回 1
     */
    int reduceNumber(@Param("seckillTime") Date seckillTime, @Param("seckillId") Long seckillId);

    /**
     * 根据Id查询商品
     * @param seckillId 商品id
     * @return 所查询的商品
     */
    Seckill queryById(@Param("seckillId") Long seckillId);


    /**
     *
     * @param offset 偏移量
     * @param limit 查询商品数量
     * @return 商品列表
     */
    List<Seckill> queryAll(@Param("offset") int offset , @Param("limit") int limit);

}
