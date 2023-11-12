package com.dnc.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    private List<String> leakList = new ArrayList();

    @RequestMapping(value="/memory-leak.do", method = RequestMethod.POST)
    public void memoryLeak(){
        if(log.isDebugEnabled()){
            log.debug("Test Start");
        }
        while(true){
            leakList.add("Memory leak Test String");
        }
    }
}
