/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology;

import java.io.Serializable;
import org.pdfclown.jada.examples.uml.protoevo.core.Settings;
import org.pdfclown.jada.examples.uml.protoevo.core.Simulation;

/**
 * @author Dylan Cope
 */
public interface Brain extends Serializable {
  Brain RANDOM = new Brain() {
    private static final long serialVersionUID = 1648484737904226314L;

    @Override
    public float attack(Protozoan p) {
      return Simulation.RANDOM.nextFloat();
    }

    @Override
    public float energyConsumption() {
      return 0;
    }

    @Override
    public float speed(Protozoan p) {
      return (float) (Simulation.RANDOM.nextDouble() * Settings.maxProtozoaSpeed);
    }

    @Override
    public void tick(Protozoan p) {
    }

    @Override
    public float turn(Protozoan p) {
      float x = (float) (2 * Simulation.RANDOM.nextDouble() - 1);
      float t = (float) Math.toRadians(35);
      return t * x;
    }

    @Override
    public boolean wantToMateWith(Protozoan p) {
      return false;
    }

  };
  Brain EMPTY = new Brain() {
    @Override
    public float attack(Protozoan p) {
      return 0;
    }

    @Override
    public float energyConsumption() {
      return 0;
    }

    @Override
    public float speed(Protozoan p) {
      return 0;
    }

    @Override
    public void tick(Protozoan p) {
    }

    @Override
    public float turn(Protozoan p) {
      return 0;
    }

    @Override
    public boolean wantToMateWith(Protozoan p) {
      return false;
    }
  };

  float attack(Protozoan p);

  float energyConsumption();

  float speed(Protozoan p);

  void tick(Protozoan p);

  float turn(Protozoan p);

  boolean wantToMateWith(Protozoan p);
}
