package codingbat.warmup1;

public class FrontBack {

    /**
     * Given a string, return a new string where the first and last chars have been exchanged.
     *
     * frontBack("code") → "eodc"
     * frontBack("a") → "a"
     * frontBack("ab") → "ba"
     **/

    public String frontBack(String str) {
        if (str.length()>1){
            return str.substring(str.length()-1) + str.substring(1,str.length()-1) + str.substring(0,1);
        } else return str;
    }
}
