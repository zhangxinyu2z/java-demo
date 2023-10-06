package com.z2xinyu.enums;

/**
 * 枚举类可以有普通方法、静态方法、抽象方法、构造方法
 * 无法继承，因为所有的枚举类都是继承Enum类，java不支持多继承
 *
 * @author z-xy
 */
public enum ErrorCodeEn implements INumberEnum {
    /**
     *
     */
    OK(0, "成功"),
    ERROR_A(100, "错误A"),
    ERROR_B(200, "错误B");

    ErrorCodeEn(int number, String description) {
        this.code = number;
        this.description = description;
    }

    private int code;
    private String description;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static void main(String[] args) { // 静态方法
        for (ErrorCodeEn s : ErrorCodeEn.values()) {
            /*
                code: 0, description: 成功
                code: 100, description: 错误A
                code: 200, description: 错误B
             */
            System.out.println("code: " + s.getCode() + ", description: " + s.getDescription());
        }
    }
}

interface INumberEnum {
    int getCode();

    String getDescription();
}
