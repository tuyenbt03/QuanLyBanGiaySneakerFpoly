/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class HoaDon {
    private String id;
    private String maHoaDon;
    private NhanVien idNhanVien;
    private KhachHang idKhachHang;
    private String ngayMua;
    private BigDecimal thanhTien;
    private KhuyenMai idKhuyenMai;
    private BigDecimal tienKhachDua;
    private BigDecimal tienThua;
    private String ghiChu;
    private String ngayTao;
    private String ngaySua;
    private int trangThai;

    public HoaDon() {
    }

    public HoaDon(String id, String maHoaDon, NhanVien idNhanVien, KhachHang idKhachHang, String ngayMua, BigDecimal thanhTien, KhuyenMai idKhuyenMai, BigDecimal tienKhachDua, BigDecimal tienThua, String ghiChu, String ngayTao, String ngaySua, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.ngayMua = ngayMua;
        this.thanhTien = thanhTien;
        this.idKhuyenMai = idKhuyenMai;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
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

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public KhuyenMai getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(KhuyenMai idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

   

    
   
}
