package com.vng.uiwebapp.grpc;


import com.vng.apigateway.WebClientServiceGrpc;
import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.uiwebapp.model.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private static WebClientServiceGrpc.WebClientServiceBlockingStub webClientServiceBlockingStub;

    static {
        init();
    }

    private static void init(){

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085").usePlaintext().build();
        webClientServiceBlockingStub = WebClientServiceGrpc.newBlockingStub(channel);
        //channel.shutdownNow();
    }

    public static WebClientServiceOuterClass.Response login(String username, String password){

        WebClientServiceOuterClass.LoginRequest request = WebClientServiceOuterClass.LoginRequest.newBuilder()
                .setUsername(username).setPassword(password).build();
        WebClientServiceOuterClass.Response response = webClientServiceBlockingStub.login(request);

        return response;
    }

    public static void logout(){}

    public static WebClientServiceOuterClass.WebsocketInfo getWebsocketInfo(String token){

        WebClientServiceOuterClass.Message request = WebClientServiceOuterClass.Message.newBuilder().
                setMessage(token).build();
        WebClientServiceOuterClass.WebsocketInfo response = webClientServiceBlockingStub.getWebsocketInfo(request);

        return response;
    }

    public static WebClientServiceOuterClass.Message register(User user) {


        WebClientServiceOuterClass.RegisterRequest request = WebClientServiceOuterClass.RegisterRequest
                .newBuilder()
                .setFullname(user.getFullname())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setGender(user.getGender())
                .setBirthday(user.getBirthday())
                .build();

        WebClientServiceOuterClass.Message message = webClientServiceBlockingStub.register(request);

        return message;
    }

    public static WebClientServiceOuterClass.Message forgot(User user){
        WebClientServiceOuterClass.ForgotRequest request = WebClientServiceOuterClass.ForgotRequest
                .newBuilder()
                .setEmail(user.getEmail())
                .build();

        WebClientServiceOuterClass.Message message = webClientServiceBlockingStub.forgot(request);

        return message;
    }
}
