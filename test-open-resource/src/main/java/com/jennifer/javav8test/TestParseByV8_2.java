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
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * Created by IntelliJ IDEA.
// * User: com.jennifer.huang
// * Date: 10/26/2017
// */
//public class TestParseByV8_2 {
//    public static void main(String[] args) throws IOException {
//        Options options = new Options("nashorn");
//        options.set("anon.functions", true);
//        options.set("parse.only", true);
//        options.set("scripting", true);
//
//        ErrorManager errors = new ErrorManager();
//        Context context = new Context(options, errors, Thread.currentThread().getContextClassLoader());
//        Source source   = Source.sourceFor("test", "var a = 10; var b = a + 1;" +
//                "function someFunction() { return b + 1; }  ");
//        Parser parser = new Parser(context.getEnv(), source, errors);
//        FunctionNode functionNode = parser.parse();
//        Block block = functionNode.getBody();
//        List<Statement> statements = block.getStatements();
//
//
//    }
//
//}
