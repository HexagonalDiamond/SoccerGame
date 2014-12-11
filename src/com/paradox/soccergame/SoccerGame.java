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
	private final Player[] players = {
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
			new Player("Tristan", Position.LEFT_DEFENSE, 1),
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
	 * Adds all players
	 */
	private void addPlayers() {
		for(Player p:this.players) {
			gom.addObject(p);
		}
	}
	
	@Override
	public void draw() {
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
	}


	@Override
	public void update() {
		ball.move(100, 100, 100);
	}

	@Override
	public void input() {
		Keyboard.poll();
		while(Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				this.quitGame();
				break;
			}
		}
	}
	@Override
	public void initialize() {
		field = new SoccerField();
		gom.addObject(field);
		ball = new Ball();
		gom.addObject(ball);
		addPlayers();
	}
}
