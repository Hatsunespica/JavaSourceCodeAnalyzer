import com.google.gson.Gson;
import exception.SameClassException;
import info.ClassInfo;
import info.FileInfo;
import info.ProjectInfo;
import org.eclipse.jdt.core.dom.ASTNode;
import util.Util;
import visitor.FileVisitor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Spica on 6/6/2018.
 */
public class InfoExtractor {
    private String path;
    private ProjectInfo projectInfo;
    public InfoExtractor(String path){
        this.path=path;
        projectInfo=null;
    }

    public void setPath(String path){
        this.path=path;
    }

    public ProjectInfo extract()throws SameClassException {
        projectInfo=new ProjectInfo();
        List<String> strings=Util.getFiles(path);
        for(String string:strings){
            File file=new File(string);
            ASTNode astNode = Util.getASTNode(string);
            FileVisitor fileVisitor = new FileVisitor(file.getName(),Util.getCommentNum(file));
            astNode.accept(fileVisitor);
            projectInfo.addFileInfo(fileVisitor.getFileInfo());
        }
        String check=validate();
        if(check!=null)
            throw new SameClassException("there are same class in your source code!\nClass and filename:\n"+check);
        return projectInfo;
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public String toJson(){
        return new Gson().toJson(projectInfo);
    }

    public String getPath() {
        return path;
    }

    private String validate(){
        Map<String,String> stringMap=new HashMap<String, String>();
        for(FileInfo fileInfo:projectInfo.getFileInfos())
            for (ClassInfo classInfo:fileInfo.getClassInfos())
                if(!classInfo.isInnerClass())
                    if(stringMap.containsKey(classInfo.getFullName()))
                        return classInfo.getFullName()+"\n"+stringMap.get(classInfo.getName())+"\n"+fileInfo.getName()+" "+fileInfo.getPackageName();
                    else
                        stringMap.put(classInfo.getFullName(),fileInfo.getName()+" "+fileInfo.getPackageName());
        return null;
    }
}
