package maf.lib.xml;

import java.io.IOException;
import java.io.Writer;

/**
 * Writer/StringBuffer 를 이용 
 * @author bluevlad
 *
 */
public class XmlSafeWriter {
	private final Writer writer;
	private StringBuffer sb = null;
	public final static String TYPE_WRITER = "WRITER";
	public final static String TYPE_STRINGBUFFER = "SB";
	private String type = null;

	public XmlSafeWriter(Writer writer) {
		this.writer = writer;
		this.type = TYPE_WRITER;
	}

	public XmlSafeWriter(StringBuffer sb) {
		this.writer = null;
		this.sb = sb;
		this.type = TYPE_STRINGBUFFER;
	}

	public XmlSafeWriter append(String value) throws IOException {
		if(TYPE_WRITER.equals(this.type)) {
			this.writer.write(String.valueOf(value));
		} else {
			this.sb.append(String.valueOf(value));
		}
		return this;
	}
	
	/**
	 * StringBuffer 이용시 String 값을 return 
	 * @return
	 */
	public String getString() {
		return this.sb.toString();
	}
}
