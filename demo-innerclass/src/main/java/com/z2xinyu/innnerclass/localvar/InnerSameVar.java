package com.z2xinyu.innnerclass.localvar;

/**
 * 访问同名变量
 *
 */
public class InnerSameVar {
    public int num = 20;

    class InnerC {
        public int num = 30;

        public void method() {
            int num = 40;
            System.out.println(num); // 40
            System.out.println(this.num); // 30
            System.out.println(new InnerSameVar().num); // 20
            System.out.println(InnerSameVar.this.num); // 20
        }
    }
}
