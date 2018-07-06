package visitor;

import info.AttributeInfo;
import info.ClassInfo;
import info.FileInfo;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.Set;

public class FileVisitor extends ASTVisitor {
    private FileInfo fileInfo;
    private Set<String> stringSet;

    public FileVisitor(String name,long commentStatement){
        fileInfo=new FileInfo(name,commentStatement);
        stringSet=new HashSet<String>();
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        ClassInfo classInfo=new ClassInfo(node,fileInfo.getPackageName(),stringSet.contains(node.getName().getFullyQualifiedName()));
        for(FieldDeclaration fieldDeclaration:node.getFields()) {
            for (Object object : fieldDeclaration.fragments()) {
                classInfo.addAttribute(new AttributeInfo(((VariableDeclarationFragment)object).getName().toString(), fieldDeclaration));
            }
        }
        for(MethodDeclaration methodDeclaration:node.getMethods()){
            MethodNodeVisitor methodNodeVisitor=new MethodNodeVisitor(methodDeclaration);
            methodDeclaration.accept(methodNodeVisitor);
            classInfo.addMethod(methodNodeVisitor.getMethodInfo());
        }
        for(TypeDeclaration typeDeclaration:node.getTypes()){
            stringSet.add(typeDeclaration.getName().getFullyQualifiedName());
        }
        fileInfo.addClass(classInfo);
        return true;
    }

    @Override
    public boolean visit(ImportDeclaration importDeclaration){
        //System.out.println(importDeclaration.getName());
        fileInfo.addImportPackage(importDeclaration.getName().getFullyQualifiedName());
        fileInfo.setCodeLineNum(fileInfo.getCodeLineNum()+1);
        return true;
    }

    @Override
    public boolean visit(PackageDeclaration packageDeclaration){
        //System.out.println(packageDeclaration.getName().getFullyQualifiedName());
        fileInfo.setPackageName(packageDeclaration.getName().getFullyQualifiedName());
        fileInfo.setCodeLineNum(fileInfo.getCodeLineNum()+1);
        return true;
    }
}
