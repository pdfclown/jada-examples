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
public abstract class Gene<T> implements Serializable {
  public static final long serialVersionUID = -1504556284113269258L;
  public static int totalMutations = 0;
  private T value;
  private int numMutations = 0;

  public boolean disabled = false;

  public Gene() {
    value = getNewValue();
  }

  public Gene(T value) {
    this.value = value;
  }

  public abstract boolean canDisable();

  public abstract <G extends Gene<T>> G createNew(T value);

  public <G extends Gene<T>> G createNew(T value, int numMutations) {
    G gene = createNew(value);
    gene.setNumMutations(numMutations);
    totalMutations++;
    return gene;
  }

  public Gene<?> crossover(Gene<?> other) {
    if (Simulation.RANDOM.nextBoolean())
      return this;
    else
      return other;
  }

  public abstract T disabledValue();

  public abstract T getNewValue();

  public int getNumMutations() {
    return numMutations;
  }

  public abstract String getTraitName();

  public T getValue() {
    return value;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public <G extends Gene<T>> G mutate(Gene<?>[] genome) {
    return this.createNew(getNewValue(), numMutations + 1);
  }

  public void setNumMutations(int numMutations) {
    this.numMutations = numMutations;
  }

  public void setValue(T t) {
    value = t;
  }

  @Override
  public String toString() {
    return valueString() + ":" + numMutations + ":" + (disabled ? "0" : "1");
  }

  public Gene<T> toggle() {
    Gene<T> newGene = this.createNew(getValue(), numMutations + 1);
    newGene.disabled = !disabled;
    return newGene;
  }

  public void toggleEnabled() {
    disabled = !disabled;
  }

  public String valueString() {
    return value.toString();
  }
}
