package me.ggulmool.test;

import me.ggulmool.lss.spring.LssApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LssApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LssIntegrationTests {

    @Test
    public void whenLoadApp_thenSuccess() throws Exception {

    }
}
