/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author LENOVO
 */
public class HoaDonCho { 
    private String id;
    private String maHoaDon;
    private String idNhanVien;
    private String idKhachHang;
    private String sdt;
    private String ngayTao;
    private String tenKhuyenMai;
    private BigDecimal giaTrikhuyenMai;
    private BigDecimal thanhTien;
    private BigDecimal tienKhachDua;
    private BigDecimal tienThua;
    private BigDecimal tongTienGiam;
    private String ghiChu;
    private int trangThai;

    public HoaDonCho() {
    }

    public HoaDonCho(String id, String maHoaDon, String idNhanVien, String idKhachHang, String sdt, String ngayTao, String tenKhuyenMai, BigDecimal giaTrikhuyenMai, BigDecimal thanhTien, BigDecimal tienKhachDua, BigDecimal tienThua, BigDecimal tongTienGiam, String ghiChu, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.sdt = sdt;
        this.ngayTao = ngayTao;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTrikhuyenMai = giaTrikhuyenMai;
        this.thanhTien = thanhTien;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.tongTienGiam = tongTienGiam;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public BigDecimal getGiaTrikhuyenMai() {
        return giaTrikhuyenMai;
    }

    public void setGiaTrikhuyenMai(BigDecimal giaTrikhuyenMai) {
        this.giaTrikhuyenMai = giaTrikhuyenMai;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public BigDecimal getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(BigDecimal tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public BigDecimal getTienThua() {
        return tienThua;
    }

    public void setTienThua(BigDecimal tienThua) {
        this.tienThua = tienThua;
    }

    public BigDecimal getTongTienGiam() {
        return tongTienGiam;
    }

    public void setTongTienGiam(BigDecimal tongTienGiam) {
        this.tongTienGiam = tongTienGiam;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    

  
    
    
}
