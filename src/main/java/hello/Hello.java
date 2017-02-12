package hello;

import org.springframework.data.annotation.Id;


public class Hello {

    @Id
    public String id;

    private String message;

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
}