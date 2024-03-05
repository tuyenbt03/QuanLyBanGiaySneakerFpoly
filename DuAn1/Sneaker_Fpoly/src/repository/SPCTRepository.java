/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.DanhMuc;
import model.Hang;
import model.KhuyenMai;
import model.MauSac;
import model.SanPham;
import model.Size;
import utilitys.DBConnection;

/**
 *
 * @author PC
 */
public class SPCTRepository {
    
    HangRepo hangRepo = new HangRepo();
    SizeRepo sizeRepo = new SizeRepo();
    SanPhamRepository sanPhamRepo = new SanPhamRepository();
    DanhMucRepo danhMucRepo = new DanhMucRepo();
    ChatLieuRepo chatLieuRepo = new ChatLieuRepo();
    MauSacRepo mauSacRepo = new MauSacRepo();
    KhuyenMaiRepository khuyenMaiRepo = new KhuyenMaiRepository();
    
    private static final int pageSize = 5;
    String sql;

    public List<ChiTietSanPham> getAll() {
        String query = """
                       SELECT
                                CTS.Id,
                                SanPham.tenSanPham AS TenSanPham ,
                                CTS.giaNhap,
                                CTS.giaBan,
                                CTS.SoLuong,
                         	Hang.TenHang AS TenHang,
                                ChatLieu.Ten AS TenChatLieu,
                                MauSac.Ten AS TenMau,
                         	Size.Ten AS TenSize,
                                DanhMuc.Ten AS TenDanhMuc,
                                CTS.MoTa,
                                CTS.HinhAnh, 
                                CTS.TrangThai                        
                         FROM ChiTietSanPham AS CTS
                       
                        LEFT JOIN 
                             SanPham ON CTS.idSanPham = SanPham.Id
                        LEFT JOIN
                             MauSac ON CTS.MauSac = MauSac.id
                        LEFT JOIN
                             DanhMuc ON CTS.DanhMuc = DanhMuc.Id
                        LEFT JOIN
                             Size ON CTS.Size = Size.Id
                        LEFT JOIN
                             Hang ON CTS.hang = Hang.idHang
                        LEFT JOIN
                             ChatLieu ON CTS.ChatLieu = ChatLieu.id 
                       
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<ChiTietSanPham> lists = new ArrayList<>();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                        ctsp.setId(rs.getString("Id"));
                        ctsp.setSanPham(rs.getString("tenSanPham"));
                        ctsp.setHang(rs.getString("TenHang"));
                        ctsp.setSize(rs.getString("TenSize"));
                        ctsp.setDanhMuc(rs.getString("TenDanhMuc"));
                        ctsp.setChatLieu(rs.getString("TenChatLieu"));
                        ctsp.setMauSac(rs.getString("TenMau"));
                        ctsp.setGiaNhap(rs.getBigDecimal("giaNhap"));
                        ctsp.setGiaBan(rs.getBigDecimal("giaBan"));
                        ctsp.setHinhAnh(rs.getString("HinhAnh"));
                        ctsp.setSoLuong(rs.getInt("SoLuong"));
                        ctsp.setMoTa(rs.getString("MoTa"));
                        ctsp.setTrangThai(rs.getInt("TrangThai"));
                lists.add(ctsp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public List<ChiTietSanPham> getAllPhanTrang() {
        String query = """
                       SELECT * FROM ChiTietSanPham ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                                
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<ChiTietSanPham> lists = new ArrayList<>();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                        ctsp.setId(rs.getString("Id"));
                        ctsp.setSanPham(rs.getString(rs.getString("idSanPham")));
                        ctsp.setHang(rs.getString("TenHang"));
                        ctsp.setSize(rs.getString("TenSize"));
                        ctsp.setDanhMuc(rs.getString("TenDanhMuc"));
                        ctsp.setChatLieu(rs.getString("TenChatLieu"));
                        ctsp.setMauSac(rs.getString("TenMau"));
                        ctsp.setGiaNhap(rs.getBigDecimal("giaNhap"));
                        ctsp.setGiaBan(rs.getBigDecimal("giaBan"));
                        ctsp.setHinhAnh(rs.getString("HinhAnh"));
                        ctsp.setSoLuong(rs.getInt("SoLuong"));
                        ctsp.setMoTa(rs.getString("MoTa"));
                        ctsp.setTrangThai(rs.getInt("TrangThai"));
                lists.add(ctsp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ArrayList<ChiTietSanPham> phanTrang(int page) {
        ArrayList<ChiTietSanPham> lstCTSP = new ArrayList<>();
        String sql2 = "SELECT * FROM ChiTietSanPham ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY; ";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql2);
            int ofset = (page - 1) * pageSize;
            ptsm.setInt(1, ofset);
            ptsm.setInt(2, pageSize);

        } catch (Exception e) {
            System.out.println("" + e);
        }
        return lstCTSP;
    }
    
