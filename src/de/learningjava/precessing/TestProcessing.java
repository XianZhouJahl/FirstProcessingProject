package de.learningjava.precessing;

import processing.core.PApplet;

public class TestProcessing extends PApplet{

	public static void main(String[] args) {
        PApplet.main("de.learningjava.precessing.TestProcessing");
    }

    public void settings(){
        size(300,300);
    }

    public void setup(){
        fill(120,50,240);
    }

    public void draw(){
        ellipse(width/2,height/2,second(),second());
    }

}
