package com.jd.test.json;

import com.jd.test.base.BaseTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by jihaixiao on 2016/9/5.
 */
public class JacksonParseTest extends BaseTest{

    private static final int DEF_DIV_SCALE = 2;

    private int ZERO_COUNT,ONE_COUNT,TWO_COUNT,THREE_COUNT,FOUR_COUNT,FIVE_COUNT,SIX_COUNT,SEVEN_COUNT,EIGHT_COUNT,NINE_COUNT;

    private int h = 100;

    @Test
    public void testParseList(){
        Random random = new Random();
        for (int i = 0;i<h;i++){
            int rand = (random.nextInt(100))%10;
//            int rand = random.nextInt(10);
            switch (rand){
                case 0 :
                    ZERO_COUNT++;
                    break;
                case 1:
                    ONE_COUNT++;
                    break;
                case 2:
                    TWO_COUNT++;
                    break;
                case 3:
                    THREE_COUNT++;
                    break;
                case 4:
                    FOUR_COUNT++;
                    break;
                case 5:
                    FIVE_COUNT++;
                    break;
                case 6:
                    SIX_COUNT++;
                    break;
                case 7:
                    SEVEN_COUNT++;
                    break;
                case 8:
                    EIGHT_COUNT++;
                    break;
                case 9:
                    NINE_COUNT++;
                    break;
            }
        }
        System.out.println("0出现频率："+div(ZERO_COUNT,h)*100+"%");
        System.out.println("1出现频率："+div(ONE_COUNT,h)*100+"%");
        System.out.println("2出现频率："+div(TWO_COUNT,h)*100+"%");
        System.out.println("3出现频率："+div(THREE_COUNT,h)*100+"%");
        System.out.println("4出现频率："+div(FOUR_COUNT,h)*100+"%");
        System.out.println("5出现频率："+div(FIVE_COUNT,h)*100+"%");
        System.out.println("6出现频率："+div(SIX_COUNT,h)*100+"%");
        System.out.println("7出现频率："+div(SEVEN_COUNT,h)*100+"%");
        System.out.println("8出现频率："+div(EIGHT_COUNT,h)*100+"%");
        System.out.println("9出现频率："+div(NINE_COUNT,h)*100+"%");
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
