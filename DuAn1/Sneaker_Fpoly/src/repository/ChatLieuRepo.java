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
import model.ChatLieu;
import model.Hang;
import utilitys.DBConnection;

/**
 *
 * @author PC
 */
public class ChatLieuRepo {
    public List<ChatLieu> getAllChatLieuConHoatDong() {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[ChatLieu]
                       where TrangThai = 1

                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<ChatLieu> lists = new ArrayList<>();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                    cl.setId(rs.getString("Id"));
                    cl.setMaChatlieu(rs.getString("Ma"));
                    cl.setTenChatLieu(rs.getString("Ten"));
                    cl.setNgayTao(rs.getDate("NgayTao"));
                    cl.setNgaySua(rs.getDate("NgaySua"));
                    cl.setTrangThai(rs.getInt("TrangThai"));
                lists.add(cl);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ChatLieu getChatLieuById(String id) {
        ChatLieu chatLieu = new ChatLieu();
        try {
            String sql = "select * from ChatLieu where Id = ?";
            Connection con = DBConnection.getConnection();

            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                chatLieu.setId(rs.getString("id"));
                chatLieu.setMaChatlieu(rs.getString("ma"));
                chatLieu.setTenChatLieu(rs.getString("ten"));
                chatLieu.setNgayTao(rs.getDate("ngayTao"));
                chatLieu.setNgaySua(rs.getDate("ngaySua"));
                chatLieu.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }

        return chatLieu;
    }
    
    public List<ChatLieu> getAllChatLieuNgungHoatDong() {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[ChatLieu]
                       where TrangThai = 0

                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<ChatLieu> lists = new ArrayList<>();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                    cl.setId(rs.getString("Id"));
                    cl.setMaChatlieu(rs.getString("Ma"));
                    cl.setTenChatLieu(rs.getString("Ten"));
                    cl.setNgayTao(rs.getDate("NgayTao"));
                    cl.setNgaySua(rs.getDate("NgaySua"));
                    cl.setTrangThai(rs.getInt("TrangThai"));
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
                         FROM [dbo].[ChatLieu]
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
                         FROM [dbo].[ChatLieu]
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
    
    public boolean insert(ChatLieu cl) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = " INSERT INTO ChatLieu(Id, Ma, Ten, NgayTao, NgaySua, TrangThai) VALUES (?,?,?,getDate(),null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, cl.getId());
            ps.setObject(2, cl.getMaChatlieu());
            ps.setObject(3, cl.getTenChatLieu());
            
            ps.setObject(4, cl.getTrangThai());
            ps.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ChatLieu sp) {
        int check = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE ChatLieu SET Ten = ?, NgaySua = getDate(),TrangThai = ? WHERE Ma = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, sp.getTenChatLieu());
            ps.setObject(2, sp.getTrangThai());
            ps.setObject(3, sp.getMaChatlieu());
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
          String sql = " DELETE FROM ChatLieu WHERE Ma = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, id);
          ps.execute();
          return true;
      } catch (Exception e) {
      e.printStackTrace();
      return false;
      }
  }
    
    public List<ChatLieu> TimKiem(String tenCL) {
        String query = """
                       SELECT [Id]
                               ,[Ma]
                               ,[Ten]
                               ,[NgayTao]
                               ,[NgaySua]
                               ,[TrangThai]
                           FROM [dbo].[ChatLieu]
                         WHERE [Ten] like CONCAT('%',?,'%')
                        OR [ma] LIKE CONCAT('%',?,'%')
                       """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, tenCL);
            ps.setObject(2, tenCL);
            ResultSet rs = ps.executeQuery();
            List<ChatLieu> lists = new ArrayList<>();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                    cl.setId(rs.getString("Id"));
                    cl.setMaChatlieu(rs.getString("Ma"));
                    cl.setTenChatLieu(rs.getString("Ten"));
                    cl.setNgayTao(rs.getDate("NgayTao"));
                    cl.setNgaySua(rs.getDate("NgaySua"));
                    cl.setTrangThai(rs.getInt("TrangThai"));
                lists.add(cl);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    public void updateTrangThaiChatLieu(String id){
        try {
            String sql = "UPDATE chatlieu\n"
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
