/**
 * File:  ModelViewer.java
 * Date of Creation:  Sep 18, 2012
 */
package com.strixa.fpdp.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.strixa.fpdp.gl.ModelViewerCanvas;
import com.strixa.fpdp.gui.ModelViewerWindow;

/**
 * Main Class for the application.  Applet entry point.
 *
 * @author Nicholas Rogé
 */
public class ModelViewer extends JApplet{
    private static final long serialVersionUID = 5470628771514767566L;
    private JPanel            __main_content;
    private ModelViewerCanvas __model_viewer_canvas;
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
        
        
        this.setContentPane(this.__main_content);
    }
    /*End Initilizer Methods*/
    
    public ModelViewer(){
        this.setSize(new Dimension(400,400));
        
        this._initializeGUI();
    }
}
