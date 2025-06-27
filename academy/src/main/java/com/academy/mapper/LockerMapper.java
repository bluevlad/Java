package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.locker.service.LockerVO;
import com.academy.locker.service.OrdersVO;

/**
 * 사물함정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2025.02.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.02.01  		rainend          최초 생성
 * </pre>
 */
@Mapper
public interface LockerMapper {

    /**
     * @param CommonVO 검색조건
     * @return List 사물함 목록정보
     */
	public ArrayList<JSONObject> selectLockerList(LockerVO lockerVO);
	public int selectLockerListTotCnt(LockerVO lockerVO);

	public JSONObject getLocker(LockerVO lockerVO);
	public ArrayList<JSONObject> selectLockerNumList(LockerVO lockerVO);

    public int getLockerRentSeq(LockerVO lockerVO);
    public String getOrderNo(OrdersVO ordersVO);
    public void updateOrderSeq(LockerVO lockerVO);

    public void insertOrders(OrdersVO ordersVO);
    public void insertOrderItem(OrdersVO ordersVO);
    public void insertOrderPay(OrdersVO ordersVO);

    public void insertLockerRent(LockerVO lockerVO);
    public void updateLockerNum(LockerVO lockerVO);

    public void insertOrderRefund(OrdersVO ordersVO);
	public JSONObject selectLockerNumRentDetail(LockerVO lockerVO);

	
	public JSONObject selectLockerNumRentOrderDetail(LockerVO lockerVO);
    public JSONObject selectLockerOrderDetail(LockerVO lockerVO);

    
	
    public void insertLocker(LockerVO lockerVO);

    public void updateLocker(LockerVO lockerVO);

    public void insertLockerNum(LockerVO lockerVO);

    public int selectLockerNumUseCount(LockerVO lockerVO);


	public ArrayList<JSONObject> selectLockerNumRentOrderList(LockerVO lockerVO);

    public void updateLockerFlag(LockerVO lockerVO);

    public void updateLockerNumRent(LockerVO lockerVO);

    public void updateApprovals(LockerVO lockerVO);

    public void updateLockerNumChange(LockerVO lockerVO);

    public void updateLockerNumReset(LockerVO lockerVO);

    public void updateLockerRentChange(LockerVO lockerVO);

    public void updateLockerNumRentRefund(LockerVO lockerVO);

    public void deleteLockerRentByOrderId(LockerVO lockerVO);

    public void deleteLockerOrder(LockerVO lockerVO);

    public void insertOrderItemLocker(LockerVO lockerVO);

    public void updateOrderApprovalsRefund(LockerVO lockerVO);

}
