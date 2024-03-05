/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import model.ChucVu;
import model.NhanVien;
import repository.ChucVuRepository;
import repository.NhanVienRepository;
import utilitys.DBConnection;
import utilitys.DBConnection;

/**
 *
 * @author Chien Duong
 */
public class jplNhanVien extends javax.swing.JPanel {

    DefaultTableModel dtmCV;
    String fileanh = null;

    DefaultComboBoxModel<String> dcmCVLoc;
    DefaultComboBoxModel<ChucVu> dcmCV;

    DefaultComboBoxModel<ChucVu> dcmCVM;
    DefaultTableModel dtmNVLam;
    DefaultTableModel dtmNVNghi;

    ChucVuRepository cvr = new ChucVuRepository();
    NhanVienRepository nvr = new NhanVienRepository();
    private int soTrang = 1;
    private int page = 4;

    /**
     * Creates new form jplNhanVien1
     */
    public jplNhanVien() {
        initComponents();
        dtmCV = new DefaultTableModel();
        dtmCV = (DefaultTableModel) tblChucVu.getModel();
        dcmCV = new DefaultComboBoxModel<>();
        dcmCVM = new DefaultComboBoxModel<>();
        dcmCVLoc = new DefaultComboBoxModel<>();

        dtmNVLam = new DefaultTableModel();
        dtmNVNghi = new DefaultTableModel();
        dtmNVLam = (DefaultTableModel) tblNVLam.getModel();
        dtmNVNghi = (DefaultTableModel) tblNVNghi.getModel();

        cbbCV.setModel((DefaultComboBoxModel) dcmCV);

        cbbLocCV.setModel((DefaultComboBoxModel) dcmCVLoc);
        loadComboCVLoc();
        loadComboCV();
        loadComboCVTim();
        fillTableNVLam();
        fillTableNVNghi();
        fillTableCV();
        clearNV();
    }


