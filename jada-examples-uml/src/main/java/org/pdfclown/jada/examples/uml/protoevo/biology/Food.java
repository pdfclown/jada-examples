/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.pdfclown.jada.examples.uml.protoevo.core.Settings;

/**
 * @author Dylan Cope
 */
public class Food implements Serializable {
  /**
   * Complex molecules are required for the construction of specialised cell behaviour. The
   * implemented molecules are: (1) <a href="https://en.wikipedia.org/wiki/Retinal">Retinal</a>. (2)
   * <a href="https://en.wikipedia.org/wiki/Neurotransmitter">Neurotransmitter</a>. (3)
   * <a href="https://en.wikipedia.org/wiki/Trichocyst">Toxicyst</a>.
   */
  public enum ComplexMolecule {
    Retinal(.5f),
    Neurotransmitter(1),
    Toxicyst(1);

    private final float productionCost;

    ComplexMolecule(float productionCost) {
      this.productionCost = productionCost;
    }

    /**
     * @return energy required to produce one unit of the complex molecule
     */
    public float getProductionCost() {
      return productionCost;
    }
  }

  public enum Type {
    Plant(Settings.plantEnergyDensity),
    Meat(Settings.meatEnergyDensity);

    public static int numTypes() {
      return values().length;
    }

    private final float energyDensity;

    Type(float energyDensity) {
      this.energyDensity = energyDensity;
    }

    public float getEnergyDensity() {
      return energyDensity;
    }
  }

  private float mass;
  private final Type type;
  private final Map<ComplexMolecule, Float> complexMoleculeMasses;

  public Food(float mass, Type foodType) {
    this(mass, foodType, new HashMap<>(0));
  }

  public Food(float mass, Type foodType, HashMap<ComplexMolecule, Float> complexMoleculeMass) {
    this.mass = mass;
    this.type = foodType;
    this.complexMoleculeMasses = complexMoleculeMass;
  }

  public void addComplexMoleculeMass(ComplexMolecule molecule, float mass) {
    float currentMass = complexMoleculeMasses.getOrDefault(molecule, 0f);
    complexMoleculeMasses.put(molecule, currentMass + mass);
  }

  public void addComplexMolecules(Map<ComplexMolecule, Float> masses) {
    for (ComplexMolecule molecule : masses.keySet())
      addComplexMoleculeMass(molecule, masses.get(molecule));
  }

  public void addSimpleMass(float m) {
    mass += m;
  }

  public float getComplexMoleculeMass(ComplexMolecule molecule) {
    return complexMoleculeMasses.getOrDefault(molecule, 0f);
  }

  public Map<ComplexMolecule, Float> getComplexMoleculeMasses() {
    return complexMoleculeMasses;
  }

  public Collection<ComplexMolecule> getComplexMolecules() {
    return complexMoleculeMasses.keySet();
  }

  public float getEnergy(float m) {
    return type.getEnergyDensity() * m;
  }

  public float getSimpleMass() {
    return mass;
  }

  public Type getType() {
    return type;
  }

  public void subtractComplexMolecule(ComplexMolecule molecule, float extracted) {
    float currentAmount = getComplexMoleculeMass(molecule);
    complexMoleculeMasses.put(molecule, Math.max(0, currentAmount - extracted));
  }

  public void subtractSimpleMass(float m) {
    mass = Math.max(0, mass - m);
  }

  @Override
  public String toString() {
    return type.name();
  }
}
