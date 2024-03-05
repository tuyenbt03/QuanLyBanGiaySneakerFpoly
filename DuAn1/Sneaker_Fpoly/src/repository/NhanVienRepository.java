package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.ChucVu;
import model.NhanVien;
import utilitys.DBConnection;

/**
 *
 * @author Chien Duong
 */
public class NhanVienRepository {

    ChucVuRepository cvr = new ChucVuRepository();

    private static final int pageSize = 5;
    String sql;
public Boolean updatePass(String id,String matKhau) {
        try {
            String sql = "UPDATE nhanvien\n"
                    + "SET matkhau = ?\n"
                    + "WHERE id like ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setString(1, matKhau);
            ptsm.setString(2, id);
            ptsm.executeUpdate();
        } catch (Exception e) {
            System.out.println(""+e);
        }
        return true;
    }
    public ArrayList<NhanVien> getAllNV() {
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("id"));
                nv.setMaNV(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setGioiTinh(rs.getString("gioiTinh"));
                nv.setNgaySinh(rs.getDate("ngaySinh"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("email"));
                nv.setMatKhau(rs.getString("matKhau"));
                nv.setIdCV(this.cvr.getCVByID(rs.getString("IDCV")));
                nv.setHinh(rs.getString("hinh"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setNgaySua(rs.getDate("ngaySua"));
                nv.setTrangThai(rs.getInt("trangThai"));
                lstNhanVien.add(nv);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }

        return lstNhanVien;
    }

    public ArrayList<NhanVien> getAllNhanVien(int page) {
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM NhanVien ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("id"));
                nv.setMaNV(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setGioiTinh(rs.getString("gioiTinh"));
                nv.setNgaySinh(rs.getDate("ngaySinh"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("email"));
                nv.setMatKhau(rs.getString("matKhau"));
                nv.setIdCV(this.cvr.getCVByID(rs.getString("IDCV")));
                nv.setHinh(rs.getString("hinh"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setNgaySua(rs.getDate("ngaySua"));
                nv.setTrangThai(rs.getInt("trangThai"));
                lstNhanVien.add(nv);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }

        return lstNhanVien;
    }

    public ArrayList<NhanVien> getNVByID(String id) {
        ArrayList<NhanVien> nv = new ArrayList<>();

        String sql = "SELECT ID, maNhanVien,hoVaTen FROM NhanVien WHERE ID=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idNV = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                nv.add(new NhanVien(idNV, maNV, hoVaTen));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien insertNV(NhanVien nv) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO NHANVIEN VALUES (NEWID(),?,?,?,?,?,?,?,?,?,?,GetDate(),null,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoVaTen());
            ps.setDate(3, new Date(nv.getNgaySinh().getTime()));
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getSdt());
            ps.setString(7, nv.getEmail());
            ps.setString(8, nv.getMatKhau());
            ps.setString(9, nv.getIdCV().getIdchucVu());
            ps.setString(10, nv.getHinh());
            ps.setInt(11, nv.getTrangThai());
            ps.executeUpdate();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien updateNV(NhanVien nv) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE NHANVIEN SET HovaTen=?,NGAYSINH=?,GIOITINH=?,DIACHI=?,SDT=?,EMAIL=?,MATKHAU=?,IDCV=?,HINH=?, TRANGTHAI=?,NGAYSUA=GETDATE() WHERE maNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getHoVaTen());
            ps.setDate(2, new Date(nv.getNgaySinh().getTime()));
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getMatKhau());
            ps.setString(8, nv.getIdCV().getIdchucVu());
            ps.setString(9, nv.getHinh());
            ps.setInt(10, nv.getTrangThai());
            ps.setString(11, nv.getMaNV());

            // Thực thi câu lệnh UPDATE và kiểm tra số dòng được ảnh hưởng
            ps.executeUpdate();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public ArrayList<NhanVien> getNVLam() {
//        ArrayList<NhanVien> lstNhanVienLam = new ArrayList<>();
//        String sql = "SELECT * FROM NHANVIEN WHERE TRANGTHAI=1";
//
//        try {
//           Connection con = DBConnection.getConnection();
//            PreparedStatement ptsm = con.prepareStatement(sql);
//            ResultSet rs = ptsm.executeQuery();
//
//            while (rs.next()) {
//                NhanVien nv = new NhanVien();
//                nv.setId(rs.getString("id"));
//                nv.setMaNV(rs.getString("maNhanVien"));
//                nv.setHoVaTen(rs.getString("hoVaTen"));
//                nv.setGioiTinh(rs.getString("gioiTinh"));
//                nv.setNgaySinh(rs.getDate("ngaySinh"));
//                nv.setDiaChi(rs.getString("diaChi"));
//                nv.setSdt(rs.getString("sdt"));
//                nv.setEmail(rs.getString("email"));
//               nv.setMatKhau(rs.getString("matKhau"));
//               nv.setIdCV(this.cvr.getCVByID(rs.getString("IDCV")));
//                nv.setHinh(rs.getString("hinh"));
//                nv.setNgayTao(rs.getDate("ngayTao"));
//                nv.setNgaySua(rs.getDate("ngaySua"));
//                nv.setTrangThai(rs.getInt("trangThai"));
//                lstNhanVienLam.add(nv);
//            }
//            con.close();
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
//
//       return lstNhanVienLam;
//   }
    public ArrayList<NhanVien> getNhanVienLam(int page) {
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
       ArrayList<NhanVien> lstNhanVienLam = new ArrayList<>();
        String sql = "SELECT *\n"
               + "FROM NhanVien\n"
                + "WHERE trangThai = 1\n"
               + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
             ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
           ResultSet rs = ptsm.executeQuery();

           while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("id"));
                nv.setMaNV(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setGioiTinh(rs.getString("gioiTinh"));
                nv.setNgaySinh(rs.getDate("ngaySinh"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("email"));
                nv.setMatKhau(rs.getString("matKhau"));
                nv.setIdCV(this.cvr.getCVByID(rs.getString("IDCV")));
                nv.setHinh(rs.getString("Hinh"));
                nv.setNgayTao(rs.getDate("NgayTao"));
                nv.setNgaySua(rs.getDate("NgaySua"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                lstNhanVienLam.add(nv);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstNhanVienLam;
    }
      public ArrayList<NhanVien> getNhanVienNghi(int page) {
        ArrayList<NhanVien> lstNhanVienNghi = new ArrayList<>();
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        String sql = "SELECT *\n"
                + "FROM NhanVien\n"
                + "WHERE TRANGTHAI = 0\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, pageSize);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("id"));
                nv.setMaNV(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setGioiTinh(rs.getString("gioiTinh"));
                nv.setNgaySinh(rs.getDate("ngaySinh"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("email"));
                nv.setMatKhau(rs.getString("matKhau"));
                nv.setIdCV(this.cvr.getCVByID(rs.getString("IDCV")));
                nv.setHinh(rs.getString("hinh"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setNgaySua(rs.getDate("ngaySua"));
                nv.setTrangThai(rs.getInt("trangThai"));
                lstNhanVienNghi.add(nv);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstNhanVienNghi;
    }


    public ArrayList<NhanVien> getNVlamByCV(String tenCV) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND TenChucVu LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenCV + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVien> getNVlamBySdt(String sdt) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND SDT LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + sdt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVien> getNVNghiByCV(String tenCV) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=0 AND TenChucVu LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenCV + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<NhanVien> getNVNghiBySdt(String sdt) {
        ArrayList<NhanVien> nv = new ArrayList<>();
        String sql = "SELECT NHANVIEN.* FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.IDCV=CHUCVU.ID WHERE NHANVIEN.TRANGTHAI=1 AND SDT LIKE ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + sdt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String maNV = rs.getString(2);
                String hoVaTen = rs.getString(3);
                String gioiTinh = rs.getString(5);
                String diaChi = rs.getString(6);
                Date NgaySinh = rs.getDate(4);
                sdt = rs.getString(7);
                String email = rs.getString(8);
                String matKhau = rs.getString(9);
                ChucVu cv = cvr.getCVByID(rs.getString(10));
                String hinh = rs.getString(11);
                Date ngayTao = rs.getDate(12);
                Date ngaySua = rs.getDate(13);
                int trangThai = rs.getInt(14);
                nv.add(new NhanVien(id, maNV, hoVaTen, NgaySinh, gioiTinh, hinh, sdt, email, maNV, cv, hinh, ngayTao, ngaySua, trangThai));
            }
            con.close();
            ps.close();
            rs.close();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int SoBanGhiNghi() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            sql = "SELECT COUNT(*) FROM NhanVien WHERE TrangThai = 0";
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

    
     public int SoBanGhiLam() {
        int soBanGhi = 0;
        try {
            Connection con = DBConnection.getConnection();
            sql = "SELECT COUNT(*) FROM NhanVien WHERE TrangThai = 1";
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
     
    
}
