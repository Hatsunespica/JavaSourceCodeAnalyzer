package info;

import info.ClassInfo;

import java.util.ArrayList;
import java.util.List;

public class FileInfo {
    private String name,packageName;
    private long codeLineNum=0,statementNum=0,commentLineNum=0,cyclomaticComplexity=0,methodNum=0,attributesNum=0;
    private List<ClassInfo> classInfos=new ArrayList<ClassInfo>();
    private List<String> importPackages=new ArrayList<String>();


    public FileInfo(String name,long commentLineNum){
        this.name=name;
        this.commentLineNum=commentLineNum;
        packageName="";
    }

    public long getClassNum(){
        return classInfos.size();
    }

    public void addClass(ClassInfo classInfo){
        classInfos.add(classInfo);
        cyclomaticComplexity+=classInfo.getCyclomaticComplexityNum();
        statementNum+=classInfo.getStatementNum();
        codeLineNum+=classInfo.getCodeLineNum();
        commentLineNum+=classInfo.getCommentNum();
        methodNum+=classInfo.getMethodNum();
        attributesNum+=classInfo.getAttributesNum();
    }

    public void addImportPackage(String packageName){
        importPackages.add(packageName);
    }

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public String getName() {
        return name;
    }

    public long getCodeLineNum() {

        return codeLineNum;
    }


    public long getMethodNum() {
        return methodNum;
    }

    public long getAttributesNum() {
        return attributesNum;
    }

    public void setCodeLineNum(long codeLineNum) {
        this.codeLineNum = codeLineNum;
    }

    public long getStatementNum() {
        return statementNum;
    }

    public void setStatementNum(long statementNum) {
        this.statementNum = statementNum;
    }

    public long getCommentLineNum() {
        return commentLineNum;
    }

    public void setCommentLineNum(long commentLineNum) {
        this.commentLineNum = commentLineNum;
    }

    public long getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImportPackages() {
        return importPackages;
    }
}
