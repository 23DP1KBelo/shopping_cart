package lv.rvt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParserExample {
    // public static void main(String args[]) {
    //     //Creating a JSONParser object
    //     JSONParser jsonParser = new JSONParser();
    //     try {
    //        //Parsing the contents of the JSON file
    //        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("test.json"));
    //        String id = (String) jsonObject.get("ID");
    //        String first_name = (String) jsonObject.get("First_Name");
    //        System.out.println("Contents of the JSON are: ");
    //        System.out.println("ID :" + id);
    //        System.out.println("First name: " + first_name);
    //     } catch (FileNotFoundException e) {
    //        e.printStackTrace();
    //     } catch (IOException e) {
    //        e.printStackTrace();
    //     } catch (ParseException e) {
    //        e.printStackTrace();
    //     }
    //  }
}