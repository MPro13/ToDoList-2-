package entity;

public class ToDo {

    private int id;
    private int user_id;
    private String message;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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
        return "ToDo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", message='" + message + '\'' +
                '}';
    }
}