    public void fillTableNVLam() {
        ArrayList<NhanVien> _lstNhanVien = nvr.getNhanVienLam(soTrang);

        dtmNVLam.setRowCount(0);
        String column[] = {"ID", "Mã Nhân Viên", "Họ Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại", "Email", "Mật Khẩu", "Chức Vụ", "Hình", "Trạng Thái"};
        dtmNVLam.setColumnIdentifiers(column);
        for (NhanVien nv : _lstNhanVien) {
            dtmNVLam.addRow(new Object[]{
                nv.getId(),
                nv.getMaNV(),
                nv.getHoVaTen(),
                nv.getNgaySinh(),
                nv.getGioiTinh(),
                nv.getDiaChi(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getMatKhau(),
                nv.getIdCV(),
                nv.getHinh(),
                nv.getTrangThai() == 1 ? "Đi Làm" : "Nghỉ Làm",});
        }
        lblTrangLam.setText(soTrang + "/" + SoBanGhiNhanVienLam());
        tblNVLam.setModel(dtmNVLam);

    }

    public int SoBanGhiNhanVienLam() {
        int soBanGhi;
        soBanGhi = nvr.SoBanGhiLam();
        int tongTrang = soBanGhi / page;
        return tongTrang;
    }

    public int SoBanGhiNhanVienNghi() {
        int soBanGhi;
        soBanGhi = nvr.SoBanGhiNghi();
        int tongTrang = soBanGhi / page;
        return tongTrang;
    }

    private void loadTimLamByCV(String tenCV) {
        dtmNVLam.setRowCount(0);
        ArrayList<NhanVien> list = nvr.getNVlamByCV(tenCV);
        for (NhanVien nhanVienM : list) {
            dtmNVLam.addRow(nhanVienM.toDataRow());
        }
    }

    private void loadTimLamBySdt(String sdt) {
        dtmNVLam.setRowCount(0);
        ArrayList<NhanVien> list = nvr.getNVNghiBySdt(sdt);
        for (NhanVien nhanVienM : list) {
            dtmNVLam.addRow(nhanVienM.toDataRow());
        }
    }


    public void fillTableNVNghi() {
        ArrayList<NhanVien> _lstNhanVien = nvr.getNhanVienNghi(soTrang);

        dtmNVNghi.setRowCount(0);
        String column[] = {"ID", "Mã Nhân Viên", "Họ Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại", "Email", "Mật Khẩu", "Chức Vụ", "Hình", "Trạng Thái"};
        dtmNVNghi.setColumnIdentifiers(column);
        for (NhanVien nv : _lstNhanVien) {
            dtmNVNghi.addRow(new Object[]{
                nv.getId(),
                nv.getMaNV(),
                nv.getHoVaTen(),
                nv.getNgaySinh(),
                nv.getGioiTinh(),
                nv.getDiaChi(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getMatKhau(),
                nv.getIdCV(),
                nv.getHinh(),
                nv.getTrangThai() == 1 ? "Đi Làm" : "Nghỉ Làm",});
        }
        lblTrangNghi.setText(soTrang + "/" + SoBanGhiNhanVienNghi());
        tblNVNghi.setModel(dtmNVNghi);

    }

    private void loadTimNghiByCV(String tenCV) {
        dtmNVNghi.setRowCount(0);
        ArrayList<NhanVien> list = nvr.getNVNghiByCV(tenCV);
        for (NhanVien nhanVienM : list) {
            dtmNVNghi.addRow(nhanVienM.toDataRow());
        }
    }

    private void loadTimNghiBySdt(String sdt) {
        dtmNVNghi.setRowCount(0);
        ArrayList<NhanVien> list = nvr.getNVNghiBySdt(sdt);
        for (NhanVien nhanVienM : list) {
            dtmNVNghi.addRow(nhanVienM.toDataRow());
        }
    }


     public void fillTableCV() {
        ArrayList<ChucVu> _lstChucVu = cvr.getAllChucVu(soTrang);

        dtmCV.setRowCount(0);
        String column[] = {"Mã Chức Vụ", "Tên Chức Vụ", "Trạng Thái"};
        dtmCV.setColumnIdentifiers(column);
        for (ChucVu cv : _lstChucVu) {
            dtmCV.addRow(new Object[]{
                cv.getMaChucVu(), cv.getTenChucVu(), cv.getTrangThai() == 1 ? "Hủy" : "Còn",});
        }
        lblTrangChucVu.setText(soTrang + "/" + SoBanGhiChucVu());
        tblChucVu.setModel(dtmCV);

    }
       public int SoBanGhiChucVu() {
        int soBanGhi;
        soBanGhi = cvr.SoBanGhi();
        int tongTrang = soBanGhi / page;
        return tongTrang;
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        txtHoVaTen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        rdoNghiLam = new javax.swing.JRadioButton();
        rdoDiLam = new javax.swing.JRadioButton();
        btnUpAnh = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        btnCapNhatNV = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbbLocCV = new javax.swing.JComboBox<>();
        btnTimSdt = new javax.swing.JButton();
        txtTimKiemSDT = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNVLam = new javax.swing.JTable();
        btnPreLam = new javax.swing.JButton();
        lblTrangLam = new javax.swing.JLabel();
        btnNextLam = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNVNghi = new javax.swing.JTable();
        btnPreNghi = new javax.swing.JButton();
        lblTrangNghi = new javax.swing.JLabel();
        btnNextNghi = new javax.swing.JButton();
        cbbCV = new javax.swing.JComboBox<>();
        txtMaNV = new javax.swing.JTextField();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        lblHinh = new javax.swing.JLabel();
        lblShowPass = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtMaCV = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTenCV = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoNghi = new javax.swing.JRadioButton();
        btnThemCV = new javax.swing.JButton();
        btnSuaCV = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChucVu = new javax.swing.JTable();
        btnClearCV = new javax.swing.JButton();
        btnNextChucVu = new javax.swing.JButton();
        btnPrChucVu = new javax.swing.JButton();
        lblTrangChucVu = new javax.swing.JLabel();

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel4.setText("SDT");

        jLabel9.setText("Chức vụ");

        jLabel5.setText("Điạ Chỉ");

        jLabel6.setText("Ngày Sinh");

        txtPass.setText("jPasswordField1");
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã NV");

        jLabel7.setText("Mật Khẩu");

        jLabel2.setText("Email");

        jLabel3.setText("Họ Và Tên");

        jLabel10.setText("Giới Tính");

        buttonGroup2.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel11.setText("Trạng Thái");

        buttonGroup3.add(rdoNghiLam);
        rdoNghiLam.setText("Nghỉ làm");

        buttonGroup3.add(rdoDiLam);
        rdoDiLam.setSelected(true);
        rdoDiLam.setText("Đi Làm");

        btnUpAnh.setBackground(new java.awt.Color(153, 204, 255));
        btnUpAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Upload.png"))); // NOI18N
        btnUpAnh.setText("UpLoad Ảnh");
        btnUpAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpAnhActionPerformed(evt);
            }
        });

