package com.neerajgoel.java8.lambda;

import java.util.Comparator;

/**
 * Created by neeraj on 17/03/16.
 */
public class PersonAgeComparator implements Comparator<Person>{
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}
