package lv.rvt;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
public class user {
    public String username;
    public String email;

    public user( String username, String email){
        this.username = username;
        this.email = email;
    }

    @SuppressWarnings({"unchecked", "ConvertToTryWithResources"})
    public void addUser(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Username", this.username);
        jsonObject.put("email", this.email);

        try {
           FileWriter file = new FileWriter(this.username + ".json");
           file.write(jsonObject.toJSONString());
           file.close();
        } catch (IOException e) {
            System.err.println("Unable to write data to " + this.username + ".json. Please try again later.");
        }
    }

    public void findUser(){

    }
    @Override
    public String toString(){
        return "Username: " + this.username + " \ne-mail: "+ this.email;
    }
}
