package xyz.tcbuildmc.common.powerfullib.config.v0.api.reflect;

import lombok.Getter;
import xyz.tcbuildmc.powerfullib.annotation.Unsafe;

import java.lang.reflect.*;

/**
 * Type reference.
 * @param <T> Type.
 */
@Getter
public abstract class TypeRef<T> {
    private final Class<? super T> rawType;
    private final Type type;

    protected TypeRef() {
        Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new IllegalArgumentException("TypeRef<T> constructed without actual type information.");
        } else {
            this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        }
        this.rawType = (Class<? super T>) getRawType(this.type);
    }

    @Unsafe
    private TypeRef(Type type) {
        this.type = type;
        this.rawType = (Class<? super T>) getRawType(this.type);
    }

    private static Class<?> getRawType(Type type) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (rawType instanceof Class) {
                return (Class<?>) rawType;
            }

            throw new IllegalArgumentException("Expected a Class, but <" + type + "> is of type " + rawType);
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();
        } else if (type instanceof TypeVariable) {
            return Object.class;
        } else if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or "
                    + "GenericArrayType, but <" + type + "> is of type " + className);
        }
    }
}
