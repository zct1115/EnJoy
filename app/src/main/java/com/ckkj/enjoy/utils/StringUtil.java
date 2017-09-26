package com.ckkj.enjoy.utils;

/**
 * Created by Ting on 2017/9/20.
 */

public class StringUtil {

    public String testStringBuilder() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100000; i++) {

            sb.append(String.valueOf(i));

        }

      return   sb.toString();
    }
}