        btnThemNV.setBackground(new java.awt.Color(153, 204, 255));
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Accept.png"))); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnCapNhatNV.setBackground(new java.awt.Color(153, 204, 255));
        btnCapNhatNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Refresh.png"))); // NOI18N
        btnCapNhatNV.setText("Cập Nhật");
        btnCapNhatNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNVActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setBackground(new java.awt.Color(204, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Zoom in.png"))); // NOI18N
        jLabel12.setText("Lọc Theo Chức Vụ");

        cbbLocCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbLocCV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocCVItemStateChanged(evt);
            }
        });

        btnTimSdt.setBackground(new java.awt.Color(153, 204, 255));
        btnTimSdt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Search.png"))); // NOI18N
        btnTimSdt.setText("Tìm Kiếm SDT");
        btnTimSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSdtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cbbLocCV, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268)
                .addComponent(txtTimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimSdt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbbLocCV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblNVLam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã NV", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Email", "Mật Khẩu", "Chức Vụ", "Hình ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNVLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVLamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNVLam);

        btnPreLam.setBackground(new java.awt.Color(255, 255, 204));
        btnPreLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2T.png"))); // NOI18N
        btnPreLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreLamActionPerformed(evt);
            }
        });

        lblTrangLam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTrangLam.setText("12/12");

        btnNextLam.setBackground(new java.awt.Color(255, 255, 204));
        btnNextLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2P.png"))); // NOI18N
        btnNextLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextLamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(btnPreLam, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(lblTrangLam, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnNextLam, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTrangLam, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPreLam, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNextLam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Đang làm", jPanel5);

        tblNVNghi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã NV", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Email", "Mật Khẩu", "Chức Vụ", "Hình ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNVNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVNghiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNVNghi);

        btnPreNghi.setBackground(new java.awt.Color(255, 255, 204));
        btnPreNghi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2T.png"))); // NOI18N
        btnPreNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreNghiActionPerformed(evt);
            }
        });

        lblTrangNghi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTrangNghi.setText("12/12");

        btnNextNghi.setBackground(new java.awt.Color(255, 255, 204));
        btnNextNghi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2P.png"))); // NOI18N
        btnNextNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextNghiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(btnPreNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(lblTrangNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnNextNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPreNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrangNghi)
                    .addComponent(btnNextNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Đã Nghỉ", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbbCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Null" }));
        cbbCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCVActionPerformed(evt);
            }
        });

        txtNgaySinh.setDateFormatString("yyyy-MM-dd");

        lblShowPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/An.png"))); // NOI18N
        lblShowPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShowPassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblShowPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblShowPassMouseExited(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(153, 204, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Trash.png"))); // NOI18N
        btnClear.setText("CLEAR");
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(7, 7, 7))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(8, 8, 8))))))
                            .addComponent(jLabel10))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(txtHoVaTen))
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(116, 116, 116)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail)
                                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(cbbCV, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSDT))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblShowPass)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNghiLam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpAnh)
                                .addGap(98, 98, 98)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemNV, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(btnCapNhatNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblShowPass, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cbbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(jLabel11)
                            .addComponent(rdoDiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNghiLam))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnCapNhatNV, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        jTabbedPane1.addTab("Quản lý nhân viên", jPanel1);

        jLabel14.setText("Mã chức vụ");

        jLabel15.setText("Tên chức vụ");

        jLabel16.setText("Trạng thái");

        buttonGroup1.add(rdoCon);
        rdoCon.setSelected(true);
        rdoCon.setText("Còn");

        buttonGroup1.add(rdoNghi);
        rdoNghi.setText("Hủy");

        btnThemCV.setBackground(new java.awt.Color(153, 204, 255));
        btnThemCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Create.png"))); // NOI18N
        btnThemCV.setText("Thêm");
        btnThemCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCVActionPerformed(evt);
            }
        });

        btnSuaCV.setBackground(new java.awt.Color(153, 204, 255));
        btnSuaCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Edit.png"))); // NOI18N
        btnSuaCV.setText("Sửa");
        btnSuaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCVActionPerformed(evt);
            }
        });

        tblChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Chức vụ", "Tên chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChucVuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChucVu);

        btnClearCV.setBackground(new java.awt.Color(153, 204, 255));
        btnClearCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Trash.png"))); // NOI18N
        btnClearCV.setText("Clear");
        btnClearCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCVActionPerformed(evt);
            }
        });

        btnNextChucVu.setBackground(new java.awt.Color(255, 255, 204));
        btnNextChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2P.png"))); // NOI18N
        btnNextChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextChucVuActionPerformed(evt);
            }
        });

        btnPrChucVu.setBackground(new java.awt.Color(255, 255, 204));
        btnPrChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/next2T.png"))); // NOI18N
        btnPrChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrChucVuActionPerformed(evt);
            }
        });

        lblTrangChucVu.setText("0/0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(btnThemCV)))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnSuaCV)
                                .addGap(209, 209, 209)
                                .addComponent(btnClearCV)))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(rdoCon)
                        .addGap(31, 31, 31)
                        .addComponent(rdoNghi))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(btnPrChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(lblTrangChucVu)
                        .addGap(85, 85, 85)
                        .addComponent(btnNextChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(rdoCon)
                    .addComponent(rdoNghi))
                .addGap(59, 59, 59)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCV)
                    .addComponent(btnSuaCV)
                    .addComponent(btnClearCV))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrChucVu)
                    .addComponent(btnNextChucVu)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblTrangChucVu)))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chức vụ", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
        txtPass.setText("");
    }//GEN-LAST:event_txtPassActionPerformed

    private void btnUpAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpAnhActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser f = new JFileChooser("src\\AnhNV");
            f.showOpenDialog(null);
            File file = f.getSelectedFile();
            Image img = ImageIO.read(file);
            fileanh = file.getName();
            int w = lblHinh.getWidth();
            int h = lblHinh.getHeight();
            lblHinh.setIcon(new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnUpAnhActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        NhanVien nvm = getNVForm();
        if (nvm == null) {
            return;
        }
        if (nvr.insertNV(nvm) != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại do trùng mã nhân viên");
        }
        fillTableNVLam();
        fillTableNVNghi();
        clearNV();
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnCapNhatNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNVActionPerformed
        // TODO add your handling code here:

        NhanVien nvm = getNVForm();
        if (nvm == null) {
            return;
        }

        String ma = txtMaNV.getText().trim();
        if (ma.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên muốn sửa?");
            return;
        }
        nvm.setMaNV(txtMaNV.getText());

        if (nvr.updateNV(nvm) != null) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại do trùng mã nhân viên");
        }
        fillTableNVLam();
        fillTableNVNghi();
        clearNV();
    }//GEN-LAST:event_btnCapNhatNVActionPerformed

    private void cbbLocCVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocCVItemStateChanged
        // TODO add your handling code here:
        if (cbbLocCV.getSelectedItem() != null) {
            String tenCV = cbbLocCV.getSelectedItem().toString();
            loadTimLamByCV(tenCV);
            loadTimNghiByCV(tenCV);
        } else {
            return;
        }
    }//GEN-LAST:event_cbbLocCVItemStateChanged

    private void btnTimSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSdtActionPerformed
        String sdt = txtTimKiemSDT.getText().trim();
        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại của nhân viên muốn tìm");
            txtTimKiemSDT.requestFocus();
            return;
        }

        loadTimLamBySdt(sdt);
        loadTimNghiBySdt(sdt);
    }//GEN-LAST:event_btnTimSdtActionPerformed

    private void tblNVLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVLamMouseClicked
        // TODO add your handling code here:
        try {
            int row = tblNVLam.getSelectedRow();
            if (row < 0) {
                return;
            }
            txtMaNV.setText(tblNVLam.getValueAt(row, 1).toString());
            txtHoVaTen.setText(tblNVLam.getValueAt(row, 2).toString());
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tblNVLam.getValueAt(row, 3).toString());
            txtNgaySinh.setDate(date);
            String gt = tblNVLam.getValueAt(row, 4).toString();
            if (gt.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtDiaChi.setText(tblNVLam.getValueAt(row, 5).toString());
            txtSDT.setText(tblNVLam.getValueAt(row, 6).toString());
            txtEmail.setText(tblNVLam.getValueAt(row, 7).toString());
            txtPass.setText(tblNVLam.getValueAt(row, 8).toString());
            String ChucVu = tblNVLam.getValueAt(row, 9).toString();
            cbbCV.getModel().setSelectedItem(getCV(row, ChucVu));

            String hinh = tblNVLam.getValueAt(row, 10).toString();

            ImageIcon icon = new ImageIcon(getClass().getResource("/AnhNV/" + hinh));
            Image img = icon.getImage();
            lblHinh.setIcon(new ImageIcon(img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH)));
            String tt = tblNVLam.getValueAt(row, 11).toString();
            if (tt.equalsIgnoreCase("Đi làm")) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiLam.setSelected(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tblNVLamMouseClicked

    private void tblNVNghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVNghiMouseClicked
        try {
            int row = tblNVNghi.getSelectedRow();
            if (row < 0) {
                return;
            }
            txtMaNV.setText(tblNVNghi.getValueAt(row, 1).toString());
            txtHoVaTen.setText(tblNVNghi.getValueAt(row, 2).toString());
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tblNVNghi.getValueAt(row, 3).toString());
            txtNgaySinh.setDate(date);
            String gt = tblNVNghi.getValueAt(row, 4).toString();
            if (gt.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtDiaChi.setText(tblNVNghi.getValueAt(row, 5).toString());
            txtSDT.setText(tblNVNghi.getValueAt(row, 6).toString());
            txtEmail.setText(tblNVNghi.getValueAt(row, 7).toString());
            txtPass.setText(tblNVNghi.getValueAt(row, 8).toString());
            String chucvu = tblNVNghi.getValueAt(row, 9).toString();
            cbbCV.getModel().setSelectedItem(getCV(row, chucvu));
            String hinh = tblNVNghi.getValueAt(row, 10).toString();
            ImageIcon icon = new ImageIcon(getClass().getResource("/AnhNV/" + hinh));
            Image img = icon.getImage();
            lblHinh.setIcon(new ImageIcon(img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH)));
            String tt = tblNVNghi.getValueAt(row, 11).toString();
            if (tt.equalsIgnoreCase("Nghỉ Làm")) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiLam.setSelected(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tblNVNghiMouseClicked

    private void btnThemCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCVActionPerformed
        ChucVu cvm = getCVForm();
        String tenCV = txtTenCV.getText();
        ArrayList<ChucVu> ListCV = cvr.getAllCV();

        for (ChucVu cv : ListCV) {
            if (tenCV.equals(cv.getTenChucVu())) {
                JOptionPane.showMessageDialog(this, "Tên Chức Vụ Này Đã Tồn Tại !!");
                return;
            }
        }

        if (cvm == null) {
            return;
        }
        if (cvr.insertCV(cvm) != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadComboCVLoc();
            loadComboCV();
            fillTableNVLam();
            fillTableNVNghi();
            fillTableCV();
            clearCV();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại do trùng mã chức vụ");
        }
    }//GEN-LAST:event_btnThemCVActionPerformed

    private void btnSuaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCVActionPerformed
        // TODO add your handling code here:
        int row = tblChucVu.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
            return;
        }
        ChucVu cvm = getCVForm();
        if (cvm == null) {
            return;
        }
        cvm.setMaChucVu(tblChucVu.getValueAt(row, 0).toString());
        if (cvr.updateCV(cvm) != null) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            fillTableCV();
            loadComboCVTim();
            loadComboCVLoc();
            loadComboCV();
            clearCV();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnSuaCVActionPerformed

    private void tblChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVuMouseClicked
        // TODO add your handling code here:

        int row = tblChucVu.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtMaCV.setText(tblChucVu.getValueAt(row, 0).toString());
        txtTenCV.setText(tblChucVu.getValueAt(row, 1).toString());
        String tt = tblChucVu.getValueAt(row, 2).toString();
        if (tt.equalsIgnoreCase("còn")) {
            rdoCon.setSelected(true);
            JButton button = new JButton("Click me!");

        } else {
            rdoNghi.setSelected(true);
        }
    }//GEN-LAST:event_tblChucVuMouseClicked

    private void cbbCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCVActionPerformed

    private void btnClearCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearCVActionPerformed

    private void btnNextChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextChucVuActionPerformed
