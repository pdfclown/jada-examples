[Jada Examples](../README.md) >

# Javadoc fragments inclusion example

This module demonstrates how to use <code>[@include](https://github.com/pdfclown/jada/blob/main/jada-core/src/main/java/org/pdfclown/jada/core/taglet/IncludeTaglet.java)</code> Jada taglet to include Javadoc fragments from other files.

Such inclusion is also an interesting demonstration of <b>hierarchical resource selection</b> in Jada. The resource directories are configured via `--jada-dir` option, prioritized according to their inverse order of definition (that is, the last one has the highest priority) — this behavior is useful to override a resource with a more specific one of the same name. In this
example, we defined two resource directories:

1. <code>[/jada-examples/src/main/javadoc/jada](../src/main/javadoc/jada)</code>
2. <code>[/jada-examples/jada-examples-include/src/main/javadoc/jada](src/main/javadoc/jada)</code>

Because of the above-mentioned ordering, module-specific `jada-examples/jada-examples-include/.../jada` wins over common `jada-examples/.../jada`; as a result, now you are reading the content of `jada-examples/jada-examples-include/.../jada/overview.include.html` instead of `jada-examples/.../jada/overview.include.html`, whilst they both correspond to the same resource name (`overview.include.html`). If you change the name of the former file and regenerate Javadoc (`./mvnw clean javadoc:javadoc -pl jada-examples-include`), you will see the latter (previously overridden because of its lower priority) is rendered instead!
