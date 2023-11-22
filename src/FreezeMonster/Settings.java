package FreezeMonster;

import framework.FrameSettings;

public class Settings implements FrameSettings {
    private static Settings instance;

    private Settings() {
    }

    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }

        return instance;
    }

    public int BOMB_HEIGHT = 20;
    public int GROUND = BOARD_HEIGHT - 60;
    public int MONSTER_HEIGHT = 60;
    public int MONSTER_WIDTH = 60;
    public int NUMBER_OF_MONSTERS_TO_DESTROY = 9;
    public int PLAYER_WIDTH = 40;
    public int PLAYER_HEIGHT = 60;
    public int RAY_SIZE = 25;
}
