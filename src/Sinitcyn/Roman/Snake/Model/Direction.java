package Sinitcyn.Roman.Snake.Model;

enum Direction {
    RIGTH,DOWN,LEFT,UP;
    Direction next(){
        return this.ordinal()==3 ? Direction.values()[this.ordinal()-3] : Direction.values()[this.ordinal()+1];
    }
    Direction pervious(){
        return this.ordinal()==0 ? Direction.values()[this.ordinal()+3] : Direction.values()[this.ordinal()-1];
    }
}
