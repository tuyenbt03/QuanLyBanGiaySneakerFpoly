package repository;

import java.util.List;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Random;
import model.HoaDonCho;
import java.sql.*;
import model.ChiTietSanPham;
import model.GioHang;
import model.ChiTietHoaDon;
import model.KhachHangQuan;
import model.NhanVien;
import utility.DBConnection;

/**
 *
 * @author LENOVO
 */
public class HoadonChoRepository {

    public long generateRandomId() {
        return 10000 + new Random().nextInt(90000);
    }

    public ArrayList<HoaDonCho> getAllHoaDonCho() {
        ArrayList<HoaDonCho> lstHDC = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT HoaDon.id, HoaDon.maHoaDon, NhanVien.hoVaTen, KhachHang.tenKhachHang,KhachHang.sdt,KhuyenMai.tenKhuyenMai,KhuyenMai.giaTri, HoaDon.ngayTao, HoaDon.TrangThai\n"
                    + "FROM HoaDon\n"
                    + "LEFT JOIN NhanVien ON HoaDon.idNhanVien = NhanVien.ID\n"
                    + "LEFT JOIN KhachHang ON HoaDon.idKhachHang = KhachHang.ID\n"
                    + "LEFT JOIN KhuyenMai ON HoaDon.idKhuyenMai = KhuyenMai.ID\n"
                    + "WHERE HoaDon.TrangThai = 0";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDonCho hdc = new HoaDonCho();
                hdc.setId(rs.getString("id"));
                hdc.setMaHoaDon(rs.getString("maHoaDon"));
                hdc.setSdt(rs.getString("sdt"));
                hdc.setIdNhanVien(rs.getString("hoVaTen"));
                hdc.setIdKhachHang(rs.getString("tenKhachHang"));
                hdc.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                hdc.setGiaTrikhuyenMai(rs.getBigDecimal("giaTri"));
                hdc.setNgayTao(rs.getString("ngayTao"));
                hdc.setTrangThai(rs.getInt("TrangThai"));
                lstHDC.add(hdc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHDC;
    }

    public NhanVien getNhanVienById(String id) {
        NhanVien nhanVien = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE Id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setId(rs.getString("Id"));
                nhanVien.setHoVaTen(rs.getString("hoVaTen"));
                // Thêm các trường khác tùy thuộc vào cấu trúc của bảng NhanVien
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

    public KhachHangQuan getKhachHangById(String id) {
        KhachHangQuan khachHang = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE Id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                khachHang = new KhachHangQuan();
                khachHang.setIdKh(rs.getString("Id"));
                khachHang.setTenKH(rs.getString("tenKhachHang"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHang;
    }
//       public KhachHang getChitietSanPhamById(String id) {
//           ChiTietSanPham chiTietSanPham = null;
//        try {
//            Connection con = DBConnection.getConnection();
//            String sql = "SELECT * FROM ChiTietSanPham WHERE Id = ?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, id);
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()) {
//                chiTietSanPham = new ChiTietSanPham();
//                chiTietSanPham.setId(rs.getString("Id"));
//                chiTietSanPham.set(rs.getString("tenKhachHang"));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return khachHang;
//    }

    public Integer addHoaDonCho(String idNhanVien) {
        Integer row = null;
        String sql = "INSERT INTO HoaDon (maHoaDon,idNhanVien, NgayTao, TrangThai)\n"
                + "VALUES (?,?,getDate(),?)";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            Long maHD = generateRandomId();
            ptm.setString(1, "HD" + maHD);
            ptm.setString(2, idNhanVien);
            ptm.setInt(3, 0);

            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void xoaHoaDonCho(String id) {
        String sql = "DELETE FROM HoaDon WHERE maHoaDon = ?";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer addHoaDonChiTiet(ChiTietHoaDon hdct) {
        Integer row = null;
        String sql = "INSERT INTO ChiTietHoaDon(idHoaDon, idChiTietSanPham, soLuong, donGia, ngayTao, trangThai)\n"
                + "VALUES (?,?,?,?,getDate(),?)";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setString(1, hdct.getIdHoaDon());
            ptm.setString(2, hdct.getIdChiTietSanPham());
            ptm.setInt(3, hdct.getSoLuong());
            ptm.setBigDecimal(4, hdct.getDonGia());
            ptm.setInt(5, 0);
            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public String getIdHoaDonByMa(String maHD) {
        String idHD = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT ID FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maHD);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                idHD = rs.getString("ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idHD;
    }

    public String getIdHoaDonCTByMaHD(String idHD) {

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT ID FROM ChiTietHoaDon WHERE idHoaDon = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHD);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                idHD = rs.getString("ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idHD;
    }

    public ArrayList getAllGioHang(String idHoaDon) {
        ArrayList<GioHang> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT ChiTietHoaDon.Id, ChiTietHoaDon.idHoaDon, ChiTietHoaDon.idChiTietSanPham, ChiTietHoaDon.soLuong, ChiTietHoaDon.donGia, ChiTietHoaDon.ngayBan, ChiTietHoaDon.ngayTao, ChiTietHoaDon.ngaySua, ChiTietHoaDon.trangThai, ChiTietSanPham.Id, SanPham.tenSanPham AS TenSanPham, Hang.tenHang AS TenHang, Size.Ten AS TenSize, DanhMuc.Ten AS TenDanhMuc, ChatLieu.Ten AS TenChatLieu, MauSac.Ten AS TenMauSac, ChiTietSanPham.giaBan AS giaBan\n"
                    + "FROM ChiTietHoaDon \n"
                    + "JOIN ChiTietSanPham ON ChiTietHoaDon.idChiTietSanPham = ChiTietSanPham.Id \n"
                    + "JOIN SanPham ON ChiTietSanPham.idSanPham = SanPham.Id \n"
                    + "JOIN Hang ON ChiTietSanPham.hang = Hang.idHang \n"
                    + "JOIN Size ON ChiTietSanPham.Size = Size.Id \n"
                    + "JOIN DanhMuc ON ChiTietSanPham.DanhMuc = DanhMuc.Id \n"
                    + "JOIN ChatLieu ON ChiTietSanPham.ChatLieu = ChatLieu.Id \n"
                    + "JOIN MauSac ON ChiTietSanPham.MauSac = MauSac.Id where idHoaDon = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHoaDon);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GioHang gh = new GioHang();
                gh.setId(rs.getString("id"));
                gh.setSoLuong(rs.getInt("soLuong"));
                gh.setNgayTao(rs.getDate("ngayTao"));
                gh.setDonGia(rs.getBigDecimal("donGia"));
                gh.setGiaBan(rs.getBigDecimal("giaBan"));
                gh.setTrangThai(rs.getInt("TrangThai"));
                gh.setSanPham((rs.getString("TenSanPham")));
                gh.setHang((rs.getString("TenHang")));
                gh.setSize((rs.getString("TenSize")));
                gh.setDanhMuc((rs.getString("TenDanhMuc")));
                gh.setChatLieu((rs.getString("TenChatLieu")));
                gh.setMauSac((rs.getString("TenMauSac")));

                // Lấy thông tin khách hàng từ bảng KhachHang
                list.add(gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from ChiTietHoaDon";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon hd = new ChiTietHoaDon();
                hd.setId(rs.getString("id"));
                hd.setIdChiTietSanPham(rs.getString("idChiTietSanPham"));
                hd.setIdHoaDon(rs.getString("idHoaDon"));
                hd.setSoLuong(rs.getInt("soLuong"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ChiTietHoaDon> getAllChiTietHoaDonByIDHD(String idHD) {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from ChiTietHoaDon Where idHoaDon = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHD);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon hd = new ChiTietHoaDon();
                hd.setId(rs.getString("id"));
                hd.setIdChiTietSanPham(rs.getString("idChiTietSanPham"));
                hd.setIdHoaDon(rs.getString("idHoaDon"));
                hd.setSoLuong(rs.getInt("soLuong"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ChiTietSanPham> getAllChiTietSanPham() {
        ArrayList<ChiTietSanPham> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from ChiTietSanPham";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChiTietSanPham sp = new ChiTietSanPham();
                sp.setId(rs.getString("id"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer updateSoLuongChiTietSanPham(int sl, String id) {
        Integer row = null;
        String sql = "UPDATE ChiTietSanPham SET ngaySua = getDate(),  SoLuong = ? WHERE  id = ?";

        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setInt(1, sl);
            ptm.setString(2, id);
            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public List<String> getIdCTSPByIDHD(String idHD) {
        List<String> listID = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT idChiTietSanPham FROM ChiTietHoaDon WHERE idHoaDon = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHD);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String idCTSP = rs.getString("idChiTietSanPham");
                listID.add(idCTSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listID;
    }

    public int getSoLuongCTHDByIDCTSP(String idCTSP) {
        int sl = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT soLuong FROM ChiTietHoaDon where idChiTietSanPham =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idCTSP);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                sl = rs.getInt("soLuong");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sl;
    }

    public Integer updateSoLuongChiTietHoaDonByIDCtsp(int sl, BigDecimal donGia, String idChiTietSanPham) {
        Integer row = null;
        String sql = "UPDATE ChiTietHoaDon SET  SoLuong = ?,donGia = ?,ngaySua = getDate() WHERE idChiTietSanPham = ?";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setInt(1, sl);
            ptm.setBigDecimal(2, donGia);
            ptm.setString(3, idChiTietSanPham);
            row = ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateSoLuongChiTietHoaDonByID(int sl, BigDecimal donGia, String id) {
        Integer row = null;
        String sql = "UPDATE ChiTietHoaDon SET  SoLuong = ?,donGia = ? ,ngaySua = getDate() WHERE id = ?";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setInt(1, sl);
            ptm.setBigDecimal(2, donGia);
            ptm.setString(3, id);
            row = ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void capNhatSoLuongChiTietSanPhamClear(String idChiTietSanPham, int soLuongCong) {
        String sql = "UPDATE ChiTietSanPham SET SoLuong = SoLuong + ? WHERE Id = ?";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setInt(1, soLuongCong);
            ptm.setString(2, idChiTietSanPham);
            ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaHoaDonChiTiet(String id) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE id = ? and TrangThai = 0";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaTatCaHoaDonChiTiet(String id) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE idHoaDon = ? AND TrangThai = 0";
        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ptm = cn.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIdCTSPByIDHDCT(String idHDCT) {
        String idCTSP = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT idChiTietSanPham FROM ChiTietHoaDon WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHDCT);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                idCTSP = rs.getString("idChiTietSanPham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCTSP;
    }

    public int getSoLuongCtspByID(String id) {
        int sl = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT SoLuong\n"
                    + "FROM     ChiTietSanPham where id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                sl = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sl;
    }

    public int checkTrungCtspAndGH(String idHD, String idCTSP) {
        int row = 0;
        try {
            Connection cn = DBConnection.getConnection();
            String sql = "SELECT\n"
                    + "    chd.Id AS ChiTietHoaDonId,\n"
                    + "\n"
                    + "\n"
                    + "    csp.Id AS ChiTietSanPhamId,\n"
                    + "    csp.idSanPham\n"
                    + "FROM\n"
                    + "    ChiTietHoaDon chd\n"
                    + "JOIN\n"
                    + "    ChiTietSanPham csp ON chd.idChiTietSanPham = csp.Id\n"
                    + "WHERE\n"
                    + "    chd.idHoaDon = ? \n"
                    + "    AND chd.idChiTietSanPham = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, idHD);
            st.setString(2, idCTSP);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                row = 1;
            }
        } catch (Exception e) {
        }
        return row;
    }

    public int checkTrungCtsp(ChiTietSanPham ctsp) {
        int row = 0;
        try {
            Connection cn = DBConnection.getConnection();
            String sql = "SELECT * FROM ChiTietSanPham WHERE idSanPham = ? AND hang = ? AND Size = ? AND DanhMuc = ? AND ChatLieu = ? AND MauSac = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, ctsp.getSanPham());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                row = 1;
            }
        } catch (Exception e) {
        }
        return row;
    }

    public BigDecimal getTongGiaTien(String idHD) {
        BigDecimal tongGia = BigDecimal.ZERO;
        try {
            Connection cn = DBConnection.getConnection();
            String sql = "SELECT ISNULL(SUM(donGia), 0) AS TongGia FROM ChiTietHoaDon WHERE idHoaDon = ? And TrangThai = 0";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, idHD);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tongGia = rs.getBigDecimal("TongGia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongGia;
    }

    public Integer updateKhachHangVaoHoaDonByID(String idHD, String idKH) {
        Integer row = null;
        String sql = "UPDATE HoaDon SET idKhachHang = ? WHERE id = ?";
        try (Connection cn = DBConnection.getConnection(); PreparedStatement ptm = cn.prepareStatement(sql)) {
            ptm.setString(1, idKH);
            ptm.setString(2, idHD);
            row = ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateKhuyenMaiVaoHoaDonByID(String idHD, String idKM) {
        Integer row = null;
        String sql = "UPDATE HoaDon SET idKhuyenMai = ? WHERE id = ?";
        try (Connection cn = DBConnection.getConnection(); PreparedStatement ptm = cn.prepareStatement(sql)) {

            ptm.setString(1, idKM);
            ptm.setString(2, idHD);

            row = ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

}
