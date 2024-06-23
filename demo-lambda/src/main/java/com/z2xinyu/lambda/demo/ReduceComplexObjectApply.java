package com.z2xinyu.lambda.demo;

import lombok.*;
import lombok.experimental.Tolerate;

import java.util.*;

/**
 * 场景：计算一个网站用户的评分，该评分是所有用户所有评论的平均值。
 *
 * @author zhangxinyu
 * @since 2022/5/30 19:14
 */
public class ReduceComplexObjectApply {
    public static void main(String[] args) {
        User john = User.builder().username("John").age(30).build();
        john.getRating().add(new Review(5, ""));
        john.getRating().add(new Review(3, "not bad"));

        User julie = User.builder().username("John").age(35).build();
        john.getRating().add(new Review(4, "great!"));
        john.getRating().add(new Review(2, "terrible experience"));
        john.getRating().add(new Review(4, ""));

        List<User> users = Arrays.asList(john, julie);
        // 处理评分
        Rating averageRating = users.stream()
            .reduce(new Rating(), (rating, user) -> Rating.average(rating, user.getRating()), Rating::average);
    }

}

@Builder // 生成全参构造函数
@Setter
@Getter
//@Data
    /*
    @Data和@Builder同时使用添加无参构造函数报错
    java: 无法将类 my.java.util.sample.User中的构造器 User应用到给定类型;
  需要: 没有参数
  找到: java.lang.String,int,my.java.util.sample.Rating
  原因: 实际参数列表和形式参数列表长度不同

  缺少无参构造：使用 @Tolerate注解让lombok忽略
     */
class User {
    private String username;
    private int age;
    private Rating rating;

    @Tolerate
    public User() {}
}

@Data
@AllArgsConstructor
class Review {

    private int points;
    private String review;

    // constructor, getters and setters
}

//    引用 Review 计算用户的评分
class Rating {

    double points;
    List<Review> reviews = new ArrayList<>();

    public void add(Review review) {
        reviews.add(review);
        computeRating();
    }

    private double computeRating() {
        double totalPoints = reviews.stream().map(Review::getPoints).reduce(0, Integer::sum);
        this.points = totalPoints / reviews.size();
        return this.points;
    }

    public static Rating average(Rating r1, Rating r2) {
        Rating combined = new Rating();
        combined.reviews = new ArrayList<>(r1.reviews);
        combined.reviews.addAll(r2.reviews);
        combined.computeRating();
        return combined;
    }

}

