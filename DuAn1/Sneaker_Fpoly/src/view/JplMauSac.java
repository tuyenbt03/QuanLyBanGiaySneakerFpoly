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
import model.MauSac;
import service.MauSacService;

public class JplMauSac extends javax.swing.JFrame {

    private MauSacService service = new MauSacService();

    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;

    private List<String> listMaDM = service.listMaCL();
    private List<String> listTenDM = service.listTenCL();

    private List<MauSac> listSP = new ArrayList<>();
    jplSanPham jplsp;

    public JplMauSac(jplSanPham sp) {
        initComponents();
        this.setLocationRelativeTo(null);
        dtm = (DefaultTableModel) this.tblMauConHoatDong.getModel();
        dtm2 = (DefaultTableModel) this.tblMauNgungHoatDong.getModel();
        this.loadTableSCL();
        this.loadTableSCL2();
        this.clear();
         this.jplsp = sp;
    }

    private void loadTableSCL() {
        ArrayList<MauSac> list = (ArrayList<MauSac>) this.service.getAllMauSacConHoatDong();
        dtm.setRowCount(0);
        for (MauSac sp : list) {
            dtm.addRow(new Object[]{
                sp.getMaMau(),
                sp.getTenMau(),
                sp.getNgayTao(),
                sp.getNgaySua(),
                sp.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    private void loadTableSCL2() {
        ArrayList<MauSac> list = (ArrayList<MauSac>) this.service.getAllMauSacNgungHoatDong();
        dtm2.setRowCount(0);
        for (MauSac sp : list) {
            dtm2.addRow(new Object[]{
                sp.getMaMau(),
                sp.getTenMau(),
                sp.getNgayTao(),
                sp.getNgaySua(),
                sp.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        btnLuiTrang1 = new javax.swing.JButton();
        btnTienTrang1 = new javax.swing.JButton();
        Hang = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaMS = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenMS = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rdoConMS = new javax.swing.JRadioButton();
        rdoHetMS = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMauConHoatDong = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMauNgungHoatDong = new javax.swing.JTable();

        btnLuiTrang1.setText("<<");

        btnTienTrang1.setText(">>");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnLuiTrang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTienTrang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLuiTrang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTienTrang1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Màu sắc"));

        jLabel1.setText("Mã Màu Sắc");

        jLabel2.setText("Tên Màu Sắc");

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

        jButton1.setText("Đổi trạng thái");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaSP)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1)
                        .addComponent(btnThem)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnSuaSP, btnThem, jButton1});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jLabel5.setText("Trạng thái");

        buttonGroup1.add(rdoConMS);
        rdoConMS.setSelected(true);
        rdoConMS.setText("Hoạt Động");

        buttonGroup1.add(rdoHetMS);
        rdoHetMS.setText("Không hoạt động");
        rdoHetMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHetMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaMS, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rdoHetMS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(rdoConMS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdoConMS))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHetMS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc màu sắc"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel6.setText("Tìm kiếm theo tên Màu Sắc");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
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

        tblMauConHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Màu Sắc", "Tên Màu Sắc", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"
            }
        ));
        tblMauConHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauConHoatDongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMauConHoatDong);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Còn hàng", jPanel1);

        tblMauNgungHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã màu", "Tên màu", "Ngày tạo", "Ngày xuất", "Trạng thái"
            }
        ));
        tblMauNgungHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauNgungHoatDongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMauNgungHoatDong);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hết hàng", jPanel2);

        javax.swing.GroupLayout HangLayout = new javax.swing.GroupLayout(Hang);
        Hang.setLayout(HangLayout);
        HangLayout.setHorizontalGroup(
            HangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        HangLayout.setVerticalGroup(
            HangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(HangLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Hang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Hang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkTrongSP()) {
            if (checkMaSP()) {
                if (checkTenSP()) {
                    UUID id = UUID.randomUUID();
                    String maSP = this.txtMaMS.getText();
                    String tenSP = this.txtTenMS.getText();
                    int trangThai = rdoConMS.isSelected() == true ? 1 : 0;

                    MauSac sp = new MauSac(String.valueOf(id), maSP, tenSP, trangThai);

                    boolean a = this.service.insert(sp);

                    if (a == true) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                        return;
                    }
                    this.clear();
                    this.loadTableSCL();
                    jplsp.loadCbb();
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int index = tblMauConHoatDong.getSelectedRow();
        if (index >= 0) {
            listSP = service.getAllMauSacConHoatDong();
            int trangThai = 1;
            if (rdoHetMS.isSelected()) {
                trangThai = 0;
            }
            String ma = txtMaMS.getText();
            String ten = txtTenMS.getText();
            MauSac sp = new MauSac();
            sp.setMaMau(ma);
            sp.setTenMau(ten);
            sp.setTrangThai(trangThai);
            if (ma.equalsIgnoreCase(sp.getMaMau()) && ten.equalsIgnoreCase(sp.getTenMau())) {
                if (checkTrongSP()) {
                    service.update(sp);
                    this.loadTableSCL();
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    jplsp.loadCbb();

                } else {
                    JOptionPane.showMessageDialog(this, "Không được để trống");

                }
            } else if (!ma.equalsIgnoreCase(sp.getMaMau())) {
                if (checkTrongSP()) {
                    if (checkMaSP()) {
                        service.update(sp);
                        this.loadTableSCL();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                        jplsp.loadCbb();

                    } else {
                        JOptionPane.showMessageDialog(this, "Mã sản phẩm bị trùng");
                    }
                }
            } else if (!ten.equalsIgnoreCase(sp.getTenMau())) {
                if (checkTrongSP()) {
                    if (checkTenSP()) {
                        service.update(sp);
                        this.loadTableSCL();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                         jplsp.loadCbb();
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
                            jplsp.loadCbb();
                        }
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblMauConHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauConHoatDongMouseClicked
        int index = tblMauConHoatDong.getSelectedRow();
        clickVaoBang(index);
    }//GEN-LAST:event_tblMauConHoatDongMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String tenDM = txtTimKiem.getText().trim();
        listSP = service.timKiem(tenDM);
        showDataTablePT(listSP);

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void rdoHetMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHetMSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<MauSac> lstMauSacConHoatDong = service.getAllMauSacConHoatDong();
        int selectedtRow1 = tblMauConHoatDong.getSelectedRow();
        List<MauSac> lstMauSacNgungHoatDong = service.getAllMauSacNgungHoatDong();
        int selectedRow2 = tblMauNgungHoatDong.getSelectedRow();

        if (selectedtRow1 >= 0) {
            if (selectedtRow1 >= 0 && selectedRow2 < lstMauSacConHoatDong.size()) {
                String idChoice = lstMauSacConHoatDong.get(selectedtRow1).getId();
                // Sử dụng idChoice ở đây
                service.updateTrangThaiMauSac(idChoice);
            } else {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng");
            }
        } else if (selectedRow2 >= 0) {
            if (selectedRow2 >= 0 && selectedRow2 < lstMauSacNgungHoatDong.size()) {
                String idChoice = lstMauSacNgungHoatDong.get(selectedRow2).getId();
                // Sử dụng idChoice ở đây
                service.updateTrangThaiMauSac(idChoice);
            } else {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng");
            }
        }
        clear();
        loadTableSCL2();
        loadTableSCL();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblMauNgungHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauNgungHoatDongMouseClicked
        // TODO add your handling code here:
        List<MauSac> lstMauSacNgungHoatDong = service.getAllMauSacNgungHoatDong();
        int selectedRow = tblMauNgungHoatDong.getSelectedRow();
        if (selectedRow >= 0 && selectedRow <= lstMauSacNgungHoatDong.size()) {
            String idChoice = lstMauSacNgungHoatDong.get(selectedRow).getId();
            for (MauSac mau : lstMauSacNgungHoatDong) {
                if (mau.getId().equals(idChoice)) {
                    txtMaMS.setText(mau.getMaMau());
                    txtTenMS.setText(mau.getTenMau());
                    rdoHetMS.setSelected(true);
                }
            }
        }
    }//GEN-LAST:event_tblMauNgungHoatDongMouseClicked

    public void showDataTablePT(List<MauSac> lists) {
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        List<MauSac> lstMauSacConHang = new ArrayList<>();
        List<MauSac> lstMauSacHetHang = new ArrayList<>();
        for (MauSac pt : listSP) {
            if(pt.getTrangThai()==1){
                lstMauSacConHang.add(pt);
            }else{
                lstMauSacHetHang.add(pt);
            }
        }
        for (MauSac mauSac : lstMauSacHetHang) {
            dtm2.addRow(mauSac.toDataRow());
        }
        for (MauSac mauSac : lstMauSacConHang) {
            dtm.addRow(mauSac.toDataRow());
        }
    }

    public void clickVaoBang(int index) {
        listSP = service.getAllMauSacConHoatDong();
        MauSac sp = listSP.get(index);

        txtMaMS.setText(sp.getMaMau());
        txtTenMS.setText(sp.getTenMau());
        if (sp.getTrangThai() == 1) {
            rdoConMS.setSelected(true);
        } else {
            rdoHetMS.setSelected(true);
        }
    }

    public boolean checkTrongSP() {
        if (txtMaMS.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống!");
            return false;
        } else if (txtTenMS.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống!");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkMaSP() {
        String ma = txtMaMS.getText().trim();
        for (String s : listMaDM) {
            if (ma.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public boolean checkTenSP() {
        String ten = txtTenMS.getText().trim();
        for (String s : listTenDM) {
            if (ten.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Tên đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    private void clear() {

        this.txtMaMS.setText("");
        this.txtTenMS.setText("");
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
//            java.util.logging.Logger.getLogger(JplMauSac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JplMauSac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JplMauSac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JplMauSac.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JplMauSac().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Hang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuiTrang1;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTienTrang1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoConMS;
    private javax.swing.JRadioButton rdoHetMS;
    private javax.swing.JTable tblMauConHoatDong;
    private javax.swing.JTable tblMauNgungHoatDong;
    private javax.swing.JTextField txtMaMS;
    private javax.swing.JTextField txtTenMS;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
