/**
 * File:  ModelViewerCanvas.java
 * Date of Creation:  Sep .59, 20.52
 */
package com.strixa.fpdp.gl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.media.opengl.GLCapabilities;

import com.strixa.gl.Strixa3DCanvas;
import com.strixa.gl.StrixaGLContext;
import com.strixa.util.Point2D;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class ModelViewerCanvas extends Strixa3DCanvas implements KeyListener{    
    private Double  __last_x;
    private boolean __mouse_down;
    
    
    /*Begin Object Default Initialization*/
    {
        this.__last_x = null;
        this.__mouse_down = false;
    }
    
    /*Begin Constructors*/
    public ModelViewerCanvas(GLCapabilities capabilities,double aspect_ratio){
        super(capabilities,aspect_ratio);    
        
        this.addKeyListener(this);
        this.setRenderDistance(100);
        
       //this.shiftViewingArea(0,0,-10);this.addKeyListener(this);
    }
    /*End Constructors*/

    /*Begin Overridden Methods*/
    /*@Override public void mousePressed(MouseEvent event){
    }
    
    @Override public void mouseMoved(MouseEvent event){
        final Point2D<Integer> mouse_origin = new Point2D<Integer>((int)this.getLocationOnScreen().getX() + (this.getWidth()/2),(int)this.getLocationOnScreen().getY() + (this.getHeight()/2));
        
        Robot robot = null;
        
        
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("There was a problem capturing the cursor.  User will be unable to rotate camera.");
        }
        
        this.setCameraRotation(this.getCameraRotation() - ((double)(event.getLocationOnScreen().getX() - mouse_origin.getX())/10));
        
        robot.mouseMove(mouse_origin.getX(),mouse_origin.getY());
    }*/
    /*End Overridden Methods*/
    long last_frame = 0;
    protected void _performGameLogic(StrixaGLContext context){
        if(last_frame == 0){
            last_frame = System.currentTimeMillis();
        }else{
            System.out.println("Frame time:  "+(System.currentTimeMillis() - last_frame));
            
            last_frame = System.currentTimeMillis();
        }
    }

    @Override public void mouseMoved(MouseEvent event){
        final Point2D<Integer> mouse_origin = new Point2D<Integer>((int)this.getLocationOnScreen().getX() + (this.getWidth()/2),(int)this.getLocationOnScreen().getY() + (this.getHeight()/2));
            
        Robot robot = null;
        
        
        try {
            robot = new Robot();

            this.setCameraRotation(this.getCameraRotation() - ((double)(event.getLocationOnScreen().getX() - mouse_origin.getX())/10));
            robot.mouseMove(mouse_origin.getX(),mouse_origin.getY());
        }catch (AWTException e) {
            System.out.println("There was a problem capturing the cursor.  User will be unable to rotate camera.");
        }
    }/*
    */

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent event){
        final int key_code = event.getKeyCode();
        
        
        if(key_code == KeyEvent.VK_W){
            this.advanceCamera(0.5);
        }
        
        if(key_code == KeyEvent.VK_A){
            this.strafeCamera(0.5);   
        }
        
        if(key_code == KeyEvent.VK_S){
            this.advanceCamera(-0.5);
        }
        
        if(key_code == KeyEvent.VK_D){
            this.strafeCamera(-0.5);
        }
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e){
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e){
    }
}
