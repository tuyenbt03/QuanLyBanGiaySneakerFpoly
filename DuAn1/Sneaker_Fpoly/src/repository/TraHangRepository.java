/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import model.ChiTietSanPham;
import model.HDCT_CTSP;
import model.HoaDon;
import model.KhachHang;
import model.KhuyenMai;
import model.NhanVien;
import model.TraHang;
import utilitys.DBConnection;

/**
 *
 * @author Thuan
 */
public class TraHangRepository {
    
    HDCT_CTSP hdct_spct = new HDCT_CTSP();
    HoaDonRepository hoaDonRepo = new HoaDonRepository();
    SPCTRepository CTSPRepo = new SPCTRepository();
    HoaDonRepository HDRepo = new HoaDonRepository();
     NhanVienRepositoryQuan nhanVienRepository = new NhanVienRepositoryQuan();
    KhachHangRepository1 khachHangRepository = new KhachHangRepository1();
    KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    KhuyenMai khuyenMai = new KhuyenMai();
    KhachHang khachHang = new KhachHang();
    NhanVien nhanVien = new NhanVien();
public ArrayList<HDCT_CTSP> getKhachHangByIdHoaDon(String id) {
        ArrayList<HDCT_CTSP> listKhachHang = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select hd.mahoadon, kh.tenKhachhang,cthd.ngayTao,cthd.dongia, cthd.soLuong from chitiethoadon as cthd\n" +
"                    join hoadon as hd\n" +
"                    on cthd.idhoadon = hd.id\n" +
"                    join khachhang as kh\n" +
"                    on hd.idKhachHang = kh.id\n" +
"                    where hd.id like ? ";
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setString(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
               
                hdct_spct.setMaHoaDon(rs.getString("maHoaDon"));
                hdct_spct.setTenKhachHang(rs.getString("tenKhachHang"));
                hdct_spct.setSoLuong(rs.getInt("soLuong"));
                hdct_spct.setDonGia(rs.getBigDecimal("donGia"));
                hdct_spct.setNgayBan(rs.getDate("ngayTao"));

                listKhachHang.add(hdct_spct);
            }
            return listKhachHang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  public ArrayList<HoaDon> getAllHoaDon(String maHD) {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
         String  sql = "SELECT * FROM HoaDon where maHoaDon = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
              ptsm.setString(1, maHD);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                nhanVien = nhanVienRepository.getNVByIDTinh(rs.getString("idNhanVien"));
                khachHang = khachHangRepository.getKhachHangById(rs.getString("idKhachHang"));
                khuyenMai = khuyenMaiRepository.getKhuyenMaiByIdTinh(rs.getString("idKhuyenMai"));
                hoaDon.setId(rs.getString("id"));
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
                hoaDon.setIdNhanVien(nhanVien);
                hoaDon.setIdKhachHang(khachHang);
                hoaDon.setNgayMua(rs.getString("ngayMua"));
                System.out.println("ngaymua"+rs.getDate("ngaymua"));
                hoaDon.setThanhTien(rs.getBigDecimal("thanhTien"));
                hoaDon.setIdKhuyenMai(khuyenMai);
                hoaDon.setGhiChu(rs.getString("ghiChu"));
                hoaDon.setNgayTao(rs.getString("ngayTao"));
                hoaDon.setNgaySua(rs.getString("ngaySua"));
                hoaDon.setTrangThai(rs.getInt("trangThai"));
                hoaDon.setTienKhachDua(rs.getBigDecimal("TienKhachDua"));
                hoaDon.setTienThua(rs.getBigDecimal("TienThua"));
                lstHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstHoaDon;

    }
   public ArrayList<ChiTietSanPham> getAllChiTietSanPham(String idCTSP) {
        ArrayList<ChiTietSanPham> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT  csp.*\n" +
"FROM ChiTietHoaDon cthd\n" +
"JOIN ChiTietSanPham csp ON cthd.idChiTietSanPham = csp.Id\n" +
"WHERE cthd.Id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idCTSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChiTietSanPham sp = new ChiTietSanPham();
                sp.setId(rs.getString("id"));
                sp.setGiaBan(rs.getBigDecimal("giaBan"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public long generateRandomId() {
        return 10000 + new Random().nextInt(90000);
    }

    public Integer addTraHang(TraHang th) {
        Integer row = null;
        String sql = "INSERT INTO trahang\n" +
"                  (maTraHang, idNhanVien, idKhachHang,idHoaDon,ghichu,lyDo,soluong,tongtien, ngayTraHang,trangThai)\n" +
"VALUES (?,?,?,?,?,?,?,?,getDate(),1)";
        try (Connection cn = DBConnection.getConnection(); PreparedStatement ptm = cn.prepareStatement(sql)) {
             Long maHD = generateRandomId();
            ptm.setString(1, "TH" + maHD);
            ptm.setString(2, th.getIdNhanVien().getId());
            ptm.setString(3, th.getIdKhachHang().getId());
            ptm.setString(4, th.getIdHoaDon().getId());
            ptm.setString(5, th.getGhiChu());
            ptm.setString(6, th.getLyDo());
            ptm.setInt(7, th.getSoluong());
            ptm.setBigDecimal(8, th.getTongtien());
            
//            ptm.setString(2,);
            
           
            row = ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    
       public ArrayList getAllTraHang(String idHoaDon) {
        ArrayList<TraHang> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "Select * from trahang where idHoaDon = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHoaDon);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                             
                TraHang gh = new TraHang();
                gh.setId(rs.getString("id"));
                gh.setMa(rs.getString("matrahang"));
                gh.setSoluong(rs.getInt("soluong"));
                 gh.setTongtien(rs.getBigDecimal("tongtien"));
                 gh.setNgayTra(rs.getDate("ngayTraHang"));
                // Lấy thông tin khách hàng từ bảng KhachHang
                list.add(gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
