/*
 * Created on 2005. 11. 9.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.util;

import maf.lib.regexTool;
import maf.web.multipart.UploadedFile;

import org.apache.regexp.RE;

public class MHtmlAreaLib {
	private static RE RE_IMAGE = new RE("^image/");

	/**
	 * mHtmlArea에서 온 html content를 정리 한다..
	 * 특히 file 경로
	 * @param aFiles
	 * @param c_content
	 * @param URL
	 * @return
	 */
	public static String ArrangeContents(UploadedFile[] aFiles, String c_content, String URL) {
		if (aFiles != null) {
			for (int i = 0; i < aFiles.length; i++) {
				if (RE_IMAGE.match(aFiles[i].getContentType())) {
					String oFileName = aFiles[i].getOriginalFileName().replaceAll(" ", "%20");
					String regex = "file:///[^\".]+" + maf.lib.regexTool.toNormalStr(oFileName);
					org.apache.regexp.RE reg_NL = new org.apache.regexp.RE(regex);
					StringBuffer sT = new StringBuffer(50);
					sT.append(URL);
					sT.append("/");
					sT.append(HTMLUtil.rawURLEncode(aFiles[i].getNewfilename()));
					c_content = reg_NL.subst(c_content, sT.toString());
				}
			}
		}

		return c_content;
	}
}
