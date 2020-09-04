package com.example.web.entity;

import java.io.Serializable;
import java.util.List;

public class BootTableResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int total;  // 页面总条数

	private List<T> rows;  // 对应页面结果集

	public BootTableResult() {
		super();
	}

	public BootTableResult(int total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
