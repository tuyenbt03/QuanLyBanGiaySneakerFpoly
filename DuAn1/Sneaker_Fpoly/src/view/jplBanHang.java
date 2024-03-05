/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.microsoft.sqlserver.jdbc.StringUtils;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.HoaDonCho;
import model.ChiTietSanPham;
import model.DanhMuc;
import model.GioHang;
import model.Hang;
import model.ChiTietHoaDon;
import model.KhuyenMai;
import model.MauSac;
import model.Size;
import repository.BanHangRepository;
import repository.HoadonChoRepository;
import service.SPCTService;
import service.SanPhamService;

/**
 *
 * @author LENOVO
 */
public class jplBanHang extends javax.swing.JPanel {
   public final String url = getClass().getClassLoader().getResource("font/unicode.ttf").getPath();
    String pathUnicode = URLDecoder.decode(url, StandardCharsets.UTF_8);
      private SPCTService serviceSPCT = new SPCTService();
    private SanPhamService service = new SanPhamService();
    private int sotrang = 1;
    private int tongSoTrang = 0;
    private final BanHangRepository banHangRepository = new BanHangRepository();
    private final HoadonChoRepository hoadonChoRepository = new HoadonChoRepository();
    public int selectedRowInBanHang = -1;
    DefaultComboBoxModel<DanhMuc> modelComboDanhMuc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<MauSac> modelComboMauSac = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Size> modelComboSize = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Hang> modelComboHang = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> modelComboChatLieu = new DefaultComboBoxModel<>();
    DefaultTableModel model = new DefaultTableModel();
    ArrayList<HoaDonCho> hdc = hoadonChoRepository.getAllHoaDonCho();
    model.NhanVien _nv = Login.nvs;
    

    public jplBanHang() {
        initComponents();
        loadDataToTableSanPham();
        loadDataToTableHoaDonCho();
        loadDataCBDanhMuc();
        loadDataCBMauSac();
        loadDataCBSize();
        loadDataCBHang();
        loadDataCBChatLieu();
        cbbDanhMuc.setModel((DefaultComboBoxModel) modelComboDanhMuc);
        cbbMauSac.setModel((DefaultComboBoxModel) modelComboMauSac);
        cbbSize.setModel((DefaultComboBoxModel) modelComboSize);
        cbbHang.setModel((DefaultComboBoxModel) modelComboHang);
        cbbChatLieu.setModel((DefaultComboBoxModel) modelComboChatLieu);
        txtSDTKH.setEditable(false);
        txtTenKH.setEditable(false);
        btnChonKH.setEnabled(false);
        btnChonVoucher.setEnabled(false);
        txtGiaTriGiam.setEditable(false);

    }

    private void loadDataCBDanhMuc() {
        modelComboDanhMuc.removeAllElements();// xoas het du lieu cu trenn combo
        ArrayList<DanhMuc> lst = banHangRepository.getAllDanhMuc();
        for (DanhMuc n : lst) {
            modelComboDanhMuc.addElement(n);
        }
    }

    private void loadDataCBMauSac() {
        modelComboMauSac.removeAllElements();// xoas het du lieu cu trenn combo
        ArrayList<MauSac> lst = banHangRepository.getAllMauSac();
        for (MauSac n : lst) {
            modelComboMauSac.addElement(n);

        }
    }

    private void loadDataCBSize() {
        modelComboSize.removeAllElements();// xoas het du lieu cu trenn combo
        ArrayList<Size> lst = banHangRepository.getAllSize();
        for (Size n : lst) {
            modelComboSize.addElement(n);
        }
    }

    private void loadDataCBHang() {
        modelComboHang.removeAllElements();// xoas het du lieu cu trenn combo
        ArrayList<Hang> lst = banHangRepository.getAllHang();
        for (Hang n : lst) {
            modelComboHang.addElement(n);

        }
    }

    private void loadDataCBChatLieu() {
        modelComboChatLieu.removeAllElements();// xoas het du lieu cu trenn combo
        ArrayList<ChatLieu> lst = banHangRepository.getAllChatLieu();
        for (ChatLieu n : lst) {
            modelComboChatLieu.addElement(n);
        }
    }

    private void loadDataToTableSanPham() {
        model = (DefaultTableModel) tblSanPham.getModel();
        ArrayList<ChiTietSanPham> list = banHangRepository.getSanPhamChiTietForPage(sotrang);
        model.setRowCount(0);
        for (ChiTietSanPham sp : list) {
            model.addRow(new Object[]{sp.getId(), sp.getSanPham(), sp.getHang(),
                sp.getSize(), sp.getDanhMuc(), sp.getChatLieu(), sp.getMauSac(),
                sp.getGiaNhap(), sp.getGiaBan(), sp.getSoLuong(), sp.getTrangThai() == 1 ? "còn hàng" : "hết hàng"});
        }
        lblTrang.setText("" + sotrang);
    }
    public BigDecimal tienHoaDon(){
     BigDecimal thd = new BigDecimal(txtTongTienHoaDon.getText());
     return thd;
    }
    public void loadDataToTableHoaDonCho() {
        model = (DefaultTableModel) tblHoaDon.getModel();
        ArrayList<HoaDonCho> list = hoadonChoRepository.getAllHoaDonCho();
        model.setRowCount(0);
        for (HoaDonCho hd : list) {
            model.addRow(new Object[]{hd.getMaHoaDon(), hd.getIdNhanVien() == null ? "" : hd.getIdNhanVien(), hd.getIdKhachHang() == null ? "khach le" : hd.getIdKhachHang(), hd.getNgayTao(), hd.getTrangThai() == 1 ? "dathanhtoan" : "chuathanhtoan"});
        }
        lblTrang.setText("" + sotrang);
    }

