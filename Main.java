package Kangaroo;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author Glandaizer
 */
public class Main extends Frame implements GLEventListener, MouseListener, MouseMotionListener
{
    //Window properties
    public static final int WIN_WIDTH = 800;
    public static final int WIN_HEIGHT = 600;
    public static final String WIN_TITLE = "3D Kangaroo";
    
    //Mouse rotation
    private float view_rotx = 0.0f;
    private float view_roty = 0.0f;
    private int oldMouseX;
    private int oldMouseY;  
    
    //Position of figure in the window
    private static final float X_POSITION = 0.0f;
    private static final float Y_POSITION = 0.5f;
    private static final float Z_POSITION = -5.0f;
    
    private Input key = new Input();
    boolean isJumping = false;
    Figure roger = null;
    
    public static void main(String[] args)
    {
        Window.createWindow(WIN_WIDTH, WIN_HEIGHT, WIN_TITLE);
    }
    
    public void init(GLAutoDrawable drawable) 
    {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());    
        // Enables VSync. Limit the frame rate to 60-75fps
        gl.setSwapInterval(1);

        // set up lighting
        float light_ambient[]={0.2f, 0.2f, 0.2f, 1.0f};
        float light_diffuse[]={0.3f, 0.3f, 0.3f, 1.0f};
        float light_specular[]={1.0f, 1.0f, 1.0f, 1.0f};
        float light_position[] = {1.0f,1.5f,1.0f,0.0f };
        
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);
        
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_NORMALIZE);
              
        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);       
        gl.glShadeModel(GL.GL_SMOOTH);
        
        key.setMain((this));
        drawable.addKeyListener(key);
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        
        roger = new Figure();
//        roger.draw_figure(gl);
    }

    public void display(GLAutoDrawable drawable) 
    {
        render(drawable);
        if(isJumping == true)
        {
            roger.updateAngles();
        }
        else
        {
            roger.resetAngles();
        }
    }
    
    public void render(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
   
        // Cleat the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);        
        gl.glLoadIdentity();
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(view_roty, 1.0f, 0.0f, 0.0f);
        
        //Draw figure      
        roger.draw_figure(gl);
        gl.glScalef(0.45f, 0.45f, 0.45f);
        gl.glTranslatef(0.0f, -1.45f, 0.7f);
        gl.glRotatef(30f, 1.0f, 0.0f, 0.0f);
        roger.draw_mini_figure(gl);
       
        gl.glFlush();
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) 
        {
            height = 1;
        }

        // keep ratio
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public void mousePressed(MouseEvent e) 
    {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e) 
    {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaX = 360.0f * ( (float)(x-oldMouseX)/(float)size.width);
        float thetaY = 360.0f * ( (float)(oldMouseY-y)/(float)size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }
    
    public void doJump(boolean jump)
    {
        isJumping = jump;
    }
    
    //Unused functions
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
      
}
