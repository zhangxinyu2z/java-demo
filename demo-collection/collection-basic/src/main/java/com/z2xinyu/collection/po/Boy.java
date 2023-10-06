package com.z2xinyu.collection.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhangxinyu
 * @date 2023/7/10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Boy {

    private String name;

    private int age;

    @Override
    public int hashCode() {
        return 20;
    }

    @Override
    public boolean equals(Object obj) {
        Boy s = (Boy) obj;
        return s.name.equals(this.name) && s.age == age;
    }
}
