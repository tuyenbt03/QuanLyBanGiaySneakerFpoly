/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.microsoft.sqlserver.jdbc.StringUtils;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.ChiTietHoaDon;
import model.ChiTietSanPham;
import model.GioHang;
import model.HDCT_CTSP;
import model.HoaDon;
import model.HoaDonCho;
import model.KhachHang;
import model.TraHang;
import repository.BanHangRepository;
import repository.HoaDonChiTietRepository;
import repository.HoaDonRepository;
import repository.HoadonChoRepository;
import repository.KhachHangRepository;
import repository.TraHangRepository;

/**
 *
 * @author Thuan
 */
public class jplTraHang1 extends javax.swing.JPanel {

    /**
     * Creates new form jplTraHang1
     */
    private int soTrang = 1;
    private int page = 4;
    private final HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    private final HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private final HoadonChoRepository hoadonChoRepository = new HoadonChoRepository();
    private final BanHangRepository banHangRepository = new BanHangRepository();
    DefaultTableModel model = new DefaultTableModel();
    TraHangRepository traHangRepository = new TraHangRepository();
    DefaultTableModel defaultTableModelHD = new DefaultTableModel();
    DefaultTableModel defaultTableModelHDCT = new DefaultTableModel();
    DefaultTableModel defaultTableModelHT = new DefaultTableModel();
    public ArrayList<HoaDon> _lstHoaDon = hoaDonRepository.getAllHoaDon(soTrang);

    Date ngayHienTai = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    LocalDate ngayHienTai1 = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    model.NhanVien nv = Login.nvs;

    public jplTraHang1() {
        initComponents();
        fillTableHD();

    }
   
    private void loadDataToTableTraHang() {
        model = (DefaultTableModel) tbl_HoanTra.getModel();
        int rowTH = tbl_HoanTra.getSelectedRow();
        
        int rowHD = tbl_HoaDon.getSelectedRow();
        String idhd = hoadonChoRepository.getIdHoaDonByMa(tbl_HoaDon.getValueAt(rowHD, 0).toString());
        ArrayList<TraHang> list = traHangRepository.getAllTraHang(idhd);
        model.setRowCount(0);
        for (TraHang th : list) {
            model.addRow(new Object[]{th.getMa(),th.getSoluong(),th.getNgayTra(),th.getTongtien()});
        }
    }
    public int SoBanGhiHoaDon() {

        int soBanGhi;
        soBanGhi = hoaDonRepository.SoBanGhi();
        int tongTrang = soBanGhi / page;
        return tongTrang;
    }

    public int SoBanGhiHoaDonChiTiet() {

        int soBanGhi = hoaDonChiTietRepository.SoBanGhiHoaDonChiTiet();
        int tongTrang = soBanGhi / page;
        return tongTrang;
    }

    private void loadDataToTableGioHang() {
        model = (DefaultTableModel) tbl_CTHD.getModel();
        int row = tbl_HoaDon.getSelectedRow();
        String mahd = tbl_HoaDon.getValueAt(row, 0).toString();
        String idhd = hoadonChoRepository.getIdHoaDonByMa(mahd);

        ArrayList<GioHang> list = hoadonChoRepository.getAllGioHang(idhd);
        model.setRowCount(0);
        for (GioHang gh : list) {
            model.addRow(new Object[]{gh.getId(), gh.getSanPham(), gh.getHang(), gh.getSize(), gh.getDanhMuc(), gh.getChatLieu(), gh.getMauSac(), gh.getGiaBan(), gh.getSoLuong(), gh.getDonGia()});
        }
    }

