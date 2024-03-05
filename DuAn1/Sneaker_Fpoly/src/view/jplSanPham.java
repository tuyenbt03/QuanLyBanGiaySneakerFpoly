/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Panel;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.accessibility.AccessibleContext;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import model.ChiTietSanPham;
import model.Hang;
import model.SanPham;
import repository.SPCTRepository;
import service.SPCTService;
import service.SanPhamService;
import utilitys.DBConnection;

/**
 *
 * @author LENOVO
 */
public class jplSanPham extends javax.swing.JPanel {

    private SPCTService serviceSPCT = new SPCTService();
    private SanPhamService service = new SanPhamService();
    
    private List<ChiTietSanPham> listSPCT = new ArrayList<>();
    private List<SanPham> listSP = new ArrayList<>();

    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;

    private DefaultComboBoxModel dcbm;

    private String pathImage = null;

    private List<String> listMaSP = service.listMaSP();
    private List<String> listTenSP = service.listTenSP();
    private List<String> listTenDanhMuc = serviceSPCT.getListDanhMuc();
    private List<String> listTenChatLieu = serviceSPCT.getListChatLieu();
    private List<String> listTenHang = serviceSPCT.getListHang();
    private List<String> listTenSize = serviceSPCT.getListSize();
    private List<String> listTenMauSac = serviceSPCT.getListMauSac();

    ArrayList<SanPham> _list = (ArrayList<SanPham>) this.service.getAll();

    private int soTrang3 = 1;
    private int page = 3;
    
    public jplSanPham() {

        initComponents();
        this.loadTableSP();

        this.loadCbb();
        dtm2 = (DefaultTableModel) tblBangCTSP.getModel();
        loadTableCTSP();
        cbbTenSP.setEditable(true);
        System.out.println(""+SoTrangSanPham());
    }

    private void loadTableSP() {
        ArrayList<SanPham> list = (ArrayList<SanPham>) this.service.getAllSanPham(soTrang3);
        dtm = (DefaultTableModel) this.tblBangSP.getModel();
        dtm.setRowCount(0);
        for (SanPham sp : list) {
            dtm.addRow(new Object[]{
                sp.getId(),
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getNgayTao(),
                sp.getNgaySua(),
                sp.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
            });
        }
        lbSoTrangSP.setText(soTrang3 + "/" + SoTrangSanPham());
        tblBangSP.setModel(dtm);
        
    }
    
    public int SoTrangSanPham() {
        int soBanGhi = service.SoBanGhiSanPham(); // Lấy số bản ghi từ service
        int soBanGhiMoiTrang = 5; // Số bản ghi mỗi trang
        int tongTrang = (int) Math.ceil((double) soBanGhi / soBanGhiMoiTrang);
        return tongTrang;
    }

