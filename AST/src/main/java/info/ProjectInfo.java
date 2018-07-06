package info;

import info.FileInfo;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {
    private long codeLineNum,statementNum,commentNum,cyclomaticComplexity,methodNum,attributesNum,longestMethod;
    private String longestMethodName;
    private List<FileInfo> fileInfos=new ArrayList<FileInfo>();

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }

    public ProjectInfo(){
        codeLineNum=0;
        statementNum=0;
        commentNum=0;
        cyclomaticComplexity=0;
        methodNum=attributesNum=0;
    }

    public void addFileInfo(FileInfo fileInfo){
        codeLineNum+=fileInfo.getCodeLineNum();
        statementNum+=fileInfo.getStatementNum();
        commentNum+=fileInfo.getCommentLineNum();
        cyclomaticComplexity+=fileInfo.getCyclomaticComplexity();
        methodNum+=fileInfo.getMethodNum();
        attributesNum+=fileInfo.getAttributesNum();
        fileInfos.add(fileInfo);
    }

    public long getCodeLineNum() {
        return codeLineNum;
    }

    public long getStatementNum() {
        return statementNum;
    }

    public long getCommentNum() {
        return commentNum;
    }

    public long getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public long getLongestMethod() {
        return longestMethod;
    }

    public long getMethodNum() {
        return methodNum;
    }

    public long getAttributesNum() {
        return attributesNum;
    }

    public void setLongestMethod(long longestMethod) {
        this.longestMethod = longestMethod;
    }

    public String getLongestMethodName() {
        return longestMethodName;
    }

    public void setLongestMethodName(String longestMethodName) {
        this.longestMethodName = longestMethodName;
    }
}
