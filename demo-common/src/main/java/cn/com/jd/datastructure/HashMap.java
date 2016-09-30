package cn.com.jd.datastructure;

import java.io.Serializable;

/**
 * Created by jihaixiao on 2016/9/29.
 */
public class HashMap<K,V> implements Serializable{

    private static final long serialVersionUID = -181127408343278283L;

    private static final int DEFAULT_CAPACITY = 16;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;


    transient Entry[] table;

    volatile int modCount;

    transient int size;

    final float loadFactor;

    public HashMap(float loadFactor) {
        this.loadFactor = loadFactor;
        table = new Entry[DEFAULT_CAPACITY];
    }

    public HashMap() {
        table = new Entry[DEFAULT_CAPACITY];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    static int hash(int h) {
        return h ^ (h >>> 16);
    }

    static int indexFor(int hash,int length){
        return hash & (length - 1);
    }

    public V put(K key,V value){
        if (key == null)
            return putNullKey(value);
        int hash = hash(key.hashCode());
        int i = indexFor(hash,table.length);
        for (Entry<K,V> e = table[i]; e !=null;e = e.next){
            Object k;
            if (e.hash == hash && (key == e.key || key.equals(e.key))){
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        modCount++;
        addEtry(hash,key,value,i);
        return null;
    }

    public V get(K key){
        if (key == null)
            return getNullKey();
        int hash = hash(key.hashCode());
        for (Entry<K,V> e = table[indexFor(hash,table.length)];
                e != null; e = e.next){
            Object k;
            if (e.hash == hash && (key == e.key || key.equals(e.key))){
                return e.value;
            }
        }
        return null;
    }


    private V getNullKey(){
        for (Entry<K,V> e = table[0];e != null; e = e.next){
            if (e.key == null){
                return e.value;
            }
        }
        return null;
    }

    /**
     *
     * put方法，处理 key是null的情况，key是null的时候统一都放到entry数组索引为0的地方
     * 如果索引为0的地方，包括它的链表，有key是null的情况，就覆盖old value
     * 如果没有，那么就给数组索引为0处增加上这个entry
     * @param value
     * @return
     */
    private V putNullKey(V value){
        for (Entry<K,V> e = table[0]; e!=null; e = e.next){
            if (e.key == null){
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        modCount++;
        addEtry(0,null,value,0);
        return null;
    }

    /**
     *
     * 将key-value 的entry放入数组，如果一旦数组索引有值  PS：说明Hash冲突了
     * 转化为链表，old entry 变成 new entry的next节点
     *
     * 也就是说，越早put进来的entry，一旦形成链表，会越来越往链表的尾部靠近，查询起来会越来越慢~
     *
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex
     */
    void addEtry(int hash,K key,V value,int bucketIndex){
        Entry<K,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry(key,value,e,hash);
    }



    static class Entry<K,V> {
        K key;
        V value;
        Entry<K,V> next;
        int hash;

        public Entry(K key, V value, Entry<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }
    }
}
