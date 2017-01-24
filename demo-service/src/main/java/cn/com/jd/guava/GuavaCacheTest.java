package cn.com.jd.guava;

import com.google.common.cache.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jihaixiao on 2016/9/13.
 */
public class GuavaCacheTest {

    private static LoadingCache<String,Object> cache = CacheBuilder.newBuilder()
                    .maximumSize(1000)
                    //开启统计命中
                    .recordStats()
                    //2秒后缓存超时
//                    .expireAfterWrite(2, TimeUnit.SECONDS)
                    .build(
                            new CacheLoader<String, Object>() {
                                    @Override
                                    public Object load(String key) throws Exception {
                                        System.out.println("--loading-");
                                        return "hhhh";
                                    }
                            }
                    );


    private static CountDownLatch count = new CountDownLatch(5);

    public GuavaCacheTest() {

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        InitThread t1 = new InitThread();
//        t1.start();
//        Thread.sleep(2000);

        System.out.println("开始执行！！");
        System.out.println(cache.get("ms"));
        for (int i=0;i<5;i++){
            CacheThread cacheThread = new CacheThread();
            cacheThread.start();
        }
        count.await();

        //统计命中率
        CacheStats stats = cache.stats();
        System.out.println("命中数："+stats.hitCount());
        System.out.println("命中率："+stats.hitRate());
    }

//    static class InitThread extends Thread{
//
//
//        @Override
//        public void run() {
//            cache = CacheBuilder.newBuilder()
//                    .maximumSize(1000)
//                    //开启统计命中
//                    .recordStats()
//                    //2秒后缓存超时
////                    .expireAfterWrite(2, TimeUnit.SECONDS)
//                    .build(
//                            new CacheLoader<String, Object>() {
//                                @Override
//                                public Object load(String key) throws Exception {
//                                    System.out.println("--loading-");
//                                    return "hhhh";
//                                }
//                            }
//                    );
//        }
//    }

    static class CacheThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println(cache.get("ms"));
                count.countDown();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
