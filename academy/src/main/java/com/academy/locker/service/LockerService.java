package com.academy.locker.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.LockerMapper;

@Service
public class LockerService {

	private LockerMapper lockerMapper;
	
	public LockerService(LockerMapper lockerMapper) {
		this.lockerMapper = lockerMapper;
	}
	
	public ArrayList<JSONObject> selectLockerList(LockerVO lockerVO) {
		return lockerMapper.selectLockerList(lockerVO);
	}
    public int selectLockerListTotCnt(LockerVO lockerVO) {
        return lockerMapper.selectLockerListTotCnt(lockerVO);
    }
	
	public JSONObject getLocker(LockerVO lockerVO) {
		return lockerMapper.getLocker(lockerVO);
	}
    public ArrayList<JSONObject> selectLockerNumList(LockerVO lockerVO) {
		return lockerMapper.selectLockerNumList(lockerVO);
    }

    public int getLockerRentSeq(LockerVO lockerVO) {
        return lockerMapper.getLockerRentSeq(lockerVO);
    }
    public String getOrderNo(OrdersVO ordersVO) {
        return lockerMapper.getOrderNo(ordersVO);
    }
    public void updateOrderSeq(LockerVO lockerVO) {
    	lockerMapper.updateOrderSeq(lockerVO);
    }

    public void insertOrders(OrdersVO ordersVO) {
    	lockerMapper.insertOrders(ordersVO);
    }
    public void insertOrderPay(OrdersVO ordersVO) {
    	lockerMapper.insertOrderPay(ordersVO);
    }
    public void insertOrderItem(OrdersVO ordersVO) {
    	lockerMapper.insertOrderItem(ordersVO);
    }
    
    public void insertLockerRent(LockerVO lockerVO) {
    	lockerMapper.insertLockerRent(lockerVO);
    }
    public void updateLockerNum(LockerVO lockerVO) {
    	lockerMapper.updateLockerNum(lockerVO);
    }

    public void insertOrderRefund(OrdersVO ordersVO) {
    	lockerMapper.insertOrderRefund(ordersVO);
    }
	public JSONObject selectLockerNumRentDetail(LockerVO lockerVO) {
		return lockerMapper.selectLockerNumRentDetail(lockerVO);
	}

	
	
	
	
	public JSONObject selectLockerNumRentOrderDetail(LockerVO lockerVO) {
		return lockerMapper.selectLockerNumRentOrderDetail(lockerVO);
	}
	public JSONObject selectLockerOrderDetail(LockerVO lockerVO) {
		return lockerMapper.selectLockerOrderDetail(lockerVO);
	}


	
    
    public void insertLocker(LockerVO lockerVO) {
    	lockerMapper.insertLocker(lockerVO);
    }

    public void updateLocker(LockerVO lockerVO) {
    	lockerMapper.updateLocker(lockerVO);
    }

    public void insertLockerNum(LockerVO lockerVO) {
    	lockerMapper.insertLockerNum(lockerVO);
    }

    public int selectLockerNumUseCount(LockerVO lockerVO) {
        return lockerMapper.selectLockerNumUseCount(lockerVO);
    }
    


	public ArrayList<JSONObject> selectLockerNumRentOrderList(LockerVO lockerVO) {
		return lockerMapper.selectLockerNumRentOrderList(lockerVO);
	}

    public void updateLockerFlag(LockerVO lockerVO) {
    	lockerMapper.updateLockerFlag(lockerVO);
    }

    public void updateLockerNumRent(LockerVO lockerVO) {
    	lockerMapper.updateLockerNumRent(lockerVO);
    }

    public void updateApprovals(LockerVO lockerVO) {
    	lockerMapper.updateApprovals(lockerVO);
    }

    public void updateLockerNumChange(LockerVO lockerVO) {
    	lockerMapper.updateLockerNumChange(lockerVO);
    }

    public void updateLockerNumReset(LockerVO lockerVO) {
    	lockerMapper.updateLockerNumReset(lockerVO);
    }

    public void updateLockerRentChange(LockerVO lockerVO) {
    	lockerMapper.updateLockerRentChange(lockerVO);
    }

    public void updateLockerNumRentRefund(LockerVO lockerVO) {
    	lockerMapper.updateLockerNumRentRefund(lockerVO);
    }

    public void deleteLockerRentByOrderId(LockerVO lockerVO) {
    	lockerMapper.deleteLockerRentByOrderId(lockerVO);
    }

    public void deleteLockerOrder(LockerVO lockerVO) {
    	lockerMapper.deleteLockerOrder(lockerVO);
    }

    public void insertOrderItemLocker(LockerVO lockerVO) {
    	lockerMapper.insertOrderItemLocker(lockerVO);
    }

    public void updateOrderApprovalsRefund(LockerVO lockerVO) {
    	lockerMapper.updateOrderApprovalsRefund(lockerVO);
    }

}