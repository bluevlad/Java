/**
 * 
 */
package web.axis;

import java.io.*;
import java.net.*; 
/**
 * @author axissoft,inc
 *
 */
public class AxLicense {
	public AxLicense(String requestUrl) throws IOException
	{
		out_ = "";
		URL url = new URL(requestUrl.toString());
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
        	out_ += inputLine;
        }
        in.close();
	}
	public String getLicense()
	{
		return out_;
	}
	
	private String out_;
}
