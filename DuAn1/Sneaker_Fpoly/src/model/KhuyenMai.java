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
public class KhuyenMai {
 private String id;
 private String maKhuyenMai;
 private String tenKhuyenMai;
 private BigDecimal giaTri;
 private BigDecimal dieuKienGiam;
 private Date ngayBatDau;
 private Date ngayKetThuc;
 private int soLuong;
 private Boolean hinhThucApDung;
 private Date ngayTao;
 private Date ngaySua;
 private int trangThai;
    public KhuyenMai() {
    }
    public KhuyenMai(String id, String maKhuyenMai, String tenKhuyenMai, BigDecimal giaTri, BigDecimal dieuKienGiam, Date ngayBatDau, Date ngayKetThuc, int soLuong, Boolean hinhThucApDung, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTri = giaTri;
        this.dieuKienGiam = dieuKienGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuong = soLuong;
        this.hinhThucApDung = hinhThucApDung;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, BigDecimal giaTri, BigDecimal dieuKienGiam, Date ngayBatDau, Date ngayKetThuc, int soLuong, Boolean hinhThucApDung, int trangThai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTri = giaTri;
        this.dieuKienGiam = dieuKienGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuong = soLuong;
        this.hinhThucApDung = hinhThucApDung;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public BigDecimal getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(BigDecimal giaTri) {
        this.giaTri = giaTri;
    }

    public BigDecimal getDieuKienGiam() {
        return dieuKienGiam;
    }

    public void setDieuKienGiam(BigDecimal dieuKienGiam) {
        this.dieuKienGiam = dieuKienGiam;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Boolean getHinhThucApDung() {
        return hinhThucApDung;
    }

    public void setHinhThucApDung(Boolean hinhThucApDung) {
        this.hinhThucApDung = hinhThucApDung;
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
