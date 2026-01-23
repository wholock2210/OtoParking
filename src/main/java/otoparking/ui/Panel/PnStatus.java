package otoparking.ui.Panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import otoparking.utilities.GetImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import otoparking.DAO.*;
import otoparking.model.Cell;
import otoparking.model.Status;

public class PnStatus extends JPanel {
    private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnStatus(JPanel pnContent, CardLayout cl) {
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

		StatusDAO DAO = new StatusDAO();
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {
				"ID",
				"Name",
                "Description"
		}, 0);
		for (Status r : DAO.FindAll()) {
			tableModel.addRow(new Object[] {
					r.getId(),
					r.getName(),
                    r.getDescription()
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

		JLabel title = new JLabel("Symbol");
		title.setFont(new Font("Segoe UI", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JTextField tfId = new JTextField("ID: ");
		tfId.setEditable(false);
		tfId.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfId.getPreferredSize().height));
		tfId.setFont(f);
		JTextField tfName = new JTextField("Name: ");
		tfName.setEditable(false);
		tfName.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfName.getPreferredSize().height));
		tfName.setFont(f);
        JTextField tfDes = new JTextField("Description: ");
		tfDes.setEditable(false);
		tfDes.setMaximumSize(new Dimension(Integer.MAX_VALUE, tfName.getPreferredSize().height));
		tfDes.setFont(f);


		pnDetail.add(title);
		pnDetail.add(Box.createVerticalStrut(20));
		pnDetail.add(tfId);
		pnDetail.add(Box.createVerticalStrut(5));
		pnDetail.add(tfName);
        pnDetail.add(Box.createVerticalStrut(5));
        pnDetail.add(tfDes);

		// Events

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int modelRow = table.convertRowIndexToModel(selectedRow);

					tfId.setText("ID: " + table.getValueAt(modelRow, 0));
					tfName.setText("Symbol: " + table.getValueAt(modelRow, 1));
                    tfDes.setText("Description: "+ table.getValueAt(modelRow, 2));
				}
			}
		});

	}
}
