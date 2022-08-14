package TestDao;

import com.mappers.SuccessSeckilledMapper;
import com.pojo.SuccessSeckilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestSuccessSeckilledMapper {

    @Autowired
    SuccessSeckilledMapper successSeckilledMapper;

    @Test
    public void testInsertSuccessSeckilled(){
        int i = successSeckilledMapper.insertSuccessSeckilled(1000L,12345679L);
        //重复插入
        int j = successSeckilledMapper.insertSuccessSeckilled(1000L,12345678L);
        System.out.println(i + "  "+ j);
    }

    @Test
    public void testQueryByIdWithSeckill(){
        SuccessSeckilled successSeckilled = successSeckilledMapper.queryByIdWithSeckill(1000L, 12345678L);
        System.out.println(successSeckilled);
    }
}
