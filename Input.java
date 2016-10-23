package Kangaroo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Glandaizer
 */

public class Input implements KeyListener
{
//    boolean[] Keys = new boolean[256];
    Main j = null;
 
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) 
        {
            j.doJump(true);
        }
        else if (keyCode == KeyEvent.VK_DOWN) 
        {
            j.doJump(false);
        }
        
        if (keyCode == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
    }
    
    //Ungå å lage dobbel/annen instanse av Main! (Window class)
    public void setMain(Main m) { j = m; }
    
    public void keyReleased(KeyEvent e){} 
    public void keyTyped(KeyEvent e){}

}
