package com.divide2.team.listener;

/**
 * @author bvvy
 * @date 2019/3/5
 */
public interface Listener<T> {

    void onCreate(T t);

    void onDelete(T t);

}
