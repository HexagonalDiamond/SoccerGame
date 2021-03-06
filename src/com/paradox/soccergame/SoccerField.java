package com.paradox.soccergame;

import org.lwjgl.opengl.GL11;

import com.paradox.engine.graphics.Graphics;
import com.paradox.engine.graphics.Sprite;

public class SoccerField extends Sprite {
	public SoccerField() {
		super();
	}

	/**
	 * Line width to use while drawing lines
	 */
	private final int LINE_WIDTH = 2;
	/**
	 * Draw goal boxes
	 */
	private void drawGoalBoxes() {
		drawLeftGoalBox();
		drawRightGoalBox();
	}
	/**
	 * Draw left goal box
	 */
	private void drawLeftGoalBox() {
		Graphics.drawLine(75, 190, 165, 190, LINE_WIDTH);
		Graphics.drawLine(165, 190, 165, 410, LINE_WIDTH);
		Graphics.drawLine(165, 410, 75, 410, LINE_WIDTH);
	}
	/**
	 * Draw right goal box
	 */
	private void drawRightGoalBox() {
		Graphics.drawLine(725, 190, 635, 190, LINE_WIDTH);
		Graphics.drawLine(635, 190, 635, 410, LINE_WIDTH);
		Graphics.drawLine(635, 410, 725, 410, LINE_WIDTH);
	}
	
	/**
	 * Draw penalty boxes
	 */
	private void drawPenaltyBoxes() {
		drawLeftPenaltyBox();
		drawRightPenaltyBox();
	}
	/**
	 * Draw left penalty box
	 */
	private void drawLeftPenaltyBox() {
		Graphics.drawLine(75, 250, 105, 250, LINE_WIDTH);
		Graphics.drawLine(75, 350, 105, 350, LINE_WIDTH);
		Graphics.drawLine(105, 250, 105, 350, LINE_WIDTH);
	}
	/**
	 * Draw right penalty box
	 */
	private void drawRightPenaltyBox() {
		Graphics.drawLine(725, 250, 695, 250, LINE_WIDTH);
		Graphics.drawLine(695, 250, 695, 350, LINE_WIDTH);
		Graphics.drawLine(695, 350, 725, 350, LINE_WIDTH);
	}
	/**
	 * Draw half line
	 */
	private void drawHalfLine() {
		Graphics.drawLine(400, 550, 400, 50, LINE_WIDTH);
	}
	
	/**
	 * Draw center circle
	 */
	private void drawCenterCircle() {
		Graphics.drawArc(400, 300, 50, 360, 0f, 6.29f);
	}
	
	/**
	 * Draws penalty arcs
	 */
	private void drawPenaltyArcs() {
		Graphics.drawArc(135,300,50,360, -0.92729522f, 0.92729522f); // 0.643501109 is angle of penalty arc
		Graphics.drawArc(665,300,50,360, 2.21270478f, 4.06729522f);
	}
	/**
	 * Draw bounds of fields
	 */
	private void drawBounds() {
		Graphics.drawLine(75, 50, 725, 50, LINE_WIDTH);
		Graphics.drawLine(725, 50, 725, 550, LINE_WIDTH);
		Graphics.drawLine(725, 550, 75, 550, LINE_WIDTH);
		Graphics.drawLine(75, 550, 75, 50, LINE_WIDTH);
	}
	
	/**
	 * Draw goals
	 */
	private void drawGoals() {
		//Left goal
		Graphics.drawLine(75, 280, 62, 280, LINE_WIDTH);
		Graphics.drawLine(75, 320, 62, 320, LINE_WIDTH);
		Graphics.drawLine(62, 280, 62, 320, LINE_WIDTH);
		//Right goal
		Graphics.drawLine(725, 280, 738, 280, LINE_WIDTH);
		Graphics.drawLine(725, 320, 738, 320, LINE_WIDTH);
		Graphics.drawLine(738, 320, 738, 280, LINE_WIDTH);
	}
	
	/**
	 * Draw field
	 */
	public void draw() {
		GL11.glColor3f(0.0f, 0.7f, 0.0f);
		Graphics.drawRect(55, 30, 690, 540);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		drawGoalBoxes();
		drawPenaltyBoxes();
		drawHalfLine();
		drawCenterCircle();
		drawPenaltyArcs();
		drawBounds();
		drawGoals();
	}
}
