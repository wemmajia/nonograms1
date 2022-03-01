package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MessageView implements FXComponent {

  private final Controller controller;

  public MessageView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Label label = new Label();
    label.setFont(Font.font("Menlo", 18));
    label.setTextFill(Paint.valueOf("blue"));
    label.setPrefHeight(50);
    label.setPrefWidth(600);
    if (controller.isSolved()) {
      label.setText("Puzzle is solved!");
    } else {
      label.setText("  ");
    }
    label.setTextAlignment(TextAlignment.CENTER);

    return label;
  }
}