    public void fillTableHD() {

        ArrayList<HoaDon> _lstHoaDon = hoaDonRepository.getAllHoaDon(soTrang);
        defaultTableModelHD = (DefaultTableModel) tbl_HoaDon.getModel();
        defaultTableModelHD.setRowCount(0);
        for (HoaDon hoaDon : _lstHoaDon) {
            defaultTableModelHD.addRow(new Object[]{
                hoaDon.getMaHoaDon(),
                hoaDon.getIdKhachHang().getTenKhachHang() == null ? "Khachle" : hoaDon.getIdKhachHang().getTenKhachHang(),
                hoaDon.getNgayMua(),
                hoaDon.getThanhTien(),
                hoaDon.getIdNhanVien().getHoVaTen(),});
        }
        lb_trangHoaDon.setText(soTrang + "/" + SoBanGhiHoaDon());
        tbl_HoaDon.setModel(defaultTableModelHD);
    }

//    public void fillTableHDCT(String idHoaDon) {
//
//        ArrayList<HDCT_CTSP> _listHDCT = hoaDonChiTietRepository.getByIdHoaDon(idHoaDon);
//        defaultTableModelHDCT = (DefaultTableModel) tbl_CTHD.getModel();
//        defaultTableModelHDCT.setRowCount(0);
//        for (HDCT_CTSP HDCT : _listHDCT) {
//            defaultTableModelHDCT.addRow(new Object[]{
//                HDCT.getId(),
//                HDCT.getMaHoaDon(),
//                HDCT.getTenSanPham(),
//                HDCT.getTenHang(),
//                HDCT.getSize(),
//                HDCT.getMauSac(),
//                HDCT.getChatLieu(),
//                HDCT.getTenDanhMuc(),
//                HDCT.getSoLuong(),
//                HDCT.getDonGia(),});
//        }
//        tbl_CTHD.setModel(defaultTableModelHDCT);
//        lb_TrangCTSP.setText(soTrang + "/" + SoBanGhiHoaDonChiTiet());
//    }
    public void fillTableHT(String idHoaDon) {
        String ngayTraHangString = ngayHienTai1.format(formatter);
        LocalDate ngayTraHang = LocalDate.parse(ngayTraHangString, formatter);
        ArrayList<HDCT_CTSP> lisKhachHang = traHangRepository.getKhachHangByIdHoaDon(idHoaDon);
        defaultTableModelHT = (DefaultTableModel) tbl_HoanTra.getModel();
        defaultTableModelHT.setRowCount(0);
        ArrayList<HDCT_CTSP> list = new ArrayList<>();
        for (HDCT_CTSP HDCT : lisKhachHang) {
            defaultTableModelHT.addRow(new Object[]{
                HDCT.getMaHoaDon(),
                HDCT.getTenKhachHang(),
                ngayTraHang,
                HDCT.getDonGia()
            });
        }
        tbl_HoanTra.setModel(defaultTableModelHT);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupHDCt = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_HoaDon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_TimKiemHoaDon = new javax.swing.JTextField();
        bn_BackHD = new javax.swing.JButton();
        bn_nextHD = new javax.swing.JButton();
        lb_trangHoaDon = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_CTHD = new javax.swing.JTable();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtTienDaThanhToan = new javax.swing.JTextField();
        txtTongTienHoanTra = new javax.swing.JTextField();
        bn_HoanTra = new javax.swing.JButton();
        txtNgayTraHang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_HoanTra = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ TRẢ HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(513, 513, 513)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(574, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setMaximumSize(new java.awt.Dimension(100, 100));

        tbl_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Tên Khách Hàng", "Ngày Mua", "Thành Tiền", "Người Tạo"
            }
        ));
        tbl_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_HoaDon);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel2.setText("Tìm kiếm");

        bn_BackHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Left.png"))); // NOI18N

        bn_nextHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Right.png"))); // NOI18N

        lb_trangHoaDon.setText("jLabel3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(bn_BackHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_trangHoaDon)
                        .addGap(8, 8, 8)
                        .addComponent(bn_nextHD))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bn_BackHD)
                        .addComponent(bn_nextHD))
                    .addComponent(lb_trangHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbl_CTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Tên sản phẩm", "Hãng", "Size", "Danh mục", "Chất liệu", "Màu sắc", "Giá bán", "Số Lượng ", "Đơn Giá"
            }
        ));
        tbl_CTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CTHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_CTHD);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Chi Tiết Hóa Đơn", jPanel4);

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã Hóa Đơn");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên Khách Hàng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tiền Đã Thanh Toán");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Số lượng ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tổng Tiền Hoàn Trả");

        bn_HoanTra.setBackground(new java.awt.Color(102, 255, 102));
        bn_HoanTra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bn_HoanTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Refresh.png"))); // NOI18N
        bn_HoanTra.setText("Hoàn Trả");
        bn_HoanTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bn_HoanTraMouseClicked(evt);
            }
        });
        bn_HoanTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bn_HoanTraActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Ngày Trả Hàng");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienDaThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTienHoanTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNgayTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bn_HoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMaHoaDon, txtNgayTraHang, txtTenKhachHang, txtTienDaThanhToan, txtTongTienHoanTra});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTienDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTienHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bn_HoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMaHoaDon, txtNgayTraHang, txtTenKhachHang, txtTienDaThanhToan, txtTongTienHoanTra});

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbl_HoanTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã trả hàng", "Tên Nhân Viên", "Ngày Trả", "Tổng Tiền"
            }
        ));
        jScrollPane3.setViewportView(tbl_HoanTra);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Hoàn Trả", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_CTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CTHDMouseClicked
        int rowHD = tbl_HoaDon.getSelectedRow();
        int rowHDCT = tbl_CTHD.getSelectedRow();
        int sl = 0;
        if (rowHDCT <= -1) {
            JOptionPane.showMessageDialog(this, "ban phai chon 1 hoa don");
            return;
        }
        int cf = JOptionPane.showConfirmDialog(this,
                "Bạn chắc chắn muốn trả", "trả hàng", JOptionPane.YES_NO_OPTION);
        if (cf != JOptionPane.YES_OPTION) {
            return;
        }
        String slNhap = JOptionPane.showInputDialog(this, "Nhập số lượng muốn trả");
        if (slNhap == null) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng");
            return;
        }
        //Bắt buộc nhập số
        if (!StringUtils.isNumeric(slNhap)) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số và phải nguyên dương");
            return;
        }
        sl = Integer.parseInt(slNhap);
        if (sl <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải nguyên dương");
            return;
        }
        if (sl > Integer.parseInt(tbl_CTHD.getValueAt(rowHDCT, 8).toString())) {
            JOptionPane.showMessageDialog(this, "Số lượng không đủ");
            return;
        }
        String idCTHD = tbl_CTHD.getValueAt(rowHDCT, 0).toString();

        ArrayList<ChiTietSanPham> listCTSP = traHangRepository.getAllChiTietSanPham(idCTHD);
        for (ChiTietSanPham sp : listCTSP) {
            BigDecimal soLuongNhapBigDecimal = new BigDecimal(sl);
            BigDecimal dongia = new BigDecimal(sp.getGiaBan().toString());
            BigDecimal tongGia = dongia.multiply(soLuongNhapBigDecimal);
            if (txtTongTienHoanTra.getText().isEmpty() || txtTongTienHoanTra == null) {
                txtTongTienHoanTra.setText(tongGia.toString());
                txtSoLuong.setText(soLuongNhapBigDecimal.toString());
            } else {
//                BigDecimal tongTienHienTai = new BigDecimal(txtTongTienHoanTra.getText());
//                BigDecimal tongTienMoi = tongGia.add(tongTienHienTai);
//                txtTongTienHoanTra.setText(tongTienMoi.toString());
                JOptionPane.showMessageDialog(this, "đã tồn tại sản phẩm cần trả");
            }

        }

