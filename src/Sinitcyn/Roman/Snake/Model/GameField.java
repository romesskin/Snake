package Sinitcyn.Roman.Snake.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GameField extends Observable {

    private FieldAnimal[][] gameField;
    private int score;

    GameField(){
        gameField =new FieldAnimal[ModelSnake.getSizeMap().x][ModelSnake.getSizeMap().y];
        ClearField();
    }

    //очиска поля игры
    void ClearField(){
        for(int i=0;i<ModelSnake.getSizeMap().x;i++)
            for(int j=0;j<ModelSnake.getSizeMap().y;j++)
                gameField[i][j]=FieldAnimal.NULL;
        ModelChange(gameField);
        score=0;
    }

    Coord addFrog(FieldAnimal fieldAnimal) {
        while (true) {
            Coord coord = new Coord(new Random().nextInt(ModelSnake.getSizeMap().x),
                                    new Random().nextInt(ModelSnake.getSizeMap().y));
            if (gameField[coord.x][coord.y] == FieldAnimal.NULL) {
                setField(coord,fieldAnimal);
                return coord;
            }
            ModelChange(gameField);
        }
    }

    //начальное расположение змеи
    void addSnake(ArrayList<Coord> sn){
        setField(sn.get(0),FieldAnimal.SNAKEHEAD);
        setField(sn.get(sn.size()-1),FieldAnimal.SNAKEBODY);
        for(int i=1;i<sn.size()-1;i++){
            setField(sn.get(i),FieldAnimal.SNAKEBODY);
        }
        ModelChange(gameField);
    }
    void MoveFrog(Coord coord) {

    }

    void MoveSnake(ArrayList<Coord> sn,Coord oldCoord) {
        setField(sn.get(0),FieldAnimal.SNAKEHEAD);
        setField(sn.get(1),FieldAnimal.SNAKEBODY);
        setField(sn.get(sn.size()-1),FieldAnimal.SNAKETAIL);
        setField(oldCoord,FieldAnimal.NULL);
        ModelChange(gameField);
    }

    //метод передачи объектов в интерфейс
    synchronized private void ModelChange(Object arg){
        setChanged();
        notifyObservers(arg);
    }

    synchronized public FieldAnimal testField(Coord coord) {
        return gameField[coord.x][coord.y];
    }

    synchronized private void setField(Coord coord, FieldAnimal animal){
        gameField[coord.x][coord.y]=animal;
    }

    void incScore(){
        ModelChange(++score);
    }

    void decScore(){
        ModelChange(--score);
    }

    void zeroScore(){
        score=0;
        ModelChange(score);
    }

    public FieldAnimal[][] getGameField() {
        return gameField;
    }
}
