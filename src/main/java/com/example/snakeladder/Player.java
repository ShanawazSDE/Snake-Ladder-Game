package com.example.snakeladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.util.Pair;

public class Player {
    private Circle posPointer;
    private String playerName;
    int currPosition;
    GameBoard gameBoard = new GameBoard();
    SnakeLadder snakeLadder = new SnakeLadder();
    public Player(int tileSize, Color color,String playerName){
        posPointer = new Circle((double)tileSize/4,color);
        this.playerName = playerName;
        initialisePosition();
    }

    public void initialisePosition() {
        currPosition = 0;
        movePlayer(currPosition);
    }

    public Circle getPosPointer(){
        return this.posPointer;
    }
    public void movePlayer(int diceValue){
        if(currPosition + diceValue <= 100){
            currPosition += diceValue;
            Pair<Integer,Integer> positions = gameBoard.getXY(currPosition);
            TranslateTransition animation = new TranslateTransition(Duration.millis(1500),posPointer);
            animation.setToX(positions.getKey());
            animation.setToY(positions.getValue());
            animation.setAutoReverse(false);
            animation.play();
            int key = currPosition;
            if(gameBoard.snakeLadderMap.containsKey(key)){
                currPosition = gameBoard.snakeLadderMap.get(key);
                movePlayer(0);
            }

        }
    }


}
