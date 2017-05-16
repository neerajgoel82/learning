package com.neerajgoel.java8.lambda;

/**
 * Created by neeraj on 17/03/16.
 */
public class Person {
    private int age;
    private String name ;

    public Person(int age, String name) {
        this.age = age ;
        this.name = name;
    }

    public String getName() {
        return this.name ;
    }

    public int getAge() {
        return this.age ;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age ;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int compareAges(Person p2) {
        return Integer.compare(this.age, p2.age);
    }

    public int randomCompare(Person p2, Person p1) {
        return 1;
    }
}
