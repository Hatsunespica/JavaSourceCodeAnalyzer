import exception.SameClassException;
import exception.SuperClassNotFoundException;

import java.util.Map;

public class ProjectAnalyzer {
    private MetricsGenerator metricsGenerator;
    private InfoExtractor infoExtractor;
    private String analyzer;
    private String path;

    /*public ProjectAnalyzer(String path,String analyzer){
        this(path);
        this.analyzer=analyzer;
    }*/

    public ProjectAnalyzer(String path,String analyzer){
        infoExtractor=new InfoExtractor(path);
        this.setAnalyzer(analyzer);
        this.path=path;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = "analyzer."+analyzer;
    }

    public Map<String,Map<String,Long>> analyze()throws SameClassException{
        return new MetricsGenerator(infoExtractor.extract(),analyzer).generate();
    }


    public Map<String,Map<String,Long>> analyze(String path)throws SameClassException{
        return new MetricsGenerator(new InfoExtractor(path).extract(),analyzer).generate();
    }

    public String getPath() {
        return path;
    }
}
