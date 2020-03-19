package maf.lib.xml;

import java.io.IOException;
import java.io.Writer;
import java.util.Stack;

import maf.util.StringUtils;
import maf.web.tags.TagWriter;

/**
 * HTML Writer를 이용해 만든 Xml Writer
 * @author bluevlad
 *
 */
public class XmlWriter  {
	/**
	 * Stores {@link TagStateEntry tag state}. Stack model naturally supports tag nesting.
	 */
	protected Stack tagState = new Stack();
	
	private final XmlSafeWriter writer;
	
	/**
	 * Adds the supplied tag name to the {@link #tagState tag state}.
	 */
	protected void push(String tagName) {
		this.tagState.push(new TagStateEntry(tagName));
	}

	public XmlWriter(Writer writer) {
		this.writer = new XmlSafeWriter(writer);
	}
	
	public XmlWriter(StringBuffer sb) {
		this.writer = new XmlSafeWriter(sb);
	}
	
	/**
	 * Holds state about a tag and its rendered behaviour.
	 */
	protected static final class TagStateEntry {
		private final String tagName;
		private boolean blockTag;

		public TagStateEntry(String tagName) {
			this.tagName = tagName;
		}

		public String getTagName() {
			return tagName;
		}

		public boolean isBlockTag() {
			return blockTag;
		}

		public void markAsBlockTag() {
			this.blockTag = true;
		}
	}
	
	
	/**
	 * Note: This element neither has attached source nor attached Javadoc and hence no information could be found.
	 * @param encording
	 * @throws IOException
	 */
	public void setEncoding(String encording) throws IOException {
		//<?xml version="1.0" encoding="utf-8"?>
		this.writer.append("<?xml version=\"1.0\" encoding=\"").append(encording).append("\" ?>");
	}
	/**
	 * <!DOCTYPE maf-command PUBLIC "-//UBQ//DTD maf-command config XML V1.0//EN"  "resourceBundle.dtd">
	 * @param encording
	 * @throws IOException
	 */
	public void setDocType(String doctype) throws IOException {
//		<!DOCTYPE maf-command PUBLIC "-//UBQ//DTD maf-command config XML V1.0//EN"  "resourceBundle.dtd">
		this.writer.append("<!DOCTYPE ").append(doctype).append(">");
	}
	
	/**
	 * Starts a new tag with the supplied name. Leaves the tag open so
	 * that attributes, inner text or nested tags can be written into it.
	 * @see #endTag()
	 */
	public void startTag(String tagName) throws IOException {
		if (inTag()) {
			closeTagAndMarkAsBlock();
		}
		push(tagName);
		this.writer.append("<").append(tagName);
	}

	/**
	 * Writes an XML attribute with the specified name and value.
	 * <p>Be sure to write all attributes <strong>before</strong> writing
	 * any inner text or nested tags.
	 * @throws IllegalStateException if the opening tag is closed
	 */
	public void setAttribute(String attributeName, String attributeValue)
	        throws IOException {
		if (currentState().isBlockTag()) {
			throw new IllegalStateException(
			                                "Cannot write attributes after opening tag is closed.");
		}
		this.writer.append(" ").append(attributeName).append("=\"")
		           .append(StringUtils.escapeXml(attributeValue)).append("\"");
		
	}

	/**
	 * Writes an HTML attribute if the supplied value is not <code>null</code>
	 * or zero length.
	 * @see #setAttribute(String, String)
	 */
	public void setOptionalAttributeValue(String attributeName, String attributeValue)
	        throws IOException {
		if (StringUtils.hasText(attributeValue)) {
			setAttribute(attributeName, attributeValue);
		}
	}

	/**
	 * 현재 열려 있는 Tag를 닫고(필요 하다면) inner text를 추가함
	 * escapeXML이 true 면 value값을 escapeXml하여 만듬 
	 * @param value
	 * @param escapeXml
	 * @throws IOException
	 */
	public void appendValue(String value, boolean escapeXml) throws IOException {
		if (!inTag()) {
			throw new IllegalStateException(
			                                "Cannot write tag value. No open tag available.");
		}
		closeTagAndMarkAsBlock();
		if(!escapeXml) {
			this.writer.append(value);
		} else {
			this.writer.append(StringUtils.escapeXml(value));
		}
	}
	/**
	 * 현재 열려 있는 Tag를 닫고(필요 하다면) inner text를 추가함
	 * value값을 escapeXml하여 만듬 
	 * @param value
	 * @param escapeXml
	 * @throws IOException
	 */
	public void appendValue(String value) throws IOException {
		this.appendValue(value, true);
	}
	/**
	 * Indicates that the currently open tag should be closed and marked
	 * as a block level element.
	 * <p>Useful when you plan to write additional content in the body
	 * outside the context of the current {@link TagWriter}.
	 */
	public void forceBlock() throws IOException {
		if (currentState().isBlockTag()) {
			return; // just ignore since we are already in the block
		}
		closeTagAndMarkAsBlock();
	}

	/**
	 * Closes the current tag.
	 * <p>Correctly writes an empty tag if no inner text or nested tags
	 * have been written.
	 */
	public void endTag() throws IOException {
		if (!inTag()) {
			throw new IllegalStateException(
			                                "Cannot write end of tag. No open tag available.");
		}
		if (currentState().isBlockTag()) {
			// writing the end of the block - the opening tag was closed earlier.
			this.writer.append("</").append(currentState().getTagName()).append(">");
		} else {
			this.writer.append(" />");
		}
		this.tagState.pop();
	}

	
	/**
	 * Closes the current opening tag and marks it as a block tag.
	 */
	private void closeTagAndMarkAsBlock() throws IOException {
		if (!currentState().isBlockTag()) {
			currentState().markAsBlockTag();
			this.writer.append(">");
		}
	}

	private boolean inTag() {
		return this.tagState.size() > 0;
	}

	private TagStateEntry currentState() {
		return (TagStateEntry) this.tagState.peek();
	}
	
	public String getString() {
		return this.writer.getString();
	}

	
}
