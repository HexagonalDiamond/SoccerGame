package com.paradox.soccergame;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.TrueTypeFont;

import com.paradox.engine.Game;
import com.paradox.engine.graphics.Graphics;
import com.paradox.engine.util.Observable;
public class SoccerGame extends Game {
	/**
	 * Player names, positions, and teams
	 */
	public final Player[] players = {
			new Player("Julian", Position.RIGHT_DEFENSE, 0),
			new Player("Holden", Position.CENTER_DEFENSE, 0),
			new Player("Tristan", Position.LEFT_DEFENSE, 0),
			new Player("Dad", Position.RIGHT_MID, 0),
			new Player("Mom", Position.CENTER_MID, 0),
			new Player("Random Guy 1", Position.LEFT_MID, 0),
			new Player("Random Guy 2", Position.RIGHT_FORWARD, 0),
			new Player("Random Guy 3", Position.CENTER_FORWARD, 0),
			new Player("Random Guy 4", Position.LEFT_FORWARD, 0),
			new Player("Random Guy 5", Position.GOALIE, 0),
			new Player("Julian", Position.RIGHT_DEFENSE, 1),
			new Player("Holden", Position.CENTER_DEFENSE, 1),
			new Player("Tristan2", Position.LEFT_DEFENSE, 1),
			new Player("Dad", Position.RIGHT_MID, 1),
			new Player("Mom", Position.CENTER_MID, 1),
			new Player("Random Guy 1", Position.LEFT_MID, 1),
			new Player("Random Guy 2", Position.RIGHT_FORWARD, 1),
			new Player("Random Guy 3", Position.CENTER_FORWARD, 1),
			new Player("Random Guy 4", Position.LEFT_FORWARD, 1),
			new Player("Random Guy 5", Position.GOALIE, 1)
	};
	private SoccerField field;
	private Ball ball;
	
	public static void main(String[] args) {
		SoccerGame sg = new SoccerGame();
		sg.run("SoccerGame");
	}
	
	/**
	 * Draws all players
	 */
	private void drawPlayers() {
		for(Player p:this.players) {
			p.draw();
		}
	}
	/**
	 * Updates all players
	 */
	private void updatePlayers() {
		for(Player p:this.players) {
			p.update(ball, players);
		}
	}
	/**
	 * Ticks all players
	 */
	private void tickPlayers() {
		for(Player p:this.players) {
			p.tick(ball);
		}
	}
	
	public void draw() {
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
		field.draw();
		drawPlayers();
		ball.draw();
	}


	public void update() {
		if(this.frameNumber % 15 == 0) {
			tickPlayers();
		}
		updatePlayers();
		ball.move(100, 100, 100);
	}

	public void input() {
		Keyboard.poll();
		while(Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				this.quitGame();
				break;
			}
		}
	}
	public void initialize() {
		field = new SoccerField();
		ball = new Ball();
	}
	public static double distanceToBall(Player p, Ball b) {
		double distance = Math.sqrt(Math.pow(p.x-b.x, 2) + Math.pow(p.y-b.y, 2));
		return distance;
	}
	public SoccerGame() {
		super();
		initialize();
	}
}
