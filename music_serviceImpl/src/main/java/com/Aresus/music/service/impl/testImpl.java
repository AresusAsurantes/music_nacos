package com.Aresus.music.service.impl;

import com.Aresus.music.service.Test;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", interfaceClass = Test.class)
public class testImpl implements Test {

    @Override
    public String testProject() {
        return "Test Success!!!!";
    }
}
