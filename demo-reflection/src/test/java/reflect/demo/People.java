package reflect.demo;

public class People {
    public String name;
    private int age;
    double high;
    public double weight;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    protected People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public People(String name, int age, double high, double weight) {
        this.name = name;
        this.age = age;
        this.high = high;
        this.weight = weight;
    }

    public void eat() {
        System.out.println("人要吃饭");
    }

    public void sleep() {
        System.out.println("人要睡觉");
    }

}

