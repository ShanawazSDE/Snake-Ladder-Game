package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public final int tileSize = 40;
    int height = 10;
    int width = 10;
    int diceValue;
    Group tileGroup = new Group();
    Player playerOne, playerTwo;
    boolean gameStarted = false;


    Label label = new Label();
    Button startButton;
    Button playerOneButton;
    Button playerTwoButton;
   public Pane createContent(){
        VBox root = new VBox(10);

        root.setAlignment(Pos.CENTER);
        //root.setPrefSize(tileSize * width, tileSize * height+120);

        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile(tileSize,tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                tileGroup.getChildren().addAll(tile);
            }
        }

       Image img = new Image("board.jpg");
       ImageView boardImage = new ImageView();
       boardImage.setImage(img);
       boardImage.setFitHeight(height * tileSize);
       boardImage.setFitWidth(width*tileSize);
       playerOne = new Player(tileSize, Color.BLACK,"playerOne");
       playerTwo = new Player(tileSize, Color.BLUE,"playerTwo");
       tileGroup.getChildren().addAll(boardImage,playerOne.getPosPointer(),playerTwo.getPosPointer());



        label.setText("press start to begin");
       //label.setAlignment(Pos.CENTER);



       //root.getChildren().addAll(label);
       HBox hbox = new HBox(20);
       hbox.setAlignment(Pos.CENTER);
       playerOneButton = new Button("playerOne");
       playerTwoButton = new Button("playerTwo");
       playerOneButton.setPrefWidth(75);
       playerTwoButton.setPrefWidth(75);
       playerOneButton.setDisable(true);
       playerTwoButton.setDisable(true);
       playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               if(gameStarted) {
                   diceValue = getDiceValue();
                   label.setText(String.valueOf(diceValue));
                   playerOne.movePlayer(diceValue);
                   if(playerOne.currPosition == 100){
                       playerWon("player one");
                   }
                   playerOneButton.setDisable(true);
                   if(playerOne.currPosition != 100) {
                       playerTwoButton.setDisable(false);
                   }
               }
           }
       });
       playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               if(gameStarted) {
                   diceValue = getDiceValue();
                   label.setText(String.valueOf(diceValue));
                   playerTwo.movePlayer(diceValue);
                   if(playerTwo.currPosition == 100){
                      playerWon("player Two");
                   }

                   playerTwoButton.setDisable(true);
                   if(playerTwo.currPosition != 100){
                       playerOneButton.setDisable(false);
                   }
               }
           }
       });
       startButton = new Button("start");
       startButton.setPrefWidth(75);
       startButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               gameStarted = true;
               label.setText("");
               if(playerOne.currPosition != 0){
                   playerOne.initialisePosition();

               }
               if(playerTwo.currPosition != 0){
                   playerTwo.initialisePosition();
               }
               playerOneButton.setDisable(false);
               playerTwoButton.setDisable(false);
               startButton.setDisable(true);
           }
       });
      hbox.getChildren().addAll(playerOneButton,startButton,playerTwoButton);
      // hbox.setPrefHeight(120);

       Region space = new Region();
       space.setPrefHeight(10);
      // root.getChildren().addAll(hbox,space);
       root.getChildren().addAll(tileGroup,label,hbox,space);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
        GameBoard gameBoard = new GameBoard();
        gameBoard.setPositionalCoordinates();
    }
    public int getDiceValue(){
       return (int)(Math.random()*6+1);
    }

    public void playerWon(String playerName){
       label.setText("   "+playerName+" Won \npress start to play again\n ");
       startButton.setDisable(false);
       playerOneButton.setDisable(true);
       playerTwoButton.setDisable(true);
       gameStarted = false;
    }

    public static void main(String[] args) {
        launch();
    }
}