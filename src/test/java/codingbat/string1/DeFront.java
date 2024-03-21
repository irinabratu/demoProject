package codingbat.string1;

public class DeFront {
    /**
     * Given a string, return a version without the first 2 chars. Except keep the first char if it is 'a' and keep the second char if it is 'b'. The string may be any length. Harder than it looks.
     *
     * deFront("Hello") → "llo"
     * deFront("java") → "va"
     * deFront("away") → "aay"
     **/

    public String deFront(String str) {
        String result = "";
        if(str.length()>0 && str.charAt(0)=='a'){
            result = result + str.charAt(0);
        }
        if(str.length()>1 && str.charAt(1)=='b'){
            result = result + str.charAt(1);
        }
        result = result + str.substring(2);
        return result;
    }
}
