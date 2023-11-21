package FreezeMonster.factory;

public interface Abstractfactory<T> {
   T create(String entityType);
}
