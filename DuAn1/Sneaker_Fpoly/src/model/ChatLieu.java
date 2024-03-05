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
public class ChatLieu {
    private String id;
    private String maChatlieu;
    private String tenChatLieu;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public ChatLieu(){
    }

    public ChatLieu(String id, String maChatlieu, String tenChatLieu, int trangThai) {
        this.id = id;
        this.maChatlieu = maChatlieu;
        this.tenChatLieu = tenChatLieu;
    
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaChatlieu() {
        return maChatlieu;
    }

    public void setMaChatlieu(String maChatlieu) {
        this.maChatlieu = maChatlieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
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
        return tenChatLieu;
    }
        public Object[] toDataRow() {
        return new Object[]{ maChatlieu, tenChatLieu, ngayTao, ngaySua, trangThai == 1 ? "Đang còn hàng" : "Hết hàng"};
    }
    
    
}
