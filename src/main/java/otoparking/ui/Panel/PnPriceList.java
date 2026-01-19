package otoparking.ui.Panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SpinnerDateModel;
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
import java.sql.Date;
import java.util.List;

import otoparking.DAO.*;
import otoparking.model.*;

public class PnPriceList extends JPanel {
	private static final long serialVersionUID = 1L;

	//option
	Font f = new Font("Segoe UI", Font.BOLD, 20);

	//table
	private JTable table;
	private DefaultTableModel tableModel;

	//details
	JTextField tfId, tfPricePerHour;
	JComboBox<TypeCar> cbTypeCar;

	//db
	PriceListDAO plDAO;
	PriceList currentPriceList;


	private JTable InitTable(){
		tableModel = new DefaultTableModel(new String[] {
			"ID", "Tên", "giá", 
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

		for(PriceList u : plDAO.FindAll()){
			tableModel.addRow(new Object[]{
				u.getId(),
                u.getTypeCar().getName(),
                u.getPricePerHour()
			});
		}
	}
	

	private void DrawDetails(JPanel pnDetailsInfo){
		JLabel title = new JLabel("Giá đỗ xe");
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

		JLabel lbPricePerHour = new JLabel("Giá đỗ xe: ");
		lbPricePerHour.setFont(f);
		tfPricePerHour = new JTextField();
		tfPricePerHour.setFont(fnomal);
		tfPricePerHour.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfPricePerHour.setPreferredSize(new Dimension(0, height));


        JLabel lbTypeCar = new JLabel("Loại xe: ");
        lbTypeCar.setFont(f);
		TypeCarDAO tcDao = new TypeCarDAO();
		DefaultComboBoxModel<TypeCar> modelRole = new DefaultComboBoxModel<>();
		for(TypeCar r : tcDao.FindAll()){
			modelRole.addElement(r);
		}
        cbTypeCar = new JComboBox<>(modelRole);

		cbTypeCar.setFont(fnomal);
        cbTypeCar.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        cbTypeCar.setPreferredSize(new Dimension(0, height));
		cbTypeCar.setAlignmentX(0);


		pnDetailsInfo.add(title);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(lbId);
		pnDetailsInfo.add(tfId);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbTypeCar);
		pnDetailsInfo.add(cbTypeCar);
        pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbPricePerHour);
		pnDetailsInfo.add(tfPricePerHour);

	}

	private void onTableSelected(ListSelectionEvent e){
		if (!e.getValueIsAdjusting()) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int modelRow = table.convertRowIndexToModel(selectedRow);
				int id = (int) tableModel.getValueAt(modelRow, 0);
				

				currentPriceList = plDAO.FirstOfDefault(id);

				if(currentPriceList != null){
					tfId.setText(String.valueOf(currentPriceList.getId()));
					tfPricePerHour.setText(String.valueOf(currentPriceList.getPricePerHour()));
					TypeCar typeCar = currentPriceList.getTypeCar();
					if(typeCar != null){
						ComboBoxModel<TypeCar> model = cbTypeCar.getModel();
						for(int i = 0 ;i < model.getSize();i++){
							TypeCar r = model.getElementAt(i);
							if(r.getId() == typeCar.getId()){
								cbTypeCar.setSelectedIndex(i);
								break;
							}
						}
					}
				}
			}
		}
	}

	private void onJBCreate(){
		TypeCar tc = (TypeCar) cbTypeCar.getSelectedItem();
		if(
			tc != null
		){
			double pricePerHour = 0;
			if(tfPricePerHour.getText().length() > 0 && !tfPricePerHour.getText().isBlank()){
				pricePerHour = Double.parseDouble(tfPricePerHour.getText());
			}

			PriceList newPriceList = new PriceList(
				tc,
                pricePerHour
			);

			boolean result = plDAO.Insert(newPriceList);
			if(result)
				JOptionPane.showMessageDialog(
					this,
					"Thêm giá " + pricePerHour + " cho loại " + tc.getName() + " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"Thêm giá " + pricePerHour + " cho loại " + tc.getName() + " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			LoadDataToTableModel();
		}
	}

	private void onJBDelete(){
		if(currentPriceList == null){
			JOptionPane.showMessageDialog(this, "vui lòng chọn giá cần xóa!", "thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		int result = JOptionPane.showConfirmDialog(this, 
			"bạn có chắc muốn xóa giá : " + currentPriceList.getPricePerHour() +
            "cho loại " + currentPriceList.getTypeCar().getName() + " không?",
			"xác nhận xóa",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE
		);
		if(result == JOptionPane.YES_OPTION){
			boolean isDel = plDAO.Delete(currentPriceList);
			if(isDel)
				JOptionPane.showMessageDialog(
					this,
					"xóa giá : " + currentPriceList.getPricePerHour() +
                    "cho loại " + currentPriceList.getTypeCar().getName() + " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"xóa giá : " + currentPriceList.getPricePerHour() +
                    "cho loại " + currentPriceList.getTypeCar().getName() + " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			currentPriceList = null;
			LoadDataToTableModel();
		}
	}

	private void onJBEdit(){
		if(currentPriceList == null){
			JOptionPane.showMessageDialog(this, "vui lòng chọn giá cần sửa!", "thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		TypeCar tc = (TypeCar) cbTypeCar.getSelectedItem();
		if(
			tc != null
		){
			double pricePerHour = 0;
			if(tfPricePerHour.getText().length() > 0 && !tfPricePerHour.getText().isBlank()){
				pricePerHour = Double.parseDouble(tfPricePerHour.getText());
			}

			PriceList newPriceList = new PriceList(
                currentPriceList.getId(),
				tc,
                pricePerHour
			);

			boolean result = plDAO.Update(newPriceList);
			if(result)
				JOptionPane.showMessageDialog(
					this,
					"Sửa giá " + pricePerHour + " cho loại " + tc.getName() + " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"Sửa giá " + pricePerHour + " cho loại " + tc.getName() + " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			LoadDataToTableModel();
		}
	}



	public PnPriceList(JPanel pnContent, CardLayout cl) {
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
		plDAO = new PriceListDAO();

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

		JButton jbCreate = new JButton("Thêm");
		jbCreate.setFont(f);
		JButton jbDelete = new JButton("Xóa");
		jbDelete.setFont(f);
		JButton jbEdit = new JButton("Sửa");
		jbEdit.setFont(f);

		pnDetailsFunction.add(jbCreate);
		pnDetailsFunction.add(jbDelete);
		pnDetailsFunction.add(jbEdit);

		//// details info
		
		DrawDetails(pnDetailsInfo);
		// Events


		table.getSelectionModel().addListSelectionListener(e -> {
			onTableSelected(e);
		});

		jbCreate.addActionListener(e -> {
			onJBCreate();
		});

		jbDelete.addActionListener(e -> {
			onJBDelete();
		});

		jbEdit.addActionListener(e ->{
			onJBEdit();
		});
		
	}
}
