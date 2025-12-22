/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.ui.simulation;

import java.awt.event.KeyEvent;
import org.pdfclown.jada.examples.uml.protoevo.biology.Cell;
import org.pdfclown.jada.examples.uml.protoevo.core.Chunk;
import org.pdfclown.jada.examples.uml.protoevo.core.Settings;
import org.pdfclown.jada.examples.uml.protoevo.core.Simulation;
import org.pdfclown.jada.examples.uml.protoevo.env.Tank;
import org.pdfclown.jada.examples.uml.protoevo.ui.Controller;
import org.pdfclown.jada.examples.uml.protoevo.ui.Window;
import org.pdfclown.jada.examples.uml.protoevo.ui.components.Input;
import org.pdfclown.jada.examples.uml.protoevo.utils.Vector2;

/**
 * @author Dylan Cope
 */
public class SimulationController implements Controller {
  private final Input input;
  private final Simulation simulation;
  private final SimulationRenderer renderer;

  public SimulationController(Window window, Simulation simulation, SimulationRenderer renderer) {
    this.simulation = simulation;
    this.renderer = renderer;
    this.input = window.getInput();

    input.registerOnPressHandler(KeyEvent.VK_F1, simulation::togglePause);
    input.registerOnPressHandler(KeyEvent.VK_F2, renderer.getUI()::toggleShowFPS);
    input.registerOnPressHandler(KeyEvent.VK_F3, simulation::toggleDebug);
    input.registerOnPressHandler(KeyEvent.VK_F4, renderer::toggleAdvancedDebugInfo);

    input.registerOnPressHandler(KeyEvent.VK_F9, this::resetCamera);
    input.registerOnPressHandler(KeyEvent.VK_F10, renderer::toggleChemicalGrid);
    input.registerOnPressHandler(KeyEvent.VK_F11, renderer::toggleAA);
    input.registerOnPressHandler(KeyEvent.VK_F12, renderer::toggleUI);
  }

  public Vector2 getCurrentMousePosition() {
    return input.getMousePosition();
  }

  public void handleLeftMouseClick() {
    Vector2 pos = input.getMousePosition();
    boolean track = false;
    synchronized (simulation.getTank()) {
      for (Chunk chunk : simulation.getTank().getChunkManager().getChunks()) {
        if (isPosInChunk(pos, chunk)) {
          for (Cell e : chunk.getCells()) {
            Vector2 s = renderer.toRenderSpace(e.getPos());
            double r = renderer.toRenderSpace(e.getRadius());
            if (s.sub(pos).len2() < r * r) {
              renderer.track(e);
              track = true;
              break;
            }
          }
        }
      }
    }
    if (!track)
      renderer.track(null);
  }

  public void handleRightMouseClick() {
    Vector2 pos = input.getMousePosition();
    Tank tank = simulation.getTank();
    int r = renderer.toRenderSpace(tank.getRadius() / 25f);
    synchronized (simulation.getTank()) {
      for (Chunk chunk : simulation.getTank().getChunkManager().getChunks()) {
        for (Cell cell : chunk.getCells()) {
          Vector2 cellPos = renderer.toRenderSpace(cell.getPos());
          Vector2 dir = cellPos.sub(pos);
          float dist = dir.len2();
          int i = 0;
          while (dist < r * r && i < 8) {
            float p = (r * r) / dist;
            float strength = 1 / 100f;
            dir.setLength(strength * p * tank.getRadius() / 8);
            cell.physicsStep(Settings.simulationUpdateDelta);
            cell.getPos().translate(dir);
            dist = cellPos.sub(pos).len2();
            i++;
          }
        }
      }
    }
  }

  public void resetCamera() {
    renderer.resetCamera();
    input.reset();
  }

  public void update() {
    //		if (input.getKey(KeyEvent.VK_ESCAPE)) {
    //			simulation.close();
    //			Application.exit();
    //		}

    renderer.setZoom(1 - input.getMouseWheelRotation() / 7.0f);

    if (input.isLeftMouseJustPressed())
      handleLeftMouseClick();
    if (input.isRightMousePressed())
      handleRightMouseClick();

    renderer.setPan(input.getMouseLeftClickDelta());

  }

  private boolean isCircleInChunk(Vector2 pos, int r, Chunk chunk) {
    return isPosInChunk(new Vector2(pos.getX() - r, pos.getY() - r), chunk) ||
        isPosInChunk(new Vector2(pos.getX() - r, pos.getY() + r), chunk) ||
        isPosInChunk(new Vector2(pos.getX() + r, pos.getY() - r), chunk) ||
        isPosInChunk(new Vector2(pos.getX() + r, pos.getY() + r), chunk);
  }

  private boolean isPosInChunk(Vector2 pos, Chunk chunk) {
    Vector2 chunkCoords = renderer.toRenderSpace(chunk.getTankCoords());
    int originX = (int) chunkCoords.getX();
    int originY = (int) chunkCoords.getY();
    int chunkSize = renderer.toRenderSpace(simulation.getTank().getChunkManager().getChunkSize());
    return originX <= pos.getX() && pos.getX() < originX + chunkSize
        && originY <= pos.getY() && pos.getY() < originY + chunkSize;
  }
}
