package org.pdfclown.jada.examples.docreuse;

/**
 * This example demonstrates Javadoc fragment reuse in callable member overloads.
 */
public class OverloadExample {
  /**
   * Constructor overload without parameter. {@jada.reuseDoc }
   * <p>
   * This is from {@link #OverloadExample(String)}
   * </p>
   * {@jada.reuseDoc END}
   */
  public OverloadExample() {
  }

  /**
   * Constructor overload with 1 parameter. {@jada.doc }
   * <p>
   * This is from {@link #OverloadExample(String)}
   * </p>
   * {@jada.doc END}
   *
   * @param s
   *          String value.
   */
  public OverloadExample(String s) {
  }

  /**
   * Method overload without parameters. {@jada.reuseDoc }
   * <p>
   * This is from {@link #overload(String, int)}
   * </p>
   * {@jada.reuseDoc END}
   */
  public void overload() {
  }

  /**
   * Method overload with 1 parameter. {@jada.reuseDoc :extra}
   * <p>
   * This is an extra fragment from {@link #overload(String, int)}
   * </p>
   * {@jada.reuseDoc END} {@jada.reuseDoc }
   * <p>
   * This is from {@link #overload(String, int)}
   * </p>
   * {@jada.reuseDoc END}
   *
   * @param s
   *          String value.
   */
  public void overload(String s) {
  }

  /**
   * Method overload with 2 parameters. {@jada.doc }
   * <p>
   * This is from {@link #overload(String, int)}
   * </p>
   * {@jada.doc END} {@jada.doc extra}
   * <p>
   * This is an extra fragment from {@link #overload(String, int)}
   * </p>
   * {@jada.doc END}
   *
   * @param s
   *          String value.
   * @param i
   *          Index.
   */
  public void overload(String s, int i) {
  }
}
