/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.ui.components;

import java.awt.*;
import org.pdfclown.jada.examples.uml.protoevo.utils.Vector2;

/**
 * @author Dylan Cope
 */
public interface UIComponent {
  enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

  default int[] getBounds() {
    return new int[] { getX(), getY(), getWidth(), getHeight() };
  }

  int getHeight();

  Vector2 getPosition();

  int getWidth();

  default int getX() {
    return (int) getPosition().getX();
  }

  default int getY() {
    return (int) getPosition().getY();
  }

  default boolean inBounds(int testX, int testY) {
    int x = getX();
    int y = getY();
    int w = getWidth();
    int h = getHeight();
    return x <= testX && testX <= x + w && y <= testY && testY <= y + h;
  }

  default <T extends UIComponent> T placeNextTo(UIComponent other, Direction dir) {
    return placeNextTo(other, dir, 0);
  }

  default <T extends UIComponent> T placeNextTo(UIComponent other, Direction dir, int buffer) {
    if (dir.equals(Direction.UP))
      this.setPosition(other.getPosition().add(new Vector2(0, -getHeight() - buffer)));
    else if (dir.equals(Direction.LEFT))
      this.setPosition(other.getPosition().add(new Vector2(-getWidth() - buffer, 0)));
    else if (dir.equals(Direction.RIGHT))
      this.setPosition(other.getPosition().add(new Vector2(other.getWidth() + buffer, 0)));
    else
      this.setPosition(other.getPosition().add(new Vector2(0, other.getHeight() + buffer)));
    return (T) this;
  }

  void render(Graphics2D g);

  void setPosition(Vector2 pos);
}
