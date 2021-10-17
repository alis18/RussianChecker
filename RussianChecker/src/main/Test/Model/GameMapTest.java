package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

    @Test
    void fightChecker() {
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("0");
            }
        }
        tails[6][4].setWhatIs("");
    }

    @Test
    void fightCheckerQueen() {
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("3");
            }
        }
        tails[4][3].setWhatIs("5");
        Step start=new Step(7,6);
        Step end=new Step(3,2);
        gameMap.fightCheckerQueen(start,end);
        assertEquals("3",tails[4][3].getWhatIs());//затирает все идя от одной к другой
    }

    @Test
    void findQueen() {
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("0");
            }
        }
        tails[4][3].setWhatIs("2");
        tails[3][6].setWhatIs("2");
        tails[6][7].setWhatIs("5");
        List<Step> steps=new ArrayList<>();
        steps.add(new Step(3,6));
        steps.add(new Step(4,3));
        steps.add(new Step(6,7));
        assertEquals(steps,gameMap.findQueen());
        tails[8][5].setWhatIs("5");
        steps.add(new Step(8,5));
        assertEquals(steps,gameMap.findQueen());
    }

    @Test
    void what() {
        GameMap gameMap = new GameMap();
        assertEquals("2,4",gameMap.what(true));//белые дамка и фишка
        assertEquals("5,1",gameMap.what(false));//черные дамка и фишка
    }

    @Test
    void goQueen() {
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("0");
            }
        }
        tails[1][4].setWhatIs("4");
        tails[8][3].setWhatIs("1");
        gameMap.goQueen();
        assertEquals("2",tails[1][4].getWhatIs());//превращает в белую дамку
        assertEquals("5",tails[8][3].getWhatIs());//превращает в черную дамку
    }

    @Test
    void whoWin() {
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("0");
            }
        }
        tails[5][6].setWhatIs("1");//осталась обычная черная
        assertEquals("Победа черных",gameMap.whoWin().get(0));
        tails[5][6].setWhatIs("5");//осталась дамка черная
        assertEquals("Победа черных",gameMap.whoWin().get(0));
        tails[5][6].setWhatIs("4");//осталась обычная белая
        assertEquals("Победа белых",gameMap.whoWin().get(0));
        tails[5][6].setWhatIs("2");//осталась дамка черная
        assertEquals("Победа белых",gameMap.whoWin().get(0));
    }

    @Test
    void createRegularGameMap() {// тест на создание игрового поля
        GameMap gameMap = new GameMap();
        assertEquals(true, gameMap.createRegularGameMap());
    }

    @Test
    void stepWhite() {
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("0");
            }
        }
        tails[6][3].setWhatIs("4");//если с лева и права пусто
        tails[5][2].setWhatIs("3");
        tails[5][4].setWhatIs("3");
        List<Step> steps=new ArrayList<>();
        steps.add(new Step(5,4));
        steps.add(new Step(5,2));
        assertEquals(steps,gameMap.stepWhite(6,3));
        steps.clear();
        tails[5][2].setWhatIs("1");//если с права фишка
        tails[5][4].setWhatIs("3");
        steps.add(new Step(5,4));
        assertEquals(steps,gameMap.stepWhite(6,3));
        steps.clear();
        tails[5][2].setWhatIs("3");//если с лева фишка
        tails[5][4].setWhatIs("1");
        steps.add(new Step(5,2));
        assertEquals(steps,gameMap.stepWhite(6,3));
        steps.clear();
        tails[5][2].setWhatIs("3");//если с лева фишка
        tails[5][4].setWhatIs("4");
        steps.add(new Step(5,2));
        assertEquals(steps,gameMap.stepWhite(6,3));
        steps.clear();
        tails[5][2].setWhatIs("4");//если с права фишка
        tails[5][4].setWhatIs("3");
        steps.add(new Step(5,4));
        //фишка может быть любой
    }

    @Test
    void stepBlack() {// ход черными
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("0");
            }
        }
        tails[6][3].setWhatIs("1");//если с лева и права пусто
        tails[7][2].setWhatIs("3");
        tails[7][4].setWhatIs("3");
        List<Step> steps=new ArrayList<>();
        steps.add(new Step(7,4));
        steps.add(new Step(7,2));
        assertEquals(steps,gameMap.stepBlack(6,3));
        steps.clear();
        tails[7][2].setWhatIs("4");//если с права фишка
        tails[7][4].setWhatIs("3");
        steps.add(new Step(7,4));
        assertEquals(steps,gameMap.stepBlack(6,3));
        steps.clear();
        tails[7][2].setWhatIs("3");//если с лева фишка
        tails[7][4].setWhatIs("4");
        steps.add(new Step(7,2));
        assertEquals(steps,gameMap.stepBlack(6,3));
        steps.clear();
        tails[7][2].setWhatIs("3");//если с лева фишка
        tails[7][4].setWhatIs("1");
        steps.add(new Step(7,2));
        assertEquals(steps,gameMap.stepBlack(6,3));
        steps.clear();
        tails[7][2].setWhatIs("1");//если с права фишка
        tails[7][4].setWhatIs("3");
        steps.add(new Step(7,4));
        //фишка может быть любой
    }

    @Test
    void stepQueen() {//ход любой королевы
        GameMap gameMap = new GameMap();
        gameMap.createRegularGameMap();
        Tail[][] tails=gameMap.getTails();
        for(Tail[] it: tails){
            for(Tail it1: it) {
                it1.setWhatIs("3");
            }
        }
        tails[6][5].setWhatIs("5");
        List<Step> steps=new ArrayList<>();
        steps.add(new Step(7,6));
        steps.add(new Step(8,7));
        steps.add(new Step(5,4));
        steps.add(new Step(4,3));
        steps.add(new Step(3,2));
        steps.add(new Step(2,1));
        steps.add(new Step(5,6));
        steps.add(new Step(4,7));
        steps.add(new Step(3,8));
        steps.add(new Step(7,4));
        steps.add(new Step(8,3));
        assertEquals(steps,gameMap.stepQueen(6,5));
        steps.clear();
        tails[6][5].setWhatIs("3");
        tails[7][2].setWhatIs("2");
        steps.add(new Step(8,3));
        steps.add(new Step(6,1));
        steps.add(new Step(6,3));
        steps.add(new Step(5,4));
        steps.add(new Step(4,5));
        steps.add(new Step(3,6));
        steps.add(new Step(2,7));
        steps.add(new Step(1,8));
        steps.add(new Step(8,1));
        assertEquals(steps,gameMap.stepQueen(7,2));
    }
}