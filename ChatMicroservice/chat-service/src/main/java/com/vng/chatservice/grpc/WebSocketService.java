package com.vng.chatservice.grpc;

import com.vng.chatservice.WebSocketServiceGrpc;
import com.vng.chatservice.WebSocketServiceOuterClass;
import com.vng.chatservice.form.UserForm;
import com.vng.chatservice.global.Global;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class WebSocketService extends WebSocketServiceGrpc.WebSocketServiceImplBase {

    @Override
    public void getWebsocketInfo(WebSocketServiceOuterClass.Request request,
                                 StreamObserver<WebSocketServiceOuterClass.Response> responseObserver) {

        // Add user online
        if (!Global.listOnlineUser.stream().anyMatch(uExist -> {
            if (uExist.getUsername() == request.getUsername())
                return true;
            return false;
        })) {
            Global.listOnlineUser.add(new UserForm(request.getUsername(), request.getChatCode()));
        }

        WebSocketServiceOuterClass.Response response = WebSocketServiceOuterClass.Response.newBuilder()
                .setEndpoint("http://localhost:8080/ws").setTopic("/topic").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
