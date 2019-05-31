package com.vng.chatservice.controller;

import com.vng.chatservice.form.RoomForm;
import com.vng.chatservice.model.Room;
import com.vng.chatservice.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MessageController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping(value = "/messages/{roomId}")
    public ResponseEntity<?> getMessages(@PathVariable String roomId) {

        Optional<Room> result = roomRepository.findByIdRoom(roomId);
        RoomForm roomForm = new RoomForm();

        if (result.isPresent()) {
            roomForm.setIdRoom(result.get().getIdRoom());
            roomForm.setMessages(result.get().getMessages());
            roomForm.setStatus("success");
            return ResponseEntity.ok(roomForm);
        }

        roomForm.setStatus("error");
        return ResponseEntity.badRequest().body(roomForm);
    }
}
