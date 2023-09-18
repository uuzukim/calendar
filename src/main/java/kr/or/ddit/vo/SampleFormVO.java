package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;

public class SampleFormVO implements Serializable {
	private String[] chkParam; // 얘도 체크박스 값이 여러개 선택 가능 해서 []배열로
	private String dateParam;
	private String datetiemParam;
	private String param1;
	private int param2;
	private String rdoParam;
	private String[] selParamMulti;// 얘는 체크박스 값이 여러개 선택 가능해서 []배열로
	private String selParamSingle;
	
	@Override
	public String toString() {
		return "SampleFormVO [chkParam=" + Arrays.toString(chkParam) + ", dateParam=" + dateParam + ", datetiemParam="
				+ datetiemParam + ", param1=" + param1 + ", param2=" + param2 + ", rdoParam=" + rdoParam
				+ ", selParamMulti=" + Arrays.toString(selParamMulti) + ", selParamSingle=" + selParamSingle + "]";
	}
	
	public String[] getChkParam() {
		return chkParam;
	}
	public void setChkParam(String[] chkParam) {
		this.chkParam = chkParam;
	}
	public String getDateParam() {
		return dateParam;
	}
	public void setDateParam(String dateParam) {
		this.dateParam = dateParam;
	}
	public String getDatetiemParam() {
		return datetiemParam;
	}
	public void setDatetiemParam(String datetiemParam) {
		this.datetiemParam = datetiemParam;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public int getParam2() {
		return param2;
	}
	public void setParam2(int param2) {
		this.param2 = param2;
	}
	public String getRdoParam() {
		return rdoParam;
	}
	public void setRdoParam(String rdoParam) {
		this.rdoParam = rdoParam;
	}
	public String[] getSelParamMulti() {
		return selParamMulti;
	}
	public void setSelParamMulti(String[] selParamMulti) {
		this.selParamMulti = selParamMulti;
	}
	public String getSelParamSingle() {
		return selParamSingle;
	}
	public void setSelParamSingle(String selParamSingle) {
		this.selParamSingle = selParamSingle;
	}
	
	
}
