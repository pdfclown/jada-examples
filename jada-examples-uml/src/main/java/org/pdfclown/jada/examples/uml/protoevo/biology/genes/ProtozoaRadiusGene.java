/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology.genes;

import java.io.Serializable;
import org.pdfclown.jada.examples.uml.protoevo.core.Settings;

/**
 * @author Dylan Cope
 */
public class ProtozoaRadiusGene extends BoundedFloatGene implements Serializable {
  public ProtozoaRadiusGene() {
    super(Settings.minProtozoanBirthRadius, Settings.maxProtozoanBirthRadius);
  }

  public ProtozoaRadiusGene(Float value) {
    super(Settings.minProtozoanBirthRadius, Settings.maxProtozoanBirthRadius, value);
  }

  @Override
  public <G extends Gene<Float>> G createNew(Float value) {
    return (G) new ProtozoaRadiusGene(value);
  }

  @Override
  public String getTraitName() {
    return "Radius";
  }
}
