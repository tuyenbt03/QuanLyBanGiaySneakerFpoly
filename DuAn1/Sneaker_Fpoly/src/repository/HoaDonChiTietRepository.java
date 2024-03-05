/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.HDCT_CTSP;
import java.util.ArrayList;
import model.HoaDonChiTiet;
import java.sql.Connection;
import utilitys.DBConnection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.ChiTietSanPham;
import model.HoaDon;

/**
 *
 * @author DELL
 */
public class HoaDonChiTietRepository {

    HDCT_CTSP hdct_spct = new HDCT_CTSP();
    HoaDonRepository hoaDonRepo = new HoaDonRepository();
    SPCTRepository CTSPRepo = new SPCTRepository();
    HoaDonRepository HDRepo = new HoaDonRepository();

    public ArrayList<HDCT_CTSP> getAllHoaDonChiTiet_CTSP() {
        ArrayList<HDCT_CTSP> listHDCT = new ArrayList<>();
        String sql = ("Select * from chitiethoadon");
        try {
            Connection con = DBConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HoaDon hoaDon = HDRepo.getHoaDonById(rs.getString("idHoaDon"));
                ChiTietSanPham ctsp = CTSPRepo.getChiTietSanPhamById(rs.getString("idChiTietSanPham"));
                hdct_spct.setMaHoaDon(hoaDon.getMaHoaDon());
                hdct_spct.setTenSanPham(ctsp.getSanPham());
                hdct_spct.setTenHang(ctsp.getHang());
                hdct_spct.setSize(ctsp.getSize());
                hdct_spct.setTenDanhMuc(ctsp.getDanhMuc());
                hdct_spct.setChatLieu(ctsp.getChatLieu());
                hdct_spct.setMauSac(ctsp.getMauSac());
                hdct_spct.setSoLuong(rs.getInt("soLuong"));
                hdct_spct.setDonGia(rs.getBigDecimal("donGia"));
                hdct_spct.setNgayBan(rs.getDate("ngayBan"));
                listHDCT.add(hdct_spct);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return listHDCT;
    }

    public int SoBanGhiHoaDonChiTiet() {
        int soBanGhi = 0;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "Select count (*) from chitiethoadon";
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

    public ArrayList<HDCT_CTSP> getByIdHoaDon(String id) {
        ArrayList<HDCT_CTSP> lstHDCT = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM chitiethoadon WHERE idhoadon LIKE ?";
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = HDRepo.getHoaDonById(rs.getString("idHoaDon"));
                ChiTietSanPham ctsp = CTSPRepo.getChiTietSanPhamById(rs.getString("idChiTietSanPham"));
                hdct_spct.setMaHoaDon(hoaDon.getMaHoaDon());
                hdct_spct.setTenSanPham(ctsp.getSanPham());
                hdct_spct.setTenHang(ctsp.getHang());
                hdct_spct.setSize(ctsp.getSize());
                hdct_spct.setTenDanhMuc(ctsp.getDanhMuc());
                hdct_spct.setChatLieu(ctsp.getChatLieu());
                hdct_spct.setMauSac(ctsp.getMauSac());
                hdct_spct.setSoLuong(rs.getInt("soLuong"));
                hdct_spct.setDonGia(rs.getBigDecimal("donGia"));
                hdct_spct.setNgayBan(rs.getDate("ngayBan"));
                lstHDCT.add(hdct_spct);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return lstHDCT;
    }
}
