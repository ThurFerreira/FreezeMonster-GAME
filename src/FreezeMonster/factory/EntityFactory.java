package FreezeMonster.factory;

import java.util.*;

import FreezeMonster.Commons;
import FreezeMonster.sprite.Monster;
import FreezeMonster.sprite.Player;
import FreezeMonster.sprite.Ray;
import FreezeMonster.sprite.Sprite;

public class EntityFactory implements Abstractfactory<Sprite>{
    public Commons commons = Commons.getInstance();
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
        return random.nextInt(commons.MONSTER_QTD);
    }
}
