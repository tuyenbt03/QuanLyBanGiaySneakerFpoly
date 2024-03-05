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
import java.util.List;
import model.MauSac;
import utilitys.DBConnection;

/**
 *
 * @author PC
 */
public class MauSacRepo {
    public List<MauSac> getAllMauSacConHoatDong() {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[MauSac]
                       where TrangThai = 1

                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<MauSac> lists = new ArrayList<>();
            while (rs.next()) {
                MauSac ms = new MauSac();
                    ms.setId(rs.getString("Id"));
                    ms.setMaMau(rs.getString("Ma"));
                    ms.setTenMau(rs.getString("Ten"));
                    ms.setNgayTao(rs.getDate("NgayTao"));
                    ms.setNgaySua(rs.getDate("NgaySua"));
                    ms.setTrangThai(rs.getInt("TrangThai"));
                lists.add(ms);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    String sql = "select * from MauSac where Id = ?";
    MauSac mauSac = new MauSac();

    public MauSac getMauSacById(String id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                mauSac.setId(rs.getString(1));
                mauSac.setMaMau(rs.getString(2));
                mauSac.setTenMau(rs.getString(3));
                mauSac.setNgayTao(rs.getDate(4));
                mauSac.setNgaySua(rs.getDate(5));
                mauSac.setTrangThai(rs.getInt(6));

            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return mauSac;
    }
    
     public List<MauSac> getAllMauSacNgungHoatDong() {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[MauSac]
                       where TrangThai = 0

                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<MauSac> lists = new ArrayList<>();
            while (rs.next()) {
                MauSac cl = new MauSac(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
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
                         FROM [dbo].[MauSac]
                       ORDER BY [Ma]
                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
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
                         FROM [dbo].[MauSac]
                       ORDER BY [Ma]
                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
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
    
    public boolean insert(MauSac cl) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = " INSERT INTO MauSac(Id, Ma, Ten, NgayTao, NgaySua, TrangThai) VALUES (?,?,?,getDate(),null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, cl.getId());
            ps.setObject(2, cl.getMaMau());
            ps.setObject(3, cl.getTenMau());
            
            ps.setObject(4, cl.getTrangThai());
            ps.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(MauSac sp) {
        int check = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE MauSac SET Ten = ?, NgaySua = getDate(),TrangThai = ? WHERE Ma = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, sp.getTenMau());
            ps.setObject(2, sp.getTrangThai());
            ps.setObject(3, sp.getMaMau());
            check = ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(String id){
      try {
          Connection conn = DBConnection.getConnection();
          String sql = " DELETE FROM MauSac WHERE Ma = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, id);
          ps.execute();
          return true;
      } catch (Exception e) {
      e.printStackTrace();
      return false;
      }
  }
    
    public List<MauSac> TimKiem(String tenCL) {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[MauSac]
                         WHERE [Ten] like CONCAT('%',?,'%')
                        OR [ma] LIKE CONCAT('%',?,'%')
                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, tenCL);
            ps.setObject(2, tenCL);
            ResultSet rs = ps.executeQuery();
            List<MauSac> lists = new ArrayList<>();
            while (rs.next()) {
                MauSac pt = new MauSac(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
                lists.add(pt);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
     public void updateTrangThaiMauSac(String id){
        try {
            String sql = "UPDATE mausac\n"
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
