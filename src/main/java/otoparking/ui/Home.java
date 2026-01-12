package otoparking.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Home() {
		setLayout(new GridBagLayout());
		setBackground(new Color(238, 238, 240));
		
		JLabel lb = new JLabel("Home Panel");
		Font f = new Font("Segoe UI", Font.BOLD, 24);
		lb.setFont(f);
		add(lb);
	}

}
