/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.DanhMuc;
import repository.DanhMucRepo;

/**
 *
 * @author PC
 */
public class DanhMucService {
    private DanhMucRepo repository = new DanhMucRepo();
    
    public List<DanhMuc> getAllDanhMucConHoatDong(){
        return repository.getAllDanhMucConHoatDong();
    }
     public List<DanhMuc> getAllDanhMucNgungHoatDong(){
        return repository.getAllDanhMucNgungHoatDong();
    }
    public List<String> listMaCL(){
        return repository.getListMa();
    }
    
    public List<String> listTenCL(){
        return repository.getListTen();
    }
    
    public boolean insert(DanhMuc sp){
        return this.repository.insert(sp);
    }
    public boolean update(DanhMuc sp){
        return this.repository.update(sp);
    }
    
     public boolean delete(String id){
        return this.repository.delete(id);
    }
     
     public List<DanhMuc> timKiem(String tenDM){
         return repository.TimKiem(tenDM);
     }
       public void updateTrangThaiDanhMuc(String id){
        repository.updateTrangThaiDanhMuc(id);
    }
}
