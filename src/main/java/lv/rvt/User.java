package lv.rvt;

public class User {
    private String username;
    private  String email;
    public static String currentUsername;
    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public User(String username){
        this.username = username;
    }

    public String toCsvRow() {
        return this.username + ", " + this.email;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }
    
    @Override
    public String toString() {
        return this.username + " " + this.email;
    }

}
