package maf.web.context.support;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import maf.beans.FileInfo;
import maf.context.i18n.MafResourceStore;
import maf.util.FileUtils;
import maf.util.StringUtils;
import maf.web.context.MafConfig;

public class ResourceLoaderByXML {
	private static ResourceLoaderByXML  _instance = null;
	
	private ResourceLoaderByXML() {
		
	}

	public static synchronized ResourceLoaderByXML getInstance() {

		if (_instance == null) {
			_instance = new ResourceLoaderByXML();
		}
		return _instance;
	}

	/**
	 * Resource Config 설정 
	 * @param path
	 * @param rulefile
	 */
	public synchronized void configureResource(ServletContext servletContext) {
		String path = "/WEB-INF/maf-resource/";
		String rulefile =  "/WEB-INF/maf-config/resourceDigesterRules.xml";
		
		System.out.println(">>>> MVC Resource Confiure Start ");
		String configFilePath = servletContext.getRealPath(path);
		FileInfo[] files = FileUtils.getFileList(configFilePath);

		FileInfo configFile = null;
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				configFile = files[i];

				if (MafConfig.reg_XML.match(configFile.getFilename())) {
					String filename = StringUtils.stripFilenameExtension(configFile.getFilename());
					if (filename != null) {
						try {

							configureResourceConfig(filename, configFile.getFullFileName(),
													servletContext.getRealPath(rulefile));
						} catch (Exception e) {
						}
					}
					
				} else {
					System.out.println("     [" + configFile.getFilename() + "] is not xml file ");
				}
			}
		} else {
			System.out.println(" !!! files is null ");
		}

		System.out.println("<<<< MVC Resource Confiure  Finished ");
		
	}

	/**
	 * ServletPath에대한 설정정보 configFilePath의 xml파일을 읽어 드린다.
	 * 
	 * @param servletPath
	 * @param configFileName
	 * @throws ServletException
	 */
	private synchronized void configureResourceConfig(String filename, String configFileName, String ruleFilePath) {
		try {
			MafResourceStore resourceStore = MafResourceStore.getInstance();
			resourceStore.addBundle2(filename, configFileName, ruleFilePath);
			//System.out.println("        configure on " + filename + "\n       ConfigFile is " + configFileName);
		} catch (Exception e) {
			System.out.println("        configure failue on " + filename + "\n      ConfigFile is " + configFileName);
		}

	}
}
