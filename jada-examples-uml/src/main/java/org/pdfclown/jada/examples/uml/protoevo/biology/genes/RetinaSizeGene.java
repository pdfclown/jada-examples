/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology.genes;

import java.io.Serializable;
import java.util.Optional;
import org.pdfclown.jada.examples.uml.protoevo.core.Settings;
import org.pdfclown.jada.examples.uml.protoevo.neat.NetworkGenome;

/**
 * @author Dylan Cope
 */
public class RetinaSizeGene extends Gene<Integer> implements Serializable {
  public static final long serialVersionUID = -4191267363677698742L;

  public RetinaSizeGene() {
    super();
  }

  public RetinaSizeGene(Integer value) {
    super(value);
  }

  @Override
  public boolean canDisable() {
    return true;
  }

  @Override
  public RetinaSizeGene createNew(Integer value) {
    return new RetinaSizeGene(value);
  }

  @Override
  public Integer disabledValue() {
    return 0;
  }

  @Override
  public Integer getNewValue() {
    return Settings.defaultRetinaSize;
  }

  @Override
  public String getTraitName() {
    return "Num Retina Cells";
  }

  @Override
  public <G extends Gene<Integer>> G mutate(Gene<?>[] genes) {
    int size = getValue();
    if (size == Settings.maxRetinaSize)
      return (G) this;

    int newSize = size + 1;
    addNetworkSensors(genes, newSize);
    return createNew(newSize, getNumMutations() + 1);
  }

  private void addNetworkSensors(Gene<?>[] genes, int newRetinaSize) {

    int i = findNetworkGene(genes).orElseThrow(() -> new RuntimeException("No Network Gene found"));
    NetworkGene networkGene = (NetworkGene) genes[i];
    NetworkGenome currentNetworkGenome = networkGene.getValue();
    NetworkGenome newNetworkGenome = new NetworkGenome(currentNetworkGenome);
    newNetworkGenome.ensureRetinaSensorsExist(newRetinaSize);
    genes[i] = networkGene.createNew(newNetworkGenome);

  }

  private Optional<Integer> findNetworkGene(Gene<?>[] genes) {
    for (int i = 0; i < genes.length; i++) {
      Gene<?> gene = genes[i];
      if (gene instanceof NetworkGene)
        return Optional.of(i);
    }
    return Optional.empty();
  }
}