package common.engine.support;

import java.util.*;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/13 08:32
 */
public class InitParameters extends  LazyMap<String>{

    public boolean setInitParameter(String name, String value){
      if(name == null|| name.isEmpty()){
          throw new IllegalArgumentException("Init parameter name cannot be null or empty");
      }

      if(value == null|| value.isEmpty()){
          throw new IllegalArgumentException("Init parameter value cannot be null or empty");
      }

      if(super.containsKey(name)){
          return false;
      }

      super.put(name,value);
      return true;
    }

    public String getInitParameter(String name){
        return super.get(name);
    }

    public Set<String> setInitParameters(Map<String,String> initParams){
        if (initParams == null) {
            throw  new IllegalArgumentException("Init parameters map cannot be null");
        }

        if (initParams.isEmpty()){
            return Set.of();
        }

        Set<String> conflicts = new HashSet<>();

        for (String name : initParams.keySet()){
            String value = initParams.get(name);
            if (value == null){
                throw new IllegalArgumentException("Init parameter value cannot be null for name: " + name);
            }
            if (super.containsKey(name)) {
                conflicts.add(name);
            }else{
                super.put(name,value);
            }
        }
        return conflicts;
    }

    public Map<String,String>getInitParameter(){
        return super.map();
    }

    public Enumeration<String> getInitParameterNames() {
        return Collections.enumeration(super.map().keySet());
    }
}
