package FreezeMonster;

import FreezeMonster.factory.EnemyFactory;
import FreezeMonster.sprite.Gosma;
import FreezeMonster.sprite.Monster;
import FreezeMonster.sprite.Player;
import FreezeMonster.sprite.Ray;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {
    public Commons commons = Commons.getInstance();
    private Dimension d;
    private List<Monster> monsters;
    private Player player;
    private Ray ray;
    EnemyFactory enemyFactory = new EnemyFactory();

    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;


    public Board() {

        initBoard();
        gameInit();
    }

    //!!comum em ambos jogos
    //ADD A KEY LISTENER, CREATE DIMENSION AND gameInit()
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(commons.BOARD_WIDTH, commons.BOARD_HEIGHT);
        setBackground(Color.getHSBColor(153, 55, 72));

        timer = new Timer(commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    //!!comum em ambos jogos
    //CREATES ENTITIES
    private void gameInit() {

        monsters = new ArrayList<>();
        
        for (int i = 1; i < 2; i++) {
            Monster monster = (Monster) enemyFactory.create("Monster");
            monsters.add(monster);
        }

        player = new Player();
        ray = new Ray();
    }


    private void drawAliens(Graphics g) {

        for (Monster monster : monsters) {

            if (monster.isVisible()) {

                g.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
            }

            if (monster.isDying()) {

                monster.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (ray.isVisible()) {

            g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {

        for (Monster a : monsters) {

            Gosma b = a.getGosma();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, commons.GROUND,
                    commons.BOARD_WIDTH, commons.GROUND);

            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, commons.BOARD_WIDTH, commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, commons.BOARD_WIDTH / 2 - 30, commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, commons.BOARD_WIDTH / 2 - 30, commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                commons.BOARD_WIDTH / 2);
    }

    private void update() {

        if (deaths == commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        player.act();

        // ray
        if (ray.isVisible()) {

            int shotX = ray.getX();
            int shotY = ray.getY();

            for (Monster monster : monsters) {

                int alienX = monster.getX();
                int alienY = monster.getY();

                if (monster.isVisible() && ray.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + commons.ALIEN_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        monster.setImage(ii.getImage());
                        monster.setDying(true);
                        deaths++;
                        ray.die();
                    }
                }
            }

            int y = ray.getY();
            y -= 4;

            if (y < 0) {
                ray.die();
            } else {
                ray.setY(y);
            }
        }

        // monsters

        for (Monster monster : monsters) {

            int x = monster.getX();

//            if (x >= commons.BOARD_WIDTH - commons.BORDER_RIGHT && direction != -1) {
//
//                direction = -1;
//
//                Iterator<Monster> i1 = monsters.iterator();
//
//                while (i1.hasNext()) {
//
//                    Monster a2 = i1.next();
//                    a2.setY(a2.getY() + commons.GO_DOWN);
//                }
//            }
//
//            if (x <= commons.BORDER_LEFT && direction != 1) {
//
//                direction = 1;
//
//                Iterator<Monster> i2 = monsters.iterator();
//
//                while (i2.hasNext()) {
//
//                    Monster a = i2.next();
//                    a.setY(a.getY() + commons.GO_DOWN);
//                }
//            }
        }

        Iterator<Monster> it = monsters.iterator();

        while (it.hasNext()) {

            Monster monster = it.next();

            //MOVIMENTACAO DOS MONSTROS
            var generator = new Random();
            int dx = generator.nextInt(3) - 1;
            dx *= 2;
            int dy = generator.nextInt(3) - 1;
            dy *= 2;

            if (monster.isVisible()) {
                monster.act(dx, dy);
            }
        }

        // bombs
        var generator = new Random();

        for (Monster monster : monsters) {

            int ray = generator.nextInt(15);
            Gosma gosma = monster.getGosma();

            if (ray == commons.CHANCE && monster.isVisible() && gosma.isDestroyed()) {

                gosma.setDestroyed(false);
                gosma.setX(monster.getX());
                gosma.setY(monster.getY());
            }

            int bombX = gosma.getX();
            int bombY = gosma.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !gosma.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + commons.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    gosma.setDestroyed(true);
                }
            }

            if (!gosma.isDestroyed()) {

                gosma.setY(gosma.getY() + 1);

                if (gosma.getY() >= commons.GROUND - commons.BOMB_HEIGHT) {

                    gosma.setDestroyed(true);
                }
            }
        }
    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!ray.isVisible()) {

                        ray = new Ray(x, y);
                    }
                }
            }
        }
    }
}

