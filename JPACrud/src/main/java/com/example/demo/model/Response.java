package com.example.demo.model;

import java.io.Serializable;

public class Response implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private int responseCode;

	private String responseMsg;

	private Object jData;

	private String Data;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getRespondMsg() {
		return responseMsg;
	}

	public void setRespondMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public Object getjData() {
		return jData;
	}

	public void setjData(Object jData) {
		this.jData = jData;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

}
