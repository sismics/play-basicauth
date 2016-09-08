package play.plugins;

import helpers.BasicAuthHelper;
import play.Play;
import play.PlayPlugin;
import play.mvc.Http;
import play.vfs.VirtualFile;

public class BasicAuthPlugin extends PlayPlugin {
    @Override
    public boolean serveStatic(VirtualFile file, Http.Request request, Http.Response response) {
        String basicAuthUrl = Play.configuration.getProperty("basicAuth.url");
        if (basicAuthUrl != null && request.url.startsWith(basicAuthUrl)) {
            if (!BasicAuthHelper.checkAuthenticationHeaders(request)) {
                BasicAuthHelper.unauthorized(response);
                return true;
            }
        }
        return super.serveStatic(file, request, response);
    }
}
