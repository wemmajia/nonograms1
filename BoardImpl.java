package com.comp301.a09nonograms.model;

public class BoardImpl implements Board {

  private final int[][] board;
  private final int height;
  private final int width;

  public BoardImpl(Clues clues) {
    this.board = new int[clues.getHeight()][clues.getWidth()];
    this.height = clues.getHeight();
    this.width = clues.getWidth();
  }

  // 0=unshaded, 1=shaded, 2=eliminated

  @Override
  public boolean isShaded(int row, int col) {
    if (!(row < height && row >= 0 && col < width && col >= 0)) {
      throw new RuntimeException();
    }
    return board[row][col] == 1;
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (!(row < height && row >= 0 && col < width && col >= 0)) {
      throw new RuntimeException();
    }
    return board[row][col] == 2;
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (!(row < height && row >= 0 && col < width && col >= 0)) {
      throw new RuntimeException();
    }
    return !isShaded(row, col) && !isEliminated(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (!(row < height && row >= 0 && col < width && col >= 0)) {
      throw new RuntimeException();
    } else {
      if (isShaded(row, col)) {
        board[row][col] = 0;
      } else {
        board[row][col] = 1;
      }
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (!(row < height && row >= 0 && col < width && col >= 0)) {
      throw new RuntimeException();
    }
    if (isEliminated(row, col)) {
      board[row][col] = 0;
    } else {
      board[row][col] = 2;
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = 0;
      }
    }
  }
}
