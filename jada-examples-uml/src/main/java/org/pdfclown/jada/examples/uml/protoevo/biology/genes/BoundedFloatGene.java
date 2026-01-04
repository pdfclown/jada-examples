/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology.genes;

import java.io.Serializable;
import org.pdfclown.jada.examples.uml.protoevo.core.Simulation;

/**
 * @author Dylan Cope
 */
public abstract class BoundedFloatGene extends Gene<Float> implements Serializable {
  float minValue, maxValue;

  public BoundedFloatGene(float minValue, float maxValue) {
    this.minValue = minValue;
    this.maxValue = maxValue;
    setValue(getNewValue());
  }

  public BoundedFloatGene(float minValue, float maxValue, float value) {
    this.minValue = minValue;
    this.maxValue = maxValue;
    setValue(value);
  }

  @Override
  public boolean canDisable() {
    return false;
  }

  @Override
  public Float disabledValue() {
    throw new RuntimeException("Cannot disable " + BoundedFloatGene.class);
  }

  @Override
  public Float getNewValue() {
    return minValue + (maxValue - minValue) * Simulation.RANDOM.nextFloat();
  }
}
