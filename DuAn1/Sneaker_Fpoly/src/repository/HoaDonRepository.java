/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.security.Timestamp;
import java.util.ArrayList;
import model.HoaDon;
import java.sql.ResultSet;
import utilitys.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KhachHang;
import model.KhuyenMai;
import model.NhanVien;

/**
 *
 * @author DELL
 */
public class HoaDonRepository {

    NhanVienRepositoryQuan nhanVienRepository = new NhanVienRepositoryQuan();
    KhachHangRepository1 khachHangRepository = new KhachHangRepository1();
    KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    KhuyenMai khuyenMai = new KhuyenMai();
    KhachHang khachHang = new KhachHang();
    NhanVien nhanVien = new NhanVien();
    private static final int pageSize = 5;
    String sql;

    public ArrayList<HoaDon> getAllHoaDon(int page) {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        sql = "SELECT * FROM dbo.HoaDon ORDER BY ThanhTien OFFSET ? ROWS FETCH NEXT ? ROWS ONLY; ";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                nhanVien = nhanVienRepository.getNVByIDTinh(rs.getString("idNhanVien"));
                khachHang = khachHangRepository.getKhachHangById(rs.getString("idKhachHang"));
                khuyenMai = khuyenMaiRepository.getKhuyenMaiByIdTinh(rs.getString("idKhuyenMai"));
                hoaDon.setId(rs.getString("id"));
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
                hoaDon.setIdNhanVien(nhanVien);
                hoaDon.setIdKhachHang(khachHang);
                hoaDon.setNgayMua(rs.getString("ngayMua"));
                System.out.println("ngaymua"+rs.getDate("ngaymua"));
                hoaDon.setThanhTien(rs.getBigDecimal("thanhTien"));
                hoaDon.setIdKhuyenMai(khuyenMai);
                hoaDon.setGhiChu(rs.getString("ghiChu"));
                hoaDon.setNgayTao(rs.getString("ngayTao"));
                hoaDon.setNgaySua(rs.getString("ngaySua"));
                hoaDon.setTrangThai(rs.getInt("trangThai"));
                hoaDon.setTienKhachDua(rs.getBigDecimal("TienKhachDua"));
                hoaDon.setTienThua(rs.getBigDecimal("TienThua"));
                lstHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstHoaDon;

    }

