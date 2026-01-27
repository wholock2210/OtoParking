package otoparking.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
// import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import otoparking.DAO.BillDAO;
import otoparking.DAO.CarDAO;
import otoparking.DAO.ParkingHistoryDAO;
import otoparking.DAO.PriceListDAO;
import otoparking.DAO.RowCellDAO;
import otoparking.DAO.TypeCarDAO;
// import otoparking.DAO.UserDAO;
import otoparking.model.Bill;
import otoparking.model.Car;
import otoparking.model.ParkingHistory;
import otoparking.model.PriceList;
import otoparking.model.RowCell;
import otoparking.model.TypeCar;
import otoparking.ui.Panel.PnCamera;
import otoparking.utilities.CameraService;

public class Home extends JPanel {

	private static final long serialVersionUID = 1L;

	private PnCamera cameraPanel;
	private CameraService cameraService;
	private boolean cameraStarted = false;

	private TextField tfLicencePlate, tfUser, tfTotalTime, tfTotalMoney, tfParkingSite;
	private JComboBox<String> cbStatus;
	private JComboBox<TypeCar> cbTypeCar;
	private JSpinner spStart, spEnd;
	private CarDAO cDAO;
	private TypeCarDAO	tcDAO;
	private ParkingHistoryDAO phDAO;
	private RowCellDAO rcDAO;
	private PriceListDAO plDAO;
	private BillDAO bDAO;
	private double totalMoney, totalTime;

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

		cDAO = new CarDAO();
		tcDAO = new TypeCarDAO();
		phDAO = new ParkingHistoryDAO();
		rcDAO = new RowCellDAO();
		plDAO = new PriceListDAO();
		bDAO = new BillDAO();

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

