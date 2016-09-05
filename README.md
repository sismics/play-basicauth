# play-basicauth plugin

This plugin adds [HTTP Basic authentication](https://en.wikipedia.org/wiki/Basic_access_authentication) support to Play! Framework 1 applications.

# How to use

####  Add the dependency to your `dependencies.yml` file

```
require:
    - basicauth -> basicauth 0.1

repositories:
    - sismics:
        type:       http
        artifact:   "http://release.sismics.com/repo/play/[module]-[revision].zip"
        contains:
            - basicauth -> *

```
####  Run the `play deps` command

####  Add the configuration to `application.conf`

```
basicAuth.url=/public/admin
basicAuth.username=admin
basicAuth.password=pass1234
basicAuth.realm=Secure
```

# License

This software is released under the terms of the Apache License, Version 2.0. See `LICENSE` for more
information or see <https://opensource.org/licenses/Apache-2.0>.
