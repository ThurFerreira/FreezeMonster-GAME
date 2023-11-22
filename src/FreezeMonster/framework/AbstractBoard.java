package FreezeMonster.framework;

import javax.swing.*;

import FreezeMonster.Settings;
import FreezeMonster.framework.FrameSettings;
import FreezeMonster.framework.BasicPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractBoard extends JPanel {

    private Dimension d;

    protected int deaths = 0;
    protected boolean inGame = true;
    protected String message = "Game Over";
    protected Timer timer;

    //funcao que cria entidades
    protected abstract void initBoard();

    //instancia o array de sprites, player, etc
    public void gameInit() { //AbstractBoard
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(FrameSettings.BOARD_WIDTH, FrameSettings.BOARD_HEIGHT);
        
        setBackground(Color.GREEN.darker());
        timer = new Timer(FrameSettings.DELAY, new GameCycle());
        timer.start();
    }
    
    //implementar "doDrawings" para desenhar cada sprite
    protected abstract void doDrawing(Graphics g);
    @Override //controla interface e chama doDrawing()
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

        if(inGame){
            System.out.println("still in game");
            doDrawing(g);
        }
        else{
            System.out.println("not in game on abstract board");
            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    

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


    //chamada pelo update e deve ser implementada
    protected abstract void update();

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
            ControlsKeyRealeased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ControlsKeyPressed(e);
        }
    }

    protected void ControlsKeyRealeased(KeyEvent e){};
    protected void ControlsKeyPressed(KeyEvent e){};
    
}

