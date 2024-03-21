package codingbat.string1;

public class FrontAgain {
    /**
     * Given a string, return true if the first 2 chars in the string also appear at the end of the string, such as with "edited".
     *
     * frontAgain("edited") → true
     * frontAgain("edit") → false
     * frontAgain("ed") → true
     **/

    public boolean frontAgain(String str) {
        return (str.length()>=2 && str.substring(0,2).equals(str.substring(str.length()-2)));
    }
}
