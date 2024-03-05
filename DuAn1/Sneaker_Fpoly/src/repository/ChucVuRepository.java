/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChucVu;
import model.NhanVien;
import utilitys.DBConnection;

/**
 *
 * @author Chien Duong
 */
public class ChucVuRepository {
     private static final int pageSize = 5;
    String sql;
//
//    public ArrayList<ChucVu> getAll() {
//        try {
//            ArrayList<ChucVu> list = new ArrayList<>();
//            java.sql.Connection con = DBConnection.getConnection();
//            String query = "SELECT * FROM ChucVu";
//            java.sql.Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                String idchucVu = rs.getString(1);
//                String maChucVu = rs.getString(2);
//                String tenChucVu = rs.getString(3);
//                Date ngayTao = rs.getDate(4);
//                Date ngaySua = rs.getDate(5);
//                int trangThai = rs.getInt(6);
//                list.add(new ChucVu(idchucVu, maChucVu, tenChucVu, trangThai));
//            }
//            return list;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
        public ArrayList<ChucVu> getAllCV() {
        ArrayList<ChucVu> lstChucVu = new ArrayList<>();
        String sql = "SELECT * FROM ChucVu";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setIdchucVu(rs.getString("id"));
                cv.setMaChucVu(rs.getString("maChucVu"));
                cv.setTenChucVu(rs.getString("tenChucVu"));
                cv.setNgayTao(rs.getDate("ngayTao"));
                cv.setNgaySua(rs.getDate("ngaySua"));
                cv.setTrangThai(rs.getInt("trangThai"));
                lstChucVu.add(cv);
            }

            con.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstChucVu;
    }
           public ArrayList<ChucVu> getAllChucVu(int page) {
        ArrayList<ChucVu> lstChucVu = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM ChucVu ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setIdchucVu(rs.getString("id"));
                cv.setMaChucVu(rs.getString("maChucVu"));
                cv.setTenChucVu(rs.getString("tenChucVu"));
                cv.setNgayTao(rs.getDate("ngayTao"));
                cv.setNgaySua(rs.getDate("ngaySua"));
                cv.setTrangThai(rs.getInt("trangThai"));
                lstChucVu.add(cv);
            }

            con.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstChucVu;
    }
            public int SoBanGhi() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            sql = "Select count (*) from ChucVu";
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
        
     public ArrayList<ChucVu> getTenCV(){
          ArrayList<ChucVu> list = new ArrayList<>();
        try {
           
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM ChucVu ";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ChucVu cv= new ChucVu();
                cv.setTenChucVu(rs.getString("tenChucVu"));
                list.add(cv);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return list;
    }
    
    
// public ChucVu getCVByID(String idchucVu) {
//    ChucVu cv = null;
//    String sql = "SELECT * FROM CHUCVU WHERE id = ?";
//    try {
//        Connection con = DBConnection.getConnection();
//        PreparedStatement ps = con.prepareStatement(sql);
//
//        ps.setString(1, idchucVu);
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) {
//            idchucVu = rs.getString(1);
//            String maChucVu = rs.getString(2);
//            String tenChucVu = rs.getString(3);
//            Date ngayTao = rs.getDate(4);
//            Date ngaySua = rs.getDate(5);
//            int trangThai = rs.getInt(6);
//            cv = new ChucVu(idchucVu, maChucVu, tenChucVu, trangThai);
//        }
//
//        con.close();
//        ps.close();
//        rs.close();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return cv;
//}
     public ChucVu getCVByID(String idchucVu) {
    ChucVu cv = null;
    String sql = "SELECT * FROM CHUCVU WHERE id = ?";

    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ptsm = con.prepareStatement(sql);
        ptsm.setString(1, idchucVu);
        ResultSet rs = ptsm.executeQuery();

        while (rs.next()) {
            cv = new ChucVu();
            cv.setIdchucVu(rs.getString("id"));
            cv.setMaChucVu(rs.getString("maChucVu"));
            cv.setTenChucVu(rs.getString("tenChucVu"));
            cv.setNgayTao(rs.getDate("ngayTao"));
            cv.setNgaySua(rs.getDate("ngaySua"));
            cv.setTrangThai(rs.getInt("trangThai"));
        }
       ptsm.close();
        con.close();
        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return cv;
}

    public ChucVu insertCV(ChucVu cv) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO CHUCVU VALUES(NEWID(),?,?,GetDate(),null,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cv.getMaChucVu());
            ps.setString(2, cv.getTenChucVu());
            ps.setInt(3, cv.getTrangThai());
            ps.executeUpdate();
            return cv;
        } catch (Exception e) {
            System.out.println(""+e);
        }
        return null;
    }

    public ChucVu updateCV(ChucVu cv) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE ChucVu SET tenChucVu= ?, trangThai = ? WHERE maChucVu=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cv.getTenChucVu());
            ps.setInt(2, cv.getTrangThai());
            ps.setString(3, cv.getMaChucVu());

            ps.executeUpdate();
            return cv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//        public int SoBanGhi() {
//        int soBanGhi = 0;
//
//        try {
//            Connection con = DBConnection.getConnection();
//           String sql = "Select count (*) from ChucVu";
//            PreparedStatement ptsm = con.prepareStatement(sql);
//            ResultSet rs = ptsm.executeQuery();
//            while (rs.next()) {
//                soBanGhi = rs.getInt(1);
//            }
//        } catch (Exception e) {
//            System.out.println("" + e);
//        }
//        return soBanGhi;
//    }
}
