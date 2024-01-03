package rest.oldChapters;

public class MyClassImpl implements MyInterface{
    private MyInterface MyInterface;

    public MyInterface printMe(){
        System.out.println("im the class that implements the interface");
        return MyInterface;
    }
}