//        hdct.setSoLuong(sl);
//        String idctsp = tblSanPham.getValueAt(rowDSSP, 0).toString();
//        int soLuongChiTietSPUD = Integer.parseInt(tblSanPham.getValueAt(rowDSSP, 9).toString()) - sl;
//        BigDecimal thanhTien = dongia.multiply(soLuongNhapBigDecimal);
//        hdct.setDonGia(thanhTien

    }//GEN-LAST:event_tbl_CTHDMouseClicked

    private Boolean CheckTrung() {
        int rowSP = tbl_CTHD.getSelectedRow();
        int rowHD = tbl_HoaDon.getSelectedRow();
        String idctsp = tbl_CTHD.getValueAt(rowSP, 0).toString();
        String idHD = hoadonChoRepository.getIdHoaDonByMa(tbl_HoaDon.getValueAt(rowHD, 0).toString());
        if (hoadonChoRepository.checkTrungCtspAndGH(idHD, idctsp) == 0) {
            return false;
        } else {
            return true;
        }
    }
    private void tbl_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HoaDonMouseClicked
        int rowHD = tbl_HoaDon.getSelectedRow();
        loadDataToTableGioHang();
        String maHD = tbl_HoaDon.getValueAt(rowHD, 0).toString();
        ArrayList<HoaDon> hd = traHangRepository.getAllHoaDon(maHD);
        for (HoaDon h : hd) {
            txtTenKhachHang.setText(h.getIdKhachHang().getTenKhachHang() == null ? "khachle" : h.getIdKhachHang().getTenKhachHang());
            txtTienDaThanhToan.setText(h.getThanhTien().toString());
        }
        txtMaHoaDon.setText(tbl_HoaDon.getValueAt(rowHD, 0).toString());
        txtNgayTraHang.setText(maHD);
        LocalDate ngayHomNay = LocalDate.now();
        // Định dạng ngày theo mong muốn (ví dụ: "dd/MM/yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngayHomNayFormatted = ngayHomNay.format(formatter);
        
        // Đặt giá trị cho txtNgayTraHang
        txtNgayTraHang.setText(ngayHomNayFormatted);
         loadDataToTableGioHang();
