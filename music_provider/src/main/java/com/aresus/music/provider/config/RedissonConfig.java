package com.aresus.music.provider.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RLock rLock(){
        RedissonClient redissonClient = Redisson.create();
        return redissonClient.getLock("myLock");
    }

    @Bean
    public MyLock myLock(){
        return new MyLock();
    }

    static class MyLock{

        @Autowired
        private RLock rLock;

        public boolean tryLock(){
            return rLock.tryLock();
        }

        public void unLock(){
            rLock.unlock();
        }
    }

}
