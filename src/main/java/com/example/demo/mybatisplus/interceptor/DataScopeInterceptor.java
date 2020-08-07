package com.example.demo.mybatisplus.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;

import com.sun.xml.internal.ws.api.model.CheckedException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;
import sun.security.util.SecurityConstants;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {
    private final DataSource dataSource;

    @Override
    @SneakyThrows
    public Object intercept(Invocation invocation) {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);

        // 先判断是不是SELECT操作
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }

        //TODO:查询数据权限配置

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();


//        String scopeName = CommonConstants.SCOPENAME;
//        List<String> organIds = new ArrayList<>();
//        // 优先获取赋值数据
//        BaseUser user = SecurityUtils.getUser();
//        if (user == null) {
//            throw new CheckedException("auto datascope, set up security details true");
//        }
//
//        List<String> roleIdList = user.getAuthorities()
//                .stream().map(GrantedAuthority::getAuthority)
//                .filter(authority -> authority.startsWith(SecurityConstants.ROLE))
//                .map(authority -> authority.split("_")[1])
//                .collect(Collectors.toList());
//        Entity query = Db.use(dataSource)
//                .query("SELECT * FROM "+CommonConstants.UPMS_DATABASE+".sys_role where id IN ('" + CollUtil.join(roleIdList, "','") + "')")
//                .stream().min(Comparator.comparingInt(o -> o.getInt("ds_type"))).get();
//
//        Integer dsType = query.getInt("ds_type");
//        // 查询全部
//        if (DataScopeTypeEnum.ALL.getType() == dsType) {
//            return invocation.proceed();
//        }
//        // 自定义
//        if (DataScopeTypeEnum.CUSTOM.getType() == dsType) {
//            String dsScope = query.getStr("ds_scope");
//            organIds.addAll(Arrays.stream(dsScope.split(","))
//                    .map(String::toString).collect(Collectors.toList()));
//        }
//        // 查询本级及其下级
//        if (DataScopeTypeEnum.OWN_CHILD_LEVEL.getType() == dsType) {
//            List<String> organIdList = Db.use(dataSource)
//                    .findBy(CommonConstants.UPMS_DATABASE+".sys_organ_relation", "ancestor", user.getOrganId())
//                    .stream().map(entity -> entity.getStr("descendant"))
//                    .collect(Collectors.toList());
//            organIds.addAll(organIdList);
//        }
//        // 只查询本级
//        if (DataScopeTypeEnum.OWN_LEVEL.getType() == dsType) {
//            organIds.add(user.getOrganId());
//        }
//        String join = CollectionUtil.join(organIds, "','");
        originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + "id" + " in ('" + "2" + "')";
        metaObject.setValue("delegate.boundSql.sql", originalSql);
        return invocation.proceed();
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }
}