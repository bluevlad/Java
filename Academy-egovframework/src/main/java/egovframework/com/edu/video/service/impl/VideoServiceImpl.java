package egovframework.com.edu.video.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.edu.video.service.VideoService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 시험문제관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2024.05
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일       			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2024.05     			rainend          최초 생성
 * </pre>
 */
@Service("videoService")
public class VideoServiceImpl extends EgovAbstractServiceImpl implements VideoService {

	@Resource(name="videoDAO")
	private VideoDAO videoDAO;
	
}
