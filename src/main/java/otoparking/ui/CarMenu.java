package otoparking.ui;

import otoparking.utilities.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CarMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CarMenu(JPanel pnContent, CardLayout cl) {
		setLayout(new BorderLayout());
		setBackground(new Color(238, 238, 240));

		JPanel pnMain = new JPanel(new GridLayout(3, 1, 10, 10));
		JPanel pnChild = new JPanel(new CardLayout());

		add(pnChild, BorderLayout.CENTER);
		pnChild.add(pnMain, "Main");
		pnMain.setBackground(new Color(182, 187, 195));
		Font f = new Font("Segoe UI", Font.BOLD, 20);
		Color hover = new Color(5, 122, 128, 30);
		// tool bar
		JPanel Toolbar = new JPanel(new BorderLayout());
		add(Toolbar, BorderLayout.NORTH);

		ImageIcon backIcon = GetImage.getIcon("back.png");
		JButton btnBack = new JButton("Trở về", backIcon);
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setVerticalAlignment(SwingConstants.CENTER);
		btnBack.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBack.setVerticalTextPosition(SwingConstants.CENTER);
		btnBack.setIconTextGap(40);
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		btnBack.setFont(f);

		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBack.setOpaque(true);
				btnBack.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setOpaque(false);
				btnBack.setBackground(null);
			}
		});

		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(pnContent, "Main");
			}
		});

		Toolbar.add(btnBack, BorderLayout.CENTER);

		// button

		ImageIcon typeIcon = GetImage.getIcon("typecar.png");
		JButton btnType = new JButton("Tùy chỉnh loại xe", typeIcon);
		btnType.setHorizontalAlignment(SwingConstants.CENTER);
		btnType.setVerticalAlignment(SwingConstants.CENTER);
		btnType.setHorizontalTextPosition(SwingConstants.CENTER);
		btnType.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnType.setIconTextGap(40);
		btnType.setOpaque(false);
		btnType.setContentAreaFilled(false);
		btnType.setFocusPainted(false);
		btnType.setFont(f);

		btnType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnType.setOpaque(true);
				btnType.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnType.setOpaque(false);
				btnType.setBackground(null);
			}
		});

		ImageIcon carIcon = GetImage.getIcon("car.png");
		JButton btnCar = new JButton("Tùy chỉnh xe", carIcon);
		btnCar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCar.setVerticalAlignment(SwingConstants.CENTER);
		btnCar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCar.setIconTextGap(40);
		btnCar.setOpaque(false);
		btnCar.setContentAreaFilled(false);
		btnCar.setFocusPainted(false);
		btnCar.setFont(f);

		btnCar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCar.setOpaque(true);
				btnCar.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCar.setOpaque(false);
				btnCar.setBackground(null);
			}
		});

		ImageIcon priceIcon = GetImage.getIcon("pricelist.png");
		JButton btnPrice = new JButton("Tùy chỉnh giá đỗ xe", priceIcon);
		btnPrice.setHorizontalAlignment(SwingConstants.CENTER);
		btnPrice.setVerticalAlignment(SwingConstants.CENTER);
		btnPrice.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPrice.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPrice.setIconTextGap(40);
		btnPrice.setOpaque(false);
		btnPrice.setContentAreaFilled(false);
		btnPrice.setFocusPainted(false);
		btnPrice.setFont(f);

		btnPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPrice.setOpaque(true);
				btnPrice.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPrice.setOpaque(false);
				btnPrice.setBackground(null);
			}
		});

		pnMain.add(btnType);
		pnMain.add(btnCar);
		pnMain.add(btnPrice);
	}

}
