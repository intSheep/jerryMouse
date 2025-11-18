package common.engine.support;

import java.util.*;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/10
 */
public class LazyMap<V> {

    private Map<String,V> map = null;

    private final boolean concurrent;

    public LazyMap(boolean concurrent){
        this.concurrent = concurrent;
    }

    protected V get(String key){
        if (map == null){
            return null;
        }
        return map.get(key);
    }

    protected Set<String>keySet(){
        if (this.map == null){
            return Set.of();
        }
        return this.map.keySet();
    }


    protected Enumeration<String> keyEnumeration(){
        if (this.map == null){
            return Collections.emptyEnumeration();
        }
        return Collections.enumeration(this.map.keySet());
    }

    protected V put(String key,V value){
        if (this.map == null){
            this.map = new HashMap<>();
        }
        return this.map.put(key,value);
    }

    protected boolean containsKey(String key){
        if (this.map == null){
            return false;
        }
        return this.map.containsKey(key);
    }

    protected V remove(String key){
        if (this.map == null){
            return null;
        }
        return this.map.remove(key);
    }

    protected  Map<String,V> map(){
        if (this.map == null){
            return Map.of();
        }
        return Collections.unmodifiableMap(this.map);
    }

    public boolean isConcurrent() {
        return concurrent;
    }
}
