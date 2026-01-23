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
import otoparking.ui.Panel.*;

public class UserMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public UserMenu(JPanel pnContent, CardLayout cl) {
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
		JButton btnBack = new JButton("trở về", backIcon);
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
				cl.show(pnContent, "Main");
			}
		});

		Toolbar.add(btnBack, BorderLayout.CENTER);

		// button

		ImageIcon roleIcon = GetImage.getIcon("role.png");
		JButton btnRole = new JButton("tùy chỉnh vai trò", roleIcon);
		btnRole.setHorizontalAlignment(SwingConstants.CENTER);
		btnRole.setVerticalAlignment(SwingConstants.CENTER);
		btnRole.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRole.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRole.setIconTextGap(40);
		btnRole.setOpaque(false);
		btnRole.setContentAreaFilled(false);
		btnRole.setFocusPainted(false);
		btnRole.setFont(f);

		btnRole.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRole.setOpaque(true);
				btnRole.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRole.setOpaque(false);
				btnRole.setBackground(null);
			}
		});

		ImageIcon userIcon = GetImage.getIcon("user.png");
		JButton btnUser = new JButton("Tùy chỉnh người dùng", userIcon);
		btnUser.setHorizontalAlignment(SwingConstants.CENTER);
		btnUser.setVerticalAlignment(SwingConstants.CENTER);
		btnUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUser.setIconTextGap(40);
		btnUser.setOpaque(false);
		btnUser.setContentAreaFilled(false);
		btnUser.setFocusPainted(false);
		btnUser.setFont(f);

		btnUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUser.setOpaque(true);
				btnUser.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnUser.setOpaque(false);
				btnUser.setBackground(null);
			}
		});

		ImageIcon carownerIcon = GetImage.getIcon("carowner.png");
		JButton btnCarOwner = new JButton("Tùy chỉnh người sở hữu xe", carownerIcon);
		btnCarOwner.setHorizontalAlignment(SwingConstants.CENTER);
		btnCarOwner.setVerticalAlignment(SwingConstants.CENTER);
		btnCarOwner.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCarOwner.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCarOwner.setIconTextGap(40);
		btnCarOwner.setOpaque(false);
		btnCarOwner.setContentAreaFilled(false);
		btnCarOwner.setFocusPainted(false);
		btnCarOwner.setFont(f);

		btnCarOwner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCarOwner.setOpaque(true);
				btnCarOwner.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCarOwner.setOpaque(false);
				btnCarOwner.setBackground(null);
			}
		});

		pnMain.add(btnRole);
		pnMain.add(btnUser);
		pnMain.add(btnCarOwner);

		CardLayout clChild = (CardLayout) pnChild.getLayout();
		pnChild.add(new PnRole(pnChild, clChild), "Role");
		pnChild.add(new PnUser(pnChild, clChild), "User");
		pnChild.add(new PnCarOwner(pnChild, clChild), "CarOwner");


		btnRole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clChild.show(pnChild, "Role");
			}
		});

		btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clChild.show(pnChild, "User");
			}
		});

		btnCarOwner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clChild.show(pnChild, "CarOwner");
			}
		});
	}

}
