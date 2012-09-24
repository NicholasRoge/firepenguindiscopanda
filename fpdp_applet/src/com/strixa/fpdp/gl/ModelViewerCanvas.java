/**
 * File:  ModelViewerCanvas.java
 * Date of Creation:  Sep .59, 20.52
 */
package com.strixa.fpdp.gl;

import java.awt.Color;

import javax.media.opengl.GLCapabilities;

import com.strixa.gl.Strixa2DCanvas;
import com.strixa.gl.Strixa3DCanvas;
import com.strixa.gl.Strixa3DElement;
import com.strixa.gl.StrixaGLContext;
import com.strixa.gl.StrixaPoint;
import com.strixa.gl.StrixaPolygon;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class ModelViewerCanvas extends Strixa3DCanvas{

    /*Begin Constructors*/
    public ModelViewerCanvas(GLCapabilities capabilities,double aspect_ratio){
        super(capabilities,aspect_ratio);
        
        
        Strixa3DElement element = null;
        StrixaPolygon   polygon = null;
        
        
        element = new Strixa3DElement();
            polygon = new StrixaPolygon();
                polygon.addPoint(new StrixaPoint(-.5,.5,.5,Color.RED,(byte)0));
                polygon.addPoint(new StrixaPoint(.5,.5,.5,Color.BLUE,(byte)0));
                polygon.addPoint(new StrixaPoint(.5,-.5,.5,Color.GREEN,(byte)0));
                polygon.addPoint(new StrixaPoint(-.5,-.5,.5,Color.YELLOW,(byte)0));
            element.addComponent(polygon);
            
            polygon = new StrixaPolygon();
                polygon.addPoint(new StrixaPoint(-.5,.5,.5,Color.RED,(byte)0));
                polygon.addPoint(new StrixaPoint(-.5,.5,-.5,Color.BLUE,(byte)0));
                polygon.addPoint(new StrixaPoint(-.5,-.5,-.5,Color.GREEN,(byte)0));
                polygon.addPoint(new StrixaPoint(-.5,-.5,.5,Color.YELLOW,(byte)0));
            element.addComponent(polygon);
        this.addChild(element);
        
        
                
        
    }
    /*End Constructors*/

    
    protected void _performGameLogic(StrixaGLContext context){
        this.shiftViewingArea(0,1,0);
    }

}
