package de.learningjava.precessing;

import processing.core.PApplet;

public class SimpleGame extends PApplet {

	// 0: Initial Screen
	// 1: Game Screen
	// 2: Game-over Screen
	private int gameScreen = 0;
	private int ballX, ballY;
	private int ballSize = 20;
	private int ballColor = color(255,0,0);
	private float gravity = 1;
	private float ballSpeedVert = 0;
	private float airfriction = 0.0001f;
	private float friction = 0.1f;
	private int racketColor = color(0);
	private float racketWidth = 100;
	private float racketHeight = 10;
	private int racketBounceRate = 20;
	
	
	public static void main(String[] args) {
		PApplet.main("de.learningjava.precessing.SimpleGame");
	}

    public void settings(){
        size(300,300);
        ballX=width/4;
        ballY=height/5;
    }
	
    public void draw(){
    	// Display the contents of the current screen
    	if (gameScreen == 0) {
    		initScreen();
    	}
    	else if (gameScreen == 1) {
    		gameScreen();
    	}
    	else if (gameScreen == 2) {
    		gameOverScreen();
    	}
    }
 
    public void initScreen () {	
    	background(255, 204, 0);
    	textAlign(CENTER);
    	text("Click to start", height/2, width/2);
 
    }
    

	public void gameScreen () {
		background(0,255,0);
		drawBall();
		applyGravity();
		keepInScreen();
		drawRacket();
		watchRacketBounce(); 
    }
	
	public void drawBall() {
		fill(ballColor);
		ellipse(ballX, ballY, ballSize, ballSize);
	}
    
    public void gameOverScreen () {
    	
    }
    
    public void mousePressed() {
    	if (gameScreen == 0) {
    		startGame();
    	}
    }
    
    public void startGame() {
    	gameScreen=1;
    }
    
    public void applyGravity() {
    	ballSpeedVert += gravity;
    	ballY += ballSpeedVert;
    	ballSpeedVert -= (ballSpeedVert * airfriction);
    }
    
    public void makeBounceBottom(int surface) {
    	ballY = surface -(ballSize/2);
    	ballSpeedVert *= -1;
    	ballSpeedVert -= (ballSpeedVert * friction);
    }
    
    public void makeBounceTop(int surface) {
    	ballY = surface + (ballSize/2);
    	ballSpeedVert *= -1;
    	ballSpeedVert -= (ballSpeedVert * friction);
    }
    
    public void keepInScreen() {
    	if (ballY + (ballSize/2) > height) {	
    		makeBounceBottom(height);
    	}
    	
    	if (ballY - (ballSize/2) < 0) {
    		makeBounceTop(0);
    	}
    }
    
    public void drawRacket() {
    	fill(racketColor);
    	rectMode(CENTER);
    	rect(mouseX, mouseY, racketWidth, racketHeight);
    }
    
    public void watchRacketBounce() {
    	float overhead = mouseY - pmouseY;
    	if((ballX+(ballSize/2) > mouseX-(racketWidth/2)) && (ballX+(ballSize/2) < mouseX+(racketWidth/2))); {
    		if(dist(ballX, ballY, ballX, mouseY) <= (ballSize/2)+abs(overhead)) {
    			makeBounceBottom(mouseY);
    			if (overhead < 0) {
    				ballY += overhead;
    				ballSpeedVert += overhead;
    				
    			}
    		}
    	}
    	
    }
    
}