    public int SoBanGhi() {
        int soBanGhi = 0;

        try {
            Connection con = DBConnection.getConnection();
            sql = "Select count (*) from ChiTietSanPham";
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                soBanGhi = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return soBanGhi;
    }
    
    public ChiTietSanPham getChiTietSanPhamById(String id) {
        ChiTietSanPham ctsp = new ChiTietSanPham();

        try {
            String sql = "Select * from ChiTietSanPham where id = ?";
            Connection con = DBConnection.getConnection();

            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, id);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                ctsp.setId(rs.getString("id"));
                SanPham sanPham = sanPhamRepo.getSanPhamByID(rs.getString("idSanPham"));
                Hang hang = hangRepo.getHangById(rs.getString("hang"));
                Size size = sizeRepo.getSizeById(rs.getString("size"));
                DanhMuc danhMuc = danhMucRepo.getDanhMucById(rs.getString("danhmuc"));
                ChatLieu chatLieu = chatLieuRepo.getChatLieuById(rs.getString("chatlieu"));
                MauSac mauSac = mauSacRepo.getMauSacById(rs.getString("mausac"));
                ctsp.setSanPham(sanPham.getTenSanPham());
                ctsp.setHang(hang.getTenHang());
                ctsp.setSize(size.getTenSize());
                ctsp.setMauSac(mauSac.getTenMau());
                ctsp.setDanhMuc(danhMuc.getTenDanhMuc());
                ctsp.setChatLieu(chatLieu.getTenChatLieu());
                ctsp.setGiaNhap(rs.getBigDecimal("giaNhap"));
                ctsp.setGiaBan(rs.getBigDecimal("giaBan"));
                ctsp.setHinhAnh(rs.getString("hinhAnh"));
                ctsp.setSoLuong(rs.getInt("soLuong"));
                ctsp.setMoTa(rs.getString("moTa"));
                ctsp.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return ctsp;
    }

    public boolean add(ChiTietSanPham ctsp) {
        int check = 0;
        String query = "INSERT INTO dbo.ChiTietSanPham\n"
                + "(\n"
                + "    idSanPham,\n"
                + "    hang,\n"
                + "    Size,\n"
                + "    DanhMuc,\n"
                + "    ChatLieu,\n"
                + "    MauSac,\n"
                + "    giaNhap,\n"
                + "    giaBan,\n"
                + "    HinhAnh,\n"
                + "    SoLuong,\n"
                + "    MoTa,\n"
                + "    NgayTao,\n"
                + "    NgaySua,\n"
                + "    TrangThai\n"
                + ")\n"
                + "VALUES\n"
                + "( \n"
                + "   ?,?,?,?,?,?,?,?,?,?,?,getdate(),null,? )";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ctsp.getSanPham());
            ps.setString(2, ctsp.getHang());
            ps.setString(3, ctsp.getSize());
            ps.setString(4, ctsp.getDanhMuc());
            ps.setString(5, ctsp.getChatLieu());
            ps.setString(6, ctsp.getMauSac());
            ps.setObject(7, ctsp.getGiaNhap());
            ps.setObject(8, ctsp.getGiaBan());
            ps.setString(9, ctsp.getHinhAnh());
            ps.setInt(10, ctsp.getSoLuong());
            ps.setString(11, ctsp.getMoTa());
            ps.setInt(12, ctsp.getTrangThai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(ChiTietSanPham ctsp) {
        int check = 0;
        String query = """
                       UPDATE [dbo].[ChiTietSanPham]
                          SET [idSanPham] = ?
                       ,[giaNhap] = ?
                       ,[giaBan] = ?
                       ,[SoLuong] = ?
                       ,[hang] = ?
                       ,[ChatLieu] = ?
                       ,[MauSac] = ?
                       ,[Size] = ?
                       ,[DanhMuc] = ?
                       ,[MoTa] = ?
                       ,[HinhAnh] = ?
                       ,[NgaySua] = getDate()
                       ,[TrangThai] = ?
                        WHERE [Id] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, ctsp.getSanPham());
            ps.setObject(2, ctsp.getGiaNhap());
            ps.setObject(3, ctsp.getGiaBan());
            ps.setObject(4, ctsp.getSoLuong());
            ps.setObject(5, ctsp.getHang());
            ps.setObject(6, ctsp.getChatLieu());
            ps.setObject(7, ctsp.getMauSac());
            ps.setObject(8, ctsp.getSize());
            ps.setObject(9, ctsp.getDanhMuc());
            ps.setObject(10, ctsp.getMoTa());
            ps.setObject(11, ctsp.getHinhAnh());
            ps.setObject(12, ctsp.getTrangThai());
            ps.setObject(13, ctsp.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<ChiTietSanPham> getListIDSP(String id) {
        ArrayList<ChiTietSanPham> lists = new ArrayList<>();
        String query = "SELECT\n"
                + "                                CTS.Id,\n"
                + "                                dbo.SanPham.tenSanPham as tensanpham ,\n"
                + "                                CTS.giaNhap,\n"
                + "                                CTS.giaBan,\n"
                + "                                CTS.SoLuong,\n"
                + "                         	Hang.TenHang ,\n"
                + "                                ChatLieu.Ten as tenchatlieu,\n"
                + "                                MauSac.Ten as tenmau,\n"
                + "                         	Size.Ten as tensize,\n"
                + "                                DanhMuc.Ten as tendanhmuc,\n"
                + "                                CTS.MoTa,\n"
                + "                                CTS.HinhAnh, \n"
                + "                                CTS.TrangThai\n"
                + "                         \n"
                + "                         FROM\n"
                + "                             ChiTietSanPham AS CTS\n"
                + "                         	JOIN dbo.SanPham ON CTS.idSanPham=dbo.SanPham.Id\n"
                + "                         JOIN\n"
                + "                             MauSac ON CTS.MauSac = MauSac.id\n"
                + "                         JOIN\n"
                + "                             DanhMuc ON CTS.DanhMuc = DanhMuc.Id\n"
                + "                         JOIN\n"
                + "                             Size ON CTS.Size = Size.Id\n"
                + "                         JOIN\n"
                + "                             Hang ON CTS.hang = Hang.idHang\n"
                + "                         JOIN\n"
                + "                         	dbo.ChatLieu ON cts.ChatLieu=ChatLieu.id \n"
                + "							WHERE CTS.idSanPham LIKE ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal giaNhap = new BigDecimal(rs.getString("gianhap"));
                BigDecimal giaBan = new BigDecimal(rs.getString("giaBan"));
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setId(rs.getString("id"));
                ctsp.setSanPham(rs.getString("tensanpham"));
                ctsp.setGiaNhap(giaNhap);
                ctsp.setGiaBan(giaBan);
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setHang(rs.getString("tenhang"));
                ctsp.setChatLieu(rs.getString("tenchatlieu"));
                ctsp.setMauSac(rs.getString("tenmau"));
                ctsp.setSize(rs.getString("tensize"));
                ctsp.setDanhMuc(rs.getString("tendanhmuc"));
                ctsp.setMoTa(rs.getString("mota"));
                ctsp.setHinhAnh(rs.getString("hinhanh"));
                ctsp.setTrangThai(rs.getInt("trangthai"));

                lists.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<String> getListSP() {
        String query = """
                       SELECT [tenSanPham]
                         FROM [dbo].[SanPham]
                       Order By [maSanPham] asc
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String sp = rs.getString(1);
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getSPByID(String id) {
        String query = """
                       SELECT [tenSanPham]
                         FROM [dbo].[SanPham]
                       Where [Id] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getIDBySP(String ten) {
        String query = """
                       SELECT [Id]
                         FROM [dbo].[SanPham]
                       Where [tenSanPham] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<String> getListDanhMuc() {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[DanhMuc]
                       Order By [Ma] asc
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String sp = rs.getString(1);
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getDanhMucByID(String id) {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[DanhMuc]
                       Where [Id] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getIDByDanhMuc(String ten) {
        String query = """
                       SELECT [Id]
                         FROM [dbo].[DanhMuc]
                       Where [Ten] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<String> getListHang() {
        String query = """
                       SELECT [tenHang]
                         FROM [dbo].[hang]
                       Order By [maHang] asc
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String sp = rs.getString(1);
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getHangByID(String id) {
        String query = """
                       SELECT [tenHang]
                         FROM [dbo].[hang]
                       Where [idHang] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getIDByHang(String ten) {
        String query = """
                       SELECT [idHang]
                         FROM [dbo].[hang]
                       Where [tenHang] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<String> getListSize() {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[Size]
                       Order By [Ma] asc
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String sp = rs.getString(1);
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getSizeByID(String id) {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[Size]
                       Where [Id] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getIDBySize(String ten) {
        String query = """
                       SELECT [Id]
                         FROM [dbo].[Size]
                       Where [Ten] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<String> getListMauSac() {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[MauSac]
                       Order By [Ma] asc
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String sp = rs.getString(1);
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getMauSacByID(String id) {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[MauSac]
                       Where [Id] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getIDByMauSac(String ten) {
        String query = """
                       SELECT [Id]
                         FROM [dbo].[MauSac]
                       Where [Ten] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<String> getListChatLieu() {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[ChatLieu]
                       Order By [Ma] asc
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<String> lists = new ArrayList<>();
            while (rs.next()) {
                String sp = rs.getString(1);
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getChatLieuByID(String id) {
        String query = """
                       SELECT [Ten]
                         FROM [dbo].[ChatLieu]
                       Where [Id] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String getIDByChatLieu(String ten) {
        String query = """
                       SELECT [Id]
                         FROM [dbo].[ChatLieu]
                       Where [Ten] = ?
                       """;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ArrayList<ChiTietSanPham> timKiemTenSPCT(String timKiem) {
        ArrayList<ChiTietSanPham> lstCTSP = new ArrayList<>();
        System.out.println("" + timKiem);
        try {
            String sql = "SELECT\n"
                    + "    CTS.Id,\n"
                    + "    dbo.SanPham.tenSanPham AS tensanpham,\n"
                    + "    CTS.giaNhap,\n"
                    + "    CTS.giaBan,\n"
                    + "    CTS.SoLuong,\n"
                    + "    Hang.TenHang,\n"
                    + "    ChatLieu.Ten AS tenchatlieu,\n"
                    + "    MauSac.Ten AS tenmau,\n"
                    + "    Size.Ten AS tensize,\n"
                    + "    DanhMuc.Ten AS tendanhmuc,\n"
                    + "    CTS.MoTa,\n"
                    + "    CTS.HinhAnh,\n"
                    + "    CTS.TrangThai\n"
                    + "FROM\n"
                    + "    ChiTietSanPham AS CTS\n"
                    + "JOIN\n"
                    + "    dbo.SanPham ON CTS.idSanPham = dbo.SanPham.Id\n"
                    + "JOIN\n"
                    + "    MauSac ON CTS.MauSac = MauSac.id\n"
                    + "JOIN\n"
                    + "    DanhMuc ON CTS.DanhMuc = DanhMuc.Id\n"
                    + "JOIN\n"
                    + "    Size ON CTS.Size = Size.Id\n"
                    + "JOIN\n"
                    + "    Hang ON CTS.hang = Hang.idHang\n"
                    + "JOIN\n"
                    + "    dbo.ChatLieu ON cts.ChatLieu = ChatLieu.id\n"
                    + "WHERE\n"
                    + "    dbo.SanPham.tenSanPham LIKE '%' + ? + '%' -- Search by product name\n";
//                    + "    OR Hang.TenHang LIKE '%' + ? + '%' -- Search by brand name\n"
//                    + "    OR Size.Ten LIKE '%' + ? + '%' -- Search by size\n"
//                    + "    OR MauSac.Ten LIKE '%' + ? + '%' -- Search by color\n"
//                    + "    OR DanhMuc.Ten LIKE '%' + ? + '%' -- Search by category\n"
//                    + "    OR ChatLieu.Ten LIKE '%' + ? + '%' -- Search by material;";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, timKiem);
//            ptsm.setString(2, timKiem);
//            ptsm.setString(3, timKiem);
//            ptsm.setString(4, timKiem);
//            ptsm.setString(5, timKiem);
//            ptsm.setString(6, timKiem);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                BigDecimal giaNhap = new BigDecimal(rs.getString("gianhap"));
                BigDecimal giaBan = new BigDecimal(rs.getString("giaBan"));
                ctsp.setId(rs.getString("id"));
                ctsp.setSanPham(rs.getString("tensanpham"));
                ctsp.setGiaNhap(giaNhap);
                ctsp.setGiaBan(giaBan);
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setHang(rs.getString("tenhang"));
                ctsp.setChatLieu(rs.getString("tenchatlieu"));
                ctsp.setMauSac(rs.getString("tenmau"));
                ctsp.setSize(rs.getString("tensize"));
                ctsp.setDanhMuc(rs.getString("tendanhmuc"));
                ctsp.setMoTa(rs.getString("mota"));
                ctsp.setHinhAnh(rs.getString("hinhanh"));
                ctsp.setTrangThai(rs.getInt("trangthai"));
                lstCTSP.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println("");
        }
        return lstCTSP;
    }
    
    public ArrayList<ChiTietSanPham> timKiemHangSPCT(String timKiem) {
        ArrayList<ChiTietSanPham> lstCTSP = new ArrayList<>();
        System.out.println("" + timKiem);
        try {
            String sql = "SELECT\n"
                    + "    CTS.Id,\n"
                    + "    dbo.SanPham.tenSanPham AS tensanpham,\n"
                    + "    CTS.giaNhap,\n"
                    + "    CTS.giaBan,\n"
                    + "    CTS.SoLuong,\n"
                    + "    Hang.TenHang,\n"
                    + "    ChatLieu.Ten AS tenchatlieu,\n"
                    + "    MauSac.Ten AS tenmau,\n"
                    + "    Size.Ten AS tensize,\n"
                    + "    DanhMuc.Ten AS tendanhmuc,\n"
                    + "    CTS.MoTa,\n"
                    + "    CTS.HinhAnh,\n"
                    + "    CTS.TrangThai\n"
                    + "FROM\n"
                    + "    ChiTietSanPham AS CTS\n"
                    + "JOIN\n"
                    + "    dbo.SanPham ON CTS.idSanPham = dbo.SanPham.Id\n"
                    + "JOIN\n"
                    + "    MauSac ON CTS.MauSac = MauSac.id\n"
                    + "JOIN\n"
                    + "    DanhMuc ON CTS.DanhMuc = DanhMuc.Id\n"
                    + "JOIN\n"
                    + "    Size ON CTS.Size = Size.Id\n"
                    + "JOIN\n"
                    + "    Hang ON CTS.hang = Hang.idHang\n"
                    + "JOIN\n"
                    + "    dbo.ChatLieu ON cts.ChatLieu = ChatLieu.id\n"
                    + "WHERE\n"
                    + "    Hang.tenHang LIKE '%' + ? + '%' -- Search by brand name\n";

            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, timKiem);

            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                BigDecimal giaNhap = new BigDecimal(rs.getString("gianhap"));
                BigDecimal giaBan = new BigDecimal(rs.getString("giaBan"));
                ctsp.setId(rs.getString("id"));
                ctsp.setSanPham(rs.getString("tensanpham"));
                ctsp.setGiaNhap(giaNhap);
                ctsp.setGiaBan(giaBan);
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setHang(rs.getString("tenhang"));
                ctsp.setChatLieu(rs.getString("tenchatlieu"));
                ctsp.setMauSac(rs.getString("tenmau"));
                ctsp.setSize(rs.getString("tensize"));
                ctsp.setDanhMuc(rs.getString("tendanhmuc"));
                ctsp.setMoTa(rs.getString("mota"));
                ctsp.setHinhAnh(rs.getString("hinhanh"));
                ctsp.setTrangThai(rs.getInt("trangthai"));
                lstCTSP.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println("");
        }
        return lstCTSP;
    }
    
    public ArrayList<ChiTietSanPham> timKiemChatLieuSPCT(String timKiem) {
        ArrayList<ChiTietSanPham> lstCTSP = new ArrayList<>();
        System.out.println("" + timKiem);
        try {
            String sql = "SELECT\n"
                    + "    CTS.Id,\n"
                    + "    dbo.SanPham.tenSanPham AS tensanpham,\n"
                    + "    CTS.giaNhap,\n"
                    + "    CTS.giaBan,\n"
                    + "    CTS.SoLuong,\n"
                    + "    Hang.TenHang,\n"
                    + "    ChatLieu.Ten AS tenchatlieu,\n"
                    + "    MauSac.Ten AS tenmau,\n"
                    + "    Size.Ten AS tensize,\n"
                    + "    DanhMuc.Ten AS tendanhmuc,\n"
                    + "    CTS.MoTa,\n"
                    + "    CTS.HinhAnh,\n"
                    + "    CTS.TrangThai\n"
                    + "FROM\n"
                    + "    ChiTietSanPham AS CTS\n"
                    + "JOIN\n"
                    + "    dbo.SanPham ON CTS.idSanPham = dbo.SanPham.Id\n"
                    + "JOIN\n"
                    + "    MauSac ON CTS.MauSac = MauSac.id\n"
                    + "JOIN\n"
                    + "    DanhMuc ON CTS.DanhMuc = DanhMuc.Id\n"
                    + "JOIN\n"
                    + "    Size ON CTS.Size = Size.Id\n"
                    + "JOIN\n"
                    + "    Hang ON CTS.hang = Hang.idHang\n"
                    + "JOIN\n"
                    + "    dbo.ChatLieu ON cts.ChatLieu = ChatLieu.id\n"
                    + "WHERE\n"
                    + "     ChatLieu.Ten LIKE '%' + ? + '%' -- Search by material;";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, timKiem);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                BigDecimal giaNhap = new BigDecimal(rs.getString("gianhap"));
                BigDecimal giaBan = new BigDecimal(rs.getString("giaBan"));
                ctsp.setId(rs.getString("id"));
                ctsp.setSanPham(rs.getString("tensanpham"));
                ctsp.setGiaNhap(giaNhap);
                ctsp.setGiaBan(giaBan);
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setHang(rs.getString("tenhang"));
                ctsp.setChatLieu(rs.getString("tenchatlieu"));
                ctsp.setMauSac(rs.getString("tenmau"));
                ctsp.setSize(rs.getString("tensize"));
                ctsp.setDanhMuc(rs.getString("tendanhmuc"));
                ctsp.setMoTa(rs.getString("mota"));
                ctsp.setHinhAnh(rs.getString("hinhanh"));
                ctsp.setTrangThai(rs.getInt("trangthai"));
                lstCTSP.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println("");
        }
        return lstCTSP;
    }
    
    public ArrayList<ChiTietSanPham> timKiemDanhMucSPCT(String timKiem) {
        ArrayList<ChiTietSanPham> lstCTSP = new ArrayList<>();
        System.out.println("" + timKiem);
        try {
            String sql = "SELECT\n"
                    + "    CTS.Id,\n"
                    + "    dbo.SanPham.tenSanPham AS tensanpham,\n"
                    + "    CTS.giaNhap,\n"
                    + "    CTS.giaBan,\n"
                    + "    CTS.SoLuong,\n"
                    + "    Hang.TenHang,\n"
                    + "    ChatLieu.Ten AS tenchatlieu,\n"
                    + "    MauSac.Ten AS tenmau,\n"
                    + "    Size.Ten AS tensize,\n"
                    + "    DanhMuc.Ten AS tendanhmuc,\n"
                    + "    CTS.MoTa,\n"
                    + "    CTS.HinhAnh,\n"
                    + "    CTS.TrangThai\n"
                    + "FROM\n"
                    + "    ChiTietSanPham AS CTS\n"
                    + "JOIN\n"
                    + "    dbo.SanPham ON CTS.idSanPham = dbo.SanPham.Id\n"
                    + "JOIN\n"
                    + "    MauSac ON CTS.MauSac = MauSac.id\n"
                    + "JOIN\n"
                    + "    DanhMuc ON CTS.DanhMuc = DanhMuc.Id\n"
                    + "JOIN\n"
                    + "    Size ON CTS.Size = Size.Id\n"
                    + "JOIN\n"
                    + "    Hang ON CTS.hang = Hang.idHang\n"
                    + "JOIN\n"
                    + "    dbo.ChatLieu ON cts.ChatLieu = ChatLieu.id\n"
                    + "WHERE\n"
                    + "    DanhMuc.Ten LIKE '%' + ? + '%' -- Search by category\n";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, timKiem);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                BigDecimal giaNhap = new BigDecimal(rs.getString("gianhap"));
                BigDecimal giaBan = new BigDecimal(rs.getString("giaBan"));
                ctsp.setId(rs.getString("id"));
                ctsp.setSanPham(rs.getString("tensanpham"));
                ctsp.setGiaNhap(giaNhap);
                ctsp.setGiaBan(giaBan);
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setHang(rs.getString("tenhang"));
                ctsp.setChatLieu(rs.getString("tenchatlieu"));
                ctsp.setMauSac(rs.getString("tenmau"));
                ctsp.setSize(rs.getString("tensize"));
                ctsp.setDanhMuc(rs.getString("tendanhmuc"));
                ctsp.setMoTa(rs.getString("mota"));
                ctsp.setHinhAnh(rs.getString("hinhanh"));
                ctsp.setTrangThai(rs.getInt("trangthai"));
                lstCTSP.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println("");
        }
        return lstCTSP;
    }
    
    public ArrayList<ChiTietSanPham> timKiemSizeSPCT(String timKiem) {
        ArrayList<ChiTietSanPham> lstCTSP = new ArrayList<>();
        System.out.println("" + timKiem);
        try {
            String sql = "SELECT\n"
                    + "    CTS.Id,\n"
                    + "    dbo.SanPham.tenSanPham AS tensanpham,\n"
                    + "    CTS.giaNhap,\n"
                    + "    CTS.giaBan,\n"
                    + "    CTS.SoLuong,\n"
                    + "    Hang.TenHang,\n"
                    + "    ChatLieu.Ten AS tenchatlieu,\n"
                    + "    MauSac.Ten AS tenmau,\n"
                    + "    Size.Ten AS tensize,\n"
                    + "    DanhMuc.Ten AS tendanhmuc,\n"
                    + "    CTS.MoTa,\n"
                    + "    CTS.HinhAnh,\n"
                    + "    CTS.TrangThai\n"
                    + "FROM\n"
                    + "    ChiTietSanPham AS CTS\n"
                    + "JOIN\n"
                    + "    dbo.SanPham ON CTS.idSanPham = dbo.SanPham.Id\n"
                    + "JOIN\n"
                    + "    MauSac ON CTS.MauSac = MauSac.id\n"
                    + "JOIN\n"
                    + "    DanhMuc ON CTS.DanhMuc = DanhMuc.Id\n"
                    + "JOIN\n"
                    + "    Size ON CTS.Size = Size.Id\n"
                    + "JOIN\n"
                    + "    Hang ON CTS.hang = Hang.idHang\n"
                    + "JOIN\n"
                    + "    dbo.ChatLieu ON cts.ChatLieu = ChatLieu.id\n"
                    + "WHERE\n"
                    + "    Size.Ten LIKE '%' + ? + '%' -- Search by size\n";

            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, timKiem);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                BigDecimal giaNhap = new BigDecimal(rs.getString("gianhap"));
                BigDecimal giaBan = new BigDecimal(rs.getString("giaBan"));
                ctsp.setId(rs.getString("id"));
                ctsp.setSanPham(rs.getString("tensanpham"));
                ctsp.setGiaNhap(giaNhap);
                ctsp.setGiaBan(giaBan);
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setHang(rs.getString("tenhang"));
                ctsp.setChatLieu(rs.getString("tenchatlieu"));
                ctsp.setMauSac(rs.getString("tenmau"));
                ctsp.setSize(rs.getString("tensize"));
                ctsp.setDanhMuc(rs.getString("tendanhmuc"));
                ctsp.setMoTa(rs.getString("mota"));
                ctsp.setHinhAnh(rs.getString("hinhanh"));
                ctsp.setTrangThai(rs.getInt("trangthai"));
                lstCTSP.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println("");
        }
        return lstCTSP;
    }
    
    public ArrayList<ChiTietSanPham> timKiemMauSacSPCT(String timKiem) {
        ArrayList<ChiTietSanPham> lstCTSP = new ArrayList<>();
        System.out.println("" + timKiem);
        try {
            String sql = "SELECT\n"
                    + "    CTS.Id,\n"
                    + "    dbo.SanPham.tenSanPham AS tensanpham,\n"
                    + "    CTS.giaNhap,\n"
                    + "    CTS.giaBan,\n"
                    + "    CTS.SoLuong,\n"
                    + "    Hang.TenHang,\n"
                    + "    ChatLieu.Ten AS tenchatlieu,\n"
                    + "    MauSac.Ten AS tenmau,\n"
                    + "    Size.Ten AS tensize,\n"
                    + "    DanhMuc.Ten AS tendanhmuc,\n"
                    + "    CTS.MoTa,\n"
                    + "    CTS.HinhAnh,\n"
                    + "    CTS.TrangThai\n"
                    + "FROM\n"
                    + "    ChiTietSanPham AS CTS\n"
                    + "JOIN\n"
                    + "    dbo.SanPham ON CTS.idSanPham = dbo.SanPham.Id\n"
                    + "JOIN\n"
                    + "    MauSac ON CTS.MauSac = MauSac.id\n"
                    + "JOIN\n"
                    + "    DanhMuc ON CTS.DanhMuc = DanhMuc.Id\n"
                    + "JOIN\n"
                    + "    Size ON CTS.Size = Size.Id\n"
                    + "JOIN\n"
                    + "    Hang ON CTS.hang = Hang.idHang\n"
                    + "JOIN\n"
                    + "    dbo.ChatLieu ON cts.ChatLieu = ChatLieu.id\n"
                    + "WHERE\n"
                    + "    MauSac.Ten LIKE '%' + ? + '%' -- Search by color\n";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, timKiem);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                BigDecimal giaNhap = new BigDecimal(rs.getString("gianhap"));
                BigDecimal giaBan = new BigDecimal(rs.getString("giaBan"));
                ctsp.setId(rs.getString("id"));
                ctsp.setSanPham(rs.getString("tensanpham"));
                ctsp.setGiaNhap(giaNhap);
                ctsp.setGiaBan(giaBan);
                ctsp.setSoLuong(rs.getInt("soluong"));
                ctsp.setHang(rs.getString("tenhang"));
                ctsp.setChatLieu(rs.getString("tenchatlieu"));
                ctsp.setMauSac(rs.getString("tenmau"));
                ctsp.setSize(rs.getString("tensize"));
                ctsp.setDanhMuc(rs.getString("tendanhmuc"));
                ctsp.setMoTa(rs.getString("mota"));
                ctsp.setHinhAnh(rs.getString("hinhanh"));
                ctsp.setTrangThai(rs.getInt("trangthai"));
                lstCTSP.add(ctsp);
            }
        } catch (Exception e) {
            System.out.println("");
        }
        return lstCTSP;
    }

    public int checkTrungCtsp(ChiTietSanPham ctsp) {
        int row = 0;
        try {
            Connection cn = DBConnection.getConnection();
            String sql = "SELECT * FROM ChiTietSanPham WHERE idSanPham = ? AND hang = ? AND Size = ? AND DanhMuc = ? AND ChatLieu = ? AND MauSac = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            System.out.println(""+ctsp.getSanPham());
            ps.setObject(1, ctsp.getSanPham());
            ps.setObject(2, ctsp.getHang());
            ps.setObject(3, ctsp.getSize());
            ps.setObject(4, ctsp.getDanhMuc());
            ps.setObject(5, ctsp.getChatLieu());
            ps.setObject(6, ctsp.getMauSac());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                row = 1;
            }
        } catch (Exception e) {
        }
        return row;
    }
}
