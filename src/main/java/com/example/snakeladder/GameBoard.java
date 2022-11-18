package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {
    int tileSize = 40;
    int height = 10;
    int width = 10;
    ArrayList<Pair<Integer,Integer>> positionalCoordinates;
    HashMap<Integer,Integer> snakeLadderMap;
    public GameBoard(){
        setPositionalCoordinates();
        setSnakeLadderMap();
    }

    private void setSnakeLadderMap() {
        snakeLadderMap = new HashMap<>();
        snakeLadderMap.put(1,38);
        snakeLadderMap.put(4,14);
        snakeLadderMap.put(9,31);
        snakeLadderMap.put(17,7);
        snakeLadderMap.put(21,42);
        snakeLadderMap.put(28,84);
        snakeLadderMap.put(51,67);
        snakeLadderMap.put(54,34);
        snakeLadderMap.put(62,19);
        snakeLadderMap.put(64,60);
        snakeLadderMap.put(72,91);
        snakeLadderMap.put(80,99);
        snakeLadderMap.put(87,36);
        snakeLadderMap.put(93,73);
        snakeLadderMap.put(95,75);
        snakeLadderMap.put(98,79);

    }


    public void setPositionalCoordinates(){
        positionalCoordinates = new ArrayList<>();
        positionalCoordinates.add(new Pair<>(tileSize/2,height * tileSize + 20));
        int tempRow,tempCol,xPos,yPos;

        for (int i = 1; i <=100 ; i++) {
            tempRow = i / height;
            tempCol = i % width;

            tempRow = (tempCol > 0)? (height - ++tempRow): (height - tempRow);

            if(tempCol == 0){
                tempCol = (tempRow & 1) == 0 ? tempCol : width - 1;
            }else {
                tempCol = ((tempRow & 1) == 0) ? width - tempCol : --tempCol;
            }
            xPos = tempCol * tileSize + (tileSize/2);
            yPos = tempRow *tileSize + (tileSize/2);

            positionalCoordinates.add(new Pair<>(xPos,yPos));

        }
//                for(int i = 1; i <= 100; i++){
////            Pair pair = positionalCoordinates.get(i);
////            System.out.println(i+" "+pair.getKey()+" "+pair.getValue());
////        }
    }

    public Pair<Integer,Integer> getXY(int position){
        return positionalCoordinates.get(position);
    }

}
