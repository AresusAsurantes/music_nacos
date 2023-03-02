package com.Areusus.music.api.test;

import com.Aresus.music.service.Test;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @DubboReference(version = "1.0.0")
    private Test tes;

    @GetMapping()
    @ResponseBody
    public String test(){
        return tes.testProject();
    }
}