    public ArrayList<HoaDon> phanTrang(int page) {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        String sql2 = "SELECT * FROM dbo.HoaDon ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY; ";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql2);
            int ofset = (page - 1) * pageSize;
            ptsm.setInt(1, ofset);
            ptsm.setInt(2, pageSize);

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return lstHoaDon;
    }

    public HoaDon getHoaDonById(String id) {
        sql = "Select * from HoaDon where id = ?";
        HoaDon hoaDon = new HoaDon();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                nhanVien = nhanVienRepository.getNVByIDTinh(rs.getString("idNhanVien"));
                khachHang = khachHangRepository.getKhachHangById(rs.getString("idKhachHang"));
                khuyenMai = khuyenMaiRepository.getKhuyenMaiByIdTinh(rs.getString("idKhuyenMai"));
                hoaDon.setId(rs.getString("id"));
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
                hoaDon.setIdNhanVien(nhanVien);
                hoaDon.setIdKhachHang(khachHang);
                hoaDon.setNgayMua(rs.getString("ngayMua"));
                hoaDon.setThanhTien(rs.getBigDecimal("thanhTien"));
                hoaDon.setIdKhuyenMai(khuyenMai);
                hoaDon.setGhiChu(rs.getString("ghiChu"));
                hoaDon.setNgayTao(rs.getString("ngayTao"));
                hoaDon.setNgaySua(rs.getString("ngaySua"));
                hoaDon.setTrangThai(rs.getInt("trangThai"));
                hoaDon.setTienKhachDua(rs.getBigDecimal("TienKhachDua"));
                hoaDon.setTienThua(rs.getBigDecimal("TienThua"));

            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return hoaDon;
    }

    public ArrayList<HoaDon> searchHoaDon(String keyword) {
        ArrayList<HoaDon> listHD = new ArrayList<>();
        sql = "SELECT\n"
                + "    hd.ID,\n"
                + "    hd.maHoaDon,\n"
                + "    hd.idNhanVien,\n"
                + "    hd.idKhachHang,\n"
                + "    hd.ngayMua,\n"
                + "    hd.thanhTien,\n"
                + "    hd.idKhuyenMai,\n"
                + "    hd.GhiChu,\n"
                + "    hd.tienKhachDua,\n"
                + "    hd.tienThua,\n"
                + "    hd.NgayTao,\n"
                + "    hd.NgaySua,\n"
                + "    hd.TrangThai\n"
                + "FROM\n"
                + "    HoaDon hd\n"
                + "INNER JOIN\n"
                + "    NhanVien nv ON hd.idNhanVien = nv.ID\n"
                + "INNER JOIN\n"
                + "    KhachHang kh ON hd.idKhachHang = kh.ID\n"
                + "WHERE\n"
                + "    nv.hoVaTen LIKE CONCAT('%', ?, '%') OR\n"
                + "    kh.tenKhachHang LIKE CONCAT('%', ?, '%') OR\n"
                + "    hd.maHoaDon LIKE CONCAT('%', ?, '%')";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);

            ptsm.setString(1, keyword);
            ptsm.setString(2, keyword);
            ptsm.setString(3, keyword);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                NhanVien nv = nhanVienRepository.getNVByIDTinh(rs.getString("idNhanVien"));

                KhachHang kh = khachHangRepository.getKhachHangById(rs.getString("idKhachHang"));
                KhuyenMai km = khuyenMaiRepository.getKhuyenMaiByIdTinh(rs.getString("idKhuyenMai"));
                hd.setId(rs.getString("id"));
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setIdNhanVien(nv);
                hd.setIdKhachHang(kh);
                hd.setNgayMua(rs.getString("ngayMua"));
                hd.setThanhTien(rs.getBigDecimal("thanhTien"));
                hd.setIdKhuyenMai(km);
                hd.setGhiChu(rs.getString("ghiChu"));
                hd.setNgayTao(rs.getString("ngayTao"));
                hd.setNgaySua(rs.getString("NgaySua"));
                hd.setTrangThai(rs.getInt("trangThai"));
                hd.setTienKhachDua(rs.getBigDecimal("TienKhachDua"));
                hd.setTienThua(rs.getBigDecimal("TienThua"));
                listHD.add(hd);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return listHD;
    }

    public int SoBanGhi() {
        int soBanGhi = 0;

        try {
            Connection con = DBConnection.getConnection();
            sql = "Select count (*) from hoaDon";
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

    public HoaDon themHoaDon(HoaDon hoaDon) {
        try {
            Connection con = DBConnection.getConnection();
            sql = "	INSERT INTO dbo.HoaDon\n"
                    + "	(\n"
                    + "	   id, maHoaDon,\n"
                    + "	    idNhanVien,\n"
                    + "	    idKhachHang,\n"
                    + "	    ngayMua,\n"
                    + "	    thanhTien,\n"
                    + "	    idKhuyenMai,\n"
                    + "	    GhiChu,\n"
                    + "	    NgayTao,\n"
                    + "	    NgaySua,\n"
                    + "	    TrangThai\n"
                    + "	)\n"
                    + "	VALUES\n"
                    + "	(NEWID(),?,?,?,?,?,?,?,getdate(),null,1  \n"
                    + "	    )";
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, hoaDon.getMaHoaDon());
            ptsm.setString(2, hoaDon.getIdNhanVien().getId());
            ptsm.setString(3, hoaDon.getIdKhachHang().getId());
            ptsm.setString(4, String.valueOf(hoaDon.getNgayMua()));
            ptsm.setBigDecimal(5, hoaDon.getThanhTien());
            ptsm.setString(6, hoaDon.getIdKhuyenMai().getId());
            ptsm.setString(7, hoaDon.getGhiChu());

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return hoaDon;
    }

    public ArrayList<HoaDon> getHoaDonByDate(java.util.Date end, java.util.Date start, String kieuNgay) {
        ArrayList<HoaDon> lstHoaDonLoc = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            if (kieuNgay.equals("NgayTao")) {
                sql = "				SELECT * FROM dbo.HoaDon\n"
                        + "				WHERE ngayTao BETWEEN ? AND ?\n"
                        + "			ORDER BY NgayTao;";
            } else if (kieuNgay.equals("NgayMua")) {

                sql = "				SELECT * FROM dbo.HoaDon\n"
                        + "				WHERE ngayMua BETWEEN ? AND ?\n"
                        + "			ORDER BY NgayMua; ";
            }
            PreparedStatement ptsm = con.prepareStatement(sql);
            LocalDate st = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ed = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         
            ptsm.setString(1, String.valueOf(st));
            ptsm.setString(2, String.valueOf(ed));
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                NhanVien nv = nhanVienRepository.getNVByIDTinh(rs.getString("idNhanVien"));
                KhachHang kh = khachHangRepository.getKhachHangById(rs.getString("idKhachHang"));
                KhuyenMai km = khuyenMaiRepository.getKhuyenMaiByIdTinh(rs.getString("idKhuyenMai"));
                hd.setId(rs.getString("id"));
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setIdNhanVien(nv);
                hd.setIdKhachHang(kh);
                hd.setNgayMua(rs.getString("ngayMua"));
                hd.setThanhTien(rs.getBigDecimal("thanhTien"));
                hd.setIdKhuyenMai(km);
                hd.setGhiChu(rs.getString("ghiChu"));
                hd.setNgayTao(rs.getString("ngayTao"));
                hd.setNgaySua(rs.getString("NgaySua"));
                hd.setTrangThai(rs.getInt("trangThai"));
                hd.setTienKhachDua(rs.getBigDecimal("TienKhachDua"));
                hd.setTienThua(rs.getBigDecimal("TienThua"));
                lstHoaDonLoc.add(hd);

            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

        return lstHoaDonLoc;

    }
}
