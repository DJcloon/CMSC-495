package application;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ManifestContent extends ContentArea{
	@Override
	public void initialize() {
		content = new VBox(
				new Label("Manifest Content")
				);
	}
}
