package pl.edu.agh.rentableoffices.authentication.security.jwt;

class JwtAuthenticationConfig {

    private static final int expiration = 60 * 60; // default 1 hour

    private static final String secret = "secret";

    public static int getExpiration() {
        return expiration;
    }

    public static String getSecret() {
        return secret;
    }
}
