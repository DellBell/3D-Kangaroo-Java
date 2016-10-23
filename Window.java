/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kangaroo;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCanvas;

/**
 *
 * @author Glandaizer
 */
public class Window 
{
    public static void createWindow(int width, int height, String title)
    {
        Frame frame = new Frame(title);
        GLCanvas canvas = new GLCanvas();
        Main m = new Main();
        canvas.addGLEventListener(m);
        frame.add(canvas);
        frame.setSize(width, height);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() 
                {
                    public void run() 
                    {

                        animator.stop();
                        System.exit(0);
                      
                    }
                }).start();
            }
        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start(); 
        //Focus on window at start up
        //canvas.requestFocus();
    }    
}
