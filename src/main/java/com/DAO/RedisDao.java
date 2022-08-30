package com.DAO;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.pojo.Seckill;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {

    private JedisPool jedisPool;

    RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip,int port) {
        jedisPool = new JedisPool(ip,port);
    }

    public Seckill getSeckill(Long seckillId){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String key = "seckillId:" + seckillId;
            byte[] bytes = jedis.get(key.getBytes());
            if(bytes != null){
                Seckill seckill = schema.newMessage();
                //反序列化
                ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                return seckill;
            }
        }finally {
            jedis.close();
        }
        return null;
    }

    public void putSeckill(Seckill seckill){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String key = "seckillId:" + seckill.getSeckillId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            jedis.setex(key.getBytes(),60*60,bytes);
        }finally {
            jedis.close();
        }
    }
}
