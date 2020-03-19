/*
 * PrdManagerDB.java, @ 2005-05-03
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.stat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * @author xxx
 *
 */
public class StatDB  extends CommonDB{
    private static StatDB instance;


    private StatDB() {
    }

    public static synchronized StatDB getInstance() {
        if (instance == null)
            instance = new StatDB();
        return instance;
    }

    // 매출현황
    public List getStatSale(MdbDriver oDb, String st_dt, String end_dt) throws MdbException {
        
        List at = new ArrayList();

        String sql = "  select to_char(reg_dt, 'YYYY-MM-DD') as reg_dt,"   +
                        "       pay_ok_date,"   +
                        "         ord_no,"   +
                        "       (select nm from mst_user where usn = mal_orders.usn) as usnm,"   +
                        "         total, DECODE(process, '2', pay, '8', pay, 0) AS pay,"   +
                        "       decode(pay_div, 'Card', '카드결제', 'Onbank', '무통장입금', 'DirectBank', '온라인입금', '카드결제') as pay_div,"   +
                        "       (select count(paf_no) from mal_payoff where ord_no = mal_orders.ord_no) as cnt,"   +
                        "       (select nvl(sum(amount),0) from mal_payoff where ord_no = mal_orders.ord_no) as sum,"   +
                        "     (select nvl(sum(e.price),0)"   +
                        "        from mal_orders a, mal_ord_detail b, book_coupon c, lec_req d, mal_book e"   +
                        "        where a.ord_no = b.ord_no"   +
                        "        and b.cop_no = c.cop_no"   +
                        "        and c.cop_no = d.cop_no"   +
                        "        and b.book_code = e.book_code"   +
                        "        and a.ord_no = mal_orders.ord_no"   +
                        "        and d.reqstat = 'E'"   +
                        "     ) as lecsum"   +
                        "   from mal_orders "+
                        " where rownum >= 0 ";
                        if (!"".equals(st_dt) && (st_dt != null)){
                            sql = sql + "                 and to_char(reg_dt,'YYYY-MM-DD') >= :st_dt";
                            at.add(st_dt);
                        }
                        if (!"".equals(end_dt) && (end_dt != null)){
                            sql = sql + "                 and to_char(reg_dt,'YYYY-MM-DD') <= :end_dt";
                            at.add(end_dt);
                        }
                        sql = sql + "  order by ord_no desc";
        
        return oDb.selector(Map.class, sql, at);
    }
    
    // 입출금현황
    public List getStatReceipt(MdbDriver oDb, String st_dt, String end_dt) throws MdbException {
        
        List at = new ArrayList();

        String sql = " select TO_CHAR(mo.reg_dt, 'YYYY-MM') as mon, mb.btype,"   +
                        "        sum(md.cnt) as cnt,"   +
                        "        sum(md.cnt*mb.PRICE) as sum "   +
                        "  from mal_orders mo, mal_ord_item mi, mal_ord_detail md, mal_book mb"   +
                        "  where mo.ord_no = mi.ord_no "   +
                        "  and mi.ord_no = md.ord_no " +
                        "  and mi.oit_no = md.oit_no "   +
                        "  and md.book_code = mb.book_code" +
                        "  and mo.process in (2,8,9) " +
                        "  and mb.btype in ('1','2','4') ";
                        if (!"".equals(st_dt) && (st_dt != null)){
                            sql = sql + "                 and to_char(mo.reg_dt,'YYYY-MM-DD') >= :st_dt";
                            at.add(st_dt);
                        }
                        if (!"".equals(end_dt) && (end_dt != null)){
                            sql = sql + "                 and to_char(mo.reg_dt,'YYYY-MM-DD') <= :end_dt";
                            at.add(end_dt);
                        }
                        sql = sql + "  group by TO_CHAR(mo.reg_dt, 'YYYY-MM'), mb.btype";
        
        return oDb.selector(Map.class, sql, at);
    }
    
    // 수강현황
    public List getStatLec(MdbDriver oDb, String st_dt, String end_dt) throws MdbException {
        
        List at = new ArrayList();

        String sql = " select b.mon,"   +
                        "           b.sjtcode, b.cnt,"   +
                        "           b.lectype, b.lecnumb,"   +
                        "           a.sjtname, a.crscode"   +
                        "  from sjt_mst a, (select TO_CHAR(lr.start_dt, 'YYYY-MM') as mon,"   +
                        "                          lm.sjtcode, count(lm.sjtcode) as cnt,"   +
                        "                          lm.lectype, lr.lecnumb "   +
                        "                 from lec_mst lm, lec_req lr"   +
                        "                 where lm.LECCODE = lr.LECCODE";
                        if (!"".equals(st_dt) && (st_dt != null)){
                        	sql = sql + "                 and to_char(lr.start_dt,'YYYY-MM-DD') >= :st_dt ";
                            at.add(st_dt);
                        }
                        if (!"".equals(end_dt) && (end_dt != null)){
                            sql = sql + "                 and to_char(lr.start_dt,'YYYY-MM-DD') <= :end_dt ";
                            at.add(end_dt);
                        }
                        sql = sql + "                 group by TO_CHAR(lr.start_dt, 'YYYY-MM'), lm.sjtcode, lm.lectype, lr.lecnumb) b"   +
                        "  where a.sjtcode = b.sjtcode";
        
        return oDb.selector(Map.class, sql, at);
    }

