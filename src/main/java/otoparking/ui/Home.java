package otoparking.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import otoparking.ui.Panel.PnCamera;
import otoparking.utilities.CameraService;

public class Home extends JPanel {

	private static final long serialVersionUID = 1L;

	private PnCamera cameraPanel;
	private CameraService cameraService;
	private boolean cameraStarted = false;

	/**
	 * Create the panel.
	 */
	public Home() {
		// setLayout(new GridBagLayout());
		// setBackground(new Color(238, 238, 240));
		
		// JLabel lb = new JLabel("Home Panel");
		// Font f = new Font("Segoe UI", Font.BOLD, 24);
		// lb.setFont(f);
		// add(lb);
		Init();
		Draw();
	}

	private void Init(){
		cameraPanel = new PnCamera();
        cameraService = new CameraService();

		cameraService.Start(cameraPanel);
	}

	public void onShow() {
		System.out.println("vao home");
		if (!cameraStarted) {
			cameraService.Start(cameraPanel);
			cameraStarted = true;
		}
	}

	public void onHide() {
		System.out.println("roi home");
		cameraService.Stop();
		cameraStarted = false;
	}


	private void Draw(){
		setLayout(new BorderLayout());
        setBackground(new Color(238, 238, 240));
		
		JPanel rightPn = new JPanel(new BorderLayout());
		JPanel leftPn = new JPanel(new BorderLayout());

		add(leftPn, BorderLayout.EAST);
		leftPn.setPreferredSize(new Dimension(350, 0));
		add(rightPn, BorderLayout.CENTER);

		rightPn.add(cameraPanel);

		Font f = new Font("Segoe UI", Font.BOLD, 20);

		//detail

		JPanel pnDetailsInfo = new JPanel();
        JPanel pnDetailsFunction = new JPanel(new GridLayout(2, 2, 10, 10));
		leftPn.add(pnDetailsFunction, BorderLayout.SOUTH);
		pnDetailsInfo.setLayout(new BoxLayout(pnDetailsInfo, BoxLayout.Y_AXIS));
        JScrollPane spDetails = new JScrollPane(pnDetailsInfo);
        leftPn.add(spDetails, BorderLayout.CENTER);

		//// details function

        JButton jbCarIn = new JButton("Gửi xe");
        jbCarIn.setFont(f);
        JButton jbCarOut = new JButton("Trả Xe");
        jbCarOut.setFont(f);
        JButton jbAddNewCar = new JButton("Thêm xe mới");
        jbAddNewCar.setFont(f);
		JButton jbGetLicencePlate = new JButton("Lấy biến số");
        jbGetLicencePlate.setFont(f);

        pnDetailsFunction.add(jbCarIn);
        pnDetailsFunction.add(jbCarOut);
        pnDetailsFunction.add(jbAddNewCar);
        pnDetailsFunction.add(jbGetLicencePlate);

		//details info

		JLabel title = new JLabel("Gửi xe");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

		Font fnomal = new Font("Segoe UI", Font.PLAIN, 16);
        int height = 40;

		JLabel lbLicencePlate = new JLabel("Biển số");
		lbLicencePlate.setFont(f);
		TextField tfLicencePlate = new TextField();
		tfLicencePlate.setFont(fnomal);
		tfLicencePlate.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfLicencePlate.setPreferredSize(new Dimension(0, height));

		JLabel lbUser = new JLabel("Người sỡ Hữu: ");
		lbUser.setFont(f);
		TextField tfUser = new TextField();
		tfUser.setFont(fnomal);
		tfUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfUser.setPreferredSize(new Dimension(0, height));

		// JLabel lbStatus = new JLabel("Trạng thái: ");
		// lbStatus.setFont(f);
		// DefaultComboBoxModel<String> modelRole = new DefaultComboBoxModel<>(
		// 	"Chưa đỗ",
		// 	"Đang đỗ"
		// );
        // JComboBox cbStatus = new JComboBox<>(modelRole);

		

		pnDetailsInfo.add(title);
        pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbLicencePlate);
        pnDetailsInfo.add(tfLicencePlate);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbUser);
        pnDetailsInfo.add(tfUser);


		//events

		jbGetLicencePlate.addActionListener(e -> {
			tfLicencePlate.setText(cameraService.getLicencePlate());
		});
	}

}
