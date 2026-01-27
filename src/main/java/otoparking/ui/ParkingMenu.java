package otoparking.ui;

import otoparking.ui.Panel.PnCell;
import otoparking.ui.Panel.PnFloor;
import otoparking.ui.Panel.PnParkingRow;
import otoparking.ui.Panel.PnRowCell;
import otoparking.ui.Panel.PnStatus;
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

public class ParkingMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ParkingMenu(JPanel pnContent, CardLayout cl) {
		setLayout(new BorderLayout());
		setBackground(new Color(238, 238, 240));

		JPanel pnMain = new JPanel(new GridLayout(2, 3, 10, 10));
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

		ImageIcon statusIcon = GetImage.getIcon("status.png");
		JButton btnStatus = new JButton("Tùy chỉnh trạng thái", statusIcon);
		btnStatus.setHorizontalAlignment(SwingConstants.CENTER);
		btnStatus.setVerticalAlignment(SwingConstants.CENTER);
		btnStatus.setHorizontalTextPosition(SwingConstants.CENTER);
		btnStatus.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnStatus.setIconTextGap(40);
		btnStatus.setOpaque(false);
		btnStatus.setContentAreaFilled(false);
		btnStatus.setFocusPainted(false);
		btnStatus.setFont(f);

		btnStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStatus.setOpaque(true);
				btnStatus.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnStatus.setOpaque(false);
				btnStatus.setBackground(null);
			}
		});

		ImageIcon floorIcon = GetImage.getIcon("floor.png");
		JButton btnFloor = new JButton("Tùy chỉnh tầng", floorIcon);
		btnFloor.setHorizontalAlignment(SwingConstants.CENTER);
		btnFloor.setVerticalAlignment(SwingConstants.CENTER);
		btnFloor.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFloor.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnFloor.setIconTextGap(40);
		btnFloor.setOpaque(false);
		btnFloor.setContentAreaFilled(false);
		btnFloor.setFocusPainted(false);
		btnFloor.setFont(f);

		btnFloor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFloor.setOpaque(true);
				btnFloor.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFloor.setOpaque(false);
				btnFloor.setBackground(null);
			}
		});

		ImageIcon rowIcon = GetImage.getIcon("row.png");
		JButton btnRow = new JButton("Tùy chỉnh hàng", rowIcon);
		btnRow.setHorizontalAlignment(SwingConstants.CENTER);
		btnRow.setVerticalAlignment(SwingConstants.CENTER);
		btnRow.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRow.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRow.setIconTextGap(40);
		btnRow.setOpaque(false);
		btnRow.setContentAreaFilled(false);
		btnRow.setFocusPainted(false);
		btnRow.setFont(f);

		btnRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRow.setOpaque(true);
				btnRow.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRow.setOpaque(false);
				btnRow.setBackground(null);
			}
		});

		ImageIcon cellIcon = GetImage.getIcon("cell.png");
		JButton btnCell = new JButton("Tùy chỉnh Ô ", cellIcon);
		btnCell.setHorizontalAlignment(SwingConstants.CENTER);
		btnCell.setVerticalAlignment(SwingConstants.CENTER);
		btnCell.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCell.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCell.setIconTextGap(40);
		btnCell.setOpaque(false);
		btnCell.setContentAreaFilled(false);
		btnCell.setFocusPainted(false);
		btnCell.setFont(f);

		btnCell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCell.setOpaque(true);
				btnCell.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCell.setOpaque(false);
				btnCell.setBackground(null);
			}
		});

		ImageIcon rowCellIcon = GetImage.getIcon("rowcell.png");
		JButton btnRowCell = new JButton("Tùy chỉnh chỗ đậu xe", rowCellIcon);
		btnRowCell.setHorizontalAlignment(SwingConstants.CENTER);
		btnRowCell.setVerticalAlignment(SwingConstants.CENTER);
		btnRowCell.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRowCell.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRowCell.setIconTextGap(40);
		btnRowCell.setOpaque(false);
		btnRowCell.setContentAreaFilled(false);
		btnRowCell.setFocusPainted(false);
		btnRowCell.setFont(f);

		btnRowCell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRowCell.setOpaque(true);
				btnRowCell.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRowCell.setOpaque(false);
				btnRowCell.setBackground(null);
			}
		});

		pnMain.add(btnStatus);
		pnMain.add(btnFloor);
		pnMain.add(btnRow);
		pnMain.add(btnCell);
		pnMain.add(btnRowCell);

		CardLayout clChild = (CardLayout) pnChild.getLayout();
		pnChild.add(new PnCell(pnChild, clChild), "Cell");
		pnChild.add(new PnFloor(pnChild, clChild), "Floor");
		pnChild.add(new PnParkingRow(pnChild, clChild), "Parking Row");
		pnChild.add(new PnStatus(pnChild, clChild), "Status");
		pnChild.add(new PnRowCell(pnChild, clChild), "Row Cell");

		btnCell.addActionListener(e -> {
			clChild.show(pnChild, "Cell");
		});

		btnFloor.addActionListener(e -> {
			clChild.show(pnChild, "Floor");
		});

		btnRow.addActionListener(e -> {
			clChild.show(pnChild, "Parking Row");
		});

		btnStatus.addActionListener(e -> {
			clChild.show(pnChild, "Status");
		});

		btnRowCell.addActionListener(e -> {
			clChild.show(pnChild, "Row Cell");
		});
	}

}
