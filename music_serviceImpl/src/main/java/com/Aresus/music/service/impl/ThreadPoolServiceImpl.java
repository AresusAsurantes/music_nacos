package com.Aresus.music.service.impl;

import com.Aresus.music.model.vo.ThreadTask;
import com.Aresus.music.service.ThreadPoolService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@DubboService(version = "1.0.0", interfaceClass = ThreadPoolService.class)
public class ThreadPoolServiceImpl implements ThreadPoolService {

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    public void doTask(ThreadTask task) {
        executor.execute(task.getTask());
    }


}
