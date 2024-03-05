/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.KhachHang;
import repository.KhachHangRepository;
import service.KhachHangService;
import utilitys.DBConnection;

/**
 *
 * @author LENOVO
 */
public class jplKhachHang extends javax.swing.JPanel {

    /**
     * Creates new form jplKhachHang
     */
//    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModelHD;
    DefaultTableModel defaultTableModelNHD;

//    KhachHangService khachHangService = new KhachHangService();
    KhachHangRepository khachHangRepository = new KhachHangRepository();

    private int soTrang3 = 1;
    private int page = 4;

    public jplKhachHang() throws Exception {
        initComponents();
        loadDataTableKhachHangHD();
        loadDataTableKhachHangNHD();
//        loadTableTimKiem();
    }

    private void clearForm() {
        txtMa.setText("");
        txtTenKhachHang.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtNgaySinh.setDate(null);
        rdoNam.setSelected(true);
        rdoHD.setSelected(true);
        txt_TimKiem.setText("");
    }

    public KhachHang getMa(String maKh) {
        for (KhachHang kh : khachHangRepository.getAll()) {
            if (kh.getMaKhachHang().equalsIgnoreCase(maKh)) {
                return kh;
            }
        }
        return null;
    }

    public int SoBanGhiKhachHangHD() {
        int soBanGhi;
        soBanGhi = khachHangRepository.SoBanGhiKhachHangHD();
        int tongTrang = soBanGhi / page;
        return tongTrang;
    }

    public int SoBanGhiKhachHangNHD() {
        int soBanGhi;
        soBanGhi = khachHangRepository.SoBanGhiKhachHangNHD();
        int tongTrang = soBanGhi / page;
        return tongTrang;
    }

