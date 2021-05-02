import io.github.pythonian23.physicsish.*;
import io.github.pythonian23.physicsish.Collision.Circle;
import io.github.pythonian23.physicsish.Object;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Main extends JPanel implements ActionListener {
    double prevTime;
    private int FPS;
    private int debug_x;
    private int debug_y;

    Physicsish physicsish;
    Circle mouseObj;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphic2d = (Graphics2D) g;
        debug_x = 5;
        debug_y = 20;

        debug("FPS: " + FPS, graphic2d);

        for (Object o: physicsish.getObjects()) {
            graphic2d.setColor(o.color);
            graphic2d.fillOval(
                    (int) (o.x * physicsish.M_TO_PX - o.radius * physicsish.M_TO_PX),
                    (int) (o.y * physicsish.M_TO_PX - o.radius * physicsish.M_TO_PX),
                    (int) (o.radius * 2 * physicsish.M_TO_PX), (int) (o.radius * 2 * physicsish.M_TO_PX)
            );
            debug("L: (" + (int) o.x + ", " + (int) o.y + ")", graphic2d);
            debug("V: (" + (int) o.vel_x + ", " + (int) o.vel_y + ")", graphic2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double newTime = System.currentTimeMillis();
        float delta = (float) (newTime - prevTime) / 1000;
        prevTime = newTime;

        Point mouse = getMousePosition();
        if (mouse != null) {
//            mouseObj.vel_x = (mouse.x / physicsish.M_TO_PX - mouseObj.x) / delta;
//            mouseObj.vel_y = (mouse.y / physicsish.M_TO_PX - mouseObj.y) / delta;

            mouseObj.x = (mouse.x / physicsish.M_TO_PX);
            mouseObj.y = (mouse.y / physicsish.M_TO_PX);
        }

        physicsish.tick(delta);
        for (Object o: physicsish.getObjects()) o.color = o.collided? Color.RED : Color.GREEN;
        FPS = (int) (1 / delta);
        repaint();
    }

    public Main() {
        JFrame frame = new JFrame("Demo");
        frame.add(this);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBackground(Color.black);

        prevTime = System.currentTimeMillis();

        physicsish = new Physicsish();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                Circle c = new Circle(1, 0.25f);
                c.x = 1 + i;
                c.y = 1 + j;
                c.GRAVITY = false;
                physicsish.addObject(c);
            }
        }
        mouseObj = new Circle(1, 1f);
        mouseObj.GRAVITY = false;
        physicsish.addObject(mouseObj);

        Timer timer = new Timer(10, this);
        timer.start();
    }

    private void debug(String text, Graphics2D graphic2d) {
        graphic2d.setColor(Color.GRAY);
        graphic2d.drawString(
                text,
                debug_x,
                debug_y
        );
        debug_y += 10;
        if (debug_y >= 720) {
            debug_x += 50;
            debug_y = 20;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}