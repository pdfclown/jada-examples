/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.core;

import com.google.common.collect.Iterators;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import org.pdfclown.jada.examples.uml.protoevo.biology.Cell;
import org.pdfclown.jada.examples.uml.protoevo.env.Rock;
import org.pdfclown.jada.examples.uml.protoevo.utils.Vector2;

/**
 * @author Dylan Cope
 */
public class ChunkManager implements Serializable {
  public static final long serialVersionUID = 1L;

  private final float chunkSize;
  private final float xMin;
  private final float yMin;
  private final float xMax;
  private final float yMax;
  private final int nYChunks;
  private final int nXChunks;

  private final Chunk[] chunks;
  private final List<Cell> entities = new ArrayList<>();

  public ChunkManager(float xMin, float xMax,
      float yMin, float yMax,
      float chunkSize) {
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
    this.chunkSize = chunkSize;

    this.nXChunks = 2 + (int) ((xMax - xMin) / chunkSize);
    this.nYChunks = 2 + (int) ((yMax - yMin) / chunkSize);

    this.chunks = new Chunk[nXChunks * nYChunks];
    for (int i = 0; i < nXChunks; i++)
      for (int j = 0; j < nYChunks; j++)
        this.chunks[toChunkID(i, j)] = new Chunk(i, j, this);

  }

  public void add(Cell e) {
    if (e != null)
      entities.add(e);
  }

  public void allocateToChunk(Cell e) {
    Chunk chunk = getChunk(e);
    chunk.addEntity(e);
  }

  public void allocateToChunk(Rock rock) {
    int iMax = Integer.MIN_VALUE;
    int iMin = Integer.MAX_VALUE;
    int jMax = Integer.MIN_VALUE;
    int jMin = Integer.MAX_VALUE;
    for (Vector2 p : rock.getPoints()) {
      Chunk chunk = getChunk(p);
      int i = (int) chunk.getChunkCoords().getX();
      int j = (int) chunk.getChunkCoords().getY();
      iMax = Math.max(i, iMax);
      iMin = Math.min(i, iMin);
      jMax = Math.max(j, jMax);
      jMin = Math.min(j, jMin);
    }
    for (int i = iMin; i <= iMax; i++)
      for (int j = jMin; j <= jMax; j++)
        chunks[toChunkID(i, j)].addRock(rock);
  }

  public Iterator<Collidable> broadCollisionDetection(Vector2 pos, float range) {
    return broadScan(pos, range, Chunk::getCollidables);
  }

  public Iterator<Cell> broadEntityDetection(Vector2 pos, float range) {
    return broadScan(pos, range, chunk -> chunk.getCells().iterator());
  }

  public <T extends Collidable> Iterator<T> broadScan(
      Vector2 pos,
      float range,
      Function<Chunk, Iterator<T>> scanner) {
    float x = pos.getX();
    float y = pos.getY();

    int iMin = this.toChunkX(x - range);
    int iMax = this.toChunkX(x + range);
    int jMin = this.toChunkY(y - range);
    int jMax = this.toChunkY(y + range);

    List<Iterator<T>> iterators = new ArrayList<>((iMax - iMin + 1) * (jMax - jMin + 1));
    for (int i = iMin; i <= iMax; i++) {
      for (int j = jMin; j <= jMax; j++) {
        Chunk chunk = getChunk(toChunkID(i, j));
        iterators.add(scanner.apply(chunk));
      }
    }
    return Iterators.concat(iterators.iterator());
  }

  public Collection<Cell> getAllCells() {
    return entities;
  }

  public Chunk getChunk(Cell e) {
    return getChunk(e.getPos());
  }

  public Chunk getChunk(Vector2 pos) {
    int chunkID = this.toChunkID(pos.getX(), pos.getY());
    return getChunk(chunkID);
  }

  public Chunk getChunk(int chunkID) {
    return this.chunks[chunkID];
  }

  public float getChunkSize() {
    return chunkSize;
  }

  public Chunk[] getChunks() {
    return chunks;
  }

  public int getNXChunks() {
    return nXChunks;
  }

  public int getNYChunks() {
    return nYChunks;
  }

  public float getXMax() {
    return xMax;
  }

  public float getXMin() {
    return xMin;
  }

  public float getYMax() {
    return yMax;
  }

  public float getYMin() {
    return yMin;
  }

  public int toChunkID(float x, float y) {

    int i = (int) (1 + (x - xMin) / chunkSize);
    int j = (int) (1 + (y - yMin) / chunkSize);

    return toChunkID(i, j);
  }

  public int toChunkID(int i, int j) {
    int id = i + j * nYChunks;
    if (id < 0)
      return 0;
    if (id >= nXChunks * nYChunks)
      return nXChunks * nYChunks - 1;
    return id % (nXChunks * nYChunks);
  }

  public int toChunkX(float tankX) {
    int i = (int) (1 + (tankX - xMin) / chunkSize);
    if (i < 0)
      return 0;
    if (i >= nXChunks)
      return nXChunks - 1;
    return i;
  }

  public int toChunkY(float tankY) {
    int j = (int) (1 + (tankY - yMin) / chunkSize);
    if (j < 0)
      return 0;
    if (j >= nYChunks)
      return nYChunks - 1;
    return j;
  }

  public Vector2 toTankCoords(Vector2 chunkCoords) {
    float x = (chunkCoords.getX() - 1) * chunkSize + xMin;
    float y = (chunkCoords.getY() - 1) * chunkSize + yMin;
    return new Vector2(x, y);
  }

  public void update() {
    Arrays.stream(chunks).parallel().forEach(Chunk::clear);
    //        for (Chunk chunk : chunks)
    //            chunk.clear();

    entities.removeIf(Cell::isDead);
    entities.forEach(this::allocateToChunk);
  }
}
