/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.core;

import java.awt.*;
import java.io.Serializable;
import org.pdfclown.jada.examples.uml.protoevo.utils.Vector2;

/**
 * @author Dylan Cope
 */
public abstract class Collidable {
  public static class Collision implements Serializable {
    public static final long serialVersionUID = 1L;

    public final Vector2 point = new Vector2(0, 0);
    public boolean collided;
  }

  public abstract Vector2[] getBoundingBox();

  public abstract Color getColor();

  public abstract boolean handlePotentialCollision(Collidable other, float delta);

  public abstract boolean pointInside(Vector2 p);

  public abstract void rayCollisions(Vector2 start, Vector2 end, Collision[] collisions);

  public abstract boolean rayIntersects(Vector2 start, Vector2 end);

}
