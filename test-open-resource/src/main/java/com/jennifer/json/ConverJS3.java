//package com.com.jennifer.json;
//
//import org.mozilla.javascript.CompilerEnvirons;
//import org.mozilla.javascript.IRFactory;
//import org.mozilla.javascript.ast.AstNode;
//import org.mozilla.javascript.ast.AstRoot;
//
//import java.io.FileReader;
//
///**
// * Created by IntelliJ IDEA.
// * User: com.jennifer.huang
// * Date: 10/26/2017
// */
//public class ConverJS3 {
//
//    public void parseJS (String filePath) throws Exception
//    {
//        CompilerEnvirons env = new CompilerEnvirons();
//
//        env.setRecoverFromErrors(true);
//
//        FileReader strReader = new FileReader(filePath);
//
//        IRFactory factory = new IRFactory(env, new JSErrorReporter());
//        AstRoot rootNode = factory.parse(strReader, null, 0);
//
//        JSNodeVisitor nodeVisitor = new JSNodeVisitor();
//
//        rootNode.visit(nodeVisitor);
//
//        nodeVisitor.getRoot().visit(new JSSymbolVisitor());
//    }
//
//    private void addToParent(AstNode node)
//    {
//        if (root == null)
//        {
//            root = new JSSymbol(node);
//            functionsStack.push(root);
//            currentFuncEndOffset = node.getAbsolutePosition() + node.getLength();
//            return;
//        }
//
//        if (functionsStack.size() == 0)
//            return;
//
//        int nodeType = node.getType();
//
//        //we will track only variables and functions
//        if (nodeType != Token.FUNCTION && nodeType != Token.VAR && nodeType != Token.OBJECTLIT &&
//                !(nodeType == Token.NAME && node.getParent() instanceof ObjectProperty))
//        {
//            if (isVariableName(node))
//            {
//                //check if it is in the current function
//                String symbolName = ((Name)node).getIdentifier();
//                JSSymbol currentSymContainer = functionsStack.peek();
//                if (!currentSymContainer.childExist(symbolName))
//                {
//                    //this is a global symbol
//                    root.addChild(node);
//                }
//            }
//            return;
//        }
//
//        if (node.getType() == Token.VAR && node instanceof VariableInitializer == false)
//            return;
//
//        JSSymbol currSym = null;
//
//        JSSymbol parent = functionsStack.peek();
//        if (parent.getNode().getAbsolutePosition() + parent.getNode().getLength() > node.getAbsolutePosition())
//        {
//            currSym = new JSSymbol(node);
//            parent.addChild(currSym);
//        }
//        else //outside current function boundary
//        {
//            //pop current parent
//            functionsStack.pop();
//            addToParent(node);
//            return;
//        }
//
//        //currSym is already set above
//        if (nodeType == Token.FUNCTION || nodeType == Token.OBJECTLIT)
//        {
//            AstNode parentNode = node.getParent();
//            AstNode leftNode = null;
//            if (parentNode.getType() == Token.ASSIGN)
//            {
//                leftNode = ((Assignment)parentNode).getLeft();
//            }
//            else if (parentNode instanceof ObjectProperty)
//            {
//                leftNode = ((ObjectProperty)parentNode).getLeft();
//            }
//
//            if (leftNode instanceof Name)
//                currSym.setName(((Name)leftNode).getIdentifier());
//
//            functionsStack.push(currSym);
//            currentFuncEndOffset = node.getAbsolutePosition() + node.getLength();
//        }
//    }
//}
