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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
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

import otoparking.DAO.*;
import otoparking.model.*;

public class Users extends JPanel {
	private static final long serialVersionUID = 1L;

	public Users(JPanel pnContent, CardLayout cl) {
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

		//// list

		UserDAO DAO = new UserDAO();
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {
				"ID",
				"Tên",
				"Điện thoại",
				"Vai trò"
		}, 0);
		for (AppUser r : DAO.FindAll()) {
			tableModel.addRow(new Object[] {
					r.getId(),
					r.getName(),
					r.getPhone(),
					r.getRole().getName(),
			});
		}
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		sorter.setSortKeys(java.util.List.of(
			new RowSorter.SortKey(0, SortOrder.ASCENDING)
		));

		JTable table = new JTable(tableModel);
		table.setRowSorter(sorter);
		table.setFont(f);
		table.setRowHeight(40);
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

		JLabel title = new JLabel("Người dùng");
		title.setFont(new Font("Segoe UI", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		Font fnomal = new Font("Segoe UI", Font.PLAIN, 16);
		int height = 40;

		JLabel lbId = new JLabel("ID: ");
		lbId.setFont(f);
		JTextField tfId = new JTextField();
		tfId.setFont(fnomal);
		tfId.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfId.setPreferredSize(new Dimension(0, height));

		JLabel lbName = new JLabel("Tên: ");
		lbName.setFont(f);
		JTextField tfName = new JTextField();
		tfName.setFont(fnomal);
		tfName.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfName.setPreferredSize(new Dimension(0, height));

		JLabel lbPhone = new JLabel("Điện thoại: ");
		lbPhone.setFont(f);
		JTextField tfPhone = new JTextField();
		tfPhone.setFont(fnomal);
		tfPhone.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfPhone.setPreferredSize(new Dimension(0, height));

		JLabel lbEmail = new JLabel("Email: ");
		lbEmail.setFont(f);
		JTextField tfEmail = new JTextField();
		tfEmail.setFont(fnomal);
		tfEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfEmail.setPreferredSize(new Dimension(0, height));


        JLabel lbBirth = new JLabel("Ngày sinh: ");
        lbBirth.setFont(f);
		SpinnerDateModel modelBirth = new SpinnerDateModel();
		JSpinner spBirth = new JSpinner(modelBirth);
		spBirth.setEditor(new JSpinner.DateEditor(spBirth, "dd,MM,yyyy"));
		spBirth.setFont(fnomal);
        spBirth.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spBirth.setPreferredSize(new Dimension(0, height));
		spBirth.setAlignmentX(0);

		JLabel lbAddess = new JLabel("Địa Chỉ: ");
		lbAddess.setFont(f);
		JTextField tfAddess = new JTextField();
		tfAddess.setFont(fnomal);
		tfAddess.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfAddess.setPreferredSize(new Dimension(0, height));

		JLabel lbUsername = new JLabel("Tên đăng nhập: ");
		lbUsername.setFont(f);
		JTextField tfUserName = new JTextField();
		tfUserName.setFont(fnomal);
		tfUserName.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfUserName.setPreferredSize(new Dimension(0, height));

		JLabel lbPassword = new JLabel("Mật khẩu: ");
		lbPassword.setFont(f);
		JTextField tfPassword = new JTextField();
		tfPassword.setFont(fnomal);
		tfPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		tfPassword.setPreferredSize(new Dimension(0, height));

        JLabel lbStartDate = new JLabel("Ngày tạo: ");
        lbBirth.setFont(f);
		SpinnerDateModel modelStartDate = new SpinnerDateModel();
		JSpinner spStartDate = new JSpinner(modelStartDate);
		spStartDate.setEditor(new JSpinner.DateEditor(spStartDate, "dd,MM,yyyy"));
		spStartDate.setFont(fnomal);
        spStartDate.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        spStartDate.setPreferredSize(new Dimension(0, height));
		spStartDate.setAlignmentX(0);


		JLabel lbSalary = new JLabel("Lương: ");
		lbSalary.setFont(f);
		JTextField tfSalary = new JTextField();
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
        JComboBox<Role> cbRole = new JComboBox<>(modelRole);

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

		// Events

		UserDAO uDAO = new UserDAO();

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int modelRow = table.convertRowIndexToModel(selectedRow);
					int idUser = (int) table.getValueAt(modelRow, 0);

					AppUser currentUser = uDAO.FirstOfDefault(idUser);

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
					}else{
						tfName.setText("null Id : " + idUser);
					}
					
					
				}
			}
		});
	}
}
