package com.paradox.soccergame;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.TrueTypeFont;

import com.paradox.engine.Game;
import com.paradox.engine.util.Observable;
public class SoccerGame extends Game {
	
	/**
	 * Line width to use while drawing lines
	 */
	private final int LINE_WIDTH = 1;
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
	
	public static void main(String[] args) {
		SoccerGame sg = new SoccerGame();
		sg.run("SoccerGame");
	}

	TrueTypeFont font;
	
	/**
	 * Renders player's name above their head
	 * @param x x value of center of sign
	 * @param y y value of center of sign
	 * @param name name of player
	 */
	public void renderPlayerName(int x, int y, String name) {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		int width = 10 + font.getWidth(name);
		int height = 10 + font.getHeight(name);
		GL11.glColor4f(0.7f, 0.7f, 0.7f, 0.7f);
		drawRect(x - (width / 2), y - (height / 2), width, height);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		font.drawString(x - (width / 2) + 5, y - (height / 2) + 5, name);
		GL11.glPopAttrib();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	/**
	 * Initializes fonts
	 */
	public void initFont() {
		Font awtFont = new Font("Arial", Font.BOLD, 12);
		font = new TrueTypeFont(awtFont, true);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	/**
	 * Draws a filled circle
	 * @param f center x
	 * @param g center y
	 * @param r radius
	 */
	private void drawCircle(int f, int g, int r) {
		GL11.glPushMatrix();
		GL11.glTranslatef(f, g, 0);
		GL11.glScalef(r, r, 1);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for(int i = 0; i <= 360; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
		    double angle = Math.PI * 2 * i / 360;
		    GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		GL11.glEnd();

		GL11.glPopMatrix();
	}
	
	/**
	 * Draws a player
	 * @param p player to draw
	 */
	private void drawPlayer(Player p) {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		if(p.getTeam() == 0) {
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
		} else {
			GL11.glColor3f(0.0f, 0.0f, 1.0f);
		}
		int multiplier = p.getTeam() * 2 - 1;
		int offsetX = 75;
		int offsetY = 50;
		int posX = 0;
		int posY = 0;
		if(p.getTeam() == 0) {
			posX = (int) (offsetX + positionCoords.get(p.getPosition()).x);
			posY = (int) (offsetY + positionCoords.get(p.getPosition()).y);
		} else {
			posX = (int) (offsetX + (650 - positionCoords.get(p.getPosition()).x));
			posY = (int) (offsetY + (500 - positionCoords.get(p.getPosition()).y));
		}
		drawCircle(posX, posY, 10);
		renderPlayerName(posX, posY - 30, p.getName());
		GL11.glPopAttrib();
	}
	
	/**
	 * Draws all players
	 */
	private void drawPlayers() {
		for(Player p:this.players) {
			drawPlayer(p);
		}
	}
	
	@Override
	public void draw() {
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
		drawField();
		drawPlayers();
	}


	@Override
	public void update() {
		// TODO Update game state
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
	public void update(Observable observer, Object[] args) {
		
	}

	@Override
	public void initialize() {
		initFont();
		System.out.println("Loaded fonts");
	}
}
