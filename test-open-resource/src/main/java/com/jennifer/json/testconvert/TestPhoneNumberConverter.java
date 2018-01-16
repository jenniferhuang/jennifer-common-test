package com.jennifer.json.testconvert;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.ListOrderedMap;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPhoneNumberConverter {




    @Test
    public void tackleWithGetPhoneNumber(){
        Map<Long, List<Long>> map = new HashMap<>();

        JSONObject wholeObject = JSONObject.fromObject(s);

        JSONArray jsonArray =  wholeObject.getJSONArray("records");

        for (Object temp : jsonArray) {
            JSONObject map1 = JSONObject.fromObject(temp);
            Long phoneNumber = Long.valueOf((String) map1.get("phoneNumber"));
            if (map1.get("extension") == null)
                continue;
            Long extNumber = Long.valueOf(((Map<String, String>) map1.get("extension")).get("extensionNumber"));
            if (map.get(extNumber) != null) {
                map.get(extNumber).add(phoneNumber);
            } else {
                List<Long> phoneNumbers = new ArrayList<>();
                phoneNumbers.add(phoneNumber);
                map.put(extNumber, phoneNumbers);
            }
        }
        map.isEmpty();
    }






    String s = "{\n" +
            "    \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number?page=1&perPage=100\",\n" +
            "    \"records\": [\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206905004\",\n" +
            "            \"id\": 1206905004,\n" +
            "            \"phoneNumber\": \"+13055573333\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Miami, FL\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558561004\",\n" +
            "                \"id\": 2558561004,\n" +
            "                \"extensionNumber\": \"102\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206892004\",\n" +
            "            \"id\": 1206892004,\n" +
            "            \"phoneNumber\": \"+13462225566\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"La Porte, TX\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558548004\",\n" +
            "                \"id\": 2558548004,\n" +
            "                \"extensionNumber\": \"101\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206903004\",\n" +
            "            \"id\": 1206903004,\n" +
            "            \"phoneNumber\": \"+13474605576\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"New York City, NY\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558561004\",\n" +
            "                \"id\": 2558561004,\n" +
            "                \"extensionNumber\": \"102\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206894004\",\n" +
            "            \"id\": 1206894004,\n" +
            "            \"phoneNumber\": \"+14072833333\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Orlando, FL\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558548004\",\n" +
            "                \"id\": 2558548004,\n" +
            "                \"extensionNumber\": \"101\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206898004\",\n" +
            "            \"id\": 1206898004,\n" +
            "            \"phoneNumber\": \"+14322965560\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Terminal, TX\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558562004\",\n" +
            "                \"id\": 2558562004,\n" +
            "                \"extensionNumber\": \"103\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206885004\",\n" +
            "            \"id\": 1206885004,\n" +
            "            \"phoneNumber\": \"+15042795556\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"New Orleans, LA\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558548004\",\n" +
            "                \"id\": 2558548004,\n" +
            "                \"extensionNumber\": \"101\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206890004\",\n" +
            "            \"id\": 1206890004,\n" +
            "            \"phoneNumber\": \"+15628612222\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Downey, CA\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558562004\",\n" +
            "                \"id\": 2558562004,\n" +
            "                \"extensionNumber\": \"103\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206904004\",\n" +
            "            \"id\": 1206904004,\n" +
            "            \"phoneNumber\": \"+17134294444\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Houston, TX\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558561004\",\n" +
            "                \"id\": 2558561004,\n" +
            "                \"extensionNumber\": \"102\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206899004\",\n" +
            "            \"id\": 1206899004,\n" +
            "            \"phoneNumber\": \"+17706185555\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Atlanta Northwest, GA\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558562004\",\n" +
            "                \"id\": 2558562004,\n" +
            "                \"extensionNumber\": \"103\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206893004\",\n" +
            "            \"id\": 1206893004,\n" +
            "            \"phoneNumber\": \"+17865295555\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"North Dade, FL\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558548004\",\n" +
            "                \"id\": 2558548004,\n" +
            "                \"extensionNumber\": \"101\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206896004\",\n" +
            "            \"id\": 1206896004,\n" +
            "            \"phoneNumber\": \"+18046843333\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Hayes, VA\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558562004\",\n" +
            "                \"id\": 2558562004,\n" +
            "                \"extensionNumber\": \"103\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206888004\",\n" +
            "            \"id\": 1206888004,\n" +
            "            \"phoneNumber\": \"+18146194444\",\n" +
            "            \"paymentType\": \"Local\",\n" +
            "            \"location\": \"Johnstown, PA\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"DirectNumber\",\n" +
            "            \"extension\": {\n" +
            "                \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/extension/2558561004\",\n" +
            "                \"id\": 2558561004,\n" +
            "                \"extensionNumber\": \"102\"\n" +
            "            },\n" +
            "            \"status\": \"Normal\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number/1206871004\",\n" +
            "            \"id\": 1206871004,\n" +
            "            \"phoneNumber\": \"+18779998255\",\n" +
            "            \"paymentType\": \"TollFree\",\n" +
            "            \"type\": \"VoiceFax\",\n" +
            "            \"usageType\": \"MainCompanyNumber\",\n" +
            "            \"status\": \"Normal\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"paging\": {\n" +
            "        \"page\": 1,\n" +
            "        \"totalPages\": 1,\n" +
            "        \"perPage\": 100,\n" +
            "        \"totalElements\": 13,\n" +
            "        \"pageStart\": 0,\n" +
            "        \"pageEnd\": 12\n" +
            "    },\n" +
            "    \"navigation\": {\n" +
            "        \"firstPage\": {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number?page=1&perPage=100\"\n" +
            "        },\n" +
            "        \"lastPage\": {\n" +
            "            \"uri\": \"http://api-up.lab.rcch.ringcentral.com/restapi/v1.0/account/2558548004/phone-number?page=1&perPage=100\"\n" +
            "        }\n" +
            "    }\n" +
            "}";


}