    public void loadDataTableKhachHangHD() {
        ArrayList<KhachHang> list = khachHangRepository.getAllKhachHangHoatDong(soTrang3);
        defaultTableModelHD = (DefaultTableModel) tblKhachhang.getModel();
        defaultTableModelHD.setRowCount(0);
        for (KhachHang kh : list) {
            defaultTableModelHD.addRow(new Object[]{
                kh.getId(),
                kh.getMaKhachHang(),
                kh.getTenKhachHang(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getGioiTinh(),
                kh.getNgaySinh(),
                kh.getTrangThai() == 1 ? "Hoạt động" : "Ngưng hoạt động",});
        }
        lb_Trang.setText(soTrang3 + "/" + SoBanGhiKhachHangHD());
        tblKhachhang.setModel(defaultTableModelHD);
    }

    public void loadDataTableKhachHangNHD() {
        ArrayList<KhachHang> list = khachHangRepository.getAllKhachHangNgungHoatDong(soTrang3);
        defaultTableModelNHD = (DefaultTableModel) tblKhachhangNHD.getModel();
        defaultTableModelNHD.setRowCount(0);
        for (KhachHang kh : list) {
            defaultTableModelNHD.addRow(new Object[]{
                kh.getId(),
                kh.getMaKhachHang(),
                kh.getTenKhachHang(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getGioiTinh(),
                kh.getNgaySinh(),
                kh.getTrangThai() == 1 ? "Hoạt động" : "Ngưng hoạt động",});
        }
        lb_Trang1.setText(soTrang3 + "/" + SoBanGhiKhachHangNHD());
        tblKhachhangNHD.setModel(defaultTableModelNHD);
    }

    public KhachHang getFormData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        ArrayList<KhachHang> listKH = khachHangRepository.getAll();
//        String maKh = "KH" + (listKH.size() + 1);
        String maKh = txtMa.getText();
        String tenKH = txtTenKhachHang.getText();

        String diaChi = txtDiaChi.getText();
        String gioiTinh = rdoNam.isSelected() == true ? "Nam" : "Nữ";
        String email = txtEmail.getText();
        String sdt = txtSoDienThoai.getText();
        Date ngaySinh = txtNgaySinh.getDate();
        int trangThai = rdoHD.isSelected() == true ? 1 : 0;

        if (tenKH.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Tên không được trống");
            return null;
        }

        if (sdt.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống số điện thoại");
            txtSoDienThoai.requestFocus();
            return null;
        } else {
            try {
                int dienthoai = Integer.parseInt(txtSoDienThoai.getText());
                String ktsdt = "0\\d{9}";
                if (txtSoDienThoai.getText().matches(ktsdt) == false) {
                    JOptionPane.showMessageDialog(this, "Bạn nhập sai số điện thoại");
                    txtSoDienThoai.requestFocus();
                    return null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại");
                txtSoDienThoai.requestFocus();
                e.printStackTrace();
                return null;
            }
        }

        if (email.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống email");
            txtEmail.requestFocus();
            return null;
        } else {
            String ktemail = "\\w+@\\w+(\\.\\w+){1,2}";
            if (email.matches(ktemail) == false) {
                JOptionPane.showMessageDialog(this, "Sai định dạng email");
                txtEmail.requestFocus();
                return null;
            }
        }

        if (diaChi.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được trống");
            return null;
        }

        if (ngaySinh == null || ngaySinh.toString().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày sinh");
            txtNgaySinh.requestFocus();
            return null;
        } else {

            try {
                date = txtNgaySinh.getDate();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(maKh);
        kh.setLoaiKhachHang("thuong");
        kh.setTenKhachHang(tenKH);
        kh.setDiaChi(diaChi);
        kh.setGioiTinh(gioiTinh);
        kh.setEmail(email);
        kh.setSdt(sdt);
        kh.setNgaySinh(ngaySinh);
        kh.setTrangThai(trangThai);
        return kh;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachhang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rdoHD = new javax.swing.JRadioButton();
        rdoNHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        lb_Trang = new javax.swing.JLabel();
        btn_nho2 = new javax.swing.JButton();
        btn_lon2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhachhangNHD = new javax.swing.JTable();
        btnNho1 = new javax.swing.JButton();
        lb_Trang1 = new javax.swing.JLabel();
        btnLon1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_TimKiem1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblKhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Mã", "Họ và tên ", "Sdt", "Email ", "Địa chỉ ", "Giới tính ", "Ngày sinh ", "Trạng thái "
            }
        ));
        tblKhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachhangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachhang);

        jLabel1.setText("Mã Khách Hàng");

        jLabel2.setText("Tên khách hàng ");

        jLabel3.setText("Số điện thoại ");

        jLabel4.setText("Email ");

        jLabel5.setText("Địa chỉ ");

        jLabel6.setText("Ngày sinh ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Quản lý khách hàng ");

        txt_TimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_TimKiemKeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Zoom.png"))); // NOI18N

        jLabel9.setText("Giới tính ");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam ");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel10.setText("Trạng thái ");

        buttonGroup2.add(rdoHD);
        rdoHD.setSelected(true);
        rdoHD.setText("Hoạt động ");

        buttonGroup2.add(rdoNHD);
        rdoNHD.setText("Ngừng hoạt đông ");

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Create.png"))); // NOI18N
        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Gear.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/cancel.png"))); // NOI18N
        btnClear.setText("Clear ");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtNgaySinh.setDateFormatString("yyyy-MM-dd");

        lb_Trang.setText("jLabel11");

        btn_nho2.setBackground(new java.awt.Color(153, 255, 204));
        btn_nho2.setForeground(new java.awt.Color(153, 255, 204));
        btn_nho2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Left.png"))); // NOI18N
        btn_nho2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nho2ActionPerformed(evt);
            }
        });

        btn_lon2.setBackground(new java.awt.Color(153, 255, 204));
        btn_lon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Right.png"))); // NOI18N
        btn_lon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lon2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(185, 185, 185)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170)
                                .addComponent(btnClear))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(43, 43, 43)
                                .addComponent(rdoNu)))
                        .addGap(222, 222, 222))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(btn_nho2)
                                .addGap(18, 18, 18)
                                .addComponent(lb_Trang)
                                .addGap(18, 18, 18)
                                .addComponent(btn_lon2)
                                .addGap(0, 119, Short.MAX_VALUE))
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoDienThoai))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(45, 45, 45))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(28, 28, 28)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoHD)
                                .addGap(26, 26, 26)
                                .addComponent(rdoNHD))
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                            .addComponent(txtDiaChi))
                        .addGap(295, 295, 295))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lb_Trang)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_lon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_nho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoDienThoai)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel10)
                    .addComponent(rdoHD)
                    .addComponent(rdoNHD))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua))
                .addGap(144, 144, 144))
        );

        jTabbedPane1.addTab("Khách hàng", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblKhachhangNHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Mã", "Họ và tên ", "Sdt", "Email ", "Địa chỉ ", "Giới tính ", "Ngày sinh ", "Trạng thái "
            }
        ));
        tblKhachhangNHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachhangNHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKhachhangNHD);

        btnNho1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextT.png"))); // NOI18N
        btnNho1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNho1ActionPerformed(evt);
            }
        });

        lb_Trang1.setText("jLabel11");

        btnLon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/nextP.png"))); // NOI18N
        btnLon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLon1ActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Zoom.png"))); // NOI18N

        txt_TimKiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_TimKiem1KeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Quản lý khách hàng ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(btnNho1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_Trang1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLon1)
                        .addContainerGap(935, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_TimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLon1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNho1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lb_Trang1)))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khách hàng ngưng hoạt động", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nho2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nho2ActionPerformed
        // TODO add your handling code here:
        if (soTrang3 > 1) {
            soTrang3--;
            loadDataTableKhachHangHD();
            lb_Trang.setText(soTrang3 + "/" + SoBanGhiKhachHangHD());

        }
    }//GEN-LAST:event_btn_nho2ActionPerformed

    private void btn_lon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lon2ActionPerformed
        // TODO add your handling code here:
        if (soTrang3 < SoBanGhiKhachHangHD()) {
            soTrang3++;
            loadDataTableKhachHangHD();
            lb_Trang.setText(soTrang3 + "/" + SoBanGhiKhachHangHD());
        }
    }//GEN-LAST:event_btn_lon2ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearForm();
