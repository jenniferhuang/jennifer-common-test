package com.jennifer.json;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 10/26/2017
 */
public class ConverJS2 {

    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        File f = new File("D:\\test\\swEntryPoints.js");
        // expose File object as variable to script
        engine.put("file", f);

        // evaluate a script string. The script accesses "file"
        // variable and calls method on it
        engine.eval("print(file.getAbsolutePath())");

        engine.getContext().getAttribute("AutoReceptionist");

    }


    //http://simplenspeed.com/programming/java-versions/java8/java8-nashorn-tutorial.php

    //support the command line,    Nashorn script engine can be invoked from Java programs and also from the command line tool    jjs.


}
