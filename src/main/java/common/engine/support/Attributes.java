package common.engine.support;

import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/18 08:57
 */
public class Attributes extends LazyMap<Object> {
    public Attributes(boolean concurrent){
        super(concurrent);
    }

    public Attributes(){
        this(false);
    }

    public Object getAttribute(String name){
        return super.get(name);
    }

    public Enumeration<String>getAttributeNames(){
        return super.keyEnumeration();
    }

    public Object setAttribute(String name,Object value) {
        Objects.requireNonNull(name, "Attribute name cannot be null");
        return super.put(name, value);
    }

    public Object removeAttribute(String name){
        Objects.requireNonNull(name, "Attribute name cannot be null");
        return super.remove(name);
    }

    public Map<String,Object> getAttributes(){
        return super.map();
    }

}
