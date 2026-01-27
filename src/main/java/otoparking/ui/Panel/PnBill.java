package otoparking.ui.Panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
// import javax.swing.ComboBoxModel;
// import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
// import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import otoparking.utilities.GetImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import otoparking.DAO.*;
import otoparking.model.*;

public class PnBill extends JPanel{
    private static final long serialVersionUID = 1L;

	//option
	Font f = new Font("Segoe UI", Font.BOLD, 20);

	//table
	private JTable table;
	private DefaultTableModel tableModel;

	//details
	JTextField tfId, tfIdParkingHistory, tfTotalAmount, tfCreatedAt;
    

	//db
	BillDAO bDAO;
	Bill currentBill;


	private JTable InitTable(){
		tableModel = new DefaultTableModel(new String[] {
			"ID", "IdParkingHistory", "Tổng tiền", "Tạo lúc"
		}, 0);

		LoadDataToTableModel(); 

		JTable table = new JTable(tableModel);

		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		sorter.setSortKeys(List.of(
			new RowSorter.SortKey(0, SortOrder.ASCENDING)
		));
		table.setRowSorter(sorter);

		table.setFont(f);
		table.setRowHeight(40);
		
		return table;
	}

	private void LoadDataToTableModel(){
		tableModel.setRowCount(0); 

		for(Bill u : bDAO.FindAll()){
            if (u.getParkingHistory() == null){
				ParkingHistory parkingHistory = new ParkingHistory();
				u.setParkingHistory(parkingHistory);
			}
                
			tableModel.addRow(new Object[]{
				u.getId(),
                u.getParkingHistory().getId(),
                u.getTotalAmount(),                
                u.getCreatedAt()
			});
		}
	}
	

	private void DrawDetails(JPanel pnDetailsInfo){
		JLabel title = new JLabel("Hóa đơn");
		title.setFont(new Font("Segoe UI", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		Font fnomal = new Font("Segoe UI", Font.PLAIN, 16);
		int height = 40;

		JLabel lbId = new JLabel("ID: ");
		lbId.setFont(f);
		tfId = new JTextField();
		tfId.setFont(fnomal);
		tfId.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfId.setPreferredSize(new Dimension(0, height));

		JLabel lbParkingHistory = new JLabel("ID Parking: ");
		lbParkingHistory.setFont(f);
		tfIdParkingHistory = new JTextField();
		tfIdParkingHistory.setFont(fnomal);
		tfIdParkingHistory.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfIdParkingHistory.setPreferredSize(new Dimension(0, height));

        JLabel lbTotalAmount = new JLabel("Tổng tiền: ");
		lbTotalAmount.setFont(f);
		tfTotalAmount = new JTextField();
		tfTotalAmount.setFont(fnomal);
		tfTotalAmount.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfTotalAmount.setPreferredSize(new Dimension(0, height));
        
        JLabel lbCreatedAt = new JLabel("Được tạo lúc: ");
		lbCreatedAt.setFont(f);
		tfCreatedAt = new JTextField();
		tfCreatedAt.setFont(fnomal);
		tfCreatedAt.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfCreatedAt.setPreferredSize(new Dimension(0, height));

		pnDetailsInfo.add(title);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(lbId);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(tfId);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(lbParkingHistory);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(tfIdParkingHistory);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbTotalAmount);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(tfTotalAmount);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbCreatedAt);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(tfCreatedAt);
		

	}

