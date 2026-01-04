/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology;

import java.awt.*;
import org.pdfclown.jada.examples.uml.protoevo.core.Simulation;
import org.pdfclown.jada.examples.uml.protoevo.env.Tank;

/**
 * @author Dylan Cope
 */
public class MeatCell extends EdibleCell {
  public static final long serialVersionUID = -5549426815144079228L;

  private float rotteness = 0.0f;

  public MeatCell(float radius, Tank tank) {
    super(radius, Food.Type.Meat, tank);

    int r = 150 + Simulation.RANDOM.nextInt(105);
    int g = 25 + Simulation.RANDOM.nextInt(100);
    int b = 25 + Simulation.RANDOM.nextInt(100);
    setHealthyColour(new Color(r, g, b));
    setDegradedColour(new Color(158, 121, 79));
  }

  public void age(float delta) {
    float deathRate = getRadius() * delta * 100;
    setHealth(getHealth() * (1 - deathRate));
    rotteness = rotteness * (1 - deathRate);
    if (rotteness > 1)
      rotteness = 1;
  }

  @Override
  public boolean cannotMakeBinding() {
    return true;
  }

  @Override
  public String getPrettyName() {
    return "Meat";
  }

  @Override
  public void update(float delta) {
    age(delta);
    super.update(delta);
  }
}
