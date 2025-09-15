package introduction;

public class MethodsDemo {

    public static void main(String[] args) {
        MethodsDemo d = new MethodsDemo();
        String name = d.getData();
        System.out.println(name); // Prints "rahul shetty"

        // This requires the MethodsDemo2 class to exist
        MethodsDemo2 d1 = new MethodsDemo2();
        d1.getUserData();

        // Calling the static method directly
        getData2();
    }

    // This is an instance method, requires an object to be called
    public String getData() {
        System.out.println("hello world");
        return "rahul shetty";
    }

    // This is a static method, can be called without creating an object
    public static String getData2() {
        System.out.println("hello world");
        return "rahul shetty";
    }
}