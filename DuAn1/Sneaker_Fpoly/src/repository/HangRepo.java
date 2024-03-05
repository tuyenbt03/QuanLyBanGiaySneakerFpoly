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
import model.Hang;
import utilitys.DBConnection;

/**
 *
 * @author PC
 */
public class HangRepo {

    public List<Hang> getAllHangConHoatDong() {
        String query = """
                       SELECT [idHang]
                               ,[maHang]
                               ,[tenHang]
                               ,[ngayTao]
                               ,[ngaySua]
                               ,[trangThai]
                           FROM [dbo].[hang]
                       where trangThai = 1

                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<Hang> lists = new ArrayList<>();
            while (rs.next()) {
                Hang hang = new Hang();
                    hang.setId(rs.getString("idHang"));
                    hang.setMaHang(rs.getString("maHang"));
                    hang.setTenHang(rs.getString("tenHang"));
                    hang.setNgayTao(rs.getDate("ngayTao"));
                    hang.setNgaySua(rs.getDate("ngaySua"));
                    hang.setTrangThai(rs.getInt("trangThai"));
                lists.add(hang);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

     String sql = "select * from hang where idHang = ?";
    Hang hang = new Hang();

    public Hang getHangById(String id) {

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                hang.setId(rs.getString("idHang"));
                hang.setMaHang(rs.getString("maHang"));
                hang.setTenHang(rs.getString("tenHang"));
                hang.setNgayTao(rs.getDate("ngayTao"));
                hang.setNgaySua(rs.getDate("ngaySua"));
                hang.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return hang;
    }
    
    
    public ArrayList<Hang> getALLHangNgungHoatDong() {
        ArrayList<Hang> lstHang = new ArrayList<>();
        String sql = "SELECT * FROM dbo.hang WHERE trangThai = 0";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                Hang hang = new Hang();
                hang.setId(rs.getString(1));
                hang.setMaHang(rs.getString(2));
                hang.setTenHang(rs.getString(3));
                hang.setNgayTao(rs.getDate(4));
                hang.setNgaySua(rs.getDate(5));
                hang.setTrangThai(rs.getInt(6));
                lstHang.add(hang);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return lstHang;
    }

    public List<String> getListMa() {
        String query = """
                       SELECT [maHang]
                         FROM [dbo].[hang]
                       ORDER BY [maHang]
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
                       SELECT [tenHang]
                         FROM [dbo].[hang]
                       ORDER BY [maHang]
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

    public boolean insert(Hang cl) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = " INSERT INTO hang(idHang, maHang, tenHang, ngayTao, ngaySua, trangThai) VALUES (?,?,?,getDate(),null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, cl.getId());
            ps.setObject(2, cl.getMaHang());
            ps.setObject(3, cl.getTenHang());
            ps.setObject(4, cl.getTrangThai());
            ps.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Hang sp) {
        int check = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE hang SET tenHang = ?, ngaySua = getDate(),TrangThai = ? WHERE maHang = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, sp.getTenHang());
            ps.setObject(2, sp.getTrangThai());
            ps.setObject(3, sp.getMaHang());
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
            String sql = " DELETE FROM hang WHERE maHang = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void UpdateTrangThai(String id) {
        String sql = "UPDATE hang\n"
                + "SET trangthai = CASE\n"
                + "    WHEN trangThai = 0 THEN 1\n"
                + "    WHEN trangThai = 1 THEN 0\n"
                + "    ELSE trangThai\n"
                + "END\n"
                + "WHERE idHang like ? ";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ptsm.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public List<Hang> TimKiem(String tenCL) {
        String query = "SELECT [idHang]\n"
                + "      ,[maHang]\n"
                + "      ,[tenHang]\n"
                + "      ,[ngayTao]\n"
                + "      ,[ngaySua]\n"
                + "      ,[trangThai]\n"
                + "FROM [dbo].[hang]\n"
                + "WHERE [tenHang] LIKE CONCAT('%',?,'%') OR [maHang] LIKE CONCAT('%',?,'%');";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, tenCL);
            ps.setObject(2, tenCL);
            ResultSet rs = ps.executeQuery();
            List<Hang> lists = new ArrayList<>();
            while (rs.next()) {
                Hang pt = new Hang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
                lists.add(pt);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
