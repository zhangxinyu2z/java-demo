package com.z2xinyu.collection.po;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangxinyu
 * @date 2023/7/9
 **/
@Data
@Builder
@ToString
public class Girl {
    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Girl student = (Girl)o;
        return age == student.age && name.equals(student.name);
    }

}
