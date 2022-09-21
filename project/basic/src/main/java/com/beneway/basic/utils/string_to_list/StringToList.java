package com.beneway.basic.utils.string_to_list;

import com.beneway.basic.system.sys_agency.po.SysAgency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringToList {

    /**
     * 将字符串转换成list
     * @param value 字符串
     * @param split 分隔符
     * @return 集合
     */
    public static List<String> stringToList(String value, String split) {
        String[] values = value.split(split);
        return Arrays.asList(values);
    }

    public static String SysAgencyToNameList(List<SysAgency> lists){
        List<String> result = new ArrayList<>();
        if (lists.size() == 0) {return result.toString();}
        lists.forEach(item -> {
            result.add(item.getName());
        });
        return result.toString();
    }
}
