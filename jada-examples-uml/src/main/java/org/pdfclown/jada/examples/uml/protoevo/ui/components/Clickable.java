/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.ui.components;

/**
 * @author Dylan Cope
 */
public interface Clickable extends UIComponent {
  void activate();

  void deactivate();

  boolean isActive();

  default boolean isInClickRegion(int mouseX, int mouseY) {
    return inBounds(mouseX, mouseY);
  }

  void onClick();
}
