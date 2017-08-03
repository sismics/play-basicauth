package controllers;

import helpers.BasicAuthHelper;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * @author jtremeaux
 */
public class BasicAuthSecure extends Controller {
    @Before(priority = 1)
    static void checkAccess() throws Throwable {
        CheckBasicAuth basicAuth = getActionAnnotation(CheckBasicAuth.class);
        if (basicAuth == null) {
            return;
        }
        String credentials = basicAuth.credentials();
        if (!BasicAuthHelper.checkAuthenticationHeaders(request, BasicAuthHelper.getUsername(credentials), BasicAuthHelper.getPassword(credentials))) {
            BasicAuthHelper.unauthorized(response, BasicAuthHelper.getRealm(credentials));
        }
    }
}
