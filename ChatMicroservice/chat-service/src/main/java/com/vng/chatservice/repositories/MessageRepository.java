package com.vng.chatservice.repositories;

import com.vng.chatservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    Optional<List<Message>> findAllByRoom(String roomId);
}