//        txtTenKhachHang.setText();
    }//GEN-LAST:event_tbl_HoaDonMouseClicked

    private void bn_HoanTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bn_HoanTraMouseClicked
        int rowHD = tbl_HoaDon.getSelectedRow();
        int cf = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn trả", "Trả hàng", JOptionPane.YES_NO_OPTION);
        if (cf == JOptionPane.YES_OPTION) {
            // Chọn lý do trả hàng
            String[] lyDoTraHang = {"Hàng lỗi", "Không hài lòng"};
            JComboBox<String> comboBox = new JComboBox<>(lyDoTraHang);
            JPanel panel = new JPanel();
            panel.add(comboBox);

            int result = JOptionPane.showConfirmDialog(this, panel, "Chọn lý do trả hàng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String selectedReason = (String) comboBox.getSelectedItem();
                String ghiChu = JOptionPane.showInputDialog(this, "Ghi chú");
                TraHang trahang = new TraHang();
                String idHD = hoadonChoRepository.getIdHoaDonByMa(tbl_HoaDon.getValueAt(rowHD, 0).toString());
                KhachHangRepository khrp = new KhachHangRepository();
                HoaDon hd = hoaDonRepository.getHoaDonById(idHD);
                KhachHang kh = khrp.getKhachHangById(hd.getIdKhachHang().getId());
                trahang.setIdHoaDon(hd);
                trahang.setIdNhanVien(nv);
                trahang.setIdKhachHang(kh);
                trahang.setSoluong(Integer.parseInt(txtSoLuong.getText()));
               BigDecimal tongTien = new BigDecimal(txtTongTienHoanTra.getText());
                trahang.setTongtien(tongTien);
                System.out.println("" + trahang.getIdHoaDon());
                trahang.setGhiChu(ghiChu);
                trahang.setLyDo(selectedReason);
                traHangRepository.addTraHang(trahang);
                loadDataToTableTraHang();
            } else {
                return;
            }
        } else {
            return;
        }
    }//GEN-LAST:event_bn_HoanTraMouseClicked

    private void bn_HoanTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bn_HoanTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bn_HoanTraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bn_BackHD;
    private javax.swing.JButton bn_HoanTra;
    private javax.swing.JButton bn_nextHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lb_trangHoaDon;
    private javax.swing.JPopupMenu popupHDCt;
    private javax.swing.JTable tbl_CTHD;
    private javax.swing.JTable tbl_HoaDon;
    private javax.swing.JTable tbl_HoanTra;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNgayTraHang;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienDaThanhToan;
    private javax.swing.JTextField txtTongTienHoanTra;
    private javax.swing.JTextField txt_TimKiemHoaDon;
    // End of variables declaration//GEN-END:variables
}
