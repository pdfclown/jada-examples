/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.utils;

/**
 * @author Dylan Cope
 */
public class CachedMath {
  static final int precision = 1000;

  static final float[] sin = new float[precision]; // lookup table
  static {
    // a static initializer fills the table
    // in this implementation, units are in degrees
    for (int i = 0; i < sin.length; i++) {
      sin[i] = (float) Math.sin(2 * Math.PI * i / (float) precision);
    }
  }

  public static float cos(float a) {
    return sinLookup((int) (((a + Math.PI / 2) * precision) / (2 * Math.PI)));
  }

  // These are your working functions:
  public static float sin(float a) {
    return sinLookup((int) (a * precision / (2 * Math.PI)));
  }

  // Private function for table lookup
  private static float sinLookup(int a) {
    return a >= 0 ? sin[a % precision] : -sin[-a % precision];
  }
}
