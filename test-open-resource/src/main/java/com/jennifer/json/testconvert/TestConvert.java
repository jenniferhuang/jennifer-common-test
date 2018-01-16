package com.jennifer.json.testconvert;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class TestConvert {



    @Test
    public void testConvertJsonToClass() throws IOException {

        File fileSystemObtainedFile = Paths.get("src/main/resources/rcBetaUserAccount.json").toFile();
        ObjectMapper mapper = new ObjectMapper();

        //conver to class
        RCNormalAccountGenerationPOJO rcNormalAccount1 =  mapper.readValue(fileSystemObtainedFile, RCNormalAccountGenerationPOJO.class);
        System.out.println(rcNormalAccount1.getCompanyEmailDomain());

        rcNormalAccount1.setCompanyEmailDomain("testjennifer@ringcentral4444555.com"); //change to this


        //write to json file
        mapper.writeValue(fileSystemObtainedFile,rcNormalAccount1);  //write back to file
        String jsonInString = mapper.writeValueAsString(rcNormalAccount1);



        rcNormalAccount1.setCompanyEmailDomain("testjennifer@ringcentral2.com");  //change to this
        rcNormalAccount1 =  mapper.readValue(fileSystemObtainedFile, RCNormalAccountGenerationPOJO.class);  //over write by the file

        System.out.println(rcNormalAccount1.getCompanyEmailDomain());
    }



    @Test
    public void testJsonConverterHelper() throws IOException {
        RCNormalAccountGenerationPOJO rcNormalAccount1 = JsonCoverterHelper.convertToJavaFromFile("src/main/resources/rcBetaUserAccount.json");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(rcNormalAccount1);
        JsonCoverterHelper.convertToAccountPoolJson("src/main/resources/rcBetaUserAccountInAccountPool.json", jsonInString);
    }

}
