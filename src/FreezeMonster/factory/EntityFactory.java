package FreezeMonster.factory;

import java.util.*;

import FreezeMonster.Settings;
import framework.factory.Abstractfactory;
import FreezeMonster.sprite.Monster;
import FreezeMonster.sprite.Player;
import FreezeMonster.sprite.Ray;
import framework.Sprite;

public class EntityFactory implements Abstractfactory<Sprite> {
    public Settings commons = Settings.getInstance();
    Random random = new Random();
    
    @Override
    public Sprite create(String entityType) {
        switch (entityType) {
            case "Monster": return new Monster(getMonsterSkin());
            case "Ray": return new Ray();
            case "Player": return new Player();
            default:
                break;
        }

        return null;
    }

    private int getMonsterSkin() {
        return random.nextInt(8) + 1; //numero de 1 ate NUMBER_OF_MONSTERS_TO_DESTROY
    }
}
