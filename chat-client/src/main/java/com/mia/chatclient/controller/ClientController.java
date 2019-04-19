package com.mia.chatclient.controller;

import com.mia.chatclient.model.ClientParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ClientController
 * @Description TODO
 * @Author mia.he
 * @Date 2019/4/19
 * @Version 1.0
 */
@Api(value = "聊天室", description = "聊天室服务的API")
@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @ApiOperation("聊天")
    @RequestMapping(value = "chat",method = RequestMethod.POST)
    public  String clientChat(@RequestBody ClientParam param){

        return "";
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String getDemo(){
        return "";
    }
}
