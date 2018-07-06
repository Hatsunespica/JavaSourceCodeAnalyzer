package analyzer;

import info.ClassInfo;
import info.FileInfo;
import info.MethodInfo;
import info.ProjectInfo;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;
import java.util.Map;

public class LongParameterListAnalyzer extends SmellAnalyzer {
    private static final long NADIR=7;

    @Override
    public Map<String,Long> analyze(ProjectInfo projectInfo) {
        Map<String,Long> result=new HashMap<String, Long>();
        for(FileInfo fileInfo:projectInfo.getFileInfos())
        for(ClassInfo classInfo:fileInfo.getClassInfos())
        for(MethodInfo methodInfo:classInfo.getMethodInfos())
        if(methodInfo.getParametersNum()>NADIR)
            result.put(classInfo.getFullName()+" Method:"+methodInfo.getName(),methodInfo.getParametersNum());
        return result;
    }
}
