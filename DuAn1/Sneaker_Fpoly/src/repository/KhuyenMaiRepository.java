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
import java.util.UUID;
import model.KhuyenMai;
import utilitys.DBConnection;

/**
 *
 * @author DELL
 */
public class KhuyenMaiRepository {

    private static final int pageSize = 5;

    String sql = "Select * from KhuyenMai where id = ?";
    KhuyenMai khuyenMai = new KhuyenMai();
    

    public KhuyenMai getKhuyenMaiByIdTinh(String id) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                khuyenMai.setId(rs.getString(1));
                khuyenMai.setMaKhuyenMai(rs.getString(2));
                khuyenMai.setTenKhuyenMai(rs.getString(3));
                khuyenMai.setGiaTri(rs.getBigDecimal(4));
                khuyenMai.setDieuKienGiam(rs.getBigDecimal(5));
                khuyenMai.setNgayBatDau(rs.getDate(6));
                khuyenMai.setNgayKetThuc(rs.getDate(7));
//                khuyenMai.setHinhThucApDung(rs.getString(8));
                khuyenMai.setNgayTao(rs.getDate(9));
                khuyenMai.setNgaySua(rs.getDate(10));
                khuyenMai.setTrangThai(rs.getInt(11));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return khuyenMai;
    }

    public KhuyenMai getKhuyenMaiByMa(String maKM) {
        KhuyenMai km = new KhuyenMai();
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, maKM);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                km.setId(rs.getString(1));
                km.setMaKhuyenMai(rs.getString(2));
                km.setTenKhuyenMai(rs.getString(3));
                km.setGiaTri(rs.getBigDecimal(4));
                km.setDieuKienGiam(rs.getBigDecimal(5));
                km.setNgayBatDau(rs.getDate(6));
                km.setNgayKetThuc(rs.getDate(7));
                km.setHinhThucApDung(rs.getBoolean(8));
                
                km.setNgayTao(rs.getDate(9));
                km.setNgaySua(rs.getDate(10));
                km.setTrangThai(rs.getInt(11));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return km;

    }

    public ArrayList<KhuyenMai> getAll() {
        try {
            ArrayList<KhuyenMai> list = new ArrayList<>();
            java.sql.Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM KhuyenMai";
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId(rs.getString("Id"));
                km.setMaKhuyenMai(rs.getString("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setHinhThucApDung(rs.getBoolean("hinhThucApDung"));
                km.setGiaTri(rs.getBigDecimal("giaTri"));
                km.setDieuKienGiam(rs.getBigDecimal("dieuKienGiam"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                km.setTrangThai(rs.getInt("trangThai"));
//                String id = rs.getString(1);
//                String maKhuyenMai = rs.getString(2);
//                String tenKhuyenMai = rs.getString(3);
//                int soLuong = rs.getInt(8);
//                Boolean hinhThucApDung = rs.getBoolean(9);
////                String loaiGiamGia = rs.getString(10);
//                BigDecimal giaTri = rs.getBigDecimal(4);
//                BigDecimal giamToiDa = rs.getBigDecimal(5);
//                Date ngayBatDau = rs.getDate(6);
//                Date ngayKetThuc = rs.getDate(7);
//                int trangThai = rs.getInt(14);
//                list.add(new KhuyenMai(id, maKhuyenMai, tenKhuyenMai, giaTri, giamToiDa, ngayBatDau, ngayKetThuc, soLuong, hinhThucApDung, trangThai));
                list.add(km);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<KhuyenMai> getAllKhuyenMaiHoatDong(int page) {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM KhuyenMai WHERE trangThai = 1 ORDER BY ngayBatDau OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId(rs.getString("Id"));
                km.setMaKhuyenMai(rs.getString("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setHinhThucApDung(rs.getBoolean("hinhThucApDung"));
                km.setGiaTri(rs.getBigDecimal("giaTri"));
                km.setDieuKienGiam(rs.getBigDecimal("dieuKienGiam"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                km.setTrangThai(rs.getInt("trangThai"));
                list.add(km);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<KhuyenMai> getAllKhuyenMaiNgungHoatDong(int page) {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM KhuyenMai WHERE trangThai = 0 ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId(rs.getString("Id"));
                km.setMaKhuyenMai(rs.getString("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setHinhThucApDung(rs.getBoolean("hinhThucApDung"));
                km.setGiaTri(rs.getBigDecimal("giaTri"));
                km.setDieuKienGiam(rs.getBigDecimal("dieuKienGiam"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                km.setTrangThai(rs.getInt("trangThai"));
                list.add(km);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int SoBanGhiKhuyenMaiHD() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM KhuyenMai WHERE TrangThai = 1";
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

    public int SoBanGhiKhuyenMaiNHD() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM KhuyenMai WHERE TrangThai = 0";
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

    public KhuyenMai them(KhuyenMai k) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO [dbo].[KhuyenMai] ([Id],[maKhuyenMai], [tenKhuyenMai],[hinhThucApDung],[soLuong],[giaTri], [dieuKienGiam], [ngayBatDau],[ngayKetThuc],[trangThai]) \n"
                    + "VALUES(NewID(),?,?,?,?,?,?,?,?,1)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, k.getMaKhuyenMai());
            ps.setString(2, k.getTenKhuyenMai());
            ps.setBoolean(3, k.getHinhThucApDung());
            ps.setInt(4, k.getSoLuong());
            ps.setBigDecimal(5, k.getGiaTri());
            ps.setBigDecimal(6, k.getDieuKienGiam());
            ps.setDate(7, new Date(k.getNgayBatDau().getTime()));
            ps.setDate(8, new Date(k.getNgayKetThuc().getTime()));
//            ps.setInt(8, k.getTrangThai());
            ps.executeUpdate();
            return k;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

//    public KhuyenMai updateKhuyenMai(KhuyenMai km) {
//        try {
//            Connection con = DBConnection.getConnection();
//            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, hinhThucApDung = ?,soLuong = ?, giaTri = ?, giamToiDa = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = 1 WHERE maKhuyenMai = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, km.getTenKhuyenMai());
//            ps.setBoolean(2, km.getHinhThucApDung());
//            ps.setInt(3, km.getSoLuong());
//            ps.setBigDecimal(4, km.getGiaTri());
//            ps.setBigDecimal(5, km.getGiamToiDa());
//            ps.setDate(6, new Date(km.getNgayBatDau().getTime()));
//            ps.setDate(7, new Date(km.getNgayKetThuc().getTime()));
////            ps.setInt(8, km.getTrangThai());
//            ps.setString(8, km.getMaKhuyenMai());
//
//            ps.executeUpdate();
//            return km;
//
//            // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng, tức là câu lệnh UPDATE đã thành công
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
    public KhuyenMai updateKhuyenMai(KhuyenMai km) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, hinhThucApDung = ?,soLuong = ?, giaTri = ?, dieuKienGiam = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = 1 WHERE maKhuyenMai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, km.getTenKhuyenMai());
            ps.setBoolean(2, km.getHinhThucApDung());
            ps.setInt(3, km.getSoLuong());
            ps.setBigDecimal(4, km.getGiaTri());
            ps.setBigDecimal(5, km.getDieuKienGiam());
            ps.setDate(6, new Date(km.getNgayBatDau().getTime()));
            ps.setDate(7, new Date(km.getNgayKetThuc().getTime()));
//            ps.setInt(8, km.getTrangThai());
            ps.setString(8, km.getMaKhuyenMai());
//            ps.setString(10, km.getId());
//            ps.setObject(10, UUID.fromString(km.getId()));

            ps.executeUpdate();
            return km;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
