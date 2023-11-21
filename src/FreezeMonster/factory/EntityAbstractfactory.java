package FreezeMonster.factory;

import FreezeMonster.sprite.Sprite;

public interface EntityAbstractfactory {
   Sprite create(String entityType);
}
