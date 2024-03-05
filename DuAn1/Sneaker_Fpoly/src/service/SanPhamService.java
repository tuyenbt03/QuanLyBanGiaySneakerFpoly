/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.SanPham;
import repository.SanPhamRepository;

/**
 *
 * @author PC
 */
public class SanPhamService {
    private SanPhamRepository repository = new SanPhamRepository();
    
    public List<SanPham> getAll(){
        return repository.getAll();
    }
    
    public List<SanPham> getAllSanPham(int page){
        return repository.getAllSanPham(page);
    }
    
    public int SoBanGhiSanPham(){
        return repository.SoBanGhiSanPham();
    }
    
    public SanPham getSanPhamByID(String id){
       return  repository.getSanPhamByID(id);
    }
    public List<String> listMaSP(){
        return repository.getListMa();
    }
    
    public List<String> listTenSP(){
        return repository.getListTen();
    }
    
    public boolean insert(SanPham sp){
        return this.repository.insert(sp);
    }
    public boolean update(SanPham sp){
        return this.repository.update(sp);
    }
    
    public List<SanPham> timKiem(String sp){
        return repository.TimKiem(sp);
    }
    
}
