/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology.genes;

import java.io.Serializable;

/**
 * @author Dylan Cope
 */
public class HerbivoreFactorGene extends BoundedFloatGene implements Serializable {
  private static final float minValue = 0.5f;
  private static final float maxValue = 2f;

  public HerbivoreFactorGene() {
    super(minValue, maxValue);
  }

  public HerbivoreFactorGene(Float value) {
    super(minValue, maxValue, value);
  }

  @Override
  public <G extends Gene<Float>> G createNew(Float value) {
    return (G) new HerbivoreFactorGene(value);
  }

  @Override
  public String getTraitName() {
    return "Herbivore Factor";
  }
}
