package com.ge.webcrawler.model;

import java.io.Serializable;
import java.util.List;

public class ResponseDTO implements Serializable{
	
	private static final long serialVersionUID = -8282893L;
	
	private List<String> success;
	private List<String> skipped;
	private List<String> error;
	
	public List<String> getSuccess() {
		return success;
	}
	public void setSuccess(List<String> success) {
		this.success = success;
	}
	public List<String> getSkipped() {
		return skipped;
	}
	public void setSkipped(List<String> skipped) {
		this.skipped = skipped;
	}
	public List<String> getError() {
		return error;
	}
	public void setError(List<String> error) {
		this.error = error;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("SUCCESS : [");
		for (String suc : success) {
			sb.append(suc + " ");
		}
		sb.append("], SKIPPED : [");
		for (String ski : skipped) {
			sb.append(ski + " ");
		}
		sb.append("], ERROR : [");
		for (String err : error) {
			sb.append(err + " ");
		}
		sb.append("]");
		return sb.toString();
	}
}
