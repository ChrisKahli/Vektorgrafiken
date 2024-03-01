import ch.aplu.util.*;
import ch.beattl.gm3d.*;
import ch.beattl.projektor.*;
import java.awt.event.KeyEvent;


/*
 * Berechnung des Spurpunktes:
 * P = Punkt auf dem 3D-Objekt
 * Z = Augpunkt
 * P' = Bildpunkt auf der Zeichenebene
 * 
 * P'x = (-Zc/Pz-Zc)*Px
 * P'y = (-Zc/Pz-Zc)*Py
 * P'z = (-Zc/Pz-Zc)*Pz
 * 
 */

public class Projektor3D{
    
    private GPanel Gp;
    private double viewPos = 5;
    
    private double[][] cube = new double[][]
        {
        {10,-10,10},
        {10,10,10},
        {-10,10,10},
        {-10,-10,10},
        {10,-10,-10},
        {10,10,-10},
        {-10,-10,-10},
        {-10,10,-10}
        };
    private FileReader fileReader = new FileReader("cube.obj");
    private Polyeder polyeder = new Polyeder(fileReader);
    
    
    public Projektor3D(){
        Gp = new GPanel(-20, 20, -20, 20);
    }
    
    
    public void main(){
        drawCube2d(0,0);
        
        
    }
    
    public void drawCube2d (double posX, double posY){
        Gp.move(posX, posY);
        Point[] cubePoints = new Point[8];
        
        for(int i = 0; i<cube.length; i++){
            cubePoints[i] = new Point(cube[i][0], cube[i][1], cube[i][2]);
        }
        
        for(int i = 0; i<cubePoints.length; i++){
            Gp.move(getX2d(cubePoints[i]), getY2d(cubePoints[i]));
            Gp.fillCircle(0.2);
            
        }
        
    }
    
    public void drawPolyeder2d (double posX, double posY){
        Gp.move(posX, posY);
        Point[] polyederPoints = new Point[polyeder.getNumOfVertices()];
        
        for(int i = 0; i<polyeder.getNumOfVertices(); i++){
            polyederPoints[i] = polyeder.getVertex(i);
        }
        
        for(Point p: polyederPoints){
            Gp.move(getX2d(p), getY2d(p));
            Gp.fillCircle(0.2);
        }
    }
    
    
    // method for adjusting the view position (viewPos) using the keyboard
    public void keyboardControls(KeyEvent e){
        switch(e.getKeyCode()){
            case 87: //W
                viewPos += 1;
                break;
            case 83: //W
                viewPos -= 1;
                break;
            default:
                break;
        }
        
    }
    
    
    //some methods to use for calculations, not really for external use
    private double getX2d(Point p){
        return -viewPos / (p.z - viewPos) * p.x;
    }
    
    private double getY2d(Point p){
        return -viewPos / (p.z - viewPos) * p.y;
    }
    
    
}