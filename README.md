[![GitHub release](https://img.shields.io/github/release/sismics/play-basicauth.svg?style=flat-square)](https://github.com/sismics/play-basicauth/releases/latest)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# play-basicauth plugin

This plugin adds [HTTP Basic authentication](https://en.wikipedia.org/wiki/Basic_access_authentication) support to Play! Framework 1 applications.

# How to use

####  Add the dependency to your `dependencies.yml` file

```
require:
    - basicauth -> basicauth 1.1.0

repositories:
    - sismicsNexusRaw:
        type: http
        artifact: "https://nexus.sismics.com/repository/sismics/[module]-[revision].zip"
        contains:
            - basicauth -> *

```
####  Run the `play deps` command

####  Add the configuration to `application.conf`

```
basicAuth.username=admin
basicAuth.password=pass1234
basicAuth.realm=Secure
```

####  Secure your actions

Add the BasicAuthSecure interceptor to the controllers to secure.

```
@With(BasicAuthSecure.class)
public class MyController extends Controller { ... }
```

Add the @CheckBasicAuth annotation to the actions to secure.

```
@BasicAuth
public static void doSomeStuff() { ... }
```

####  Secure a static route

Add the route URL to the configuration:

```
basicAuth.url=/public/admin
```

####  Secure actions with different credentials

Add a prefix to declare multiple credentials: 

@CheckBasicAuth(credentials = "admin")

```
basicAuth.admin.username=admin
basicAuth.admin.password=pass1234
basicAuth.admin.realm=Secure admin
```

# License

This software is released under the terms of the Apache License, Version 2.0. See `LICENSE` for more
information or see <https://opensource.org/licenses/Apache-2.0>.
