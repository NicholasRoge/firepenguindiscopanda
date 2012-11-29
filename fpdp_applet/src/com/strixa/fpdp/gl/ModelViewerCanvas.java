/**
 * File:  ModelViewerCanvas.java
 * Date of Creation:  Sep .59, 20.52
 */
package com.strixa.fpdp.gl;

import java.awt.AWTException;
import java.awt.IllegalComponentStateException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;

import com.strixa.gl.Strixa3DCanvas;
import com.strixa.gl.Strixa3DElement;
import com.strixa.gl.Strixa3DElement.CollisionDetectionMethod;
import com.strixa.gl.StrixaGLContext;
import com.strixa.gl.shapes.RectangularPrism;
import com.strixa.util.Log;
import com.strixa.util.Point2D;
import com.strixa.util.Point3D;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class ModelViewerCanvas extends Strixa3DCanvas implements KeyListener{    
    private Double           __last_x;
    private RectangularPrism __ground;
    private RectangularPrism __observer;
    private double           __observer_rotation;
    
    
    /*Begin Constructors*/
    public ModelViewerCanvas(GLCapabilities capabilities,double aspect_ratio){
        super(capabilities,aspect_ratio);    
        
        this.addKeyListener(this);
        this.setRenderDistance(1000);
        
        this.__ground = new RectangularPrism(100,1,100);
            this.__ground.getMaterial().setAmbientColor(1,1,0);
            this.__ground.getMaterial().setSpecularColor(1,1,0);
            this.__ground.getMaterial().setSpecularCoefficient(50);
            this.__ground.setCollisionDetectionEnabled(false);
        this.addChild(this.__ground);
        
        this.__observer = new RectangularPrism(1,2,1);
            this.__observer.getMaterial().setAlpha(0f);
        this.addChild(this.__observer);
        
        this.__ground.setCoordinates(0,0,0);
        this.__observer.setCoordinates(49,-1,40);
        this.shiftViewingArea(50,1,40.5);
    }
    /*End Constructors*/

    /*Begin Overridden Methods*/
    @Override public void init(GLAutoDrawable drawable){
        super.init(drawable);
        
        drawable.getGL().getGL2().glPolygonMode(GL2.GL_FRONT_AND_BACK,GL2.GL_POINT);
        
        /*drawable.getGL().getGL2().glFogi(GL2.GL_FOG_MODE,GL2.GL_EXP2);
        drawable.getGL().getGL2().glFogfv(GL2.GL_FOG_COLOR,new float[]{.5f,.5f,.5f,1f},0);
        drawable.getGL().getGL2().glFogf(GL2.GL_FOG_DENSITY,.01f);
        drawable.getGL().getGL2().glHint(GL2.GL_FOG_HINT,GL2.GL_FASTEST);
        drawable.getGL().getGL2().glFogf(GL2.GL_FOG_START,35f);
        drawable.getGL().getGL2().glFogf(GL2.GL_FOG_END,50f);
        drawable.getGL().getGL2().glEnable(GL2.GL_FOG);*/
    }
    /*End Overridden Methods*/
    
    protected void _performGameLogic(StrixaGLContext context){

    }

    @Override public void mouseMoved(MouseEvent event){
        Point2D<Integer> mouse_origin = null;
        double           pitch_delta = 0;
        Robot            robot = null;
        double           rotation_delta = 0;
        
        
        try{
            mouse_origin = new Point2D<Integer>((int)this.getLocationOnScreen().getX() + (this.getWidth()/2),(int)this.getLocationOnScreen().getY() + (this.getHeight()/2));
            robot = new Robot(); 

            rotation_delta = ((double)(event.getLocationOnScreen().getX() - mouse_origin.getX())/10);
            this.__observer_rotation = this.getCameraRotation() - rotation_delta;             
            this.setCameraRotation(this.__observer_rotation);
            this.__observer.rotate(-rotation_delta,(byte)0x2);
            
            pitch_delta = ((double)(event.getLocationOnScreen().getY() - mouse_origin.getY())/10);
            /*this.setCameraPitch(this.getCameraPitch() + pitch_delta);*/
            robot.mouseMove(mouse_origin.getX(),mouse_origin.getY());
        }catch(AWTException e) {
            Log.logEvent(Log.Type.WARNING,"There was a problem capturing the cursor.  User will be unable to rotate camera.");
        }catch(IllegalComponentStateException e){
            //This most likely happened because the user alt+f4'd out of the application.  This is an okay exception.
        }
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent event){
        final int             key_code = event.getKeyCode();
        final Point3D<Double> observer_coordinates = new Point3D<Double>(this.__observer.getCoordinates());
        
        boolean collision = false;
        
        
        switch(key_code){
            case KeyEvent.VK_W:
                this.__observer.setCoordinates(
                    observer_coordinates.getX() + (.5 * Math.sin((this.__observer_rotation * Math.PI) / 180)),
                    observer_coordinates.getY(),
                    observer_coordinates.getZ() + (.5 * Math.cos((this.__observer_rotation * Math.PI) / 180))
                );
                break;
            case KeyEvent.VK_A:
                this.__observer.setCoordinates(
                    observer_coordinates.getX() + (.5 * Math.sin(((this.__observer_rotation + 90) * Math.PI) / 180)),
                    observer_coordinates.getY(),
                    observer_coordinates.getZ() + (.5 * Math.cos(((this.__observer_rotation + 90) * Math.PI) / 180))
                );
                break;
            case KeyEvent.VK_S:
                this.__observer.setCoordinates(
                    observer_coordinates.getX() - (.5 * Math.sin((this.__observer_rotation * Math.PI) / 180)),
                    observer_coordinates.getY(),
                    observer_coordinates.getZ() - (.5 * Math.cos((this.__observer_rotation * Math.PI) / 180))
                );
                break;
            case KeyEvent.VK_D:
                this.__observer.setCoordinates(
                    observer_coordinates.getX() - (.5 * Math.sin(((this.__observer_rotation + 90) * Math.PI) / 180)),
                    observer_coordinates.getY(),
                    observer_coordinates.getZ() - (.5 * Math.cos(((this.__observer_rotation + 90) * Math.PI) / 180))
                );
                break;
        }
        
        /*for(Strixa3DElement element:this.getChildren()){
            if(element != this.__observer){
                if(this.__observer.isColliding(element,CollisionDetectionMethod.POINT)){
                    collision = true;
                    
                    break;
                }
            }
        }*/
        
        if(collision){
            this.__observer.setCoordinates(observer_coordinates.getX(),observer_coordinates.getY(),observer_coordinates.getZ());
        }else{
            switch(key_code){
                case KeyEvent.VK_W:
                    this.advanceCamera(0.5);
                    break;
                case KeyEvent.VK_A:
                    this.strafeCamera(0.5);
                    break;
                case KeyEvent.VK_S:
                    this.advanceCamera(-0.5);
                    break;
                case KeyEvent.VK_D:
                    this.strafeCamera(-0.5);
                    break;   
            }
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
