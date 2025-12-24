Jada Examples > [Documentation](README.md) >

# Usage

This document describes how to use Jada Examples.

All the examples are based on the Maven build system.

Generate the Javadoc output (under `target/reports/apidocs` of respective module) entering the following CLI command (where `%MODULE_NAME%` corresponds to the module to generate, e.g. `jada-examples-biblio`):

```shell
./mvnw package -pl %MODULE_NAME%
```

> [!IMPORTANT]
> Because of [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/CORS) policies, modern web browsers don't allow running Javascript in local HTML files; as a consequence, *the generated Javadoc cannot be accessed directly from the filesystem: you need to locally host it via **web server***. A popular choice is Python HTTP server (replace `%PORT%` with the port to use (e.g., `8000`), and `%JAVADOC_OUTPUT_PATH%` with the local path to `target/reports/apidocs`):
> ```shell
> python3 -m http.server %PORT% -d %JAVADOC_OUTPUT_PATH%
> ```
> Once the web server is running, use your browser to navigate to `http://localhost:8000/` (replace `8000` with the port you set on server start).