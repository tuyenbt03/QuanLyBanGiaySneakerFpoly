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
import model.DanhMuc;
import utilitys.DBConnection;

/**
 *
 * @author PC
 */
public class DanhMucRepo {

    public List<DanhMuc> getAllDanhMucConHoatDong() {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[DanhMuc]
                       where TrangThai = 1

                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<DanhMuc> lists = new ArrayList<>();
            while (rs.next()) {
                DanhMuc dm = new DanhMuc();
                    dm.setId(rs.getString("Id"));
                    dm.setMaDanhMuc(rs.getString("Ma"));
                    dm.setTenDanhMuc(rs.getString("Ten"));
                    dm.setNgayTao(rs.getDate("NgayTao"));
                    dm.setNgaySua(rs.getDate("NgaySua"));
                    dm.setTrangThai(rs.getInt("TrangThai"));
                lists.add(dm);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public DanhMuc getDanhMucById(String id) {
        DanhMuc danhMuc = new DanhMuc();
         try {
        String sql = "select * from danhmuc where id = ? ";
        Connection con = DBConnection.getConnection();
        
       
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                danhMuc.setId(rs.getString("id"));
                danhMuc.setMaDanhMuc(rs.getString("ma"));
                danhMuc.setTenDanhMuc(rs.getString("ten"));
                danhMuc.setNgayTao(rs.getDate("ngayTao"));
                danhMuc.setNgaySua(rs.getDate("ngaySua"));
                danhMuc.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return danhMuc;
    }
    
     public List<DanhMuc> getAllDanhMucNgungHoatDong() {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[DanhMuc]
                       where TrangThai = 0

                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<DanhMuc> lists = new ArrayList<>();
            while (rs.next()) {
                DanhMuc cl = new DanhMuc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
                lists.add(cl);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<String> getListMa() {
        String query = """
                       SELECT [Ma]
                         FROM [dbo].[DanhMuc]
                       ORDER BY [Ma]
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
                       SELECT [Ten]
                         FROM [dbo].[DanhMuc]
                       ORDER BY [Ma]
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

    public boolean insert(DanhMuc cl) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = " INSERT INTO DanhMuc(Id, Ma, Ten, NgayTao, NgaySua, TrangThai) VALUES (?,?,?,getDate(),null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, cl.getId());
            ps.setObject(2, cl.getMaDanhMuc());
            ps.setObject(3, cl.getTenDanhMuc());

            ps.setObject(4, cl.getTrangThai());
            ps.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(DanhMuc sp) {
        int check = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE DanhMuc SET Ten = ?, NgaySua = getDate(),TrangThai = ? WHERE Ma = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, sp.getTenDanhMuc());
            ps.setObject(2, sp.getTrangThai());
            ps.setObject(3, sp.getMaDanhMuc());
            check = ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = " DELETE FROM DanhMuc WHERE Ma = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DanhMuc> TimKiem(String tenCL) {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[DanhMuc]
                         WHERE [Ten] like CONCAT('%',?,'%')
                        OR [ma] LIKE CONCAT('%',?,'%')
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, tenCL);
            ps.setObject(2, tenCL);
            ResultSet rs = ps.executeQuery();
            List<DanhMuc> lists = new ArrayList<>();
            while (rs.next()) {
                DanhMuc pt = new DanhMuc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
                lists.add(pt);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public void updateTrangThaiDanhMuc(String id) {
        try {
            String sql = "UPDATE danhmuc\n"
                    + "SET trangthai = CASE\n"
                    + "    WHEN trangThai = 0 THEN 1\n"
                    + "    WHEN trangThai = 1 THEN 0\n"
                    + "    ELSE trangThai\n"
                    + "END\n"
                    + "WHERE id like ? ";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ptsm.executeUpdate();
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
}
