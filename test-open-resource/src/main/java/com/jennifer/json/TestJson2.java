package com.jennifer.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 5/5/2017
 */
public class TestJson2 {


    public static void main(String[] args) {
        String s ="{\"page\":\"1\",\"records\":\"1\",\"rows\":[{\"cell\":[\"4793553\",\"760647004\",\"18552424448\",\"Test!123\",\"swataccountwithdl_otherphone(3420,4425)\",\"\",\"up0101\",\"<a target=\\\"_blank\\\" href=\\\"http:\\/\\/service-up.lab.rcch.ringcentral.com\\/login\\/main.asp?LoginName=18552424448\\\\&Password=Test!123\\\" onclick='value' >Login To Account<\\/a>\"],\"id\":\"4793553\"}],\"total\":1}";

        String s2="{\"page\":\"1\",\"records\":\"1\",\"rows\":[{\"cell\":[\"4842161\",\"847961004\",\"18003423335\",\"Test!123\",\"swataccountwithdl_otherphone(1210,4488)\",\"\",\"up0101\",\"<a target=\\\"_blank\\\" href=\\\"http:\\/\\/service-up.lab.rcch.ringcentral.com\\/login\\/main.asp?LoginName=18003423335\\\\&Password=Test!123\\\" onclick='value' >Login To Account<\\/a>\"],\"id\":\"4842161\"}],\"total\":1}";
        System.out.println(s2.split(",")[4].replaceAll("\"",""));

        String s3 ="{\"cancelled\":0,\"failed\":0,\"status\":\"IN_PROGRESS\",\"success\":0,\"total\":1}";
        JSONObject resultJsonObject = JSONObject.fromObject(s3);
        System.out.println(resultJsonObject.get("status"));


        String s4 ="{\"page\":\"1\",\"records\":\"1\",\"rows\":[{\"cell\":[\"4842439\",\"849688004\",\"18882645566\",\"Test!123\",\"att.4425_3u_1hp3did\",\"\",\"up0101\",\"<a target=\\\"_blank\\\" href=\\\"http:\\/\\/service-up.lab.rcch.ringcentral.com\\/login\\/main.asp?LoginName=18882645566\\\\&Password=Test!123\\\" onclick='value' >Login To Account<\\/a>\"],\"id\":\"4842439\"}],\"total\":1}";

        JSONObject resultJsonObject4 = JSONObject.fromObject(s4);
        JSONArray jsonArray = resultJsonObject4.getJSONArray("rows");
        JSONObject json = JSONObject.fromObject(jsonArray.get(0));
        System.out.println(json.get("cell"));

    }
}
