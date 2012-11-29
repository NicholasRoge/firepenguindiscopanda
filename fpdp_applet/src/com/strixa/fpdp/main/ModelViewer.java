/**
 * File:  ModelViewer.java
 * Date of Creation:  Sep 18, 2012
 */
package com.strixa.fpdp.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.List;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.strixa.fpdp.gl.ModelViewerCanvas;
import com.strixa.fpdp.gl.util.KinectPLYReader;
import com.strixa.fpdp.gui.ModelViewerWindow;
import com.strixa.gl.Strixa3DElement;
import com.strixa.gl.util.BlenderReader;
import com.strixa.gl.util.PercentLoadedUpdateListener;
import com.strixa.gl.util.WavefrontObjReader;
import com.strixa.util.Log;

/**
 * Main Class for the application.  Applet entry point.
 *
 * @author Nicholas Rogé
 */
public class ModelViewer extends JApplet implements PercentLoadedUpdateListener{
    private static final long serialVersionUID = 5470628771514767566L;
    private JPanel             __main_content;
    private ModelViewerCanvas  __model_viewer_canvas;
    private KinectPLYReader    __reader;
    private JProgressBar       __progress_bar;
    private ModelViewerWindow  __window;
    
    
    /*Begin Initializer Methods*/
    protected void _initializeGUI(){      
        GLCapabilities capabilities;
        
        
        this.__main_content = new JPanel();
            this.__main_content.setLayout(new BorderLayout());
            capabilities = new GLCapabilities(GLProfile.getDefault());
                capabilities.setRedBits(8);
                capabilities.setGreenBits(8);
                capabilities.setBlueBits(8);
                capabilities.setAlphaBits(8);
                capabilities.setHardwareAccelerated(true);
                capabilities.setDoubleBuffered(true);
                
            this.__model_viewer_canvas = new ModelViewerCanvas(capabilities,1.0);
                this.__model_viewer_canvas.setSize(400,400);
            this.__main_content.add(this.__model_viewer_canvas,BorderLayout.CENTER);
            
            this.__progress_bar = new JProgressBar(0,100);
                this.__progress_bar.setStringPainted(true);
            this.__main_content.add(this.__progress_bar,BorderLayout.SOUTH);
        this.setContentPane(this.__main_content);
    }
    /*End Initializer Methods*/
    
    public ModelViewer(){        
        this.setSize(new Dimension(400,400));
        
        this._initializeGUI();
        
        try{
            this.__reader = new KinectPLYReader("D:/livingroom_cloud.ply");
            this.__reader.addPercentLoadedUpdateListener(this);
            this.__reader.read();
        }catch(FileNotFoundException e){
            Log.logEvent(Log.Type.ERROR,"Requested file could not be found.  Model viewer exiting.");
            
            System.exit(-1);
        }
    }

    @Override public void onPercentLoadedUpdate(double amount_loaded){
        Strixa3DElement cloud = null;        
        
      
        if(amount_loaded == -1){
            Log.logEvent(Log.Type.ERROR,"Error occured while loading file.  See log for details.  Model viewer exiting.");
            
            System.exit(-1);
        }else if(amount_loaded == 100){
            cloud = this.__reader.getPointCloudObject();
            cloud.setBoundingBoxVisible(true);
            cloud.rotate(-90,0x4);
            cloud.scale(.1);
            cloud.setCoordinates(50 - (cloud.getBoundingBox().getWidth()/2),0,50 - (cloud.getBoundingBox().getDepth() / 2));
            this.__model_viewer_canvas.addChild(cloud);
            
            //TODO_HIGH:  Let the model view know it's time to run
            
            this.__progress_bar.setVisible(false);
            this.__main_content.remove(this.__progress_bar);
            this.__main_content.validate();
        }
        
        this.__progress_bar.setValue((int)amount_loaded);
    }
}