    private void loadDataToTableGioHang() {
        model = (DefaultTableModel) tblGioHang.getModel();
        int row = tblHoaDon.getSelectedRow();
        String mahd = tblHoaDon.getValueAt(row, 0).toString();
        String idhd = hoadonChoRepository.getIdHoaDonByMa(mahd);

        ArrayList<GioHang> list = hoadonChoRepository.getAllGioHang(idhd);
        model.setRowCount(0);
        for (GioHang gh : list) {
            model.addRow(new Object[]{gh.getId(), gh.getSanPham(), gh.getHang(), gh.getSize(), gh.getDanhMuc(), gh.getChatLieu(), gh.getMauSac(), gh.getGiaBan(), gh.getSoLuong(), gh.getDonGia(), gh.getTrangThai() == 1 ? "dathanhtoa" : "chothanhtoan"});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        btnChonKH = new javax.swing.JButton();
        btnHuyKH = new javax.swing.JButton();
        txtSDTKH = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtTongTienHoaDon = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtMaVoucher = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        btnHuyVoucher = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        txtTongTienGiam = new javax.swing.JTextField();
        txtTongTienThanhToan = new javax.swing.JTextField();
        btnTTvaIn = new javax.swing.JButton();
        btnChonVoucher = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtGiaTriGiam = new javax.swing.JTextField();
        lblGiaTriGiam = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTaoHD = new javax.swing.JButton();
        btnHuyHD = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnXoaSP = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoaGH = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiemSP = new javax.swing.JTextField();
        btnSpTien = new javax.swing.JButton();
        btnSpLui = new javax.swing.JButton();
        lblTrang = new javax.swing.JLabel();
        cbbHang = new javax.swing.JComboBox<>();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng"));
        jPanel5.setEnabled(false);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setOpaque(false);

        jLabel30.setText("Số điện thoại");

        jLabel31.setText("Tên KH");

        txtTenKH.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenKH.setEnabled(false);

        btnChonKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Search.png"))); // NOI18N
        btnChonKH.setText("Tìm");
        btnChonKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonKHMouseClicked(evt);
            }
        });
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        btnHuyKH.setText("Hủy");
        btnHuyKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyKHActionPerformed(evt);
            }
        });

        txtSDTKH.setEditable(false);
        txtSDTKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKHKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(txtSDTKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnChonKH, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHuyKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(btnChonKH)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyKH))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel32.setText("Mã HĐ");

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHD.setForeground(new java.awt.Color(255, 0, 0));
        txtMaHD.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaHD.setEnabled(false);

        txtTongTienHoaDon.setEditable(false);
        txtTongTienHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienHoaDon.setForeground(new java.awt.Color(255, 0, 0));
        txtTongTienHoaDon.setText("0");
        txtTongTienHoaDon.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTongTienHoaDon.setEnabled(false);

        jLabel33.setText("Tiền hóa đơn");

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });
        txtTienKhachDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaMouseReleased(evt);
            }
        });
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        jLabel34.setText("Tiền khách đưa");

        txtTienThua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienThua.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTienThua.setEnabled(false);
        txtTienThua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTienThuaMouseExited(evt);
            }
        });

        jLabel35.setText("Tiền thừa");

        txtMaVoucher.setEditable(false);
        txtMaVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaVoucher.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaVoucher.setEnabled(false);
        txtMaVoucher.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaVoucherCaretUpdate(evt);
            }
        });

        jLabel37.setText("Voucher");

        jLabel39.setText("Tổng tiền giảm");

        jLabel40.setText("Tổng tiền thanh toán ");

        jLabel41.setText("Ghi chú");

        btnHuyVoucher.setText("Hủy");
        btnHuyVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyVoucherActionPerformed(evt);
            }
        });

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/shopping.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel38.setText("VND");

        jLabel42.setText("VND");

        jLabel43.setText("VND");

        lbl.setText("VND");

        txtTongTienGiam.setEditable(false);
        txtTongTienGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienGiam.setText("0");
        txtTongTienGiam.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTongTienGiam.setEnabled(false);
        txtTongTienGiam.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTongTienGiamCaretUpdate(evt);
            }
        });

        txtTongTienThanhToan.setEditable(false);
        txtTongTienThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTongTienThanhToan.setForeground(new java.awt.Color(255, 51, 51));
        txtTongTienThanhToan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTongTienThanhToan.setEnabled(false);

        btnTTvaIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Fax.png"))); // NOI18N
        btnTTvaIn.setText("Thanh Toán + In");
        btnTTvaIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTvaInActionPerformed(evt);
            }
        });

        btnChonVoucher.setText("Sử dụng");
        btnChonVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonVoucherActionPerformed(evt);
            }
        });

        btnXacNhan.setText("xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel1.setText("Giá trị giảm ");

        txtGiaTriGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaTriGiam.setText("0");

        lblGiaTriGiam.setText("VND");

        jLabel3.setText("VND");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTTvaIn)
                .addGap(40, 40, 40))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTongTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(49, 49, 49)
                                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel33)
                                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel1))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtMaVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                                    .addComponent(txtTongTienHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                                    .addComponent(txtGiaTriGiam))))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblGiaTriGiam)
                                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                                        .addComponent(btnChonVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnHuyVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnXacNhan))
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTongTienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel38)))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonVoucher)
                    .addComponent(btnHuyVoucher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lblGiaTriGiam))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtTongTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(btnXacNhan))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43)))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnTTvaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Tên nhân viên", "Tên khách hàng", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setSelectionBackground(new java.awt.Color(153, 255, 204));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblHoaDon);

        btnTaoHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Accept.png"))); // NOI18N
        btnTaoHD.setText("Tạo hóa đơn");
        btnTaoHD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnHuyHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/cancel.png"))); // NOI18N
        btnHuyHD.setText("Hủy hóa đơn");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuyHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnTaoHD)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        btnXoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/cleaning.png"))); // NOI18N
        btnXoaSP.setText("clear");
        btnXoaSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Refresh.png"))); // NOI18N
        btnSua.setText("Sửa ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoaGH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/cancel.png"))); // NOI18N
        btnXoaGH.setText("Xóa");
        btnXoaGH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGHActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Right.png"))); // NOI18N

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Left.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id HDCT", "ten sp", "hang", "size", "danh muc", "chat lieu ", "mau sac", "gia ban", "so luong", "Thành tiền", "Trang thai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false, false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setRowHeight(25);
        tblGioHang.setSelectionBackground(new java.awt.Color(255, 255, 153));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblGioHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnXoaSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnXoaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaGH, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id San pham", "ten sp", "hang", "size", "danh muc", "chat lieu ", "mau sac", "gia nhap", "gia ban", "so luong", "Trang thai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(25);
        tblSanPham.setSelectionBackground(new java.awt.Color(255, 255, 153));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPham);

        txtTimKiemSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSPCaretUpdate(evt);
            }
        });
        txtTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSPActionPerformed(evt);
            }
        });

        btnSpTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Right.png"))); // NOI18N
        btnSpTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpTienActionPerformed(evt);
            }
        });

        btnSpLui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Left.png"))); // NOI18N
        btnSpLui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSpLuiMouseClicked(evt);
            }
        });
        btnSpLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpLuiActionPerformed(evt);
            }
        });

        lblTrang.setText("1");

        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHangItemStateChanged(evt);
            }
        });
        cbbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHangActionPerformed(evt);
            }
        });

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDanhMucItemStateChanged(evt);
            }
        });

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMauSacItemStateChanged(evt);
            }
        });

        jLabel5.setText("hang");

        jLabel6.setText("danh muc");

        jLabel7.setText("Mausac");

        jLabel8.setText("Size");

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSizeItemStateChanged(evt);
            }
        });

        jLabel9.setText("chat lieu");

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChatLieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbChatLieuItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utility/icon/Delete.png"))); // NOI18N
        jButton1.setText("Hủy lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSpLui, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSpTien, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(46, 46, 46)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbMauSac, 0, 115, Short.MAX_VALUE)
                    .addComponent(cbbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSpTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSpLui, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void showdetailHDC() {
        int row = tblHoaDon.getSelectedRow();
        String mahd = tblHoaDon.getValueAt(row, 0).toString();
        String idhd = hoadonChoRepository.getIdHoaDonByMa(mahd);
        BigDecimal tongTien = hoadonChoRepository.getTongGiaTien(idhd);
        txtTongTienHoaDon.setText(tongTien.toString());
        txtMaHD.setText(tblHoaDon.getValueAt(row, 0).toString());
        txtTenKH.setText(tblHoaDon.getValueAt(row, 2).toString());
        txtSDTKH.setText(banHangRepository.getSdtKhachHang(idhd));
        
        ArrayList<KhuyenMai> km = banHangRepository.getGiaGiamVaMaVoucher(idhd);

        if (km != null && !km.isEmpty()) {
            for (KhuyenMai khuyenMai : km) {
                if (khuyenMai.getHinhThucApDung() == false) {
                    txtMaVoucher.setText(khuyenMai.getMaKhuyenMai());
                    txtGiaTriGiam.setText(khuyenMai.getGiaTri().toString());
                    lblGiaTriGiam.setText("VND");
                    BigDecimal tongTienGiam = new BigDecimal(txtGiaTriGiam.getText());
                    txtTongTienGiam.setText(tongTienGiam.toString());

                } else {
                    txtMaVoucher.setText(khuyenMai.getMaKhuyenMai());
                    txtGiaTriGiam.setText(khuyenMai.getGiaTri().toString());
                    lblGiaTriGiam.setText("%");
                    BigDecimal phanTramGiam = new BigDecimal(txtGiaTriGiam.getText());
                    BigDecimal tongTienGiam = tongTien.multiply(phanTramGiam.divide(new BigDecimal(100)))
                            .setScale(0, BigDecimal.ROUND_DOWN); // bỏ thập phân ở đuôi
                    txtTongTienGiam.setText(tongTienGiam.toString());

                }
                BigDecimal thanhToan = tongTien.subtract(new BigDecimal(txtTongTienGiam.getText()));
                txtTongTienThanhToan.setText(thanhToan.toString());
            }
        } else {
            txtMaVoucher.setText("");
            txtGiaTriGiam.setText("0");
            txtTongTienGiam.setText("0");
            txtTongTienThanhToan.setText(txtTongTienHoaDon.getText());
        }

    }

    public void clear() {
        txtMaHD.setText("");
        txtGhiChu.setText("");
        txtTongTienHoaDon.setText("0");
        txtTongTienGiam.setText("0");
        txtTienThua.setText("0");
        txtMaVoucher.setText("");
        txtTienKhachDua.setText("0");
        txtTenKH.setText("");
        txtTongTienThanhToan.setText("0");
        txtSDTKH.setText("");
        txtGiaTriGiam.setText("0");
    }

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed

        try {
            new jplHD_KH(this, tblHoaDon).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(jplBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnHuyKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyKHActionPerformed
        int rowHDC = tblHoaDon.getSelectedRow();
        if (rowHDC <= -1) {
            JOptionPane.showMessageDialog(this, "bạn phải chọn 1 dòng");
        }
        String idHoaDon = hoadonChoRepository.getIdHoaDonByMa(tblHoaDon.getValueAt(rowHDC, 0).toString());
        String idKH = null;
        hoadonChoRepository.updateKhachHangVaoHoaDonByID(idHoaDon, idKH);
        loadDataToTableHoaDonCho();
        JOptionPane.showMessageDialog(this, "Hủy thành công");
        selectedRowInBanHang = rowHDC;
        tblHoaDon.setRowSelectionInterval(selectedRowInBanHang, selectedRowInBanHang);

        txtSDTKH.setText("");
        txtTenKH.setText("");

    }//GEN-LAST:event_btnHuyKHActionPerformed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate

    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtMaVoucherCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaVoucherCaretUpdate


    }//GEN-LAST:event_txtMaVoucherCaretUpdate

    private void btnHuyVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyVoucherActionPerformed
        int rowHDC = tblHoaDon.getSelectedRow();
        if (rowHDC <= -1) {
            JOptionPane.showMessageDialog(this, "bạn phải chọn 1 dòng");
        }else{
            int cf = JOptionPane.showConfirmDialog(this,
                    "Bạn chắc chắn muốn hủy voucher này", "Xóa hóa đơn", JOptionPane.YES_NO_OPTION);
            if (cf != JOptionPane.YES_OPTION) {
                return;
            }
        String idHD = hoadonChoRepository.getIdHoaDonByMa(tblHoaDon.getValueAt(rowHDC, 0).toString());
        String idKM = null;
        hoadonChoRepository.updateKhuyenMaiVaoHoaDonByID(idHD, idKM);
        loadDataToTableHoaDonCho();
        JOptionPane.showMessageDialog(this, "xóa thành công");
        selectedRowInBanHang = rowHDC;
        tblHoaDon.setRowSelectionInterval(selectedRowInBanHang, selectedRowInBanHang);
        txtMaVoucher.setText("");
        txtGiaTriGiam.setText("0");
        txtTongTienGiam.setText("0");
        showdetailHDC();
        }

    }//GEN-LAST:event_btnHuyVoucherActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int row = tblHoaDon.getSelectedRow();
        if(row <= -1){
        JOptionPane.showMessageDialog(this, "hãy chọn hóa đơn bạn muốn thanh toán");
        return;
        }
        if (txtTongTienHoaDon.getText().equals(0)) {
       JOptionPane.showMessageDialog(this, "bạn chưa có sản phẩm trong giỏ hàng");
       return;}
           if (txtTienKhachDua.getText() == null || txtTienKhachDua.getText().trim().isEmpty()) {
       JOptionPane.showMessageDialog(this, "Bạn phải nhập số tiền khách đưa");
       return;
} if(txtTienThua.getText() == null || txtTienThua.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(this, "Bạn phải nhập số tiền thừa");
    return;
}
        
        String idHD = hoadonChoRepository.getIdHoaDonByMa(tblHoaDon.getValueAt(row, 0).toString());
        HoaDonCho hd = new HoaDonCho();
        if (txtGhiChu == null || txtGhiChu.getText().trim().isEmpty()) {
        String ghichu = "Null";
        hd.setGhiChu(ghichu);
        }else{
         hd.setGhiChu(txtGhiChu.getText());
        }
        hd.setId(idHD);
        BigDecimal tongTienGiam = new BigDecimal(txtTongTienGiam.getText());
        hd.setTongTienGiam(tongTienGiam);
        BigDecimal tienkhachdua = new BigDecimal(txtTienKhachDua.getText());
        hd.setTienKhachDua(tienkhachdua);
        BigDecimal tienthua = new BigDecimal(txtTienThua.getText());
        hd.setTienThua(tienthua);
        BigDecimal tongthanhtoan = new BigDecimal(txtTongTienThanhToan.getText());
        hd.setThanhTien(tongthanhtoan);
//        if(hd.getIdKhachHang()==null){
//        hd.setIdKhachHang("khachle");
//        }
     
         int cf = JOptionPane.showConfirmDialog(this,
                "Bạn chắc chắn muốn thanh toan", "thanh toan hóa đơn", JOptionPane.YES_NO_OPTION);
        if (cf != JOptionPane.YES_OPTION) {
            return;
        }
        banHangRepository.upDateThanhToanHoaDon(hd);
        banHangRepository.upDateThanhToanHoaDonCTByIDHD(idHD);
        model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        loadDataToTableHoaDonCho();
        loadDataToTableSanPham();
        clear();
        JOptionPane.showMessageDialog(this, "thanh toan thanh cong");
         
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTongTienGiamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTongTienGiamCaretUpdate

    }//GEN-LAST:event_txtTongTienGiamCaretUpdate

    private void btnTTvaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTvaInActionPerformed
        int row = tblHoaDon.getSelectedRow();
        if (row <= -1) {
            JOptionPane.showMessageDialog(this, "hãy chọn hóa đơn bạn muốn thanh toán");
            return;
        }
        if (txtTongTienHoaDon.getText().equals(0)) {
            JOptionPane.showMessageDialog(this, "bạn chưa có sản phẩm trong giỏ hàng");
            return;
        }
        if (txtTienKhachDua.getText() == null || txtTienKhachDua.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số tiền khách đưa");
            return;
        }
        if (txtTienThua.getText() == null || txtTienThua.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số tiền thừa");
            return;
        }
       ArrayList<HoaDonCho> hdcc = hoadonChoRepository.getAllHoaDonCho();
        String idHD = hdcc.get(tblHoaDon.getSelectedRow()).getId();
        HoaDonCho hd = new HoaDonCho();
        if(txtGhiChu == null){
            String ghichu = "null";
        hd.setGhiChu(ghichu);
        }else{
         hd.setGhiChu(txtGhiChu.getText());
        }
            
        hd.setId(idHD);
        BigDecimal tienkhachdua = new BigDecimal(txtTienKhachDua.getText());
        hd.setTienKhachDua(tienkhachdua);
        BigDecimal tienthua = new BigDecimal(txtTienThua.getText());
        hd.setTienThua(tienthua);
        BigDecimal tongthanhtoan = new BigDecimal(txtTongTienThanhToan.getText());
        hd.setThanhTien(tongthanhtoan);

        int cf = JOptionPane.showConfirmDialog(this,
                "Bạn chắc chắn muốn thanh toan", "thanh toan hóa đơn", JOptionPane.YES_NO_OPTION);
        if (cf != JOptionPane.YES_OPTION) {
            return;
        }
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "abc.pdf"));
            doc.open();
           
            doc.add(new Paragraph("------------------------"));
            PdfPTable tbl = new PdfPTable(3);
            tbl.addCell("ten sp");
            tbl.addCell("so luong");
            tbl.addCell("Thanh tien");
            for (int i = 0; i < 1; i++) {
                String o1 = tblGioHang.getValueAt(i, 1).toString();
                String o2 = tblGioHang.getValueAt(i, 8).toString();
                String o3 = tblGioHang.getValueAt(i, 9).toString();
                tbl.addCell(o1);
                tbl.addCell(o2);
                tbl.addCell(o3);
            }
            doc.add(tbl);
            doc.add(new Paragraph("Ma hoa đon " + tblHoaDon.getValueAt(row, 0)));
            doc.add(new Paragraph("Ten khach hang " + tblHoaDon.getValueAt(row, 2)));
            doc.add(new Paragraph("So đien thoai" + txtSDTKH.getText()));
            doc.add(new Paragraph("Tong hoa đon" + txtTongTienThanhToan.getText()));
            doc.add(new Paragraph("------------------------"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        banHangRepository.upDateThanhToanHoaDon(hd);
        banHangRepository.upDateThanhToanHoaDonCTByIDHD(idHD);
        model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        loadDataToTableHoaDonCho();
        loadDataToTableSanPham();
        clear();

        doc.close();
        JOptionPane.showMessageDialog(this, "thanh toan thanh cong");

    }//GEN-LAST:event_btnTTvaInActionPerformed

    private void btnChonVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonVoucherActionPerformed
       try {
            new jplHD_KM(this, tblHoaDon).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(jplBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnChonVoucherActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        int row = tblHoaDon.getSelectedRow();
        btnChonKH.setEnabled(true);
        btnChonVoucher.setEnabled(true);
        if (row != -1) {          // Cập nhật giá trị của biến selectedRowInBanHang
            selectedRowInBanHang = row;
        }
        showdetailHDC();
        loadDataToTableGioHang();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
//      if(hoadonChoRepository.listHDC.size()<=4){
//       hoadonChoRepository.themHoaDonCho();
//       loadDataToTableHoaDonCho();
//      }else{
//          JOptionPane.showMessageDialog(this, "bạn chỉ có thể tạo tối đa 5 hóa đơn chờ");
//          return;
//      }
        ArrayList<HoaDonCho> list = hoadonChoRepository.getAllHoaDonCho();
        if (list.size() >= 5) {
            JOptionPane.showMessageDialog(this, "Bạn đã tạo tối đa 5 hóa đơn");
            return;
        } else {
            hoadonChoRepository.addHoaDonCho(_nv.getId());
        }
        loadDataToTableHoaDonCho();
        JOptionPane.showMessageDialog(this, "Them thanh cong");


    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        int row = tblHoaDon.getSelectedRow();
        try {

            if (row <= -1) {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 dòng");
                return;
            } else {
                int cf = JOptionPane.showConfirmDialog(this,
                        "Bạn chắc chắn muốn xóa", "Xóa hóa đơn", JOptionPane.YES_NO_OPTION);
                if (cf != JOptionPane.YES_OPTION) {
                    return;
                }  // Đưa con trỏ đến dòng được chọn
                String maHoaDon = tblHoaDon.getValueAt(row, 0).toString();
                String idHoaDon = hoadonChoRepository.getIdHoaDonByMa(maHoaDon);
                // Sử dụng id để xóa hóa đơn
                List<ChiTietHoaDon> chiTietHoaDonList = hoadonChoRepository.getAllChiTietHoaDonByIDHD(idHoaDon);
//                System.out.println(chiTietHoaDonList);

                for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) {
                    // Lấy thông tin chi tiết sản phẩm từ chi tiết hóa đơn
                    String idChiTietSanPham = chiTietHoaDon.getIdChiTietSanPham();
                    int soLuongCongVaoSPCT = chiTietHoaDon.getSoLuong();
                    // Cập nhật số lượng trong bảng ChiTietSanPham
                    hoadonChoRepository.capNhatSoLuongChiTietSanPhamClear(idChiTietSanPham, soLuongCongVaoSPCT);
                    banHangRepository.upDateTrangThaiCTSP(idChiTietSanPham,1);
                }

                hoadonChoRepository.xoaHoaDonCho(maHoaDon);
                loadDataToTableHoaDonCho();
                model = (DefaultTableModel) tblGioHang.getModel();
                model.setRowCount(0);
                
                loadDataToTableSanPham();
                clear();
                JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnHuyHDActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
     
        int cf = JOptionPane.showConfirmDialog(this,
                "Bạn chắc chắn muốn xóa tất cả", "Xóa giỏ hàng", JOptionPane.YES_NO_OPTION);
        if (cf != JOptionPane.YES_OPTION) {
            return;
        }
        int rowHD = tblHoaDon.getSelectedRow();
//        ArrayList<HoaDonCho> lst = new ArrayList<>();
//        String idHD = hoadonChoRepository.getIdHoaDonByMa(tblHoaDon.getValueAt(rowHD, 0).toString());
        String idHoaDon = hoadonChoRepository.getIdHoaDonByMa(tblHoaDon.getValueAt(rowHD, 0).toString());
        List<ChiTietHoaDon> chiTietHoaDonList = hoadonChoRepository.getAllChiTietHoaDonByIDHD(idHoaDon);
        hoadonChoRepository.xoaTatCaHoaDonChiTiet(idHoaDon);
        for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) {
            // Lấy thông tin chi tiết sản phẩm từ chi tiết hóa đơn
            String idChiTietSanPham = chiTietHoaDon.getIdChiTietSanPham();
            int soLuongCongVaoSPCT = chiTietHoaDon.getSoLuong();
            // Cập nhật số lượng trong bảng ChiTietSanPham
            hoadonChoRepository.capNhatSoLuongChiTietSanPhamClear(idChiTietSanPham, soLuongCongVaoSPCT);
              banHangRepository.upDateTrangThaiCTSP(idChiTietSanPham,1);
        }
        loadDataToTableGioHang();
        loadDataToTableSanPham();
        showdetailHDC();
        JOptionPane.showMessageDialog(this, "Bạn đã xóa thành công");
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int sl = 0;
        int rowGH = tblGioHang.getSelectedRow();
        if (rowGH <= -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 dòng");
            return;
        }
        BigDecimal giaban = new BigDecimal(tblGioHang.getValueAt(rowGH, 7).toString());
        String idCTHD = tblGioHang.getValueAt(rowGH, 0).toString();
        String idCTSP = hoadonChoRepository.getIdCTSPByIDHDCT(idCTHD);
        int slgCtsp = hoadonChoRepository.getSoLuongCtspByID(idCTSP);
        int slGh = Integer.parseInt(tblGioHang.getValueAt(rowGH, 8).toString());
        int slTong = slgCtsp + slGh;

        String slNhap = JOptionPane.showInputDialog(this, "Số lượng còn lại trong kho là "
                + slTong
                + "\n Nhập số lượng bạn muốn sửa");
        if (!StringUtils.isNumeric(slNhap)) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số");
            return;
        }
        if (slNhap == null) {
            JOptionPane.showMessageDialog(this, "Bạn không được để trống");
            return;
        }
        sl = Integer.parseInt(slNhap);
        if (sl <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải từ 1 đổ lên");
            return;
        }

        if (sl > slTong) {
            JOptionPane.showMessageDialog(this, "Số lượng trong kho không đủ");
            return;
        }
        BigDecimal slNhapBigDecimal = new BigDecimal(sl);
        BigDecimal dongia = giaban.multiply(slNhapBigDecimal);
        if (JOptionPane.showConfirmDialog(this, "---Thông tin sản phẩm---\n"
                + "\nTên Sp: " + tblGioHang.getValueAt(rowGH, 1)
                + "\nSố lượng cập nhật: " + sl
                + "\nThành tiền: " + dongia, "Bạn chắc chắn mua sản phẩm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            hoadonChoRepository.updateSoLuongChiTietHoaDonByID(sl, dongia, idCTHD);
            hoadonChoRepository.updateSoLuongChiTietSanPham(slTong - sl, idCTSP);
            loadDataToTableGioHang();
            loadDataToTableSanPham();
            showdetailHDC();
            JOptionPane.showMessageDialog(this, "Cập nhật số lượng thành công");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGHActionPerformed
        int rowGH = tblGioHang.getSelectedRow();

        if (rowGH <= -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 dòng");
        } else {
            int cf = JOptionPane.showConfirmDialog(this,
                    "Bạn chắc chắn muốn xóa", "Xóa hóa đơn", JOptionPane.YES_NO_OPTION);
            if (cf != JOptionPane.YES_OPTION) {
                return;
            }
            // Đưa con trỏ đến dòng được chọn
            String idHDCT = tblGioHang.getValueAt(rowGH, 0).toString();

            // Sử dụng id để xóa hóa đơn
            int slhdct = Integer.parseInt(tblGioHang.getValueAt(rowGH, 8).toString());

//             System.out.println(slhdct);
            String idCTSP = hoadonChoRepository.getIdCTSPByIDHDCT(idHDCT);
   
            int soLuongChiTietSPUD = hoadonChoRepository.getSoLuongCtspByID(idCTSP) + slhdct;
            

            hoadonChoRepository.updateSoLuongChiTietSanPham(soLuongChiTietSPUD, idCTSP);
            hoadonChoRepository.xoaHoaDonChiTiet(idHDCT);
            banHangRepository.upDateTrangThaiCTSP(idCTSP,1);
            loadDataToTableGioHang();
            loadDataToTableSanPham();
            showdetailHDC();
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
        }
    }//GEN-LAST:event_btnXoaGHActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int rowHDC = tblHoaDon.getSelectedRow();
        int rowDSSP = tblSanPham.getSelectedRow();
        int sl = 0;
        if (rowHDC <= -1) {
            JOptionPane.showMessageDialog(this, "ban phai chon hoa don muon them");
            return;
        }
       
        String slNhap = JOptionPane.showInputDialog(this, "Nhập số lượng mua");
        if (slNhap == null) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng");
            return;
        }
        //Bắt buộc nhập số
        if (!StringUtils.isNumeric(slNhap)) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số");
            return;
        }
        sl = Integer.parseInt(slNhap);
        if (sl <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải nguyên dương");
            return;
        }
        if (sl > Integer.parseInt(tblSanPham.getValueAt(rowDSSP, 9).toString())) {
            JOptionPane.showMessageDialog(this, "Số lượng trong kho không đủ");
            return;
        }
        ChiTietHoaDon hdct = new ChiTietHoaDon();
        hdct.setIdHoaDon(hoadonChoRepository.getIdHoaDonByMa(tblHoaDon.getValueAt(rowHDC, 0).toString()));
        hdct.setIdChiTietSanPham(tblSanPham.getValueAt(rowDSSP, 0).toString());
        BigDecimal dongia = new BigDecimal(tblSanPham.getValueAt(rowDSSP, 8).toString());
        BigDecimal soLuongNhapBigDecimal = new BigDecimal(sl);
        hdct.setSoLuong(sl);
        String idctsp = tblSanPham.getValueAt(rowDSSP, 0).toString();
        int soLuongChiTietSPUD = Integer.parseInt(tblSanPham.getValueAt(rowDSSP, 9).toString()) - sl;
        BigDecimal thanhTien = dongia.multiply(soLuongNhapBigDecimal);
        hdct.setDonGia(thanhTien);
//  
        if (JOptionPane.showConfirmDialog(this, "---Thông tin sản phẩm---\n"
                + "\nMã Sp: " + tblHoaDon.getValueAt(rowHDC, 0)
                + "\nSố lượng:" + sl
                + "\nThành tiền: " + thanhTien, "Bạn chắc chắn mua sản phẩm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (CheckTrung() == true) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong giỏ hàng");
                return;
            }else {
                hoadonChoRepository.addHoaDonChiTiet(hdct);
                hoadonChoRepository.updateSoLuongChiTietSanPham(soLuongChiTietSPUD, idctsp);
            }
            loadDataToTableGioHang();
            loadDataToTableSanPham();
//            ArrayList<ChiTietSanPham> ctsp = hoadonChoRepository.getAllChiTietSanPham();
//            int slctsp = ctsp.get(tblSanPham.getSelectedRow()).getSoLuong();
             int slctsp = Integer.parseInt(tblSanPham.getValueAt(rowDSSP, 9).toString());
             System.out.println(slctsp);
             System.out.println(idctsp);
               
                banHangRepository.upDateTrangThaiCTSPSL(0);
                  loadDataToTableGioHang();
                  loadDataToTableSanPham();
                
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            showdetailHDC();
        }

        //        hdct.setDonGia(tblSanPham.getValueAt(rowDSSP, 8));

    }//GEN-LAST:event_tblSanPhamMouseClicked
    // check trung idctsp voi idctspby gio hang
    private Boolean CheckTrung() {
        int rowSP = tblSanPham.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        String idctsp = tblSanPham.getValueAt(rowSP, 0).toString();
        String idHD = hoadonChoRepository.getIdHoaDonByMa(tblHoaDon.getValueAt(rowHD, 0).toString());
        if (hoadonChoRepository.checkTrungCtspAndGH(idHD, idctsp) == 0) {
            return false;
        } else {
            return true;
        }
    }
    private void txtTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSPActionPerformed

    }//GEN-LAST:event_txtTimKiemSPActionPerformed

    private void btnSpLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpLuiActionPerformed
        if (sotrang > 1) {
            sotrang--;
            loadDataToTableSanPham();
            lblTrang.setText("" + sotrang);
            System.out.println(sotrang);
        }
    }//GEN-LAST:event_btnSpLuiActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnSpLuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSpLuiMouseClicked

    }//GEN-LAST:event_btnSpLuiMouseClicked

    private void btnSpTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpTienActionPerformed
        int tong = banHangRepository.tongsoItem();
        tongSoTrang = tong / 2;
        if (sotrang < tongSoTrang) {
            sotrang++;
            lblTrang.setText("" + sotrang);
            loadDataToTableSanPham();
            System.out.println(sotrang);
        }
    }//GEN-LAST:event_btnSpTienActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void cbbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbHangActionPerformed

    private void btnChonKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonKHMouseClicked

    }//GEN-LAST:event_btnChonKHMouseClicked

    private void txtSDTKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKHKeyReleased

        String tensp = txtTimKiemSP.getText();
        model = (DefaultTableModel) tblSanPham.getModel();
        ArrayList<ChiTietSanPham> list = banHangRepository.search(tensp);
        model.setRowCount(0);
        for (ChiTietSanPham sp : list) {
            model.addRow(new Object[]{sp.getId(), sp.getSanPham(), sp.getHang(),
                sp.getSize(), sp.getDanhMuc(), sp.getChatLieu(), sp.getMauSac(),
                sp.getGiaNhap(), sp.getGiaBan(), sp.getSoLuong(), sp.getTrangThai() == 1 ? "hoatdong" : "ngunghoatdong"});
        }
        lblTrang.setText("" + sotrang);

    }//GEN-LAST:event_txtSDTKHKeyReleased

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed

    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void txtTienKhachDuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaMouseExited

    }//GEN-LAST:event_txtTienKhachDuaMouseExited

    private void txtTienKhachDuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaMouseEntered

    private void txtTienKhachDuaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaMouseReleased

    }//GEN-LAST:event_txtTienKhachDuaMouseReleased

    private void txtTienKhachDuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaMousePressed

    }//GEN-LAST:event_txtTienKhachDuaMousePressed

    private void txtTienThuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienThuaMouseExited

    }//GEN-LAST:event_txtTienThuaMouseExited

    private void txtTienKhachDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaMouseClicked

    }//GEN-LAST:event_txtTienKhachDuaMouseClicked

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        tienthua();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void cbbHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHangItemStateChanged
       DefaultTableModel dtm2 = (DefaultTableModel) tblSanPham.getModel();
        String timKiem =  cbbHang.getSelectedItem().toString();
        System.out.println(timKiem);
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemHangCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Hang San Pham" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblSanPham.setModel(dtm2);
        } else {
            loadDataToTableSanPham();
        }
    }//GEN-LAST:event_cbbHangItemStateChanged

    private void cbbChatLieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbChatLieuItemStateChanged
        DefaultTableModel dtm2 = (DefaultTableModel) tblSanPham.getModel();
        String timKiem =  cbbChatLieu.getSelectedItem().toString();
        System.out.println(timKiem);
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemChatLieuCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Hang San Pham" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblSanPham.setModel(dtm2);
        } else {
            loadDataToTableSanPham();
        }
    }//GEN-LAST:event_cbbChatLieuItemStateChanged

    private void cbbDanhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDanhMucItemStateChanged
          DefaultTableModel dtm2 = (DefaultTableModel) tblSanPham.getModel();
        String timKiem =  cbbDanhMuc.getSelectedItem().toString();
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemDanhMucCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Hang San Pham" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblSanPham.setModel(dtm2);
        } else {
            loadDataToTableSanPham();
        }
    }//GEN-LAST:event_cbbDanhMucItemStateChanged

    private void cbbSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSizeItemStateChanged
        DefaultTableModel dtm2 = (DefaultTableModel) tblSanPham.getModel();
        String timKiem =  cbbSize.getSelectedItem().toString();
        System.out.println(timKiem);
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemSizeCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Hang San Pham" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblSanPham.setModel(dtm2);
        } else {
            loadDataToTableSanPham();
        }
    }//GEN-LAST:event_cbbSizeItemStateChanged

    private void cbbMauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMauSacItemStateChanged
        DefaultTableModel dtm2 = (DefaultTableModel) tblSanPham.getModel();
        String timKiem =  cbbMauSac.getSelectedItem().toString();
        System.out.println(timKiem);
        ArrayList<ChiTietSanPham> lstCTSP = serviceSPCT.timKiemMauSacCTSP(timKiem);
        if (!timKiem.equals("")) {
            dtm2.setRowCount(0);
            for (ChiTietSanPham chiTietSanPham : lstCTSP) {
                System.out.println("Hang San Pham" + chiTietSanPham.getSanPham());
                dtm2.addRow(new Object[]{
                    chiTietSanPham.getId(),
                    chiTietSanPham.getSanPham(),
                    chiTietSanPham.getHang(),
                    chiTietSanPham.getSize(),
                    chiTietSanPham.getDanhMuc(),
                    chiTietSanPham.getChatLieu(),
                    chiTietSanPham.getMauSac(),
                    chiTietSanPham.getGiaNhap(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getTrangThai() == 1 ? "Đang còn hàng" : "Hết hàng"
                });
            }
            tblSanPham.setModel(dtm2);
        } else {
            loadDataToTableSanPham();
        }
    }//GEN-LAST:event_cbbMauSacItemStateChanged

    private void txtTimKiemSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSPCaretUpdate
          if (txtTimKiemSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "khong co du lieu");
            loadDataToTableSanPham();
        } else {
            String tensp = txtTimKiemSP.getText();
            model = (DefaultTableModel) tblSanPham.getModel();
            ArrayList<ChiTietSanPham> list = banHangRepository.search(tensp);
            model.setRowCount(0);
            for (ChiTietSanPham sp : list) {
                model.addRow(new Object[]{sp.getId(), sp.getSanPham(), sp.getHang(),
                    sp.getSize(), sp.getDanhMuc(), sp.getChatLieu(), sp.getMauSac(),
                    sp.getGiaNhap(), sp.getGiaBan(), sp.getSoLuong(), sp.getTrangThai() == 1 ? "hoatdong" : "ngunghoatdong"});
            }
            lblTrang.setText("" + sotrang);
        }
    }//GEN-LAST:event_txtTimKiemSPCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      loadDataToTableSanPham();
    }//GEN-LAST:event_jButton1ActionPerformed
    private void tienthua() {
        BigDecimal tienHoaDon = new BigDecimal(txtTongTienHoaDon.getText());
        String inputText = txtTienKhachDua.getText();
        // compareTo so sánh giá trị  ,gt của nó ban đầu là âm

        if (!StringUtils.isNumeric(inputText)) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số và giá trị không được âm");
            return;
        }
        BigDecimal tienKhachDua = new BigDecimal(inputText);
        if (tienKhachDua.compareTo(tienHoaDon) < 0) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải lớn hơn hoặc bằng tiền hóa đơn");
        } else {
            BigDecimal tienThua = tienKhachDua.subtract(tienHoaDon);
            txtTienThua.setText(tienThua.toString());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnChonVoucher;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnHuyKH;
    private javax.swing.JButton btnHuyVoucher;
    private javax.swing.JButton btnSpLui;
    private javax.swing.JButton btnSpTien;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTTvaIn;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoaGH;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblGiaTriGiam;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTongTienGiam;
    private javax.swing.JTextField txtTongTienHoaDon;
    private javax.swing.JTextField txtTongTienThanhToan;
    // End of variables declaration//GEN-END:variables
}
