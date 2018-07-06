package calculator.inheritance;

import calculator.MetricsCalculator;
import calculator.inheritance.specific.SpecializationIndexCalculator;
import exception.SuperClassNotFoundException;
import info.ClassInfo;
import info.FileInfo;
import info.MethodInfo;
import info.ProjectInfo;

import java.util.*;

public class InheritanceTreeCalculator extends MetricsCalculator {
    /*NOO,NOA,SI,DIT,NOC*/

    private Map<String,Long> classId;
    private Map<Long,ClassData> className;
    private Map<Long,List<Long>> inheritanceTree;
    private boolean[] isRoot;
    private final static String[] calculators={"calculator.inheritance.specific.DepthOfInheritanceTreeCalculator"
            ,"calculator.inheritance.specific.NumberOfAddedCalculator",
    "calculator.inheritance.specific.NumberOfChildCalculator",
    "calculator.inheritance.specific.NumberOfOperationsOverriddenCalculator",
    "calculator.inheritance.specific.SpecializationIndexCalculator"};
    private final static String[] metricsName={"DepthOfInheritanceTree","NumberOfAdded","NumberOfChild","NumberOfOperationsOverridden","SpecializationIndex"};


    public InheritanceTreeCalculator(){
        classId=new HashMap<String, Long>();
        className=new HashMap<Long, ClassData>();
        inheritanceTree=new HashMap<Long, List<Long>>();
    }

    @Override
    public Map<String, Map<String, Long>> calculate(ProjectInfo projectInfo){
        Map<String,Map<String,Long>> result=new HashMap<String, Map<String, Long>>();
        init(projectInfo);
        for(int i=0;i<calculators.length;++i) {
            try {
                if(i==calculators.length-1)
                    result.put(metricsName[i],
                            ((SpecializationIndexCalculator)Class.forName(calculators[i]).newInstance()).
                                    calculate(result.get(metricsName[3]),result.get(metricsName[0]),classId,className,inheritanceTree,isRoot));
                else
                    result.put(metricsName[i],
                            ((InheritanceMetricCalculator)Class.forName(calculators[i]).newInstance()).
                                    calculate(classId,className,inheritanceTree,isRoot));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    private void init(ProjectInfo projectInfo){
        createClassData(projectInfo);
        buildInheritanceTree();

    }

    private void createClassData(ProjectInfo projectInfo){
        for(FileInfo fileInfo:projectInfo.getFileInfos())
            for(ClassInfo classInfo:fileInfo.getClassInfos())
                if(!classInfo.isInnerClass()){
                    ClassData classData=new ClassData(classInfo.getFullName());
                    List<MethodData> methods=new ArrayList<MethodData>();
                    for(MethodInfo methodInfo:classInfo.getMethodInfos())
                        methods.add(new MethodData(methodInfo.getName(),methodInfo.getModifier()));
                    classData.setMethods(methods);
                    classId.put(classInfo.getFullName(),(long)classId.size());
                    className.put(classId.size()-(long)1,classData);
                }

        for(FileInfo fileInfo:projectInfo.getFileInfos())
            for(ClassInfo classInfo:fileInfo.getClassInfos())
                if(!classInfo.isInnerClass()&&classInfo.getSuperClass()!=null){
                    String superClassFullName=null;
                    try {
                        superClassFullName = matchSuperClass(fileInfo, classInfo);
                    }catch (SuperClassNotFoundException e){
                        e.printStackTrace();
                        superClassFullName=null;
                    }
                    if(!classId.containsKey(superClassFullName))
                        superClassFullName=null;
                    className.get(classId.get(classInfo.getFullName())).setSuperClassFullName(superClassFullName);
                }
    }
    private void buildInheritanceTree(){
        isRoot =new boolean[classId.size()];
        for(int i = 0; i< isRoot.length; ++i) isRoot[i]=true;

        for(Map.Entry<Long,ClassData> entry:className.entrySet())
        if(entry.getValue().getSuperClassFullName()!=null){
            isRoot[(int)entry.getKey().longValue()]=false;
            Long father=classId.get(entry.getValue().getSuperClassFullName());
            if(father==null)
                System.out.println(entry.getValue().getSuperClassFullName());
            if(!inheritanceTree.containsKey(father))
                inheritanceTree.put(father,new ArrayList<Long>());
            inheritanceTree.get(father).add(entry.getKey());
        }

    }
    private String matchSuperClass(FileInfo fileInfo,ClassInfo classInfo)throws SuperClassNotFoundException {
        String superClassFullName=null;
        List<String> packages=fileInfo.getImportPackages();
        packages.add(fileInfo.getPackageName());
        String superClass=classInfo.getSuperClass();
        for(String packageName:packages)
        if(packageName.endsWith(superClass)) {
            superClassFullName = packageName;
            break;
        } else if(classId.containsKey(packageName+"."+superClass)){
            superClassFullName=packageName+"."+superClass;
            break;
        }else if(superClass.contains(".")&&classId.containsKey(superClass)){
            superClassFullName=superClass;
            break;
        }

        if(superClassFullName==null) {
            StringBuilder packageImported=new StringBuilder("");
            for(String packageName:packages)
                packageImported.append(packageName+"\n");
            throw new SuperClassNotFoundException("Super class not found,\nFile name: " + fileInfo.getName() +
                    "\nClass name: " + classInfo.getFullName() + "\nSuper Class: " + superClass
                    + "\nPackage Imported:\n"+packageImported);
        }
        return superClassFullName;
    }
}

