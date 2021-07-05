package com.subba.binarytree;

import java.io.Serializable;
import java.util.Objects;

/**
 * Message object
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -54527985349234L;

    Message() {
    }

    Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    private int id;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message otherMessage = (Message) o;
        return id == otherMessage.id && message.equals(otherMessage.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }
}
