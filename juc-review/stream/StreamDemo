package stream;

import pojo.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
//        常见使用方法：
//        题目要求:一分钟内完成此题，只能用一行代码实现!
//                现在有5个用户!筛选：
//        1、ID 必须是偶数
//        2、龄必须大于23岁
//        3、用户名转为大写字母
//        4、用户名字母倒着排序
//        5、只输出一个用户!
        User u1 = new User( 1, "a" ,21);
        User u2 = new User( 2, "b", 22);
        User u3 = new User( 3, "c", 23);
        User u4 = new User( 4, "d", 24);
        User u5 = new User( 6, "e",  25);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream()
                .filter((user)->{return user.getId()%2==0;})
                .filter((user)->{return user.getAge()>23;})
                .map((user -> {return user.getName().toUpperCase();}))
                .sorted((user1,user2)->{return user1.compareTo(user2);})
                .limit(1).forEach(System.out::println);


    }
}
