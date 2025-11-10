package common.engine.mapping;


import jakarta.servlet.Filter;

/**
 * @description FilterMapping
 * @author: zpy
 * @Date: 2025/11/10
 */
public class FilterMapping extends AbstractMapping{

    public final Filter filter;

    public FilterMapping(String urlPattern,Filter filter) {
        super(urlPattern);
        this.filter = filter;
    }
}
