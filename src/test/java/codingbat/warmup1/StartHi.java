package codingbat.warmup1;

public class StartHi {

    /**
     * Given a string, return true if the string starts with "hi" and false otherwise.
     *
     * startHi("hi there") → true
     * startHi("hi") → true
     * startHi("hello hi") → false
     **/

    public boolean startHi(String str) {
        return (str.length()>=2 && str.substring(0,2).equals("hi"));
    }
}
