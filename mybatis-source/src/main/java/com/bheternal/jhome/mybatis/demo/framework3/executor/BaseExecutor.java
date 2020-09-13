package com.bheternal.jhome.mybatis.demo.framework3.executor;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * BaseExecutor
 * 具有一级缓存，内存缓存
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.executor.BaseExecutor
 * @see org.apache.ibatis.cache.impl.PerpetualCache 缓存
 * @see org.apache.ibatis.cache.CacheKey
 */
public abstract class BaseExecutor implements Executor {

    protected final Configuration configuration;
    protected final BaseExecutor wrapper;
    private final Map<Object, Object> cache = new HashMap<>();


    public BaseExecutor(Configuration configuration) {
        this.configuration = configuration;
        wrapper = this;
    }

    /**
     * 执行查询实现
     *
     * @param ms
     * @param parameter
     * @param boundSql
     * @param <T>
     * @return
     */
    protected abstract <T> List<T> doQuery(MappedStatement ms, Object parameter, BoundSql boundSql);

    /**
     * 基础查询逻辑
     *
     * @param ms
     * @param parameter
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> query(MappedStatement ms, Object parameter) {
        BoundSql boundSql = ms.getSqlSource().getBoundSql(parameter);

        Object cacheKey = getCacheKey(ms, parameter, boundSql);
        Object o = cache.get(cacheKey);
        if (Objects.nonNull(o)) {
            return (List<T>) o;
        }

        return queryFromDatabase(ms, parameter, boundSql);
    }

    /**
     * 查询数据库
     *
     * @param <T>
     * @param ms
     * @param parameter
     * @param boundSql
     * @return
     */
    private <T> List<T> queryFromDatabase(MappedStatement ms, Object parameter, BoundSql boundSql) {
        return doQuery(ms, parameter, boundSql);
    }


    private Object getCacheKey(MappedStatement ms, Object parameter, BoundSql boundSql) {
        return boundSql.getSql().hashCode() + "-" + parameter.hashCode();
    }
}
