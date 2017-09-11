package com.neerajgoel.java8.lambda;

import java.util.*;

/**
 * Created by neeraj on 17/03/16.
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person(39, "Neeraj"), new Person(35, "Mohit"));
        Comparator<Person> comp = new PersonAgeComparator();

        //Collections.sort(people, comp);
        //Collections.sort(people, (p1,p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        Collections.sort(people, Person::compareAges);
        for ( int i = 0 ; i < people.size(); i++) {
            System.out.println(people.get(i).getName());
        }
    }
}
