package codingbat.string1;

public class LastChars {
    /**
     * Given 2 strings, a and b, return a new string made of the first char of a and the last char of b, so "yo" and "java" yields "ya". If either string is length 0, use '@' for its missing char.
     *
     * lastChars("last", "chars") → "ls"
     * lastChars("yo", "java") → "ya"
     * lastChars("hi", "") → "h@"
     **/

    public String lastChars(String a, String b) {
        String charA = "@";
        String charB = "@";
        if(a.length()>0){
            charA = a.substring(0,1);
        }
        if(b.length()>0){
            charB = b.substring(b.length()-1);
        }
        return charA+charB;
    }
}
