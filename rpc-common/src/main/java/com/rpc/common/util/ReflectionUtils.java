package com.rpc.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 * @author JianBai
 * @date 2022/8/26 23:44
 */
public class ReflectionUtils {

    /**
     * 根据clazz创建对象
     * @param clazz 待创建对象的类
     * @param <T> 对象类型
     * @return 创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch(Exception e){
            throw  new IllegalStateException(e);
        }
    }

    /**
     * 获取某个class的公有方法
     * @param clazz --
     * @return --
     */
    public static Method[] getPublicMethods(Class clazz){
        //返回当前类所有的方法，不包含父类
        Method[] methods= clazz.getDeclaredMethods();
        List<Method> publicMethods = new ArrayList<>();
        //过滤出public
        for(Method m : methods){
            if(Modifier.isPublic(m.getModifiers())){
                publicMethods.add(m);
            }
        }
        return publicMethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 返回结果
     */


    public static Object invoke(Object obj,Method method,Object... args){
        try {
            return  method.invoke(obj,args);
        }catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
}
