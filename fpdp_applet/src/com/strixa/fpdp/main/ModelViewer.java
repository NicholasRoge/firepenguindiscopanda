/**
 * File:  ModelViewer.java
 * Date of Creation:  Sep 18, 2012
 */
package com.strixa.fpdp.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.strixa.fpdp.gl.ModelViewerCanvas;
import com.strixa.fpdp.gui.ModelViewerWindow;
import com.strixa.gl.Strixa3DElement;
import com.strixa.gl.util.BlenderReader;
import com.strixa.gl.util.PercentLoadedUpdateListener;
import com.strixa.gl.util.WavefrontReader;

/**
 * Main Class for the application.  Applet entry point.
 *
 * @author Nicholas Rogé
 */
public class ModelViewer extends JApplet implements PercentLoadedUpdateListener{
    private static final long serialVersionUID = 5470628771514767566L;
    private JPanel            __main_content;
    private ModelViewerCanvas __model_viewer_canvas;
    private WavefrontReader   __reader;
    private JProgressBar      __progress_bar;
    private ModelViewerWindow __window;
    
    
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
    /*End Initilizer Methods*/
    
    public ModelViewer(){        
        this.setSize(new Dimension(400,400));
        
        this._initializeGUI();
        
        this.__reader = new WavefrontReader("C:/box.obj");
        this.__reader.addPercentLoadedUpdateListener(this);
        this.__reader.read();
    }

    @Override public void onPercentLoadedUpdate(double amount_loaded){
        Strixa3DElement[] elements = null;        
        
        
        this.__progress_bar.setValue((int)amount_loaded);
      
        if(amount_loaded == 100){
            elements = this.__reader.getElements();
            for(Strixa3DElement element:elements){
                this.__model_viewer_canvas.addChild(element);
            }
            
            //TODO_HIGH:  Let the model view know it's time to run
            
            this.__progress_bar.setVisible(false);
            this.__main_content.remove(this.__progress_bar);
            this.__main_content.validate();
        }
    }
}
