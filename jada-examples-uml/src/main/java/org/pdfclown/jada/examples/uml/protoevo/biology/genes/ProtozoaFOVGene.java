/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology.genes;

import java.io.Serializable;

/**
 * @author Dylan Cope
 */
public class ProtozoaFOVGene extends BoundedFloatGene implements Serializable {
  public ProtozoaFOVGene() {
    super((float) Math.toRadians(20), (float) Math.toRadians(300));
  }

  public ProtozoaFOVGene(Float value) {
    super((float) Math.toRadians(20), (float) Math.toRadians(300), value);
  }

  @Override
  public BoundedFloatGene createNew(Float value) {
    return new ProtozoaFOVGene(value);
  }

  @Override
  public String getTraitName() {
    return "Retina FoV";
  }
}
