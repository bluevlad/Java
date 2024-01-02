package com.willbes.cmm.web;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.willbes.cmm.service.CmmUseService;
import com.willbes.platform.util.CommonUtil;

/**
 * @Class Name : EgovComUtlController.java
 * @Description : 공통유틸리티성 작업을 위한 Controller
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.03.02    조재영          최초 생성
 * @ 2011.10.07    이기하          .action -> .do로 변경하면서 동일 매핑이 되어 삭제처리
 *
 *  @author 공통서비스 개발팀 조재영
 *  @since 2009.03.02
 *  @version 1.0
 *  @see
 *
 */
@Controller
public class EgovComUtlController {

    //@Resource(name = "egovUserManageService")
    //private EgovUserManageService egovUserManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "CmmUseService")
    private CmmUseService cmmUseService;

    /**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value="/EgovPageLink.do")
	public String moveToPage(@RequestParam("link") String linkPage){
		String link = linkPage;
		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (linkPage==null || linkPage.equals("")){
			link="egovframework/com/cmm/egovError";
		}
		return link;
	}

    /**
	 * validato rule dynamic Javascript
	 */
	@RequestMapping("/validator.do")
	public String validate(){
		return "com/cmm/validator";
	}

    /**
     * @Method Name : selectCmmCdList
     * @작성일 : 2014. 10.
     * @Method 설명 :  공통 코드 리턴
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/cmm/select_cmmcd_list.json")
    @ResponseBody
    public List<HashMap<String, String>> selectCmmCdList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        params.put("SYS_CD", CommonUtil.isNull(request.getParameter("SYS_CD"), ""));
        List<HashMap<String, String>> cmmCdList = cmmUseService.selectCmmCodeMap(params);

        return cmmCdList;
    }

    /**
     * @Method Name : selectCmmCdMultiCondWthArray
     * @작성일 : 2014. 10.
     * @Method 설명 :  공통 코드 리턴
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/cmm/select_cmmcd_list_byarray.json")
    @ResponseBody
    public List<HashMap<String, String>> selectCmmCdMultiCondWthArray(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String[]> params = new  HashMap<String, String[]>();
        String param = CommonUtil.isNull(request.getParameter("SYS_CD"), "");
        List<HashMap<String, String>> cmmCdList = null;
        if(null != param && !"".equals(param)) {
            String[] param_arr = param.split(",");
            if(param_arr.length > 1) {
                params.put("array", param_arr);
                cmmCdList = cmmUseService.selectCmmCdMultiCondWthArray(params);
            } else {
                HashMap<String, String> p = new  HashMap<String, String>();
                p.put("SYS_CD", CommonUtil.isNull(request.getParameter("SYS_CD"), ""));
                cmmCdList = cmmUseService.selectCmmCodeMap(p);
            }
        }

        return cmmCdList;
    }

}