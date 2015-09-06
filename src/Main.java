import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

/**
 * Created by Bersik on 15.03.2015.
 */

public final class Main {
    private static int sizeWidth = 1000;
    private static int sizeHeight = 660;
    private Form form;
    private int x1,x2,y1,y2,x0,y0,R,a,b;
    private Image img;
    private int sizePixelValue=1;
    private JTextField sizePixel,textX1,textX2,textY1,textY2,textX0,textY0,textA,textB,textR;
    private JLabel maxX,maxY;
    private int MaxX,MaxY;

    private void preparationLine(){
        x1=Integer.parseInt(textX1.getText());
        x2=Integer.parseInt(textX2.getText());
        y1=Integer.parseInt(textY1.getText());
        y2=Integer.parseInt(textY2.getText());
        img.clear();
        img.paintGrid();
    }

    public Main(){
        form = new Form(sizeWidth,sizeHeight);

        img = new Image(sizeWidth-200,sizeHeight-29);
        img.paintGrid();
        form.add(img);

        JLabel labelSizePixel = new JLabel("Розмір пікселя (n*n): ");
        labelSizePixel.setBounds(sizeWidth-190,5,150,20);
        form.add(labelSizePixel);

        sizePixel = new JTextField();
        sizePixel.setText("1");
        sizePixel.setBounds(sizeWidth - 60, 5, 40, 20);
        sizePixel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                updateScale();
            }
        });
        form.add(sizePixel);

        JButton drawDDALine = new JButton("DDA Line");
        drawDDALine.setBounds(sizeWidth - 180, 40, 140, 30);
        drawDDALine.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                preparationLine();
                ArrayList<CPoint> arrays =  Figure.lineDDA(x1, y1, x2, y2);
                img.paintPixels(arrays);
            }
        });
        form.add(drawDDALine);

        JButton drawDDALinenoSymmetric = new JButton("DDA Line No Symmetric");
        drawDDALinenoSymmetric.setBounds(sizeWidth - 180, 80, 140, 30);
        drawDDALinenoSymmetric.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                preparationLine();
                ArrayList<CPoint> arrays =  Figure.lineDDAnoSymmetric(x1, y1, x2, y2);
                img.paintPixels(arrays);
            }
        });
        form.add(drawDDALinenoSymmetric);

        JButton drawBresenhamLine = new JButton("Bresenham Line");
        drawBresenhamLine.setBounds(sizeWidth - 180, 120, 140, 30);
        drawBresenhamLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                preparationLine();
                ArrayList<CPoint> arrays =  Figure.lineBresenham(x1, y1, x2, y2);
                img.paintPixels(arrays);
            }
        });
        form.add(drawBresenhamLine);

        JButton drawByLine = new JButton("By Line");
        drawByLine.setBounds(sizeWidth - 180, 160, 140, 30);
        drawByLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                preparationLine();
                ArrayList<CPoint> arrays =  Figure.lineBy(x1, y1, x2, y2);
                img.paintPixels(arrays);
            }
        });
        form.add(drawByLine);

        JLabel label = new JLabel("x1");
        label.setBounds(sizeWidth - 180, 200, 20, 20);
        form.add(label);
         textX1 = new JTextField();
        textX1.setText("1");
        textX1.setBounds(sizeWidth - 160, 200, 40, 20);
        form.add(textX1);
        label = new JLabel("y1");
        label.setBounds(sizeWidth - 100, 200, 20, 20);
        form.add(label);
         textY1 = new JTextField();
        textY1.setText("1");
        textY1.setBounds(sizeWidth - 80, 200, 40, 20);
        form.add(textY1);

        label = new JLabel("x2");
        label.setBounds(sizeWidth - 180, 230, 20, 20);
        form.add(label);
        textX2 = new JTextField();
        textX2.setText("100");
        textX2.setBounds(sizeWidth - 160, 230, 40, 20);
        form.add(textX2);
        label = new JLabel("y2");
        label.setBounds(sizeWidth - 100, 230, 20, 20);
        form.add(label);
        textY2 = new JTextField();
        textY2.setText("100");
        textY2.setBounds(sizeWidth - 80, 230, 40, 20);
        form.add(textY2);

        JButton drawCirlce = new JButton("Коло");
        drawCirlce.setBounds(sizeWidth - 180, 280, 140, 30);
        drawCirlce.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                R=Integer.parseInt(textR.getText());
                x0=Integer.parseInt(textX0.getText());
                y0=Integer.parseInt(textY0.getText());
                img.clear();
                ArrayList<CPoint> arrays =  Figure.circle(x0, y0, R);
                img.paintPixels(arrays);
            }
        });
        form.add(drawCirlce);

        JButton drawEllipse = new JButton("Еліпс");
        drawEllipse.setBounds(sizeWidth - 180, 350, 140, 30);
        drawEllipse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                a=Integer.parseInt(textA.getText());
                b=Integer.parseInt(textB.getText());
                x0=Integer.parseInt(textX0.getText());
                y0=Integer.parseInt(textY0.getText());
                img.clear();
                ArrayList<CPoint> arrays =  Figure.ellipse(x0, y0, a, b);
                img.paintPixels(arrays);
            }
        });
        form.add(drawEllipse);

        label = new JLabel("x0");
        label.setBounds(sizeWidth - 180, 420, 20, 20);
        form.add(label);
         textX0 = new JTextField();
        textX0.setText("100");
        textX0.setBounds(sizeWidth - 160, 420, 40, 20);
        form.add(textX0);
        label = new JLabel("y0");
        label.setBounds(sizeWidth - 100, 420, 20, 20);
        form.add(label);
         textY0 = new JTextField();
        textY0.setText("100");
        textY0.setBounds(sizeWidth - 80, 420, 40, 20);
        form.add(textY0);

        label = new JLabel("R");
        label.setBounds(sizeWidth - 140, 320, 20, 20);
        form.add(label);
         textR = new JTextField();
        textR.setText("100");
        textR.setBounds(sizeWidth - 120, 320, 40, 20);
        form.add(textR);

        label = new JLabel("a");
        label.setBounds(sizeWidth - 180, 390, 20, 20);
        form.add(label);
        textA = new JTextField();
        textA.setText("100");
        textA.setBounds(sizeWidth - 160, 390, 40, 20);
        form.add(textA);
        label = new JLabel("b");
        label.setBounds(sizeWidth - 100, 390, 20, 20);
        form.add(label);
        textB = new JTextField();
        textB.setText("100");
        textB.setBounds(sizeWidth - 80, 390, 40, 20);
        form.add(textB);

        JButton speed = new JButton("Швидкодія");
        speed.setBounds(sizeWidth - 180,sizeHeight - 200, 140, 30);
        speed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Performance.testPerformance();
            }
        });
        form.add(speed);

        JButton drawInitials = new JButton("Ініціали");
        drawInitials.setBounds(sizeWidth - 180,sizeHeight - 160, 140, 30);
        drawInitials.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                img.clear();
                sizePixel.setText("3");
                updateScale();
                int x=5;
                int y=100;
                int dx=50;
                int dy=80;
                int x2=110;
                int x3=180;
                img.paintPixels(Figure.lineBy(x, y - dy, x, y + dy));
                img.paintPixels(Figure.lineBy(x, y, x + dx, y + dy));
                img.paintPixels(Figure.lineBy(x, y, x + dx, y - dy));



                img.paintPixels(Figure.ellipse(x3, y - 37,57 ,37 ));
                img.paintPixels(Figure.ellipse(x3, y + 40,60 ,40 ));
                img.fillRect(x3-70,y-150,70,300,Color.WHITE);
                img.paintPixels(Figure.lineBresenham(x3, y -37*2, x3, y+40*2));

                img.paintPixels(Figure.ellipse(x2, y, dx, dy));
                img.fillRect(x2,y-50,70,y,Color.WHITE);

            }
        });
        form.add(drawInitials);

        JButton clear = new JButton("Очистити");
        clear.setBounds(sizeWidth - 180,sizeHeight - 120, 140, 30);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                img.clear();
            }
        });
        form.add(clear);

        JButton test = new JButton("Тест");
        test.setBounds(sizeWidth - 180,sizeHeight - 80, 140, 30);
        test.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                img.clear();
                sizePixel.setText("4");
                updateScale();
                img.paintPixels(Figure.lineDDA(5,5,50,45));
                img.paintPixels(Figure.lineDDAnoSymmetric(50,5,95,45));
                img.paintPixels(Figure.lineBresenham(95,5,140,45));
                img.paintPixels(Figure.lineBy(140, 5, 185, 45));
                img.paintPixels(Figure.circle(50,100,30));
                img.paintPixels(Figure.ellipse(130,100,40,20));
            }
        });
        form.add(test);

        maxX = new JLabel();
        maxX.setBounds(sizeWidth - 180, sizeHeight - 50, 80, 20);
        updateMaxX();
        form.add(maxX);

        maxY = new JLabel();
        maxY.setBounds(sizeWidth - 100, sizeHeight - 50, 80, 20);
        updateMaxY();
        form.add(maxY);
    }

    private int getMaxX(){
        return (img.getWidth()/sizePixelValue)-1;
    }

    private int getMaxY(){
        return (img.getHeight()/sizePixelValue)-1;
    }

    private void updateMaxX(){
        maxX.setText("Max X: "+String.valueOf(getMaxX()));
    }

    private void updateMaxY(){
        maxY.setText("Max Y: "+String.valueOf(getMaxY()));
    }

    private void updateScale(){
        sizePixelValue = Integer.parseInt(sizePixel.getText());
        img.setSizePixelValue(sizePixelValue);
        updateMaxX();
        updateMaxY();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                new  Main();
            }
        });
    }
}
