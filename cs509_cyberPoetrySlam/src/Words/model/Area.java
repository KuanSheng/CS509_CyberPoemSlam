package Words.model;

import java.io.Serializable;

/**just for selecting Elements in an area
 * created by KuanSheng**/
public class Area implements Serializable{
	int originalx;
	int originaly;
	int width;
	int height;
	
	public Area(int originalx,int originaly,int width,int height){
		this.originalx = originalx;
		this.originaly = originaly;
		this.width = width;
		this.height = height;
	}
	
	public int getX(){
		return this.originalx;
	}
	
	public int getY(){
		return this.originaly;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
}