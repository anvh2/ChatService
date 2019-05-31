package com.vng.chatservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "id_room")
    private String idRoom;

    @OneToMany(mappedBy = "room")
    private List<Message> messages;

    public Room(String idRoom){
        this.idRoom = idRoom;
    }
}


