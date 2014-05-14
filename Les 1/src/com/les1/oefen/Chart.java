package com.les1.oefen;

import processing.core.PApplet;
import processing.core.PFont;

public class Chart extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7347345652759312194L;
	
	int[] values = { 13661000, 10498000, 8468300, 43608000, 30335000, 25349000,
			21069501 };
	String[] plaatsArray = { "Antartica", "Europa", "Oceanië", "Azië",
			"Afrika", "Noord-Amerika", "Zuid-Amerika" };
	
	int radius = 200;
	float[] percentageValue = new float[7];
	float zoomRad = 1;
	int[] colors = new int[7];
	
	PFont helvetica;

	// misc
	float chartSize = 200;
	float minimalRadius;
	float maxRadius;

	public void setup() {
		size(640, 480);
		frameRate(30);
		stroke(0);
		smooth();

		helvetica = createFont("Helvetica-Bold", 13);
		textFont(helvetica);

		generateChart();
	}

	public void draw() {
		background(255);

		float startAngle = 0;
		int labelIndex = -1;
		
		// Totale waarde van values
		float totalVal = 0;
		for (int i = 0; i < values.length; i++) {
			totalVal += values[i];
		}

		// Percentage van values
		for (int i = 0; i < values.length; i++) {
			percentageValue[i] = values[i] / totalVal;
		}

		// iterate through segments
		for (int i = 0; i < values.length; i++) {

			// Input waarden to degrees
			float newVal = map(percentageValue[i], 0, 1, 0, 360);

			// Zoom radius naar graden
			float newRad = map(zoomRad, 0, 1, 0, chartSize);

			// set hoek van input waarde
			float endAngle = startAngle + radians(newVal);

			// Muis positie gezien vanuit centrum scherm
			float translateX = map(mouseX, 0, width, -width / 2, width / 2);
			float translateY = map(mouseY, 0, height, -height / 2, height / 2);

			// radius en hoek op basis van muispositie
			float mouseRad = sqrt(pow(translateY, 2) + pow(translateX, 2));
			float mouseAngle = atan2(translateY, translateX);

			// in geval van negatieve (muis)hoek;
			if (mouseAngle < 0) {
				mouseAngle = PI + (PI + mouseAngle);
			}

			// Kijk of de muis binnen de pie chart is
			if (((mouseAngle > startAngle) && (mouseAngle < endAngle))
					&& (mouseRad < newRad)) {
				// true
				fill(colors[i], 100);
				labelIndex = i;
			} else {
				// false
				fill(colors[i]);
			}

			// Teken arc
			arc(width / 2, height / 2, newRad * 2, newRad * 2, startAngle,
					endAngle);

			// starthoek gelijk aan eindhoek om daar weer verder te loopen
			startAngle = endAngle;
		}

		// Teken tekst label
		if (labelIndex != -1) {
			int labelVal = values[labelIndex];

			String txt = "Segment " + plaatsArray[labelIndex] + ": " + labelVal;
			float labelW = textWidth(txt);

			fill(0);
			rect(mouseX + 5, mouseY - 25, labelW + 10, 22);
			fill(255);
			text(txt, mouseX + 10, mouseY - 10);
		}
	}

	public void generateChart() {

		strokeWeight(3);

		for (int i = 0; i < colors.length; i++) {
			int r = (int) random(255);
			int g = (int) random(255);
			int b = (int) random(255);

			colors[i] = color(r, g, b);
		}


	}

}
