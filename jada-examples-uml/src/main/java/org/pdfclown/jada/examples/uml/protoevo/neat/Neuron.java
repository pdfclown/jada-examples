/*
  SPDX-FileCopyrightText: 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.neat;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by dylan on 26/05/2017.
 *
 * @author Dylan Cope
 */
public class Neuron implements Comparable<Neuron>, Serializable {
  public interface Activation extends Function<Float, Float>, Serializable {
    Activation SIGMOID = z -> 1 / (1 + (float) Math.exp(-z));
    Activation LINEAR = z -> z;
    Activation TANH = x -> (float) Math.tanh(x);
  }

  public enum Type implements Serializable {
    SENSOR("SENSOR"),
    HIDDEN("HIDDEN"),
    OUTPUT("OUTPUT");

    private final String value;

    Type(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  private static final long serialVersionUID = 1L;

  private final Neuron[] inputs;
  private final float[] weights;
  private Type type;
  private final int id;
  private float state = 0, lastState = 0, nextState = 0;
  private float learningRate = 0;
  private Activation activation;
  private int depth = -1;
  private int graphicsX = -1, graphicsY = -1;
  private boolean connectedToOutput = true;
  private final String label;

  public Neuron(int id, Neuron[] inputs, float[] weights, Type type, Activation activation,
      String label) {
    this.id = id;
    this.inputs = inputs;
    this.weights = weights;
    this.type = type;
    this.activation = activation;
    this.label = label;

    if (type.equals(Type.OUTPUT))
      connectedToOutput = true;
  }

  @Override
  public int compareTo(Neuron o) {
    return Comparator.comparingInt(Neuron::getId).compare(this, o);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Neuron)
      return ((Neuron) o).getId() == id;
    return false;
  }

  public int getDepth() {
    return depth;
  }

  public int getGraphicsX() {
    return graphicsX;
  }

  public int getGraphicsY() {
    return graphicsY;
  }

  public int getId() {
    return id;
  }

  public Neuron[] getInputs() {
    return inputs;
  }

  public String getLabel() {
    return label;
  }

  public float getLastState() {
    return lastState;
  }

  public float getState() {
    return state;
  }

  public Type getType() {
    return type;
  }

  public float[] getWeights() {
    return weights;
  }

  public boolean isConnectedToOutput() {
    return connectedToOutput;
  }

  public Neuron setActivation(Neuron.Activation activation) {
    this.activation = activation;
    return this;
  }

  public void setConnectedToOutput(boolean connectedToOutput) {
    this.connectedToOutput = connectedToOutput;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public void setGraphicsPosition(int x, int y) {
    graphicsX = x;
    graphicsY = y;
  }

  public Neuron setState(float s) {
    state = s;
    return this;
  }

  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(String.format("id:%d, state:%.1f", id, state));
    s.append(", connections: [");
    for (int i = 0; i < weights.length; i++)
      s.append(String.format("(%d, %.1f)", i, weights[i]));
    s.append("]");
    return s.toString();
  }

  void tick() {
    nextState = 0.0f;
    for (int i = 0; i < inputs.length; i++)
      nextState += inputs[i].getState() * weights[i];
    nextState = activation.apply(nextState);
  }

  void update() {
    lastState = state;
    state = nextState;
  }

  private float getLearningRate() {
    return learningRate;
  }

  private Neuron setLearningRate(float lr) {
    this.learningRate = lr;
    return this;
  }
}
