/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology.genes;

import org.pdfclown.jada.examples.uml.protoevo.core.Simulation;

/**
 * @author Dylan Cope
 */
public class RetinalProductionGene extends BoundedFloatGene {
  public RetinalProductionGene() {
    super(0, 1);
  }

  public RetinalProductionGene(Float value) {
    super(0, 1, value);
  }

  @Override
  public boolean canDisable() {
    return true;
  }

  @Override
  public <G extends Gene<Float>> G createNew(Float value) {
    return (G) new RetinalProductionGene(value);
  }

  @Override
  public Float disabledValue() {
    return 0f;
  }

  @Override
  public Float getNewValue() {
    if (Simulation.RANDOM.nextFloat() < 0.1f)
      return 0f;
    return super.getNewValue();
  }

  @Override
  public String getTraitName() {
    return "Retinal Production";
  }
}
