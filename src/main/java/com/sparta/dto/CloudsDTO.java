package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CloudsDTO {

	@JsonProperty("all")
	private Integer all;

	public Integer getAll(){
		return all;
	}

	@Override
	public String toString() {
		return "CloudsDTO{" +
				"all=" + all +
				'}';
	}
}