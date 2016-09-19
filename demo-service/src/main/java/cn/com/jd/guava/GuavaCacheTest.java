package cn.com.jd.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by jihaixiao on 2016/9/13.
 */
public class GuavaCacheTest {

    public static void main(String[] args) {
        LoadingCache<String,Object> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(
                        new CacheLoader<String, Object>() {
                            @Override
                            public Object load(String s) throws Exception {
                                return "hahaha";
                            }
                        }
                );
        Object s = graphs.getUnchecked("ms");
        System.out.println(s);
    }

}
