package com.project.storage.storagems.exceptions.messages;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {

    public Message(Type type, String description, String alias) {
        this.type = type;
        this.description = description;
        this.alias = alias;
    }

    Type type;
    String description;
    String alias;
}
