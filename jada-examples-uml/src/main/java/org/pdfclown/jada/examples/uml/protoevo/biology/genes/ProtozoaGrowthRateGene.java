/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology.genes;

import java.io.Serializable;
import org.pdfclown.jada.examples.uml.protoevo.core.Settings;

/**
 * @author Dylan Cope
 */
public class ProtozoaGrowthRateGene extends BoundedFloatGene implements Serializable {
  public ProtozoaGrowthRateGene() {
    super(Settings.minProtozoanGrowthRate, Settings.maxProtozoanGrowthRate);
  }

  public ProtozoaGrowthRateGene(Float value) {
    super(Settings.minProtozoanGrowthRate, Settings.maxProtozoanGrowthRate, value);
  }

  @Override
  public <G extends Gene<Float>> G createNew(Float value) {
    return (G) new ProtozoaGrowthRateGene(value);
  }

  @Override
  public String getTraitName() {
    return "Growth Rate";
  }
}
