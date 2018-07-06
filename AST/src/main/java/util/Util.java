package util;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import info.FileInfo;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import sun.text.resources.iw.FormatData_iw_IL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static int count(String src,String str){
        int ans=0,tmp=0;
        while((tmp=src.indexOf(str,tmp))!=-1){
            ++ans;
            tmp+=str.length();
        }
        return ans;
    }

    public static ASTNode getASTNode(String javaFilePath){
        byte[] input = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
            input = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(input);
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ASTParser astParser = ASTParser.newParser(AST.JLS3);
        astParser.setSource(new String(input).toCharArray());
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);

        return  astParser.createAST(null);
    }

    public static List<String> getFiles(String path){
        List<String> strings = new ArrayList<String>();
        File file=new File(path);
        if(!file.isDirectory()){
            if(file.getName().endsWith(".java"))
                strings.add(path);
        }else{
            for(File tmp:file.listFiles())
                strings.addAll(getFiles(tmp.getPath()));
        }
        return strings;
    }

    public static long getCommentNum(File file){
        long ans=0;
        boolean inBlockComment=false;
        String line;
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            while((line=bufferedReader.readLine())!=null)
            if(inBlockComment){
                ++ans;
                inBlockComment=!line.contains("*/");
            }
            else if(line.contains("//"))
                ++ans;
            else if(line.contains("/*")){
                ++ans;
                inBlockComment=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ans;
    }
}
