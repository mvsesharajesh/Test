/**
 * 
 */
package com.project.jpmorgan.salesInformation;

/**
 * @author Rajesh
 *
 */
public class Message {
	
	private String messageType;
	private MessageTypeOne messageTypeOne;
	private MessageTypeTwo messageTypeTwo;
	private MessageTypeThree messageTypeThree;
	
	protected String getMessageType() {
		return messageType;
	}
	protected void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	protected MessageTypeOne getMessageTypeOne() {
		return messageTypeOne;
	}
	protected void setMessageTypeOne(MessageTypeOne messageTypeOne) {
		this.messageTypeOne = messageTypeOne;
	}
	protected MessageTypeTwo getMessageTypeTwo() {
		return messageTypeTwo;
	}
	protected void setMessageTypeTwo(MessageTypeTwo messageTypeTwo) {
		this.messageTypeTwo = messageTypeTwo;
	}
	protected MessageTypeThree getMessageTypeThree() {
		return messageTypeThree;
	}
	protected void setMessageTypeThree(MessageTypeThree messageTypeThree) {
		this.messageTypeThree = messageTypeThree;
	}
}
