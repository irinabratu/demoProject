package codingbat.warmup2;

public class AltPairs {
    /**
     * Given a string, return a string made of the chars at indexes 0,1, 4,5, 8,9 ... so "kittens" yields "kien".
     *
     * altPairs("kitten") → "kien"
     * altPairs("Chocolate") → "Chole"
     * altPairs("CodingHorror") → "Congrr"
     **/

    public String altPairs(String str) {
        String result = "";
        if(str.length() <= 2 ) {
            return str;
        }
        for (int i=0; i<=str.length()-1;i+=4){
            if(i!=str.length()-1){
                result += str.substring(i,i+2);
            }
            else {
                result += str.charAt(str.length()-1);
            }
        }
        return result;
    }
}
