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
public class MauSac {
    private String id;
    private String maMau;
    private String tenMau;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public MauSac() {
    }

    public MauSac(String id, String maMau, String tenMau, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.maMau = maMau;
        this.tenMau = tenMau;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }
    
    public MauSac(String id, String maMau, String tenMau, int trangThai) {
        this.id = id;
        this.maMau = maMau;
        this.tenMau = tenMau;
        this.trangThai = trangThai;
    } 
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
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
        return tenMau ;
    }
    public Object[] toDataRow() {
        return new Object[]{ maMau, tenMau, ngayTao, ngaySua, trangThai == 1 ? "Đang còn hàng" : "Hết hàng"};
    }
    
    
}
