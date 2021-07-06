package com.project.storage.commons.exceptions.messages;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {

    private Type type;
    private String description;
    private String alias;

    public Message(Type type, String description, String alias) {
        this.type = type;
        this.description = description;
        this.alias = alias;
    }


}
