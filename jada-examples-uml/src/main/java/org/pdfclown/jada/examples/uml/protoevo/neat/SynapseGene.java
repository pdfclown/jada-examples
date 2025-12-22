/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.neat;

import java.io.Serializable;
import java.util.Objects;
import org.pdfclown.jada.examples.uml.protoevo.core.Simulation;

/**
 * @author Dylan Cope
 */
public class SynapseGene implements Comparable<SynapseGene>, Serializable {
  private static int globalInnovation = 0;

  public static float randomInitialWeight() {
    return (float) (2 * Simulation.RANDOM.nextDouble() - 1);
  }

  private final int innovation;
  private NeuronGene in, out;
  private float weight;

  private boolean disabled;

  public SynapseGene(NeuronGene in, NeuronGene out) {
    this(in, out, randomInitialWeight(), globalInnovation++);
  }

  public SynapseGene(NeuronGene in, NeuronGene out, float weight) {
    this(in, out, weight, globalInnovation++);
  }

  public SynapseGene(NeuronGene in, NeuronGene out, float weight, int innovation) {
    this.in = in;
    this.out = out;
    disabled = false;
    this.weight = weight;
    this.innovation = innovation;
  }

  @Override
  public int compareTo(SynapseGene g) {
    return innovation - g.innovation;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof SynapseGene) {
      SynapseGene otherSynGene = ((SynapseGene) o);
      NeuronGene otherIn = otherSynGene.in;
      NeuronGene otherOut = otherSynGene.out;
      return in.equals(otherIn)
          && out.equals(otherOut);
    }
    return false;
  }

  public NeuronGene getIn() {
    return in;
  }

  public int getInnovation() {
    return innovation;
  }

  public NeuronGene getOut() {
    return out;
  }

  public float getWeight() {
    return weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(in.getId(), out.getId());
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public void setIn(NeuronGene in) {
    this.in = in;
  }

  public void setOut(NeuronGene out) {
    this.out = out;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
    return String.format(
        "Synapse: innov=%d; in=%d; out=%d; w=%.5f; disabled=%b",
        innovation, in.getId(), out.getId(), weight, disabled);
  }
}
