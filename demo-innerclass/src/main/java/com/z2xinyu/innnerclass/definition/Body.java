package com.z2xinyu.innnerclass.definition;

/**
 * 人的身体
 */
public class Body {
    /**
     * 人的心脏，不应该暴露 private 保护
     */
    private class Heart {
        public void operate() {
            System.out.println("心脏手术");
        }
    }

    public void method() {
        // 如果你是医生，你可以做手术
        Heart h = new Heart();
        h.operate();
    }
}
