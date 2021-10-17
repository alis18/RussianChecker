package checker.russianchecker;

import Model.GameMap;
import Model.Step;
import Model.Tail;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.*;

public class Controller {

    private GameMap gameMap;//игровое поле само
    private Button[] buttons = new Button[32];//массив кнопок
    private boolean chekStep = true;//когда true ходят белые, когда false ходят черные
    private Button buttonBuff = new Button();//запоминает пред идущую нажатую кнопку, которая была подсвечена
    private List<Step> stepList = new ArrayList<>();//возможные ходы подсвеченной фишки на ходу, если нажали на кнопку из списка шагов, то перемещаем туда
    private Step buffStep = new Step();//буфер для кнопки которая была перед этим нажата

    @FXML
    private GridPane game;

    @FXML
    private Label stepH;

    @FXML
    private Label whoWin;

    @FXML
    protected void onHelloButtonClick() {
        buttons = new Button[32];
        chekStep = true;
        buttonBuff = new Button();
        stepList = new ArrayList<>();
        whoWin.setText("");
        initialize();
    }

    @FXML
    void initialize() {
        gameMap = new GameMap();
        gameMap.createRegularGameMap();
        print();
        stepH.setText("Ход белых");
        System.out.println(gameMap.toString());
        buttonsAll();
    }

    private void buttonsAll() {//по новой инициализируем все кнопки после заполнения массива
        buttons[0].setOnAction(event -> step(buttons[0]));
        buttons[1].setOnAction(event -> step(buttons[1]));
        buttons[2].setOnAction(event -> step(buttons[2]));
        buttons[3].setOnAction(event -> step(buttons[3]));
        buttons[4].setOnAction(event -> step(buttons[4]));
        buttons[5].setOnAction(event -> step(buttons[5]));
        buttons[6].setOnAction(event -> step(buttons[6]));
        buttons[7].setOnAction(event -> step(buttons[7]));
        buttons[8].setOnAction(event -> step(buttons[8]));
        buttons[9].setOnAction(event -> step(buttons[9]));
        buttons[10].setOnAction(event -> step(buttons[10]));
        buttons[11].setOnAction(event -> step(buttons[11]));
        buttons[12].setOnAction(event -> step(buttons[12]));
        buttons[13].setOnAction(event -> step(buttons[13]));
        buttons[14].setOnAction(event -> step(buttons[14]));
        buttons[15].setOnAction(event -> step(buttons[15]));
        buttons[16].setOnAction(event -> step(buttons[16]));
        buttons[17].setOnAction(event -> step(buttons[17]));
        buttons[18].setOnAction(event -> step(buttons[18]));
        buttons[19].setOnAction(event -> step(buttons[19]));
        buttons[20].setOnAction(event -> step(buttons[20]));
        buttons[21].setOnAction(event -> step(buttons[21]));
        buttons[22].setOnAction(event -> step(buttons[22]));
        buttons[23].setOnAction(event -> step(buttons[23]));
        buttons[24].setOnAction(event -> step(buttons[24]));
        buttons[25].setOnAction(event -> step(buttons[25]));
        buttons[26].setOnAction(event -> step(buttons[26]));
        buttons[27].setOnAction(event -> step(buttons[27]));
        buttons[28].setOnAction(event -> step(buttons[28]));
        buttons[29].setOnAction(event -> step(buttons[29]));
        buttons[30].setOnAction(event -> step(buttons[30]));
        buttons[31].setOnAction(event -> step(buttons[31]));
    }

