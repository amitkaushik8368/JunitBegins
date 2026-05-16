package tests;

public class TestClass1
{
    static {
        System.out.println("I am inside Static Block of Test Class 1");
    }
    {
        System.out.println("I am inside instance block");
    }
    public static void method()
    {
        System.out.println("I am inside static method");
    }
}
