package com.vng.chatservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String mess;

    private String email;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    public Message(String mess, String email, Room room){
        this.mess = mess;
        this.email = email;
        this.room = room;
    }
}
