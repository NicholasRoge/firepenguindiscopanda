/**
 * File:  ModelViewer.java
 * Date of Creation:  Sep 18, 2012
 */
package com.strixa.fpdp.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.strixa.fpdp.gl.ModelViewerCanvas;
import com.strixa.fpdp.gl.util.KinectPLYReader;
import com.strixa.gl.Strixa3DElement;
import com.strixa.gl.util.PercentLoadedUpdateListener;
import com.strixa.swing.filechooser.FileTypeFilter;
import com.strixa.util.Log;

/**
 * Main Class for the application.  Applet entry point.
 *
 * @author Nicholas Rogé
 */
public class ModelViewer extends JApplet implements PercentLoadedUpdateListener{
    private class AddAction implements ActionListener{
        @Override public void actionPerformed(ActionEvent arg0){
            JFileChooser file_chooser = null;
            int          status = 0;
            
            
            file_chooser = new JFileChooser();
            file_chooser.setDialogTitle("Please select the PLY file you would like added to the canvas.");
            file_chooser.setFileFilter(new FileTypeFilter("ply"));
            
            status = file_chooser.showOpenDialog(ModelViewer.this.__main_content);
            if(status == JFileChooser.APPROVE_OPTION){
                ModelViewer.this.__add_button.setEnabled(false);
                
                try{
                    ModelViewer.this.__reader = new KinectPLYReader(file_chooser.getSelectedFile().getAbsolutePath());
                    ModelViewer.this.__reader.addPercentLoadedUpdateListener(ModelViewer.this);
                    ModelViewer.this.__reader.read();
                    
                    ModelViewer.this.__progress_bar.setStringPainted(true);
                }catch(FileNotFoundException e){
                    Log.logEvent(Log.Type.ERROR,"Requested file could not be found.  Model viewer exiting.");
                    
                    System.exit(-1);
                }
            }
        }
    }
    
    private JButton            __add_button;
    private JPanel             __main_content;
    private ModelViewerCanvas  __model_viewer_canvas;
    private KinectPLYReader    __reader;
    private JProgressBar       __progress_bar;
    
    
    /*Begin Initializer Methods*/
    protected void _initializeGUI(){
        final GridBagConstraints constraints = new GridBagConstraints();
        GLCapabilities capabilities;
        
        
        this.__main_content = new JPanel();
            this.__main_content.setLayout(new GridBagLayout());
            capabilities = new GLCapabilities(GLProfile.getDefault());
                capabilities.setRedBits(8);
                capabilities.setGreenBits(8);
                capabilities.setBlueBits(8);
                capabilities.setAlphaBits(8);
                capabilities.setHardwareAccelerated(true);
                capabilities.setDoubleBuffered(true);
            
            constraints.fill = GridBagConstraints.BOTH;
            
            this.__model_viewer_canvas = new ModelViewerCanvas(capabilities,1.0);                
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.gridwidth = 2;
                constraints.weightx = 1;
                constraints.weighty = 1;
            this.__main_content.add(this.__model_viewer_canvas,constraints);
            
            this.__progress_bar = new JProgressBar(0,100);
                this.__progress_bar.setStringPainted(false);
                
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 1;
                constraints.weightx = 1;
                constraints.weighty = 1;
            this.__main_content.add(this.__progress_bar,constraints);
            
            this.__add_button = new JButton("Add Model");
            
                constraints.gridx = 1;
                constraints.gridy = 1;
                constraints.gridwidth = 1;
                constraints.weightx = 0;
                constraints.weighty = 1;
            this.__add_button.addActionListener(new AddAction());
        this.__main_content.add(this.__add_button,constraints);
        this.setContentPane(this.__main_content);
            
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException e) {
            Log.logEvent(Log.Type.NOTICE,"Could not retrieve the System's look and feel class. Look and feel will not be set.");
        } catch(InstantiationException e) {
            Log.logEvent(Log.Type.NOTICE,"Could not retrieve instantiate the system's look and feel class. Look and feel will not be set.");
        } catch(IllegalAccessException e) {
            Log.logEvent(Log.Type.NOTICE,"Could not access the System's Look and Feel class. Look and feel will not be set.");
        } catch(UnsupportedLookAndFeelException e) {
            Log.logEvent(Log.Type.NOTICE,"The requested look and feel is not available on this system. Look and feel will not be set.");
        }
        
    }
    /*End Initializer Methods*/
    
    /*Begin Constructor*/
    public ModelViewer(){        
        this.setSize(new Dimension(400,400));
        
        this._initializeGUI();
    }
    /*End Constructor*/

    /*Begin Other Methods*/
    @Override public void onPercentLoadedUpdate(double amount_loaded){
        Strixa3DElement cloud = null;        
        
      
        switch((int)amount_loaded){
            case -1:
                Log.logEvent(Log.Type.ERROR,"Error occured while loading file.  See log for details.");
                this.__progress_bar.setValue(0);
                this.__progress_bar.setStringPainted(false);
                
                break;
            case 100:
                cloud = this.__reader.getPointCloudObject();
                cloud.setBoundingBoxVisible(true);                
                cloud.setCoordinates(50 - (cloud.getBoundingBox().getWidth()/2),0,50 - (cloud.getBoundingBox().getDepth() / 2));
                
                
                this.__model_viewer_canvas.addChild(cloud);
                
                this.__progress_bar.setValue(0);
                this.__progress_bar.setStringPainted(false);
                this.__add_button.setEnabled(false);
                
                break;
            default:
                this.__progress_bar.setValue((int)amount_loaded);
                
                break;
        }
    }
    /*End Other Methdos*/
}