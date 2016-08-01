package com.forerunner.core.sso;

/**
 * 登录返回信息模版
 * 
 * @author Administrator
 *
 */
public class LoginMsg implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3791401251453394992L;

	private boolean flag;

	private String passwordErrorMsg;

	private String userErrorMsg;
	private String codeErrorMsg;
	private String publiErrorMsg;

	public LoginMsg() {
		super();
		this.flag = false;
		this.userErrorMsg = "帐号不存在";
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getPasswordErrorMsg() {
		return passwordErrorMsg;
	}

	public void setPasswordErrorMsg(String passwordErrorMsg) {
		this.passwordErrorMsg = passwordErrorMsg;
	}

	public String getUserErrorMsg() {
		return userErrorMsg;
	}

	public void setUserErrorMsg(String userErrorMsg) {
		this.userErrorMsg = userErrorMsg;
	}

	public String getCodeErrorMsg() {
		return codeErrorMsg;
	}

	public void setCodeErrorMsg(String codeErrorMsg) {
		this.codeErrorMsg = codeErrorMsg;
	}

	public String getPubliErrorMsg() {
		return publiErrorMsg;
	}

	public void setPubliErrorMsg(String publiErrorMsg) {
		this.publiErrorMsg = publiErrorMsg;
	}

}
