/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.swing.*;
import org.pdfclown.jada.examples.uml.protoevo.ui.components.Input;
import org.pdfclown.jada.examples.uml.protoevo.utils.Vector2;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * @author Dylan Cope
 */
public class Window extends Canvas implements Runnable, ActionListener {
  public static class WindowConfig {

    public static String configPath = "config/window_config.yaml";
    public static WindowConfig instance = null;

    public static WindowConfig getConfig() {
      if (instance == null) {
        instance = loadConfigYAML();
      }
      return instance;
    }

    public static WindowConfig loadConfigYAML() {
      InputStream inputStream;
      try {
        inputStream = new FileInputStream(configPath);
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
      Yaml yaml = new Yaml(new Constructor(WindowConfig.class, new LoaderOptions()));
      return yaml.load(inputStream);
    }

    public int window_width;

    public int window_height;

    public boolean fullscreen;
  }

  private static final long serialVersionUID = -2111860594941368902L;
  private final JFrame frame;
  private Input input;
  private Canvas renderer;
  private Controller controller;

  private final int width, height;

  private final Timer timer = new Timer(1000 / 60, this);

  public Window(JFrame frame, Canvas renderer, Controller controller) {
    Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    width = (int) d.getWidth();
    height = (int) d.getHeight();
    this.frame = frame;

    input = new Input();

    this.renderer = renderer;
    this.controller = controller;

    renderer.addKeyListener(input);
    renderer.addMouseListener(input);
    renderer.addMouseMotionListener(input);
    renderer.addMouseWheelListener(input);
    renderer.addFocusListener(input);
  }

  public Window(String title) {
    WindowConfig config = WindowConfig.getConfig();

    if (config.fullscreen) {
      Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
      width = (int) d.getWidth();
      height = (int) d.getHeight();
    } else {
      width = config.window_width;
      height = config.window_height;
    }
    input = new Input();

    frame = new JFrame(title);
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));

    if (config.fullscreen) {
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
    }
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (frame.isVisible()) {
      input.update();
      controller.update();

      BufferStrategy bs = this.getBufferStrategy();
      renderer.paint(bs.getDrawGraphics());
      bs.show();

      timer.restart();
    }
  }

  @Override
  public BufferStrategy getBufferStrategy() {
    BufferStrategy bs = renderer.getBufferStrategy();

    if (bs == null) {
      renderer.createBufferStrategy(3);
      bs = renderer.getBufferStrategy();
    }

    return bs;
  }

  public Vector2 getCurrentMousePosition() {
    return input.getMousePosition();
  }

  public Vector2 getDimensions() {
    return new Vector2(width, height);
  }

  public JFrame getFrame() {
    return frame;
  }

  public int getHeight() {
    return height;
  }

  public Input getInput() {
    return input;
  }

  public int getWidth() {
    return width;
  }

  public void reset() {
    input = new Input();
  }

  @Override
  public void run() {
    timer.start();
  }

  public void set(Canvas renderer, Controller controller) {
    if (this.renderer != null)
      frame.remove(this.renderer);

    this.renderer = renderer;
    this.controller = controller;

    renderer.addKeyListener(input);
    renderer.addMouseListener(input);
    renderer.addMouseMotionListener(input);
    renderer.addMouseWheelListener(input);
    renderer.addFocusListener(input);
    frame.add(renderer);
    frame.pack();
  }
}