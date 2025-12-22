[Jada Examples :: Documentation](README.md) >

# Jada Examples :: Usage

This document describes how to use Jada Examples.

All the examples are based on the Maven build system.

Generate the Javadoc output (under `target/reports/apidocs` of respective module) entering the following CLI command (where `%MODULE_NAME%` corresponds to the module to generate, e.g. `jada-examples-biblio`):

```shell
./mvnw javadoc:javadoc -pl %MODULE_NAME%
```
