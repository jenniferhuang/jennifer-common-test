package com.jennifer.javaproperties.testoveridesystemp;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertiesGet {

    private static final String CONFIGURATION_FILE_PATH_DEFAULT = "configurations.properties";  //file 未指定的情况下， 并且属性没在runner里面指定的话， 用configuration.xml里面的配置。

    private static final String CONFIGURATION_FILE_PATH_SPECIFIC = System.getProperty("configuration.file");

    private static final String CONFIGURATION_FILE_PATH_USED = CONFIGURATION_FILE_PATH_SPECIFIC ==null? CONFIGURATION_FILE_PATH_DEFAULT: CONFIGURATION_FILE_PATH_SPECIFIC;






    public void setSystemPropertiesFromFile(){
        System.out.println(System.getProperty("user.dir"));
        try {
            File fileSystemObtainedFile = Paths.get(System.getProperty("user.dir")+ "/"+CONFIGURATION_FILE_PATH_USED).toFile();


            FileInputStream propertiesFile = new FileInputStream( fileSystemObtainedFile);

            Properties fileProperties = new Properties();
            fileProperties.load(propertiesFile);

            Properties systemProperties = System.getProperties();
            System.out.println(systemProperties.size());

            Enumeration<Object> fileKeys = fileProperties.keys();
            while (fileKeys.hasMoreElements()){
                String fileKey = (String) fileKeys.nextElement();
                if(!systemProperties.containsKey(fileKey)){
                    systemProperties.put(fileKey, fileProperties.get(fileKey));
                }
            }
            System.setProperties(systemProperties);  //overwrite system properties
            System.setProperty("TOD.nodeUrl","Update.xxx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The file "+CONFIGURATION_FILE_PATH_USED + "is not found! please add the file to configure your properties.");
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        PropertiesGet propertiesGet = new PropertiesGet();
//        propertiesGet.setSystemPropertiesFromFile();
////        System.getProperties().list(System.out);
//
//
//        Properties properties = System.getProperties();
//        Enumeration<Object> fileKeys = properties.keys();
//        while (fileKeys.hasMoreElements()){
//            String fileKey = (String) fileKeys.nextElement();
//            System.out.println(fileKey + "="+properties.get(fileKey));
//        }
//
//    }




    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

        //out put: /Users/jennifer.huang/Documents/projects/myself/jennifer-common-test
    }
    @Test
    public void test(){
        System.out.println(System.getProperty("user.dir"));

        //out put: /Users/jennifer.huang/Documents/projects/myself/jennifer-common-test/test-open-resource
    }




    //vm properties test:

    /**
     * -DTOD.hubUrl=update111 -DTOD.hubNode=update222 -DTOD.hubPort=update333
     *
     */


    /**
     * -Dconfiguration.file=configurationsXM.properties
     */











}
