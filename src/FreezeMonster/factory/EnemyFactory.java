package FreezeMonster.factory;

import java.util.*;

import FreezeMonster.Commons;
import FreezeMonster.sprite.Gosma;
import FreezeMonster.sprite.Monster;
import FreezeMonster.sprite.Sprite;

public class EnemyFactory implements EntityAbstractfactory{
    public Commons commons = Commons.getInstance();
    Random random = new Random();

    Map<String, Sprite> enemyPrototype = new HashMap<>(){{
        put("Monster", new Monster(getMonsterSkin()));
    }};
    

    @Override
    public Sprite create(String entityType) {
        return enemyPrototype.get(entityType).clone();
    }

    private int getMonsterSkin() {
        return random.nextInt(commons.MONSTER_QTD);
    }
}
