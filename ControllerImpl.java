package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;
import com.comp301.a09nonograms.model.Model;

public class ControllerImpl implements Controller {

  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public Clues getClues() {
    int[][] row = new int[model.getHeight()][model.getRowCluesLength()];
    int[][] col = new int[model.getWidth()][model.getColCluesLength()];

    for (int i = 0; i < row.length; i++) {
      row[i] = model.getRowClues(i);
    }

    for (int j = 0; j < col.length; j++) {
      col[j] = model.getColClues(j);
    }

    return new CluesImpl(row, col);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    int index = model.getPuzzleIndex() + 1;
    if (index >= model.getPuzzleCount()) {
      model.setPuzzleIndex(0);
    } else {
      model.setPuzzleIndex(index);
    }
  }

  @Override
  public void prevPuzzle() {
    int index = model.getPuzzleIndex() - 1;
    if (index < 0) {
      model.setPuzzleIndex(model.getPuzzleCount() - 1);
    } else {
      model.setPuzzleIndex(index);
    }
  }

  @Override
  public void randPuzzle() {
    int random;
    do {
      random = (int) (Math.random() * model.getPuzzleCount());
    } while (random == model.getPuzzleIndex());

    model.setPuzzleIndex(random);
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
