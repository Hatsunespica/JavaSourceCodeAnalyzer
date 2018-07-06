package visitor;

import info.MethodInfo;
import util.Util;
import org.eclipse.jdt.core.dom.*;

public class MethodNodeVisitor extends ASTVisitor {
    private MethodInfo methodInfo;

    public MethodNodeVisitor(MethodDeclaration methodDeclaration){
        methodInfo=new MethodInfo(methodDeclaration);
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    @Override
    public boolean visit(MethodInvocation methodInvocation){
        return true;
    }

    @Override
    public boolean visit(ForStatement forStatement){
        methodInfo.setCyclomaticComplexity(methodInfo.getCyclomaticComplexity()+1);
        return true;
    }

    @Override
    public boolean visit(EnhancedForStatement enhancedForStatement){
        methodInfo.setCyclomaticComplexity(methodInfo.getCyclomaticComplexity()+1);
        return true;
    }

    @Override
    public boolean visit(WhileStatement whileStatement){
        methodInfo.setCyclomaticComplexity(methodInfo.getCyclomaticComplexity()+1);
        return true;
    }

    @Override
    public boolean visit(SwitchCase switchCase){
        if(!switchCase.toString().equals("default :"))
            methodInfo.setCyclomaticComplexity(methodInfo.getCyclomaticComplexity()+1);
        return true;
    }

    @Override
    public boolean visit(IfStatement ifStatement){
        methodInfo.setCyclomaticComplexity(methodInfo.getCyclomaticComplexity()+ Util.count(ifStatement.toString().replace("\n"," "),"if"));
        return true;
    }

    @Override
    public boolean visit(DoStatement doStatement){
        methodInfo.setCyclomaticComplexity(methodInfo.getCyclomaticComplexity()+1);
        return true;
    }

    @Override
    public boolean visit(ConditionalExpression conditionalExpression){
        methodInfo.setCyclomaticComplexity(methodInfo.getCyclomaticComplexity()+1);
        return true;
    }
}
