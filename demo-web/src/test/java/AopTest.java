import cn.com.jd.service.IHelloWorldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by root on 16-8-31.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class AopTest {

    @Resource
    private IHelloWorldService helloWorldService;

    @Test
    public void testHelloWorld(){
        helloWorldService.sayAround("haha");
    }

}
