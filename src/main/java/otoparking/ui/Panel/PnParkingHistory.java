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

public class PnParkingHistory extends JPanel{
    private static final long serialVersionUID = 1L;

	//option
	Font f = new Font("Segoe UI", Font.BOLD, 20);

	//table
	private JTable table;
	private DefaultTableModel tableModel;

	//details
	JTextField tfId, tfCar, tfRowCell, tfStartTime, tfEndTime, tfParkingMinutes;
    

	//db
	ParkingHistoryDAO phDAO;
	ParkingHistory currentParkingHistory;


	private JTable InitTable(){
		tableModel = new DefaultTableModel(new String[] {
			"ID", "IdCar", "Row cell", "Start time", "End time", "Parking minutes" 
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

		for(ParkingHistory u : phDAO.FindAll()){
			tableModel.addRow(new Object[]{
				u.getId(),
                u.getCar().getLicensePlate(),
                u.getRowCell(),
                u.getStartTime(),
                u.getEndTime(),
                u.getParkingMinutes()
			});
		}
	}
	

	private void DrawDetails(JPanel pnDetailsInfo){
		JLabel title = new JLabel("Lich sử parking");
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

		JLabel lbLicensePlate = new JLabel("Biển số: ");
		lbLicensePlate.setFont(f);
		tfCar = new JTextField();
		tfCar.setFont(fnomal);
		tfCar.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfCar.setPreferredSize(new Dimension(0, height));

        JLabel lbRowCell = new JLabel("Vị trí: ");
		lbRowCell.setFont(f);
		tfRowCell = new JTextField();
		tfRowCell.setFont(fnomal);
		tfRowCell.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfRowCell.setPreferredSize(new Dimension(0, height));
        
        JLabel lbStartTime = new JLabel("Giờ vào bãi: ");
		lbStartTime.setFont(f);
		tfStartTime = new JTextField();
		tfStartTime.setFont(fnomal);
		tfStartTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfStartTime.setPreferredSize(new Dimension(0, height));

        JLabel lbEndTime = new JLabel("Giờ rời bãi: ");
		lbEndTime.setFont(f);
		tfEndTime = new JTextField();
		tfEndTime.setFont(fnomal);
		tfEndTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfEndTime.setPreferredSize(new Dimension(0, height));

        JLabel lbParkingMinutes = new JLabel("Thời gian đỗ: ");
		lbParkingMinutes.setFont(f);
		tfParkingMinutes = new JTextField();
		tfParkingMinutes.setFont(fnomal);
		tfParkingMinutes.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfParkingMinutes.setPreferredSize(new Dimension(0, height));

		pnDetailsInfo.add(title);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(lbId);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(tfId);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(lbLicensePlate);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(tfCar);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbRowCell);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(tfRowCell);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbStartTime);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(tfStartTime);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbEndTime);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(tfEndTime);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbParkingMinutes);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(tfParkingMinutes);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		

	}

	private void onTableSelected(ListSelectionEvent e){
		if (!e.getValueIsAdjusting()) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int modelRow = table.convertRowIndexToModel(selectedRow);
				int id = (int) tableModel.getValueAt(modelRow, 0);
				

				currentParkingHistory = phDAO.FirstOfDefault(id);

				if(currentParkingHistory != null){
					tfId.setText(String.valueOf(currentParkingHistory.getId()));
                    tfCar.setText(String.valueOf(currentParkingHistory.getCar().getLicensePlate()));
					tfRowCell.setText(String.valueOf(currentParkingHistory.getRowCell()));
					tfStartTime.setText(String.valueOf(currentParkingHistory.getStartTime()));
                    tfEndTime.setText(String.valueOf(currentParkingHistory.getEndTime()));
                    tfParkingMinutes.setText(String.valueOf(currentParkingHistory.getParkingMinutes()));
					// if(typeCar != null){
					// 	ComboBoxModel<TypeCar> model = cbTypeCar.getModel();
					// 	for(int i = 0 ;i < model.getSize();i++){
					// 		TypeCar r = model.getElementAt(i);
					// 		if(r.getId() == typeCar.getId()){
					// 			cbTypeCar.setSelectedIndex(i);
					// 			break;
					// 		}
					// 	}
					// }
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
		if(currentParkingHistory == null){
			JOptionPane.showMessageDialog(this, "vui lòng chọn lịch sử cần xóa!", "thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		int result = JOptionPane.showConfirmDialog(this, 
			"bạn có chắc muốn xóa lịch sử : " + currentParkingHistory.getId() +
            "cho biển số " + currentParkingHistory.getCar().getLicensePlate() + " không?",
			"xác nhận xóa",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE
		);
		if(result == JOptionPane.YES_OPTION){
			boolean isDel = phDAO.Delete(currentParkingHistory);
			if(isDel)
				JOptionPane.showMessageDialog(
					this,
					"xóa lịch sử : " + currentParkingHistory.getId() +
                    "cho biển số " + currentParkingHistory.getCar().getLicensePlate() + " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"xóa lịch sử : " + currentParkingHistory.getId() +
                    "cho biên số " + currentParkingHistory.getCar().getLicensePlate() + " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			currentParkingHistory = null;
			LoadDataToTableModel();
		}
	}

	// private void onJBEdit(){
	// 	if(currentPriceList == null){
	// 		JOptionPane.showMessageDialog(this, "vui lòng chọn giá cần sửa!", "thông báo", JOptionPane.WARNING_MESSAGE);
	// 		return;
	// 	}
	// 	TypeCar tc = (TypeCar) cbTypeCar.getSelectedItem();
	// 	if(
	// 		tc != null
	// 	){
	// 		double pricePerHour = 0;
	// 		if(tfPricePerHour.getText().length() > 0 && !tfPricePerHour.getText().isBlank()){
	// 			pricePerHour = Double.parseDouble(tfPricePerHour.getText());
	// 		}

	// 		PriceList newPriceList = new PriceList(
    //             currentPriceList.getId(),
	// 			tc,
    //             pricePerHour
	// 		);

	// 		boolean result = plDAO.Update(newPriceList);
	// 		if(result)
	// 			JOptionPane.showMessageDialog(
	// 				this,
	// 				"Sửa giá " + pricePerHour + " cho loại " + tc.getName() + " thành công",
	// 				"Thông Báo",
	// 				JOptionPane.INFORMATION_MESSAGE
	// 			);
	// 		else
	// 			JOptionPane.showMessageDialog(
	// 				this,
	// 				"Sửa giá " + pricePerHour + " cho loại " + tc.getName() + " thất bại",
	// 				"Thông Báo",
	// 				JOptionPane.ERROR_MESSAGE
	// 			);
	// 		LoadDataToTableModel();
	// 	}
	// }



	public PnParkingHistory(JPanel pnContent, CardLayout cl) {
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
		phDAO = new ParkingHistoryDAO();

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
