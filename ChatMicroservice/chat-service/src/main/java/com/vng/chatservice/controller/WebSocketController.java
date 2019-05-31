package com.vng.chatservice.controller;

import com.vng.chatservice.global.Global;
import com.vng.chatservice.model.ChatMessage;
import com.vng.chatservice.model.Message;
import com.vng.chatservice.model.Room;
import com.vng.chatservice.repositories.MessageRepository;
import com.vng.chatservice.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {

        Optional<Room> room = roomRepository.findByIdRoom(chatMessage.getRoomId());
        if(room.isPresent()){
            messageRepository.save(new Message(chatMessage.getContent(),chatMessage.getSender(),room.get()));
        }
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        if(!roomRepository.findByIdRoom(chatMessage.getRoomId()).isPresent())
            roomRepository.save(new Room(chatMessage.getRoomId()));

        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping("/chat.getListOnlineUser")
    public void getListOnlineUser() {
        messagingTemplate.convertAndSend("/topic/onlineList", Global.listOnlineUser);
    }
    //@MessageMapping("/chat.sendMessage")
    //@SendTo("/topic/publicChatRoom")
    //public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    //return chatMessage;}
}
