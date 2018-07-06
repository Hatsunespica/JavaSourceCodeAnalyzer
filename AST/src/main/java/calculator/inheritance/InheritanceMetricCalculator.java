package calculator.inheritance;

import java.util.List;
import java.util.Map;

abstract public class InheritanceMetricCalculator {
    abstract public Map<String,Long> calculate(Map<String,Long> classId, Map<Long,ClassData> className,
                                               Map<Long,List<Long>> inheritanceTree, boolean[] isRoot);
}
