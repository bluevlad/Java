package web.util.file.web;

import java.io.File;
import java.io.FileInputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.service.FileVO;
import web.util.CommonUtil;

@Controller
public class FileDownloadController implements ApplicationContextAware{
	@Autowired(required = false)
	private WebApplicationContext context = null;

	@Inject
	private FileSystemResource fsResource;	//DI

	@RequestMapping("imgFileView.do")
	public ResponseEntity<byte[]>  imgFileView(@RequestParam("path")String path) throws Exception{
		String rootPath = fsResource.getPath();

		FileInputStream fin = new FileInputStream(new File(rootPath + path));
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    return new ResponseEntity<byte[]>(IOUtils.toByteArray(fin), headers, HttpStatus.CREATED);
	}


	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = (WebApplicationContext)arg0;
	}
}