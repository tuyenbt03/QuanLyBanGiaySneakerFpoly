/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author acer
 */
public class TraHang {
    private String id;
    private String ma;
    private NhanVien idNhanVien;
    private KhachHang idKhachHang;
    private ChiTietSanPham idChiTietSanPham;
    private HoaDon idHoaDon;
    private Date ngayTra;
    private String lyDo;
    private String ghiChu;
    private int soluong;
    private BigDecimal tongtien;
    private int trangThai;

    public TraHang() {
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }
    
    public TraHang(String id, String ma, NhanVien idNhanVien, KhachHang idKhachHang, ChiTietSanPham idChiTietSanPham, HoaDon idHoaDon, Date ngayTra, String lyDo, String ghiChu, int soluong, BigDecimal tongtien, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idChiTietSanPham = idChiTietSanPham;
        this.idHoaDon = idHoaDon;
        this.ngayTra = ngayTra;
        this.lyDo = lyDo;
        this.ghiChu = ghiChu;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.trangThai = trangThai;
    }

    public TraHang(String id, NhanVien idNhanVien, KhachHang idKhachHang, ChiTietSanPham idChiTietSanPham, HoaDon idHoaDon, Date ngayTra, String lyDo, String ghiChu, int soluong, BigDecimal tongtien, int trangThai) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idChiTietSanPham = idChiTietSanPham;
        this.idHoaDon = idHoaDon;
        this.ngayTra = ngayTra;
        this.lyDo = lyDo;
        this.ghiChu = ghiChu;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NhanVien getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(NhanVien idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public ChiTietSanPham getIdChiTietSanPham() {
        return idChiTietSanPham;
    }

    public void setIdChiTietSanPham(ChiTietSanPham idChiTietSanPham) {
        this.idChiTietSanPham = idChiTietSanPham;
    }

    public HoaDon getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(HoaDon idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    
    

    
  
}
