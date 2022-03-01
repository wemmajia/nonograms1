package com.comp301.a09nonograms.model;

public class Puzzle {

  private final Board board;
  private final Clues clues;

  public Puzzle(Clues clues) {
    this.board = new BoardImpl(clues);
    this.clues = clues;
  }

  public Board getBoard() {
    return board;
  }

  public Clues getClues() {
    return clues;
  }
}
