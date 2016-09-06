package helpers;

import play.Play;
import play.libs.Codec;
import play.mvc.Http;

/**
 * @author jtremeaux
 */
public class BasicAuthHelper {
    public static void unauthorized(Http.Response response) {
        String realm = Play.configuration.getProperty("basicAuth.realm", "Secure Realm");
        response.status = Http.StatusCode.UNAUTHORIZED;
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
    }

    public static boolean checkAuthenticationHeaders(Http.Request request) {
        Http.Header authHeader = request.headers.get("authorization");
        if (authHeader != null) {
            String username = Play.configuration.getProperty("basicAuth.username");
            String password = Play.configuration.getProperty("basicAuth.password");
            if (username == null || password == null) {
                return true;
            }
            String expected = "Basic " + Codec.encodeBASE64(username + ":" + password);
            if (expected.equals(authHeader.value())) {
                return true;
            }
        }
        return false;
    }
}
