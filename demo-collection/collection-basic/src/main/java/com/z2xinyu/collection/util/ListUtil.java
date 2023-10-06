package com.z2xinyu.collection.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhangxinyu
 * @date 2023/8/15
 **/
public class ListUtil {

    public static void duplicateList(List<String> stringList) {
        for (int x = 0; x < stringList.size() - 1; x++) {
            for (int j = x + 1; j < stringList.size(); j++) {
                if (stringList.get(x).equals(stringList.get(j))) {
                    stringList.remove(j);
                    j--; // 元素少1
                }
            }
        }
    }

    public static List<String> duplicateList2(List<String> stringList) {
        List<String> temp = new ArrayList<String>();

        Iterator<String> it = stringList.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (!temp.contains(s)) {
                temp.add(s);
            }
        }
        return temp;
    }
}
