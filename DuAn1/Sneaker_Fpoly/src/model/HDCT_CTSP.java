/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class HDCT_CTSP {
    private String id;
    private String maHoaDon;
    private String tenSanPham;
    private String tenHang;
    private String size;
    private String tenDanhMuc;
    private String chatLieu;
    private String mauSac;
    private String tenKhuyenMai;
    private int soLuong;
    private BigDecimal donGia;
    private Date ngayBan;
      private String tenKhachHang;

    public HDCT_CTSP() {
    }

    public HDCT_CTSP(String id, String maHoaDon, String tenSanPham, String tenHang, String size, String tenDanhMuc, String chatLieu, String mauSac, String tenKhuyenMai, int soLuong, BigDecimal donGia, Date ngayBan, String tenKhachHang) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenSanPham = tenSanPham;
        this.tenHang = tenHang;
        this.size = size;
        this.tenDanhMuc = tenDanhMuc;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.tenKhuyenMai = tenKhuyenMai;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayBan = ngayBan;
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
  
    public HDCT_CTSP(String id, String maHoaDon, String tenSanPham, String tenHang, String size, String tenDanhMuc, String chatLieu, String mauSac, String tenKhuyenMai, int soLuong, BigDecimal donGia, Date ngayBan) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenSanPham = tenSanPham;
        this.tenHang = tenHang;
        this.size = size;
        this.tenDanhMuc = tenDanhMuc;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.tenKhuyenMai = tenKhuyenMai;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayBan = ngayBan;
    }
  
    public HDCT_CTSP(String maHoaDon, String tenSanPham, String tenHang, String size, String tenDanhMuc, String chatLieu, String mauSac, String tenKhuyenMai, int soLuong, BigDecimal donGia, Date ngayBan) {
        this.maHoaDon = maHoaDon;
        this.tenSanPham = tenSanPham;
        this.tenHang = tenHang;
        this.size = size;
        this.tenDanhMuc = tenDanhMuc;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.tenKhuyenMai = tenKhuyenMai;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayBan = ngayBan;
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }
    
}
