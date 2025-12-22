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
public class ProtozoaSplitRadiusGene extends BoundedFloatGene implements Serializable {
  public ProtozoaSplitRadiusGene() {
    super(Settings.minProtozoanSplitRadius, Settings.maxProtozoanSplitRadius);
  }

  public ProtozoaSplitRadiusGene(Float value) {
    super(Settings.minProtozoanSplitRadius, Settings.maxProtozoanSplitRadius, value);
  }

  @Override
  public BoundedFloatGene createNew(Float value) {
    return new ProtozoaSplitRadiusGene(value);
  }

  @Override
  public String getTraitName() {
    return "Split Radius";
  }
}
