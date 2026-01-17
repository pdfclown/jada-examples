[Jada Examples](..) >

# Bibliography extension example

This module demonstrates how to use [JadaBiblio](https://github.com/pdfclown/jada/tree/main/jada-biblio), a Jada doclet extension for bibliography rendering in Javadoc.

Please note that the bibliographic entries come from multiple sources:

1. <code>[/jada-examples/src/main/javadoc/jada/ext/JadaBiblio/biblio.xml](../src/main/javadoc/jada/ext/JadaBiblio/biblio.xml)</code>
2. <code>[/jada-examples/jada-examples-biblio/src/main/javadoc/jada/ext/JadaBiblio/biblio.xml](src/main/javadoc/jada/ext/JadaBiblio/biblio.xml)</code>

Besides file-system sources, bibliographic entries can come from resource dependecies on classpath too — this is a core feature of Jada doclet, available to any extension. The resource directories are configured via `--jada-dir` option, prioritized according to their inverse order of definition (that is, the last one has the highest priority) — this behavior is useful to combine or override a resource with a more specific one of the same name.
