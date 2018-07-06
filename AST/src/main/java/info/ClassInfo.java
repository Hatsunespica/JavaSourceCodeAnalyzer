package info;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;

public class ClassInfo{
    private String name,modifier,superClass,packageName;
    private long codeLineNum=2,statementNum=0,commentNum=0,cyclomaticComplexityNum=0;
    private List<AttributeInfo> attributeInfos =new ArrayList<AttributeInfo>();
    private List<MethodInfo> methodInfos=new ArrayList<MethodInfo>();
    private boolean isInnerClass;
    private static final String[] MODIFIER={"public","package","private"};

    public void addMethod(MethodInfo methodInfo){
        codeLineNum+=methodInfo.getLines();
        statementNum+=methodInfo.getStatementNum();
        cyclomaticComplexityNum+=methodInfo.getCyclomaticComplexity();
        methodInfos.add(methodInfo);
    }

    public void addAttribute(AttributeInfo attributeInfo){
        attributeInfos.add(attributeInfo);
        codeLineNum+=1;
    }

    public void setCommentNum(long commentNum) {
        this.commentNum = commentNum;
    }

    public void setCyclomaticComplexityNum(long cyclomaticComplexityNum) {
        this.cyclomaticComplexityNum = cyclomaticComplexityNum;
    }

    public ClassInfo (TypeDeclaration typeDeclaration,String packageName,boolean isInnerClass){
        name=typeDeclaration.getName().toString();
        this.isInnerClass=isInnerClass;
        modifier=null;
        for(Object object: typeDeclaration.modifiers())
        if(MODIFIER[0].equals(object.toString()))
            modifier=MODIFIER[0];
        else if(MODIFIER[1].equals(object.toString()))
            modifier=MODIFIER[1];
        else if(MODIFIER[2].equals(object.toString()))
            modifier=MODIFIER[2];
        if(modifier==null)
            modifier=MODIFIER[1];

        if(typeDeclaration.getSuperclassType()==null)
            superClass=null;
        else
            superClass=typeDeclaration.getSuperclassType().toString();

        this.packageName=packageName;
    }


    public String getName() {
        return name;
    }

    public String getModifier() {
        return modifier;
    }

    public String getSuperClass() {
        return superClass;
    }

    public long getCodeLineNum() {
        return codeLineNum;
    }


    public List<MethodInfo> getMethodInfos() {
        return methodInfos;
    }

    public List<AttributeInfo> getAttributeInfos() {
        return attributeInfos;
    }

    public long getStatementNum() {
        return statementNum;
    }

    public long getCommentNum() {
        return commentNum;
    }

    public long getCyclomaticComplexityNum() {
        return cyclomaticComplexityNum;
    }

    public long getMethodNum(){
        return methodInfos.size();
    }

    public long getAttributesNum(){
        return attributeInfos.size();
    }

    public String getPackageName(){return this.packageName;}

    public String getFullName(){return this.packageName.equals("")?this.name:this.packageName+"."+this.name;}

    public boolean isInnerClass() {
        return isInnerClass;
    }
}
