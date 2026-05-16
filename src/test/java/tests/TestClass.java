package tests;

import utils.JsonReader;

public class TestClass
{
    static {
        System.out.println("I am inside static block of Test Class");
    }
    public static void main(String[] args) {
        //System.out.println(JsonReader.getValue("validUser", "username"));
        TestClass1.method();
    }
}
