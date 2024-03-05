/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import repository.DoanhThuRepository;

/**
 *
 * @author DELL
 */
public class jplThongKe extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private DoanhThuRepository doanhThuRepository = new DoanhThuRepository();
    DefaultComboBoxModel<Integer> cbbModelNam = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Integer> cbbModelThang = new DefaultComboBoxModel<>();

    //Chien
    DefaultTableModel dtmSP = new DefaultTableModel();
    DoanhThuRepository dtr = new DoanhThuRepository();
    DefaultComboBoxModel<Integer> cbbModelNamChien = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Integer> cbbModelThangChien = new DefaultComboBoxModel<>();

    public jplThongKe() {
        initComponents();
        fillDoanhThu();
        fillCbbNam();
        fillTableDoanhThu();
        fillCbbNamLoc();
        fillCbbThangLoc();
        cbbLocThang.setEnabled(false);
        cbbLocNam.setEnabled(false);
        addChartYears();
        //codechie
          loadSPBan();
        fillCbbNamLocChien();
        fillCbbThangLocChien();
        loadTopDanhMuc();
        loadTopSanPham();
    }

    public void fillDoanhThu() {
        double dtTheoNgay = doanhThuRepository.getDoanhThuTheoNgayHienTai();
        double dtTheoThang = doanhThuRepository.getDoanhThuTheoThangHienTai();
        double dtTheoNam = doanhThuRepository.getDoanhThuTheoNamHienTai();
        double donHuyTheoNgay = doanhThuRepository.getDonHuyConTheoNgay();
        double donHuyTheoThang = doanhThuRepository.getDonHuyTheoThang();
        double donHuyTheoNam = doanhThuRepository.getDonHuyConTheoNam();
        double donThanhCongTheoNgay = doanhThuRepository.getDonThanhCongTheoNgay();
        double donThanhCongTheoThang = doanhThuRepository.getDonThanhCongTheoThang();
        double donThanhCongTheoNam = doanhThuRepository.getDonThanhCongTheoNam();

        lblTienTheoNgay.setText(String.valueOf(dtTheoNgay));
        lblTienTheoThang.setText(String.valueOf(dtTheoThang));
        lblTienTheoNam.setText(String.valueOf(dtTheoNam));
        lblDonBiHuyTheoNgay.setText(String.valueOf(donHuyTheoNgay));
        lblDonBiHuyTheoThang.setText(String.valueOf(donHuyTheoThang));
        lblDonBiHuyTheoNam.setText(String.valueOf(donHuyTheoNam));
        lblDonThanhCongTheoNgay.setText(String.valueOf(donThanhCongTheoNgay));
        lblDonThanhCongTheoThang.setText(String.valueOf(donThanhCongTheoThang));
        lblDonThanhCongTheoNam.setText(String.valueOf(donThanhCongTheoNam));

    }

    public void clearDoanhThu() {
        lblDonBiHuyTheoNam.setText("-");
        lblDonBiHuyTheoNgay.setText("-");
        lblDonBiHuyTheoThang.setText("-");
        lblDonThanhCongTheoNam.setText("-");
        lblDonThanhCongTheoNgay.setText("-");
        lblDonThanhCongTheoThang.setText("-");
        lblTienTheoNam.setText("-");
        lblTienTheoNgay.setText("-");
        lblTienTheoThang.setText("-");

    }

   private void addChartYears() {

        try {

            pnBieuDo.removeAll();
            ChartPanel chartPanel = new ChartPanel(doanhThuRepository.ChartNam());
            chartPanel.setMouseZoomable(false);
            pnBieuDo.setLayout(new CardLayout());
            pnBieuDo.setPreferredSize(new Dimension(pnBieuDo.getWidth(), pnBieuDo.getHeight()));
            pnBieuDo.add(chartPanel);
            pnBieuDo.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillDoanhThuChon() {
        if (cbbLocNam.isEnabled() && cbbLocThang.isEnabled()) {
            clearDoanhThu();
            int nam = Integer.parseInt(cbbLocNam.getSelectedItem().toString());
            int thang = Integer.parseInt(cbbLocThang.getSelectedItem().toString());

            ArrayList<Object[]> lstDtNam = doanhThuRepository.getDoanhThuTheoNamChon(nam);
            for (Object[] n : lstDtNam) {
                lblTienTheoNam.setText(n[0].toString());
                lblDonThanhCongTheoNam.setText(n[1].toString());
                lblDonBiHuyTheoNam.setText(n[2].toString());
            }
            ArrayList<Object[]> lstDtThang = doanhThuRepository.getDoanhThuTheoThangChon(thang, nam);
            for (Object[] month : lstDtThang) {
                lblTienTheoThang.setText(month[0].toString());
                lblDonThanhCongTheoThang.setText(month[1].toString());
                lblDonBiHuyTheoThang.setText(month[2].toString());
            }
        } else {
        }
    }

    public void fillTableDoanhThu() {

        if (cbbNam.getSelectedItem() != null) {
            int nam = Integer.parseInt(cbbNam.getSelectedItem().toString());
            ArrayList<Object[]> lstDoanhThu = doanhThuRepository.getChiTietDoanhThu(nam);
            DefaultTableModel tblModel = (DefaultTableModel) tblDoanhThu.getModel();
            tblModel.setRowCount(0);
            tblModel.setColumnCount(0);
            tblModel.addColumn("Tháng");
            tblModel.addColumn("Tổng số lượng sản phẩm");
            tblModel.addColumn("Tổng tiền");
            tblModel.addColumn("Tổng giá trị giảm");
            for (Object[] object : lstDoanhThu) {
                String thang = object[0].toString();
                String soLuong = object[1].toString();
                String tongTien = object[2].toString() + " VND"; // Thêm "VND" vào giá trị số
                String tongGiam = object[3].toString() + " VND"; // Thêm "VND" vào giá trị số
                tblModel.addRow(new Object[]{thang, soLuong, tongTien, tongGiam});
            }
            tblDoanhThu.setModel(tblModel);
        }
    }

    public void fillCbbNam() {
        cbbNam.removeAllItems();
        ArrayList<Integer> lstNam = doanhThuRepository.getNam();
        for (Integer integer : lstNam) {
            cbbModelNam.addElement(integer);
        }
        cbbNam.setModel((DefaultComboBoxModel) cbbModelNam);
        cbbNam.setSelectedIndex(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblDoanhThuDM = new javax.swing.JLabel();
        lblDanhMuc = new javax.swing.JLabel();
        lblDoanhThuChien = new javax.swing.JLabel();
        lblSoLuongDM = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblSoLuongSP = new javax.swing.JLabel();
        lblDoanhThuSP = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        cbbThangChien = new javax.swing.JComboBox<>();
        cbbNamChien = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSPBan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblTienTheoNgay = new javax.swing.JLabel();
        lblDonBiHuyTheoNgay = new javax.swing.JLabel();
        lblDonThanhCongTheoNgay = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblTienTheoThang = new javax.swing.JLabel();
        lblDonBiHuyTheoThang = new javax.swing.JLabel();
        lblDonThanhCongTheoThang = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblTienTheoNam = new javax.swing.JLabel();
        lblDonBiHuyTheoNam = new javax.swing.JLabel();
        lblDonThanhCongTheoNam = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblThoiGian = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cbbThoiGian = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        cbbLocThang = new javax.swing.JComboBox<>();
        cbbLocNam = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        pnBieuDo = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        cbbNam = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jLabel16.setText("jLabel16");

        jLabel35.setText("jLabel35");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel13.setBackground(new java.awt.Color(204, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Red star.png"))); // NOI18N
        jLabel4.setText("DANH MỤC ĐƯỢC CHỌN NHIỀU NHẤT");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên Danh Mục :");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Số lượng");

        lblDoanhThuDM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoanhThuDM.setForeground(new java.awt.Color(255, 0, 0));

        lblDanhMuc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDanhMuc.setForeground(new java.awt.Color(255, 0, 0));

        lblDoanhThuChien.setBackground(new java.awt.Color(204, 204, 255));

        lblSoLuongDM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSoLuongDM.setForeground(new java.awt.Color(255, 0, 0));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Doanh Thu :");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)
                        .addGap(0, 126, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSoLuongDM, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDoanhThuDM, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(97, 97, 97)
                    .addComponent(lblDoanhThuChien, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(154, Short.MAX_VALUE)))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(jLabel39)
                    .addContainerGap(427, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addComponent(lblDoanhThuDM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoLuongDM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(150, 150, 150)
                    .addComponent(lblDoanhThuChien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE)))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(122, 122, 122)
                    .addComponent(jLabel39)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        jPanel15.setBackground(new java.awt.Color(153, 255, 204));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Red star.png"))); // NOI18N
        jLabel12.setText("SẢN PHẨM BÁN CHẠY NHẤT");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Tên Sản Phẩm :");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Số Lượng Sản Phẩm bán được:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Doanh Thu :");

        lblTenSP.setBackground(new java.awt.Color(204, 204, 255));
        lblTenSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTenSP.setForeground(new java.awt.Color(255, 0, 0));

        lblSoLuongSP.setBackground(new java.awt.Color(204, 204, 255));
        lblSoLuongSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSoLuongSP.setForeground(new java.awt.Color(255, 51, 0));

        lblDoanhThuSP.setBackground(new java.awt.Color(204, 204, 255));
        lblDoanhThuSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoanhThuSP.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDoanhThuSP, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDoanhThuSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 204, 204));
        jPanel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setText("Tháng");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setText("Năm");

        cbbThangChien.setBackground(new java.awt.Color(255, 255, 51));
        cbbThangChien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThangChienItemStateChanged(evt);
            }
        });

        cbbNamChien.setBackground(new java.awt.Color(255, 255, 102));
        cbbNamChien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamChienItemStateChanged(evt);
            }
        });

        jButton1.setText("LỌC");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(cbbThangChien, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbNamChien, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton1)
                .addContainerGap(836, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(cbbThangChien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNamChien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(23, 23, 23))
        );

        jPanel20.setBackground(new java.awt.Color(255, 51, 204));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(241, 128, 128));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblSPBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Size", "Màu sắc", "Danh mục", "Chất liệu", "Đã bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblSPBan);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addGap(183, 183, 183))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel3);

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setForeground(new java.awt.Color(51, 153, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Doanh thu ngày");

        jLabel23.setText("Tiền:");

        jLabel26.setText("Thành công:");

        jLabel27.setText("Bị hủy:");

        lblTienTheoNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTienTheoNgay.setForeground(new java.awt.Color(255, 0, 51));
        lblTienTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTienTheoNgay.setText("-");
        lblTienTheoNgay.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDonBiHuyTheoNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDonBiHuyTheoNgay.setForeground(new java.awt.Color(255, 0, 51));
        lblDonBiHuyTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonBiHuyTheoNgay.setText("-");
        lblDonBiHuyTheoNgay.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDonThanhCongTheoNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDonThanhCongTheoNgay.setForeground(new java.awt.Color(255, 0, 51));
        lblDonThanhCongTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonThanhCongTheoNgay.setText("-");
        lblDonThanhCongTheoNgay.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel13))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonThanhCongTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDonBiHuyTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTienTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblTienTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblDonThanhCongTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(lblDonBiHuyTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 204, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("Doanh thu tháng");

        jLabel17.setText("Tiền:");

        jLabel18.setText("Thành công:");

        jLabel19.setText("Bị hủy:");

        lblTienTheoThang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTienTheoThang.setForeground(new java.awt.Color(255, 0, 51));
        lblTienTheoThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTienTheoThang.setText("-");
        lblTienTheoThang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDonBiHuyTheoThang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDonBiHuyTheoThang.setForeground(new java.awt.Color(255, 0, 51));
        lblDonBiHuyTheoThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonBiHuyTheoThang.setText("-");
        lblDonBiHuyTheoThang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDonThanhCongTheoThang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDonThanhCongTheoThang.setForeground(new java.awt.Color(255, 0, 51));
        lblDonThanhCongTheoThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonThanhCongTheoThang.setText("-");
        lblDonThanhCongTheoThang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDonThanhCongTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDonBiHuyTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(84, 84, 84))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonThanhCongTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonBiHuyTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setText("Doanh thu năm");

        jLabel20.setText("TIền:");

        jLabel21.setText("Thành công:");

        jLabel22.setText("Bị hủy:");

        lblTienTheoNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTienTheoNam.setForeground(new java.awt.Color(255, 0, 51));
        lblTienTheoNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTienTheoNam.setText("-");
        lblTienTheoNam.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDonBiHuyTheoNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDonBiHuyTheoNam.setForeground(new java.awt.Color(255, 0, 51));
        lblDonBiHuyTheoNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonBiHuyTheoNam.setText("-");
        lblDonBiHuyTheoNam.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDonThanhCongTheoNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDonThanhCongTheoNam.setForeground(new java.awt.Color(255, 0, 51));
        lblDonThanhCongTheoNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonThanhCongTheoNam.setText("-");
        lblDonThanhCongTheoNam.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(77, 77, 77))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel20)
                .addGap(58, 58, 58)
                .addComponent(lblTienTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel22)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblDonBiHuyTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblDonThanhCongTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonThanhCongTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDonBiHuyTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(21, 21, 21))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblThoiGian.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblThoiGian.setText("Lọc theo");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel37.setText("Tháng");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel38.setText("Năm");

        cbbThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Theo tháng trong năm" }));
        cbbThoiGian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThoiGianItemStateChanged(evt);
            }
        });

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        cbbLocThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbLocNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblThoiGian)
                .addGap(18, 18, 18)
                .addComponent(cbbThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel37)
                .addGap(32, 32, 32)
                .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel38)
                .addGap(39, 39, 39)
                .addComponent(cbbLocNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(btnLoc)
                .addContainerGap(334, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbLocNam, cbbLocThang, cbbThoiGian});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLoc)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(cbbLocNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThoiGian)
                        .addComponent(jLabel37)
                        .addComponent(cbbThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbLocNam, cbbLocThang, cbbThoiGian});

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tháng", "Tổng sản phẩm đã bán", "Doanh thu", "Tổng giá giảm"
            }
        ));
        jScrollPane2.setViewportView(tblDoanhThu);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bảng", jPanel12);

        javax.swing.GroupLayout pnBieuDoLayout = new javax.swing.GroupLayout(pnBieuDo);
        pnBieuDo.setLayout(pnBieuDoLayout);
        pnBieuDoLayout.setHorizontalGroup(
            pnBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1030, Short.MAX_VALUE)
        );
        pnBieuDoLayout.setVerticalGroup(
            pnBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Biểu đồ", pnBieuDo);

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel36.setText("Năm");

        cbbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(74, 74, 74)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(37, 37, 37)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(42, 42, 42))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Doanh thu", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Thống kê");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamItemStateChanged
        // TODO add your handling code here:
        fillTableDoanhThu();
    }//GEN-LAST:event_cbbNamItemStateChanged

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        fillDoanhThuChon();
    }//GEN-LAST:event_btnLocActionPerformed

    private void cbbThoiGianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThoiGianItemStateChanged
        // TODO add your handling code here:
        int selectedIndex = cbbThoiGian.getSelectedIndex();
        switch (selectedIndex) {
            case 0:
                fillDoanhThu();
                cbbLocThang.setEnabled(false);
                cbbLocNam.setEnabled(false);
                break;
            case 1:
                cbbLocThang.setEnabled(true);
                cbbLocNam.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_cbbThoiGianItemStateChanged

    private void cbbThangChienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThangChienItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThangChienItemStateChanged

    private void cbbNamChienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamChienItemStateChanged
        if (cbbNamChien.isEnabled() && cbbThangChien.isEnabled()) {
            clearDoanhThu();
            int nam = Integer.parseInt(cbbNamChien.getSelectedItem().toString());

            ArrayList<Object[]> lstDtNam = dtr.getDoanhThuSPTheoNamChonChien(nam);
            ArrayList<Object[]> lstSPNam = dtr.getTOP1DanhMucTheoNamChien(nam);
            for (Object[] dt : lstDtNam) {
                lblTenSP.setText(dt[0].toString());
                lblSoLuongSP.setText(dt[2].toString());
                lblDoanhThuSP.setText(dt[1].toString());
            }
            for (Object[] li : lstSPNam) {
                lblDanhMuc.setText(li[0].toString());
                lblSoLuongDM.setText(li[1].toString());
                lblDoanhThuDM.setText(li[2].toString());

            }
        }
    }//GEN-LAST:event_cbbNamChienItemStateChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        //        fillDoanhThuDMChon();
        //        fillDoanhThuDMChon();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: if (cbbNamChien.isEnabled() && cbbThang.isEnabled()) {
        clearDoanhThuChien();
        int nam = Integer.parseInt(cbbNamChien.getSelectedItem().toString());

        ArrayList<Object[]> lstDtNam = dtr.getDoanhThuSPTheoNamChonChien(nam);
        ArrayList<Object[]> lstSPNam = dtr.getTOP1DanhMucTheoNamChien(nam);
        for (Object[] dt : lstDtNam) {
            lblTenSP.setText(dt[0].toString());
            lblSoLuongSP.setText(dt[2].toString());
            lblDoanhThuSP.setText(dt[1].toString());
        }
        for (Object[] li : lstSPNam) {
            lblDanhMuc.setText(li[0].toString());
            lblSoLuongDM.setText(li[1].toString());
            lblDoanhThuDM.setText(li[2].toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    public void fillCbbThangLoc() {
        ArrayList<Integer> lstThang = doanhThuRepository.getThang();
        DefaultComboBoxModel<Integer> cbmdThang = new DefaultComboBoxModel<>();
        cbmdThang.removeAllElements();
        for (Integer integer : lstThang) {
            cbmdThang.addElement(integer);
        }
        cbbLocThang.setModel((DefaultComboBoxModel) cbmdThang);
    }

    public void fillCbbNamLoc() {
        ArrayList<Integer> lstNam = doanhThuRepository.getNam();
        DefaultComboBoxModel<Integer> cbndNam = new DefaultComboBoxModel<>();
        cbndNam.removeAllElements();
        for (Integer integer : lstNam) {
            cbndNam.addElement(integer);
        }
        cbbLocNam.setModel((DefaultComboBoxModel) cbndNam);
    }

    public void enitableTextfile() {
    }

    //chien
    public void fillCbbNamChien() {
        cbbNamChien.removeAllItems();
        ArrayList<Integer> lstNam = dtr.getNam();
        for (Integer integer : lstNam) {
            cbbModelNam.addElement(integer);
        }
        cbbNamChien.setModel((DefaultComboBoxModel) cbbModelNam);
        cbbNamChien.setSelectedIndex(0);

    }

    public void fillCbbThangLocChien() {
        ArrayList<Integer> lstThang = dtr.getThang();
        DefaultComboBoxModel<Integer> cbmdThang = new DefaultComboBoxModel<>();
        cbmdThang.removeAllElements();
        for (Integer integer : lstThang) {
            cbmdThang.addElement(integer);
        }
        cbbThangChien.setModel((DefaultComboBoxModel) cbmdThang);
    }

    public void fillCbbNamLocChien() {
        ArrayList<Integer> lstNam = dtr.getNam();
        DefaultComboBoxModel<Integer> cbndNam = new DefaultComboBoxModel<>();
        cbndNam.removeAllElements();
        for (Integer integer : lstNam) {
            cbndNam.addElement(integer);
        }
        cbbNamChien.setModel((DefaultComboBoxModel) cbndNam);
    }

    public void fillDoanhThuSPChon() {
        if (cbbNamChien.isEnabled() && cbbThangChien.isEnabled()) {
            int nam = Integer.parseInt(cbbNamChien.getSelectedItem().toString());
            int thang = Integer.parseInt(cbbThangChien.getSelectedItem().toString());

            ArrayList<Object[]> lstDtThang = dtr.getDoanhThuTheoThangChonChien(thang, nam);
            for (Object[] month : lstDtThang) {
                lblTenSP.setText(month[0].toString());
                lblSoLuongSP.setText(month[1].toString());
                lblDoanhThuSP.setText(month[2].toString());
            }

        } else {
        }
    }

    public void fillDoanhThuDMChon() {
        if (cbbNamChien.isEnabled() && cbbThangChien.isEnabled()) {
            int nam = Integer.parseInt(cbbNamChien.getSelectedItem().toString());
            int thang = Integer.parseInt(cbbThangChien.getSelectedItem().toString());

            ArrayList<Object[]> lstDM = dtr.getDoanhThuDanhMucTheoThangNamChonChien(thang, nam);
            for (Object[] month : lstDM) {
                lblDanhMuc.setText(month[0].toString());
                lblSoLuongDM.setText(month[1].toString());
                lblDoanhThuDM.setText(month[2].toString());
            }
        } else {
        }
    }

    public void clearDoanhThuChien() {
        lblSoLuongSP.setText("");
        lblTenSP.setText("");
        lblDoanhThuSP.setText("");
        lblDoanhThuDM.setText("");
        lblDanhMuc.setText("");

    }

    private void loadSPBan() {
        dtmSP = (DefaultTableModel) tblSPBan.getModel();
        dtmSP.setRowCount(0);
        ArrayList<Object[]> listSp = dtr.getSPDaBanChien();
        for (Object[] o : listSp) {
            dtmSP.addRow(o);
        }
    }

    public void loadTopDanhMuc() {
        ArrayList<Object[]> result = dtr.getTOP1DanhMucChien();
        for (Object[] o : result) {
            lblDanhMuc.setText(o[0].toString());
            lblSoLuongDM.setText(o[1].toString());
            lblDoanhThuDM.setText(o[2].toString());
        }
    }

    public void loadTopSanPham() {
        ArrayList<Object[]> result = dtr.getTOP1SPBANChien();
        for (Object[] o : result) {
            lblTenSP.setText(o[0].toString());
            lblSoLuongSP.setText(o[1].toString());
            lblDoanhThuSP.setText(o[2].toString());
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JComboBox<String> cbbLocNam;
    private javax.swing.JComboBox<String> cbbLocThang;
    private javax.swing.JComboBox<String> cbbNam;
    private javax.swing.JComboBox<String> cbbNamChien;
    private javax.swing.JComboBox<String> cbbThangChien;
    private javax.swing.JComboBox<String> cbbThoiGian;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblDoanhThuChien;
    private javax.swing.JLabel lblDoanhThuDM;
    private javax.swing.JLabel lblDoanhThuSP;
    private javax.swing.JLabel lblDonBiHuyTheoNam;
    private javax.swing.JLabel lblDonBiHuyTheoNgay;
    private javax.swing.JLabel lblDonBiHuyTheoThang;
    private javax.swing.JLabel lblDonThanhCongTheoNam;
    private javax.swing.JLabel lblDonThanhCongTheoNgay;
    private javax.swing.JLabel lblDonThanhCongTheoThang;
    private javax.swing.JLabel lblSoLuongDM;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTienTheoNam;
    private javax.swing.JLabel lblTienTheoNgay;
    private javax.swing.JLabel lblTienTheoThang;
    private javax.swing.JPanel pnBieuDo;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblSPBan;
    // End of variables declaration//GEN-END:variables
}
