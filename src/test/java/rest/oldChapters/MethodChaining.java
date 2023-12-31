package rest.oldChapters;

public class MethodChaining {

    public static void main(String[] args) {
        a1().a2().a3();
    }

    public static MethodChaining a1(){
        System.out.println("a1 method");
        return new MethodChaining();
    }

    public static MethodChaining a2() {
        System.out.println("a2 method");
        return new MethodChaining();
    }

    public static MethodChaining a3() {
        System.out.println("a3 method");
        return new MethodChaining();
    }
}
