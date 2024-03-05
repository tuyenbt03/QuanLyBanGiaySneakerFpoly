/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.ChatLieu;
import repository.ChatLieuRepo;

/**
 *
 * @author PC
 */
public class ChatLieuService {
    private ChatLieuRepo repository = new ChatLieuRepo();
    
    public List<ChatLieu> getAllChatLieuConHoatDong(){
        return repository.getAllChatLieuConHoatDong();
    }
    public List<ChatLieu> getAllChatLieuNgungHoatDong(){
        return repository.getAllChatLieuNgungHoatDong();
    }
    public List<String> listMaCL(){
        return repository.getListMa();
    }
    
    public List<String> listTenCL(){
        return repository.getListTen();
    }
    
    public boolean insert(ChatLieu sp){
        return this.repository.insert(sp);
    }
    public boolean update(ChatLieu sp){
        return this.repository.update(sp);
    }
    
     public boolean delete(String id){
        return this.repository.delete(id);
    }
     
     public List<ChatLieu> timKiem(String tenCL){
         return repository.TimKiem(tenCL);
     }
       public void updateTrangThaiChatLieu(String id){
        repository.updateTrangThaiChatLieu(id);
    }
}
