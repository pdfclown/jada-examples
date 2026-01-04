/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.ui.main;

import org.pdfclown.jada.examples.uml.protoevo.ui.Controller;
import org.pdfclown.jada.examples.uml.protoevo.ui.Window;
import org.pdfclown.jada.examples.uml.protoevo.ui.components.Input;

/**
 * @author Dylan Cope
 */
public class MainScreenController implements Controller {
  private final Input input;
  private final MainScreenRenderer renderer;

  public MainScreenController(Window window, MainScreenRenderer renderer) {
    this.renderer = renderer;
    this.input = window.getInput();
  }

  public void update() {
    //		if (input.getKey(KeyEvent.VK_ESCAPE)) {
    //			Application.exit();
    //		}

    //		renderer.setZoom(1 - input.getMouseWheelRotation() / 7.0f);

  }
}
