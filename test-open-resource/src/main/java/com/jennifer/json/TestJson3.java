package com.jennifer.json;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 5/15/2017
 */
import org.json.JSONException;
import org.json.JSONObject;
public class TestJson3 {

    public static void main(String[] args) {
        String s3 ="{\"cancelled\":0,\"failed\":0,\"status\":\"IN_PROGRESS\",\"success\":0,\"total\":1}";

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(        jsonObject.get("status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
