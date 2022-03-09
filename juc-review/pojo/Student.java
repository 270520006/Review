package pojo;

/**
 * @author zsp
 * @version 1.0
 * @date 2022/3/9 9:40
 */

public class Student {
    private int age;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Student() {
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
