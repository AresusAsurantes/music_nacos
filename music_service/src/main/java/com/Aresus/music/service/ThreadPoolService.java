package com.Aresus.music.service;

import com.Aresus.music.model.vo.ThreadTask;

public interface ThreadPoolService {
    void doTask(ThreadTask task);
}
