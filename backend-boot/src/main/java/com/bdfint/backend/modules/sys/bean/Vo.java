package com.bdfint.backend.modules.sys.bean;

import java.util.List;

/**
 * Created by lsl on 2018/1/30.
 */
public class Vo {
	private String orderId;
    public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private List<CrmNorthCard> list;

    public List<CrmNorthCard> getList() {
        return list;
    }

    public void setList(List<CrmNorthCard> list) {
        this.list = list;
    }
}
