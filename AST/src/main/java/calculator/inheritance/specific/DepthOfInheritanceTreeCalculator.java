package calculator.inheritance.specific;

import calculator.inheritance.ClassData;
import calculator.inheritance.InheritanceMetricCalculator;
import calculator.inheritance.InheritanceTreeCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthOfInheritanceTreeCalculator extends InheritanceMetricCalculator {
    private Map<Long,List<Long>> inheritanceTree;
    private Map<String,Long> result;
    private Map<Long, ClassData> className;


    public Map<String, Long> calculate(Map<String, Long> classId, Map<Long, ClassData> className, Map<Long, List<Long>> inheritanceTree, boolean[] isRoot) {
        this.inheritanceTree=inheritanceTree;
        this.className=className;
        result=new HashMap<String, Long>();
        for(long i=0;i<isRoot.length;++i)
        if(isRoot[(int)i])
            dfs(i,0);
        return result;
    }

    private void dfs(long now,long depth){
        result.put(className.get(now).getFullName(),depth);
        if(inheritanceTree.containsKey(now)){
            List<Long> list=inheritanceTree.get(now);
            for(long val:list)
                dfs(val,now+1);
        }
    }
}
