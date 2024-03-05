/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.ChiTietSanPham;
import utility.DBConnection;
import java.sql.*;
import java.util.UUID;
import model.ChatLieu;
import model.DanhMuc;
import model.Hang;
import model.HoaDonCho;
import model.KhachHangQuan;
import model.KhuyenMai;
import model.MauSac;
import model.Size;

/**
 *
 * @author LENOVO
 */
public class BanHangRepository {
    private static final int PAGESIZE = 5;
    public ArrayList<ChiTietSanPham> getSanPhamChiTietForPage(int page) {
        ArrayList<ChiTietSanPham> listSanPhamChiTiet = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            int offset = (page - 1) * PAGESIZE;
            String query = "SELECT \n"
                + "    ChiTietSanPham.Id,\n"
                + "    SanPham.tenSanPham AS TenSanPham,\n"
                + "    Hang.tenHang AS TenHang,\n"
                + "    Size.Ten AS TenSize,\n"
                + "    DanhMuc.Ten AS TenDanhMuc,\n"
                + "    ChatLieu.Ten AS TenChatLieu,\n"
                + "    MauSac.Ten AS TenMauSac,\n"
                + "    ChiTietSanPham.giaNhap,\n"
                + "    ChiTietSanPham.giaBan,\n"
                + "    ChiTietSanPham.QR,\n"
                + "    ChiTietSanPham.HinhAnh,\n"
                + "    ChiTietSanPham.SoLuong,\n"
                + "    ChiTietSanPham.MoTa,\n"
                + "    ChiTietSanPham.NgayTao,\n"
                + "    ChiTietSanPham.NgaySua,\n"
                + "    ChiTietSanPham.TrangThai\n"
                + "FROM \n"
                + "    ChiTietSanPham \n"
                + "JOIN \n"
                + "    SanPham ON ChiTietSanPham.idSanPham = SanPham.Id\n"
                + "JOIN \n"
                + "    Hang ON ChiTietSanPham.hang = Hang.idHang\n"
                + "JOIN \n"
                + "    Size ON ChiTietSanPham.Size = Size.Id\n"
                + "JOIN \n"
                + "    DanhMuc ON ChiTietSanPham.DanhMuc = DanhMuc.Id\n"
                + "JOIN \n"
                + "    ChatLieu ON ChiTietSanPham.ChatLieu = ChatLieu.Id\n"
                + "JOIN \n"
                + "    MauSac ON ChiTietSanPham.MauSac = MauSac.Id \n"
                + "WHERE \n"
                + "    ChiTietSanPham.TrangThai = 1 \n"
                + "ORDER BY SoLuong OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            PreparedStatement st = con.prepareStatement(query);
            // Đặt giá trị cho tham số OFFSET
            st.setInt(1, offset);
            // Đặt giá trị cho tham số FETCH NEXT
            st.setInt(2, PAGESIZE);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChiTietSanPham spct = new ChiTietSanPham();
                spct.setId(rs.getString("Id"));
                spct.setSanPham((rs.getString("TenSanPham")));
                spct.setHang((rs.getString("TenHang")));
                spct.setSize((rs.getString("TenSize")));
                spct.setDanhMuc((rs.getString("TenDanhMuc")));
                spct.setChatLieu((rs.getString("TenChatLieu")));
                spct.setMauSac((rs.getString("TenMauSac")));
                spct.setGiaNhap(rs.getBigDecimal("giaNhap"));
                spct.setGiaBan(rs.getBigDecimal("giaBan"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setTrangThai(rs.getInt("TrangThai"));
                listSanPhamChiTiet.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSanPhamChiTiet;
    }
      
     public ArrayList<KhachHangQuan> getAllKH(String id) {
         ArrayList<KhachHangQuan> list = new ArrayList<>();
        try {
            
            Connection cn = DBConnection.getConnection();
            String sql = "SELECT * FROM KhachHang where id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, id);
               ResultSet rs = st.executeQuery();
            while (rs.next()){
                KhachHangQuan kh = new KhachHangQuan();
                kh.setIdKh(rs.getString("id"));
                kh.setMaKH(rs.getString("maKhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setGioiTinh(rs.getString("gioiTinh"));
                kh.setSdt(rs.getString("sdt"));
                list.add(kh);
            }   
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
          return list;
    }
      public ArrayList<KhachHangQuan> searchSDTKH( String sdt) {
        ArrayList<KhachHangQuan> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            

            String sql = "SELECT * FROM KhachHang\n"
                    + "WHERE KhachHang.sdt LIKE ?\n"
                    ;

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + sdt + "%"); // Thêm điều kiện tìm kiếm theo tên sản phẩm
           
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                KhachHangQuan kh = new KhachHangQuan();
                kh.setIdKh(rs.getString("id"));
                kh.setMaKH(rs.getString("maKhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setGioiTinh(rs.getString("gioiTinh"));
                kh.setSdt(rs.getString("sdt"));
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
     
     
     
    public int tongsoItem() {
        int tongSoTrang = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM ChiTietSanPham;";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                tongSoTrang = rs.getInt(1);
            }
            rs.close();
            pstm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSoTrang;
    }
    public ArrayList<ChiTietSanPham> search( String tenSanPham) {
        ArrayList<ChiTietSanPham> listSPCT = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            

            String sql = "SELECT \n"
                    + "    ChiTietSanPham.Id,\n"
                    + "    SanPham.tenSanPham AS TenSanPham,\n"
                    + "    Hang.tenHang AS TenHang,\n"
                    + "    Size.Ten AS TenSize,\n"
                    + "    DanhMuc.Ten AS TenDanhMuc,\n"
                    + "    ChatLieu.Ten AS TenChatLieu,\n"
                    + "    MauSac.Ten AS TenMauSac,\n"
                    + "    ChiTietSanPham.giaNhap,\n"
                    + "    ChiTietSanPham.giaBan,\n"
                    + "    ChiTietSanPham.QR,\n"
                    + "    ChiTietSanPham.HinhAnh,\n"
                    + "    ChiTietSanPham.SoLuong,\n"
                    + "    ChiTietSanPham.MoTa,\n"
                    + "    ChiTietSanPham.NgayTao,\n"
                    + "    ChiTietSanPham.NgaySua,\n"
                    + "    ChiTietSanPham.TrangThai\n"
                    + "FROM \n"
                    + "    ChiTietSanPham\n"
                    + "JOIN \n"
                    + "    SanPham ON ChiTietSanPham.idSanPham = SanPham.Id\n"
                    + "JOIN \n"
                    + "    Hang ON ChiTietSanPham.hang = Hang.idHang\n"
                    + "JOIN \n"
                    + "    Size ON ChiTietSanPham.Size = Size.Id\n"
                    + "JOIN \n"
                    + "    DanhMuc ON ChiTietSanPham.DanhMuc = DanhMuc.Id\n"
                    + "JOIN \n"
                    + "    ChatLieu ON ChiTietSanPham.ChatLieu = ChatLieu.Id\n"
                    + "JOIN \n"
                    + "    MauSac ON ChiTietSanPham.MauSac = MauSac.Id\n"
                    + "WHERE SanPham.tenSanPham LIKE ?\n"
                    ;

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + tenSanPham + "%"); // Thêm điều kiện tìm kiếm theo tên sản phẩm
           
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChiTietSanPham spct = new ChiTietSanPham();
                spct.setId(rs.getString("Id"));
                spct.setSanPham((rs.getString("TenSanPham")));
                spct.setHang((rs.getString("TenHang")));
                spct.setSize((rs.getString("TenSize")));
                spct.setDanhMuc((rs.getString("TenDanhMuc")));
                spct.setChatLieu((rs.getString("TenChatLieu")));
                spct.setMauSac((rs.getString("TenMauSac")));
                spct.setGiaNhap(rs.getBigDecimal("giaNhap"));
                spct.setGiaBan(rs.getBigDecimal("giaBan"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setTrangThai(rs.getInt("TrangThai"));
                listSPCT.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listSPCT;
    }
        public ArrayList<DanhMuc> getAllDanhMuc()  {
        ArrayList<DanhMuc> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from DanhMuc";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                DanhMuc dm = new DanhMuc();
                dm.setTenDanhMuc(rs.getString("ten"));
                list.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
        public ArrayList<MauSac> getAllMauSac()  {
        ArrayList<MauSac> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from MauSac";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MauSac ms = new MauSac();
                ms.setTenMau(rs.getString("ten"));
                list.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
        public ArrayList<Size> getAllSize()  {
        ArrayList<Size> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from Size";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Size s = new Size();
                s.setTenSize(rs.getString("ten"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
        public ArrayList<Hang> getAllHang()  {
        ArrayList<Hang> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from Hang";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Hang s = new Hang();
                s.setTenHang(rs.getString("tenHang"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
       public ArrayList<ChatLieu> getAllChatLieu()  {
        ArrayList<ChatLieu> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from ChatLieu";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ChatLieu s = new ChatLieu();
                s.setTenChatLieu(rs.getString("ten"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

     public Integer upDateThanhToanHoaDon(HoaDonCho hd){
    Integer row = null;
    String sql = "UPDATE HoaDon\n" +
    "SET  TienKhachDua =?, TienThua =?, thanhTien =?,ngayMua = getDate(),GhiChu = ?,TongTienGiam = ?,TrangThai = 1 where id = ?";
    try {
        Connection cn = DBConnection.getConnection();
        PreparedStatement ptm = cn.prepareStatement(sql);
        ptm.setBigDecimal(1, hd.getTienKhachDua());
        ptm.setBigDecimal(2, hd.getTienThua());
        ptm.setBigDecimal(3, hd.getThanhTien());
        ptm.setString(4, hd.getGhiChu());
        ptm.setBigDecimal(5, hd.getTongTienGiam());
        ptm.setString(6, hd.getId());
        row = ptm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return row;
}
     public Integer upDateThanhToanHoaDonCTByIDHD(String id){
    Integer row = null;
    String sql ="UPDATE ChiTietHoaDon\n" +
                "SET ngayBan = getDate(), trangThai = 1 where idHoaDon = ? ;";
    try {
        Connection cn = DBConnection.getConnection();
        PreparedStatement ptm = cn.prepareStatement(sql);
        ptm.setString(1, id);
        row = ptm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return row;
}
    public String getSdtKhachHang(String idHoaDon) {
    String sdt = null;
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT KhachHang.sdt " +
                     "FROM HoaDon " +
                     "JOIN KhachHang ON HoaDon.idKhachHang = KhachHang.ID " +
                     "WHERE HoaDon.ID = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, idHoaDon);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            sdt = rs.getString("sdt");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return sdt;
}
    public Integer upDateTrangThaiCTSP(String id,int trangthai){
    Integer row = null;
    String sql = "UPDATE ChiTietSanPham\n" +
    "SET  TrangThai = ? where id = ?";
    try {
        Connection cn = DBConnection.getConnection();
        PreparedStatement ptm = cn.prepareStatement(sql);
        ptm.setInt(1, trangthai);
        ptm.setString(2, id);
        row = ptm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return row;     
}
      public Integer upDateTrangThaiCTSPSL(int sl){
    Integer row = null;
    String sql = "UPDATE ChiTietSanPham\n" +
    "SET  TrangThai = 0 where soluong = ?";
    try {
        Connection cn = DBConnection.getConnection();
        PreparedStatement ptm = cn.prepareStatement(sql);
        ptm.setInt(1,sl);
        row = ptm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return row;     
}
     public ArrayList<KhuyenMai> getGiaGiamVaMaVoucher(String idHoaDon) { 
     ArrayList<KhuyenMai> list = new ArrayList<>();
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT KhuyenMai.giaTri,KhuyenMai.maKhuyenMai,KhuyenMai.hinhThucApDung " +
                     "FROM HoaDon " +
                     "JOIN KhuyenMai ON HoaDon.idKhuyenMai = KhuyenMai.ID " +
                     "WHERE HoaDon.ID = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, idHoaDon);
        ResultSet rs = st.executeQuery();
            while(rs.next()){
                KhuyenMai km = new KhuyenMai();
                km.setGiaTri(rs.getBigDecimal("giaTri"));
                km.setMaKhuyenMai(rs.getString("maKhuyenMai"));
                km.setHinhThucApDung(rs.getBoolean("hinhThucApDung"));
                list.add(km);
            }
    } catch (Exception e) {
        e.printStackTrace();
    }
      return list;
}
}





        
