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
// import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
// import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import otoparking.utilities.GetImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
// import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import otoparking.DAO.*;
import otoparking.model.*;

public class PnRowCell extends JPanel{
    private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnRowCell(JPanel pnContent, CardLayout cl) {
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

		RowCellDAO DAO = new RowCellDAO();
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {
				"ID",
				"Floor",
                "Row",
                "Cell",
                "Status"
		}, 0);
		for (RowCell r : DAO.FindAll()) {
			tableModel.addRow(new Object[] {
					r.getId(),
					r.getFloor().getSymbol(),
                    r.getParkingRow().getSymbol(),
                    r.getCell().getSymbol(),
                    r.getStatus().getDescription()
			});
		}
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		sorter.setSortKeys(List.of(
				new RowSorter.SortKey(0, SortOrder.ASCENDING)));
		JTable table = new JTable(tableModel);
		table.setRowSorter(sorter);
		table.setFont(f);
		table.setRowHeight(40);
		JScrollPane scrollPane = new JScrollPane(table);

		pnMain.add(scrollPane);

		//// details

		JPanel pnDetail = new JPanel();
		pnDetail.setLayout(new BoxLayout(pnDetail, BoxLayout.Y_AXIS));
		JScrollPane spDetails = new JScrollPane(pnDetail);
		pnMain.add(spDetails, BorderLayout.EAST);
		pnDetail.setPreferredSize(new Dimension(350, 0));

		JLabel title = new JLabel("RowCell");
		title.setFont(new Font("Segoe UI", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JTextField tfId = new JTextField("ID: ");
		tfId.setEditable(false);
		tfId.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfId.getPreferredSize().height));
		tfId.setFont(f);

		JTextField tfFloor = new JTextField("Floor: ");
		tfFloor.setEditable(false);
		tfFloor.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfFloor.getPreferredSize().height));
		tfFloor.setFont(f);

        JTextField tfRow = new JTextField("Row: ");
        tfRow.setEditable(false);
		tfRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfRow.getPreferredSize().height));
		tfRow.setFont(f);

        JTextField tfCell = new JTextField("Cell: ");
        tfCell.setEditable(false);
		tfCell.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfCell.getPreferredSize().height));
		tfCell.setFont(f);

        JTextField tfStatus = new JTextField("Status: ");
        tfStatus.setEditable(false);
		tfStatus.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfStatus.getPreferredSize().height));
		tfStatus.setFont(f);



		pnDetail.add(title);
		pnDetail.add(Box.createVerticalStrut(20));
		pnDetail.add(tfId);
		pnDetail.add(Box.createVerticalStrut(5));
		pnDetail.add(tfFloor);
        pnDetail.add(Box.createVerticalStrut(5));
        pnDetail.add(tfRow);
		pnDetail.add(Box.createVerticalStrut(5));
		pnDetail.add(tfCell);
		pnDetail.add(Box.createVerticalStrut(5));
		pnDetail.add(tfStatus);
        pnDetail.add(Box.createVerticalStrut(5));


		// Events

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int modelRow = table.convertRowIndexToModel(selectedRow);

					tfId.setText("ID: " + table.getValueAt(modelRow, 0));
					tfFloor.setText("Fllor: " + table.getValueAt(modelRow, 1));
                    tfRow.setText("Row: " +table.getValueAt(modelRow, 2));
                    tfCell.setText("Cell: " + table.getValueAt(modelRow, 3));
                    tfStatus.setText("Status: " +table.getValueAt(modelRow, 4));
				}
			}
		});

	}
}
