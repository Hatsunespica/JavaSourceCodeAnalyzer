package analyzer;

import info.ClassInfo;
import info.FileInfo;
import info.MethodInfo;
import info.ProjectInfo;

import java.util.HashMap;
import java.util.Map;

public class LongMethodAnalyzer extends SmellAnalyzer {
    private final static long NADIR=30;

    @Override
    public Map<String,Long> analyze(ProjectInfo projectInfo) {
        Map<String,Long> result=new HashMap<String, Long>();
        for(FileInfo fileInfo:projectInfo.getFileInfos())
        for(ClassInfo classInfo:fileInfo.getClassInfos())
        for(MethodInfo methodInfo:classInfo.getMethodInfos())
        if(methodInfo.getLines()>=NADIR)
            result.put(classInfo.getFullName()+" Method:"+methodInfo.getModifier(),methodInfo.getLines());
        return result;
    }
}
