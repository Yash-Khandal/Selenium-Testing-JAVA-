package introduction;

public class CoreJavaBrushUp2 {

    public static void main(String[] args) {
        // String is an object //String literal
        // String s1 = "Rahul Shetty Academy";
        String s5 = "hello";

        // new
        String s2 = new String("Welcome");
        String s3 = new String("Welcome");

        String s = "Rahul Shetty Academy";
        String[] splittedString = s.split("Shetty");
        System.out.println(splittedString[0]);
        System.out.println(splittedString[1]);
        System.out.println(splittedString[1].trim());

        // This loop prints the string in reverse
        for (int i = s.length() - 1; i >= 0; i--) {
            // Using print instead of println to see the reversed string on one line
            System.out.print(s.charAt(i));
        }
    }
}