package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class View implements FXComponent {

  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {

    VBox layout = new VBox();

    Label label = new Label("Number of Puzzles in Puzzle Library: " + controller.getPuzzleCount());
    Label label2 = new Label("Current Puzzle: " + (controller.getPuzzleIndex() + 1));

    label.setFont(Font.font("Menlo", 15));
    label2.setFont(Font.font("Menlo", 15));

    layout.getChildren().add(label);
    layout.getChildren().add(label2);

    MessageView messageView = new MessageView(controller);
    layout.getChildren().add(messageView.render());

    PuzzleView puzzleView = new PuzzleView(controller);
    layout.getChildren().add(puzzleView.render());

    ControlView controlView = new ControlView(controller);
    layout.getChildren().add(controlView.render());

    return layout;
  }
}
