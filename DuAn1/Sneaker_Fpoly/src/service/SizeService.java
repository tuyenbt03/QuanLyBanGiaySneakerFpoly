/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Size;
import repository.SizeRepo;

/**
 *
 * @author PC
 */
public class SizeService {
    private SizeRepo repository = new SizeRepo();
    
    public List<Size> getAllSizeConHoatDong(){
        return repository.getAllSizeConHoatDong();
    }
      public List<Size> getAllSizeNgungHoatDong(){
        return repository.getAllSizeNgungHoatDong();
    }
    public List<String> listMaCL(){
        return repository.getListMa();
    }
    
    public List<String> listTenCL(){
        return repository.getListTen();
    }
    
    public boolean insert(Size sp){
        return this.repository.insert(sp);
    }
    public boolean update(Size sp){
        return this.repository.update(sp);
    }
    
     public boolean delete(String id){
        return this.repository.delete(id);
    }
     
     public List<Size> timKiem(String size){
         return repository.TimKiem(size);
     }
       public void updateTrangThaiSize(String id){
        repository.updateTrangThaiSize(id);
    }
}
