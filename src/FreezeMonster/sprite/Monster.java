package FreezeMonster.sprite;

import FreezeMonster.Settings;
import framework.Sprite;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Monster extends Sprite {
    public Settings commons = Settings.getInstance();
    private int monsterType;

    private Gosma gosma;

    public Monster(int monsterType) {
        this.monsterType = monsterType;
        initMonster();
    }

    private void initMonster() {
        Random random = new Random();
        this.x = random.nextInt(commons.BOARD_WIDTH);
        this.y = random.nextInt(commons.BOARD_HEIGHT);

        gosma = new Gosma(x, y);

        String url = "src/images/monster" + monsterType + ".png";
        var ii = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(commons.MONSTER_WIDTH, commons.MONSTER_HEIGHT, Image.SCALE_SMOOTH));

        setImage(ii.getImage());
    }

    public void act(int dx, int dy) {

        this.x += dx;
        this.y += dy;
    }

    public Gosma getGosma() {
        return gosma;
    }

    @Override
    public void die(){
        freeze();
    }

    public void freeze(){
        String url = "src/images/monster" + monsterType + "bg.png";
        //!!!! resize image, fazer resize depois manualmente
        var ii = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(commons.MONSTER_WIDTH, commons.MONSTER_HEIGHT, Image.SCALE_SMOOTH));
        setImage(ii.getImage());

        setDying(true);
    }
}
