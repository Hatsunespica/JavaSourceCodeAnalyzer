package calculator.inheritance.specific;

import calculator.inheritance.ClassData;
import calculator.inheritance.InheritanceMetricCalculator;
import calculator.inheritance.InheritanceTreeCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecializationIndexCalculator  {
    private long[] methodNum;
    private Map<String,Long> classId;
    private Map<Long,ClassData> className;
    private Map<Long,List<Long>> inheritanceTree;
    private boolean[] isRoot;
    private Map<String,Long> noo;

    public Map<String,Long> calculate(Map<String,Long> noo,Map<String,Long> dit,Map<String,Long> classId,Map<Long,ClassData> className,Map<Long,List<Long>> inheritanceTree, boolean[] isRoot){
        this.classId=classId;this.className=className;this.inheritanceTree=inheritanceTree;this.isRoot=isRoot;this.noo=noo;

        Map<String,Long> result=new HashMap<String, Long>();
        calcMethodNum();
        for(Map.Entry<String,Long> entry:noo.entrySet())
        if(className.get(entry.getValue()).getSuperClassFullName()==null)
            result.put(entry.getKey(),(long)0);
        else
            result.put(entry.getKey(),
                    methodNum[(int)classId.get(entry.getKey()).longValue()]==0?0:entry.getValue()*dit.get(entry.getKey())/methodNum[(int)classId.get(entry.getKey()).longValue()]);
        return result;
    }

    private void calcMethodNum(){
        methodNum=new long[classId.size()];
        for(int i=0;i<isRoot.length;++i)
        if(isRoot[i]) {
            methodNum[i] = className.get((long) i).getMethods().size();
            if(inheritanceTree.containsKey((long)i))
            for(long val:inheritanceTree.get((long)i))
                dfs(val);
        }

    }

    private void dfs(long now){
        methodNum[(int)now]=className.get(now).getMethods().size();
        long added=className.get(classId.get(className.get(now).getSuperClassFullName())).getMethods().size()-noo.get(className.get(now).getFullName());
        if(added<0){
            System.out.println(className.get(now).getFullName());
            System.exit(1);
        }
        if(inheritanceTree.containsKey(now))
        for(long val:inheritanceTree.get(now))
            dfs(val);
    }
}
