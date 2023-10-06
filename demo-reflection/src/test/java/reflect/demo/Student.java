package reflect.demo;

public class Student extends People  {

    public String classNumber;
    protected double score;
    static String country;

    public Student() {

    }

    private Student(String name) {
        super(name);
    }

    public Student(String classNumber, double score) {
        this.classNumber = classNumber;
        this.score = score;
    }

    protected Student(String name, int age, double high, double weight, String classNumber, double score) {
        super(name, age, high, weight);
        this.classNumber = classNumber;
        this.score = score;
    }



    @Override
    public String toString() {
        return weight + " : " + classNumber + " : " + score;
    }

    public static void study() {
        System.out.println("学习");
    }

    private void run(double time) {
        System.out.println("跑步" + time);
    }

    void play() {
        System.out.println("玩游戏");
    }
}