if (soTrang < SoBanGhiChucVu()) {
            soTrang++;
            fillTableCV();
            lblTrangChucVu.setText(soTrang + "/" + SoBanGhiChucVu());
        }
    }//GEN-LAST:event_btnNextChucVuActionPerformed

    private void btnPrChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrChucVuActionPerformed
        // TODO add your handling code here:
 if (soTrang > 1) {
            soTrang--;
            fillTableCV();
            lblTrangChucVu.setText(soTrang + "/" + SoBanGhiChucVu());

        }

    }//GEN-LAST:event_btnPrChucVuActionPerformed

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        // TODO add your handling code here:
        clearNV();
    }//GEN-LAST:event_btnClearMouseClicked

    private void btnPreLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreLamActionPerformed
        // TODO add your handling code here:
        if (soTrang > 1) {
            soTrang--;
            fillTableNVLam();
            lblTrangLam.setText(soTrang + "/" + SoBanGhiNhanVienLam());

        }
    }//GEN-LAST:event_btnPreLamActionPerformed

    private void btnNextLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextLamActionPerformed
        if (soTrang < SoBanGhiNhanVienLam()) {
            soTrang++;
            fillTableNVLam();
            lblTrangNghi.setText(soTrang + "/" + SoBanGhiNhanVienLam());
        }
    }//GEN-LAST:event_btnNextLamActionPerformed

    private void btnPreNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreNghiActionPerformed
        // TODO add your handling code here:
        if (soTrang > 1) {
            soTrang--;
            fillTableNVNghi();
            lblTrangNghi.setText(soTrang + "/" + SoBanGhiNhanVienNghi());

        }
    }//GEN-LAST:event_btnPreNghiActionPerformed

    private void btnNextNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextNghiActionPerformed
        // TODO add your handling code here:
        if (soTrang < SoBanGhiNhanVienNghi()) {
            soTrang++;
            fillTableNVNghi();
            lblTrangNghi.setText(soTrang + "/" + SoBanGhiNhanVienNghi());
        }

    }//GEN-LAST:event_btnNextNghiActionPerformed

    private void lblShowPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowPassMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblShowPassMouseClicked

    private void lblShowPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowPassMouseEntered
        // TODO add your handling code here:
        txtPass.setEchoChar((char)0);
    }//GEN-LAST:event_lblShowPassMouseEntered

    private void lblShowPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowPassMouseExited
        // TODO add your handling code here:
                txtPass.setEchoChar('\u25cf');

    }//GEN-LAST:event_lblShowPassMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatNV;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearCV;
    private javax.swing.JButton btnNextChucVu;
    private javax.swing.JButton btnNextLam;
    private javax.swing.JButton btnNextNghi;
    private javax.swing.JButton btnPrChucVu;
    private javax.swing.JButton btnPreLam;
    private javax.swing.JButton btnPreNghi;
    private javax.swing.JButton btnSuaCV;
    private javax.swing.JButton btnThemCV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnTimSdt;
    private javax.swing.JButton btnUpAnh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbbCV;
    private javax.swing.JComboBox<String> cbbLocCV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JButton lblShowPass;
    private javax.swing.JLabel lblTrangChucVu;
    private javax.swing.JLabel lblTrangLam;
    private javax.swing.JLabel lblTrangNghi;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoDiLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoNghiLam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblChucVu;
    private javax.swing.JTable tblNVLam;
    private javax.swing.JTable tblNVNghi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtMaCV;
    private javax.swing.JTextField txtMaNV;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenCV;
    private javax.swing.JTextField txtTimKiemSDT;
    // End of variables declaration//GEN-END:variables

    private void clearCV() {
        txtMaCV.setText("");
        txtTenCV.setText("");
        rdoCon.setSelected(false);
        rdoNghi.setSelected(false);

    }

    private void clearNV() {
        txtMaNV.setText("");
        txtHoVaTen.setText("");
        txtNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtPass.setText("");
        rdoNam.setSelected(true);
        rdoDiLam.setSelected(true);
        cbbCV.setSelectedItem(null);
        cbbLocCV.setSelectedItem(null);
        lblHinh.setIcon(new ImageIcon("src\\AnhNV\\hinh_nv025.jpg"));
    }

    private ChucVu getCVForm() {
        String ma = txtMaCV.getText().trim();
        String ten = txtTenCV.getText().trim();
        int tt = rdoCon.isSelected() == true ? 0 : 1;
        if (ma.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã chức vụ");
            txtMaCV.requestFocus();
            return null;
        }
        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên chức vụ");
            txtTenCV.requestFocus();
            return null;
        }
        return new ChucVu(ma, ten, tt);
    }

    private void loadComboCVLoc() {
        ArrayList<ChucVu> list = cvr.getAllCV();
        cbbLocCV.removeAllItems();
        for (ChucVu x : list) {
            dcmCVLoc.addElement(x.getTenChucVu());

        }
    }

    private void loadComboCV() {
        ArrayList<ChucVu> list = cvr.getAllCV();
        cbbCV.removeAllItems();
        for (ChucVu x : list) {
            dcmCV.addElement(x);

        }
    }

    private NhanVien getNVForm() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = txtNgaySinh.getDate();

        if (date == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày sinh nhân viên");
            txtNgaySinh.requestFocus();
            return null;
        }

        ArrayList<NhanVien> listNV = nvr.getAllNV();
        String ma = "NV" + (listNV.size() + 1);
        String ten = txtHoVaTen.getText().trim();
        String gt = rdoNam.isSelected() ? "Nam" : "Nữ";
        String dchi = txtDiaChi.getText().trim();
        String sdt = txtSDT.getText().trim();
        String email = txtEmail.getText().trim();
        String mk = String.valueOf(txtPass.getPassword());
        ChucVu cv = (ChucVu) cbbCV.getModel().getSelectedItem();
        int tt = rdoDiLam.isSelected() ? 1 : 0;

        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống họ tên nhân viên");
            txtHoVaTen.requestFocus();
            return null;
        }

        if (dchi.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống địa chỉ nhân viên");
            txtDiaChi.requestFocus();
            return null;
        }

        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống số điện thoại nhân viên");
            txtDiaChi.requestFocus();
            return null;
        } else {
            try {
                int dienthoai = Integer.parseInt(txtSDT.getText());
                String ktsdt = "0\\d{9}";
                if (!txtSDT.getText().matches(ktsdt)) {
                    JOptionPane.showMessageDialog(this, "Bạn nhập sai số điện thoại");
                    txtSDT.requestFocus();
                    return null;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại");
                txtSDT.requestFocus();
                e.printStackTrace();
                return null;
            }
        }

        if (email.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống email nhân viên");
            txtEmail.requestFocus();
            return null;
        } else {
            String ktemail = "\\w+@\\w+(\\.\\w+){1,2}";
            if (!email.matches(ktemail)) {
                JOptionPane.showMessageDialog(this, "Sai định dạng email");
                txtEmail.requestFocus();
                return null;
            }
        }

        if (mk.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
            txtPass.requestFocus();
            return null;
        }

        if (fileanh == null) {
            fileanh = "hinh1.jpg";
        }

        NhanVien nvm = new NhanVien(null, ma, ten, date, gt, dchi, sdt, email, mk, cv, fileanh, tt);

        return nvm;
    }

    private ChucVu getCV(int row, String tenCV) {
        ArrayList<ChucVu> cvm = cvr.getAllCV();
        for (ChucVu cm : cvm) {
            if (cm.getTenChucVu().equals(tenCV)) {
                return new ChucVu(cm.getIdchucVu(), cm.getMaChucVu(), cm.getTenChucVu(), cm.getTrangThai());
            }
        }
        return null;
    }

    private void loadComboCVTim() {
        ArrayList<ChucVu> list = cvr.getTenCV();
        for (ChucVu cv : list) {
            dcmCVM.addElement(new ChucVu(cv.getIdchucVu(), cv.getMaChucVu(), cv.getTenChucVu(), cv.getTrangThai()));

        }
    }

}
