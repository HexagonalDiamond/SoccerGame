package com.paradox.soccergame;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.paradox.engine.Game;
import com.paradox.engine.util.Observable;
public class SoccerGame extends Game {
	
	private final int LINE_WIDTH = 1;
	
	/**
	 * Draw a line from x1, y1 to x2, y2
	 * @param x1 X value of first point
	 * @param y1 Y value of first point
	 * @param x2 X value of second point
	 * @param y2 Y value of second point
	 * @param width width of line
	 */
	public void drawLine(float x1, float y1, float x2, float y2, int width) {
		GL11.glLineWidth(width); 
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
	}
	
	/**
	 * Draw a rectangle
	 */
	public void drawRect(float x, float y, float w, float h) {
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(x,y);
		    GL11.glVertex2f(x,y+h);
		    GL11.glVertex2f(x+w,y+h);
		    GL11.glVertex2f(x+w,y);
	    GL11.glEnd();
	}
	
	/**
	 * Draw goal boxes
	 */
	public void drawGoalBoxes() {
		drawLeftGoalBox();
		drawRightGoalBox();
	}
	/**
	 * Draw left goal box
	 */
	public void drawLeftGoalBox() {
		drawLine(75, 190, 165, 190, LINE_WIDTH);
		drawLine(165, 190, 165, 410, LINE_WIDTH);
		drawLine(165, 410, 75, 410, LINE_WIDTH);
	}
	/**
	 * Draw right goal box
	 */
	public void drawRightGoalBox() {
		drawLine(725, 190, 635, 190, LINE_WIDTH);
		drawLine(635, 190, 635, 410, LINE_WIDTH);
		drawLine(635, 410, 725, 410, LINE_WIDTH);
	}
	
	/**
	 * Draw penalty boxes
	 */
	public void drawPenaltyBoxes() {
		drawLeftPenaltyBox();
		drawRightPenaltyBox();
	}
	/**
	 * Draw left penalty box
	 */
	public void drawLeftPenaltyBox() {
		drawLine(75, 250, 105, 250, LINE_WIDTH);
		drawLine(75, 350, 105, 350, LINE_WIDTH);
		drawLine(105, 250, 105, 350, LINE_WIDTH);
	}
	/**
	 * Draw right penalty box
	 */
	public void drawRightPenaltyBox() {
		drawLine(725, 250, 695, 250, LINE_WIDTH);
		drawLine(695, 250, 695, 350, LINE_WIDTH);
		drawLine(695, 350, 725, 350, LINE_WIDTH);
	}
	/**
	 * Draw half line
	 */
	public void drawHalfLine() {
		drawLine(400, 550, 400, 50, LINE_WIDTH);
	}
	/**
	 * Draws an arc
	 * @param cx center x
	 * @param cy center y
	 * @param r radius
	 * @param num_segments segments of arc
	 * @param start_angle angle to start at in radians
	 * @param end_angle angle to end at in radians
	 */
	public void drawArc(int cx, int cy, int r, int num_segments, float start_angle, float end_angle) {
		GL11.glBegin(GL11.GL_LINE_STRIP); 
	    for(int ii = 0; ii < num_segments; ii++) 
	    { 
	        float theta = start_angle + (ii * Math.abs(end_angle - start_angle) / num_segments);//get the current angle 

	        float x = (float) (r * Math.cos(theta));//calculate the x component 
	        float y = (float) (r * Math.sin(theta));//calculate the y component 

	        GL11.glVertex2f(x + cx - 1, y + cy);//output vertex 
	    } 
	    GL11. glEnd(); 
	}
	
	/**
	 * Draw center circle
	 */
	public void drawCenterCircle() {
		drawArc(400, 300, 50, 360, 0f, 6.29f);
	}
	
	/**
	 * Draws penalty arcs
	 */
	public void drawPenaltyArcs() {
		drawArc(135,300,50,360, -0.92729522f, 0.92729522f); // 0.643501109 is angle of penalty arc
		drawArc(665,300,50,360, 2.21270478f, 4.06729522f);
	}
	/**
	 * Draw bounds of fields
	 */
	public void drawBounds() {
		drawLine(75, 50, 725, 50, LINE_WIDTH);
		drawLine(725, 50, 725, 550, LINE_WIDTH);
		drawLine(725, 550, 75, 550, LINE_WIDTH);
		drawLine(75, 550, 75, 50, LINE_WIDTH);
	}
	
	/**
	 * Draw goals
	 */
	public void drawGoals() {
		//Left goal
		drawLine(75, 280, 62, 280, LINE_WIDTH);
		drawLine(75, 320, 62, 320, LINE_WIDTH);
		drawLine(62, 280, 62, 320, LINE_WIDTH);
		//Right goal
		drawLine(725, 280, 738, 280, LINE_WIDTH);
		drawLine(725, 320, 738, 320, LINE_WIDTH);
		drawLine(738, 320, 738, 280, LINE_WIDTH);
	}
	
	/**
	 * Draw field
	 */
	public void drawField() {
		GL11.glColor3f(0.0f, 0.7f, 0.0f);
		drawRect(55, 30, 690, 540);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		drawGoalBoxes();
		drawPenaltyBoxes();
		drawHalfLine();
		drawCenterCircle();
		drawPenaltyArcs();
		drawBounds();
		drawGoals();
	}

	@Override
	public void draw() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		drawField();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
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
	
	public static void main(String[] args) {
		SoccerGame sg = new SoccerGame();
		sg.run("SoccerGame");
	}

	@Override
	public void update(Observable observer, Object[] args) {
		
	}
}
