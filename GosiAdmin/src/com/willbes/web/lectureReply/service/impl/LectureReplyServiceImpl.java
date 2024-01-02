package com.willbes.web.lectureReply.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.lectureReply.service.LectureReplyService;

@Service
public class LectureReplyServiceImpl  implements  LectureReplyService{
	
	
	@Autowired
	private LectureReplyDAO lectureReplydao;
	
	public List<HashMap<String, Object>> lectureReplyList(	Map<String, Object> searchMap){
		return lectureReplydao.lectureReplyList(searchMap);
	}
	public List<HashMap<String, Object>> lectureReplyDetailList(HashMap<String, Object> params){
		return lectureReplydao.lectureReplyDetailList(params);
	}
	
	public int lectureReplyDetailListCount(HashMap<String, Object> params){
		return lectureReplydao.lectureReplyDetailListCount(params);
	}
	
	public int lectureReplyListCount(Map<String, Object> searchMap){
		return lectureReplydao.lectureReplyListCount(searchMap);
	}
	public HashMap<String, Object> lectureReplyDetail(HashMap<String, Object> params){
		return lectureReplydao.lectureReplyDetail(params);
	}
	
	public void replyDelete(HashMap<String, Object> params){
		lectureReplydao.replyDelete(params);
	}
}
