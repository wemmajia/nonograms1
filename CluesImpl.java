package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues {

  private final int height;
  private final int width;
  private final int[][] rowClues;
  private final int[][] colClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    this.height = rowClues.length;
    this.width = colClues.length;
    this.rowClues = rowClues;
    this.colClues = colClues;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int[] getRowClues(int index) {
    return rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    return colClues[index];
  }

  @Override
  public int getRowCluesLength() {
    int length = 0;
    for (int i = 0; i < rowClues.length; i++) {
      if (rowClues[i].length > length) {
        length = rowClues[i].length;
      }
    }
    return length;
  }

  @Override
  public int getColCluesLength() {
    int length = 0;
    for (int i = 0; i < colClues.length; i++) {
      if (colClues[i].length > length) {
        length = colClues[i].length;
      }
    }
    return length;
  }
}
