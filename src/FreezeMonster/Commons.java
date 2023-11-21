package FreezeMonster;

public class Commons {
    private static Commons instance;

    private Commons() {
    }

    public static synchronized Commons getInstance() {
        if (instance == null) {
            instance = new Commons();
        }

        return instance;
    }

    public int BOMB_HEIGHT = 20;
    public int MONSTER_QTD = 9;
    public int BOARD_WIDTH = 500;
    public int BOARD_HEIGHT = 500;
    public int BORDER_RIGHT = 40;
    public int BORDER_LEFT = 5;
    public int GROUND = BOARD_HEIGHT -60;
    public int MONSTER_HEIGHT = 30;
    public int MONSTER_WIDTH = 30;
    public int MONSTER_INIT_X = 150;
    public int MONSTER_INIT_Y = 5;
    public int NUMBER_OF_MONSTERS_TO_DESTROY = 9;
    public int DELAY = 17;
    public int PLAYER_WIDTH = 20;
    public int PLAYER_HEIGHT = 30;
}
