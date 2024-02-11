package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter // getter, setter, constructor 등을 자동으로 생성해 줌.
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
