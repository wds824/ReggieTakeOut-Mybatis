package com.wds.common;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 9:45
 * 每个线程一个Id存储器
 */

public class BaseContext {
    private static ThreadLocal<AppId> threadLocal= new ThreadLocal<>();

    public static Long getUserId(){
        if (threadLocal.get() == null) {
            return null;
        }
        return threadLocal.get().getUserId();
    }
    public static Long getEmpId(){
        if (threadLocal.get() == null) {
            return null;
        }
        return threadLocal.get().getEmployeeId();
    }
    public static void setUserId(Long id){
        if (threadLocal.get() == null){
            threadLocal.set(new AppId());
        }
        threadLocal.get().setUserId(id);
    }
    public static void setEmpId(Long id){
        if (threadLocal.get() == null){
            threadLocal.set(new AppId());
        }
        threadLocal.get().setEmployeeId(id);
    }



}
