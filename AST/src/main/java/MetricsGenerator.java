import analyzer.SmellAnalyzer;
import calculator.inheritance.InheritanceTreeCalculator;
import calculator.MetricsCalculator;
import com.google.gson.Gson;
import exception.SuperClassNotFoundException;
import info.ClassInfo;
import info.FileInfo;
import info.MethodInfo;
import info.ProjectInfo;

import java.util.HashMap;
import java.util.Map;

public class MetricsGenerator {
    /*NOO,NOA,SI,DIT,NOC*/
    private SmellAnalyzer smellAnalyzer;
    private MetricsCalculator metricsCalculator;
    private Map<String,Map<String,Long>> result;
    private ProjectInfo projectInfo;

    public MetricsGenerator(ProjectInfo projectInfo,String smellAnalyzer){
        this.projectInfo=projectInfo;
        metricsCalculator =new InheritanceTreeCalculator();
        result=new HashMap<String, Map<String,Long>>();
        try {
            this.smellAnalyzer=(SmellAnalyzer)Class.forName(smellAnalyzer).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<String,Map<String,Long>> generate() {
        result.put("CodeSmell",smellAnalyzer.analyze(projectInfo));
        result.putAll(metricsCalculator.calculate(projectInfo));
        //compress();
        return result;
    }

    public String toJson(){
        return new Gson().toJson(result);
    }

    public Map<String, Map<String, Long>> getResult() {
        return result;
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    private void setLongestMethod(MethodInfo method,String prefix){
        if(projectInfo.getLongestMethod()<method.getLines()){
            projectInfo.setLongestMethod(method.getLines());
            projectInfo.setLongestMethodName(prefix+":"+method.getName());
        }
    }

    private void compress(){
        Map<String,Long> projectMap=new HashMap<String, Long>();
        projectMap.put("statementNum",projectInfo.getStatementNum());
        projectMap.put("codeLineNum",projectInfo.getCodeLineNum());
        projectMap.put("cyclomaticComplexity",projectInfo.getCyclomaticComplexity());
        projectMap.put("commentNum",projectInfo.getCommentNum());
        projectMap.put("MethodNum",projectInfo.getMethodNum());
        projectMap.put("AttributeNum",projectInfo.getAttributesNum());
        result.put("projectInfo",projectMap);
        for(FileInfo fileInfo:projectInfo.getFileInfos()){
            Map<String,Long> fileMap=new HashMap<String, Long>();
            fileMap.put("codeLineNum",fileInfo.getCodeLineNum());
            fileMap.put("commentNum",fileInfo.getCommentLineNum());
            fileMap.put("statementNum",fileInfo.getStatementNum());
            fileMap.put("classNum",fileInfo.getClassNum());
            fileMap.put("cyclomaticComplexity",fileInfo.getCyclomaticComplexity());
            result.put(fileInfo.getPackageName()==null?fileInfo.getName():fileInfo.getPackageName()+"."+fileInfo.getName(),fileMap);
            for(ClassInfo classInfo:fileInfo.getClassInfos()){
                Map<String,Long> classMap=new HashMap<String, Long>();
                classMap.put("codeLineNum",classInfo.getCodeLineNum());
                classMap.put("cyclomaticComplexity",classInfo.getCyclomaticComplexityNum());
                classMap.put("attributesNum",classInfo.getAttributesNum());
                classMap.put("methodNum",classInfo.getMethodNum());
                classMap.put("statementNum",classInfo.getStatementNum());
                result.put(classInfo.getFullName(),classMap);

                for(MethodInfo methodInfo:classInfo.getMethodInfos())
                    setLongestMethod(methodInfo,classInfo.getFullName());
            }
        }

        projectMap.put(projectInfo.getLongestMethodName(),projectInfo.getLongestMethod());
    }
}
