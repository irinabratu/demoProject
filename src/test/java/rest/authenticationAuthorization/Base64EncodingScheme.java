package rest.authenticationAuthorization;

import java.util.Base64;

public class Base64EncodingScheme {
    public static void main(String[] args) {
        String usernameColonPass = "myUsername:myPass";
        String encoded = Base64.getEncoder().encodeToString(usernameColonPass.getBytes());
        System.out.println(encoded);

        byte[] decoded = Base64.getDecoder().decode(encoded);

        System.out.println(new String(decoded));
     }
}
