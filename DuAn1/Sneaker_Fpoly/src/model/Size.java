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
public class Size {
    private String id;
    private String maSize;
    private String tenSize;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public Size() {
    }

    public Size(String id, String maSize, String tenSize, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.maSize = maSize;
        this.tenSize = tenSize;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }
        public Size(String id, String maSize, String tenSize, int trangThai) {
        this.id = id;
        this.maSize = maSize;
        this.tenSize = tenSize;
        this.trangThai = trangThai;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaSize() {
        return maSize;
    }

    public void setMaSize(String maSize) {
        this.maSize = maSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
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
        return  tenSize ;
    }
    
        public Object[] toDataRow() {
        return new Object[]{ maSize, tenSize, ngayTao, ngaySua, trangThai == 1 ? "Đang còn hàng" : "Hết hàng"};
    }
}
