import exception.SameClassException;

public class Main {

    public static void main(String[] args)throws SameClassException{
        ProjectAnalyzer projectAnalyzer1=new ProjectAnalyzer("G:\\Study\\SoftwareMetric\\exp\\apache-ant(1.9.3)\\src\\main","LongMethodAnalyzer");
        System.out.println(projectAnalyzer1.analyze());
    }
}
