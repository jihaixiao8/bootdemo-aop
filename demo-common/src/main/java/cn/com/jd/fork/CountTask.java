package cn.com.jd.fork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jihaixiao on 2016/11/30.
 */
public class CountTask {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i =0;i<600;i++){
            list.add(i);
        }

        int start = 0;
        for (int j=0;j<200;j++){
            int end = start+(660/200);
            if (end ==768){
                System.out.println("ri]");
            }
            List<Integer> list1 = list.subList(start,end);
            start=end;
        }
    }

}
