package com.vng.chatservice.form;
import com.vng.chatservice.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RoomForm {

    private String status;

    private String idRoom;

    private List<MessageForm> messages;

    public void setMessages(List<Message> messages){
        this.messages = messages.stream().map(msg->{
            MessageForm messageForm = new MessageForm(msg.getMess(), msg.getEmail());
            return  messageForm;
        }).collect(Collectors.toList());
    }
}
