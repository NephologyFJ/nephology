package org.nephology;

import org.springframework.data.annotation.Id;

public class Hello {

    @Id private String id;

    public String userName;
    private String message;

    public Hello() {
    }

    public Hello(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() { return userName; }

    public void setUserName() { this.userName = userName; }
}