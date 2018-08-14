package Sinitcyn.Roman.Snake.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GameField extends Observable {

    private FieldAnimal[][] gameField;

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
    }

    Coord addFrog(FieldAnimal fieldAnimal) {
        while (true) {
            Coord coord = new Coord(new Random().nextInt(ModelSnake.getSizeMap().x),
                                    new Random().nextInt(ModelSnake.getSizeMap().y));
            if (gameField[coord.x][coord.y] == FieldAnimal.NULL) {
                gameField[coord.x][coord.y]=fieldAnimal;
                return coord;
            }
            ModelChange(gameField);
        }
    }

    //начальное расположение змеи
    void addSnake(ArrayList<Coord> sn){
        gameField[sn.get(0).x][sn.get(0).y]=FieldAnimal.SNAKEHEAD;
        gameField[sn.get(sn.size()-1).x][sn.get(sn.size()-1).y]=FieldAnimal.SNAKETAIL;
        for(int i=1;i<sn.size()-1;i++){
            gameField[sn.get(i).x][sn.get(i).y]=FieldAnimal.SNAKEBODY;
        }
        ModelChange(gameField);
    }
    void MoveFrog(Coord coord) {

    }

    void MoveSnake(ArrayList<Coord> sn,Coord oldCoord) {
        gameField[sn.get(0).x][sn.get(0).y]=FieldAnimal.SNAKEHEAD;
        gameField[sn.get(1).x][sn.get(1).y]=FieldAnimal.SNAKEBODY;
        gameField[sn.get(sn.size()-1).x][sn.get(sn.size()-1).y]=FieldAnimal.SNAKETAIL;
        gameField[oldCoord.x][oldCoord.y]=FieldAnimal.NULL;
        ModelChange(gameField);
    }

    //метод передачи объектов в интерфейс

    synchronized private void ModelChange(Object arg){
        setChanged();
        notifyObservers(arg);
    }

    public FieldAnimal[][] getGameField() {
        return gameField;
    }
}