	private void drawDetails(JPanel pnDetailsInfo){
		Font f = new Font("Segoe UI", Font.BOLD, 20);
		JLabel title = new JLabel("Gửi xe");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

		Font fnomal = new Font("Segoe UI", Font.PLAIN, 16);
        int height = 40;

		JLabel lbLicencePlate = new JLabel("Biển số");
		lbLicencePlate.setFont(f);
		tfLicencePlate = new TextField();
		tfLicencePlate.setFont(fnomal);
		tfLicencePlate.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfLicencePlate.setPreferredSize(new Dimension(0, height));

		JLabel lbTypeCar = new JLabel("Loại xe: ");
        lbTypeCar.setFont(f);
        TypeCarDAO typeCarDAO = new TypeCarDAO();
        DefaultComboBoxModel<TypeCar> modelTypeCar = new DefaultComboBoxModel<>();
        for (TypeCar t : typeCarDAO.FindAll()) {
            modelTypeCar.addElement(t);
        }
        cbTypeCar = new JComboBox<>(modelTypeCar);

        cbTypeCar.setFont(fnomal);
        cbTypeCar.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        cbTypeCar.setPreferredSize(new Dimension(0, height));
        cbTypeCar.setAlignmentX(0);

		JLabel lbUser = new JLabel("Người sỡ Hữu: ");
		lbUser.setFont(f);
		tfUser = new TextField();
		tfUser.setFont(fnomal);
		tfUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfUser.setPreferredSize(new Dimension(0, height));

		DefaultComboBoxModel<String> modelStatus =
			new DefaultComboBoxModel<>(new String[] {
				"Chưa đỗ",
				"Đang đỗ",
				"Xe Mới"
			});
		
		JLabel lbStatus = new JLabel("Trạng thái: ");
		lbStatus.setFont(f);
		cbStatus = new JComboBox<>(modelStatus);
		cbStatus.setFont(fnomal);
        cbStatus.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        cbStatus.setPreferredSize(new Dimension(0, height));
		cbStatus.setAlignmentX(0);

		JLabel lbStart = new JLabel("Thời gian vào: ");
        lbStart.setFont(f);
		SpinnerDateModel modelStart = new SpinnerDateModel(
			new java.util.Date(),
			null,
			null,
			Calendar.SECOND
		);
		spStart = new JSpinner(modelStart);
		spStart.setEditor(new JSpinner.DateEditor(spStart, "dd/MM/yyyy HH:mm:ss"));
		spStart.setFont(fnomal);
        spStart.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spStart.setPreferredSize(new Dimension(0, height));
		spStart.setAlignmentX(0);

		JLabel lbEnd = new JLabel("Thời gian ra: ");
        lbEnd.setFont(f);
		SpinnerDateModel modelEnd = new SpinnerDateModel(
			new java.util.Date(),
			null,
			null,
			Calendar.SECOND
		);
		spEnd = new JSpinner(modelEnd);
		spEnd.setEditor(new JSpinner.DateEditor(spEnd, "dd/MM/yyyy HH:mm:ss"));
		spEnd.setFont(fnomal);
        spEnd.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spEnd.setPreferredSize(new Dimension(0, height));
		spEnd.setAlignmentX(0);

		JLabel lbParkingSite = new JLabel("Khu vực đỗ: ");
		lbParkingSite.setFont(f);
		tfParkingSite = new TextField();
		tfParkingSite.setFont(fnomal);
		tfParkingSite.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfParkingSite.setPreferredSize(new Dimension(0, height));
		
		JLabel lbTotalTime = new JLabel("Tổng thời gian đỗ: ");
		lbTotalTime.setFont(f);
		tfTotalTime = new TextField();
		tfTotalTime.setFont(fnomal);
		tfTotalTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfTotalTime.setPreferredSize(new Dimension(0, height));

		JLabel lbTotalMoney = new JLabel("Tổng tiền: ");
		lbTotalMoney.setFont(f);
		tfTotalMoney = new TextField();
		tfTotalMoney.setFont(fnomal);
		tfTotalMoney.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfTotalMoney.setPreferredSize(new Dimension(0, height));
		

		pnDetailsInfo.add(title);
        pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbLicencePlate);
        pnDetailsInfo.add(tfLicencePlate);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbTypeCar);
        pnDetailsInfo.add(cbTypeCar);
		// pnDetailsInfo.add(Box.createVerticalStrut(10));
        // pnDetailsInfo.add(lbUser);
        // pnDetailsInfo.add(tfUser);
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
	}

	private void OnGetLicencePlate(){
		String licencePlate = cameraService.getLicencePlate();

		Car newCar = cDAO.FirstOfDefault(licencePlate);
		tfLicencePlate.setText(licencePlate);


		if(newCar == null) {
			ComboBoxModel<String> model = cbStatus.getModel();

			for(int i = 0; i < model.getSize();i++){
				String status = model.getElementAt(i);
				if(status.equals("Xe Mới")){
					cbStatus.setSelectedIndex(i);
					break;
				}
			}
			return;
		}

		ComboBoxModel<TypeCar> modelTypeCar = cbTypeCar.getModel();

		for(int i =0; i < modelTypeCar.getSize();i++){
			TypeCar typecar = modelTypeCar.getElementAt(i);
			if(newCar.getTypeCar().getId() == typecar.getId()){
				cbTypeCar.setSelectedIndex(i);
				break;
			}
		}
		

		ParkingHistory history = phDAO.FindParkedCar(newCar.getId());
		
		if(
			history == null ||
			history.getStartTime() != null &&
			history.getEndTime() != null
		){
			ComboBoxModel<String> model = cbStatus.getModel();

			for(int i = 0; i < model.getSize();i++){
				String status = model.getElementAt(i);
				if(status.equals("Chưa đỗ")){
					cbStatus.setSelectedIndex(i);
					break;
				}
			}
			return;
		}

		ComboBoxModel<String> model = cbStatus.getModel();

		for(int i = 0; i < model.getSize();i++){
			String status = model.getElementAt(i);
			if(status.equals("Đang đỗ")){
				cbStatus.setSelectedIndex(i);
				break;
			}
		}

		Timestamp startDate = history.getStartTime();
		spStart.setValue(startDate);
		spEnd.setValue(new java.util.Date());

		if(history.getRowCell() != null){
			RowCell rowCell = rcDAO.FirstOfDefault(history.getRowCell().getId());
			if(rowCell != null){
				tfParkingSite.setText("Tầng " + rowCell.getFloor().getSymbol() + 
									" Khu " + rowCell.getParkingRow().getSymbol() +
									rowCell.getCell().getSymbol() );
			}
		}

		java.util.Date startUtil = (java.util.Date) spStart.getValue();
		java.util.Date endUtil   = (java.util.Date) spEnd.getValue();

		Timestamp start = new Timestamp(startUtil.getTime());
		Timestamp end   = new Timestamp(endUtil.getTime());

		double diff = (end.getTime() - start.getTime());
		totalTime = diff / (1000.0 * 60 * 60);

		tfTotalTime.setText(String.format("%.2f", totalTime));

		PriceList price = plDAO.FindByTypeOfDefault(newCar.getTypeCar().getId());
		
		totalMoney = price.getPricePerHour() * totalTime;
		NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String money = vndFormat.format(totalMoney);

		tfTotalMoney.setText(money);
			
	}

	private void OnAddNewCar(){
		String NewlicensePlate;
        NewlicensePlate = tfLicencePlate.getText();
        TypeCar tc = (TypeCar)cbTypeCar.getSelectedItem();
        if (!NewlicensePlate.isBlank()
                && tc != null) {

            Car newCar = new Car(
                    NewlicensePlate,
                    tc);

            boolean result = cDAO.Insert(newCar);
            if (result)
                JOptionPane.showMessageDialog(
                        this,
                        (Object) "Thêm xe " + NewlicensePlate + " thành công",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(
                        this,
                        "Thêm xe " + NewlicensePlate + " thất bại",
                        "Thông Báo",
                        JOptionPane.ERROR_MESSAGE);
        }
	}

	private void OnRefesh(){
		String licencePlate = tfLicencePlate.getText();

		Car newCar = cDAO.FirstOfDefault(licencePlate);


		if(newCar == null) {
			ComboBoxModel<String> model = cbStatus.getModel();

			for(int i = 0; i < model.getSize();i++){
				String status = model.getElementAt(i);
				if(status.equals("Xe Mới")){
					cbStatus.setSelectedIndex(i);
					break;
				}
			}
			return;
		}

		ComboBoxModel<TypeCar> modelTypeCar = cbTypeCar.getModel();

		for(int i =0; i < modelTypeCar.getSize();i++){
			TypeCar typecar = modelTypeCar.getElementAt(i);
			if(newCar.getTypeCar().getId() == typecar.getId()){
				cbTypeCar.setSelectedIndex(i);
				break;
			}
		}
		

		ParkingHistory history = phDAO.FindParkedCar(newCar.getId());
		
		if(
			history == null ||
			history.getStartTime() != null &&
			history.getEndTime() != null
		){
			ComboBoxModel<String> model = cbStatus.getModel();

			for(int i = 0; i < model.getSize();i++){
				String status = model.getElementAt(i);
				if(status.equals("Chưa đỗ")){
					cbStatus.setSelectedIndex(i);
					break;
				}
			}
			return;
		}

		ComboBoxModel<String> model = cbStatus.getModel();

		for(int i = 0; i < model.getSize();i++){
			String status = model.getElementAt(i);
			if(status.equals("Đang đỗ")){
				cbStatus.setSelectedIndex(i);
				break;
			}
		}

		Timestamp startDate = history.getStartTime();
		spStart.setValue(startDate);
		spEnd.setValue(new java.util.Date());

		if(history.getRowCell() != null){
			RowCell rowCell = rcDAO.FirstOfDefault(history.getRowCell().getId());
			if(rowCell != null){
				tfParkingSite.setText("Tầng " + rowCell.getFloor().getSymbol() + 
									" Khu " + rowCell.getParkingRow().getSymbol() +
									rowCell.getCell().getSymbol() );
			}
		}


		java.util.Date startUtil = (java.util.Date) spStart.getValue();
		java.util.Date endUtil   = (java.util.Date) spEnd.getValue();

		Timestamp start = new Timestamp(startUtil.getTime());
		Timestamp end   = new Timestamp(endUtil.getTime());

		double diff = (end.getTime() - start.getTime());
		totalTime = diff / (1000.0 * 60 * 60);

		tfTotalTime.setText(String.format("%.2f", totalTime));

		PriceList price = plDAO.FindByTypeOfDefault(newCar.getTypeCar().getId());
		
		totalMoney = price.getPricePerHour() * totalTime;
		NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String money = vndFormat.format(totalMoney);

		tfTotalMoney.setText(money);
	}


	private void OnJBCarIn(){
		String licencePlate = tfLicencePlate.getText();
		if(licencePlate.isBlank()){
			JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập biển số",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Car currentCar = cDAO.FirstOfDefault(licencePlate);
		if(currentCar == null){
			JOptionPane.showMessageDialog(
                        this,
                        "vui lòng đăng ký xe vào cơ sở dữ liệu trước!",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		ParkingHistory isParked = phDAO.FindParkedCar(currentCar.getId());

		if(isParked != null && isParked.getEndTime() == null){
			JOptionPane.showMessageDialog(
                        this,
                        "xe vẫn còn đang đậu!",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		ParkingHistory newParking = new ParkingHistory(currentCar,
			 new java.sql.Timestamp(System.currentTimeMillis()));

		boolean result = phDAO.Insert(newParking);
		if(result)
			JOptionPane.showMessageDialog(
				this,
				"đã đỗ xe " + currentCar.getLicensePlate() + " thành công",
				"Thông Báo",
				JOptionPane.INFORMATION_MESSAGE
			);
		else
			JOptionPane.showMessageDialog(
				this,
				"đã đỗ xe " + currentCar.getLicensePlate() + " thất bại",
				"Thông Báo",
				JOptionPane.ERROR_MESSAGE
			);
	}

	private void OnJbCarOut(){
		String licencePlate = tfLicencePlate.getText();
		if(licencePlate.isBlank()){
			JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập biển số",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Car currentCar = cDAO.FirstOfDefault(licencePlate);
		if(currentCar == null){
			JOptionPane.showMessageDialog(
                        this,
                        "vui lòng đăng ký xe vào cơ sở dữ liệu trước!",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		ParkingHistory isParked = phDAO.FindParkedCar(currentCar.getId());

		if(isParked != null && isParked.getEndTime() != null && isParked.getStartTime() != null){
			JOptionPane.showMessageDialog(
                        this,
                        "xe chưa được đỗ!",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		ParkingHistory currentParking = isParked;

		currentParking.setEndTime(new java.sql.Timestamp(System.currentTimeMillis()));

		if(totalMoney == 0.0 || totalMoney < 0.0){
			JOptionPane.showMessageDialog(
                        this,
                        "có lỗi xảy ra hãy nhập lại thông tin",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		currentParking.setParkingMinutes(totalTime);

		boolean result = phDAO.Update(currentParking);
		if(result){

			Bill newBill = new Bill(currentParking, totalMoney, new java.sql.Timestamp(System.currentTimeMillis()));

			boolean result1 = bDAO.Insert(newBill);
			NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
			String money = vndFormat.format(totalMoney);

			if(result1)
				JOptionPane.showMessageDialog(
                        this,
                        "thanh toán thành công cho xe " + currentCar.getLicensePlate() + "với số tiền là : " + money,
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(
                        this,
                        "thanh toán thất bại",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(
                        this,
                        "có lỗi khi thao tác với lịch sử đỗ xe",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
		}
		

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
        JPanel pnDetailsFunction = new JPanel(new GridLayout(3, 2, 10, 10));
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
		JButton jbGetLicencePlate = new JButton("Lấy biến số Tự động");
        jbGetLicencePlate.setFont(f);
		JButton jbRefesh = new JButton("Lấy Thủ công");
        jbRefesh.setFont(f);

        pnDetailsFunction.add(jbCarIn);
        pnDetailsFunction.add(jbCarOut);
        pnDetailsFunction.add(jbAddNewCar);
        pnDetailsFunction.add(jbGetLicencePlate);
		pnDetailsFunction.add(jbRefesh);

		//details info

		drawDetails(pnDetailsInfo);
		
		//events

		jbGetLicencePlate.addActionListener(e -> {
			OnGetLicencePlate();
		});
		jbAddNewCar.addActionListener(e -> {
			OnAddNewCar();
		});
		jbRefesh.addActionListener(e -> {
			OnRefesh();
		});
		jbCarIn.addActionListener(e -> {
			OnJBCarIn();
		});

		jbCarOut.addActionListener(e -> {
			OnJbCarOut();
		});
	}

}
