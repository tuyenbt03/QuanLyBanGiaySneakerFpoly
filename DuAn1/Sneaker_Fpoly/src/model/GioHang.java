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
 * @author LENOVO
 */
public class GioHang {
    private String id;
    private String idHoaDon;
    private String idChiTietSanPham;
    private String SanPham;
    private String hang;
    private String size;
    private String danhMuc;
    private String chatLieu;
    private String mauSac;
    private String tenKhuyenMai;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private int soLuong;
    private BigDecimal donGia;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public GioHang() {
    }
//      public void updateDonGia() {
//        if (giaBan != null) {
//            this.donGia = this.giaBan.multiply(BigDecimal.valueOf(this.soLuong));
//        } else {
//            // Xử lý khi giaBan là null, ví dụ có thể đặt đơn giá bằng 0 hoặc giá trị mặc định khác
//            this.donGia = BigDecimal.ZERO; 
//        }
//    }

    public GioHang(String id, String idHoaDon, String idChiTietSanPham, String SanPham, String hang, String size, String danhMuc, String chatLieu, String mauSac, String tenKhuyenMai, BigDecimal giaNhap, BigDecimal giaBan, int soLuong, BigDecimal donGia, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idChiTietSanPham = idChiTietSanPham;
        this.SanPham = SanPham;
        this.hang = hang;
        this.size = size;
        this.danhMuc = danhMuc;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.donGia = donGia;
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

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdChiTietSanPham() {
        return idChiTietSanPham;
    }

    public void setIdChiTietSanPham(String idChiTietSanPham) {
        this.idChiTietSanPham = idChiTietSanPham;
    }

    public String getSanPham() {
        return SanPham;
    }

    public void setSanPham(String SanPham) {
        this.SanPham = SanPham;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
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

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
//        updateDonGia();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
//        updateDonGia();
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
   
}
