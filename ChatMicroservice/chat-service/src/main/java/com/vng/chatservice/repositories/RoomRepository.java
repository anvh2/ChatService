package com.vng.chatservice.repositories;

import com.vng.chatservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

    Optional<Room> findByIdRoom(String idRoom);
}
