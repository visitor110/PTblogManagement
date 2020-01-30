package com.pt.bloglib.service.Impl;

import com.pt.bloglib.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略模式；根据不同情况使用合适的MailService实例
 */
@Component
public class MailServiceStrategy {

    @Autowired
    private final Map<String, MailService> strategyMap = new ConcurrentHashMap<>();

    public MailServiceStrategy(Map<String, MailService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    public MailService getMailServiceImpl(String strategy) {
        return strategyMap.get(strategy);
    }
}
