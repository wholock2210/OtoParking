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
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
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

		DefaultComboBoxModel<String> modelStatus =
			new DefaultComboBoxModel<>(new String[] {
				"Chưa đỗ",
				"Đang đỗ"
			});
		
		JLabel lbStatus = new JLabel("Trạng thái: ");
		lbStatus.setFont(f);
		JComboBox<String> cbStatus = new JComboBox<>(modelStatus);
		cbStatus.setFont(fnomal);
        cbStatus.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        cbStatus.setPreferredSize(new Dimension(0, height));
		cbStatus.setAlignmentX(0);

		JLabel lbStart = new JLabel("Thời gian vào: ");
        lbStart.setFont(f);
		SpinnerDateModel modelStart = new SpinnerDateModel();
		JSpinner spStart = new JSpinner(modelStart);
		spStart.setEditor(new JSpinner.DateEditor(spStart, "dd,MM,yyyy"));
		spStart.setFont(fnomal);
        spStart.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spStart.setPreferredSize(new Dimension(0, height));
		spStart.setAlignmentX(0);

		JLabel lbEnd = new JLabel("Thời gian ra: ");
        lbEnd.setFont(f);
		SpinnerDateModel modelEnd = new SpinnerDateModel();
		JSpinner spEnd = new JSpinner(modelEnd);
		spEnd.setEditor(new JSpinner.DateEditor(spEnd, "dd,MM,yyyy"));
		spEnd.setFont(fnomal);
        spEnd.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spEnd.setPreferredSize(new Dimension(0, height));
		spEnd.setAlignmentX(0);

		JLabel lbTotalTime = new JLabel("Tổng thời gian đỗ: ");
		lbTotalTime.setFont(f);
		TextField tfTotalTime = new TextField();
		tfTotalTime.setFont(fnomal);
		tfTotalTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfTotalTime.setPreferredSize(new Dimension(0, height));

		JLabel lbTotalMoney = new JLabel("Tổng tiền: ");
		lbTotalMoney.setFont(f);
		TextField tfTotalMoney = new TextField();
		tfTotalMoney.setFont(fnomal);
		tfTotalMoney.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfTotalMoney.setPreferredSize(new Dimension(0, height));
		

		pnDetailsInfo.add(title);
        pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbLicencePlate);
        pnDetailsInfo.add(tfLicencePlate);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbUser);
        pnDetailsInfo.add(tfUser);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbStatus);
        pnDetailsInfo.add(cbStatus);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbStart);
        pnDetailsInfo.add(spStart);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbEnd);
        pnDetailsInfo.add(spEnd);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbTotalTime);
        pnDetailsInfo.add(tfTotalTime);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbTotalMoney);
        pnDetailsInfo.add(tfTotalMoney);


		//events

		jbGetLicencePlate.addActionListener(e -> {
			tfLicencePlate.setText(cameraService.getLicencePlate());
		});
	}

}
