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
import utilitys.JDBCHelper;

/**
 *
 * @author Chien Duong
 */
public class NhanVienRepositoryQuan {
      ChucVuRepository cvr = new ChucVuRepository();
    public ArrayList<NhanVien> getAllNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN ";
        ResultSet rs = JDBCHelper.excuteQuery(sql);

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                list.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), cv, rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public NhanVien getNVByID(String id) {
        String sql = "SELECT ID, maNhanVien,HOTEN FROM NhanVien WHERE ID=?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new NhanVien(rs.getString(1), rs.getString(2),rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public NhanVien insertNV(NhanVien nv) {
        String sql = "INSERT INTO NHANVIEN VALUES (NEWID(),?,?,?,?,?,?,?,?,?,?,GetDate(),null,?)";
        
        JDBCHelper.executeUpdate(sql, nv.getMaNV(), nv.getHoVaTen(), nv.getNgaySinh(), nv.getGioiTinh(), nv.getDiaChi(), nv.getSdt(), nv.getEmail(), nv.getMatKhau(), nv.getIdCV().getIdchucVu(), nv.getHinh(), nv.getTrangThai());
        return nv;
    }

    public NhanVien updateNV(NhanVien nv) {
        String sql = "UPDATE NHANVIEN SET HovaTen=?,NGAYSINH=?,GIOITINH=?,DIACHI=?,SDT=?,EMAIL=?,MATKHAU=?,IDCV=?,HINH=?, TRANGTHAI=?,NGAYSUA=GETDATE() WHERE maNhanVien=?";
        JDBCHelper.executeUpdate(sql, nv.getHoVaTen(), nv.getNgaySinh(), nv.getGioiTinh(), nv.getDiaChi(), nv.getSdt(), nv.getEmail(), nv.getMatKhau(), nv.getIdCV().getIdchucVu(), nv.getHinh(), nv.getTrangThai(),nv.getMaNV());
        return nv;
    }
    public ArrayList<NhanVien> getNVLam() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN WHERE TRANGTHAI=1";
        ResultSet rs = JDBCHelper.excuteQuery(sql);

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                list.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), cv, rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
        public ArrayList<NhanVien> getNVLamBySdt(String sdt){
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND SDT LIKE ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql,"%"+sdt+"%");

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                list.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), cv, rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
        public ArrayList<NhanVien> getNVLamByCV(String tenCV){
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND TenChucVu LIKE ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql,"%"+tenCV+"%");

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                list.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), cv, rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
     public ArrayList<NhanVien> getNVNghi() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN WHERE TRANGTHAI=0";
        ResultSet rs = JDBCHelper.excuteQuery(sql);

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                list.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), cv, rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
         public ArrayList<NhanVien> getNVNghiBySdt(String sdt){
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND SDT LIKE ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql,"%"+sdt+"%");

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                list.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), cv, rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
           public ArrayList<NhanVien> getNVNghiByCV(String tenCV){
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=0 AND TenChucVu LIKE ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql,"%"+tenCV+"%");

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                list.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), cv, rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepositoryQuan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
     
     
      public Integer deleteNV(String id) {
        String sql = "DELETE FROM NHANVIEN WHERE id";
        int row = JDBCHelper.executeUpdate(sql, id);
        return row;
    }
    public String updatePass(String pass, String ma) {
        String sql = "UPDATE NHANVIEN SET MATKHAU = ? WHERE maNhanVien = ?";
        JDBCHelper.executeUpdate(sql, pass,ma);
        return pass;
    }

    public NhanVien getNVByIDTinh(String id) {
        NhanVien nv =new NhanVien();
        String sql = "SELECT * FROM NhanVien WHERE id like ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idNV = rs.getString("id");
                String maNV = rs.getString("maNhanVien");
                String hoVaTen = rs.getString("hoVaTen");
                String gioiTinh = rs.getString("gioiTinh");
                Date NgaySinh = rs.getDate("ngaySinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String diaChi = rs.getString("diachi");
                ChucVu cv = this.cvr.getCVByID(rs.getString("IDCV"));
                String hinh = rs.getString("hinh");
                Date ngayTao = rs.getDate("ngayTao");
                Date ngaySua = rs.getDate("ngaySua");
                int trangThai = rs.getInt("TrangThai");
                nv.setId(idNV);
                nv.setDiaChi(diaChi);
                nv.setEmail(email);
                nv.setGioiTinh(gioiTinh);
                nv.setHinh(hinh);
                nv.setHoVaTen(hoVaTen);
                nv.setIdCV(cv);
                nv.setMaNV(maNV);
                nv.setMatKhau(matKhau);
                nv.setNgaySinh(NgaySinh);
                nv.setNgaySua(ngaySua);
                nv.setNgayTao(ngayTao);
                nv.setSdt(sdt);
                nv.setTrangThai(trangThai);
                
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }
    }


