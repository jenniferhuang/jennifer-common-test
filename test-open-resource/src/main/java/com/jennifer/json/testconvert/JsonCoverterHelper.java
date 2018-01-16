package com.jennifer.json.testconvert;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonCoverterHelper {


    public static RCNormalAccountGenerationPOJO convertToJavaFromFile(String filePath) throws IOException { //filePath eg:   "src/test/resources/rcBetaUserAccount.json"
        File fileSystemObtainedFile = Paths.get(filePath).toFile();
        ObjectMapper mapper = new ObjectMapper();
        RCNormalAccountGenerationPOJO rcNormalAccount =  mapper.readValue(fileSystemObtainedFile, RCNormalAccountGenerationPOJO.class);
        return rcNormalAccount;
    }

    public static RCNormalAccountGenerationPOJO convertToJavaFromString(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RCNormalAccountGenerationPOJO rcNormalAccount =  mapper.readValue(jsonString, RCNormalAccountGenerationPOJO.class);
        return rcNormalAccount;
    }




    private static final String USERS_KEY = "users";
    private static final String GUESTS_KEY = "guests";
    private static final String TEAMS_KEY = "teams";

    public static void convertToAccountPoolJson(String storedFilePath, String jsonString) throws IOException {
        File fileSystemObtainedFile = Paths.get(storedFilePath).toFile();

        JSONObject wholeObject = JSONObject.fromObject(jsonString);

        Map newUsersMap = new HashMap();
        Map newTeamsMap = new HashMap();

        //tackle with users
        JSONArray users = wholeObject.getJSONArray(USERS_KEY);
        for(Object user:users){
            newUsersMap.put(JSONObject.fromObject(user).get("id"), removeIdAndNewAnotherJSONObject(user));
        }

        //tackle with guests
        JSONArray guests = wholeObject.getJSONArray(GUESTS_KEY);
        for(Object guest:guests){
            newUsersMap.put(JSONObject.fromObject(guest).get("id"), removeIdAndNewAnotherJSONObject(guest));
        }

        //tackle with teams
        JSONArray teams = wholeObject.getJSONArray(TEAMS_KEY);
        for(Object team:teams){
            newTeamsMap.put(JSONObject.fromObject(team).get("id"), removeIdAndNewAnotherJSONObject(team));
        }

        wholeObject.remove(USERS_KEY);
        wholeObject.remove(GUESTS_KEY);
        wholeObject.remove(TEAMS_KEY);
        wholeObject.put(USERS_KEY, newUsersMap);
        wholeObject.put(TEAMS_KEY, newTeamsMap);


        FileWriter fileWriter = new FileWriter(fileSystemObtainedFile);
        fileWriter.write(wholeObject.toString());
        fileWriter.flush();
        fileWriter.close();
    }


    private static JSONObject removeIdAndNewAnotherJSONObject(Object originObject){
        JSONObject originJSONObject = JSONObject.fromObject(originObject);
        String id = (String) originJSONObject.get("id");
        originJSONObject.remove("id");
        JSONObject newJSONObject = new JSONObject();
        Iterator<String> sIterator = originJSONObject.keys();
        while(sIterator.hasNext()){
            String key = sIterator.next();
            newJSONObject.put(key, originJSONObject.getString(key));
        }
        return newJSONObject;
    }
}
