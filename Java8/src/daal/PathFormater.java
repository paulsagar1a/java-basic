package daal;

import java.util.Arrays;
import java.util.Stack;

public class PathFormater {
    public String simplifyPath(String path) {
       String[] arr = path.split("/+");
       Stack<String> stack = new Stack<>();
       for(String s : arr) {
    	   if(stack.size() > 0 && "..".equals(s)) {
    		   stack.pop();
    	   } else if(!Arrays.asList("", ".").contains(s)) {
    		   stack.push(s);
    	   }
       }
       
       StringBuilder sb =  new StringBuilder();
       for(String s: stack) {
    	   sb.append("/").append(s);
       }
       return sb.length() == 0 ? "/" : sb.toString();
    }
    public static void main(String[] args) {
    	PathFormater test = new PathFormater();
    	System.out.println(test.simplifyPath("/a/./b/../../c/"));
    }
}