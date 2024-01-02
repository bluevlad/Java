package com.willbes.platform.util.file.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.platform.util.file.service.MultipartFileService;

@Service
public class MultipartFileServiceImpl  implements  MultipartFileService {

	@Autowired
	private MultipartFileDAO multipartfiledao;

	@Override
	public Object insertAttachFile(HashMap<String, Object> params) {
		return multipartfiledao.insertAttachFile(params);
	}
}
