package calculator.inheritance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassData{
    private String fullName,superClassFullName;
    private List<MethodData> methods;

    ClassData(String fullName){
        this.fullName=fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSuperClassFullName() {
        return superClassFullName;
    }

    public void setSuperClassFullName(String superClassFullName) {
        this.superClassFullName = superClassFullName;
    }

    public List<MethodData> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodData> methods) {
        this.methods = methods;
    }

    public static long intersect(List<MethodData> methods1, List<MethodData> methods2){
        long answer=0;
        Map<String,Long> map=new HashMap<String, Long>();
        for(MethodData methodData:methods1)
        if(!methodData.getModifier().equals("private")){
            if(!map.containsKey(methodData.getFullMethod()))
                map.put(methodData.getFullMethod(),(long)0);
            map.put(methodData.getFullMethod(),map.get(methodData.getFullMethod())+1);
        }

        for(MethodData methodData:methods2)
        if(!methodData.getModifier().equals("private")&&map.containsKey(methodData.getFullMethod())){
            map.put(methodData.getFullMethod(),map.get(methodData.getFullMethod())-1);
            if(map.get(methodData.getFullMethod())==0)
                map.remove(methodData.getFullMethod());
        }
        return answer;
    }
}
