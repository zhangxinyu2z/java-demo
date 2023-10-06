package com.z2xinyu.io.tobe.demo03.test;

public class Student {
    private String name;
    private int mathScore;
    private int chineseScore;
    private int englishScore;

    public Student() {
        super();
    }

    public Student(String name, int mathScore, int chineseScore,
        int englishScore) {
        super();
        this.name = name;
        this.mathScore = mathScore;
        this.chineseScore = chineseScore;
        this.englishScore = englishScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(int chineseScore) {
        this.chineseScore = chineseScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(int englishScore) {
        this.englishScore = englishScore;
    }

    public int getTotalScore() {
        return this.mathScore + this.chineseScore + this.englishScore;
    }
}
