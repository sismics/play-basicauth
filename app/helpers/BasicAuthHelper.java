package helpers;

import play.Play;
import play.libs.Codec;
import play.mvc.Http;

/**
 * @author jtremeaux
 */
public class BasicAuthHelper {
    public static boolean checkAuthenticationHeaders(Http.Request request, String username, String password) {
        if (username == null || password == null) {
            return true;
        }
        Http.Header authHeader = request.headers.get("authorization");
        if (authHeader != null) {
            String expected = "Basic " + Codec.encodeBASE64(username + ":" + password);
            if (expected.equals(authHeader.value())) {
                return true;
            }
        }
        return false;
    }

    public static String getPassword(String credentials) {
        return Play.configuration.getProperty(getPrefix(credentials) + ".password");
    }

    public static String getUsername(String credentials) {
        return Play.configuration.getProperty(getPrefix(credentials) + ".username");
    }

    public static String getRealm(String credentials) {
        return Play.configuration.getProperty(getPrefix(credentials) + ".realm", "Secure Realm");
    }

    private static String getPrefix(String credentials) {
        String prefix = "basicAuth";
        if (credentials != null && !credentials.trim().isEmpty()) {
            prefix += "." + credentials;
        }
        return prefix;
    }
}
