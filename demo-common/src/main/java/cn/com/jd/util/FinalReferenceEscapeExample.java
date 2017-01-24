package cn.com.jd.util;

/**
 * Created by jihaixiao on 2017/1/23.
 */
public class FinalReferenceEscapeExample {

    final int i;

    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample(){
        i=1;                            //1 写fina-l域
        obj = this;                     //2 this引用在此“逃逸”
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
    }

    public static void reader(){
        if (obj != null) {              //3
            int temp = obj.i;           //4
        }
    }

}
