package calculator;

import exception.SuperClassNotFoundException;
import info.ProjectInfo;

import java.util.Map;

public abstract class MetricsCalculator {
    abstract public Map<String,Map<String,Long>> calculate(ProjectInfo projectInfo);
}
