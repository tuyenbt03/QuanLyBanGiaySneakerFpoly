/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MauSac;
import model.Size;
import service.SizeService;

/**
 *
 * @author PC
 */
public class JplSize extends javax.swing.JFrame {

    private SizeService service = new SizeService();

    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;

    private List<String> listMaDM = service.listMaCL();
    private List<String> listTenDM = service.listTenCL();

    private List<Size> listSP = new ArrayList<>();
     jplSanPham size;
     
    public JplSize(jplSanPham sp) {
        initComponents();
        this.setLocationRelativeTo(null);
        dtm = (DefaultTableModel) this.tblSizeConHoatDong.getModel();
        this.loadTableSCL();
        dtm2 = (DefaultTableModel) this.tblSizeNgungHoatDong.getModel();
        this.loadTableSCL2();
        this.clear();
         this.size = sp;
    }

    private void loadTableSCL() {
        ArrayList<Size> list = (ArrayList<Size>) this.service.getAllSizeConHoatDong();
        dtm.setRowCount(0);

        for (Size sp : list) {
            dtm.addRow(new Object[]{
                sp.getMaSize(),
                sp.getTenSize(),
                sp.getNgayTao(),
                sp.getNgaySua(),
                sp.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    private void loadTableSCL2() {
        ArrayList<Size> list = (ArrayList<Size>) this.service.getAllSizeNgungHoatDong();
        dtm2.setRowCount(0);
        for (Size sp : list) {
            dtm2.addRow(new Object[]{
                sp.getMaSize(),
                sp.getTenSize(),
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSize = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenSize = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnDoiTrangThai = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rdoConSize = new javax.swing.JRadioButton();
        rdoHetSize = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSizeConHoatDong = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSizeNgungHoatDong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Size"));

        jLabel1.setText("Mã Size");

        jLabel2.setText("Tên Size");

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

        btnDoiTrangThai.setText("Đổi trạng thái");
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDoiTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDoiTrangThai, btnLamMoi, btnSuaSP, btnThem});

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

        buttonGroup1.add(rdoConSize);
        rdoConSize.setSelected(true);
        rdoConSize.setText("Hoạt động");

        buttonGroup1.add(rdoHetSize);
        rdoHetSize.setText("Không hoạt động");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txtMaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoConSize, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoHetSize))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenSize, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdoConSize))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHetSize)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc Size"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel7.setText("Tìm kiếm theo tên Size");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tblSizeConHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Size", "Tên Size", "Ngày Tạo", "Ngày Sửa", "Tranh Thái"
            }
        ));
        tblSizeConHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSizeConHoatDongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSizeConHoatDong);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Còn hàng", jPanel1);

        tblSizeNgungHoatDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Size", "Tên Size", "Ngày Tạo", "Ngày Sửa", "Tranh Thái"
            }
        ));
        tblSizeNgungHoatDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSizeNgungHoatDongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSizeNgungHoatDong);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hết hàng", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSizeConHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSizeConHoatDongMouseClicked
        int index = tblSizeConHoatDong.getSelectedRow();
        clickVaoBang(index);
    }//GEN-LAST:event_tblSizeConHoatDongMouseClicked

    private void btnDoiTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTrangThaiActionPerformed
        List<Size> lstSizeConHoatDong = service.getAllSizeConHoatDong();
        int selectedtRow1 = tblSizeConHoatDong.getSelectedRow();
        List<Size> lstSizeNgungHoatDong = service.getAllSizeNgungHoatDong();
        int selectedRow2 = tblSizeNgungHoatDong.getSelectedRow();

        if (selectedtRow1 >= 0) {
            if (selectedtRow1 >= 0 && selectedRow2 < lstSizeConHoatDong.size()) {
                String idChoice = lstSizeConHoatDong.get(selectedtRow1).getId();
                // Sử dụng idChoice ở đây
                service.updateTrangThaiSize(idChoice);
            } else {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng");
            }
        } else if (selectedRow2 >= 0) {
            if (selectedRow2 >= 0 && selectedRow2 < lstSizeNgungHoatDong.size()) {
                String idChoice = lstSizeNgungHoatDong.get(selectedRow2).getId();
                // Sử dụng idChoice ở đây
                service.updateTrangThaiSize(idChoice);
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
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int index = tblSizeConHoatDong.getSelectedRow();
        if (index >= 0) {
            listSP = service.getAllSizeConHoatDong();
            int trangThai = 1;
            if (rdoHetSize.isSelected()) {
                trangThai = 0;
            }
            String ma = txtMaSize.getText();
            String ten = txtTenSize.getText();

            Size sp = new Size();
            sp.setMaSize(ma);
            sp.setTenSize(ten);
            sp.setTrangThai(trangThai);
            if (ma.equalsIgnoreCase(sp.getMaSize()) && ten.equalsIgnoreCase(sp.getTenSize())) {
                if (checkTrongSP()) {
                    service.update(sp);
                    this.loadTableSCL();
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                     size.loadCbb();

                } else {
                    JOptionPane.showMessageDialog(this, "Không được để trống");

                }
            } else if (!ma.equalsIgnoreCase(sp.getMaSize())) {
                if (checkTrongSP()) {
                    if (checkMaSP()) {
                        service.update(sp);
                        this.loadTableSCL();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                         size.loadCbb();

                    } else {
                        JOptionPane.showMessageDialog(this, "Mã sản phẩm bị trùng");
                    }
                }
            } else if (!ten.equalsIgnoreCase(sp.getTenSize())) {
                if (checkTrongSP()) {
                    if (checkTenSP()) {
                        service.update(sp);
                        this.loadTableSCL();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                         size.loadCbb();
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
                           
                             size.loadCbb();
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
                    String maSP = this.txtMaSize.getText();
                    String tenSP = this.txtTenSize.getText();
                    int trangThai = 1;
                    if (rdoHetSize.isSelected()) {
                        trangThai = 0;
                    }

                    Size sp = new Size(String.valueOf(id), maSP, tenSP, trangThai);

                    boolean a = this.service.insert(sp);

                    if (a == true) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                        return;
                    }
                    this.clear();
                    this.loadTableSCL();
                    
                    size.loadCbb();
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String tenDM = txtTimKiem.getText().trim();
        listSP = service.timKiem(tenDM);
        showDataTablePT(listSP);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblSizeNgungHoatDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSizeNgungHoatDongMouseClicked
        // TODO add your handling code here:
        List<Size> lstSizeNgungHoatDong = service.getAllSizeNgungHoatDong();
        int selectedRow = tblSizeNgungHoatDong.getSelectedRow();
        if (selectedRow >= 0 && selectedRow <= lstSizeNgungHoatDong.size()) {
            String idChoice = lstSizeNgungHoatDong.get(selectedRow).getId();
            for (Size size : lstSizeNgungHoatDong) {
                if (size.getId().equals(idChoice)) {
                    txtMaSize.setText(size.getMaSize());
                    txtTenSize.setText(size.getTenSize());
                    rdoHetSize.setSelected(true);
                }
            }
        }
    }//GEN-LAST:event_tblSizeNgungHoatDongMouseClicked

    public void showDataTablePT(List<Size> lists) {
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        List<Size> lstSizeConHang = new ArrayList<>();
        List<Size> lstSizeHetHang = new ArrayList<>();
        for (Size pt : lists) {
            if(pt.getTrangThai()==1){
                lstSizeConHang.add(pt);
            }else{
                lstSizeHetHang.add(pt);
            }
        }
        for (Size size : lstSizeHetHang) {
            dtm2.addRow(size.toDataRow());
        }
        for (Size size : lstSizeConHang) {
            dtm.addRow(size.toDataRow());
        }
    }

    public void clickVaoBang(int index) {
        listSP = service.getAllSizeConHoatDong();
        Size sp = listSP.get(index);
        txtMaSize.setText(sp.getMaSize());
        txtTenSize.setText(sp.getTenSize());
        if (sp.getTrangThai() == 1) {
            rdoConSize.setSelected(true);
        } else {
            rdoHetSize.setSelected(true);
        }
    }

    public boolean checkTrongSP() {
        if (txtMaSize.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống!");
            return false;
        } else if (txtTenSize.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống!");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkMaSP() {
        String ma = txtMaSize.getText().trim();
        for (String s : listMaDM) {
            if (ma.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public boolean checkTenSP() {
        String ten = txtTenSize.getText().trim();
        for (String s : listTenDM) {
            if (ten.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Tên đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    private void clear() {

        this.txtMaSize.setText("");
        this.txtTenSize.setText("");
    }

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
//            java.util.logging.Logger.getLogger(JplSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JplSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JplSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JplSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JplSize().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiTrangThai;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoConSize;
    private javax.swing.JRadioButton rdoHetSize;
    private javax.swing.JTable tblSizeConHoatDong;
    private javax.swing.JTable tblSizeNgungHoatDong;
    private javax.swing.JTextField txtMaSize;
    private javax.swing.JTextField txtTenSize;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
