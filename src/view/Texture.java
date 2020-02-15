package view;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class Texture {
	private Paint[] texturePaint;
	private Color strokeColor;
	
	public Texture(int size, String[] fillAssociateToPow, boolean isImgContent, double[] RGBstrokeColor) {
		this.texturePaint = new Paint[size];
		if(isImgContent) {
			this.fillTextureWithImg(fillAssociateToPow);
		}else {
			this.fillTexture(fillAssociateToPow);	
		}
		this.setStrokeColor(RGBstrokeColor);
	}
	
	private void setStrokeColor(double[] RGBstrokeColor) {
		this.strokeColor = new Color(RGBstrokeColor[0], RGBstrokeColor[1], RGBstrokeColor[2], 1.0);
	}

	public Color getStrokeColor() {
		return this.strokeColor;
	}
	
	protected void fillTextureWithImg(String[] fillAssociateToPow) {
		double color = Double.parseDouble(fillAssociateToPow[0]);
		texturePaint[0] = (Paint)(new Color(color, color, color, 1.0));
		for (int idx = 1; idx < fillAssociateToPow.length; idx++) {
			texturePaint[idx] = (Paint)(new ImagePattern(new Image(fillAssociateToPow[idx])));
		}
	}

	protected void fillTexture(String[] fillAssociateToPow) {
		double color;
		for (int idx = 0; idx < fillAssociateToPow.length; idx++) {
			color = Double.parseDouble(fillAssociateToPow[idx]);
			texturePaint[idx] = (Paint)(new Color(color, color, color, 1.0));
		}
	}

	public Paint getTexturePaint(int idx) {
		return this.texturePaint[idx];
	}
}
