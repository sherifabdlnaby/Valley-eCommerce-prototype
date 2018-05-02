package com.piper.valley.models.common;

import java.util.List;

public class LineChartData {
	List<Integer> data;
	String label;
	String borderColor;
	Boolean fill;

	public LineChartData(List<Integer> data, String label, String borderColor, Boolean fill) {
		this.data = data;
		this.label = label;
		this.borderColor = borderColor;
		this.fill = fill;
	}

	public List<Integer> getData() {

		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Boolean getFill() {
		return fill;
	}

	public void setFill(Boolean fill) {
		this.fill = fill;
	}
}
