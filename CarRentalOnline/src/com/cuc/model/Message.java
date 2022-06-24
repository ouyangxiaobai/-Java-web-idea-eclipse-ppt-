package com.cuc.model;

public class Message {
	private int messageId;// 留言编号
	private int memberId;// 会员编号
	private String messageContent;// 留言内容
	private String messageDate;// 留言发布日期
	private String messageImage;// 留言发布图片
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageImage() {
		return messageImage;
	}

	public void setMessageImage(String messageImage) {
		this.messageImage = messageImage;
	}

}