//        loadDataTable(trang);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        KhachHang kh = getFormData();

        String maKH = kh.getMaKhachHang();
        if (maKH == null) {
            JOptionPane.showMessageDialog(this, "Mã KH không được để trống");
            return;
        }

        if (getMa(maKH) != null) {
            JOptionPane.showMessageDialog(this, "Đã có mã này");
            return;
        }

        if (khachHangRepository.them(kh) != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadDataTableKhachHangHD();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void tblKhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachhangMouseClicked
        // TODO add your handling code here:
        try {
            int row = tblKhachhang.getSelectedRow();
            if (row == -1) {
                return;
            } else {
                txtMa.setText(tblKhachhang.getValueAt(row, 1).toString());
                txtTenKhachHang.setText(tblKhachhang.getValueAt(row, 2).toString());
                txtSoDienThoai.setText(tblKhachhang.getValueAt(row, 3).toString());
                txtEmail.setText(tblKhachhang.getValueAt(row, 4).toString());
                txtDiaChi.setText(tblKhachhang.getValueAt(row, 5).toString());
                String gt = tblKhachhang.getValueAt(row, 6).toString();
                if (gt.equalsIgnoreCase("Nam")) {
                    rdoNam.setSelected(true);
                } else {
                    rdoNu.setSelected(true);
                }

                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tblKhachhang.getValueAt(row, 7).toString());
                txtNgaySinh.setDate(date);
//                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(tblThongtinKH.getValueAt(row, 8).toString());

                String tt = tblKhachhang.getValueAt(row, 8).toString();
                if (tt.equalsIgnoreCase("Hoạt động")) {
                    rdoHD.setSelected(true);
                } else {
                    rdoNHD.setSelected(true);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblKhachhangMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblKhachhang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
            return;
        }
        KhachHang kh = getFormData();
        if (kh == null) {
            return;
        }
        if (khachHangRepository.updateKhachHang(kh) != null) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
//            loadDataTable(trang);
            loadDataTableKhachHangHD();
            loadDataTableKhachHangNHD();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void loadTableTimKiemHD() {
        ArrayList<KhachHang> list = khachHangRepository.getKhachHangByTenKH(txt_TimKiem.getText());
        defaultTableModelHD.setRowCount(0);
        for (KhachHang khachHangViewModel : list) {
            defaultTableModelHD.addRow(new Object[]{
                khachHangViewModel.getId(),
                khachHangViewModel.getMaKhachHang(),
                khachHangViewModel.getTenKhachHang(),
                khachHangViewModel.getSdt(),
                khachHangViewModel.getEmail(),
                khachHangViewModel.getDiaChi(),
                khachHangViewModel.getGioiTinh(),
                khachHangViewModel.getNgaySinh(),
                khachHangViewModel.getTrangThai() == 1 ? "Còn hoạt động" : "Ngừng hoạt động"
            });
        }
    }

    private void loadTableTimKiemNHD() {
        ArrayList<KhachHang> list = khachHangRepository.getKhachHangByTenKH(txt_TimKiem1.getText());
        defaultTableModelNHD.setRowCount(0);
        for (KhachHang khachHangViewModel : list) {
            defaultTableModelNHD.addRow(new Object[]{
                khachHangViewModel.getId(),
                khachHangViewModel.getMaKhachHang(),
                khachHangViewModel.getTenKhachHang(),
                khachHangViewModel.getSdt(),
                khachHangViewModel.getEmail(),
                khachHangViewModel.getDiaChi(),
                khachHangViewModel.getGioiTinh(),
                khachHangViewModel.getNgaySinh(),
                khachHangViewModel.getTrangThai() == 1 ? "Còn hoạt động" : "Ngừng hoạt động"
            });
        }
    }
    private void tblKhachhangNHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachhangNHDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKhachhangNHDMouseClicked

    private void btnNho1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNho1ActionPerformed
        // TODO add your handling code here:
        if (soTrang3 > 1) {
            soTrang3--;
            loadDataTableKhachHangNHD();
            lb_Trang1.setText(soTrang3 + "/" + SoBanGhiKhachHangNHD());

        }
    }//GEN-LAST:event_btnNho1ActionPerformed

    private void btnLon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLon1ActionPerformed
        // TODO add your handling code here:
        if (soTrang3 < SoBanGhiKhachHangNHD()) {
            soTrang3++;
            loadDataTableKhachHangNHD();
            lb_Trang1.setText(soTrang3 + "/" + SoBanGhiKhachHangNHD());

        }
    }//GEN-LAST:event_btnLon1ActionPerformed

    private void txt_TimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemKeyReleased
        // TODO add your handling code here:
        loadTableTimKiemHD();
    }//GEN-LAST:event_txt_TimKiemKeyReleased

    private void txt_TimKiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiem1KeyReleased
        // TODO add your handling code here:
        loadTableTimKiemNHD();
    }//GEN-LAST:event_txt_TimKiem1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLon1;
    private javax.swing.JButton btnNho1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btn_lon2;
    private javax.swing.JButton btn_nho2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb_Trang;
    private javax.swing.JLabel lb_Trang1;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoNHD;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachhang;
    private javax.swing.JTable tblKhachhangNHD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txt_TimKiem;
    private javax.swing.JTextField txt_TimKiem1;
    // End of variables declaration//GEN-END:variables
}
