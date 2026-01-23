package otoparking;

import otoparking.ui.*;
import otoparking.utilities.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import nu.pattern.OpenCV;

public class Main extends JFrame {
	static {
		OpenCV.loadLocally();
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Draw();
	}

	private void Draw() {
		JPanel pnMain = new JPanel(new BorderLayout());
		setContentPane(pnMain);
		JPanel pnContent = new JPanel(new CardLayout());
		pnMain.add(pnContent, BorderLayout.CENTER);
		JPanel wrapperNaviation = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		JPanel pnNavigation = new JPanel(new BorderLayout());
		wrapperNaviation.add(pnNavigation, gbc);
		pnMain.add(wrapperNaviation, BorderLayout.WEST);
		wrapperNaviation.setBackground(new Color(30,30,30,30));
		pnNavigation.setBackground(new Color(30,30,30,30));

		// Naviagion gen

		JPanel ctnButtonNavigation = new JPanel();
		ctnButtonNavigation.setLayout(
				new BoxLayout(ctnButtonNavigation, BoxLayout.Y_AXIS));
		ctnButtonNavigation.setBackground(new Color(30,30,30,30));
		pnNavigation.add(ctnButtonNavigation, BorderLayout.CENTER);
		Font f = new Font("Segoe UI", Font.BOLD, 14);
		Color hover = new Color(240, 242, 243);

		ImageIcon homeIcon = GetImage.getIcon("home.png");

		JButton btnHome = new JButton("Home", homeIcon);
		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		btnHome.setIconTextGap(10);
		btnHome.setPreferredSize(new Dimension(Integer.MAX_VALUE, 90));
		btnHome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
		btnHome.setAlignmentX(LEFT_ALIGNMENT);
		btnHome.setOpaque(false);
		btnHome.setContentAreaFilled(false);
		btnHome.setBorderPainted(false);
		btnHome.setFocusPainted(false);
		btnHome.setFont(f);

		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHome.setOpaque(true);
				btnHome.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnHome.setOpaque(false);
				btnHome.setBackground(null);
			}
		});

		ImageIcon moreIcon = GetImage.getIcon("more.png");
		JButton btnMore = new JButton("More", moreIcon);
		btnMore.setHorizontalAlignment(SwingConstants.LEFT);
		btnMore.setIconTextGap(10);
		btnMore.setPreferredSize(new Dimension(Integer.MAX_VALUE, 90));
		btnMore.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
		btnMore.setAlignmentX(LEFT_ALIGNMENT);
		btnMore.setOpaque(false);
		btnMore.setContentAreaFilled(false);
		btnMore.setBorderPainted(false);
		btnMore.setFocusPainted(false);
		btnMore.setFont(f);

		btnMore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMore.setOpaque(true);
				btnMore.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMore.setOpaque(false);
				btnMore.setBackground(null);
			}
		});

		ImageIcon settingIcon = GetImage.getIcon("setting.png");
		JButton btnSetting = new JButton("Setting", settingIcon);
		btnSetting.setHorizontalAlignment(SwingConstants.LEFT);
		btnSetting.setIconTextGap(10);
		btnSetting.setPreferredSize(new Dimension(Integer.MAX_VALUE, 90));
		btnSetting.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
		btnSetting.setAlignmentX(LEFT_ALIGNMENT);
		btnSetting.setOpaque(false);
		btnSetting.setContentAreaFilled(false);
		btnSetting.setBorderPainted(false);
		btnSetting.setFocusPainted(false);
		btnSetting.setFont(f);

		btnSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSetting.setOpaque(true);
				btnSetting.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSetting.setOpaque(false);
				btnSetting.setBackground(null);
			}
		});

		ImageIcon infoIcon = GetImage.getIcon("info.png");
		JButton btnInfo = new JButton("Info", infoIcon);
		btnInfo.setHorizontalAlignment(SwingConstants.LEFT);
		btnInfo.setIconTextGap(10);
		btnInfo.setPreferredSize(new Dimension(Integer.MAX_VALUE, 90));
		btnInfo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
		btnInfo.setAlignmentX(LEFT_ALIGNMENT);
		btnInfo.setOpaque(false);
		btnInfo.setContentAreaFilled(false);
		btnInfo.setBorderPainted(false);
		btnInfo.setFocusPainted(false);
		btnInfo.setFont(f);

		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnInfo.setOpaque(true);
				btnInfo.setBackground(hover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnInfo.setOpaque(false);
				btnInfo.setBackground(null);
			}
		});

		ctnButtonNavigation.add(btnHome);
		ctnButtonNavigation.add(Box.createVerticalStrut(8));
		ctnButtonNavigation.add(btnMore);
		ctnButtonNavigation.add(Box.createVerticalStrut(8));
		ctnButtonNavigation.add(btnSetting);
		ctnButtonNavigation.add(Box.createVerticalStrut(8));
		ctnButtonNavigation.add(btnInfo);

		// event click button

		pnContent.add(new Home(), "Home");
		pnContent.add(new More(), "More");
		pnContent.add(new Setting(), "Setting");
		pnContent.add(new Info(), "Info");

		CardLayout cl = (CardLayout) pnContent.getLayout();

		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(pnContent, "Home");
			}
		});
		btnMore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(pnContent, "More");
			}
		});
		btnSetting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(pnContent, "Setting");
			}
		});
		btnInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(pnContent, "Info");
			}
		});

		// Event Resize
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int w = getWidth();
				int h = getHeight();
				wrapperNaviation.setPreferredSize(new Dimension((int) (w * 0.1), h));
				pnMain.revalidate();
			}
		});
	}

}
