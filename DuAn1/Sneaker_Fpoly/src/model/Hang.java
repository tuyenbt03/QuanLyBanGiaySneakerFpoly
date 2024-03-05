/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Hang {
    private String id;
    private String maHang;
    private String tenHang;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public Hang() {
    }

    public Hang(String id, String maHang, String tenHang, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }
    
    public Hang(String id, String maHang, String tenHang, int trangThai) {
        this.id = id;
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.trangThai = trangThai;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
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

    @Override
    public String toString() {
        return  tenHang;
    }
    public Object[] toDataRow() {
        return new Object[]{ maHang, tenHang, ngayTao, ngaySua, trangThai == 1 ? "Đang còn hàng" : "Hết hàng"};
    }
    
    
}
