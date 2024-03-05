/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.ChiTietSanPham;
import repository.SPCTRepository;

/**
 *
 * @author PC
 */
public class SPCTService {
    private SPCTRepository repository = new SPCTRepository();
   
    public int CheckTrung(ChiTietSanPham ctsp){
        return repository.checkTrungCtsp(ctsp);
    }
    public List<ChiTietSanPham> getAll(){
        return repository.getAll();
    }
    
    public boolean InsertCTSP(ChiTietSanPham ctsp) {
        return this.repository.add(ctsp);
    } 
    
    public boolean update(ChiTietSanPham model) {
        return this.repository.update(model);
    }
    
    public String getSanPhamByID(String id) {
        return repository.getSPByID(id);
    }

    public String getIDBySanPham(String ten) {
        return repository.getIDBySP(ten);
    }
    
    public List<String> getListDanhMuc() {
        return repository.getListDanhMuc();
    }
    
    public String getDanhMucByID(String id) {
        return repository.getDanhMucByID(id);
    }

    public String getIDByDanhMuc(String ten) {
        return repository.getIDByDanhMuc(ten);
    }
    
    public List<String> getListChatLieu() {
        return repository.getListChatLieu();
    }
    
    public String getChatLieuByID(String id) {
        return repository.getChatLieuByID(id);
    }

    public String getIDByChatLieu(String ten) {
        return repository.getIDByChatLieu(ten);
    }
    
    public List<String> getListHang() {
        return repository.getListHang();
    }
    
    public String getHangByID(String id) {
        return repository.getHangByID(id);
    }

    public String getIDByHang(String ten) {
        return repository.getIDByHang(ten);
    }
    
    public List<String> getListSize() {
        return repository.getListSize();
    }
    
    public String getSizeByID(String id) {
        return repository.getSizeByID(id);
    }

    public String getIDBySize(String ten) {
        return repository.getIDBySize(ten);
    }
    
    public List<String> getListMauSac() {
        return repository.getListMauSac();
    }

    public String getMauSacByID(String id) {
        return repository.getMauSacByID(id);
    }

    public String getIDByMauSac(String ten) {
        return repository.getIDByMauSac(ten);
    }
    public ArrayList<ChiTietSanPham> getSPCTByIDSP(String id){
        return repository.getListIDSP(id);
    }
    
    public ArrayList<ChiTietSanPham> timKiemCTSP(String timKiem){
        return repository.timKiemTenSPCT(timKiem);
    }
    
    public ArrayList<ChiTietSanPham> timKiemHangCTSP(String timKiem){
        return repository.timKiemHangSPCT(timKiem);
    }
    
    public ArrayList<ChiTietSanPham> timKiemChatLieuCTSP(String timKiem){
        return repository.timKiemChatLieuSPCT(timKiem);
    }
    
    public ArrayList<ChiTietSanPham> timKiemDanhMucCTSP(String timKiem){
        return repository.timKiemDanhMucSPCT(timKiem);
    }
    
    public ArrayList<ChiTietSanPham> timKiemSizeCTSP(String timKiem){
        return repository.timKiemSizeSPCT(timKiem);
    }
    
    public ArrayList<ChiTietSanPham> timKiemMauSacCTSP(String timKiem){
        return repository.timKiemMauSacSPCT(timKiem);
    }
}
