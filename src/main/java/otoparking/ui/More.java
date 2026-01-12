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

public class More extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public More() {
		setLayout(new BorderLayout());
		//setBackground(new Color(182, 187, 195));
		
		JPanel pnMain = new JPanel(new GridLayout(2, 2, 10, 10));
		JPanel pnContent = new JPanel(new CardLayout());
		add(pnContent, BorderLayout.CENTER);
		pnContent.add(pnMain, "Main");
		pnMain.setBackground(new Color(182, 187, 195));
		//button 
		
		Font f = new Font("Segoe UI", Font.BOLD, 20);
		Color hover = new Color(0,0,0, 30);
		
		ImageIcon userIcon = GetImage.getIcon("user.png");
		JButton btnUser = new JButton("tùy chỉnh người dùng",userIcon);
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
		
		ImageIcon carIcon = GetImage.getIcon("car.png");
		JButton btnCar = new JButton("tùy chỉnh xe",carIcon);
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
		
		ImageIcon parkingIcon = GetImage.getIcon("parking.png");
		JButton btnParking = new JButton("tùy chỉnh nơi đỗ", parkingIcon);
		btnParking.setHorizontalAlignment(SwingConstants.CENTER);
		btnParking.setVerticalAlignment(SwingConstants.CENTER);
		btnParking.setHorizontalTextPosition(SwingConstants.CENTER);
		btnParking.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnParking.setIconTextGap(40);
		btnParking.setOpaque(false);
		btnParking.setContentAreaFilled(false);
		btnParking.setFocusPainted(false);
		btnParking.setFont(f);
		
		btnParking.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnParking.setOpaque(true);
		    	btnParking.setBackground(hover);
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnParking.setOpaque(false);
		    	btnParking.setBackground(null);
		    }
		});
		
		ImageIcon historyIcon = GetImage.getIcon("history.png");
		JButton btnHistory = new JButton("tùy chỉnh lịch sử hoặc hóa đơn",historyIcon);
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
		
		pnMain.add(btnUser);
		pnMain.add(btnCar);
		pnMain.add(btnParking);
		pnMain.add(btnHistory);
		
		
		
		CardLayout cl = (CardLayout)pnContent.getLayout();
		pnContent.add(new UserMenu(pnContent, cl), "User");
		pnContent.add(new CarMenu(pnContent, cl), "Car");
		pnContent.add(new ParkingMenu(pnContent, cl), "Parking");
		pnContent.add(new HistoryMenu(pnContent, cl), "History");
		// Child User
		
		
		
		//event click
		btnUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(pnContent, "User");
			}
		});
		btnCar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(pnContent, "Car");
			}
		});
		btnParking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(pnContent, "Parking");
			}
		});
		btnHistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(pnContent, "History");
			}
		});
		

	}

}
