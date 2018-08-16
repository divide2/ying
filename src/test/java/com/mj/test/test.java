package com.ying.test;

/**
 * @author zejun
 * @date 2018/7/27 10:49
 */
public class test {

    public static void main(String[] args) {
        String strs = "1\t10GP\t10\'通用箱\n2\t10RT\t10\'冻柜";
        String[] ss = strs.split("\t");
        System.out.println(ss[0]);
    }
}
