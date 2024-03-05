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
public class ChiTietSanPham {
    private String id;
    private String SanPham;
    private String hang;
    private String size;
    private String danhMuc;
    private String chatLieu;
    private String mauSac;
    private String tenKhuyenMai;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private  int QR;
    private String hinhAnh;
    private int soLuong;
    private String moTa;
    private LocalDate ngayTao;
    private LocalDate ngaySua;
    private int trangThai;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String id, String SanPham, String hang, String size, String danhMuc, String chatLieu, String mauSac, String tenKhuyenMai, BigDecimal giaNhap, BigDecimal giaBan, int QR, String hinhAnh, int soLuong, String moTa, LocalDate ngayTao, LocalDate ngaySua, int trangThai) {
        this.id = id;
        this.SanPham = SanPham;
        this.hang = hang;
        this.size = size;
        this.danhMuc = danhMuc;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.QR = QR;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }
        public ChiTietSanPham(String id, String SanPham, BigDecimal giaNhap, BigDecimal giaBan, int soLuong, String hang, String chatLieu, String mauSac, String size, String danhMuc, String moTa, String hinhAnh, int trangThai) {
        this.id = id;
        this.SanPham = SanPham;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.hang = hang;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.size = size;
        this.danhMuc = danhMuc;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }
        public ChiTietSanPham(String SanPham, BigDecimal giaNhap, BigDecimal giaBan, int soLuong, String hang, String chatLieu, String mauSac, String size, String danhMuc, String moTa, String hinhAnh, int trangThai) {
        this.SanPham = SanPham;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.hang = hang;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.size = size;
        this.danhMuc = danhMuc;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    }

    public int getQR() {
        return QR;
    }

    public void setQR(int QR) {
        this.QR = QR;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDate getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(LocalDate ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
   

    
    

  
    
    
    
}
