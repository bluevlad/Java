/*
 * @ SortUtil.java
 *
 * TYPE : 공통 클래스
 *
 *
 * 사용법
 *    - static method는 SortUtil.mehtod()로 호출한다.
 *    - 페이지정렬 업무와 관련된 유틸리티 클래스
 */

package com.willbes.cmm.util;

public class SortUtil{

    public SortUtil() {
    }

    public static String fnSort(String field_name, String sortfield, String sort_op, String selected_field_name) {
        StringBuffer sb = new StringBuffer();
        // 필드명과 선택된 필드명이 동일한지 체크한다
        if(sortfield.equals(selected_field_name) ) {
            // 역순 선택된 항목일때
            if( "1".equals(sort_op) || "DESC".equals(sort_op.toUpperCase())){
                sb.append("<a  href=\"javascript:fn_sort('"+selected_field_name+"');\">");
                sb.append(field_name);
                sb.append("<span class=point2>↑</span></a>");
                // 순차 선택된 항목일때
            } else if( "0".equals(sort_op) || "ASC".equals(sort_op.toUpperCase())){
                sb.append("<a  href=\"javascript:fn_sort('"+selected_field_name+"');\">");
                sb.append(field_name);
                sb.append("<span class=point2>↓</span></a>");
            }
        } else {
            // 선택되지 않은 경우
            sb.append("<a href=\"javascript:fn_sort('"+selected_field_name+"');\">");
            sb.append(field_name);
            sb.append("<span class=point9>↑</span></a>");
        }
        return sb.toString();
    }

    /**
     * displaySort(String fild_name, String sortfield, String sort_op, String selected_field_name)
     *  테이블 목록 정렬
     * @param        field_name - 화면에 뿌려지는 이름(제목, 날짜등)
     * @param        sortfield - 정렬할 항목(필드)
     * @param        sort_op - ASC, DESC
     * @param        selected_field_name - 항목(필드)
     * @return       String - 스크립트
     * @exception
     * @version
     */
    /**  사용예)
     *       String sortfield   = Util.nullToString(request.getParameter("sortfield"));
     *       String sort_op     = Util.nullToString(request.getParameter("sort_op"));
     *
     *     <tr height=26 class="th">
     *        <td align=center width=14%><%=SortUtil.displaySort("학과",sortfield, sort_op, "b.dept_name")%></td>
     *       <td align=center width=9%><%=SortUtil.displaySort("학번",sortfield, sort_op, "a.hakbun")%></td>
     *       <td align=center width=7%><%=SortUtil.displaySort("이름",sortfield, sort_op, "a.ko_name")%></td>
     *       <td align=center width=5%><%=SortUtil.displaySort("학년",sortfield, sort_op, "a.suup_hakyun")%></td>
     *       <td align=center><%=SortUtil.displaySort("주소",sortfield, sort_op, "juso")%></td>
     *       <td align=center width=7%><%=SortUtil.displaySort("학적상태",sortfield, sort_op, "c.ko_title")%></td>
     *       <td align=center width=7%><%=SortUtil.displaySort("등록상태",sortfield, sort_op, "deng_gbn")%></td>
     */

    public static String displaySort(String field_name, String sortfield, String sort_op, String selected_field_name) {
        StringBuffer sb = new StringBuffer();

        // 필드명과 선택된 필드명이 동일한지 체크한다
        if(sortfield.equals(selected_field_name) ) {
            // 역순 선택된 항목일때
            if( "1".equals(sort_op) || "DESC".equals(sort_op.toUpperCase())){
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','asc');\"><span class=arrow2>↑</span></a>");
                sb.append(field_name);
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','desc');\"><span class=tit_arrow>↓</span></a>");
                // 순차 선택된 항목일때
            } else if( "0".equals(sort_op) || "ASC".equals(sort_op.toUpperCase())){
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','asc');\"><span class=tit_arrow>↑</span></a>");
                sb.append(field_name);
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','desc');\"><span class=arrow2>↓</span></a>");
            }
        } else {
            // 선택되지 않은 경우
            sb.append("<a href=\"javascript:sort('"+selected_field_name+"','asc');\"><span class=arrow2>↑</span></a>");
            sb.append(field_name);
            sb.append("<a href=\"javascript:sort('"+selected_field_name+"','desc');\"><span class=arrow2>↓</span></a>");
        }
        return sb.toString();
    }

    public static String displaySort1(String field_name, String sortfield, String sort_op, String selected_field_name) {
        StringBuffer sb = new StringBuffer();

        // 필드명과 선택된 필드명이 동일한지 체크한다
        if(sortfield.equals(selected_field_name) ) {
            // 역순 선택된 항목일때
            if( "1".equals(sort_op) || "DESC".equals(sort_op.toUpperCase())) {
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','asc');\"><img src=\"/lifelong/images/ko/home/board/btn_brd_up.gif\" class=\"pr2\"/></a>"); //↑

                sb.append(field_name);
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','desc');\"><img src=\"/lifelong/images/ko/home/board/btn_brd_down.gif\" class=\"pl2\" /></a>"); //↓
                // 순차 선택된 항목일때
            } else if( "0".equals(sort_op) || "ASC".equals(sort_op.toUpperCase())){
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','asc');\"><img src=\"/lifelong/images/ko/home/board/btn_brd_up.gif\" class=\"pr2\" /></a>");//↑
                sb.append(field_name);
                sb.append("<a  href=\"javascript:sort('"+selected_field_name+"','desc');\"><img src=\"/lifelong/images/ko/home/board/btn_brd_down.gif\" class=\"pl2\" /></a>");//↓
            }
        } else {
            // 선택되지 않은 경우
            sb.append("<a href=\"javascript:sort('"+selected_field_name+"','asc');\"><img src=\"/lifelong/images/ko/home/board/btn_brd_up.gif\" class=\"pr2\" /></a>");//↑
            sb.append(field_name);
            sb.append("<a href=\"javascript:sort('"+selected_field_name+"','desc');\"><img src=\"/lifelong/images/ko/home/board/btn_brd_down.gif\" class=\"pl2\" /></a>");//↓
        }
        return sb.toString();
    }
}
