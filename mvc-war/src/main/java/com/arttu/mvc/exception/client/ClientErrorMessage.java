package com.arttu.mvc.exception.client;

import java.util.Objects;

public class ClientErrorMessage {
    private String message;

    ClientErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientErrorMessage that = (ClientErrorMessage) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ClientErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
