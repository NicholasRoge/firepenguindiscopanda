/**
 * File:  KinectPLY.java
 * Date of Creation:  Nov 28, 2012
 */
package com.strixa.fpdp.gl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.strixa.gl.Strixa3DElement;
import com.strixa.gl.StrixaPolygon;
import com.strixa.gl.util.PercentLoadedUpdateListener;
import com.strixa.gl.util.Vertex;
import com.strixa.util.FileIO;
import com.strixa.util.Log;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class KinectPLYReader extends AsciiFileReader{
    private int             __attribute_count;
    private float           __color_blue_index;
    private float           __color_green_index;
    private float           __color_red_index;
    private boolean         __exited_header;
    private Strixa3DElement __point_cloud_object;
    private int             __vertex_count;
    private List<Vertex>    __vertices;
    private int             __vertices_read;
    private int             __x_index;
    private int             __y_index;
    private int             __z_index;
    private int             __x_normal_index;
    private int             __y_normal_index;
    private int             __z_normal_index;
    
    
    /*Begin Constructor*/    
    /**
     * Constructs a new KinectPLYReader object and prepares it to be read.
     * 
     * @param filename Path to file being read.  This may be absolute or relative.
     * 
     * @throws FileNotFoundException Thrown if the requested file does not exist.
     * @throws IllegalArgumentException Thrown if filename is null or empty.
     */
    public KinectPLYReader(String filename) throws FileNotFoundException,IllegalArgumentException{
        super(filename);
        
        this.__color_blue_index = -1;
        this.__color_green_index = -1;
        this.__color_red_index = -1;
        this.__exited_header = false;
        this.__vertex_count = 0;
        this.__vertices = new ArrayList<Vertex>();
        this.__vertices_read = 0;
        this.__x_index = -1;
        this.__y_index = -1;
        this.__z_index = -1;
    }
    /*End Constructor*/
    
    /*Begin Getter/Setter Methods*/
    public Strixa3DElement getPointCloudObject(){
        if(this.__point_cloud_object == null){
            if(this.isReading()){
                Log.logEvent(Log.Type.ERROR,"You must first wait for this object to finish reading before attempting to retrieve the point cloud object.");
            }else{
                Log.logEvent(Log.Type.ERROR,"You must first call the read method on this object before attempting to retrieve the point cloud object.");
            }
            
            return null;
        }
        
        return this.__point_cloud_object;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Methods*/
    protected boolean _processLine(int line_number,String[] arguments){
        if(line_number == 1){
            if(arguments[0] == null || arguments[0].isEmpty() || !arguments[0].equals("ply")){
                Log.logEvent(Log.Type.ERROR,"Given file is not PLY file.");
                
                return false;
            }
        }else{
            if(arguments[0].equals("comment") || arguments[0].isEmpty()){
                //Just a comment or empty line.  We're gonna just throw that out.
            }else if(arguments[0].equals("element")){
                if(arguments.length < 3){
                    Log.logEvent(Log.Type.ERROR,"Invalid number of arguments on line " + line_number + " of " + this.getFilename());
                    
                    return false;
                }
                
                if(arguments[1].equals("vertex")){
                    this.__vertex_count = Integer.parseInt(arguments[2]);
                }
            }else if(arguments[0].equals("property")){
                if(arguments.length < 3){
                    Log.logEvent(Log.Type.ERROR,"Invalid number of arguments on line " + line_number + " of " + this.getFilename());
                    
                    return false;
                }
                
                if(arguments[2].equals("x")){
                    this.__x_index = this.__attribute_count;
                }else if(arguments[2].equals("y")){
                    this.__y_index = this.__attribute_count;
                }else if(arguments[2].equals("z")){
                    this.__z_index = this.__attribute_count;
                }
                
                this.__attribute_count++;
            }else if(arguments[0].equals("end_header")){
                if(this.__x_index == -1 || this.__y_index == -1 || this.__z_index == -1){
                    Log.logEvent(Log.Type.ERROR,"The given file does not appear to be a point cloud file as it does not have all its coordinates attributes assigned to it.");
                }
                
                this.__exited_header = true;
            }else{
                if(!this.__exited_header){
                    Log.logEvent(Log.Type.WARNING,"Unrecognized command in header on line " + line_number + " of " + this.getFilename());
                }else{
                    if(arguments.length != this.__attribute_count){
                        Log.logEvent(Log.Type.ERROR,"Invalid number of arguments on line " + line_number + " of " + this.getFilename());
                        
                        return false;
                    }
                    
                    this.__vertices.add(new Vertex(
                        Double.parseDouble(arguments[this.__x_index]),
                        Double.parseDouble(arguments[this.__y_index]),
                        Double.parseDouble(arguments[this.__z_index]),
                        1
                    ));
                    
                    this.__vertices_read++;
                    if(this.__vertices_read == this.__vertex_count){
                        StrixaPolygon a_really_messed_up_looking_polygon = null;
                        double min_x = 0;
                        double min_y = 0;
                        double min_z = 0;
                        Vertex vertex = null;
                        
                        
                        for(int index = 0,end_index = this.__vertices.size() - 1;index <= end_index;index++){
                            vertex = this.__vertices.get(index);
                            
                            if(index == 1){
                                min_x = vertex.getX();
                                min_y = vertex.getY();
                                min_z = vertex.getZ();
                            }else{
                                min_x = Math.min(vertex.getX(),min_x);
                                min_y = Math.min(vertex.getY(),min_y);
                                min_z = Math.min(vertex.getZ(),min_z);
                            }
                        }
                        
                        for(int index = 0,end_index = this.__vertices.size() - 1;index <= end_index;index++){
                            vertex = this.__vertices.get(index);
                            
                            vertex.setX(vertex.getX() - min_x);
                            vertex.setY(vertex.getY() - min_y);
                            vertex.setZ(vertex.getZ() - min_z);
                        }
                        
                        a_really_messed_up_looking_polygon = new StrixaPolygon();
                        a_really_messed_up_looking_polygon.addPoints(this.__vertices);
                        
                        this.__point_cloud_object = new Strixa3DElement();
                        this.__point_cloud_object.addComponent(a_really_messed_up_looking_polygon);
                    }
                }
            }
        }
        
        return true;
    }
    /*End Other Methods*/
}
