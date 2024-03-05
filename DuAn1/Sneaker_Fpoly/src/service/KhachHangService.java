/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.KhachHangQuan;
import repository.KhachHangRepository1;



/**
 *
 * @author Thuan
 */
public class KhachHangService {
    KhachHangRepository1 khachHangRepository = new KhachHangRepository1();
    public ArrayList<KhachHangQuan> loadData(){
        return khachHangRepository.getAll();
    }
    public KhachHangQuan insert(KhachHangQuan k){
        return khachHangRepository.them(k);
    }
    public KhachHangQuan update(KhachHangQuan kh){
        return khachHangRepository.updateKhachHang(kh);
    }
    public ArrayList<KhachHangQuan> getByTenKH(String tenKH){
        return khachHangRepository.getKhachHangByTenKH(tenKH);
    }

    
}
