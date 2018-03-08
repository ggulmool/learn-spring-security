package me.ggulmool.lss.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncBean {

    @Async
    public void asyncMethod() {
        System.out.println("asyncMethod call");
    }
}