    /*
    0-это пустота на которую никогда нельзя походить и там не стоят фишки
    1-это черные
    2-дамка Белая
    3-пустота, но туда можно походить
    4-это белая фишка
    5-дамка Черная
     */
    private void print() {
        int m = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (gameMap.getTails()[j][i].getWhatIs()) {
                    case "1" -> {
                        Button button = new Button("", new ImageView("black.png"));
                        buttons[m] = button;
                        button.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #776E65;");
                        button.setMaxSize(250, 250);
                        button.setAlignment(Pos.CENTER);
                        game.add(button, i, j);
                        m++;
                    }
                    case "2" -> {
                        Button button = new Button("", new ImageView("white_q.png"));
                        buttons[m] = button;
                        button.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #776E65;");
                        button.setMaxSize(250, 250);
                        button.setAlignment(Pos.CENTER);
                        game.add(button, i, j);
                        m++;
                    }
                    case "3" -> {
                        Button button = new Button();
                        buttons[m] = button;
                        button.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #776E65;");
                        button.setMaxSize(250, 250);
                        button.setAlignment(Pos.CENTER);
                        game.add(button, i, j);
                        m++;
                    }
                    case "4" -> {
                        Button button = new Button("", new ImageView("white.png"));
                        buttons[m] = button;
                        button.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #776E65;");
                        button.setMaxSize(250, 250);
                        button.setAlignment(Pos.CENTER);
                        game.add(button, i, j);
                        m++;
                    }
                    case "5" -> {
                        Button button = new Button("", new ImageView("black_q.png"));
                        buttons[m] = button;
                        button.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #776E65;");
                        button.setMaxSize(250, 250);
                        button.setAlignment(Pos.CENTER);
                        game.add(button, i, j);
                        m++;
                    }
                    default -> { // пустота(побочные поля, на которых мы не играем)
                        Label label;
                        if (gameMap.getTails()[j][i].getWhatIs().equals("0")) {
                            label = new Label(" ");
                            label.setStyle("-fx-background-color: #F0E68C;" + "-fx-border-color: #776E65;");
                        } else {
                            label = new Label(gameMap.getTails()[j][i].getWhatIs());
                            label.setStyle("-fx-background-color: #696969;" + "-fx-border-color: #776E65;");
                        }
                        label.setMaxSize(250, 250);
                        label.setAlignment(Pos.CENTER);
                        game.add(label, i, j);
                    }
                }

            }
        }
    }


    private void illuminationBorder(Button button) {// подсвет элемента на который мы нажали
        button.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #FF0000;");
    }

    private void offBorder(Button button) {// подсвет элемента на который мы нажали
        button.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #776E65;");
    }

    private void canIlStep(List<Step> list) {// подсвет элемента на который мы можем походить
        for (Step it : list) {
            for (Button element : buttons) {
                if (GridPane.getRowIndex(element) == it.getX() && GridPane.getColumnIndex(element) == it.getY()) {
                    element.setStyle("-fx-background-color: #CD5C5C;");
                }
            }
        }
    }

    private boolean difStep() {//проверяем есть ли где-то подсветка
        for (Button it : buttons) {
            if (it.getStyle().equals("-fx-background-color: #CD5C5C;")) {
                return true;
            }
        }
        return false;
    }

    private void cantIlStep(List<Step> list) {// убрать подсвеченный элемент, на который мы можем походить
        for (Step it : list) {
            for (Button element : buttons) {
                if (GridPane.getRowIndex(element) == it.getX() && GridPane.getColumnIndex(element) == it.getY()) {
                    element.setStyle("-fx-background-color: #CD853F;" + "-fx-border-color: #776E65;");
                }
            }
        }
        list.clear();
    }


    //--------------------------- сверху все связанно с отрисовками


    private Map<Step, List<Step>> findKillNormalChecker() {//ищет обязательные убийства на ходу //ключ кто бьет, список его ударов возможных
        Map<Step, List<Step>> map = new HashMap<>();
        boolean buffChStep = chekStep;
        String checker = gameMap.what(buffChStep).split(",")[1];
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (gameMap.getTails()[i][j].toString().equals(checker)) {
                    if ((gameMap.getTails()[i + 1][j + 1].toString().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[i + 1][j + 1].toString().equals(gameMap.what(!buffChStep).split(",")[0])) && j + 2 < 9
                            & gameMap.getTails()[i + 2][j + 2].toString().equals("3")) {
                        if (map.containsKey(new Step(i, j))) {
                            List<Step> list = map.get(new Step(i, j));
                            list.add(new Step(i + 2, j + 2));
                        } else {
                            List<Step> list = new ArrayList<>();
                            list.add(new Step(i + 2, j + 2));
                            map.put(new Step(i, j), list);
                        }
                    }
                    if ((gameMap.getTails()[i - 1][j + 1].toString().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[i - 1][j + 1].toString().equals(gameMap.what(!buffChStep).split(",")[0])) && j + 2 < 9
                            & gameMap.getTails()[i - 2][j + 2].toString().equals("3")) {
                        if (map.containsKey(new Step(i, j))) {
                            List<Step> list = map.get(new Step(i, j));
                            list.add(new Step(i - 2, j + 2));
                        } else {
                            List<Step> list = new ArrayList<>();
                            list.add(new Step(i - 2, j + 2));
                            map.put(new Step(i, j), list);
                        }
                    }
                    if ((gameMap.getTails()[i + 1][j - 1].toString().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[i + 1][j - 1].toString().equals(gameMap.what(!buffChStep).split(",")[0])) && j - 2 > 0
                            & gameMap.getTails()[i + 2][j - 2].toString().equals("3")) {
                        if (map.containsKey(new Step(i, j))) {
                            List<Step> list = map.get(new Step(i, j));
                            list.add(new Step(i + 2, j - 2));
                        } else {
                            List<Step> list = new ArrayList<>();
                            list.add(new Step(i + 2, j - 2));
                            map.put(new Step(i, j), list);
                        }
                    }
                    if ((gameMap.getTails()[i - 1][j - 1].toString().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[i - 1][j - 1].toString().equals(gameMap.what(!buffChStep).split(",")[0])) && j - 2 > 0
                            & gameMap.getTails()[i - 2][j - 2].toString().equals("3")) {
                        if (map.containsKey(new Step(i, j))) {
                            List<Step> list = map.get(new Step(i, j));
                            list.add(new Step(i - 2, j - 2));
                        } else {
                            List<Step> list = new ArrayList<>();
                            list.add(new Step(i - 2, j - 2));
                            map.put(new Step(i, j), list);
                        }
                    }
                }
            }
        }

        return map;
    }

    private Map<Step, List<Step>> findKillQueen(List<Step> step) {//ключ кто бьет, список его ударов возможных
        // после того, как нашли ударную, проверить все клетки за ней, на возможность такого же удара
        //(то есть все шаги)
        // просто вернуть все возможные клетки после ударной !!!(и их так же проверить рекурсией на удар)
        Map<Step, List<Step>> map = new HashMap<>();
        boolean buffChStep = chekStep;
        String checker = gameMap.what(buffChStep).split(",")[0];
        List<Step> canGoStep = new ArrayList<>();
        for (Step element : step) {
            if (gameMap.getTails()[element.getX()][element.getY()].getWhatIs().equals(checker)) {
                int x = element.getX() + 1;
                int y = element.getY() + 1;
                int i = 0;
                while (x < 9 && y < 9) {
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i == 0) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i > 0) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[0]) && i < 2) {
                        i++;
                    }
                    if (i == 2) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals("3") && i > 0) {
                        canGoStep.add(new Step(x, y));
                    }
                    x++;
                    y++;
                }
                x = element.getX() - 1;
                y = element.getY() - 1;
                i = 0;
                while (x > 0 && y > 0) {
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i == 0) {
                        break;
                    }

                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i > 0) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[0]) && i < 2) {

                        i++;
                    }
                    if (i == 2) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals("3") && i > 0) {
                        canGoStep.add(new Step(x, y));
                    }
                    x--;
                    y--;
                }
                x = element.getX() - 1;
                y = element.getY() + 1;
                i = 0;
                while (x > 0 && y < 9) {
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i == 0) {
                        break;
                    }

                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i > 0) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[0]) && i < 2) {
                        i++;
                    }
                    if (i == 2) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals("3") && i > 0) {
                        canGoStep.add(new Step(x, y));
                    }
                    x--;
                    y++;
                }
                x = element.getX() + 1;
                y = element.getY() - 1;
                i = 0;
                while (x < 9 && y > 0) {
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i == 0) {
                        break;
                    }

                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(buffChStep).split(",")[0]) && i > 0) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[1])
                            || gameMap.getTails()[x][y].getWhatIs().equals(gameMap.what(!buffChStep).split(",")[0]) && i < 2) {
                        i++;
                    }
                    if (i == 2) {
                        break;
                    }
                    if (gameMap.getTails()[x][y].getWhatIs().equals("3") && i > 0) {
                        canGoStep.add(new Step(x, y));
                    }
                    x++;
                    y--;
                }
                if (canGoStep.size() != 0) {
                    map.put(element, canGoStep);
                }
            }
        }
        return map;
    }


    private void step(Button button) {
        String s = gameMap.getTails()[GridPane.getRowIndex(button)][GridPane.getColumnIndex(button)].toString();
        offBorder(buttonBuff);// выключаем подсветку старой кнопки
        Step step = new Step(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
        Map<Step, List<Step>> mustStep = findKillNormalChecker();//если он будет не пустой, то нужно искать возможность хода
        mustStep.putAll(findKillQueen(gameMap.findQueen()));
        if (mustStep.containsKey(step)) {
            for (Step mustS : mustStep.get(step)) {
                List<Step> list = new ArrayList<>();
                list.add(mustS);
                if (findKillQueen(list).size() != 0) {
                    mustStep.putAll(findKillQueen(list));
                }
            }
        }

        for (Step it : stepList) {
            if (step.equals(it)) {// проверка нажата ли кнопка возможного хода
                Step step1 = new Step(GridPane.getRowIndex(buttonBuff), GridPane.getColumnIndex(buttonBuff));
                if (mustStep.containsKey(step1)) {//стирается тот кого ударили
                    gameMap.fightChecker(step, step1).setWhatIs("3");
                    gameMap.fightCheckerQueen(step, buffStep);
                }
                String buffValue = gameMap.getTails()[GridPane.getRowIndex(buttonBuff)][GridPane.getColumnIndex(buttonBuff)].toString();
                buttonBuff.setText("");
                gameMap.getTails()[GridPane.getRowIndex(buttonBuff)][GridPane.getColumnIndex(buttonBuff)].setWhatIs(gameMap.getTails()[GridPane.getRowIndex(button)]
                        [GridPane.getColumnIndex(button)].toString());
                offBorder(button);
                Tail tail = gameMap.getTails()[it.getX()][it.getY()];
                tail.setWhatIs(buffValue);
                gameMap.goQueen();
                game.getChildren().clear();
                print();
                buttonsAll();
                stepList.clear();
                if (mustStep.size() == 0) {
                    chekStep = !chekStep;//после того как мы чем то походили передаем ход другому, если это серия из 1 удара
                } else {
                    step(button);
                    mustStep = findKillNormalChecker();//если он будет не пустой, то нужно искать возможность хода
                    mustStep.putAll(findKillQueen(gameMap.findQueen()));
                    if (mustStep.size() == 0 || !difStep()) {//если подсветка пропала, значит передать ход
                        chekStep = !chekStep;
                        print();
                        buttonsAll();
                        stepList.clear();
                    }
                }

                if (chekStep) {//отображение чей сейчас ход
                    stepH.setText("Ход белых");
                } else {
                    stepH.setText("Ход черных");
                }
                if (gameMap.whoWin().size() != 0) {//вывод кто выиграл пока что в консоль
                    whoWin.setText(gameMap.whoWin().get(0) + ", если хотите сыграть снова нажмите кнопку Новая игра");
                }
                return;
            }
        }

        cantIlStep(stepList);//при нажатии на какуето другу кнопку убирается подсветка, если мы не походи на подсвеченную
        illuminationBorder(button);// включаем подсветку новой
        buttonBuff = button;
        Step step1 = new Step(GridPane.getRowIndex(buttonBuff), GridPane.getColumnIndex(buttonBuff));
        int row = GridPane.getRowIndex(buttonBuff);
        int column = GridPane.getColumnIndex(buttonBuff);
        if (s.equals("1")) {//кликнули на черный
            if (!chekStep) {//ход черных
                if (mustStep.containsKey(step1)) {
                    stepList = mustStep.get(step1);
                } else {
                    if (mustStep.size() == 0) {
                        stepList = gameMap.stepBlack(row, column);
                    }
                }
                canIlStep(stepList);
            }
        } else if (s.equals("4")) {//кликнули на белый
            if (chekStep) {//ход белых
                if (mustStep.containsKey(step1)) {
                    stepList = mustStep.get(step1);
                } else {
                    if (mustStep.size() == 0) {
                        stepList = gameMap.stepWhite(row, column);
                    }
                }
                canIlStep(stepList);

            }
        } else if (s.equals("2")) {//кликнули на белую дамку
            if (chekStep) {
                if (mustStep.containsKey(step1)) {
                    stepList = mustStep.get(step1);
                } else {
                    if (mustStep.size() == 0) {
                        stepList = gameMap.stepQueen(row, column);
                    }
                }
            }
            canIlStep(stepList);
        } else if (s.equals("5")) {//кликнули на черную дамку
            if (!chekStep) {
                if (mustStep.containsKey(step1)) {
                    stepList = mustStep.get(step1);
                } else {
                    if (mustStep.size() == 0) {
                        stepList = gameMap.stepQueen(row, column);
                    }
                }
            }
            canIlStep(stepList);
        }
        buffStep = new Step(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
        System.out.println("Линия: "+GridPane.getRowIndex(button)+", Столбик: "+GridPane.getColumnIndex(button));
    }
}