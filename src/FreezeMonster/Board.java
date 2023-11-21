package FreezeMonster;

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

    private Dimension d;
    private List<Monster> monsters;
    private Player player;
    private Ray ray;

    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;


    public Board() {

        initBoard();
        gameInit();
    }

    //ADD A KEY LISTENER, CREATE DIMENSION AND gameInit()
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.getHSBColor(153, 55, 72));

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    //CREATES ENTITIES
    private void gameInit() {

        monsters = new ArrayList<>();

        String urlMonsterImg = "src/images/monster";

        for (int i = 0; i < Commons.NUMBER_OF_MONSTERS_TO_DESTROY; i++) {
            var monster = new Monster(Commons.MONSTER_INIT_X + 18, Commons.MONSTER_INIT_Y + 18, (urlMonsterImg + (i+1) + ".png"));
            monsters.add(monster);
        }

        player = new Player();
        ray = new Ray();
        ray.die();
    }

    //plota os monstros
    private void drawMonsters(Graphics g) {

        for (Monster monster : monsters) {

            if (monster.isVisible()) {

                g.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
            }

            //!!!!Alterar como o monstro morre (deve ficar paralizado com outra imagem congelado)
            if (monster.isDying()) {

                monster.die();
            }
        }
    }

    //desenha jogador
    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            inGame = false;
        }
    }

    private void drawRay(Graphics g) {

        if (ray.isVisible()) {

            g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
        }
    }

    private void drawGosma(Graphics g) {

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

    //desenha todas as entidades
    private void doDrawing(Graphics g) {


        if (inGame) {

            drawMonsters(g);
            drawPlayer(g);
            drawRay(g);
            drawGosma(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    //tela de game over
    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.BOARD_WIDTH / 2);
    }

    //metodo que atualiza as entidades, variaveis  e verifica colisoes
    private void update() {

        if (deaths == Commons.NUMBER_OF_MONSTERS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        player.act();

        // ray
        if (ray.isVisible()) {

            int rayX = ray.getX();
            int rayY = ray.getY();

            //colisao raio com monstros
            for (Monster monster : monsters) {

                int monsterX = monster.getX();
                int monsterY = monster.getY();

                if (monster.isVisible() && !monster.isDying() && ray.isVisible()) {
                    if (rayX >= (monsterX)
                            && rayX <= (monsterX + Commons.MONSTER_WIDTH)
                            && rayY >= (monsterY)
                            && rayY <= (monsterY + Commons.MONSTER_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        monster.setImage(ii.getImage());
                        monster.setDying(true);
                        deaths++;
                        ray.die();
                    }
                }
            }

            //move o raio

            if (rayY < 0 || rayY > Commons.BOARD_HEIGHT || rayX < 0 || rayX > Commons.BOARD_WIDTH) {
                ray.die();
            } else {
                ray.move();
            }
        }

        //mantem os monstros dentro do board
        for (Monster monster : monsters) {

            int monsterX = monster.getX();
            int monsterY = monster.getY();

            if (monsterX >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
                monster.setX(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT);
            }

            if (monsterX <= Commons.BORDER_LEFT) {
                monster.setX(Commons.BORDER_LEFT);
            }

            if (monsterY >= Commons.GROUND) {
                monster.setY(Commons.GROUND);
            }

            if (monsterY <= 5) {
                monster.setY(5);
            }
        }

        //movimentacao dos monstros
        Iterator<Monster> it = monsters.iterator();
        while (it.hasNext()) {

            Monster monster = it.next();

            var generator = new Random();

            int dx = generator.nextInt(3) - 1;
            dx *= 1;
            int dy = generator.nextInt(3) - 1;
            dy *= 1;

            if (monster.isVisible()) {
                monster.act(dx, dy);
            }

            if (monster.isVisible() && !monster.isDying()) {
                monster.act(dx, dy);
            }
        }

        //gosmas
        var generator = new Random();

        for (Monster monster : monsters) {

            Gosma gosma = monster.getGosma();

            //inicia gosma na posicao do monstro se estiver destruida
            if (monster.isVisible() && gosma.isDestroyed()) {

                gosma.setX(monster.getX());
                gosma.setY(monster.getY());
                gosma.setDestroyed(false);
            }

            //pega coordenada da gosma
            int gosmaX = gosma.getX();
            int gosmaY = gosma.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            //colisao player com gosma
            if (player.isVisible() && !gosma.isDestroyed()) {

                if (gosmaX >= (playerX)
                        && gosmaX <= (playerX + Commons.PLAYER_WIDTH)
                        && gosmaY >= (playerY)
                        && gosmaY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    gosma.setDestroyed(true);
                }
            }

            //movimenta a gosma e verifica se saiu do quadro para destrui-la
            if (!gosma.isDestroyed()) {

                gosma.mover();

                if (gosma.getX() >= (Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) ||
                        gosma.getX() <= Commons.BORDER_LEFT ||
                        gosma.getY() >= Commons.GROUND ||
                        gosma.getY() <= 5) {
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
            //efetuando o disparo do raio
            if (key == KeyEvent.VK_SPACE) {
                System.out.println("apertou espaço");
                if (inGame) {

                    if (!ray.isVisible()) {

                        ray = new Ray(x, y, player.getxLastMove(), player.getyLastMove());
                    }
                }
            }
        }
    }
}

