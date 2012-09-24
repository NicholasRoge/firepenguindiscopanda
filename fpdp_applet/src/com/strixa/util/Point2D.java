/**
 * File:  Point2D.java
 * Date of Creation:  Jul 17, 2012
 */
package com.strixa.util;

/**
 * Generic point class which allows for any type of numeric to be used.  
 *
 * @author Nicholas Rog�
 */
public class Point2D <T extends Number> extends Point1D<T>{
    private T __y;
    
    
    /*Begin Constructors*/
    /**
     * Constructs a Point2D object whose coordinates are at the given points.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public Point2D(T x,T y){
        super(x);
        
        this.setY(y);
    }
    
    /**
     * Constructs a copy of the given Point2D object.
     * 
     * @param copy Point2D object whose data should be copied.
     */
    public Point2D(Point2D<T> copy){
        super(copy);
        
        this.setPoint(copy.getX(),copy.getY());
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets this point's X coordinate.
     * 
     * @return This point's X coordinate.
     */
    public T getY(){
        return this.__y;
    }
    
    /**
     * Sets this point's coordinates.
     * 
     * @param x X coordinate of the point.
     * @param y Y coordinate of the point.
     */
    public void setPoint(T x,T y){
        this.setX(x);
        this.setY(y);
    }
    
    /**
     * Sets this point's Y Coordinate.
     * 
     * @param y Y coordinate of the point.
     */
    public void setY(T y){
        this.__y = y;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    /**
     * Performs a check to see if the given point is at the same location as this one.
     * 
     * @param point Point to compare against.
     * 
     * @return Returns true if the given point is at the same location as this one, and false, otherwise.
     */
    public boolean equals(Point2D<T> point){
        if(super.equals(point) && this.getY()==point.getY()){
            return true;
        }
        
        return false;
    }
    
    public String toString(){
        return "("+this.getX()+","+this.getY()+")";
    }
    /*End Other Methods*/
}
