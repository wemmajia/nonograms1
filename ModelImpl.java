package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private final List<Puzzle> puzzles;
  private int active;
  private final List<ModelObserver> modelObservers;

  public ModelImpl(List<Clues> clues) {
    this.puzzles = new ArrayList<>();
    for (Clues clue : clues) {
      this.puzzles.add(new Puzzle(clue));
    }
    this.active = 0;
    this.modelObservers = new ArrayList<>();
  }

  private void notifyObservers() {
    for (ModelObserver model : modelObservers) {
      model.update(this);
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    return puzzles.get(active).getBoard().isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return puzzles.get(active).getBoard().isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return puzzles.get(active).getBoard().isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    puzzles.get(active).getBoard().toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    puzzles.get(active).getBoard().toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    puzzles.get(active).getBoard().clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return puzzles.get(active).getClues().getWidth();
  }

  @Override
  public int getHeight() {
    return puzzles.get(active).getClues().getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return puzzles.get(active).getClues().getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return puzzles.get(active).getClues().getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return puzzles.get(active).getClues().getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return puzzles.get(active).getClues().getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return puzzles.size();
  }

  @Override
  public int getPuzzleIndex() {
    return active;
  }

  @Override
  public void setPuzzleIndex(int index) {
    active = index;
    notifyObservers();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    modelObservers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    modelObservers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    return isColSolved() && isRowSolved() && isRowSolvedSpacing() && isColSolvedSpacing();
  }

  private boolean isColSolved() {
    for (int i = 0; i < getWidth(); i++) {
      int totalShaded = 0;
      for (int j = 0; j < getColCluesLength(); j++) {
        totalShaded += getColClues(i)[j];
      }
      int count = 0;
      for (int k = 0; k < getHeight(); k++) {
        if (isShaded(k, i)) {
          count++;
        }
      }
      if (totalShaded != count) {
        return false;
      }
    }
    return true;
  }

  private boolean isRowSolved() {
    for (int i = 0; i < getHeight(); i++) {
      int totalShaded = 0;
      for (int j = 0; j < getRowCluesLength(); j++) {
        totalShaded += getRowClues(i)[j];
      }
      int count = 0;
      for (int k = 0; k < getWidth(); k++) {
        if (isShaded(i, k)) {
          count++;
        }
      }
      if (totalShaded != count) {
        return false;
      }
    }
    return true;
  }

  private boolean isRowSolvedSpacing() {
    for (int i = 0; i < getHeight(); i++) {
      int clueIndex = 0;

      int count = 0;
      for (int j = 0; j < getWidth(); j++) {
        if (isShaded(i, j)) {
          count++;
          if (j == getWidth() - 1) {
            while (clueIndex < getRowCluesLength()) {
              if (getRowClues(i)[clueIndex] == 0) {
                clueIndex++;
              } else {
                break;
              }
            }
            if (count != getRowClues(i)[clueIndex]) {
              return false;
            }
          }
        } else {
          if (count > 0) {
            while (clueIndex < getRowCluesLength()) {
              if (getRowClues(i)[clueIndex] == 0) {
                clueIndex++;
              } else {
                break;
              }
            }
            if (count != getRowClues(i)[clueIndex]) {
              return false;
            }
            clueIndex++;
            count = 0;
          }
        }
      }
    }
    return true;
  }

  private boolean isColSolvedSpacing() {
    for (int i = 0; i < getWidth(); i++) {
      int clueIndex = 0;

      int count = 0;
      for (int j = 0; j < getHeight(); j++) {
        if (isShaded(j, i)) {
          count++;
          if (j == getHeight() - 1) {
            while (clueIndex < getColCluesLength()) {
              if (getColClues(i)[clueIndex] == 0) {
                clueIndex++;
              } else {
                break;
              }
            }
            if (count != getColClues(i)[clueIndex]) {
              return false;
            }
          }
        } else {
          if (count > 0) {
            while (clueIndex < getColCluesLength()) {
              if (getColClues(i)[clueIndex] == 0) {
                clueIndex++;
              } else {
                break;
              }
            }
            if (count != getColClues(i)[clueIndex]) {
              return false;
            }
            clueIndex++;
            count = 0;
          }
        }
      }
    }
    return true;
  }
}
