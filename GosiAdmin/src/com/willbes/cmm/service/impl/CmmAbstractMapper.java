/**
 *
 */
package com.willbes.cmm.service.impl;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * EgovComAbstractMapper.java 클래스
 *
 * @author kckim
 * @since 2014. 9. 18.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2014. 9. 18.   kckim        최초 생성
 * </pre>
 */
public abstract class CmmAbstractMapper extends EgovAbstractMapper {

    /**
     * Annotation 형식으로 sqlSession(SqlSessionFactoryBean)을 받아와
     * 이를 super(SqlSessionDaoSupport)의 setSqlSessionFactory 메서드를 호출하여 설정해 준다.
     * <p>
     * SqlSessionTemplate이 지정된 경우된 경우 이 SqlSessionFactory가 무시된다. (Batch 처리를 위해서는 SqlSessionTemplate가 필요)
     *
     *
     * @param sqlSession SqlSessionFactory로 MyBatis와의 연계를 위한 기본 클래스
     */
    @Resource(name = "sqlSession")
    public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
        super.setSqlSessionFactory(sqlSession);
    }

}
