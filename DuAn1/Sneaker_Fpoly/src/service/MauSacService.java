/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.MauSac;
import repository.MauSacRepo;

/**
 *
 * @author PC
 */
public class MauSacService {

    private MauSacRepo repository = new MauSacRepo();

    public List<MauSac> getAllMauSacConHoatDong() {
        return repository.getAllMauSacConHoatDong();
    }
    public List<MauSac> getAllMauSacNgungHoatDong(){
        return repository.getAllMauSacNgungHoatDong();
    }

    public List<String> listMaCL() {
        return repository.getListMa();
    }

    public List<String> listTenCL() {
        return repository.getListTen();
    }

    public boolean insert(MauSac sp) {
        return this.repository.insert(sp);
    }

    public boolean update(MauSac sp) {
        return this.repository.update(sp);
    }

    public boolean delete(String id) {
        return this.repository.delete(id);
    }

    public List<MauSac> timKiem(String tenMS) {
        return repository.TimKiem(tenMS);
    }

    public void updateTrangThaiMauSac(String id) {
        repository.updateTrangThaiMauSac(id);
    }
}
