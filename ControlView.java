package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private final Controller controller;

  public ControlView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Button previous = new Button("Previous Puzzle");
    previous.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
        });

    Button next = new Button("Next Puzzle");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        });

    Button random = new Button("Random Puzzle");
    random.setOnAction(
        (ActionEvent event) -> {
          controller.randPuzzle();
        });

    Button clear = new Button("Clear Board");
    clear.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
        });

    HBox up = new HBox();
    up.getChildren().add(previous);
    up.getChildren().add(next);
    up.setSpacing(10);

    VBox full = new VBox();
    full.setSpacing(10);
    full.getChildren().add(clear);
    full.getChildren().add(up);
    full.getChildren().add(random);

    full.setPadding(new Insets(10, 10, 0, 0));
    return full;
  }
}
