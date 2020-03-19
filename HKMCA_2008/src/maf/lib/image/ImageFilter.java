/*
 * Created on 2004. 9. 20.
 *
 */
package maf.lib.image;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * @author bluevlad
 *
 */
public class ImageFilter {

    public ImageFilter() {
    }
    
    public static BufferedImage sharpenfloat(BufferedImage bufferedImage){
	    return filter3x3(bufferedImage, -1, 5);
	}

	/**
	 * 0    0.05 0
	 * 0.05 0.8  0.05
	 * 0    0.05 0
	 */	
	public static BufferedImage softenfloat(BufferedImage bufferedImage){
      return filter3x3(bufferedImage, 0.05f, 1-(0.05f*4));
	}

	public static BufferedImage filter3x3(BufferedImage bufferedImage, float nFactor, float mFactor ){
	    float[] filterArray = {0, nFactor, 0, nFactor, mFactor, nFactor, 0, nFactor, 0};
	    return filter3x3(bufferedImage, filterArray);
	}
	
	/**
	 * filter 3 x 3 Matrix로 image에 filter 용과를 줄때..
	 * @param bufferedImage
	 * @param filterArray
	 * @return
	 */
	public static BufferedImage filter3x3(BufferedImage bufferedImage, float[] filterArray) {
	    Kernel kernel = null;
        ConvolveOp cOp = null;
        
        kernel = new Kernel(3, 3, filterArray);
        cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);
        
	    return bufferedImage;	    
	}
}
