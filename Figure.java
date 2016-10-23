/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kangaroo;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author Glandaizer
 */
public class Figure 
{
    private Material m = new Material();
    private GLUquadric q = null;
    
    // static dimensions
    public static final float BASE = 1.0f;
    
    private static final float ARM_LENGTH = BASE * 0.45f;
    private static final float ARM_RADIUS = BASE * 0.04f;
    private static final float BACK_RADIUS = BASE * 0.5f;
    private static final float BODY_LENGTH = BASE * 1.25f; 
    private static final float BODY_BOTTOM = BASE * 0.4f;
    private static final float BODY_TOP = BASE * 0.21f;
    private static final float EYE_RADIUS = BASE * 0.035f;
    private static final float HEAD_RADIUS = BASE * 0.16f;
    private static final float LEG_LENGTH = BASE * 0.65f;
    private static final float LEG_RADIUS = BASE * 0.055f;
    private static final float LEG_HEEL = LEG_RADIUS;
    private static final float MOUTH_LENGTH = BASE * 0.09f;
    private static final float MOUTH_BACK = BASE * 0.145f;
    private static final float MOUTH_FRONT = BASE * 0.075f; 
    private static final float POUCH_LENGTH = BASE * 0.38f;
    private static final float POUCH_BOTTOM = BASE * 0.16f;
    private static final float POUCH_TOP = BASE * 0.075f; 
    private static final float TAIL_LENGTH_UPPER = BASE * 0.55f;
    private static final float TAIL_LENGTH_LOWER = BASE * 0.5f;
    private static final float TAIL_BOTTOM_UPPER = BASE * 0.1f;
    private static final float TAIL_BOTTOM_LOWER = BASE * 0.05f;
    private static final float TAIL_TOP_UPPER = BASE * 0.2f;
    private static final float TAIL_TOP_LOWER = BASE * 0.1f;
    private static final float TAIL_JOINT = BASE * 0.1f;
    private static final float TAIL_END = BASE * 0.05f;
    
    //Super ellipsoid
    private static final double SE_X = 0.35;
    private static final double SE_Y = 0.2;
    private static final double SE_Z = 0.16;
    private static final int SE_E = 30;
    private static final double SE_EXP1 = 2.5;
    private static final double SE_EXP2 = 2.5;   
    
    private static final int SLICES = 30;
    private static final int STACKS = 10;
    // eof static dimensions
    
    //Movement angles
    private static final int[] PAD_V = {0,10,20,30,40,35,30,25,20,15,10,5,
                                        0,-5,-10,-15,-20,-23,-26,-29,-32,
                                        -36,-40,-30,-20,-10,0};
    
    private static final int[] KNEE_V = {0,-10,-20,-30,-40,-35,-30,-25,-20,
                                         -15,-10,-5,0,5,10,15,20,23,26,29,32,
                                         36,40, 30, 20, 10,0};
    
    private static final int[] HIP_V = {0,10,20,30,40,35,30,25,20,15,10,5,
                                        0,-5,-10,-15,-20,-23,-26,-29,-32,
                                        -36,-40,-30,-20,-10,0};
    
//    private static final int[] TAIL_V = {-10,-19,-20,-19,-19,-19,-18,-15,-10,-5,0,10,19,20,19,19,19,18,15,10,5,0};
    
    private int movePos = 0;
//    private int moveTail = 0;
//    private int[] tail = {0};
    private int[] leg = {0,0,0};    
    
    //For clippings
    private double eqn0[] =  {0.0, 1.0, 0.0, 0.0}; // Around neck
    private double eqn1[] =  {0.0, 0.0, 1.0, -0.21}; // Front side
    private double eqn2[] =  {0.0, 0.0, 1.0, 0.0}; //Pouch
    private double eqn3[] =  {0.0, -1.0, 0.0, -0.3}; //Butt
    private double eqn4[] =  {-1.0, -1.0, 1.0, -0.05}; //Ears
    
    public Figure()
    {
        resetAngles();
    }
    
    public void draw_figure(GL gl)
    {
        //Origo in bodys center. HOW?
        GLU glu = new GLU();
        q = glu.gluNewQuadric(); 
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        //glu.gluQuadricDrawStyle(q, GLU.GLU_SILHOUETTE);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);
        
        draw_body(gl, glu);
        draw_head(gl,glu);
        draw_ears(gl, glu);
        draw_arm_left(gl, glu);
        draw_arm_right(gl, glu);
        draw_back(gl, glu);
        draw_leg_right(gl, glu, leg);
        draw_leg_left(gl, glu, leg);      
        draw_tail(gl,glu);
    }
    
