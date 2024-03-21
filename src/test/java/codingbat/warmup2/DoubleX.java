package codingbat.warmup2;

public class DoubleX {

    /**
     * Given a string, return true if the first instance of "x" in the string is immediately followed by another "x".
     *
     * doubleX("axxbb") → true
     * doubleX("axaxax") → false
     * doubleX("xxxxx") → true
     **/

    boolean doubleX(String str) {
        if (str.length()<2 || !str.contains("xx")) {return false;}
        return str.charAt(str.indexOf('x')+1) == 'x';
    }
}
