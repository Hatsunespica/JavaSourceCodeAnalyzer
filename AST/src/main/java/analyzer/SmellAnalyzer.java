package analyzer;

import info.ProjectInfo;

import java.util.Map;

public abstract class SmellAnalyzer {
    public abstract Map<String,Long> analyze(ProjectInfo projectInfo);
}
