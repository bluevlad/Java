package maf.lib.image;

/**
 * @author UBQ 
 * @ Created on 2004. 4. 12.
 *  
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;

import maf.logger.Logging;
import maf.util.FileUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Thumbnail 
{
    private static float DEFALUT_JPEG_QUALITY = 0.60f;
    private static Log logger = LogFactory.getLog(Thumbnail.class);
    FileUtils fu = new FileUtils();
    
    public Thumbnail() {
        
    }
    /**
     * 
     * @param original
     * @param resized
     * @param Width
     * @param Height
     * @param quality
     * @return
     * @throws IOException
     */
    public static boolean resize(File originalFile, String newPath, String resized, int Width, int Height, float quality)   
    throws IOException, ThumbnailException
    {	
    	if(newPath == null ){
    		throw new ThumbnailException("new path is null!!!");
    	}

        try{  
	        quality = _ChkQuality(quality);
		    int startX = 0, startY = 0;
		    int tWidth, tHeight;

	        if(!originalFile.exists()) return false; 
	        
	        Image resizedImage = null;
	        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
	        Image i = ii.getImage();
	        int iWidth = i.getWidth(null);
	        int iHeight = i.getHeight(null);
	        //resizedImage = i.getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	        float wRatio = (float) Width / (float) iWidth;
	        float hRatio = (float) Height /  (float) iHeight;
	        if (wRatio > hRatio) {
	            tWidth = (Height*iWidth)/iHeight;
	            tHeight = Height;
	        } else {
	            tWidth = Width;
	            tHeight = (Width*iHeight)/iWidth;
	        }
            resizedImage = i.getScaledInstance(tWidth,tHeight, Image.SCALE_SMOOTH);
	        
            if(tWidth < Width) {
                startX = (Width-tWidth) / 2;
            } 
            if(tHeight< Height) {
                startY = (Height-tHeight) / 2;
            }	        
	        return _resize(originalFile,newPath, resized, resizedImage, Width,  Height, startX, startY, quality);
        } catch (Throwable e) {            
			System.out.println(e.getMessage());
			throw new ThumbnailException(e);
        }    
    }
    
    public static boolean resize(File original, String newPath, String resized, int Width, int Height) 
    throws IOException, ThumbnailException
    {
        return resize(original, newPath, resized, Width, Height, DEFALUT_JPEG_QUALITY);
    }

    /**
     * 이미지를 width와 height에 꽉차게 resize 함.
     * 비율이 틀린경우 나머지 영역은 아래 부분과 좌우 영역을 잘라내 버림 
     * @param original	원본이미지
     * @param resized 	섬네일 이미지 절대경로/이미지파일이름
     * @param Width 	섬네일이미지 가로 길이
     * @param Height 	섬네일이미지 세로 길이
     * @param quality
     * @return
     * @throws IOException
     */
    public static boolean resizeFull(File originalFile, String newPath, String resized, int Width, int Height, float quality)   
    throws IOException, ThumbnailException
    {	
    	if(newPath == null ){
    		throw new ThumbnailException("new path is null!!!");
    	}
    	if(resized == null ){
    		throw new ThumbnailException("resized is null!!!");
    	}
        try{  
	        quality = _ChkQuality(quality); 
		    int startX = 0, startY = 0;
		    int tWidth, tHeight;		    

	        if(!originalFile.exists()) return false; 
	        Image resizedImage = null;
	        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
	        Image i = ii.getImage();
	        int iWidth = i.getWidth(null);
	        int iHeight = i.getHeight(null);
	        
	        float wRatio = (float) Width / (float) iWidth;
	        float hRatio = (float) Height /  (float) iHeight;
	        if (wRatio < hRatio) {
	            tWidth = (Height*iWidth)/iHeight;
	            tHeight = Height;
	            startX = (Width - tWidth) / 2;
	        } else {
	            tWidth = Width;
	            tHeight = (Width*iHeight)/iWidth;
	            startY = (Height - tHeight) / 3;
	        }
            resizedImage = i.getScaledInstance(tWidth,tHeight, Image.SCALE_SMOOTH);
	        return _resize(originalFile, newPath,  resized, resizedImage, Width,  Height, startX, startY, quality);
        } catch (Throwable e) {            
            logger.error(e.getMessage());
			throw new ThumbnailException(e);
        }    
    }
    
    
	public static boolean resizeFull(File original, String newPath, String resized, int Width, int Height)   
	   throws IOException, ThumbnailException {
	   		return resizeFull(original, newPath, resized, Width, Height, DEFALUT_JPEG_QUALITY);
	   	
	   }

    /**
     *     
     * 
     * 가로와 세로중 최대길이를 maxSize로 유지하면서 image크기를 변화 시킴 
     * @param original
     * @param resized
     * @param maxSize
     * @param quality
     * @return
     * @throws IOException
     */
    public static boolean resize(File originalFile, 
			String resizedFilePath,
			String resized,  int maxSize, float quality)
    throws IOException, ThumbnailException
    {	
    	if(resizedFilePath == null ){
    		throw new ThumbnailException("new path is null!!!");
    	}

        if(originalFile == null) return false;
        if(resized == null) return false;
        
        try{    
	        quality = _ChkQuality(quality);
	        
	        
	        if(!originalFile.exists()) return false;	        
	        Image resizedImage = null;
			ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
	        Image i = ii.getImage();
	        int iWidth = i.getWidth(null);
	        int iHeight = i.getHeight(null);
	        int nWidth; int nHeight;
	        if (iWidth > iHeight) {                
				nWidth = maxSize;
				nHeight = (maxSize*iHeight)/iWidth;
	        } else {	
				nWidth = (maxSize*iWidth)/iHeight;
				nHeight = maxSize;	            
	        }
	        resizedImage = i.getScaledInstance(nWidth, nHeight, Image.SCALE_SMOOTH);
	        return _resize(originalFile, resizedFilePath,  resized, resizedImage, nWidth, nHeight, 0,0,quality);
        } catch (Throwable e) {            
            logger.error(e.getMessage());
			throw new ThumbnailException(e);
        }    
    }
    
    public static boolean resize(File original, String resizedFilePath, String resized, int maxSize)
    throws IOException, ThumbnailException
    {	
        return resize(original, resizedFilePath, resized, maxSize, DEFALUT_JPEG_QUALITY);
    }
        
    
	/**
	 * 
	 * @param originalFile
	 * @param resized
	 * @param resizedImage
	 * @param width
	 * @param height
	 * @param quality
	 * @return
	 * @throws IOException
	 */
	private static boolean _resize(File originalFile, 
	        				String resizedFilePath,
	        				String resized, 
	        				Image resizedImage, 
	        				int width,
	        				int height,
	        				int startX,
	        				int startY,
	        				float quality) 
	throws IOException, ThumbnailException
	{	    
//	    long st = System.currentTimeMillis();
	    boolean chk = false;
		try{            
			// This code ensures that all the             
			// pixels in the image are loaded.   
			logger.debug("new filepath is " + resizedFilePath);
			logger.debug("resized filename is " + resized);
			Image temp = new ImageIcon(resizedImage).getImage();
		    // Create the buffered image.            
//			BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // Copy image to buffered image.            
			Graphics g = bufferedImage.createGraphics();
            // Clear background and paint the image.            
			g.setColor(Color.white);
//            g.fillRect(0, 0, temp.getWidth(null),temp.getHeight(null));
			g.fillRect(0, 0, width,height);

            g.drawImage(temp, startX, startY, null);
            g.dispose();
            
            // sharpen     
//            float[] filterarray = {-1,-1,-1,-1,8,-1,-1,-1,-1};
            bufferedImage = ImageFilter.filter3x3(bufferedImage, -0.4f, 2.6f);
            bufferedImage = ImageFilter.softenfloat(bufferedImage);
//            bufferedImage = ImageFilter.filter3x3(bufferedImage, filterarray);
            // bufferedImage = filter(bufferedImage, -1,5);
	        
            /* write the jpeg to a file */            
//			File file = new File(resized);
			File resizedFilePathFile = new File(resizedFilePath);
			
			if(!resizedFilePathFile.isDirectory()) {
			    if (!resizedFilePathFile.mkdir()) {
			        logger.error(resizedFilePathFile.getPath() +" 를 생성하지 못했습니다.");
			        return false;
			    } else {
			        logger.info(resizedFilePathFile.getPath() +" 를 생성하였습니다.");
			    }
			}
			File file = new File(resizedFilePath, resized);
			//if(!file.canWrite()) { return false;};
            FileOutputStream out = new FileOutputStream(file);
            /* encodes image as a JPEG data stream */     

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            com.sun.image.codec.jpeg.JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
            // writeParam = new JPEGImageWriteParam(null);
            // writeParam.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            //writeParam.setProgressiveMode(JPEGImageWriteParam.MODE_DEFAULT);
            param.setQuality(quality, true);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(bufferedImage);
            out.flush();
            out.close();
            chk = true;
            // logger.debug("Resize From : " + originalFile.getAbsolutePath() + " to " + file.getAbsolutePath() + " " +( System.currentTimeMillis() -  st)+ "ms");
        } catch (Throwable e) {       
        	Logging.trace(e);
			throw new ThumbnailException(e);
        }    
        return chk;
	}  
	
	/**
	 * quality 값의 범위를 조정 
	 * @param quality
	 * @return
	 */
	private static float _ChkQuality( float quality) {
	    if (quality > 1f) {
	        return 1;
	    } else if (quality < 0.5f) {
	        return 0.5f;
	    } else {
	        return quality;
	    }
	}
}

