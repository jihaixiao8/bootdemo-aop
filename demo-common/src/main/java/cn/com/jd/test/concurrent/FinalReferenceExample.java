package cn.com.jd.test.concurrent;

/**
 * Created by jihaixiao on 2016/12/30.
 */
public class FinalReferenceExample {

    final int[] intArray;               //final是引用类型

    static FinalReferenceExample obj;

    public FinalReferenceExample() {    //构造函数
        intArray = new int[1];          //操作1
        intArray[0]=1;                  //操作2
    }

    public static void writeOne(){              //写线程A执行
        obj = new FinalReferenceExample();      //3
    }

    public static void writeTwo(){              //写线程B执行
        obj.intArray[0] = 2;                    //4
    }

    public static void reader(){                //读线程C执行
        if (obj != null){                       //5
            int temp1 = obj.intArray[0];        //6
        }
    }

}