    public void draw_mini_figure(GL gl)
    {        
        GLU glu = new GLU();
        q = glu.gluNewQuadric(); 
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);
        
        draw_body(gl, glu);
        draw_head(gl,glu);
        draw_ears(gl, glu);
        draw_mini_arm_left(gl, glu);
        draw_mini_arm_right(gl, glu);
    }
    
    public void draw_head(GL gl, GLU glu)
    {
        m.set_brown_mat(gl);
        gl.glPushMatrix();
        
        //Neck
        gl.glTranslatef(0.0f, 0.07f, -0.005f);
        gl.glRotatef(-110f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, 0.18f, 0.1f, 0.23f, SLICES, STACKS);
        
        //Head
        gl.glRotatef(110f, 1.0f, 0.0f, 0.0f);
        gl.glTranslatef(0.0f, 0.22f, -0.01f);
        glu.gluSphere(q, HEAD_RADIUS, SLICES, STACKS);
        gl.glRotatef(-10f, 1.0f, 0.0f, 0.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.074f);
        glu.gluCylinder(q, MOUTH_BACK, MOUTH_FRONT, MOUTH_LENGTH, SLICES, STACKS); 
        
        //Left eye
        m.set_black_mat(gl);
        gl.glTranslatef(0.08f, 0.04f, 0.03f);
        glu.gluSphere(q, EYE_RADIUS, SLICES, STACKS);
        
        //Right eye
        gl.glTranslatef(-0.16f, 0.0f, 0.0f);
        glu.gluSphere(q, EYE_RADIUS, SLICES, STACKS);
        
        //Mouth
        m.set_brown_mat(gl);
        gl.glTranslatef(0.08f, -0.04f, MOUTH_LENGTH-0.03f);
        superEllipsoid(gl, 0.1, MOUTH_FRONT, 0.15, SE_E, SE_EXP1-1, SE_EXP2-0.2);
        
        //Nose
        m.set_black_mat(gl);
        gl.glTranslatef(0.0f, 0.02f, 0.13f);
        superEllipsoid(gl, 0.03, 0.021, 0.026, SE_E, SE_EXP1, SE_EXP2-1);
        
        gl.glPopMatrix();
    }
    
    public void draw_ears(GL gl, GLU glu)
    {
        m.set_brown_mat(gl);     
        //Right
        gl.glPushMatrix();
        gl.glTranslatef(-0.2f, 0.475f, -0.09f);
        gl.glRotatef(-120f, 0.0f, 1.0f, 0.0f); //y
        gl.glRotatef(40f, 1.0f, 0.0f, -1.0f); //x
        gl.glClipPlane(GL.GL_CLIP_PLANE0, eqn4, 0);
        gl.glEnable(GL.GL_CLIP_PLANE0);
        superEllipsoid(gl, SE_X-0.26, SE_Y+0.1, SE_Z-0.109, SE_E, SE_EXP1-1, SE_EXP2);
        gl.glDisable(GL.GL_CLIP_PLANE0);
        gl.glPopMatrix();
        
        //Left
        gl.glPushMatrix();
        gl.glScalef(-1.0f, 1.0f, 1.0f); //Mirror x
        gl.glTranslatef(-0.2f, 0.475f, -0.09f);
        gl.glRotatef(-120f, 0.0f, 1.0f, 0.0f); //y
        gl.glRotatef(40f, 1.0f, 0.0f, -1.0f); //x     
        gl.glClipPlane(GL.GL_CLIP_PLANE0, eqn4, 0);
        gl.glEnable(GL.GL_CLIP_PLANE0);
        superEllipsoid(gl, SE_X-0.26, 0.3, 0.051, 30, SE_EXP1-1, SE_EXP2);
        gl.glDisable(GL.GL_CLIP_PLANE0);
        gl.glPopMatrix();   
    }
    
    public void draw_body(GL gl, GLU glu)
    {        
        //Create body
        m.set_brown_mat(gl);   
        gl.glPushMatrix();
        gl.glClipPlane(GL.GL_CLIP_PLANE0, eqn0, 0);
        gl.glEnable(GL.GL_CLIP_PLANE0);
        glu.gluSphere(q, BODY_TOP+0.001f, SLICES, STACKS);
        gl.glDisable(GL.GL_CLIP_PLANE0);
        gl.glTranslatef(0.0f, 0.02f, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(q, BODY_TOP, BODY_BOTTOM, BODY_LENGTH, SLICES, STACKS);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glTranslatef(0.0f, -BODY_LENGTH, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, BODY_BOTTOM, SLICES, STACKS);
        gl.glPopMatrix();
        
        //Create front body
        m.set_grey_mat(gl);     
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.02f, 0.006f);
        
        //Clip
        gl.glClipPlane(GL.GL_CLIP_PLANE0, eqn1, 0);
        gl.glEnable(GL.GL_CLIP_PLANE0);
        
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, BODY_TOP, BODY_BOTTOM, BODY_LENGTH, SLICES, STACKS);         
        gl.glPopMatrix();
        
        //Create pouch
        m.set_grey_mat(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -1.15f, 0.25f);
        gl.glClipPlane(GL.GL_CLIP_PLANE0, eqn2, 0);
        gl.glEnable(GL.GL_CLIP_PLANE0);
        gl.glRotatef(90f, -1f, 0f, 0f);
        glu.gluCylinder(q, POUCH_TOP, POUCH_BOTTOM, POUCH_LENGTH, SLICES, STACKS);
        glu.gluDisk(q, POUCH_TOP, 0, SLICES, STACKS);
        
        //Done clipping
        gl.glDisable(GL.GL_CLIP_PLANE0);
        
        gl.glPopMatrix();
    }
    
    public void draw_arm_left(GL gl, GLU glu)
    {
        m.set_brown_mat(gl);
        gl.glPushMatrix();
        
        //Upper arm
        gl.glTranslatef(0.2f, -0.076f, 0.2f);
        gl.glRotatef(20f, 1.0f, 1.0f, 0.0f);
        superEllipsoid(gl, (SE_X-0.27), (SE_Y-0.1), (SE_Z+0.09), SE_E, SE_EXP1, SE_EXP2-0.5);
        
        //Lower arm
        gl.glTranslatef(0.0f, 0.0f, 0.2f);
        gl.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, ARM_RADIUS, ARM_RADIUS, ARM_LENGTH, SLICES, STACKS);    
        
        //Glove 
        gl.glTranslatef(0.0f, 0.0f, 0.4f);
        gl.glRotatef(-40f, 0.0f, 0.0f, 1.0f);
        draw_glove_red(gl, glu);
        gl.glPopMatrix();
    }
    
    public void draw_arm_right(GL gl, GLU glu)
    {
        m.set_brown_mat(gl);
        gl.glPushMatrix();
        
        //Upper arm
        gl.glTranslatef(-0.2f, -0.076f, 0.2f);
        gl.glRotatef(20f, 1.0f, -1.0f, 0.0f);
        superEllipsoid(gl, (SE_X-0.27), (SE_Y-0.1), (SE_Z+0.09), SE_E, SE_EXP1, SE_EXP2-0.5);
        
        //Lower arm
        gl.glTranslatef(0.0f, 0.0f, 0.2f);
        gl.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, ARM_RADIUS, ARM_RADIUS, ARM_LENGTH, SLICES, STACKS);   
        
        //Glove 
        gl.glTranslatef(0.0f, 0.0f, 0.4f);
        gl.glRotatef(-130f, 0.0f, 0.0f, 1.0f);
        draw_glove_red(gl, glu);
        gl.glPopMatrix();
    }
    
    public void draw_mini_arm_left(GL gl, GLU glu)
    {
        m.set_brown_mat(gl);
        gl.glPushMatrix();
        
        //Upper arm
        gl.glTranslatef(0.2f, -0.076f, 0.2f);
        gl.glRotatef(20f, 1.0f, 1.0f, 0.0f);
        superEllipsoid(gl, (SE_X-0.27), (SE_Y-0.1), (SE_Z+0.09), SE_E, SE_EXP1, SE_EXP2-0.5);
        
        //Lower arm
        gl.glTranslatef(0.0f, 0.0f, 0.2f);
        gl.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, ARM_RADIUS, ARM_RADIUS, ARM_LENGTH, SLICES, STACKS);    
        
        //Glove 
        gl.glTranslatef(0.0f, 0.0f, 0.4f);
        gl.glRotatef(-40f, 0.0f, 0.0f, 1.0f);
        draw_glove_blue(gl, glu);
        gl.glPopMatrix();
    }
    
    public void draw_mini_arm_right(GL gl, GLU glu)
    {
        m.set_brown_mat(gl);
        gl.glPushMatrix();
        
        //Upper arm
        gl.glTranslatef(-0.2f, -0.076f, 0.2f);
        gl.glRotatef(20f, 1.0f, -1.0f, 0.0f);
        superEllipsoid(gl, (SE_X-0.27), (SE_Y-0.1), (SE_Z+0.09), SE_E, SE_EXP1, SE_EXP2-0.5);
        
        //Lower arm
        gl.glTranslatef(0.0f, 0.0f, 0.2f);
        gl.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, ARM_RADIUS, ARM_RADIUS, ARM_LENGTH, SLICES, STACKS);  
        
        //Glove 
        gl.glTranslatef(0.0f, 0.0f, 0.4f);
        gl.glRotatef(-130f, 0.0f, 0.0f, 1.0f);
        draw_glove_blue(gl, glu);
        gl.glPopMatrix();
    }
    
    public void draw_back(GL gl, GLU glu)
    {
        //Create back
        m.set_brown_mat(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -(BODY_LENGTH-0.32f), 0.0f);
        gl.glClipPlane(GL.GL_CLIP_PLANE0, eqn3, 0);
        gl.glEnable(GL.GL_CLIP_PLANE0);
        glu.gluSphere(q, BACK_RADIUS, SLICES*2, STACKS*2);
        gl.glDisable(GL.GL_CLIP_PLANE0);
        gl.glPopMatrix();
    }
    
    public void draw_tail(GL gl, GLU glu) 
    {
        //Create tail
        m.set_brown_mat(gl);
        gl.glPushMatrix();           
        gl.glTranslatef(0.0f, -BODY_LENGTH, -0.17f);
        gl.glRotatef(110f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, TAIL_TOP_UPPER, TAIL_BOTTOM_UPPER, TAIL_LENGTH_UPPER, SLICES, STACKS);        

        gl.glPushMatrix();            
        gl.glTranslatef(0.0f, 0.0f, TAIL_LENGTH_UPPER);
        glu.gluSphere(q, TAIL_JOINT, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, 0.01f);
        gl.glRotatef(20f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, TAIL_TOP_LOWER, TAIL_BOTTOM_LOWER, TAIL_LENGTH_LOWER, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, TAIL_LENGTH_LOWER);
        glu.gluSphere(q, TAIL_END, SLICES/2, STACKS);
        gl.glPopMatrix();
        
        gl.glPopMatrix();         
    }
    
    public void draw_leg_left(GL gl, GLU glu, int[] v) 
    {
        m.set_brown_mat(gl);
        //Hip
        gl.glPushMatrix();
        gl.glTranslatef(0.25f, -(BODY_LENGTH-0.25f), 0.0f);
        gl.glRotatef(-10.0f + v[2], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(15.0f, 0.0f, 1.0f, 0.0f);      
        gl.glTranslatef(0.0f, 0.0f, 0.2f);
        superEllipsoid(gl, SE_X-0.19, SE_Y, SE_Z+0.19, SE_E, SE_EXP1, SE_EXP2);  
        
        //Leg
        gl.glTranslatef(0.0f, 0.0f, 0.2f);  
        gl.glRotatef(70.0f + v[1], 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, LEG_RADIUS, LEG_RADIUS, LEG_LENGTH, SLICES/2, STACKS);
        
        //Heel
        gl.glTranslatef(0.0f, 0.002f, LEG_LENGTH);
        glu.gluSphere(q, LEG_HEEL, SLICES/2, STACKS);   
        
        //Pads
        gl.glRotatef(-7.5f, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(-90.0f + v[0], 1.0f, 0.0f, 0.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.29f);        
        superEllipsoid(gl, (SE_X-0.28), (SE_Y-0.15), (SE_Z*2.0), SE_E, SE_EXP1, SE_EXP2+2);        
        gl.glPopMatrix();
    }
    
    public void draw_leg_right(GL gl, GLU glu, int[] v) 
    {
        m.set_brown_mat(gl);
        //Hip
        gl.glPushMatrix();
        gl.glTranslatef(-0.25f, -(BODY_LENGTH-0.25f), 0.0f);
        gl.glRotatef(-10.0f + v[2], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-15.0f, 0.0f, 1.0f, 0.0f);      
        gl.glTranslatef(0.0f, 0.0f, 0.2f);
        superEllipsoid(gl, SE_X-0.19, SE_Y, SE_Z+0.19, SE_E, SE_EXP1, SE_EXP2);

        //Leg
        gl.glTranslatef(0.0f, 0.0f, 0.2f);  
        gl.glRotatef(70.0f + v[1], 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, LEG_RADIUS, LEG_RADIUS, LEG_LENGTH, SLICES/2, STACKS);
        
        //Heel
        gl.glTranslatef(0.0f, 0.002f, LEG_LENGTH);
        glu.gluSphere(q, LEG_HEEL, SLICES/2, STACKS); 

        //Pads
        gl.glRotatef(7.5f, 0.0f, 1.0f, 0.0f); //To make the pads flat on ground
        gl.glRotatef(-90.0f + v[0], 1.0f, 0.0f, 0.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.29f);        
        superEllipsoid(gl, (SE_X-0.28), (SE_Y-0.15), (SE_Z*2.0), SE_E, SE_EXP1, SE_EXP2+2);

        gl.glPopMatrix();
    }
    
    public void draw_glove_red(GL gl, GLU glu)
    {     
        m.set_red_mat(gl);          
        superEllipsoid(gl, (SE_X-0.24), (SE_Y-0.1), (SE_Z-0.035), SE_E, SE_EXP1, SE_EXP2-0.5);
        //Thumb          
        gl.glTranslatef(-0.09f, 0.0f, -0.02f);
        gl.glRotatef(-20f, 0.0f, 1.0f, 0.0f);
        superEllipsoid(gl, (SE_X-0.31), (SE_Y-0.16), (SE_Z-0.09), SE_E, SE_EXP1, SE_EXP2-0.5);
        //Glove belt
        m.set_white_mat(gl);
        gl.glRotatef(20f, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(0.09f, 0.0f, -0.1f);
        draw_torus(gl, 0.04f, 0.03f, SLICES, STACKS);
        m.set_red_mat(gl);
        gl.glTranslatef(0.0f, 0.0f, -0.02f);          
        draw_torus(gl, 0.04f, 0.03f, SLICES, STACKS);
    }
    
    public void draw_glove_blue(GL gl, GLU glu)
    {     
        m.set_blue_mat(gl);          
        superEllipsoid(gl, (SE_X-0.24), (SE_Y-0.1), (SE_Z-0.035), SE_E, SE_EXP1, SE_EXP2-0.5);
        //Thumb          
        gl.glTranslatef(-0.09f, 0.0f, -0.02f);
        gl.glRotatef(-20f, 0.0f, 1.0f, 0.0f);
        superEllipsoid(gl, (SE_X-0.31), (SE_Y-0.16), (SE_Z-0.09), SE_E, SE_EXP1, SE_EXP2-0.5);
        //Glove belt
        m.set_white_mat(gl);
        gl.glRotatef(20f, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(0.09f, 0.0f, -0.1f);
        draw_torus(gl, 0.04f, 0.03f, SLICES, STACKS);
        m.set_blue_mat(gl);
        gl.glTranslatef(0.0f, 0.0f, -0.02f);          
        draw_torus(gl, 0.04f, 0.03f, SLICES, STACKS);
    }
   
    public void superEllipsoid(GL gl, double rx, double ry, double rz, int n, double e1, double e2) 
    {
        double pi = Math.PI;
        double dv = 2 * pi / n;
        double dw = pi / (n / 2); // ?

        double exp1 = 2.0 / e1;
        double exp2 = 2.0 / e2;

        for (double v = 0; v < 2 * pi; v += dv) {
          gl.glBegin(GL.GL_TRIANGLE_STRIP);

          gl.glNormal3d(0.0, 0.0, 1.0);
          gl.glVertex3d(0.0, 0.0, rz);

          for (double w = dw; w < pi; w += dw) {

            double cosv = Math.cos(v);
            double cosw = Math.cos(w);
            double cosvdv = Math.cos(v + dv);
            double sinv = Math.sin(v);
            double sinw = Math.sin(w);
            double sinvdv = Math.sin(v + dv);

            double acosv = Math.abs(cosv);
            double acosw = Math.abs(cosw);
            double acosvdv = Math.abs(cosvdv);
            double asinv = Math.abs(sinv);
            double asinw = Math.abs(sinw);
            double asinvdv = Math.abs(sinvdv);

            double scosv = Math.signum(cosv);
            double scosw = Math.signum(cosw);
            double scosvdv = Math.signum(cosvdv);
            double ssinv = Math.signum(sinv);
            double ssinw = Math.signum(sinw);
            double ssinvdv = Math.signum(sinvdv);

            gl.glNormal3d((1 / rx) * scosv * Math.pow(acosv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                          (1 / ry) * ssinv * Math.pow(asinv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                          (1 / rz) * scosw * Math.pow(acosw, 2.0 - exp2));
            gl.glVertex3d(rx * scosv * Math.pow(acosv, exp1) * ssinw * Math.pow(asinw, exp2),
                          ry * ssinv * Math.pow(asinv, exp1) * ssinw * Math.pow(asinw, exp2),
                          rz * scosw * Math.pow(acosw, exp2));

            gl.glNormal3d((1 / rx) * scosvdv * Math.pow(acosvdv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                          (1 / ry) * ssinvdv * Math.pow(asinvdv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                          (1 / rz) * scosw * Math.pow(acosw, 2.0 - exp2));
            gl.glVertex3d(rx * scosvdv * Math.pow(acosvdv, exp1) * ssinw * Math.pow(asinw, exp2),
                          ry * ssinvdv * Math.pow(asinvdv, exp1) * ssinw * Math.pow(asinw, exp2),
                          rz * scosw * Math.pow(acosw, exp2));

          }

          gl.glNormal3d(0.0, 0.0, -1.0);
          gl.glVertex3d(0.0, 0.0, -rz);

          gl.glEnd();
        } 
    }
    
    public void draw_torus (GL gl, float R, float r, int N, int n)
    {        
        int maxn= 1000;
        n=Math.min(n,maxn-1);
        N=Math.min(N,maxn-1);
        float rr=1.5f*r;
        double dv=2*Math.PI/n;
        double dw=2*Math.PI/N;
        double v=0.0f;
        double w=0.0f;
        while(w<2*Math.PI+dw)
        {
            v=0.0f;
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            while(v<2*Math.PI+dv)
            {
                gl.glNormal3d(
                        (R+rr*Math.cos(v))*Math.cos(w)-(R+r*Math.cos(v))*Math.cos(w),
                        (R+rr*Math.cos(v))*Math.sin(w)-(R+r*Math.cos(v))*Math.sin(w),
                        (rr*Math.sin(v)-r*Math.sin(v)));
                gl.glVertex3d((R+r*Math.cos(v))*Math.cos(w),
                           (R+r*Math.cos(v))*Math.sin(w),
                            r*Math.sin(v));
                gl.glNormal3d(
                        (R+rr*Math.cos(v+dv))*Math.cos(w+dw)-(R+r*Math.cos(v+dv))*Math.cos(w+dw),
                        (R+rr*Math.cos(v+dv))*Math.sin(w+dw)-(R+r*Math.cos(v+dv))*Math.sin(w+dw),
                        rr*Math.sin(v+dv)-r*Math.sin(v+dv));
                gl.glVertex3d((R+r*Math.cos(v+dv))*Math.cos(w+dw),
                           (R+r*Math.cos(v+dv))*Math.sin(w+dw),
                            r*Math.sin(v+dv));
                v+=dv;
            }
            gl.glEnd();
            w+=dw;
        }
    }
    
    public void resetAngles()
    {
//        moveTail = 0;
        movePos = 0;
        updateAngles();
    }
    
    public void updateAngles()
    {
        movePos = (movePos+1)%PAD_V.length;
//        moveTail = (moveTail+1)%TAIL_V.length;
        
        int movePos1 = movePos;
//        int moveTail1 = moveTail;  
        
        leg[0]=PAD_V[movePos1];        
        leg[1]=KNEE_V[movePos1];
        leg[2]=HIP_V[movePos1];
//        tail[0] = HIP_V[moveTail1];

    }
}
