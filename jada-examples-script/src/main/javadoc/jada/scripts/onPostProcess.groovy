/*
 * Jada script demonstrating how to read a value shared across scripts.
 */

printf("\n>>>>>>>>>>>>>>> `%s` script - Shared variable \"%s\": \"%s\"", self.phase, "myVar",
    self.get("myVar"))
