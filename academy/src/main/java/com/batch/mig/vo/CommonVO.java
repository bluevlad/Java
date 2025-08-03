package com.batch.mig.vo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonVO {

    private final Logger log = Logger.getLogger(getClass());
    private final String DEFAULT_DATE_DELIMITER = "-";
    private final String DEFAULT_STRING_DELIMITER = ",";
    private final String DEFAULT_THUMBNAIL_PREPATH = "/ecm/resources/thumnail";

    /**
     * thumbnail파일경로반환
     */
    public String setThumbnailFullPath(String path) {
        if (StringUtils.isBlank(path)) return "";
        return (new StringBuffer()).append(this.DEFAULT_THUMBNAIL_PREPATH)
                                   .append(path)
                                   .toString();
    }

    /**
     * 리스트반환(String값을 split)
     */
    public List<String> getListFromStringBySplit(String str) {
        return (StringUtils.isNotEmpty(str)) ?
                Arrays.stream(str.split(this.DEFAULT_STRING_DELIMITER)).collect(Collectors.toList()): new ArrayList<String>();
    }

    /**
     * String리스트 길이 확인(delimiter구분자포함시)
     */
    public boolean checkStringSplitListMaxCount(String str, int MAX_LENGTH) {
        if (StringUtils.isBlank(str)) return false;
        try {
            return (MAX_LENGTH > str.split(this.DEFAULT_STRING_DELIMITER).length);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }


    /**
     * 일자포맷 변경 
     */
    public String getDateFormatString(String strDate) {
        String[] strArr = null, dateArr = null;
        StringBuffer strBuf = new StringBuffer();

        try {
            // 날짜조회예: 25/1월/2022 16:00:26
            dateArr = strDate.split(" ");

            // 정상: 25/1월/2022 16:00:26 --> (날짜 일자)
            if (dateArr == null || dateArr.length == 0 || dateArr.length != 2)
                throw new RuntimeException("날짜 정보가 올바르지 않습니다.");

            // 정상: 25/1월/2022
            strArr = dateArr[0].split("/");
            if (strArr == null || strArr.length == 0 || strArr.length != 3)
                throw new RuntimeException("일자 정보가 올바르지 않습니다.");

            // YYYY-MM-DD로 변경
            strBuf.append(strArr[2]).append(this.DEFAULT_DATE_DELIMITER);
            // 월정보: 1월 --> 01로 변경
            strArr[1] = strArr[1].replaceAll("월", this.DEFAULT_DATE_DELIMITER);
            if (StringUtils.isBlank(strArr[1])) throw new RuntimeException("월정보가 올바르지 않습니다.");
            if (strArr[1].length() == 2) strArr[1] = "0" + strArr[1];

            // '일자 시간'으로 변경
            strBuf.append(strArr[1]).append(strArr[0])                      // 일자
                  .append(" ").append(dateArr[1]);                          // 시간

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            strArr = null;
            dateArr = null;
        }
        return strBuf.toString();
    }
}
