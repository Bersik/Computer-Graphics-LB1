/**
 * Created by Bersik on 16.03.2015.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.*;

public class Image extends JPanel{
    private BufferedImage imag;
    private Graphics2D graphics;
    private int sizePixelValue=1;

    public Image(int width,int height){
        super();
        setBounds(0,0,width,height);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        imag = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) imag.createGraphics();
        graphics .setColor(Color.white);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imag, 0, 0,this);
    }

    public void paintPixels(ArrayList<CPoint> points){
        for (CPoint cPoint: points){
            paintPixel(cPoint);
        }
        this.repaint();
    }

    public void test(){
        graphics.setColor(Color.BLACK);
        graphics.drawLine(0, 0, 100, 100);
        this.repaint();
    }

    public void test2(){
        graphics.setColor(Color.BLACK);
        graphics.drawLine(0, 0, 500, 2000);
        this.repaint();
    }

    public void clear(){
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.repaint();
    }

    private void paintPixel(CPoint point) {
        graphics.setColor(point.color);
        graphics.fillRect(point.point.x * sizePixelValue, point.point.y * sizePixelValue, sizePixelValue, sizePixelValue);
    }

    public void setSizePixelValue(int sizePixelValue) {
        this.sizePixelValue = sizePixelValue;
        paintGrid();
    }


    public void paintGrid() {
        if (sizePixelValue<15)
            return;
        int height=this.getHeight();
        int width = this.getWidth();
        int heightCount =  (height/sizePixelValue)+1; //висота
        int widthCount = (width/sizePixelValue)+1;
        clear();
        graphics.setColor(Color.BLACK);
        for(int i=0;i<=heightCount;i++){
            graphics.drawLine(0,i*sizePixelValue,height*sizePixelValue,i*sizePixelValue);
        }
        for(int i=0;i<=widthCount;i++){
            graphics.drawLine(i*sizePixelValue,0,i*sizePixelValue,width*sizePixelValue);
        }
    }

    public void fillRect(int x1, int y1, int x2, int y2,Color color) {
        graphics.setColor(color);
        graphics.fillRect(x1*sizePixelValue,y1*sizePixelValue,x2*sizePixelValue,y2*sizePixelValue);
    }
}
