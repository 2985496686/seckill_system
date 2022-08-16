package test_dao;

import com.mappers.SeckillMapper;
import com.pojo.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestSeckillMapper {
    @Autowired
    SeckillMapper seckillMapper;
    @Test
    public void testReduceNumber(){
        int count = seckillMapper.reduceNumber(new Date(),1000L);

        int count2 = seckillMapper.reduceNumber(new Date(),1001L);
        System.out.println(count + "   " + count2);
    }

    @Test
    public void testQueryById(){
        Seckill seckill = seckillMapper.queryById(1000L);
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll(){
        List<Seckill> seckills = seckillMapper.queryAll(0, 3);
        System.out.println(seckills);
    }
}
