package com.winter.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/10/15 11:37
 * @description：测试类
 * @modified By：
 * @version: v1.0
 */
public class Test<K,V> {
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public static <K> Map getKey(K key){
        Map<K,String> map=new HashMap();
        map.put(key,"aaa");
        return map;
    }
}
