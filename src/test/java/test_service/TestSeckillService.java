package test_service;


import com.dto.Exposer;
import com.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestSeckillService {
    @Autowired
     private  SeckillService seckillService;

    @Test
    public void test(){
        Exposer md5 = seckillService.exposeSeckillUrl(1000L);
        seckillService.executeSeckill(1000L,12222222L,md5.getMd5());
    }
}
