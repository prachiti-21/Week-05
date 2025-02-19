package com.json.practiceproblems;
import org.json.*;
public class JsonObject {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Alice");
        jsonObject.put("age", 25);
        JSONArray subjects=new JSONArray();
        subjects.put("Chemistry");
        subjects.put("Physics");
        subjects.put("Maths");
        jsonObject.put("subject",subjects);
        System.out.println(jsonObject.toString());

    }
}
