package Model;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.*;

public class GameMap {

    private Tail[][] tails=new Tail[10][10];

    public GameMap() {
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Tail[] it : tails) {
            s.append(Arrays.toString(it)).append('\n');
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMap gameMap = (GameMap) o;
        return Arrays.equals(tails, gameMap.tails);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tails);
    }

    public Tail[][] getTails() {
        return tails;
    }

    public void setTails(Tail[][] tails) {
        this.tails = tails;
    }

    public Tail fightChecker(Step step, Step step1) {// поиск того что нужно стереть, при убийстве вокруг обычные шашки
        int x, y;
        if (step1.getX() < step.getX()) {
            x = step.getX() - 1;
        } else {
            x = step.getX() + 1;
        }
        if (step1.getY() < step.getY()) {
            y = step.getY() - 1;
        } else {
            y = step.getY() + 1;
        }
        return tails[x][y];
    }

    public void fightCheckerQueen(Step step, Step step1) {// поиск того что нужно стереть, при убийстве дамкой
        if(step.getX()> step1.getX() && step.getY()< step1.getY()){
            int x=step.getX()-1;
            int y=step.getY()+1;
            while (x > step1.getX() && y < step1.getY()) {
                tails[x][y].setWhatIs("3");
                x--;
                y++;
            }
        }
        if(step.getX()> step1.getX() && step.getY()> step1.getY()){
            int x=step.getX()-1;
            int y=step.getY()-1;
            while (x > step1.getX() && y > step1.getY()) {
                tails[x][y].setWhatIs("3");
                x--;
                y--;
            }
        }
        if(step.getX()< step1.getX() && step.getY()< step1.getY()){
            int x=step.getX()+1;
            int y=step.getY()+1;
            while (x < step1.getX() && y < step1.getY()) {
                tails[x][y].setWhatIs("3");
                x++;
                y++;
            }
        }
        if(step.getX()< step1.getX() && step.getY()> step1.getY()){
            int x=step.getX()+1;
            int y=step.getY()-1;
            while (x < step1.getX() && y > step1.getY()) {
                tails[x][y].setWhatIs("3");
                x++;
                y--;
            }
        }
    }

    public List<Step> findQueen() {//поиск королев и возврат их положения
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tails[i][j].getWhatIs().equals("2") || tails[i][j].getWhatIs().equals("5")) {
                    steps.add(new Step(i, j));
                }
            }
        }
        return steps;
    }

    public String what(boolean buffChStep) {//какая фишка
        String checker;
        if (buffChStep) {
            checker = "2,4";
        } else {
            checker = "5,1";
        }
        return checker;
    }

    public void goQueen() {
        for (int i = 0; i < 10; i++) {//превращение в белую
            Tail tail = tails[1][i];
            if (tail.toString().equals("4")) {
                tail.setWhatIs("2");
            }
        }
        for (int i = 0; i < 10; i++) {//превращение в черную
            Tail tail = tails[8][i];
            if (tail.toString().equals("1")) {
                tail.setWhatIs("5");
            }
        }
    }

    public List<String> whoWin() {// просто считывать и каких на доске не осталось другой цвет победил, проверять после хода каждого
        List<String> whoWin = new ArrayList<>();
        int black = 0;
        int white = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tails[i][j].getWhatIs().equals("1") || tails[i][j].getWhatIs().equals("5")) {
                    black++;
                }
                if (tails[i][j].getWhatIs().equals("2") || tails[i][j].getWhatIs().equals("4")) {
                    white++;
                }
            }
        }
        if (white == 0) {
            whoWin.add("Победа черных");
        }
        if (black == 0) {
            whoWin.add("Победа белых");
        }
        return whoWin;
    }

    public boolean createRegularGameMap(){
        tails[0] = new Tail[]
                {new Tail("0"), new Tail("a"), new Tail("b"), new Tail("c"), new Tail("d")
                        , new Tail("e"), new Tail("f"), new Tail("g"), new Tail("h"), new Tail("0")};
        tails[1] = new Tail[]
                {new Tail("8 "), new Tail("0"), new Tail("1"), new Tail("0"), new Tail("1")
                        , new Tail("0"), new Tail("1"), new Tail("0"), new Tail("1"), new Tail("8 ")};
        tails[2] = new Tail[]
                {new Tail("7 "), new Tail("1"), new Tail("0"), new Tail("1"), new Tail("0")
                        , new Tail("1"), new Tail("0"), new Tail("1"), new Tail("0"), new Tail("7 ")};
        tails[3] = new Tail[]
                {new Tail("6 "), new Tail("0"), new Tail("1"), new Tail("0"), new Tail("1")
                        , new Tail("0"), new Tail("1"), new Tail("0"), new Tail("1"), new Tail("6 ")};
        tails[4] = new Tail[]
                {new Tail("5 "), new Tail("3"), new Tail("0"), new Tail("3"), new Tail("0")
                        , new Tail("3"), new Tail("0"), new Tail("3"), new Tail("0"), new Tail("5 ")};
        tails[5] = new Tail[]
                {new Tail("4 "), new Tail("0"), new Tail("3"), new Tail("0"), new Tail("3")
                        , new Tail("0"), new Tail("3"), new Tail("0"), new Tail("3"), new Tail("4 ")};
        tails[6] = new Tail[]
                {new Tail("3 "), new Tail("4"), new Tail("0"), new Tail("4"), new Tail("0")
                        , new Tail("4"), new Tail("0"), new Tail("4"), new Tail("0"), new Tail("3 ")};
        tails[7] = new Tail[]
                {new Tail("2 "), new Tail("0"), new Tail("4"), new Tail("0"), new Tail("4")
                        , new Tail("0"), new Tail("4"), new Tail("0"), new Tail("4"), new Tail("2 ")};
        tails[8] = new Tail[]
                {new Tail("1 "), new Tail("4"), new Tail("0"), new Tail("4"), new Tail("0")
                        , new Tail("4"), new Tail("0"), new Tail("4"), new Tail("0"), new Tail("1 ")};
        tails[9] = new Tail[]
                {new Tail("0"), new Tail("a"), new Tail("b"), new Tail("c"), new Tail("d")
                        , new Tail("e"), new Tail("f"), new Tail("g"), new Tail("h"), new Tail("0")};
        return true;
    }

    public List<Step> stepWhite(int row,int column) {//поиск хода белой обычной шашки
        List<Step> butnList = new ArrayList<>();
        if (column - 1 == 0) {//обработка выхода за массив с лева
            String s = tails[row - 1][column + 1].toString();
            if (s.equals("3")) {//проверка нет ли там белой(добавить на черную, то бьем если черная)
                butnList.add(new Step(row - 1, column + 1));
            }
            return butnList;
        }
        if (column + 1 == 9) {//обработка выхода за массив с права
            String s = tails[row - 1][column - 1].toString();
            if (s.equals("3")) {//проверка нет ли там белой(добавить на черную, то бьем если черная)
                butnList.add(new Step(row - 1, column - 1));
            }
            return butnList;
        }
        if (tails[row - 1][column + 1].getWhatIs().equals("3") &&
                tails[row - 1][column - 1].getWhatIs().equals("3")) {//обработка когда с верху 2 пустоты
            butnList.add(new Step(row - 1, column + 1));
            butnList.add(new Step(row - 1, column - 1));
        }
        if (tails[row - 1][column + 1].getWhatIs().equals("3") &&
                (tails[row - 1][column - 1].getWhatIs().equals("4")
                        || tails[row - 1][column - 1].getWhatIs().equals("2"))) {//обработка, когда с верху 1 белая с права
            butnList.add(new Step(row - 1, column + 1));
        }
        if ((tails[row - 1][column + 1].getWhatIs().equals("4")
                || tails[row - 1][column + 1].getWhatIs().equals("2")) &&
                tails[row - 1][column - 1].getWhatIs().equals("3")) {//обработка, когда с верху 1 белая с лева
            butnList.add(new Step(row - 1, column - 1));
        }
        if (tails[row - 1][column - 1].getWhatIs().equals("3") &&
                (tails[row - 1][column + 1].getWhatIs().equals("1")
                        || tails[row - 1][column + 1].getWhatIs().equals("5"))) {//обработка, когда с верху 1 черная с лева
            butnList.add(new Step(row - 1, column - 1));
        }
        if (tails[row - 1][column + 1].getWhatIs().equals("3") &&
                (tails[row - 1][column - 1].getWhatIs().equals("1")
                        || tails[row - 1][column - 1].getWhatIs().equals("5"))) {//обработка, когда с верху 1 черная с права
            butnList.add(new Step(row - 1, column + 1));
        }
        return butnList;
    }

    public List<Step> stepBlack(int row,int column) {// ход черной
        List<Step> butnList = new ArrayList<>();
        if (column - 1 == 0) {//обработка выхода за массив с лева
            String s = tails[row + 1][column + 1].toString();
            if (s.equals("3")) {//проверка нет ли там белой(добавить на черную, то бьем если черная)
                butnList.add(new Step(row + 1, column + 1));
            }
            return butnList;
        }
        if (column + 1 == 9) {//обработка выхода за массив с права
            String s = tails[row + 1][column - 1].toString();
            if (s.equals("3")) {//проверка нет ли там белой(добавить на черную, то бьем если черная)
                butnList.add(new Step(row + 1, column - 1));
            }
            return butnList;
        }

        if (tails[row + 1][column + 1].getWhatIs().equals("3") &&
                tails[row + 1][column - 1].getWhatIs().equals("3")) {//обработка когда с низу 2 пустоты
            butnList.add(new Step(row + 1, column + 1));
            butnList.add(new Step(row + 1, column - 1));
        }
        if (tails[row + 1][column + 1].getWhatIs().equals("3") &&
                (tails[row + 1][column - 1].getWhatIs().equals("1")
                        || tails[row + 1][column - 1].getWhatIs().equals("5"))) {//обработка, когда с низу 1 черная с права
            butnList.add(new Step(row + 1, column + 1));
        }
        if ((tails[row + 1][column + 1].getWhatIs().equals("1")
                || tails[row + 1][column + 1].getWhatIs().equals("5") ) &&
                tails[row + 1][column - 1].getWhatIs().equals("3")) {//обработка, когда с низу 1 черная с лева
            butnList.add(new Step(row + 1, column - 1));
        }
        if (tails[row + 1][column + 1].getWhatIs().equals("3") &&
                ( tails[row + 1][column - 1].getWhatIs().equals("4")
                        || tails[row + 1][column - 1].getWhatIs().equals("2"))) {//обработка, когда с низу 1 белая с лева
            butnList.add(new Step(row + 1, column + 1));
        }
        if (tails[row + 1][column - 1].getWhatIs().equals("3") &&
                (tails[row + 1][column + 1].getWhatIs().equals("4")
                        || tails[row + 1][column + 1].getWhatIs().equals("2"))) {//обработка, когда с низу 1 белая с права
            butnList.add(new Step(row + 1, column - 1));
        }
        return butnList;
    }

    public List<Step> stepQueen(int row, int column) {// не обязательные ходы белой/черной королевы
        List<Step> butnList = new ArrayList<>();
        int x = row + 1;
        int y = column + 1;
        while (x < 9 && y < 9) {
            if (tails[x][y].getWhatIs().equals("3")) {
                butnList.add(new Step(x, y));
            } else {
                break;
            }
            x++;
            y++;
        }
        x = row - 1;
        y = column - 1;
        while (x > 0 && y > 0) {
            if (tails[x][y].getWhatIs().equals("3")) {
                butnList.add(new Step(x, y));
            } else {
                break;
            }
            x--;
            y--;
        }
        x = row - 1;
        y = column + 1;
        while (x > 0 && y < 9) {
            if (tails[x][y].getWhatIs().equals("3")) {
                butnList.add(new Step(x, y));
            } else {
                break;
            }
            x--;
            y++;
        }
        x = row + 1;
        y = column - 1;
        while (x < 9 && y > 0) {
            if (tails[x][y].getWhatIs().equals("3")) {
                butnList.add(new Step(x, y));
            } else {
                break;
            }
            x++;
            y--;
        }

        return butnList;
    }
}
