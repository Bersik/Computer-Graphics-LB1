import java.awt.*;

/**
 * Created by Bersik on 16.03.2015.
 */
public class CPoint{
    public Point point;
    public Color color;
    public CPoint(Point point,Color color){
        this.point=point;
        this.color=color;
    }

    public CPoint(Point point){
        this.point=point;
        this.color=Color.BLACK;
    }
}

