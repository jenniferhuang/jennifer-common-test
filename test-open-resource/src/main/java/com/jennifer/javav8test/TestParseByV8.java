//package com.com.jennifer.javav8test;
//import jdk.nashorn.internal.ir.Block;
//import jdk.nashorn.internal.ir.FunctionNode;
//import jdk.nashorn.internal.ir.Statement;
//import jdk.nashorn.internal.parser.Parser;
//import jdk.nashorn.internal.runtime.Context;
//import jdk.nashorn.internal.runtime.ErrorManager;
//import jdk.nashorn.internal.runtime.Source;
//import jdk.nashorn.internal.runtime.options.Options;
//
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * Created by IntelliJ IDEA.
// * User: com.jennifer.huang
// * Date: 10/26/2017
// */
//public class TestParseByV8 {
//    public static void main(String[] args) throws IOException {
//        Options options = new Options("nashorn");
//        options.set("anon.functions", true);
//        options.set("parse.only", true);
//        options.set("scripting", true);
//        options.set("language", "es6");
//
//        ErrorManager errors = new ErrorManager();
//        Context context = new Context(options, errors, Thread.currentThread().getContextClassLoader());
//        File f = new File("D:\\test\\swEntryPoints2.js");
//        Source source   = Source.sourceFor("test", f);
//        Parser parser = new Parser(context.getEnv(), source, errors);
//        FunctionNode functionNode = parser.parse();
//        Block block = functionNode.getBody();
//        List<Statement> statements = block.getStatements();
//
//
//
//    }
//
//}
