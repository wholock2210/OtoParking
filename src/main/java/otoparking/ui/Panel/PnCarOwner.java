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

public class PnCarOwner extends JPanel {
    private static final long serialVersionUID = 1L;

    // option
    Font f = new Font("Segoe UI", Font.BOLD, 20);

    // table
    private JTable table;
    private DefaultTableModel tableModel;

    // details
    JTextField tfIdUser, tfIdCar;

    // db
    CarOwnerDAO carOwnerDAO;
    CarOwner currentCarOwner;

    private JTable InitTable() {
        tableModel = new DefaultTableModel(new String[] {
                "Người sở hữu", "Xe"
        }, 0);

        LoadDataToTableModel();

        JTable table = new JTable(tableModel);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setSortKeys(List.of(
                new RowSorter.SortKey(0, SortOrder.ASCENDING)));
        table.setRowSorter(sorter);

        table.setFont(f);
        table.setRowHeight(40);

        return table;
    }

    private void LoadDataToTableModel() {
        tableModel.setRowCount(0);

        for (CarOwner co : carOwnerDAO.FindAll()) {
            tableModel.addRow(new Object[] {
                    co.getAppUser().getName(),
                    co.getCar().getLicensePlate()
            });
        }
    }

    private void DrawDetails(JPanel pnDetailsInfo) {
        JLabel title = new JLabel("Chủ sở hữu xe");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        Font fnomal = new Font("Segoe UI", Font.PLAIN, 16);
        int height = 40;

        JLabel lbIdUser = new JLabel("Người dùng: ");
        lbIdUser.setFont(f);
        tfIdUser = new JTextField();
        tfIdUser.setFont(fnomal);
        tfIdUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfIdUser.setPreferredSize(new Dimension(0, height));

        JLabel lbIdCar = new JLabel("Biển số: ");
        lbIdCar.setFont(f);
        tfIdCar = new JTextField();
        tfIdCar.setFont(fnomal);
        tfIdCar.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        tfIdCar.setPreferredSize(new Dimension(0, height));

        pnDetailsInfo.add(title);
        pnDetailsInfo.add(Box.createVerticalStrut(20));
        pnDetailsInfo.add(lbIdUser);
        pnDetailsInfo.add(tfIdUser);
        pnDetailsInfo.add(Box.createVerticalStrut(10));
        pnDetailsInfo.add(lbIdCar);
        pnDetailsInfo.add(tfIdCar);
        pnDetailsInfo.add(Box.createVerticalStrut(10));
    }

    private void onTableSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = table.convertRowIndexToModel(selectedRow);
                int id = (int) tableModel.getValueAt(modelRow, 0);

                currentCarOwner = carOwnerDAO.FirstOfDefault(id);

                if (currentCarOwner != null) {
                    tfIdUser.setText(String.valueOf(currentCarOwner.getAppUser().getName()));
                    tfIdCar.setText(currentCarOwner.getCar().getLicensePlate());
                }
            }
        }
    }

    private void onJBCreate() {
        int idUser = Integer.parseInt(tfIdUser.getText());
        int idCar = Integer.parseInt(tfIdCar.getText());
        if (!String.valueOf(idCar).isBlank()
                && !String.valueOf(idUser).isBlank()) {

            CarOwner newCarOwner = new CarOwner(
                    new UserDAO().FirstOfDefault(idUser),
                    new CarDAO().FirstOfDefault(idCar));

            boolean result = carOwnerDAO.Insert(newCarOwner);
            if (result)
                JOptionPane.showMessageDialog(
                        this,
                        "Thêm xe " + idCar + " thành công",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(
                        this,
                        "Thêm xe " + idCar + " thất bại",
                        "Thông Báo",
                        JOptionPane.ERROR_MESSAGE);
            LoadDataToTableModel();
        }
    }

    private void onJBDelete() {
        if (currentCarOwner == null) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn xe cần xóa!", "thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "bạn có chắc muốn xóa người dùng : " + currentCarOwner.getCar().getLicensePlate() + " không?",
                "xác nhận xóa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            boolean isDel = carOwnerDAO.Delete(currentCarOwner);
            if (isDel)
                JOptionPane.showMessageDialog(
                        this,
                        "xóa xe " + currentCarOwner.getCar().getLicensePlate() + " thành công",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(
                        this,
                        "xóa xe " + currentCarOwner.getCar().getLicensePlate() + " thất bại",
                        "Thông Báo",
                        JOptionPane.ERROR_MESSAGE);
            currentCarOwner = null;
            LoadDataToTableModel();
        }
    }

    private void onJBEdit() {
        if (currentCarOwner == null) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn xe cần sửa!", "thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int idUser = Integer.parseInt(tfIdUser.getText());
        int idCar = Integer.parseInt(tfIdCar.getText());
        if (!String.valueOf(idCar).isBlank()
                && !String.valueOf(idUser).isBlank()) {

            CarOwner newCarOwner = new CarOwner(
                    new UserDAO().FirstOfDefault(idUser),
                    new CarDAO().FirstOfDefault(idCar));

            boolean result = carOwnerDAO.Update(newCarOwner);
            if (result)
                JOptionPane.showMessageDialog(
                        this,
                        "Sửa xe " + idCar + " thành công",
                        "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(
                        this,
                        "Sửa xe " + idCar + " thất bại",
                        "Thông Báo",
                        JOptionPane.ERROR_MESSAGE);
            LoadDataToTableModel();
        }
    }

    public PnCarOwner(JPanel pnContent, CardLayout cl) {
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
        carOwnerDAO = new CarOwnerDAO();

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

        jbEdit.addActionListener(e -> {
            onJBEdit();
        });

    }
}
