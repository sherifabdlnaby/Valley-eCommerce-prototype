package com.piper.valley.helpers;

/**
 * This is used to return the result of any operation.
 * Set the return type of the function to Msg
 * You return the msg type, e.g(WRONG_EMAIL)
 * and then use getValue() to get the corresponding message string
 */
public enum Msg {
	SUCCESS,
	WRONG_EMAIL("Please enter a valid Email"),
	EMAIL_EXISTS("la2ena tare2a ngeb howa failed leh"),
	INVALID_PASSWORD("la2ena tare2a ngeb howa failed leh"),
	PASSWORD_EXISTS("la2ena tare2a ngeb howa failed leh"),
	PASSWORD_CONFIRM("la2ena tare2a ngeb howa failed leh"),
	USERNAME_EXISTS("la2ena tare2a ngeb howa failed leh"),
	UNKNOWN("la2ena tare2a ngeb howa failed leh");
	// TODO: refill with appropriate messages
	// ... add more messages if needed


	private String value;

	Msg(String value) {
		this.value = value;
	}

	Msg() {

	}

	public String getValue() {
		return value;
	}
}
