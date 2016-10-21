package cn.com.jd.util;

/**
 * Created by jihaixiao on 2016/10/19.
 */
public class StringUtils {

    public static boolean hasLength(CharSequence cs){
        return (cs!=null && cs.length() >0);
    }

    public static boolean hasLength(String str){
        return hasLength((CharSequence)str);
    }

    public static String replace(String inString,String oldPattern,String newPattern){
        if (!hasLength(inString) || !hasLength(oldPattern) || !hasLength(newPattern)){
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        int index = inString.indexOf(oldPattern);
        int patLen = oldPattern.length();
        while (index >= 0){
            sb.append(inString.substring(pos,index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern,pos);
        }
        sb.append(inString.substring(pos));
        return sb.toString();
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            replace("D:\\abc\\txa.txt","\\","/");
//            "D:\\abc\\txa.txt".replace("\\","/");
        }
        System.out.println((System.currentTimeMillis() - start));
//        System.out.println("D:\\abc\\txa.txt".replace("\\","/"));
    }

}
