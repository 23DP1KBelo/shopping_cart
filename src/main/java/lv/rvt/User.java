package lv.rvt;

public class User {
    public String username;
    public String email;

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public String toCsvRow() {
        return this.username + ", " + this.email;
    }

    @Override
    public String toString() {
        return this.username + " " + this.email;
    }
}
