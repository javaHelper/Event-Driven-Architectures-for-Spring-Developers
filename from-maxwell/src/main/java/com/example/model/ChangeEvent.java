package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeEvent {
	private String database;
	private String table;
	private Type type;
	private Currency data;
	
	public enum Type {
		insert, update, delete
	}
}
