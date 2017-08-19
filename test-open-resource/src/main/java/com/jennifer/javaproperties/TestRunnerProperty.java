package com.jennifer.javaproperties;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/17/2017
 */
public class TestRunnerProperty {

    @Test
    public void testcase(){
        System.out.println("run with testng runner+++++++++.");
        Assert.assertEquals("run with testng runner","run with testng runner");

        System.out.println(System.getProperty("browsertype"));
    }

    /**

     1.add testng.xml in the same directory with pom's

     2.* Add this plug to run testng.xml from maven command line
     * <plugin>
     <groupId>org.apache.maven.plugins</groupId>
     <artifactId>maven-surefire-plugin</artifactId>
     <version>2.19.1</version>
     <configuration>
     <suiteXmlFiles>
     <suiteXmlFile>testng.xml</suiteXmlFile>
     </suiteXmlFiles>
     </configuration>
     </plugin>
     3.run
     mvn test -DsuiteXmlFile=testng.xml -Dbrowsertype=jennifer
     */

}
