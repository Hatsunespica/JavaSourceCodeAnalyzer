package info;

import org.eclipse.jdt.core.dom.FieldDeclaration;

public class AttributeInfo {
    private String name,type,modifier;
    private static final String[] MODIFIER={"public","package","private"};

    public AttributeInfo(String name, FieldDeclaration fieldDeclaration) {
        this.name=name;
        this.type=fieldDeclaration.getType().toString();

        modifier=null;
        for(Object object: fieldDeclaration.modifiers())
            if(MODIFIER[0].equals(object.toString()))
                modifier=MODIFIER[0];
            else if(MODIFIER[1].equals(object.toString()))
                modifier=MODIFIER[1];
            else if(MODIFIER[2].equals(object.toString()))
                modifier=MODIFIER[2];
        if(modifier==null)
            modifier=MODIFIER[1];
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getModifier() {
        return modifier;
    }
}
