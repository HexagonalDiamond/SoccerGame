package com.paradox.soccergame;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.paradox.engine.graphics.Graphics;
import com.paradox.engine.graphics.Sprite;

public class Player extends Sprite {
	/**
	 * Relative coordinates of each position
	 */
	private final static Map<Position,Vector2f> positionCoords;
	static {
		positionCoords = new HashMap<Position,Vector2f>();
		positionCoords.put(Position.RIGHT_DEFENSE, new Vector2f(102,430));
		positionCoords.put(Position.LEFT_DEFENSE, new Vector2f(102,70));
		positionCoords.put(Position.CENTER_DEFENSE, new Vector2f(102,250));
		positionCoords.put(Position.RIGHT_MID, new Vector2f(190,410));
		positionCoords.put(Position.LEFT_MID, new Vector2f(190,90));
		positionCoords.put(Position.CENTER_MID, new Vector2f(190,250));
		positionCoords.put(Position.RIGHT_FORWARD, new Vector2f(270,430));
		positionCoords.put(Position.LEFT_FORWARD, new Vector2f(270,70));
		positionCoords.put(Position.CENTER_FORWARD, new Vector2f(270,250));
		positionCoords.put(Position.GOALIE, new Vector2f(10,250));
	}
	private final int speed = 1;
	private String name;
	private Position position;
	private int team;
	public float x;
	public float y;
	public Player(String name, Position pos, int team) {
		super();
		this.name = name;
		this.team = team;
		this.setPosition(pos);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
		this.x = (int) positionCoords.get(position).x;
		this.y = (int) positionCoords.get(position).y;
		int offsetX = 75;
		int offsetY = 50;
		if(this.getTeam() == 0) {
			this.x = (int) (offsetX + this.x);
			this.y = (int) (offsetY + this.y);
		} else {
			this.x = (int) (offsetX + (650 - this.x));
			this.y = (int) (offsetY + (500 - this.y));
		}
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	/**
	 * Renders player's name above their head
	 * @param x x value of center of sign
	 * @param y y value of center of sign
	 * @param name name of player
	 */
	private void renderPlayerName(int x, int y, String name) {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		int width = 10 + Graphics.font.getWidth(name);
		int height = 10 + Graphics.font.getHeight(name);
		GL11.glColor4f(0.7f, 0.7f, 0.7f, 0.7f);
		Graphics.drawRect(x - (width / 2), y - (height / 2), width, height);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Graphics.drawText(name, x, y);
		GL11.glPopAttrib();
	}
	
	/**
	 * Draws a player
	 * @param p player to draw
	 */
	public void draw() {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		if(this.getTeam() == 0) {
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
		} else {
			GL11.glColor3f(0.0f, 0.0f, 1.0f);
		}
		Graphics.drawCircle((int)this.x, (int)this.y, 10);
		renderPlayerName((int)this.x, (int)this.y - 30, this.getName());
		GL11.glPopAttrib();
	}
	public void update(Ball b, Player[] ps) {
		boolean closest = true;
		double distance = Math.sqrt(Math.pow(this.x-b.x, 2) + Math.pow(this.y-b.y, 2));
		for(Player p:ps) {
			if (SoccerGame.distanceToBall(p, b)<distance && p.team == this.team) {
				closest = false;
			}
		}
		if(closest) {
			this.moveToPoint((int)b.x, (int)b.y);
		}
		
		//Collision detection with ball
	}
	/**
	 * Moves player to point
	 * @param x
	 * @param y
	 */
	public void moveToPoint(int x, int y) {
		float totalDx = x - this.x;
		float totalDy = y - this.y;
		Vector2f total = new Vector2f(totalDx, totalDy);
		if(total.length() <= 1) {
			return;
		}
		total = (Vector2f) total.normalise();
		total = (Vector2f) total.scale(speed);
		this.x += total.x;
		this.y += total.y;
	}
	public void tick(Ball b) {
	}
}
