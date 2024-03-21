package codingbat.warmup1;

public class Diff21 {

    /**
     * Given an int n, return the absolute difference between n and 21, except return double the absolute difference if n is over 21.
     *
     * diff21(19) → 2
     * diff21(10) → 11
     * diff21(21) → 0
     * **/

    public int diff21(int n) {
        int diff = Math.abs(21-n);
        if(n>=21){
            return 2 * diff;
        }
        return diff;
    }
}
