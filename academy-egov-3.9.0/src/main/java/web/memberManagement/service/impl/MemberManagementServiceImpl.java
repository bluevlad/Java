package web.memberManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import web.memberManagement.service.MemberManagementService;

@Service
public class MemberManagementServiceImpl  implements  MemberManagementService{

    @Autowired
    private MemberManagementDAO memberManagementdao;

    public List<HashMap<String, Object>> getMemberList(	Map<String, Object> searchMap){
        return memberManagementdao.getMemberList(searchMap);
    }

    public int getMemberListCount(Map<String, Object> searchMap){
        return memberManagementdao.getMemberListCount(searchMap);
    }

    public List<HashMap<String, Object>> getOldMemberList(	Map<String, Object> searchMap){
        return memberManagementdao.getOldMemberList(searchMap);
    }

    public int getOldMemberListCount(Map<String, Object> searchMap){
        return memberManagementdao.getOldMemberListCount(searchMap);
    }

    public int memberIdCheck(HashMap<String, String> params){
        return memberManagementdao.memberIdCheck(params);
    }

    public List<HashMap<String, String>> getCategoryList(){
        return memberManagementdao.getCategoryList();
    }

    public void memberInsertProcess(HashMap<String, String> params){
        String zipCd = String.valueOf(params.get("ZIP_CODE"));
        if(null != zipCd && !"".equals(zipCd)) {
            if(zipCd.length() == 5) {
                String areaCd = zipCd.substring(0, 2);
                if(null != areaCd && !"".equals(areaCd)) {
                    params.put("U_AREA", areaCd);
                } else {
                    params.put("U_AREA", "");
                }
            } else {
                params.put("U_AREA", "");
            }
        }
        memberManagementdao.memberInsertProcess(params);
        memberManagementdao.memberInfoInsertProcess(params);
        if(!String.valueOf(params.get("CATEGORY_CODE")).equals("")){
            //관심카테고리 등록
            memberManagementdao.memberCategoryDelete(params);
            memberManagementdao.memberCategoryInsert(params);
        }
    }
    public void memberCategoryDelete(HashMap<String, String> params){
        memberManagementdao.memberCategoryDelete(params);
    }
    public void memberCategoryInsert(HashMap<String, String> params){
        memberManagementdao.memberCategoryInsert(params);
    }

    public HashMap<String, Object> memberDetail(HashMap<String, Object> params){
        return memberManagementdao.memberDetail(params);
    }

    public HashMap<String, Object> oldMemberDetail(HashMap<String, Object> params){
        return memberManagementdao.oldMemberDetail(params);
    }

    public void memberUpdateProcess(HashMap<String, String> params){
        String zipCd = String.valueOf(params.get("ZIP_CODE"));
        if(null != zipCd && !"".equals(zipCd)) {
            if(zipCd.length() == 5) {
                String areaCd = zipCd.substring(0, 2);
                if(null != areaCd && !"".equals(areaCd)) {
                    params.put("U_AREA", areaCd);
                } else {
                    params.put("U_AREA", "");
                }
            } else {
                params.put("U_AREA", "");
            }
        }
        memberManagementdao.memberUpdateProcess(params);
        memberManagementdao.memberInfoUpdateProcess(params);

        if(!String.valueOf(params.get("CATEGORY_CODE")).equals("")){
            //관심카테고리 등록
            memberManagementdao.memberCategoryDelete(params);
            memberManagementdao.memberCategoryInsert(params);
        }
    }
    public void memberDelete(HashMap<String, String> params){
        memberManagementdao.memberDelete(params);
        memberManagementdao.memberInfoDelete(params);
    }

    public List<HashMap<String, String>> searchZipCode(HashMap<String, String> params){
        return memberManagementdao.searchZipCode(params);
    }

    public void memberSendMessage(HashMap<String, String> params){
        memberManagementdao.memberSendMessage(params);
    }

    public HashMap<String, String> getMemberAdminEmail(HashMap<String, String> params){
        return memberManagementdao.getMemberAdminEmail(params);
    }

    public void MemberAdminInsertEmail(HashMap<String, String> params){
        memberManagementdao.MemberAdminInsertEmail(params);
    }

    public List<HashMap<String, String>> getMemberStatList(HashMap<String, String> params){
        return memberManagementdao.getMemberStatList(params);
    }
    
    public void OldmemberDeleteProcess(HashMap<String, String> params){
        memberManagementdao.OldmemberDeleteProcess(params);
    }
    
    public List<HashMap<String, Object>> getPrimeMemberList(	Map<String, Object> searchMap){
        return memberManagementdao.getPrimeMemberList(searchMap);
    }

    public int getPrimeMemberListCount(Map<String, Object> searchMap){
        return memberManagementdao.getPrimeMemberListCount(searchMap);
    }
}
