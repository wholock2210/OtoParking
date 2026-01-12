package otoparking.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Info() {
		setLayout(new GridBagLayout());
		setBackground(new Color(182, 187, 195));
		
		JLabel lb = new JLabel("Info Panel");
		Font f = new Font("Segoe UI", Font.BOLD, 24);
		lb.setFont(f);
		add(lb);
	}

}
