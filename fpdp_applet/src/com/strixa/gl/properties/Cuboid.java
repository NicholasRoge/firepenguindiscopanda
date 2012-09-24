/**
 * File:  Cuboid.java
 * Date of Creation:  Jul 19, 2012
 */
package com.strixa.gl.properties;

import java.util.ArrayList;
import java.util.List;

import com.strixa.util.Dimension3D;
import com.strixa.util.Point3D;

/**
 * Contains the vertices for a Cuboid object.
 *
 * @author Nicholas Rog�
 */
public class Cuboid{
    private Point3D<Double>     __coordinates;
    private Dimension3D<Double> __dimensions;
    
    
    /*Begin Constructors*/
    public Cuboid(Point3D<Double> coordinates,Dimension3D<Double> dimensions){
        this.__coordinates = coordinates;
        this.__dimensions = dimensions;
    }
    
    public Cuboid(Point3D<Double> coordinates,double width,double height,double depth){
        this(
            new Point3D<Double>(
                coordinates
            ),
            new Dimension3D<Double>(
                width,
                height,
                depth
            )
        );
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the coordinates of the left (most negative X), bottom (most negative Y), front (most negative Z) corner of the cuboid.
     * 
     * @return The coordinates of the left (most negative X), bottom (most negative Y), front (most negative Z) corner of the cuboid.
     */
    public Point3D<Double> getCoordinates(){
        return this.__coordinates;
    }
    
    public double getDepth(){
        return this.__dimensions.getDepth();
    }
    
    public Dimension3D<Double> getDimensions(){
        return this.__dimensions;
    }
    
    public double getHeight(){
        return this.__dimensions.getHeight();
    }
    
    public double getWidth(){
        return this.__dimensions.getWidth();
    }
    /*End Getter/Setter Methods*/
}
