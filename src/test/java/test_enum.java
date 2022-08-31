import com.enums.SeckillStateEnum;
import org.junit.Test;

import java.util.Date;

public class test_enum {
    @Test
    public void test(){
        SeckillStateEnum stateEnum = SeckillStateEnum.stateOf(1);
        System.out.println(stateEnum);
        System.out.println(new Date().getTime());
    }
}
