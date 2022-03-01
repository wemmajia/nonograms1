package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PuzzleView implements FXComponent {
  private final Controller controller;

  public PuzzleView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane board = new GridPane();
    int width = controller.getClues().getWidth();
    int height = controller.getClues().getHeight();

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Button b = new Button();
        b.setPrefSize(30, 30);
        int finalJ = j;
        int finalI = i;

        if (controller.isShaded(finalJ, finalI)) {
          b.setStyle("-fx-background-color: black; -fx-border-width: 1px; -fx-border-color: grey;");
        } else if (controller.isEliminated(finalJ, finalI)) {
          b.setStyle("-fx-background-color: white; -fx-border-width: 1px; -fx-border-color: grey;");
          b.setText("X");
        } else {
          b.setStyle("-fx-background-color: white; -fx-border-width: 1px; -fx-border-color: grey;");
        }
        b.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                  controller.toggleShaded(finalJ, finalI);
                } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                  controller.toggleEliminated(finalJ, finalI);
                }
              }
            });
        board.add(b, i, j);
      }
    }

    GridPane rowClues = new GridPane();
    rowClues.setAlignment(Pos.CENTER);
    rowClues.setVgap(13);
    rowClues.setHgap(10);
    int w = controller.getClues().getRowCluesLength();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < w; j++) {
        int clue = controller.getClues().getRowClues(i)[j];
        Label tile;
        if (clue == 0) {
          tile = new Label(" ");
        } else {
          tile = new Label(String.valueOf(clue));
        }
        rowClues.add(tile, j, i);
      }
    }

    GridPane colClues = new GridPane();
    colClues.setAlignment(Pos.CENTER);
    colClues.setHgap(22);
    colClues.setVgap(10);
    int h = controller.getClues().getColCluesLength();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < h; j++) {
        int clue = controller.getClues().getColClues(i)[j];
        Label tile;
        if (clue == 0) {
          tile = new Label(" ");
        } else {
          tile = new Label(String.valueOf(clue));
        }
        colClues.add(tile, i, j);
      }
    }

    GridPane grid = new GridPane();
    grid.setHgap(5);
    grid.setVgap(5);
    grid.add(rowClues, 0, 1);
    grid.add(colClues, 1, 0);
    grid.add(board, 1, 1);

    return grid;
  }
}
