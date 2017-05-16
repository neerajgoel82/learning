package com.neerajgoel.java8.methodhandles;

/**
 * Created by neeraj on 17/03/16.
 */
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class HW
{
    public void hello1()
    {
        System.out.println("hello from hello1");
    }

    private void hello2()
    {
        System.out.println("hello from hello2");
    }
}

class Point
{
    int x;
    int y;
}


public class MHD
{
    public static void main(String[] args) throws Throwable
    {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(MHD.class, "hello",
                MethodType.methodType(void.class));
        MHD tmp = new MHD();
        mh.invokeExact(tmp);

        mh = lookup.findStatic(MHD.class, "hello2",
                MethodType.methodType(void.class));
        mh.invoke();

        mh = lookup.findStatic(MHD.class, "helloWithName",
                MethodType.methodType(void.class, String.class));
        mh.invoke("Neeraj");

        HW hw = new HW();
        mh = lookup.findVirtual(HW.class, "hello1",
                MethodType.methodType(void.class));

        mh.invoke(hw);

        /*mh = lookup.findVirtual(HW.class, "hello2",
                MethodType.methodType(void.class));
        mh.invoke(hw);*/

        // Set the x and y fields.
        Point point = new Point();
        mh = lookup.findSetter(Point.class, "x",
                int.class);
        mh.invoke(point, 15);

        mh = lookup.findSetter(Point.class, "y", int.class);
        mh.invoke(point, 30);

        mh = lookup.findGetter(Point.class, "x", int.class);
        int x = (int) mh.invoke(point);
        System.out.printf("x = %d%n", x);

        mh = lookup.findGetter(Point.class, "y", int.class);
        int y = (int) mh.invoke(point);
        System.out.printf("y = %d%n", y);

        lookup = MethodHandles.lookup();
        mh = lookup.findStatic(Math.class, "pow",
                MethodType.methodType(double.class,
                        double.class,
                        double.class));
        mh = MethodHandles.insertArguments(mh, 1, 10);
        System.out.printf("2^10 = %d%n", (int) (double) mh.invoke(2.0));
    }

    private void hello()
    {
        System.out.println("hello");
    }

    private static void hello2()
    {
        System.out.println("static hello");
    }

    private static void helloWithName(String name)
    {
        System.out.println( "hello " + name);
    }
}