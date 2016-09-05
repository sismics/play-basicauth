package play.plugins;

import play.Play;
import play.PlayPlugin;
import play.libs.Codec;
import play.mvc.Http;
import play.mvc.Util;
import play.vfs.VirtualFile;

public class BasicAuthPlugin extends PlayPlugin {
    @Override
    public boolean serveStatic(VirtualFile file, Http.Request request, Http.Response response) {
        if (!checkAuthenticationHeaders(request)) {
            String realm = Play.configuration.getProperty("basicAuth.realm", "Secure Realm");
            response.status = Http.StatusCode.UNAUTHORIZED;
            response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
            return true;
        }
        return super.serveStatic(file, request, response);
    }

    @Util
    private boolean checkAuthenticationHeaders(Http.Request request) {
        String basicAuthUrl = Play.configuration.getProperty("basicAuth.url");
        if (basicAuthUrl == null || !request.url.startsWith(basicAuthUrl)) {
            return true;
        }
        Http.Header authHeader = request.headers.get("authorization");
        if (authHeader != null) {
            String username = Play.configuration.getProperty("basicAuth.username");
            String password = Play.configuration.getProperty("basicAuth.password");
            String expected = "Basic " + Codec.encodeBASE64(username + ":" + password);
            if (expected.equals(authHeader.value())) {
                return true;
            }
        }
        return false;
    }
}
