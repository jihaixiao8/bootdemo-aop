package cn.com.jd.test.concurrent;

/**
 * Created by jihaixiao on 2016/12/30.
 */
public class FinalExample {
    int i;              //普通变量
    final int j;        //final变量

    static FinalExample obj;

    public FinalExample() {
        i=1;                    //构造函数内写普通域
        j=2;                    //构造函数内写final域
    }

    public static void writer(){   //写线程A执行，初始化FinalExample，并把它的引用赋值给obj
        obj = new FinalExample();
    }

    public static void reader(){   //读线程B执行
        FinalExample object = obj;  //读对象引用
        int a = object.i;           //读普通域
        int b = object.j;           //读final域
    }

}
