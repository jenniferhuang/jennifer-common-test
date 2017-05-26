package com.jennifer.json;


import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 2/20/2017
 */
//http://www.cnblogs.com/snake-hand/p/3167787.html

public class TestJson {

    @Test
    public void test(){
        List<String> userExtensions = new ArrayList<String>();
        userExtensions.add("123");
        userExtensions.add("456");

        JSONObject jsonObj1  = new JSONObject();
        jsonObj1.put("departmentId","2222");
        jsonObj1.put("addedExtensionIds",userExtensions);
        System.out.println(jsonObj1);


        JSONObject jsonObj2  = new JSONObject();
        jsonObj2.put("departmentId","3333");
        jsonObj2.put("addedExtensionIds",userExtensions);
        System.out.println(jsonObj1);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObj1);
        jsonArray.add(jsonObj2);
        System.out.println(jsonArray);

        JSONObject jsonObj3  = new JSONObject();
        jsonObj3.put("items",jsonArray);
        System.out.println(jsonObj3);

    }

    @Test
    public void test2(){
        JSONObject resultJsonObject = JSONObject.fromObject(s);
        JSONArray jsonArray = resultJsonObject.getJSONArray("records");
        for(Object object:jsonArray){
            JSONObject jsonObject = JSONObject.fromObject(object);
            System.out.println(jsonObject.get("id"));

        }



    }




    String s ="{\n" +
            "  \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension?status=Enabled&type=User&page=1&perPage=100\",\n" +
            "  \"records\": [\n" +
            "    {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225588003\",\n" +
            "      \"id\": 225588003,\n" +
            "      \"extensionNumber\": \"101\",\n" +
            "      \"contact\": {\n" +
            "        \"firstName\": \"Something\",\n" +
            "        \"lastName\": \"New\",\n" +
            "        \"company\": \"RingCentral Inc.\",\n" +
            "        \"email\": \"mm1+1487645120523-1503722@dins.ru\",\n" +
            "        \"businessPhone\": \"+19137322302\",\n" +
            "        \"emailAsLoginName\": true,\n" +
            "        \"pronouncedName\": {\n" +
            "          \"type\": \"Default\",\n" +
            "          \"text\": \"Something New\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"name\": \"Something New\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"status\": \"Enabled\",\n" +
            "      \"permissions\": {\n" +
            "        \"admin\": {\n" +
            "          \"enabled\": true\n" +
            "        },\n" +
            "        \"internationalCalling\": {\n" +
            "          \"enabled\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"profileImage\": {\n" +
            "        \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225588003/profile-image\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225590003\",\n" +
            "      \"id\": 225590003,\n" +
            "      \"extensionNumber\": \"102\",\n" +
            "      \"contact\": {\n" +
            "        \"firstName\": \"Admin\",\n" +
            "        \"lastName\": \"User\",\n" +
            "        \"email\": \"mm1+1487645122680-7734470@dins.ru\",\n" +
            "        \"emailAsLoginName\": true,\n" +
            "        \"pronouncedName\": {\n" +
            "          \"type\": \"Default\",\n" +
            "          \"text\": \"Admin User\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"name\": \"Admin User\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"status\": \"Enabled\",\n" +
            "      \"permissions\": {\n" +
            "        \"admin\": {\n" +
            "          \"enabled\": true\n" +
            "        },\n" +
            "        \"internationalCalling\": {\n" +
            "          \"enabled\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"profileImage\": {\n" +
            "        \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225590003/profile-image\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225594003\",\n" +
            "      \"id\": 225594003,\n" +
            "      \"extensionNumber\": \"103\",\n" +
            "      \"contact\": {\n" +
            "        \"firstName\": \"jenniferU33\",\n" +
            "        \"lastName\": \"New3\",\n" +
            "        \"company\": \"RingCentral Inc.\",\n" +
            "        \"email\": \"jenniferU33219e59fa-d738-4737-943e-b0b6b8d23f97@dins.cn\",\n" +
            "        \"businessPhone\": \"+12565317302\",\n" +
            "        \"emailAsLoginName\": true,\n" +
            "        \"pronouncedName\": {\n" +
            "          \"type\": \"Default\",\n" +
            "          \"text\": \"jenniferU33 New3\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"name\": \"jenniferU33 New3\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"status\": \"Enabled\",\n" +
            "      \"permissions\": {\n" +
            "        \"admin\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"internationalCalling\": {\n" +
            "          \"enabled\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"profileImage\": {\n" +
            "        \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225594003/profile-image\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225595003\",\n" +
            "      \"id\": 225595003,\n" +
            "      \"extensionNumber\": \"104\",\n" +
            "      \"contact\": {\n" +
            "        \"firstName\": \"jenniferU44\",\n" +
            "        \"lastName\": \"New4\",\n" +
            "        \"company\": \"RingCentral Inc.\",\n" +
            "        \"email\": \"jenniferU44a3c394e7-755b-46a4-83b3-6b78997dbec1@dins.cn\",\n" +
            "        \"businessPhone\": \"+12565317302\",\n" +
            "        \"emailAsLoginName\": true,\n" +
            "        \"pronouncedName\": {\n" +
            "          \"type\": \"Default\",\n" +
            "          \"text\": \"jenniferU44 New4\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"name\": \"jenniferU44 New4\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"status\": \"Enabled\",\n" +
            "      \"permissions\": {\n" +
            "        \"admin\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"internationalCalling\": {\n" +
            "          \"enabled\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"profileImage\": {\n" +
            "        \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225595003/profile-image\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225598003\",\n" +
            "      \"id\": 225598003,\n" +
            "      \"extensionNumber\": \"105\",\n" +
            "      \"contact\": {\n" +
            "        \"firstName\": \"jenniferU11\",\n" +
            "        \"lastName\": \"New1\",\n" +
            "        \"company\": \"RingCentral Inc.\",\n" +
            "        \"email\": \"jenniferU1190fc3bc6-7ac7-48d4-9742-f7974ca06724@dins.cn\",\n" +
            "        \"businessPhone\": \"+12565317302\",\n" +
            "        \"emailAsLoginName\": true,\n" +
            "        \"pronouncedName\": {\n" +
            "          \"type\": \"Default\",\n" +
            "          \"text\": \"jenniferU11 New1\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"name\": \"jenniferU11 New1\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"status\": \"Enabled\",\n" +
            "      \"permissions\": {\n" +
            "        \"admin\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"internationalCalling\": {\n" +
            "          \"enabled\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"profileImage\": {\n" +
            "        \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225598003/profile-image\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225599003\",\n" +
            "      \"id\": 225599003,\n" +
            "      \"extensionNumber\": \"106\",\n" +
            "      \"contact\": {\n" +
            "        \"firstName\": \"jenniferU22\",\n" +
            "        \"lastName\": \"New2\",\n" +
            "        \"company\": \"RingCentral Inc.\",\n" +
            "        \"email\": \"jenniferU2270223890-6c3d-435a-aac3-64bf8e058576@dins.cn\",\n" +
            "        \"businessPhone\": \"+12565317302\",\n" +
            "        \"emailAsLoginName\": true,\n" +
            "        \"pronouncedName\": {\n" +
            "          \"type\": \"Default\",\n" +
            "          \"text\": \"jenniferU22 New2\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"name\": \"jenniferU22 New2\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"status\": \"Enabled\",\n" +
            "      \"permissions\": {\n" +
            "        \"admin\": {\n" +
            "          \"enabled\": false\n" +
            "        },\n" +
            "        \"internationalCalling\": {\n" +
            "          \"enabled\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"profileImage\": {\n" +
            "        \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension/225599003/profile-image\"\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"paging\": {\n" +
            "    \"page\": 1,\n" +
            "    \"totalPages\": 1,\n" +
            "    \"perPage\": 100,\n" +
            "    \"totalElements\": 6,\n" +
            "    \"pageStart\": 0,\n" +
            "    \"pageEnd\": 5\n" +
            "  },\n" +
            "  \"navigation\": {\n" +
            "    \"firstPage\": {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension?status=Enabled&type=User&page=1&perPage=100\"\n" +
            "    },\n" +
            "    \"lastPage\": {\n" +
            "      \"uri\": \"http://api-devfre.lab.rcch.ringcentral.com/restapi/v1.0/account/225588003/extension?status=Enabled&type=User&page=1&perPage=100\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

}
