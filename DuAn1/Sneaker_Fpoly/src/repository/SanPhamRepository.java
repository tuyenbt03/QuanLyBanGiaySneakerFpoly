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
import java.util.List;
import model.ChiTietSanPham;
import model.SanPham;
import utilitys.DBConnection;

/**
 *
 * @author PC
 */
public class SanPhamRepository {

    public List<SanPham> getAll() {
        String query = """
                       SELECT [Id]
                               ,[maSanPham]
                               ,[tenSanPham]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[SanPham]
                         ORDER BY [maSanPham]
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<SanPham> lists = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();
                    sp.setId(rs.getString("Id"));
                    sp.setMaSanPham(rs.getString("maSanPham"));
                    sp.setTenSanPham(rs.getString("tenSanPham"));
                    sp.setNgayTao(rs.getDate("NgayTao"));
                    sp.setNgaySua(rs.getDate("NgaySua"));
                    sp.setTrangThai(rs.getInt("TrangThai"));
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ArrayList<SanPham> getAllSanPham(int page) {
        ArrayList<SanPham> list = new ArrayList<>();
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM SanPham ORDER BY Id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("Id"));
                    sp.setMaSanPham(rs.getString("maSanPham"));
                    sp.setTenSanPham(rs.getString("tenSanPham"));
                    sp.setNgayTao(rs.getDate("NgayTao"));
                    sp.setNgaySua(rs.getDate("NgaySua"));
                    sp.setTrangThai(rs.getInt("TrangThai"));
              
                list.add(sp);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public int SoBanGhiSanPham() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM SanPham";
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

    public SanPham getSPByID(String id) {
        String sql = " Select * from sanPham where id=?";
        SanPham sanPham = new SanPham();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                sanPham.setId(rs.getString("id"));
                sanPham.setMaSanPham(rs.getString("maSanPham"));
                sanPham.setTenSanPham(rs.getString("tenSanPham"));
                sanPham.setNgayTao(rs.getDate("ngayTao"));
                sanPham.setNgaySua(rs.getDate("ngaySua"));
                sanPham.setTrangThai(rs.getInt("trangThai"));
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return sanPham;
    }

    public List<String> getListMa() {
        String query = """
                       SELECT [maSanPham]
                         FROM [dbo].[SanPham]
                       ORDER BY [maSanPham]
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String ma = rs.getString(1);
                lists.add(ma);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<String> getListTen() {
        String query = """
                       SELECT [tenSanPham]
                         FROM [dbo].[SanPham]
                       ORDER BY [maSanPham]
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String ten = rs.getString(1);
                lists.add(ten);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean insert(SanPham sp) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = " INSERT INTO SanPham(Id, maSanPham, tenSanPham, NgayTao, NgaySua, TrangThai) VALUES (?,?,?,getDate(),null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, sp.getId());
            ps.setObject(2, sp.getMaSanPham());
            ps.setObject(3, sp.getTenSanPham());
            ps.setObject(4, sp.getTrangThai());
            ps.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(SanPham sp) {
        int check = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE SanPham SET tenSanPham = ?, NgaySua = getDate(),TrangThai = ? WHERE maSanPham = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getTrangThai());
            ps.setString(3, sp.getMaSanPham());
            check = ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SanPham> TimKiem(String tenDM) {
        String query = """
                       SELECT [Id]
                               ,[maSanPham]
                               ,[tenSanPham]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[SanPham]
                         WHERE [tenSanPham] like CONCAT('%',?,'%')
                       ORDER BY [maSanPham]
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, tenDM);
            ResultSet rs = ps.executeQuery();
            List<SanPham> lists = new ArrayList<>();
            while (rs.next()) {
                SanPham pt = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
                lists.add(pt);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public SanPham getSanPhamByID(String id) {
        SanPham sp = new SanPham();

        try {
            String sql = " select * from sanpham where id like ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                sp.setId(rs.getString(1));
                sp.setMaSanPham(rs.getString(2));
                sp.setTenSanPham(rs.getString(3));
                sp.setNgayTao(rs.getDate(4));
                sp.setNgaySua(rs.getDate(5));
                sp.setTrangThai(rs.getInt(6));
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
        return sp;
    }
//    public List<ChiTietSanPham> hienThiCTSP() {
//        String query = """
//                       SELECT Id, idSanPham, hang, Size, DanhMuc, ChatLieu, MauSac, idKhuyenMai, giaNhap, giaBan, QR, HinhAnh, SoLuong, MoTa, NgayTao
//                         FROM     ChiTietSanPham where idSanPham = ?;
//                       """;
//        try (Connection con = DBConnection.getConnection();
//                PreparedStatement ps = con.prepareStatement(query)) {
//            ResultSet rs = ps.executeQuery();
//            List<ChiTietSanPham> lists = new ArrayList<>();
//            while (rs.next()) {
//                ChiTietSanPham ctsp = new ChiTietSanPham();
//                ctsp.setId(rs.getString("Id"));
//                ctsp.setChatLieu(rs.getString("ChatLieu"));
//                ctsp.setDanhMuc(rs.getString("DanhMuc"));
//                ctsp.setId(rs.getString("Id"));
//                ctsp.setId(rs.getString("Id"));
//                ctsp.setId(rs.getString("Id"));
//                ctsp.setId(rs.getString("Id"));
//                ctsp.setId(rs.getString("Id"));
//                ctsp.setId(rs.getString("Id"));
//                
//            }
//            //return lists;
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return null;
//    } 

}
