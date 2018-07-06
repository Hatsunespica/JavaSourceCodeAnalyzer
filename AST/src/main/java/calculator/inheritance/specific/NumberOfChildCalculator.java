package calculator.inheritance.specific;

import calculator.inheritance.ClassData;
import calculator.inheritance.InheritanceMetricCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfChildCalculator  extends InheritanceMetricCalculator {
    public Map<String, Long> calculate(Map<String, Long> classId, Map<Long, ClassData> className, Map<Long, List<Long>> inheritanceTree, boolean[] isRoot) {
        Map<String,Long> result=new HashMap<String, Long>();
        for(Map.Entry<String,Long> entry:classId.entrySet())
        if(inheritanceTree.containsKey(entry.getValue()))
            result.put(entry.getKey(),(long)inheritanceTree.get(entry.getValue()).size());
        else
            result.put(entry.getKey(),(long)0);
        return result;
    }
}
