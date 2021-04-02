package com.aca.pgsql;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import com.aca.pgsql.usertype.BidiEnumMap;
import com.aca.pgsql.utils.PgArrayUtils;

/**
 * 自定义队列类型。
 */
public class ArrayType implements UserType, ParameterizedType {

    /** 常量：整型队列 */
    public static final int INTEGER_ARRAY = 90001;

    /** 常量：长整型队列 */
    public static final int LONG_ARRAY = 90002;

    /** 常量：字符串型队列 */
    public static final int STRING_ARRAY = 90003;

    /** 常量：Enum整型队列 */
    public static final int ENUM_INTEGER_ARRAY = 90004;

    /** 常量：浮点型队列 */
    public static final int FLOAT_ARRAY = 90005;

    /** 常量：双精度型队列 */
    public static final int DOUBLE_ARRAY = 90006;

    /** 类名到SQL码的映射MAP */
    private static final Map<Class<?>, Integer> CLASS_TO_SQL_CODE = new HashMap<>();

    static {
        CLASS_TO_SQL_CODE.put(Integer.class, INTEGER_ARRAY);
        CLASS_TO_SQL_CODE.put(Long.class, LONG_ARRAY);
        CLASS_TO_SQL_CODE.put(String.class, STRING_ARRAY);
        CLASS_TO_SQL_CODE.put(Float.class, FLOAT_ARRAY);
        CLASS_TO_SQL_CODE.put(Double.class, DOUBLE_ARRAY);
    }

    /** class类型 */
    private Class<?> typeClass;

    /** 双向 Enum Map */
    private BidiEnumMap bidiMap;

    @Override
    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    @Override
    public Serializable disassemble(Object value) {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) {
        return x == null ? y == null : x.equals(y);
    }

    @Override
    public int hashCode(Object value) {
        return value == null ? 0 : value.hashCode();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        String type = parameters.getProperty("type");
        if (type == null) {
            throw new RuntimeException("The user type needs to be configured with the type. None provided");
        }
        try {
            typeClass = Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Type '" + type + "' is not a valid type.");
        }
    }

    @Override
    public Class<?> returnedClass() {
        return java.lang.reflect.Array.newInstance(typeClass, 0).getClass();
    }

    @Override
    public int[] sqlTypes() {

        Integer type = CLASS_TO_SQL_CODE.get(typeClass);
        if (type != null) {
            return new int[] { type };
        }

        if (typeClass.isEnum()) {
            return new int[] { ENUM_INTEGER_ARRAY };
        }

        throw new RuntimeException("The type " + typeClass + " is not a valid type");
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
        throws SQLException {
        Object result = null;
        Class<?> typeArrayClass = java.lang.reflect.Array.newInstance(typeClass, 0).getClass();
        Array sqlArray = rs.getArray(names[0]);
        if (!rs.wasNull()) {
            Object array = sqlArray.getArray();
            if (typeClass.isEnum()) {
                int length = array != null ? java.lang.reflect.Array.getLength(array) : 0;
                result = java.lang.reflect.Array.newInstance(typeClass, length);
                for (int i = 0; i < length; i++) {
                    java.lang.reflect.Array.set(result, i, idToEnum((Integer) java.lang.reflect.Array.get(array, i)));
                }
            } else {
                result = typeArrayClass.cast(array);
            }
        }
        return result;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
        throws SQLException {
        if (value == null) {
            st.setNull(index, Types.ARRAY);
            return;
        }

        Object[] valueToSet = (Object[]) value;
        Class<?> typeArrayClass = java.lang.reflect.Array.newInstance(typeClass, 0).getClass();

        if (typeClass.isEnum()) {
            typeArrayClass = Integer[].class;
            Integer[] converted = new Integer[valueToSet.length];

            for (int i = 0; i < valueToSet.length; i++) {
                if (valueToSet[i] instanceof Integer) {
                    converted[i] = (Integer) valueToSet[i];
                } else {
                    converted[i] = enumToId(valueToSet[i]);
                }
            }
            valueToSet = converted;
        }

        java.sql.Array array = st.getConnection().createArrayOf(PgArrayUtils.getNativeSqlType(typeClass),
            (Object[]) typeArrayClass.cast(valueToSet));
        st.setArray(index, array);
    }

    /**
     * 获取class类型
     *
     * @return class类型
     */
    public Class<?> getTypeClass() {
        return typeClass;
    }

    /**
     * 初始化双向Enum Map。
     *
     * @throws HibernateException HibernateException
     */
    private void ensureBidiMapInitialized() {
        try {
            if (bidiMap == null) {
                bidiMap = new BidiEnumMap(typeClass);
            }
        } catch (Exception e) {
            throw new HibernateException("Unable to create bidirectional enum map for " + typeClass, e);
        }
    }

    /**
     * 根据id获取enum信息。
     *
     * @param id id
     * @return enum信息
     * @throws HibernateException HibernateException
     */
    private Object idToEnum(int id) {
        ensureBidiMapInitialized();
        return bidiMap.getEnumValue(id);
    }

    /**
     * 根据enum信息获取id
     *
     * @param enumValue enum信息
     * @return id
     * @throws HibernateException HibernateException
     */
    private int enumToId(Object enumValue) {
        ensureBidiMapInitialized();
        return bidiMap.getKey(enumValue);
    }
}