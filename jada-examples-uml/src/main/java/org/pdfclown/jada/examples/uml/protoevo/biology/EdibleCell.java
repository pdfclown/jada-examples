/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.biology;

import org.pdfclown.jada.examples.uml.protoevo.env.Tank;

/**
 * @author Dylan Cope
 */
public abstract class EdibleCell extends Cell {
  private static final long serialVersionUID = -5482090072120647315L;
  private final Food.Type foodType;

  public EdibleCell(float radius, Food.Type foodType, Tank tank) {
    super(tank);
    this.setRadius(radius);
    this.foodType = foodType;
  }

  public Food.Type getFoodType() {
    return foodType;
  }

  @Override
  public boolean isEdible() {
    return true;
  }

}
