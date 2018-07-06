package calculator.inheritance;

public class MethodData {

    private String name,modifier;

    public MethodData() {
    }

    public MethodData(String name, String modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getFullMethod(){
        return modifier+name;
    }
}
