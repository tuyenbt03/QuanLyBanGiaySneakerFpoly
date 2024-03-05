/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DanhMuc;
import model.Hang;
import service.DanhMucService;

/**
 *
 * @author PC
 */
public class JplDanhMuc extends javax.swing.JFrame {

    private DanhMucService service = new DanhMucService();

    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;
    private List<String> listMaDM = service.listMaCL();
    private List<String> listTenDM = service.listTenCL();

    private List<DanhMuc> listSP = new ArrayList<>();
    jplSanPham danhmuc;

    public JplDanhMuc(jplSanPham sp) {
        initComponents();
        this.setLocationRelativeTo(null);
        dtm = (DefaultTableModel) this.tblDanhMucConHoatDong.getModel();
        dtm2 = (DefaultTableModel) this.tblDanhMucNgungHoatDong.getModel();
        this.loadTableSCL2();
        this.loadTableSCL();
        this.clear();
        this.danhmuc = sp;
    }

    private void loadTableSCL() {
        ArrayList<DanhMuc> list = (ArrayList<DanhMuc>) this.service.getAllDanhMucConHoatDong();
        dtm.setRowCount(0);

        for (DanhMuc sp : list) {
            dtm.addRow(new Object[]{
                sp.getMaDanhMuc(),
                sp.getTenDanhMuc(),
                sp.getNgayTao(),
                sp.getNgaySua(),
                sp.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        sanpham = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaDM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenDM = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnDoiTrangThai = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rdoConDM = new javax.swing.JRadioButton();
        rdoHetDM = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnTienTrang = new javax.swing.JButton();
        btnLuiTrang = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhMucConHoatDong = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhMucNgungHoatDong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Danh Mục"));

        jLabel1.setText("Mã Danh Mục");

        jLabel2.setText("Tên Danh Mục");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnDoiTrangThai.setText("Đổi trang thái");
        btnDoiTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDoiTrangThai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addGap(18, 18, 18)
                .addComponent(btnDoiTrangThai)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel5.setText("Trạng thái");

        buttonGroup1.add(rdoConDM);
        rdoConDM.setSelected(true);
        rdoConDM.setText("Hoạt động");

        buttonGroup1.add(rdoHetDM);
        rdoHetDM.setText("Không hoạt động");
        rdoHetDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHetDMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoHetDM)
                            .addComponent(rdoConDM, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenDM, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaDM)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoConDM)
                            .addComponent(jLabel5))))
                .addGap(15, 15, 15)
                .addComponent(rdoHetDM)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản Danh Mục"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel6.setText("Tìm kiếm theo tên Danh Mục: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        btnTienTrang.setText(">>");

        btnLuiTrang.setText("<<");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(btnLuiTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100)
                .addComponent(btnTienTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(436, 436, 436))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTienTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuiTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        tblDanhMucConHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Danh Mục", "Tên Danh Mục", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"
            }
        ));
        tblDanhMucConHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucConHoatDongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhMucConHoatDong);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Còn hàng", jPanel6);

        tblDanhMucNgungHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã danh mục", "Tên danh mục", "Ngày tạo", "Ngày sửa", "Trạng thái"
            }
        ));
        tblDanhMucNgungHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucNgungHoatDongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhMucNgungHoatDong);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Hết hàng", jPanel7);

        javax.swing.GroupLayout sanphamLayout = new javax.swing.GroupLayout(sanpham);
        sanpham.setLayout(sanphamLayout);
        sanphamLayout.setHorizontalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanphamLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(sanphamLayout.createSequentialGroup()
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sanphamLayout.setVerticalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanphamLayout.createSequentialGroup()
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void loadTableSCL2() {
        ArrayList<DanhMuc> list = (ArrayList<DanhMuc>) this.service.getAllDanhMucNgungHoatDong();
        dtm2.setRowCount(0);
        for (DanhMuc sp : list) {
            dtm2.addRow(new Object[]{
                sp.getMaDanhMuc(),
                sp.getTenDanhMuc(),
                sp.getNgayTao(),
                sp.getNgaySua(),
                sp.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }
    private void tblDanhMucConHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucConHoatDongMouseClicked
        int index = tblDanhMucConHoatDong.getSelectedRow();
        clickVaoBang(index);
    }//GEN-LAST:event_tblDanhMucConHoatDongMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String tenDM = txtTimKiem.getText().trim();
        listSP = service.timKiem(tenDM);
        showDataTablePT(listSP);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void rdoHetDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHetDMActionPerformed

    private void btnDoiTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTrangThaiActionPerformed
        List<DanhMuc> lstDanhMucCoHoatDong = service.getAllDanhMucConHoatDong();
        int selectedtRow1 = tblDanhMucConHoatDong.getSelectedRow();
        List<DanhMuc> lstDanhMucNgungHoatDong = service.getAllDanhMucNgungHoatDong();
        int selectedRow2 = tblDanhMucNgungHoatDong.getSelectedRow();

        if (selectedtRow1 >= 0) {
            if (selectedtRow1 >= 0 && selectedRow2 < lstDanhMucCoHoatDong.size()) {
                String idChoice = lstDanhMucCoHoatDong.get(selectedtRow1).getId();
                // Sử dụng idChoice ở đây
                service.updateTrangThaiDanhMuc(idChoice);
            } else {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng");
            }
        } else if (selectedRow2 >= 0) {
            if (selectedRow2 >= 0 && selectedRow2 < lstDanhMucNgungHoatDong.size()) {
                String idChoice = lstDanhMucNgungHoatDong.get(selectedRow2).getId();
                // Sử dụng idChoice ở đây
                service.updateTrangThaiDanhMuc(idChoice);
            } else {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng");
            }
        }
        clear();
        loadTableSCL2();
        loadTableSCL();
    }//GEN-LAST:event_btnDoiTrangThaiActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.clear();
        loadTableSCL();
        loadTableSCL2();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int index = tblDanhMucConHoatDong.getSelectedRow();
        if (index >= 0) {
            listSP = service.getAllDanhMucConHoatDong();
            int trangThai = 1;
            if (rdoHetDM.isSelected()) {
                trangThai = 0;
            }

            String ma = txtMaDM.getText();
            String ten = txtTenDM.getText();
            DanhMuc sp = new DanhMuc();
            sp.setMaDanhMuc(ma);
            sp.setTenDanhMuc(ten);
            sp.setTrangThai(trangThai);
            if (ma.equalsIgnoreCase(sp.getMaDanhMuc()) && ten.equalsIgnoreCase(sp.getTenDanhMuc())) {
                if (checkTrongSP()) {
                    service.update(sp);
                    this.loadTableSCL();
                    JOptionPane.showMessageDialog(this, "Sủa thành công");
                     danhmuc.loadCbb();
                } else {
                    JOptionPane.showMessageDialog(this, "Không được để trống");

                }
            } else if (!ma.equalsIgnoreCase(sp.getMaDanhMuc())) {
                if (checkTrongSP()) {
                    if (checkMaSP()) {
                        service.update(sp);
                        this.loadTableSCL();
                        JOptionPane.showMessageDialog(this, "Sủa thành công");
                         danhmuc.loadCbb();
                    } else {
                        JOptionPane.showMessageDialog(this, "Mã sản phẩm bị trùng");
                    }
                }
            } else if (!ten.equalsIgnoreCase(sp.getTenDanhMuc())) {
                if (checkTrongSP()) {
                    if (checkTenSP()) {
                        service.update(sp);
                        this.loadTableSCL();
                        JOptionPane.showMessageDialog(this, "Sủa thành công");
                         danhmuc.loadCbb();
                    } else {
                        JOptionPane.showMessageDialog(this, "Tên sản phẩm bị trùng");

                    }
                }
            } else {
                if (checkTrongSP()) {
                    if (checkMaSP()) {
                        if (checkTenSP()) {
                            service.update(sp);
                            this.loadTableSCL();
                             danhmuc.loadCbb();
                        }
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkTrongSP()) {
            if (checkMaSP()) {
                if (checkTenSP()) {
                    UUID id = UUID.randomUUID();
                    String maSP = this.txtMaDM.getText();
                    String tenSP = this.txtTenDM.getText();
                    int trangThai = rdoConDM.isSelected() == true ? 1 : 0;

                    DanhMuc sp = new DanhMuc(String.valueOf(id), maSP, tenSP, trangThai);

                    boolean a = this.service.insert(sp);

                    if (a == true) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                        return;
                    }
                    this.clear();
                    this.loadTableSCL();
                    danhmuc.loadCbb();
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblDanhMucNgungHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucNgungHoatDongMouseClicked
        // TODO add your handling code here:
        List<DanhMuc> lstDanhMucNgungHoatDon = service.getAllDanhMucNgungHoatDong();
        int selectedRow = tblDanhMucNgungHoatDong.getSelectedRow();
        if (selectedRow >= 0 && selectedRow <= lstDanhMucNgungHoatDon.size()) {
            String idChoice = lstDanhMucNgungHoatDon.get(selectedRow).getId();
            for (DanhMuc dm : lstDanhMucNgungHoatDon) {
                if (dm.getId().equals(idChoice)) {
                    txtMaDM.setText(dm.getMaDanhMuc());
                    txtTenDM.setText(dm.getTenDanhMuc());
                    rdoHetDM.setSelected(true);
                }
            }
        }

    }//GEN-LAST:event_tblDanhMucNgungHoatDongMouseClicked

    public void showDataTablePT(List<DanhMuc> lists) {
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        List<DanhMuc> lstDanhMucConHang = new ArrayList<>();
        List<DanhMuc> lstDanhMucHetHang = new ArrayList<>();

        for (DanhMuc list : lists) {
            if (list.getTrangThai() == 1) {
                lstDanhMucConHang.add(list);
            } else {
                lstDanhMucHetHang.add(list);
            }
        }
        for (DanhMuc danhMuc : lstDanhMucConHang) {
            dtm.addRow(danhMuc.toDataRow());
        }
        for (DanhMuc danhMuc : lstDanhMucHetHang) {
            dtm2.addRow(danhMuc.toDataRow());
        }

    }

    public void clickVaoBang(int index) {
        listSP = service.getAllDanhMucConHoatDong();
        DanhMuc sp = listSP.get(index);

        txtMaDM.setText(sp.getMaDanhMuc());
        txtTenDM.setText(sp.getTenDanhMuc());
        if (sp.getTrangThai() == 1) {
            rdoConDM.setSelected(true);
        } else {
            rdoHetDM.setSelected(true);
        }
    }

    public boolean checkTrongSP() {
        if (txtMaDM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống!");
            return false;
        } else if (txtTenDM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống!");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkMaSP() {
        String ma = txtMaDM.getText().trim();
        for (String s : listMaDM) {
            if (ma.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public boolean checkTenSP() {
        String ten = txtMaDM.getText().trim();
        for (String s : listTenDM) {
            if (ten.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Tên đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    private void clear() {
        this.txtMaDM.setText("");
        this.txtTenDM.setText("");
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JplDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JplDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JplDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JplDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JplDanhMuc().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiTrangThai;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuiTrang;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTienTrang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoConDM;
    private javax.swing.JRadioButton rdoHetDM;
    private javax.swing.JPanel sanpham;
    private javax.swing.JTable tblDanhMucConHoatDong;
    private javax.swing.JTable tblDanhMucNgungHoatDong;
    private javax.swing.JTextField txtMaDM;
    private javax.swing.JTextField txtTenDM;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
