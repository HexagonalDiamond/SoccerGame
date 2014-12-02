package com.paradox.soccergame;

public class Player {
	private String name;
	private Position position;
	private int team;
	public Player(String name, Position pos, int team) {
		this.name = name;
		this.position = pos;
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public Position getPosition() {
		return position;
	}
	public int getTeam() {
		return team;
	}
}
