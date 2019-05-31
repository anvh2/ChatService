package com.vng.apigatewayservice.rest.controller;

import com.vng.apigatewayservice.grpc.GrpcClient;
import com.vng.apigatewayservice.rest.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

//    @GetMapping("/chat")
//    public String chat(){
//        System.out.println("nothing");
//        return GrpcClient.connect("x");
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody User user){
//       return GrpcClient.login(user.getUsername(), user.getPassword());
//    }

}
