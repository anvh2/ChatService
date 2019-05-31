package com.vng.apigatewayservice.grpc.server;

import com.vng.apigateway.WebClientServiceGrpc;
import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.apigatewayservice.grpc.GrpcClient;
import com.vng.chatservice.WebSocketServiceOuterClass;
import com.vng.security.AuthServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class WebClientServiceImpl extends WebClientServiceGrpc.WebClientServiceImplBase {



    @Override
    public void login(WebClientServiceOuterClass.LoginRequest request,
                      StreamObserver<WebClientServiceOuterClass.Response> responseObserver) {

        AuthServiceOuterClass.Response response = GrpcClient.login(request.getUsername(), request.getPassword());

        WebClientServiceOuterClass.Response tokenResponse = WebClientServiceOuterClass.Response.newBuilder()
                .setToken(response.getToken().getToken()).setUsername(response.getUsername()).build();
        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();

    }

    @Override
    public void logout(WebClientServiceOuterClass.Message request,
                       StreamObserver<WebClientServiceOuterClass.Message> responseObserver) {

    }

    @Override
    public void checkToken(WebClientServiceOuterClass.Message request,
                           StreamObserver<WebClientServiceOuterClass.Message> responseObserver) {

    }

    @Override
    public void getWebsocketInfo(WebClientServiceOuterClass.Message request,
                                 StreamObserver<WebClientServiceOuterClass.WebsocketInfo> responseObserver) {

        String endpoint = "ERROR", topic = "", chatCode = "";
        AuthServiceOuterClass.Response response = GrpcClient.checkToken(request.getMessage());
        if(response.getToken().getStatus().equals("VALID_TOKEN")){

            WebSocketServiceOuterClass.Response websocketInfo = GrpcClient.getWebsocketInfo(response.getUsername(), response.getChatCode());
            endpoint = websocketInfo.getEndpoint();
            topic = websocketInfo.getTopic();
            chatCode = response.getChatCode();

        }

        WebClientServiceOuterClass.WebsocketInfo websocketInfo = WebClientServiceOuterClass.WebsocketInfo.newBuilder()
                .setEndpoint(endpoint).setTopic(topic).setChatCode(chatCode).build();
        responseObserver.onNext(websocketInfo);
        responseObserver.onCompleted();

    }

    @Override
    public void register(WebClientServiceOuterClass.RegisterRequest request, StreamObserver<WebClientServiceOuterClass.Message> responseObserver) {
        AuthServiceOuterClass.Message message = GrpcClient.register(request);

        WebClientServiceOuterClass.Message response = WebClientServiceOuterClass.Message
                .newBuilder()
                .setMessage(message.getMessage())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void forgot(WebClientServiceOuterClass.ForgotRequest request, StreamObserver<WebClientServiceOuterClass.Message> responseObserver) {
        AuthServiceOuterClass.Message message = GrpcClient.forgot(request);

        WebClientServiceOuterClass.Message response = WebClientServiceOuterClass.Message
                .newBuilder()
                .setMessage(message.getMessage())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
