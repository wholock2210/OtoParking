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

public class PnUser extends JPanel {
	private static final long serialVersionUID = 1L;

	//option
	Font f = new Font("Segoe UI", Font.BOLD, 20);

	//table
	private JTable table;
	private DefaultTableModel tableModel;

	//details
	JTextField tfId, tfName, tfPhone, tfEmail, tfAddess, tfUserName, tfPassword , tfSalary;
	JSpinner spBirth, spStartDate;
	JComboBox<Role> cbRole;

	//db
	UserDAO uDAO;
	AppUser currentUser;


	private JTable InitTable(){
		tableModel = new DefaultTableModel(new String[] {
			"ID", "Tên", "Điện thoại", "Vai trò"
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

		for(AppUser u : uDAO.FindAll()){
			tableModel.addRow(new Object[]{
				u.getId(),
				u.getName(),
				u.getPhone(),
				u.getRole().getName()
			});
		}
	}
	

	private void DrawDetails(JPanel pnDetailsInfo){
		JLabel title = new JLabel("Người dùng");
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

		JLabel lbName = new JLabel("Tên: ");
		lbName.setFont(f);
		tfName = new JTextField();
		tfName.setFont(fnomal);
		tfName.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfName.setPreferredSize(new Dimension(0, height));

		JLabel lbPhone = new JLabel("Điện thoại: ");
		lbPhone.setFont(f);
		tfPhone = new JTextField();
		tfPhone.setFont(fnomal);
		tfPhone.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfPhone.setPreferredSize(new Dimension(0, height));

		JLabel lbEmail = new JLabel("Email: ");
		lbEmail.setFont(f);
		tfEmail = new JTextField();
		tfEmail.setFont(fnomal);
		tfEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfEmail.setPreferredSize(new Dimension(0, height));


        JLabel lbBirth = new JLabel("Ngày sinh: ");
        lbBirth.setFont(f);
		SpinnerDateModel modelBirth = new SpinnerDateModel();
		spBirth = new JSpinner(modelBirth);
		spBirth.setEditor(new JSpinner.DateEditor(spBirth, "dd,MM,yyyy"));
		spBirth.setFont(fnomal);
        spBirth.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spBirth.setPreferredSize(new Dimension(0, height));
		spBirth.setAlignmentX(0);

		JLabel lbAddess = new JLabel("Địa Chỉ: ");
		lbAddess.setFont(f);
		tfAddess = new JTextField();
		tfAddess.setFont(fnomal);
		tfAddess.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfAddess.setPreferredSize(new Dimension(0, height));

		JLabel lbUsername = new JLabel("Tên đăng nhập: ");
		lbUsername.setFont(f);
		tfUserName = new JTextField();
		tfUserName.setFont(fnomal);
		tfUserName.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfUserName.setPreferredSize(new Dimension(0, height));

		JLabel lbPassword = new JLabel("Mật khẩu: ");
		lbPassword.setFont(f);
		tfPassword = new JTextField();
		tfPassword.setFont(fnomal);
		tfPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfPassword.setPreferredSize(new Dimension(0, height));

        JLabel lbStartDate = new JLabel("Ngày tạo: ");
        lbBirth.setFont(f);
		SpinnerDateModel modelStartDate = new SpinnerDateModel();
		spStartDate = new JSpinner(modelStartDate);
		spStartDate.setEditor(new JSpinner.DateEditor(spStartDate, "dd,MM,yyyy"));
		spStartDate.setFont(fnomal);
        spStartDate.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spStartDate.setPreferredSize(new Dimension(0, height));
		spStartDate.setAlignmentX(0);


		JLabel lbSalary = new JLabel("Lương: ");
		lbSalary.setFont(f);
		tfSalary = new JTextField();
		tfSalary.setFont(fnomal);
		tfSalary.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfSalary.setPreferredSize(new Dimension(0, height));


        JLabel lbRole = new JLabel("Vai trò: ");
        lbRole.setFont(f);
		RoleDAO rDao = new RoleDAO();
		DefaultComboBoxModel<Role> modelRole = new DefaultComboBoxModel<>();
		for(Role r : rDao.FindAll()){
			modelRole.addElement(r);
		}
        cbRole = new JComboBox<>(modelRole);

		cbRole.setFont(fnomal);
        cbRole.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        cbRole.setPreferredSize(new Dimension(0, height));
		cbRole.setAlignmentX(0);


		pnDetailsInfo.add(title);
		pnDetailsInfo.add(Box.createVerticalStrut(20));
		pnDetailsInfo.add(lbId);
		pnDetailsInfo.add(tfId);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbName);
		pnDetailsInfo.add(tfName);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbPhone);
		pnDetailsInfo.add(tfPhone);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbEmail);
		pnDetailsInfo.add(tfEmail);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbBirth);
		pnDetailsInfo.add(spBirth);
        pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbAddess);
		pnDetailsInfo.add(tfAddess);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbUsername);
		pnDetailsInfo.add(tfUserName);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbPassword);
		pnDetailsInfo.add(tfPassword);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbStartDate);
		pnDetailsInfo.add(spStartDate);
        pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbSalary);
		pnDetailsInfo.add(tfSalary);
		pnDetailsInfo.add(Box.createVerticalStrut(10));
		pnDetailsInfo.add(lbRole);
		pnDetailsInfo.add(cbRole);

	}

	private void onTableSelected(ListSelectionEvent e){
		if (!e.getValueIsAdjusting()) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int modelRow = table.convertRowIndexToModel(selectedRow);
				int id = (int) tableModel.getValueAt(modelRow, 0);
				

				currentUser = uDAO.FirstOfDefault(id);

				if(currentUser != null){
					tfId.setText(String.valueOf(currentUser.getId()));
					tfName.setText(currentUser.getName());
					tfPhone.setText(currentUser.getPhone());
					tfEmail.setText(currentUser.getEmail());
					Date birth = currentUser.getBrith();
					if(birth != null){
						spBirth.setValue(birth);
					}
					tfAddess.setText(currentUser.getAddress());
					tfUserName.setText(currentUser.getUserName());
					tfPassword.setText(currentUser.getPasswordHash());
					Date startDate = currentUser.getStartDate();
					if(startDate != null){
						spStartDate.setValue(startDate);
					}
					tfSalary.setText(String.valueOf(currentUser.getSalary()));
					Role uRole = currentUser.getRole();
					if(uRole != null){
						ComboBoxModel<Role> model = cbRole.getModel();
						for(int i = 0 ;i < model.getSize();i++){
							Role r = model.getElementAt(i);
							if(r.getId() == uRole.getId()){
								cbRole.setSelectedIndex(i);
								break;
							}
						}
					}
				}
			}
		}
	}

	private void onJBCreate(){
		String name, userName, password;
		name = tfName.getText();
		userName = tfUserName.getText();
		password = tfPassword.getText();
		Role r = (Role) cbRole.getSelectedItem();
		if(
			!name.isBlank()
			&& !userName.isBlank()
			&& !password.isBlank()
			&& r != null
		){
			java.util.Date utilDateBirth = (java.util.Date) spBirth.getValue();
			java.sql.Date sqlDateBirth = new java.sql.Date(utilDateBirth.getTime());
			java.util.Date utilDateStartDate = (java.util.Date) spStartDate.getValue();
			java.sql.Date sqlStartDate = new java.sql.Date(utilDateStartDate.getTime());
			double salary = 0;
			if(tfSalary.getText().length() > 0 && !tfSalary.getText().isBlank()){
				salary = Double.parseDouble(tfSalary.getText());
			}

			AppUser newUser = new AppUser(
				name, 
				tfPhone.getText(), 
				tfEmail.getText(), 
				sqlDateBirth, 
				tfAddess.getText(), 
				userName, 
				password, 
				sqlStartDate, 
				salary,
				r
			);

			boolean result = uDAO.Insert(newUser);
			if(result)
				JOptionPane.showMessageDialog(
					this,
					"Thêm user " + name + " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"Thêm user " + name + " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			LoadDataToTableModel();
		}
	}

	private void onJBDelete(){
		if(currentUser == null){
			JOptionPane.showMessageDialog(this, "vui lòng chọn user cần xóa!", "thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		int result = JOptionPane.showConfirmDialog(this, 
			"bạn có chắc muốn xóa người dùng : " + currentUser.getName() + " không?",
			"xác nhận xóa",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE
		);
		if(result == JOptionPane.YES_OPTION){
			boolean isDel = uDAO.Delete(currentUser);
			if(isDel)
				JOptionPane.showMessageDialog(
					this,
					"xóa user " + currentUser.getName() + " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"xóa user " + currentUser.getName() + " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			currentUser = null;
			LoadDataToTableModel();
		}
	}

	private void onJBEdit(){
		if(currentUser == null){
			JOptionPane.showMessageDialog(this, "vui lòng chọn user cần sửa!", "thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String name, userName, password;
		name = tfName.getText();
		userName = tfUserName.getText();
		password = tfPassword.getText();
		Role r = (Role) cbRole.getSelectedItem();
		if(
			!name.isBlank()
			&& !userName.isBlank()
			&& !password.isBlank()
			&& r != null
		){
			java.util.Date utilDateBirth = (java.util.Date) spBirth.getValue();
			java.sql.Date sqlDateBirth = new java.sql.Date(utilDateBirth.getTime());
			java.util.Date utilDateStartDate = (java.util.Date) spStartDate.getValue();
			java.sql.Date sqlStartDate = new java.sql.Date(utilDateStartDate.getTime());
			double salary = 0;
			if(tfSalary.getText().length() > 0 && !tfSalary.getText().isBlank()){
				salary = Double.parseDouble(tfSalary.getText());
			}

			AppUser newUser = new AppUser(
				currentUser.getId(),
				name, 
				tfPhone.getText(), 
				tfEmail.getText(), 
				sqlDateBirth, 
				tfAddess.getText(), 
				userName, 
				password, 
				sqlStartDate, 
				salary,
				r
			);

			boolean result = uDAO.Update(newUser);
			if(result)
				JOptionPane.showMessageDialog(
					this,
					"Sửa user " + name + " thành công",
					"Thông Báo",
					JOptionPane.INFORMATION_MESSAGE
				);
			else
				JOptionPane.showMessageDialog(
					this,
					"Sửa user " + name + " thất bại",
					"Thông Báo",
					JOptionPane.ERROR_MESSAGE
				);
			LoadDataToTableModel();
		}
	}



	public PnUser(JPanel pnContent, CardLayout cl) {
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
		uDAO = new UserDAO();

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