    // 시험별 최초 주문자현황
    public List getStatLic(MdbDriver oDb, String lic_code, String st_dt, String end_dt) throws MdbException {
        
        List at = new ArrayList();

        String sql = " SELECT   mo.pay_ok_date, mo.usn, mu.nm, mu.pin, mo.ord_no, mo.pay"   +
        "     FROM (SELECT *"   +
        "             FROM (SELECT usn, mo.ord_no, pay_ok_date,"   +
        "                          RANK () OVER (PARTITION BY usn ORDER BY pay_ok_date, mo.ord_no)"   +
        "                                                                      ord_rank"   +
        "                     FROM mal_orders mo,"   +
        "                          (SELECT DISTINCT ord_no"   +
        "                                      FROM mal_ord_detail md, mal_book mb"   +
        "                                     WHERE md.cop_no IS NOT NULL";
        if (!"".equals(lic_code) && (lic_code != null)){

        	sql = sql + "                                       AND mb.lic_code = :lic_code";
            at.add(lic_code);
        }
        sql = sql + "                                       AND md.book_code = mb.book_code"   +
        "                                       AND md.cop_no IS NOT NULL";
        if (!"".equals(lic_code) && (lic_code != null)){
        	sql = sql + "                                       AND mb.lic_code = :lic_code";
            at.add(lic_code);
        }
        sql = sql + ") md" +
        "                    WHERE mo.ord_no = md.ord_no) moi"   +
        "            WHERE ord_rank = 1) moi,"   +
        "          mal_orders mo,"   +
        "          mst_user mu"   +
        "    WHERE mo.ord_no = moi.ord_no"   +
        "      AND mo.usn = mu.usn";
        if (!"".equals(st_dt) && (st_dt != null)){
        	sql = sql + "      AND TO_DATE (mo.pay_ok_date, 'YYYY-MM-DD') BETWEEN TO_DATE (:st_dt, 'YYYY-MM-DD') AND TO_DATE (:end_dt, 'YYYY-MM-DD') + 1";
            at.add(st_dt);
            at.add(end_dt);
        }
        sql = sql + " ORDER BY mo.pay_ok_date ASC, mu.nm ASC    "  ;

        return oDb.selector(Map.class, sql, at);
    }

   // 상품별매출현황
    public List getStatBook(MdbDriver oDb, String st_dt, String end_dt) throws MdbException {
        
        List at = new ArrayList();

        String sql = " select b.mon,"   +
                        "        a.book_code, a.book_name,"   +
                        "       decode(a.btype, '1', '도서', '2', '강의', '3', '증명서', '4', 'CD', '도서') as btype, " +
                        "      a.lic_code,"   +
                        "      b.sum, a.price"   +
                        "  from mal_book a, (select TO_CHAR(mo.reg_dt, 'YYYY-MM') as mon, md.book_code,"   +
                        "                          sum(md.cnt) as sum"   +
                        "                    from mal_orders mo, mal_ord_item mi, mal_ord_detail md, mal_book mb"   +
                        "                    where mo.ord_no = mi.ord_no"   +
                        "                    and mi.ord_no = md.ord_no " +
                        "                    and mi.oit_no = md.oit_no "   +
                        "                    and md.book_code = mb.book_code"   +
                        "                    /*and mb.btype in ('1', '4')*/ " +
                        "                    and mo.process = 2";
                        if (!"".equals(st_dt) && (st_dt != null)){
                            sql = sql + "                 and to_char(mo.reg_dt,'YYYY-MM-DD') >= :st_dt";
                            at.add(st_dt);
                        }
                        if (!"".equals(end_dt) && (end_dt != null)){
                            sql = sql + "                 and to_char(mo.reg_dt,'YYYY-MM-DD') <= :end_dt";
                            at.add(end_dt);
                        }
                        sql = sql + "                    group by TO_CHAR(mo.reg_dt, 'YYYY-MM'), md.book_code) b"   +
                        "  where a.book_code = b.book_code"  ;
        
        return oDb.selector(Map.class, sql, at);
    }
   
    // 증명서발급현황
    public List getStatCert(MdbDriver oDb, String st_dt, String end_dt) throws MdbException {
        
        List at = new ArrayList();

        String sql = " select b.mon,"   +
                        "        a.book_code, a.book_name,"   +
                        "      a.lic_code,"   +
                        "      b.sum, a.price"   +
                        "  from mal_book a, (select TO_CHAR(mo.reg_dt, 'YYYY-MM') as mon, md.book_code,"   +
                        "                          sum(md.cnt) as sum"   +
                        "                    from mal_orders mo, mal_ord_item mi, mal_ord_detail md, mal_book mb"   +
                        "                    where mo.ord_no = mi.ord_no"   +
                        "                    and mi.ord_no = md.ord_no" +
                        "                    and mi.oit_no = md.oit_no "   +
                        "                    and md.book_code = mb.book_code"   +
                        "                    and mb.btype in ('3')";
                        if (!"".equals(st_dt) && (st_dt != null)){
                            sql = sql + "                 and to_char(mo.reg_dt,'YYYY-MM-DD') >= :st_dt";
                            at.add(st_dt);
                        }
                        if (!"".equals(end_dt) && (end_dt != null)){
                            sql = sql + "                 and to_char(mo.reg_dt,'YYYY-MM-DD') <= :end_dt";
                            at.add(end_dt);
                        }
                        sql = sql + "                    group by TO_CHAR(mo.reg_dt, 'YYYY-MM'), md.book_code) b"   +
                        "  where a.book_code = b.book_code"  ;
        
        return oDb.selector(Map.class, sql, at);
    }
   
}