    private void loadTableCTSP() {
        ArrayList<ChiTietSanPham> listCtsp = (ArrayList<ChiTietSanPham>) this.serviceSPCT.getAll();
        dtm = (DefaultTableModel) this.tblBangCTSP.getModel();
        dtm.setRowCount(0);

        for (ChiTietSanPham spct : listCtsp) {
            dtm.addRow(new Object[]{
                spct.getId(),
                spct.getSanPham(),
                spct.getGiaNhap(),
                spct.getGiaBan(),
                spct.getSoLuong(),
                spct.getHang(),
                spct.getChatLieu(),
                spct.getMauSac(),
                spct.getSize(),
                spct.getDanhMuc(),
                spct.getMoTa(),
                spct.getHinhAnh(),
                spct.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        JtabelPainelAll = new javax.swing.JTabbedPane();
        sanpham = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnCTSP = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rdoConSP = new javax.swing.JRadioButton();
        rdoHetSP = new javax.swing.JRadioButton();
        txtIdSanPham = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangSP = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        lbSoTrangSP = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        chitietsanphamPN = new javax.swing.JPanel();
        thongtinCTSP = new javax.swing.JPanel();
        txtGiaNhapSP = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnThemCTSP = new javax.swing.JButton();
        btnCapNhatSP = new javax.swing.JButton();
        txtGiaBanSP = new javax.swing.JTextField();
        txtSoLuongSP = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTaSP = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rdoConSP1 = new javax.swing.JRadioButton();
        rdoHetSP1 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        cbbSizeSP = new javax.swing.JComboBox<>();
        cbbHang = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cbbMauSacSP = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbDanhMucSP = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        cbbChatLieuSP = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbbTenSP = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtIDSP = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnUpload = new javax.swing.JButton();
        ImgAnh = new javax.swing.JLabel();
        themHang = new javax.swing.JButton();
        themChatLieu = new javax.swing.JButton();
        themMauSac = new javax.swing.JButton();
        themSize = new javax.swing.JButton();
        themDanhMuc = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBangCTSP = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbbLocDM = new javax.swing.JComboBox<>();
        txtTimkiemCTSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbLocHang = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbbLocCL = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbbLocMS = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbLocSize = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jLabel1.setText("Mã SP");

        jLabel2.setText("Tên SP");

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

        btnCTSP.setText("Xem chi tiết sản phẩm");
        btnCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCTSPMouseClicked(evt);
            }
        });
        btnCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
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
                .addComponent(btnCTSP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Trạng thái");

        buttonGroup3.add(rdoConSP);
        rdoConSP.setSelected(true);
        rdoConSP.setText("Đang còn hàng");

        buttonGroup3.add(rdoHetSP);
        rdoHetSP.setText("Hết hàng");

        txtIdSanPham.setForeground(new java.awt.Color(255, 51, 51));
        txtIdSanPham.setEnabled(false);

        jLabel11.setText("ID");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP)
                            .addComponent(txtIdSanPham)
                            .addComponent(txtTenSP)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoConSP, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(rdoHetSP, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
                .addGap(155, 155, 155)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(298, 298, 298))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdSanPham)
                    .addComponent(jLabel11))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSP)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP)
                    .addComponent(jLabel2))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoHetSP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoConSP, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel13.setText("Tìm  tên sản phẩm");

        jButton2.setText("Tìm kiếm ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tblBangSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã Sản Phẩm", "Tên Sản Phẩm", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"
            }
        ));
        tblBangSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBangSP);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton4.setText("||<");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lbSoTrangSP.setText("Số Trang");

        jButton5.setText(">||");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(29, 29, 29)
                .addComponent(lbSoTrangSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(lbSoTrangSP)
                    .addComponent(jButton5))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sanphamLayout = new javax.swing.GroupLayout(sanpham);
        sanpham.setLayout(sanphamLayout);
        sanphamLayout.setHorizontalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sanphamLayout.createSequentialGroup()
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(sanphamLayout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sanphamLayout.setVerticalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanphamLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        JtabelPainelAll.addTab("Sản phẩm ", sanpham);

        chitietsanphamPN.setPreferredSize(new java.awt.Dimension(2000, 579));

        thongtinCTSP.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm"));

        jLabel14.setText("Số Lượng");

        jLabel15.setText("Giá Bán");

        jLabel16.setText("Mô Tả");

        jLabel17.setText("Giá nhập");

        btnThemCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Save as.png"))); // NOI18N
        btnThemCTSP.setText("Thêm");
        btnThemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTSPActionPerformed(evt);
            }
        });

        btnCapNhatSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Gear.png"))); // NOI18N
        btnCapNhatSP.setText("Sửa");
        btnCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPActionPerformed(evt);
            }
        });

        txtMoTaSP.setColumns(20);
        txtMoTaSP.setRows(5);
        jScrollPane3.setViewportView(txtMoTaSP);

        jLabel18.setText("Hãng");

        jLabel19.setText("Trạng Thái");

        buttonGroup2.add(rdoConSP1);
        rdoConSP1.setSelected(true);
        rdoConSP1.setText("Còn Hàng");

        buttonGroup2.add(rdoHetSP1);
        rdoHetSP1.setText("Hết Hàng");

        jLabel12.setText("Size");

        cbbSizeSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbSizeSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbSizeSPMouseClicked(evt);
            }
        });

        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbHangMouseClicked(evt);
            }
        });
        cbbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHangActionPerformed(evt);
            }
        });

        jLabel20.setText("Màu Sắc");

        cbbMauSacSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbMauSacSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbMauSacSPMouseClicked(evt);
            }
        });

        jLabel21.setText("Chất Liệu");

        cbbDanhMucSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbDanhMucSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbDanhMucSPMouseClicked(evt);
            }
        });

        jLabel24.setText("Danh Mục");

        cbbChatLieuSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbChatLieuSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbChatLieuSPMouseClicked(evt);
            }
        });

        jLabel7.setText("Tên SP");

        cbbTenSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbTenSPMouseClicked(evt);
            }
        });

        jLabel6.setText("IDSP");

        txtIDSP.setForeground(new java.awt.Color(255, 51, 51));
        txtIDSP.setEnabled(false);

        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Open folder.png"))); // NOI18N
        btnUpload.setText("UpLoad");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        ImgAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImgAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ImgAnh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ImgAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(ImgAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        themHang.setText("+");
        themHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themHangActionPerformed(evt);
            }
        });

        themChatLieu.setText("+");
        themChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themChatLieuActionPerformed(evt);
            }
        });

        themMauSac.setText("+");
        themMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themMauSacActionPerformed(evt);
            }
        });

        themSize.setText("+");
        themSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themSizeActionPerformed(evt);
            }
        });

        themDanhMuc.setText("+");
        themDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themDanhMucActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Refresh.png"))); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout thongtinCTSPLayout = new javax.swing.GroupLayout(thongtinCTSP);
        thongtinCTSP.setLayout(thongtinCTSPLayout);
        thongtinCTSPLayout.setHorizontalGroup(
            thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thongtinCTSPLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thongtinCTSPLayout.createSequentialGroup()
                                    .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSoLuongSP)
                                        .addComponent(txtGiaNhapSP)
                                        .addComponent(txtIDSP, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cbbTenSP, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtGiaBanSP))
                                    .addGap(32, 32, 32))))
                        .addGroup(thongtinCTSPLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(196, 196, 196)))
                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, thongtinCTSPLayout.createSequentialGroup()
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                        .addComponent(cbbDanhMucSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(themDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbbSizeSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbMauSacSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(themMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(themSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(39, 39, 39))
                            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbChatLieuSP, 0, 140, Short.MAX_VALUE)
                                    .addComponent(cbbHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(themChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(themHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)))
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane3))
                            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(31, 31, 31))
                                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                        .addComponent(btnThemCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                        .addGap(34, 34, 34)))
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                        .addComponent(btnCapNhatSP, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                        .addGap(43, 43, 43)
                                        .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                        .addComponent(rdoConSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoHetSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(11, 11, 11))))))
                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        thongtinCTSPLayout.setVerticalGroup(
            thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIDSP)
                                .addComponent(cbbHang)
                                .addComponent(jLabel18)
                                .addComponent(themHang, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6)))
                        .addGap(19, 19, 19)
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7))
                            .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(cbbChatLieuSP)
                                .addComponent(themChatLieu)))
                        .addGap(18, 18, 18)
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cbbMauSacSP)
                            .addComponent(themMauSac)
                            .addComponent(txtGiaNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtGiaBanSP)
                            .addComponent(jLabel12)
                            .addComponent(cbbSizeSP)
                            .addComponent(themSize))
                        .addGap(19, 19, 19)
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtSoLuongSP)
                            .addComponent(jLabel24)
                            .addComponent(cbbDanhMucSP)
                            .addComponent(themDanhMuc))
                        .addGap(100, 100, 100))
                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(129, 129, 129))
                            .addGroup(thongtinCTSPLayout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(36, 36, 36)
                                .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoHetSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdoConSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(thongtinCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addGroup(thongtinCTSPLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );

        tblBangCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID CTSP", "Tên SP", "Giá Nhập", "Giá Bán", "Số Lượng", "Hãng", "Chất Liệu", "Màu Sắc", "Size", "Danh Mục", "Mô Tả", "Hình", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangCTSPMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblBangCTSP);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc chi tiết sản phẩm"));

        jLabel8.setText("Danh mục");

        cbbLocDM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbLocDM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocDMItemStateChanged(evt);
            }
        });

        txtTimkiemCTSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemCTSPKeyReleased(evt);
            }
        });

        jLabel3.setText("Tìm kiếm theo tên Sản Phẩm");

        jLabel4.setText("Hãng");

        cbbLocHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbLocHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocHangItemStateChanged(evt);
            }
        });
        cbbLocHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbLocHangMouseClicked(evt);
            }
        });
        cbbLocHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbbLocHangKeyReleased(evt);
            }
        });

        jLabel10.setText("Chất liệu");

        cbbLocCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbLocCL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocCLItemStateChanged(evt);
            }
        });

        jLabel22.setText("Màu sắc");

        cbbLocMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbLocMS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocMSItemStateChanged(evt);
            }
        });

        jLabel9.setText("Size");

        cbbLocSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbLocSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLocSizeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLocSize, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbLocHang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbLocCL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cbbLocDM, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimkiemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbLocMS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(88, 88, 88))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cbbLocHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(cbbLocCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(cbbLocMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimkiemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 58, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbbLocSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbLocDM))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("||<");

        jButton3.setText(">||");

        jLabel23.setText("Số Trang");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jLabel23))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout chitietsanphamPNLayout = new javax.swing.GroupLayout(chitietsanphamPN);
        chitietsanphamPN.setLayout(chitietsanphamPNLayout);
        chitietsanphamPNLayout.setHorizontalGroup(
            chitietsanphamPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chitietsanphamPNLayout.createSequentialGroup()
                .addGroup(chitietsanphamPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thongtinCTSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(chitietsanphamPNLayout.createSequentialGroup()
                .addGap(471, 471, 471)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chitietsanphamPNLayout.setVerticalGroup(
            chitietsanphamPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chitietsanphamPNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(thongtinCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        JtabelPainelAll.addTab("Chi tiết sản phẩm", chitietsanphamPN);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JtabelPainelAll, javax.swing.GroupLayout.DEFAULT_SIZE, 1153, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JtabelPainelAll)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkTrongSP()) {
            if (checkMaSP()) {
                if (checkTenSP()) {
                    UUID id = UUID.randomUUID();
                    String maSP = this.txtMaSP.getText();
                    String tenSP = this.txtTenSP.getText();
                    int trangThai = 1;
                    if (rdoHetSP.isSelected()) {
                        trangThai = 0;
                    }

                    SanPham sp = new SanPham(String.valueOf(id), maSP, tenSP, trangThai);

                    boolean a = this.service.insert(sp);

                    if (a == true) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        loadTableSP();
                        loadCbb();
                        loadTableCTSP();
                        this.clearSP();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                        return;
                    }

                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int index = tblBangSP.getSelectedRow();
        if (index >= 0) {
            listSP = service.getAll();
            int trangThai = 1;
            if (rdoHetSP.isSelected()) {
                trangThai = 0;
            }
            String ma = txtMaSP.getText();
            String ten = txtTenSP.getText();

            SanPham sp = new SanPham();
            sp.setMaSanPham(ma);
            sp.setTenSanPham(ten);
            sp.setTrangThai(trangThai);

            if (ma.equalsIgnoreCase(sp.getMaSanPham()) && ten.equalsIgnoreCase(sp.getTenSanPham())) {
                if (checkTrongSP()) {
                    service.update(sp);
                    this.loadTableSP();
                    JOptionPane.showMessageDialog(this, "Sửa thành công");

                } else {
                    JOptionPane.showMessageDialog(this, "Không được để trống");

                }
            } else if (!ma.equalsIgnoreCase(sp.getMaSanPham())) {
                if (checkTrongSP()) {
                    if (checkMaSP()) {
                        service.update(sp);
                        this.loadTableSP();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");

                    } else {
                        JOptionPane.showMessageDialog(this, "Mã sản phẩm bị trùng");
                    }
                }
            } else if (!ten.equalsIgnoreCase(sp.getTenSanPham())) {
                if (checkTrongSP()) {
                    if (checkTenSP()) {
                        service.update(sp);
                        this.loadTableSP();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");

                    } else {
                        JOptionPane.showMessageDialog(this, "Tên sản phẩm bị trùng");

                    }
                }
            } else {
                if (checkTrongSP()) {
                    if (checkMaSP()) {
                        if (checkTenSP()) {
                            service.update(sp);
                            this.loadTableSP();
                        }
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.clearSP();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCTSPMouseClicked
        JtabelPainelAll.setSelectedIndex(1);
        dtm2.setRowCount(0);
        List<SanPham> lstSP = service.getAllSanPham(soTrang3);
        int selectedRow = tblBangSP.getSelectedRow();
        String idSP = lstSP.get(selectedRow).getId();
        txtIDSP.setText(idSP);
        SanPham sp = service.getSanPhamByID(idSP);
        cbbTenSP.setSelectedItem(sp.getTenSanPham());
        ArrayList<ChiTietSanPham> ctsp = serviceSPCT.getSPCTByIDSP(idSP);
        for (ChiTietSanPham chiTietSanPham : ctsp) {
            dtm2.addRow(new Object[]{
                chiTietSanPham.getId(),
                chiTietSanPham.getSanPham(),
                chiTietSanPham.getGiaNhap(),
                chiTietSanPham.getGiaBan(),
                chiTietSanPham.getSoLuong(),
                chiTietSanPham.getHang(),
                chiTietSanPham.getChatLieu(),
                chiTietSanPham.getMauSac(),
                chiTietSanPham.getSize(),
                chiTietSanPham.getDanhMuc(),
                chiTietSanPham.getMoTa(),
                chiTietSanPham.getHinhAnh(),
                chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
            });
        }
        tblBangCTSP.setModel(dtm2);
    }//GEN-LAST:event_btnCTSPMouseClicked

    private void btnCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTSPActionPerformed

    }//GEN-LAST:event_btnCTSPActionPerformed


    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String sp = txtTimKiem.getText().trim();
        listSP = service.timKiem(sp);
        dtm = (DefaultTableModel) tblBangSP.getModel();
        showDataTablePT(listSP);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblBangSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangSPMouseClicked

    int index = tblBangSP.getSelectedRow();
            if (index == -1) {
                return;
            }

            try {
                if (index >= 0) {
                   
                    String id = tblBangSP.getValueAt(index, 0).toString();
                    String ma = tblBangSP.getValueAt(index, 1).toString();
                    String ten = tblBangSP.getValueAt(index, 2).toString();
                    String trangThai = tblBangSP.getValueAt(index, 5).toString();
                    txtIdSanPham.setText(id);
                    txtMaSP.setText(ma);
                    txtTenSP.setText(ten);
                    if (trangThai.equals("Đang còn hàng")) {
                        rdoConSP.setSelected(true);
                    } else {
                        rdoHetSP.setSelected(true);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_tblBangSPMouseClicked

    private void txtTimkiemCTSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemCTSPKeyReleased

        String timKiem = txtTimkiemCTSP.getText().trim();
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("tensanpham" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getMoTa(),
                    chiTietSanPham.getHinhAnh(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblBangCTSP.setModel(dtm2);
        } else {
            loadTableCTSP();
        }
    }//GEN-LAST:event_txtTimkiemCTSPKeyReleased

    private void tblBangCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangCTSPMouseClicked
        int index = tblBangCTSP.getSelectedRow();
        if (index == -1) {
            return;
        }

        try {
            if (index >= 0) {
                String anh = tblBangCTSP.getValueAt(index, 11).toString();
                ImageIcon imageIcon = new ImageIcon(anh);
                ImgAnh.setIcon(imageIcon);
                String id = tblBangCTSP.getValueAt(index, 0).toString();
                String sanPham = tblBangCTSP.getValueAt(index, 1).toString();
                String giaNhap = tblBangCTSP.getValueAt(index, 2).toString();
                String giaBan = tblBangCTSP.getValueAt(index, 3).toString();
                String soLuong = tblBangCTSP.getValueAt(index, 4).toString();
                String hang = tblBangCTSP.getValueAt(index, 5).toString();
                String chatLieu = tblBangCTSP.getValueAt(index, 6).toString();
                String mauSac = tblBangCTSP.getValueAt(index, 7).toString();
                String size = tblBangCTSP.getValueAt(index, 8).toString();
                String danhMuc = tblBangCTSP.getValueAt(index, 9).toString();
                String moTa = tblBangCTSP.getValueAt(index, 10).toString();
                String trangThai = tblBangCTSP.getValueAt(index, 12).toString();
                txtIDSP.setText(id);
                cbbTenSP.setSelectedItem(sanPham.toString());
                txtGiaNhapSP.setText(giaNhap);
                txtGiaBanSP.setText(giaBan);
                txtSoLuongSP.setText(soLuong);
                cbbHang.setSelectedItem(hang.toString());
                cbbChatLieuSP.setSelectedItem(chatLieu.toString());
                cbbMauSacSP.setSelectedItem(mauSac.toString());
                cbbSizeSP.setSelectedItem(size.toString());
                cbbDanhMucSP.setSelectedItem(danhMuc.toString());
                txtMoTaSP.setText(moTa);

                if (trangThai.equals("Đang còn hàng")) {
                    rdoConSP1.setSelected(true);
                } else {
                    rdoHetSP1.setSelected(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tblBangCTSPMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearCTSP();
        loadTableCTSP();
    }//GEN-LAST:event_btnClearActionPerformed

    private void themDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themDanhMucActionPerformed
       try {
            new JplDanhMuc(this).setVisible(true);
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_themDanhMucActionPerformed

    private void themSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themSizeActionPerformed
          try {
            new JplSize(this).setVisible(true);
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_themSizeActionPerformed

    private void themMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themMauSacActionPerformed
       try {
            new JplMauSac(this).setVisible(true);
        } catch (Exception ex) {
            
        }
         
    }//GEN-LAST:event_themMauSacActionPerformed

    private void themChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themChatLieuActionPerformed
            try {
            new jplChatLieu(this).setVisible(true);
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_themChatLieuActionPerformed

    private void themHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themHangActionPerformed
             try {
            new jplHang(this).setVisible(true);
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_themHangActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        try {
            JFileChooser fchooser = new JFileChooser("C:\\Users\\DELL\\Desktop\\tinh\\Fpt_PolytechnicTaiNguyen\\Spring2024\\Sneaker_FpolyTuyen\\Sneaker_FpolyTuyen\\src\\utility\\img");
            int choosed = fchooser.showOpenDialog(this.getRootPane());
            if (choosed != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File f = fchooser.getSelectedFile();
            if (!f.exists()) {
                JOptionPane.showMessageDialog(this, "Ảnh không tồn tại");
                return;
            }
            ImageIcon icon = new ImageIcon(f.getAbsolutePath());
            this.pathImage = f.getAbsolutePath();
            this.ImgAnh.setIcon(icon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void cbbChatLieuSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbChatLieuSPMouseClicked
        cbbChatLieuSP.removeAllItems();
        loadCbb();
    }//GEN-LAST:event_cbbChatLieuSPMouseClicked

    private void cbbDanhMucSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbDanhMucSPMouseClicked
        cbbDanhMucSP.removeAllItems();
        loadCbb();
    }//GEN-LAST:event_cbbDanhMucSPMouseClicked

    private void cbbMauSacSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbMauSacSPMouseClicked
        cbbMauSacSP.removeAllItems();
        loadCbb();
    }//GEN-LAST:event_cbbMauSacSPMouseClicked

    private void cbbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHangActionPerformed

    }//GEN-LAST:event_cbbHangActionPerformed

    private void cbbHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbHangMouseClicked
       cbbHang.removeAllItems();
        loadCbb();       
    }//GEN-LAST:event_cbbHangMouseClicked

    private void cbbSizeSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbSizeSPMouseClicked
        cbbSizeSP.removeAllItems();
        loadCbb();
    }//GEN-LAST:event_cbbSizeSPMouseClicked

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed

        listSPCT = serviceSPCT.getAll();
        if (checkDataCTSP()) {
            int cf = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa ?");

            if (cf == JOptionPane.YES_OPTION) {
                for (ChiTietSanPham x : listSPCT) {
                    if (x.getId().equals(txtIDSP.getText())) {
                        pathImage = String.valueOf(ImgAnh.getIcon());
                        System.out.println(""+pathImage);
                    }
                }

                String id = txtIDSP.getText();
                BigDecimal giaNhap = new BigDecimal(txtGiaNhapSP.getText());
                BigDecimal giaBan = new BigDecimal(txtGiaBanSP.getText());
                String sanPham = serviceSPCT.getIDBySanPham(cbbTenSP.getSelectedItem().toString());
                String danhMuc = serviceSPCT.getIDByDanhMuc(cbbDanhMucSP.getSelectedItem().toString());
                String chatLieu = serviceSPCT.getIDByChatLieu(cbbChatLieuSP.getSelectedItem().toString());
                String mauSac = serviceSPCT.getIDByMauSac(cbbMauSacSP.getSelectedItem().toString());
                String size = serviceSPCT.getIDBySize(cbbSizeSP.getSelectedItem().toString());
                String hang = serviceSPCT.getIDByHang(cbbHang.getSelectedItem().toString());
                int soLuong = Integer.parseInt(txtSoLuongSP.getText());
                String moTa = txtMoTaSP.getText();
                int trangThai;
                if (rdoConSP1.isSelected() == true) {
                    trangThai = 1;
                } else {
                    trangThai = 0;
                }

                ChiTietSanPham ctsp = new ChiTietSanPham();

                ctsp.setHinhAnh(pathImage);
                ctsp.setGiaBan(giaBan);
                ctsp.setGiaNhap(giaNhap);
                ctsp.setChatLieu(chatLieu);
                ctsp.setDanhMuc(danhMuc);
                ctsp.setHang(hang);
                ctsp.setMauSac(mauSac);
                ctsp.setMoTa(moTa);
                ctsp.setSanPham(sanPham);
                ctsp.setSize(size);
                ctsp.setSoLuong(soLuong);
                ctsp.setTrangThai(trangThai);
                ctsp.setId(id);

               
                    if (serviceSPCT.update(ctsp)) {
                        this.loadCbb();
                        this.loadTableCTSP();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                    }
                
                this.clearCTSP();

            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed

        if (checkDataCTSP()) {
            int cf = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm ?");
            if (cf == JOptionPane.YES_OPTION) {
                if (checkDataCTSP()) {

                    String tenSP = serviceSPCT.getIDBySanPham(cbbTenSP.getSelectedItem().toString());
                    String tenDamhMuc = serviceSPCT.getIDByDanhMuc(cbbDanhMucSP.getSelectedItem().toString());
                    String tenChatLieu = serviceSPCT.getIDByChatLieu(cbbChatLieuSP.getSelectedItem().toString());
                    String tenHang = serviceSPCT.getIDByHang(cbbHang.getSelectedItem().toString());
                    String tenSize = serviceSPCT.getIDBySize(cbbSizeSP.getSelectedItem().toString());
                    String tenMauSac = serviceSPCT.getIDByMauSac(cbbMauSacSP.getSelectedItem().toString());
                    BigDecimal giaNhap = new BigDecimal(txtGiaNhapSP.getText());
                    BigDecimal giaBan = new BigDecimal(txtGiaBanSP.getText());
                    int trangThai;
                    if (rdoConSP1.isSelected() == true) {
                        trangThai = 1;
                    } else {
                        trangThai = 0;
                    }
                    String moTa = txtMoTaSP.getText();
                    String soLuong = txtSoLuongSP.getText();
                    String anh = this.pathImage;

                    ChiTietSanPham ctsp = new ChiTietSanPham(null, String.valueOf(tenSP), giaNhap, giaBan, Integer.parseInt(soLuong), tenHang, tenChatLieu, tenMauSac, tenSize, tenDamhMuc, moTa, anh, trangThai);

                    if (CheckTrung(ctsp) == true) {
                        JOptionPane.showMessageDialog(this, "San pham da ton tai");
                        return;
                    } else {
                        if (serviceSPCT.InsertCTSP(ctsp)) {
                            this.loadTableCTSP();
                            JOptionPane.showMessageDialog(this, "Thêm thành công");
                        }
                    }

                    this.pathImage = null;
                    this.clearCTSP();
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnThemCTSPActionPerformed

    private void cbbLocHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbLocHangKeyReleased
       
    }//GEN-LAST:event_cbbLocHangKeyReleased

    private void cbbLocHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbLocHangMouseClicked
     
    }//GEN-LAST:event_cbbLocHangMouseClicked

    private void cbbLocHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocHangItemStateChanged
        String timKiem = (String) cbbLocHang.getSelectedItem();
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemHangCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Hang San Pham" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getMoTa(),
                    chiTietSanPham.getHinhAnh(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblBangCTSP.setModel(dtm2);
        } else {
            loadTableCTSP();

        }
    }//GEN-LAST:event_cbbLocHangItemStateChanged

    private void cbbLocCLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocCLItemStateChanged
        String timKiem = (String) cbbLocCL.getSelectedItem();
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemChatLieuCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Chat Lieu: " + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getMoTa(),
                    chiTietSanPham.getHinhAnh(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblBangCTSP.setModel(dtm2);
        } else {
            loadTableCTSP();
        }
    }//GEN-LAST:event_cbbLocCLItemStateChanged

    private void cbbLocMSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocMSItemStateChanged
        String timKiem = (String) cbbLocMS.getSelectedItem();
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemMauSacCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Mau Sac" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getMoTa(),
                    chiTietSanPham.getHinhAnh(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblBangCTSP.setModel(dtm2);
        } else {
            loadTableCTSP();
        }
    }//GEN-LAST:event_cbbLocMSItemStateChanged

    private void cbbLocSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocSizeItemStateChanged
        String timKiem = (String) cbbLocSize.getSelectedItem();
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemSizeCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Size " + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getMoTa(),
                    chiTietSanPham.getHinhAnh(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblBangCTSP.setModel(dtm2);
        } else {
            loadTableCTSP();

        }
    }//GEN-LAST:event_cbbLocSizeItemStateChanged

    private void cbbLocDMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLocDMItemStateChanged
        String timKiem = (String) cbbLocDM.getSelectedItem();
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemDanhMucCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Danh Muc" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getMoTa(),
                    chiTietSanPham.getHinhAnh(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblBangCTSP.setModel(dtm2);
        } else {
            loadTableCTSP();
        }
    }//GEN-LAST:event_cbbLocDMItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (soTrang3 > 1) {
            soTrang3--;
            loadTableSP();
            lbSoTrangSP.setText(soTrang3 + "/" + SoTrangSanPham());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (soTrang3 < SoTrangSanPham()) {
            soTrang3++;
            loadTableSP();
            lbSoTrangSP.setText(soTrang3 + "/" + SoTrangSanPham());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbbTenSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbTenSPMouseClicked
        cbbTenSP.removeAllItems();
        loadCbb();
    }//GEN-LAST:event_cbbTenSPMouseClicked

    public void showDataTablePT(List<SanPham> lists) {
        dtm.setRowCount(0);
        for (SanPham pt : listSP) {
            dtm.addRow(pt.toDataRow());
        }
    }

    public boolean checkTrongSP() {
        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!");
            return false;
        } else if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkMaSP() {
        String ma = txtMaSP.getText().trim();
        for (String s : listMaSP) {
            if (ma.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public boolean checkTenSP() {
        String ten = txtTenSP.getText().trim();
        for (String s : listTenSP) {
            if (ten.equalsIgnoreCase(s)) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public void loadCbb() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM SanPham";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbTenSP.addItem(rs.getString("tenSanPham"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DanhMuc where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbDanhMucSP.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DanhMuc where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbLocDM.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM ChatLieu where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbChatLieuSP.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM ChatLieu where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbLocCL.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM MauSac where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbMauSacSP.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM MauSac where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbLocMS.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM Size where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbSizeSP.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM Size where TrangThai = 1 order by Ma";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbLocSize.addItem(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM hang where trangThai = 1 order by maHang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbHang.addItem(rs.getString("tenHang"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM hang where trangThai = 1 order by maHang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbbLocHang.addItem(rs.getString("tenHang"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean CheckTrung(ChiTietSanPham ctsp) {
        if (serviceSPCT.CheckTrung(ctsp) == 1) {
            return true;
        }
        return false;
    }

    public boolean checkDataCTSP() {

        if (txtGiaNhapSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá nhập không được để trống!");
            return false;
        } else if (txtGiaBanSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá Bán không không được để trống!");
            return false;
        } else if (txtSoLuongSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không không được để trống!");
            return false;
        } else if (txtMoTaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mô tả không không được để trống!");
            return false;
        } else if (ImgAnh.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "Ảnh không không được để trống!");
            return false;
        } else if (btnUpload.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ảnh không không được để trống!");
            return false;
        } else if (!checkNumber(txtSoLuongSP.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Nhập sai số lượng");
            return false;
        } else if (!checkNumber(txtGiaNhapSP.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Nhập sai giá nhập");
            return false;
        } else if (!checkNumber(txtGiaBanSP.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Nhập sai giá bán");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkNumber(String number) {
        String kytu = "\\d+";
        if (number.matches(kytu)) {
            if (Integer.valueOf(number) < 0) {
                JOptionPane.showMessageDialog(this, "Dự liệu không hợp lệ");
                return false;
            } else {
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!");
            return false;
        }
    }
    
        private void clearSP() {
        this.txtIdSanPham.setText("");
        this.txtMaSP.setText("");
        this.txtTenSP.setText("");
        this.rdoConSP.setSelected(true);
    }

    public void clearCTSP() {
        this.txtIDSP.setText("");
        this.txtGiaNhapSP.setText("");
        this.txtGiaBanSP.setText("");
        this.txtSoLuongSP.setText("");
        this.ImgAnh.setIcon(null);
        this.txtMoTaSP.setText("");
        this.cbbHang.setSelectedItem(null);
        this.cbbDanhMucSP.setSelectedItem(null);
        this.cbbChatLieuSP.setSelectedItem(null);
        this.cbbMauSacSP.setSelectedItem(null);
        this.cbbSizeSP.setSelectedItem(null);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImgAnh;
    private javax.swing.JTabbedPane JtabelPainelAll;
    private javax.swing.JButton btnCTSP;
    private javax.swing.JButton btnCapNhatSP;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnUpload;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbChatLieuSP;
    private javax.swing.JComboBox<String> cbbDanhMucSP;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbLocCL;
    private javax.swing.JComboBox<String> cbbLocDM;
    private javax.swing.JComboBox<String> cbbLocHang;
    private javax.swing.JComboBox<String> cbbLocMS;
    private javax.swing.JComboBox<String> cbbLocSize;
    private javax.swing.JComboBox<String> cbbMauSacSP;
    private javax.swing.JComboBox<String> cbbSizeSP;
    private javax.swing.JComboBox<String> cbbTenSP;
    private javax.swing.JPanel chitietsanphamPN;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbSoTrangSP;
    private javax.swing.JRadioButton rdoConSP;
    private javax.swing.JRadioButton rdoConSP1;
    private javax.swing.JRadioButton rdoHetSP;
    private javax.swing.JRadioButton rdoHetSP1;
    private javax.swing.JPanel sanpham;
    private javax.swing.JTable tblBangCTSP;
    private javax.swing.JTable tblBangSP;
    private javax.swing.JButton themChatLieu;
    private javax.swing.JButton themDanhMuc;
    private javax.swing.JButton themHang;
    private javax.swing.JButton themMauSac;
    private javax.swing.JButton themSize;
    private javax.swing.JPanel thongtinCTSP;
    private javax.swing.JTextField txtGiaBanSP;
    private javax.swing.JTextField txtGiaNhapSP;
    private javax.swing.JTextField txtIDSP;
    private javax.swing.JTextField txtIdSanPham;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMoTaSP;
    private javax.swing.JTextField txtSoLuongSP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimkiemCTSP;
    // End of variables declaration//GEN-END:variables

}
