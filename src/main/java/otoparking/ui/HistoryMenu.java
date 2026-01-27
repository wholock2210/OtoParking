package otoparking.ui;

import otoparking.ui.Panel.PnBill;
import otoparking.ui.Panel.PnParkingHistory;
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

public class HistoryMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HistoryMenu(JPanel pnContent, CardLayout cl) {
		setLayout(new BorderLayout());
		setBackground(new Color(238, 238, 240));

		JPanel pnMain = new JPanel(new GridLayout(1, 2, 10, 10));
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

		ImageIcon historyIcon = GetImage.getIcon("parkinghistory.png");
		JButton btnHistory = new JButton("Tùy chỉnh lịch sử đậu xe", historyIcon);
		btnHistory.setHorizontalAlignment(SwingConstants.CENTER);
		btnHistory.setVerticalAlignment(SwingConstants.CENTER);
		btnHistory.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHistory.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHistory.setIconTextGap(40);
		btnHistory.setOpaque(false);
		btnHistory.setContentAreaFilled(false);
		btnHistory.setFocusPainted(false);
		btnHistory.setFont(f);

		btnHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHistory.setOpaque(true);
				btnHistory.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnHistory.setOpaque(false);
				btnHistory.setBackground(null);
			}
		});

		ImageIcon billIcon = GetImage.getIcon("bill.png");
		JButton btnBill = new JButton("Tùy chỉnh hóa đơn", billIcon);
		btnBill.setHorizontalAlignment(SwingConstants.CENTER);
		btnBill.setVerticalAlignment(SwingConstants.CENTER);
		btnBill.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBill.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBill.setIconTextGap(40);
		btnBill.setOpaque(false);
		btnBill.setContentAreaFilled(false);
		btnBill.setFocusPainted(false);
		btnBill.setFont(f);

		btnBill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBill.setOpaque(true);
				btnBill.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBill.setOpaque(false);
				btnBill.setBackground(null);
			}
		});

		pnMain.add(btnHistory);
		pnMain.add(btnBill);

		CardLayout clChild = (CardLayout) pnChild.getLayout();
		pnChild.add(new PnParkingHistory(pnChild, clChild), "ParkingHistory");
		pnChild.add(new PnBill(pnChild, clChild), "Bill");
		

		btnHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				clChild.show(pnChild, "ParkingHistory");
			}
		});

		btnBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				clChild.show(pnChild, "Bill");
			}
		});
	}

	

}
