package com.z2xinyu.annotation.jpaimpl.dao;

import com.xinyu.annotation.jpaimpl.domain.Column;
import com.xinyu.annotation.jpaimpl.domain.ID;
import com.xinyu.annotation.jpaimpl.domain.Table;
import cn.itcast.jdbc.TxQueryRunner;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过注解来映射表列名和bean类名和字段名
 *
 * @author zhang xinyu
 * @version v1.0
 * @date Created in 2021-05-16 20:54
 */
public class BaseDao<T> {
    private TxQueryRunner txQueryRunner = new TxQueryRunner();
    /**
     * BaseDao实现类传递的实际类型参数的Class对象
     */
    private Class<T> beanClass;

    public BaseDao() {
        // 子类继承父类时传递实际类型参数，得到该参数的Class对象   class cn.dhjy.annotation.impl.domain.User
        beanClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public void add(T bean) {
        Field[] beanFields = beanClass.getDeclaredFields();
        /*
            如果表名和列名和javabean中的类名和字段名相同，可以通过反射获取
            但是如果不相同，那么就通过注解完成表、列名和类、字段名的映射
         */
        StringBuilder sql = new StringBuilder();
        // 通过获取字段上的注解中的value确定字段名称
        sql.append("insert into ").append(beanClass.getAnnotation(Table.class).value()).append(" (");
        sql.append(beanFields[0].getAnnotation(ID.class).value()).append(",");
        sql.append(beanFields[1].getAnnotation(Column.class).value()).append(",");
        sql.append(beanFields[2].getAnnotation(Column.class).value());
        sql.append(") values (");
        for (int i = 0; i < beanFields.length; i++) {
            sql.append("?");
            if (i < beanFields.length - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        // 为参数赋值
        // 得到bean对象的字段值
        List<Object> fieldValue = new ArrayList<>();
        for (Field field : beanFields) {
            field.setAccessible(true);
            try {
                Object value = field.get(bean);
                fieldValue.add(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Object[] params = fieldValue.toArray(new Object[fieldValue.size()]);
        try {
            txQueryRunner.update(sql.toString(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
