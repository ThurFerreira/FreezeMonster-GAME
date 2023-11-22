package FreezeMonster.framework;

import javax.swing.*;

import FreezeMonster.Settings;
import FreezeMonster.framework.FrameSettings;
import FreezeMonster.framework.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractBoard extends JPanel {

    private Dimension d;
    private List<Sprite> sprites;
    private Player player;

    private int deaths = 0;
    private boolean inGame = true;
    private String message = "Game Over";
    private Timer timer;

    //funcao que cria configs iniciais e chama gameInit()
    public void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(FrameSettings.BOARD_WIDTH, FrameSettings.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(FrameSettings.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    //instancia o array de sprites, player, etc
    public abstract void gameInit();

    @Override //controla interface e chama doDrawing()
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

        if(inGame){
            doDrawing(g);
        }
        else{
            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    //implementar "doDrawings" para desenhar cada sprite
    protected abstract void doDrawing(Graphics g);
    

    private void gameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, d.width / 2 - 30, d.width - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, d.width / 2 - 30, d.width - 100, 50);
        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (d.width - fontMetrics.stringWidth(message)) / 2, d.width / 2);
    }

    private void update() {
        gameLogic();
    }

    //chamada pelo update e deve ser implementada
    protected abstract void gameLogic();

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

            outrosListenersEventosKeyRealeased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
            
            outrosListenersEventosKeyPressed(e);
        }
    }

    private void outrosListenersEventosKeyRealeased(KeyEvent e){};
    private void outrosListenersEventosKeyPressed(KeyEvent e){};
    
}

