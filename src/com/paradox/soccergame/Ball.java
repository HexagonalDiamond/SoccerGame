package com.paradox.soccergame;

import org.lwjgl.opengl.GL11;

import com.paradox.engine.graphics.Graphics;
import com.paradox.engine.graphics.Sprite;

public class Ball extends Sprite{
	public float x = Graphics.getWidth() / 2;
	public float y = Graphics.getHeight() / 2;
	private float vx = 0;
	private float vy = 0;
	public void draw() {
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		Graphics.drawCircle((int)x, (int)y, 5);
	}

	public void update() {
		x += vx;
		y += vy;
	}
	/**
	 * Moves ball towards
	 * @param x X-value to move ball
	 * @param y Y-value to move ball
	 * @param t time to move ball in frames
	 */
	public void move(int x, int y, int t) {
		this.vx = (x - this.x) * 1.0f / t;
		this.vy = (y - this.y) * 1.0f / t;
	}
}
