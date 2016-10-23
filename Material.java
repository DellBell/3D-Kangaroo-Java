/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kangaroo;

import javax.media.opengl.GL;

/**
 *
 * @author Glandaizer
 */
public class Material 
{
    public void set_brown_mat(GL gl)
    {
        float mat_ambient[]={0.45f, 0.25f, 0.04f, 1.0f};
        float mat_diffuse[]={0.8f, 0.5f, 0.06f, 1.0f};
        float mat_specular[]={0.8f, 0.8f, 0.8f, 1.0f};
        float shine=120f;       
                
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_AMBIENT,mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_DIFFUSE,mat_diffuse,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_SPECULAR,mat_specular,0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,GL.GL_SHININESS,shine);
    }
    public void set_white_mat(GL gl)
    {
        float mat_ambient[]={1f, 1f, 1f, 1.0f};
        float mat_diffuse[]={1f, 1f, 1f, 1.0f};
        float mat_specular[]={0.8f, 0.8f, 0.8f, 1.0f};
        float shine=120f;       
                
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_AMBIENT,mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_DIFFUSE,mat_diffuse,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_SPECULAR,mat_specular,0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,GL.GL_SHININESS,shine);
    }
    
    public void set_red_mat (GL gl)
    {
        float[] mat_ambient ={ 0.8f,0.05f,0.15f,0.2f };
        float[] mat_diffuse ={ 0.1f,0.3f,0.2f,1.0f};
        float[] mat_specular ={0.8f,0.8f,0.6f,1.0f };
        float shine = 125.0f ;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_AMBIENT,mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_DIFFUSE,mat_diffuse,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_SPECULAR,mat_specular,0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,GL.GL_SHININESS,shine);      
    }
    
    public void set_blue_mat (GL gl)
    {
        float mat_ambient[]={0.2f,0.2f,0.6f,1.0f};
        float mat_diffuse[]={1.0f,1.0f,1.0f,1.0f};
        float mat_specular[]={0.8f,0.8f,0.8f,1.0f};
        float shine=125.2f;    
                
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_AMBIENT,mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_DIFFUSE,mat_diffuse,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_SPECULAR,mat_specular,0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,GL.GL_SHININESS,shine);

    }
    
    public void set_black_mat(GL gl)
    {
        float mat_ambient[]={0.0f,0.0f,0.0f,1.0f};
        float mat_diffuse[]={1.0f,1.0f,1.0f,1.0f};
        float mat_specular[]={0.8f,0.8f,0.8f,1.0f};
        float shine=128f;    
                
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_AMBIENT,mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_DIFFUSE,mat_diffuse,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_SPECULAR,mat_specular,0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,GL.GL_SHININESS,shine);
    }
    
    public void set_grey_mat (GL gl)
    {        
        float mat_ambient[]={0.7f, 0.6f, 0.5f, 1.0f};
        float mat_diffuse[]={0.9f, 0.9f, 0.9f, 1.0f};
        float mat_specular[]={0.8f ,0.8f, 0.8f, 1.0f};
        float shine=128f;    
                
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_AMBIENT,mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_DIFFUSE,mat_diffuse,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL.GL_SPECULAR,mat_specular,0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,GL.GL_SHININESS,shine);      
    }
    
}