	private void onTableSelected(ListSelectionEvent e){
		if (!e.getValueIsAdjusting()) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int modelRow = table.convertRowIndexToModel(selectedRow);
				int id = (int) tableModel.getValueAt(modelRow, 0);
				

				currentBill = bDAO.FirstOfDefault(id);

				if(currentBill != null){
					tfId.setText(String.valueOf(currentBill.getId()));
                    tfIdParkingHistory.setText(String.valueOf(currentBill.getParkingHistory().getId()));
					tfTotalAmount.setText(String.valueOf(currentBill.getTotalAmount()));
					tfCreatedAt.setText(String.valueOf(currentBill.getCreatedAt()));
				}
				else{
					tfIdParkingHistory.setText("NULL");
					System.out.print("Hóa đơn lỗi: " + currentBill.getParkingHistory().getId());
				}
			}
		}
	}

	// private void onJBCreate(){
	// 	ParkingHistory tc = (TypeCar) cbTypeCar.getSelectedItem();
	// 	if(
	// 		tc != null
	// 	){
	// 		double pricePerHour = 0;
	// 		if(tfPricePerHour.getText().length() > 0 && !tfPricePerHour.getText().isBlank()){
	// 			pricePerHour = Double.parseDouble(tfPricePerHour.getText());
	// 		}

	// 		PriceList newPriceList = new PriceList(
	// 			tc,
    //             pricePerHour
	// 		);

	// 		boolean result = plDAO.Insert(newPriceList);
	// 		if(result)
	// 			JOptionPane.showMessageDialog(
	// 				this,
	// 				"Thêm giá " + pricePerHour + " cho loại " + tc.getName() + " thành công",
	// 				"Thông Báo",
	// 				JOptionPane.INFORMATION_MESSAGE
	// 			);
	// 		else
	// 			JOptionPane.showMessageDialog(
	// 				this,
	// 				"Thêm giá " + pricePerHour + " cho loại " + tc.getName() + " thất bại",
	// 				"Thông Báo",
	// 				JOptionPane.ERROR_MESSAGE
	// 			);
	// 		LoadDataToTableModel();
	// 	}
	// }

	private void onJBDelete(){
		if(currentBill == null){
			JOptionPane.showMessageDialog(this, "vui lòng chọn hóa đơn cần xóa!", "thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		int result = JOptionPane.showConfirmDialog(this, 
			"bạn có chắc muốn xóa hóa đơn : " + currentBill.getId() + " không?",
			"xác nhận xóa",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE
		);
		if(result == JOptionPane.YES_OPTION){
			boolean isDel = bDAO.Delete(currentBill);
			if(isDel)
				JOptionPane.showMessageDialog(
					this,
					"xóa hóa đơn : " + currentBill.getId() +
                    " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"xóa hóa đơn : " + currentBill.getId() +
                    " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			currentBill = null;
			LoadDataToTableModel();
		}
	}




	public PnBill(JPanel pnContent, CardLayout cl) {
		setLayout(new BorderLayout());
		setBackground(new Color(238, 238, 240));

		JPanel pnMain = new JPanel(new BorderLayout());
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
		JButton btnBack = new JButton("Trở về trang quản lý người dùng", backIcon);
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

		// content
		bDAO = new BillDAO();

		//// list
		
		table = InitTable();
		JScrollPane scrollPane = new JScrollPane(table);
		pnMain.add(scrollPane);

		//// details

		JPanel pnDetail = new JPanel(new BorderLayout());
		JPanel pnDetailsInfo = new JPanel();
		JPanel pnDetailsFunction = new JPanel(new GridLayout(2, 2, 10, 10));
		pnDetail.add(pnDetailsFunction, BorderLayout.SOUTH);
		pnDetailsInfo.setLayout(new BoxLayout(pnDetailsInfo, BoxLayout.Y_AXIS));
		JScrollPane spDetails = new JScrollPane(pnDetailsInfo);
		pnDetail.add(spDetails, BorderLayout.CENTER);
		pnMain.add(pnDetail, BorderLayout.EAST);
		pnDetail.setPreferredSize(new Dimension(350, 0));

		//// details function

		// JButton jbCreate = new JButton("Thêm");
		// jbCreate.setFont(f);
		JButton jbDelete = new JButton("Xóa");
		jbDelete.setFont(f);
		// JButton jbEdit = new JButton("Sửa");
		// jbEdit.setFont(f);

		// pnDetailsFunction.add(jbCreate);
		pnDetailsFunction.add(jbDelete);
		// pnDetailsFunction.add(jbEdit);

		//// details info
		
		DrawDetails(pnDetailsInfo);
		// Events


		table.getSelectionModel().addListSelectionListener(e -> {
			onTableSelected(e);
		});

		// jbCreate.addActionListener(e -> {
		// 	onJBCreate();
		// });

		jbDelete.addActionListener(e -> {
			onJBDelete();
		});

		// jbEdit.addActionListener(e ->{
		// 	onJBEdit();
		// });
		
	}
}
