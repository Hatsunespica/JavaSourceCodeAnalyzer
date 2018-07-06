package calculator.inheritance.specific;

import calculator.inheritance.ClassData;
import calculator.inheritance.InheritanceMetricCalculator;
import calculator.inheritance.InheritanceTreeCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfOperationsOverriddenCalculator  extends InheritanceMetricCalculator {
    public Map<String, Long> calculate(Map<String, Long> classId, Map<Long, ClassData> className, Map<Long, List<Long>> inheritanceTree, boolean[] isRoot) {
        Map<String,Long> result=new HashMap<String, Long>();
        for(long i=0;i<isRoot.length;++i)
            if(isRoot[(int)i])
                result.put(className.get(i).getFullName(),(long)className.get(i).getMethods().size());
        for(Map.Entry<Long,List<Long>> entry:inheritanceTree.entrySet()){
            for(Long son:entry.getValue())
                result.put(className.get(son).getFullName(),
                        ClassData.intersect(className.get(son).getMethods(),className.get(entry.getKey()).getMethods()));
        }
        return result;
    }
}
