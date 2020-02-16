package view;

import game.GameClassic;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ViewPlateau {
	private Pane viewPlateau;

	private Texture texture;

	private GameClassic gc;
	private double sizeCell;
	
	public ViewPlateau(GameClassic gc, Texture texture) {
		this.viewPlateau = new GridPane();
		
		this.texture = texture;
		
		this.gc = gc;
	}
	
	protected Pane getViewPlateau() {
		return this.viewPlateau;
	}
	
	protected double getSizeCell() {
		return this.sizeCell;
	}
	
	protected void refreshViewPlateau() {
		for (int row = 0; row < gc.getPlateau().getPlateau().length; row++) {
			for (int col = 0; col < gc.getPlateau().getPlateau()[row].length; col++) {
				StackPane stack = drawStack(row, col);

				GridPane.setRowIndex(stack, col);
				GridPane.setColumnIndex(stack, row);
				this.viewPlateau.getChildren().addAll(stack);
			}
		}
	}

	/**
	 * Dessine la cellule du tableau à l'indice indiqué par la colone et la ligne passé en parametre
	 * @param row
	 * @param col
	 * @return
	 */
	private StackPane drawStack(int row, int col) {
		StackPane stack = new StackPane();
		Rectangle rec = new Rectangle();
		Text text = new Text();
		
		double pow = this.gc.getPlateau().getPlateau()[row][col].getContent().getPow();
		rec.setWidth(ViewGame.getWinHeight() / this.gc.getPlateau().getPlateau().length);
		rec.setHeight(ViewGame.getWinHeight() / this.gc.getPlateau().getPlateau().length);
		this.sizeCell = rec.getWidth();

		rec.setFill(texture.getTexturePaint((int)pow));
		
		if(! this.gc.getPlateau().isImgContent()) {
			text = new Text(gc.getPlateau().getPlateau()[row][col].getContent().toString());
			text.setFill(((Color)(rec.getFill())).invert()); 
			text.setFont(Font.font(0.3 * rec.getHeight()));
		}
		
		rec.setStroke(this.texture.getStrokeColor());
		stack.getChildren().addAll(rec, text);
		return stack;
	}
}
