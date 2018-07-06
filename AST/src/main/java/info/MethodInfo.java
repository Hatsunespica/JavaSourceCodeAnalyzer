package info;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import util.Util;

public class MethodInfo {
    private String name,modifier,returnType;
    private long cyclomaticComplexity,lines, parametersNum,statementNum;
    private static final String[] MODIFIER={"public","protected","private"};

    public MethodInfo(MethodDeclaration methodDeclaration) {
        name = methodDeclaration.getName().toString();
        parametersNum = methodDeclaration.parameters().size();
        lines = methodDeclaration.toString().split("\n").length;

        cyclomaticComplexity=1;


        modifier=null;
        for(Object object: methodDeclaration.modifiers())
            if(MODIFIER[0].equals(object.toString()))
                modifier=MODIFIER[0];
            else if(MODIFIER[1].equals(object.toString()))
                modifier=MODIFIER[1];
            else if(MODIFIER[2].equals(object.toString()))
                modifier=MODIFIER[2];
        if(modifier==null)
            modifier=MODIFIER[1];

        if(methodDeclaration.getReturnType2()!=null)
            returnType=methodDeclaration.getReturnType2().toString();
        else
            returnType=null;
        statementNum=(methodDeclaration.getBody()==null)?0:Util.count(methodDeclaration.getBody().toString(),";");
    }


    public String getName() {
        return name;
    }

    public String getModifier() {
        return modifier;
    }

    public String getReturnType() {
        return returnType;
    }

    public long getStatementNum() {
        return statementNum;
    }


    public void setName(String name) {
        this.name = name;
    }

    public long getParametersNum() {
        return parametersNum;
    }

    public long getLines() {
        return lines;
    }

    public long getCyclomaticComplexity(){
        return cyclomaticComplexity;
    }
    public void setCyclomaticComplexity(long cyclomaticComplexity){
        this.cyclomaticComplexity=cyclomaticComplexity;
    }
}
