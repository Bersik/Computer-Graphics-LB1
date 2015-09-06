import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Bersik on 16.03.2015.
 */
public class Performance {
    private static PrintWriter out=null;

    private static void test1(){
        int x1=0,y1=0,x2=400000,y2=300000;
        long start,end;
        double traceTime;
        out.format("Тест №1 - лінія (0,0,%d,%d):%n",x2,y2);

        start = System.nanoTime();
        Figure.lineDDA(x1, y1, x2, y2);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм DDA: %f с%n", traceTime);

        start = System.nanoTime();
        Figure.lineDDAnoSymmetric(x1, y1, x2, y2);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм DDA несиметричний: %f с%n", traceTime);

        start = System.nanoTime();
        Figure.lineBresenham(x1, y1, x2, y2);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм Брезенхейма: %f с%n", traceTime);

        start = System.nanoTime();
        Figure.lineBy(x1, y1, x2, y2);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм By: %f с%n", traceTime);
    }

    private static void test3(){
        int x=50000,y=50000,d=5000;
        long start,end;
        double traceTime;
        out.format("Тест №3 - %d ліній:%n",d*2);
        start = System.nanoTime();

        for (int i=-d;i<d;i++)
            Figure.lineDDA(x, y, x+d, y+i);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм DDA: %f с%n", traceTime);

        for (int i=-d;i<d;i++)
            Figure.lineDDAnoSymmetric(x, y, x + d, y + i);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм DDA несиметричний: %f с%n", traceTime);

        start = System.nanoTime();
        for (int i=-d;i<d;i++)
            Figure.lineBresenham(x, y, x + d, y + i);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм Брезенхейма: %f с%n", traceTime);

        start = System.nanoTime();
        for (int i=-d;i<d;i++)
            Figure.lineBy(x, y, x+d, y+i);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Алгоритм By: %f с%n", traceTime);
    }


    private static void test2(){
        int x=50000,y=50000,a=10000,b=14000;
        long start,end;
        double traceTime;
        out.format("Тест №2 - коло: R=%d; еліпс:висота=%d,ширина=%d:%n",a,a,b);
        start = System.nanoTime();
        Figure.circle(x, y, a);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        System.out.println(String.valueOf(traceTime));
        out.format("    Коло: %f с%n", traceTime);

        start = System.nanoTime();
        Figure.circle(x, y, a);
        end = System.nanoTime();
        traceTime = (end-start)/Math.pow(10,9);
        out.format("    Еліпс: %f с%n", traceTime);
    }

    public static void testPerformance() {
        String fname="test_performance.txt";
        File file = new File(fname);
        try{
            if(!file.exists()){
                file.createNewFile();
            }else {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out = new PrintWriter(file.getAbsoluteFile());

            test1();
            test2();
            test3();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (out!=null)
                out.close();
        }
    }
}
