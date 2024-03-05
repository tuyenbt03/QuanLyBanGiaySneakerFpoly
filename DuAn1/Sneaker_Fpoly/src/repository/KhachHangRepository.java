/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhachHang;
import model.NhanVien;
import utilitys.DBConnection;

/**
 *
 * @author DELL
 */
public class KhachHangRepository {
//    private static final int pageSize = 5;

    public ArrayList<KhachHang> getAll() {
        try {
            ArrayList<KhachHang> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM KhachHang";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getString("ID"));
                kh.setMaKhachHang(rs.getString("makhachhang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                kh.setSdt(rs.getString("sdt"));
                kh.setEmail(rs.getString("email"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setGioiTinh(rs.getString("gioiTinh"));
                kh.setNgaySinh(rs.getDate("ngaysinh"));
                kh.setTrangThai(rs.getInt("trangThai"));
                list.add(kh);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<KhachHang> getAllKhachHangHoatDong(int page) {
        ArrayList<KhachHang> list = new ArrayList<>();
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM KhachHang WHERE trangThai = 1 ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getString("ID"));
                kh.setMaKhachHang(rs.getString("makhachhang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                kh.setSdt(rs.getString("sdt"));
                kh.setEmail(rs.getString("email"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setGioiTinh(rs.getString("gioiTinh"));
                kh.setNgaySinh(rs.getDate("ngaysinh"));
                kh.setTrangThai(rs.getInt("trangThai"));
                list.add(kh);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public ArrayList<KhachHang> getAllKhachHangNgungHoatDong(int page) {
        ArrayList<KhachHang> list = new ArrayList<>();
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM KhachHang WHERE trangThai = 0 ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getString("ID"));
                kh.setMaKhachHang(rs.getString("makhachhang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                kh.setSdt(rs.getString("sdt"));
                kh.setEmail(rs.getString("email"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setGioiTinh(rs.getString("gioiTinh"));
                kh.setNgaySinh(rs.getDate("ngaysinh"));
                kh.setTrangThai(rs.getInt("trangThai"));
                list.add(kh);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public int SoBanGhiKhachHangHD() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM KhachHang WHERE TrangThai = 1";
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                soBanGhi = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return soBanGhi;
    }
    
    public int SoBanGhiKhachHangNHD() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM KhachHang WHERE TrangThai = 1";
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                soBanGhi = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return soBanGhi;
    }

    public KhachHang them(KhachHang k) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO [dbo].[KhachHang] ([ID],[maKhachHang], [tenKhachHang],[sdt],[email], [diaChi], [gioiTinh],[ngaySinh],[trangThai]) VALUES(NewID(),?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, k.getMaKhachHang());
            ps.setString(2, k.getTenKhachHang());
            ps.setString(3, k.getSdt());
            ps.setString(4, k.getEmail());
            ps.setString(5, k.getDiaChi());
            ps.setString(6, k.getGioiTinh());
            ps.setDate(7, new Date(k.getNgaySinh().getTime()));
            ps.setInt(8, k.getTrangThai());
            ps.executeUpdate();
            return k;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public ArrayList<KhachHang> getKhachHangByTenKH(String tenKH) {
        ArrayList<KhachHang> kh = new ArrayList<>();
        String sql = "SELECT ID, maKhachHang, tenKhachHang, sdt , email, diaChi, gioiTinh, ngaySinh, trangThai FROM KhachHang WHERE tenKhachHang like ?";
        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, "%" + tenKH + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idKh = rs.getString(1);
                String maKH = rs.getString(2);
                tenKH = rs.getString(3);
                String sdt = rs.getString(4);
                String email = rs.getString(5);
                String diaChi = rs.getString(6);
                String gioiTinh = rs.getString(7);
                Date ngaySinh = rs.getDate(8);
                int trangThai = rs.getInt(9);
                kh.add(new KhachHang(sdt, maKH, diaChi, tenKH, diaChi, gioiTinh, email, sdt, ngaySinh, ngaySinh, trangThai, trangThai, ngaySinh, ngaySinh, trangThai));
            }
            c.close();
            ps.close();
            rs.close();
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang updateKhachHang(KhachHang kh) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE KhachHang SET tenKhachHang = ?, sdt = ?, email = ?, diaChi = ?, gioiTinh = ?, ngaySinh = ?, trangThai = ? WHERE maKhachHang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
//        ps.setString(1, kh.getMaKH());
            ps.setString(1, kh.getTenKhachHang());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getGioiTinh());
            ps.setDate(6, new Date(kh.getNgaySinh().getTime()));
            ps.setInt(7, kh.getTrangThai());
            ps.setString(8, kh.getMaKhachHang());

            // Thực thi câu lệnh UPDATE và kiểm tra số dòng được ảnh hưởng
            ps.executeUpdate();
            return kh;

            // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng, tức là câu lệnh UPDATE đã thành công
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Trả về null nếu có lỗi xảy ra hoặc không có dòng nào được ảnh hưởng
        return null;
    }

    public KhachHang getKhachHangById(String id) {
        KhachHang khachHang = new KhachHang();
        String sql = "select * from khachhang where id =?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                khachHang.setId(rs.getString("ID"));
                khachHang.setMaKhachHang(rs.getString("makhachhang"));
                khachHang.setLoaiKhachHang(rs.getString("loaikhachhang"));
                khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
                khachHang.setDiaChi(rs.getString("diaChi"));
                khachHang.setGioiTinh(rs.getString("gioiTinh"));
                khachHang.setEmail(rs.getString("email"));
                khachHang.setSdt(rs.getString("sdt"));
                khachHang.setNgaySinh(rs.getDate("ngaysinh"));
                khachHang.setNgayThamGia(rs.getDate("ngayThamGia"));
                khachHang.setTichDiem(rs.getInt("tichDiem"));
                khachHang.setDiemEXP(rs.getInt("diemEXP"));
                khachHang.setNgayTao(rs.getDate("ngayTao"));
                khachHang.setNgaySua(rs.getDate("ngaysua"));
                khachHang.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return khachHang;

    }

    public KhachHang getKhachHangByMa(String maKhachHang) {
        KhachHang kh = new KhachHang();
        try {
            String sql = "	SELECT * FROM dbo.KhachHang WHERE maKhachHang LIKE ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, maKhachHang);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                kh.setId(rs.getString("id"));
                kh.setMaKhachHang(rs.getString("makhachhang"));
                kh.setLoaiKhachHang(rs.getString("loaikhachhang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setGioiTinh(rs.getString("gioiTinh"));
                kh.setEmail(rs.getString("email"));
                kh.setSdt(rs.getString("sdt"));
                kh.setNgaySinh(rs.getDate("ngaysinh"));
                kh.setNgayThamGia(rs.getDate("ngayThamGia"));
                kh.setTichDiem(rs.getInt("tichDiem"));
                kh.setDiemEXP(rs.getInt("diemEXP"));
                kh.setNgayTao(rs.getDate("ngayTao"));
                kh.setNgaySua(rs.getDate("ngaysua"));
                kh.setTrangThai(rs.getInt("trangThai"));
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return kh;
    }
}
