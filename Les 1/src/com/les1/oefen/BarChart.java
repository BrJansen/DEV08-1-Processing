package com.les1.oefen;

import processing.core.PApplet;
import processing.core.PFont;

public class BarChart extends PApplet {

	private static final long serialVersionUID = -6619604803640224202L;
	float x, y, w;
	float barH, barW, factor;
	double barS;
	PFont font;
	float kx, ky, kw, ks;
	String[] kwartalen = { "Kwartaal 1", "Kwartaal 2", "Kwartaal 3",
			"Kwartaal 4" };
	int[] geld = { 120000000, 150000000, 180000000, 60000000 };


	int[] colors = { color(11, 13, 50), color(13, 155, 21),
			color(13, 21, 241), color(13, 147, 111), color(190, 13, 214),
			color(190, 214, 13), color(3, 78, 113) };

	public void setup() {
		size(600, 800);
		smooth();
		noLoop();
		background(255);

		x = 50;
		y = (float) (height / 1.5);
		barW = (width /5) ;
		barS = barW * 0.1;
		barH = height / 2 - 75 ;
		factor = barH / geld[0];

		font = createFont("Helvetica", 18, true);
		textFont(font);

		// legend stuff
		kx = x;
		ky = y + 50;
		kw = 20;
		ks = 10;
	}

	public void draw() {

		for (int i = 0; i < geld.length; i++) {

			fill(colors[i]);
			rect(x, y - geld[i] * factor, (float) (barW - barS), geld[i] * factor);
			x += barW;

			rect(kx, ky, kw, kw);
			fill(0);
			text(kwartalen[i], kx + kw + ks, ky + kw);
			ky = ky + 2 * kw;
		}
	}

}
