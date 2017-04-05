package com.caac.house.system.vo;

/**
 * 银行账号信息
 * @ClassName: BankVo
 * @Description: 
 * @author 叶
 * @date 2015年7月16日 下午5:40:12
 *
 */
public class BankVo {

	private String id;
	
	/**经纪人*/
	private String brokerId;
	
	/**开户行*/
	private String bank;
	
	/**账户名*/
	private String accountName;
	
	/**银行账户*/
	private String account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
