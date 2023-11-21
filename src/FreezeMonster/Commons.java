package FreezeMonster;

import java.util.ArrayList;
import java.util.List;

public class Commons {
    private static Commons instance;
    private Commons(){}

    public static Commons getInstance(){
        if(instance == null)
            instance = new Commons();

        return instance;
    }
    public int MONSTER_QTD = 9;

    public int BOARD_WIDTH = 716;
    public int BOARD_HEIGHT = 700;
    public int BORDER_RIGHT = 30;
    public int BORDER_LEFT = 5;

    public int GROUND = 290;
    public int BOMB_HEIGHT = 20;

    public int ALIEN_HEIGHT = 12;
    public int ALIEN_WIDTH = 12;
    public int ALIEN_INIT_X = 150;
    public int ALIEN_INIT_Y = 5;

    public int GO_DOWN = 15;
    public int NUMBER_OF_ALIENS_TO_DESTROY = 24;
    public int CHANCE = 5;
    public int DELAY = 17;
    public int PLAYER_WIDTH = 15;
    public int PLAYER_HEIGHT = 10;
}
