/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.awt.BasicStroke;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import utilitys.DBConnection;

/**
 *
 * @author DELL
 */
public class DoanhThuRepository {

    public double getDoanhThuTheoNgayHienTai() {
        double dt = 0;
        try {
            String sql = "SELECT \n"
                    + "    ngaymua, \n"
                    + "    SUM(thanhtien) AS thanhtien\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    CAST(ngaymua AS DATE) = CAST(GETDATE() AS DATE)\n"
                    + "GROUP BY ngaymua";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                dt = rs.getDouble(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dt;
    }

    public double getDoanhThuTheoThangHienTai() {
        double dt = 0;
        try {
            String sql = "SELECT \n"
                    + "    SUM(thanhtien) AS thanhtien\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = YEAR(GETDATE()) AND MONTH(ngaymua) = MONTH(GETDATE());";
            Connection con = DBConnection.getConnection();
            Statement sttm = con.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                dt = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public double getDoanhThuTheoNamHienTai() {
        double dt = 0;
        try {
            String sql = "	SELECT \n"
                    + "    SUM(thanhtien) AS thanhtien\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = YEAR(GETDATE());";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                dt = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public double getDonHuyConTheoNgay() {
        double don = 0;
        try {
            String sql = "	SELECT \n"
                    + "    COUNT(*) AS SoLuongHoaDon\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    CONVERT(DATE, ngaymua) = CONVERT(DATE, GETDATE())\n"
                    + "    AND trangthai = 0;";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                don = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }

    public double getDonHuyTheoThang() {
        double don = 0;
        try {
            String sql = "SELECT \n"
                    + "    COUNT(*) AS SoLuongHoaDon\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    MONTH(ngaymua) = MONTH(GETDATE())\n"
                    + "    AND YEAR(ngaymua) = YEAR(GETDATE())\n"
                    + "    AND trangthai = 0;";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                don = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }

    public double getDonHuyConTheoNam() {
        double don = 0;
        try {
            String sql = "SELECT \n"
                    + "    COUNT(*) AS SoLuongHoaDon\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = YEAR(GETDATE())\n"
                    + "    AND trangthai = 0;";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                don = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }

    public double getDonThanhCongTheoNgay() {
        double don = 0;
        try {
            String sql = "	SELECT \n"
                    + "    COUNT(*) AS SoLuongHoaDon\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    CONVERT(DATE, ngaymua) = CONVERT(DATE, GETDATE())\n"
                    + "    AND trangthai = 1;";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                don = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }

    public double getDonThanhCongTheoThang() {
        double don = 0;
        try {
            String sql = "SELECT \n"
                    + "    COUNT(*) AS SoLuongHoaDon\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    MONTH(ngaymua) = MONTH(GETDATE())\n"
                    + "    AND YEAR(ngaymua) = YEAR(GETDATE())\n"
                    + "    AND trangthai = 1;";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                don = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }

    public double getDonThanhCongTheoNam() {
        double don = 0;
        try {
            String sql = "SELECT \n"
                    + "    COUNT(*) AS SoLuongHoaDon\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = YEAR(GETDATE())\n"
                    + "    AND trangthai = 1;";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                don = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return don;
    }

    public ArrayList<Integer> getNam() {
        ArrayList<Integer> lstNam = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT YEAR(ngaytao) as [YEAR] FROM HOADON WHERE NGAYTAO IS NOT NULL ORDER BY [YEAR] DESC";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lstNam.add(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return lstNam;
    }

    public ArrayList<Integer> getThang() {
        ArrayList<Integer> lstThang = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Month(NGAYTAO) as [month] FROM HOADON WHERE NGAYTAO IS NOT NULL ORDER BY [month] DESC";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lstThang.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstThang;
    }

    public ArrayList<Object[]> getChiTietDoanhThu(int nam) {
        ArrayList<Object[]> lstDoanhThu = new ArrayList<>();
        System.out.println("dt" + nam);
        try {
            String sql = "SELECT \n"
                    + "    YEAR(hd.ngaytao) AS Nam,\n"
                    + "    MONTH(hd.ngaytao) AS Thang,\n"
                    + "    SUM(cthd.soluong) AS TongSanPham,\n"
                    + "    SUM(hd.thanhtien) AS TongThanhTien,\n"
                    + "    SUM(hd.tongtiengiam) AS TongGiaGiam\n"
                    + "FROM \n"
                    + "    hoadon hd\n"
                    + "INNER JOIN \n"
                    + "    chitiethoadon cthd ON hd.id = cthd.idhoadon\n"
                    + "		where year(hd.ngaytao) = ? and hd.trangthai =1\n"
                    + "GROUP BY \n"
                    + "    YEAR(hd.ngaytao), MONTH(hd.ngaytao)";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, nam);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                lstDoanhThu.add(new Object[]{
                    rs.getInt("thang"),
                    rs.getInt("tongsanpham"),
                    rs.getDouble("tongthanhtien"),
                    rs.getDouble("tonggiagiam"),});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstDoanhThu;
    }

    public ArrayList<Object[]> getDoanhThuTheoThangChon(int thang, int nam) {
        ArrayList<Object[]> lstdt = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    SUM(CASE WHEN trangthai = 1 THEN thanhtien ELSE 0 END) AS doanh_thu_thanh_cong,\n"
                    + "    SUM(CASE WHEN trangthai = 1 THEN 1 ELSE 0 END) AS so_don_thanh_cong,\n"
                    + "    SUM(CASE WHEN trangthai = 0 THEN 1 ELSE 0 END) AS so_don_huy\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = ? and month(ngaymua)=?\n"
                    + "GROUP BY \n"
                    + "    MONTH(ngaymua);";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, nam);
            ptsm.setInt(2, thang);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                lstdt.add(new Object[]{
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstdt;
    }

    public ArrayList<Object[]> getDoanhThuTheoNamChon(int nam) {
        ArrayList<Object[]> lstdt = new ArrayList<>();
        try {
            String sql = "	SELECT \n"
                    + "    SUM(CASE WHEN trangthai = 1 THEN thanhtien ELSE 0 END) AS doanh_thu_thanh_cong,\n"
                    + "    SUM(CASE WHEN trangthai = 1 THEN 1 ELSE 0 END) AS so_don_thanh_cong,\n"
                    + "    SUM(CASE WHEN trangthai = 0 THEN 1 ELSE 0 END) AS so_don_huy\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = ? ";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, nam);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                lstdt.add(new Object[]{
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstdt;
    }

    public JFreeChart ChartNam() {
        String chartTitle = "Biểu đồ doanh thu các năm ";
        String sql = "SELECT \n"
                + "    YEAR(hd.ngaytao) AS Nam,\n"
                + "    MONTH(hd.ngaytao) AS Thang,\n"
                + "    SUM(cthd.soluong) AS TongSanPham,\n"
                + "    SUM(hd.thanhtien) AS TongThanhTien,\n"
                + "    SUM(hd.tongtiengiam) AS TongGiaGiam\n"
                + "FROM \n"
                + "    hoadon hd\n"
                + "INNER JOIN \n"
                + "    chitiethoadon cthd ON hd.id = cthd.idhoadon\n"
                + "GROUP BY \n"
                + "    YEAR(hd.ngaytao), MONTH(hd.ngaytao)";
           Connection con = DBConnection.getConnection();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                dataset.addValue(rs.getInt(4), "Doanh Thu", rs.getString(1));
                
            }
            JFreeChart lineChart = ChartFactory.createLineChart(chartTitle.toUpperCase(), "Năm", "Doanh Thu (VNĐ)", dataset, PlotOrientation.VERTICAL, true, true, false);
            CategoryPlot plot = lineChart.getCategoryPlot();
            plot.setRenderer(new BarRenderer());
            plot.setBackgroundPaint(Color.white);
            plot.setOutlineStroke(new BasicStroke(2.0f));
            plot.setRangeGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.BLACK);
            plot.setDomainGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.BLACK);
            return lineChart;
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//Của chiến

    public ArrayList<Object[]> getSPDaBanChien() {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql = "SELECT S.tenSanPham AS TenSanPham, SIZE.TEN AS TenSize, MAUSAC.TEN AS TenMauSac, DANHMUC.TEN AS TenDanhMuc, CHATLIEU.TEN AS TenChatLieu, SUM(ChiTietHoaDon.SoLuong) AS SOLUONG\n"
                + "FROM CHITIETHOADON\n"
                + "JOIN ChiTietSanPham ON ChiTietHoaDon.idChiTietSanPham = ChiTietSanPham.ID \n"
                + "JOIN HOADON ON HOADON.ID = ChiTietHoaDon.idHoaDon\n"
                + "JOIN SANPHAM AS S ON S.ID = ChiTietSanPham.idSanPham\n"
                + "JOIN SIZE ON SIZE.ID = ChiTietSanPham.Size\n"
                + "JOIN MAUSAC ON MAUSAC.ID = ChiTietSanPham.MauSac\n"
                + "JOIN CHATLIEU ON CHATLIEU.ID = ChiTietSanPham.ChatLieu\n"
                + "JOIN DANHMUC ON DANHMUC.ID = ChiTietSanPham.DanhMuc\n"
                + "WHERE HOADON.TRANGTHAI = 1\n"
                + "GROUP BY S.tenSanPham, SIZE.TEN, MAUSAC.TEN, CHATLIEU.TEN, DANHMUC.TEN";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6)
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<Object[]> getTOP1SPBANChien() {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql = "SELECT TOP 1 SANPHAM.tenSanPham, \n"
                + "            SUM(CHITIETHOADON.SOLUONG) AS SL,\n"
                + "            SUM(CASE WHEN HOADON.trangthai = 1 THEN CHITIETHOADON.donGia ELSE 0 END) AS doanh_thu_thanh_cong\n"
                + "FROM CHITIETHOADON\n"
                + "JOIN CHITIETSANPHAM AS CTSP ON CTSP.ID = CHITIETHOADON.idChiTietSanPham\n"
                + "JOIN SANPHAM ON SANPHAM.ID = CTSP.idSanPham\n"
                + "JOIN HOADON ON CHITIETHOADON.idHoaDon = HOADON.ID\n"
                + "WHERE HOADON.TRANGTHAI = 1\n"
                + "GROUP BY SANPHAM.tenSanPham\n"
                + "ORDER BY SL DESC;";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getBigDecimal(3),});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<Object[]> getTOP1DanhMucChien() {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql = "				SELECT TOP 1 DANHMUC.TEN,\n"
                + "    SUM(CHITIETHOADON.SOLUONG) AS SL,\n"
                + "    SUM(CASE WHEN HOADON.trangthai = 1 THEN HoaDon.thanhTien ELSE 0 END) AS doanh_thu_thanh_cong\n"
                + "FROM CHITIETHOADON\n"
                + "JOIN CHITIETSANPHAM AS CTSP ON CTSP.ID = ChiTietHoaDon.idChiTietSanPham \n"
                + "JOIN DANHMUC ON DANHMUC.ID = CTSP.DanhMuc \n"
                + "JOIN HOADON ON ChiTietHoaDon.idHoaDon = HoaDon.ID \n"
                + "WHERE HOADON.TRANGTHAI = 1  \n"
                + "GROUP BY DANHMUC.TEN \n"
                + "ORDER BY SL DESC;";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getBigDecimal(3),});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<Object[]> getTOP1DanhMucTheoNamChien(int nam) {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql = "	SELECT TOP 1 DANHMUC.TEN,\n"
                + " SUM(CHITIETHOADON.SOLUONG) AS SL,\n"
                + "    SUM(CASE WHEN HOADON.trangthai = 1 THEN HoaDon.thanhTien ELSE 0 END) AS doanh_thu_thanh_cong\n"
                + "FROM CHITIETHOADON\n"
                + "JOIN CHITIETSANPHAM AS CTSP ON CTSP.ID = ChiTietHoaDon.idChiTietSanPham  \n"
                + "JOIN DANHMUC ON DANHMUC.ID = CTSP.DanhMuc \n"
                + "JOIN HOADON ON ChiTietHoaDon.idHoaDon = HoaDon.ID  \n"
                + "WHERE HOADON.TRANGTHAI = 1  and  YEAR(ngaymua) = ?\n"
                + "GROUP BY DANHMUC.TEN \n"
                + "ORDER BY SL DESC;";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, nam);
            ResultSet rs = ptsm.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getBigDecimal(3),});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static String getTopSanPhamChien() {
        String sql = "SELECT TOP 1 SANPHAM.tenSanPham, SUM(CHITIETHOADON.SOLUONG) AS SL FROM CHITIETHOADON "
                + "JOIN CHITIETSANPHAM AS CTSP ON CTSP.ID = ChiTietHoaDon.idChiTietSanPham "
                + "JOIN SANPHAM ON SANPHAM.ID = CTSP.idSanPham "
                + "JOIN HOADON ON ChiTietHoaDon.idHoaDon = HoaDon.ID "
                + "WHERE HOADON.TRANGTHAI = 1 "
                + "GROUP BY SANPHAM.tenSanPham ORDER BY SL DESC";
        return executeQuery(sql);
    }

    private static String executeQuery(String sql) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDoanhThuDanhMucChien() {
        String sql = "SELECT SUM(ChiTietHoaDon.donGia) AS DOANHTHU \n"
                + "            FROM CHITIETHOADON \n"
                + "            JOIN ChiTietSanPham ON ChiTietHoaDon.idChiTietSanPham = ChiTietSanPham.ID\n"
                + "            JOIN HOADON ON ChiTietHoaDon.i\n"
                + "    String sql = \"SELECT SUM(ChiTietHoaDon.donGia) AS DOANHTHU \\n\" +\n"
                + "\"            FROM CHITIETHOADON \\n\" +\n"
                + "\"            JOIN ChiTietSanPham ON ChiTietHoadHoaDon = HoaDon.ID\n"
                + "            WHERE HOADON.TRANGTHAI = 1";

        return executeQuery(sql);
    }

    public double getDoanhThuTheoNgayHienTaiChien() {
        double dt = 0;
        try {
            String sql = "SELECT \n"
                    + "    ngaymua, \n"
                    + "    SUM(thanhtien) AS thanhtien\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    CAST(ngaymua AS DATE) = CAST(GETDATE() AS DATE)\n"
                    + "GROUP BY ngaymua";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                dt = rs.getDouble(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dt;
    }

    public double getDoanhThuTheoThangHienTaiChien() {
        double dt = 0;
        try {
            String sql = "SELECT \n"
                    + "    SUM(thanhtien) AS thanhtien\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = YEAR(GETDATE()) AND MONTH(ngaymua) = MONTH(GETDATE());";
            Connection con = DBConnection.getConnection();
            Statement sttm = con.createStatement();
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                dt = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public double getDoanhThuTheoNamHienTaiChien() {
        double dt = 0;
        try {
            String sql = "	SELECT \n"
                    + "    SUM(thanhtien) AS thanhtien\n"
                    + "FROM \n"
                    + "    hoadon\n"
                    + "WHERE \n"
                    + "    YEAR(ngaymua) = YEAR(GETDATE());";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                dt = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public ArrayList<Object[]> getDoanhThuTheoThangChonChien(int thang, int nam) {
        ArrayList<Object[]> lstdt = new ArrayList<>();
        try {
            String sql = "SELECT TOP 1 SANPHAM.tenSanPham, \n"
                    + "            SUM(CHITIETHOADON.SOLUONG) AS SL,\n"
                    + "            SUM(CASE WHEN HOADON.trangthai = 1 THEN CHITIETHOADON.donGia ELSE 0 END) AS doanh_thu_thanh_cong\n"
                    + "FROM CHITIETHOADON\n"
                    + "JOIN CHITIETSANPHAM AS CTSP ON CTSP.ID = CHITIETHOADON.idChiTietSanPham\n"
                    + "JOIN SANPHAM ON SANPHAM.ID = CTSP.idSanPham\n"
                    + "JOIN HOADON ON CHITIETHOADON.idHoaDon = HOADON.ID\n"
                    + "WHERE HOADON.TRANGTHAI = 1 and  YEAR(ngaymua) = ? and month(ngaymua)= ?\n"
                    + "GROUP BY SANPHAM.tenSanPham\n"
                    + "ORDER BY SL DESC";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, nam);
            ptsm.setInt(2, thang);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                lstdt.add(new Object[]{
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getBigDecimal(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstdt;
    }

    public ArrayList<Object[]> getDoanhThuDanhMucTheoThangNamChonChien(int thang, int nam) {
        ArrayList<Object[]> lstdt = new ArrayList<>();
        try {
            String sql = "				SELECT TOP 1 DANHMUC.TEN,\n"
                    + "    SUM(CHITIETHOADON.SOLUONG) AS SL,\n"
                    + "    SUM(CASE WHEN HOADON.trangthai = 1 THEN HoaDon.thanhTien ELSE 0 END) AS doanh_thu_thanh_cong\n"
                    + "FROM CHITIETHOADON\n"
                    + "JOIN CHITIETSANPHAM AS CTSP ON CTSP.ID = ChiTietHoaDon.idChiTietSanPham \n"
                    + "JOIN DANHMUC ON DANHMUC.ID = CTSP.DanhMuc \n"
                    + "JOIN HOADON ON ChiTietHoaDon.idHoaDon = HoaDon.ID \n"
                    + "WHERE HOADON.TRANGTHAI = 1  and  YEAR(ngaymua) = ? and month(ngaymua)= \n"
                    + "GROUP BY DANHMUC.TEN \n"
                    + "ORDER BY SL DESC";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, nam);
            ptsm.setInt(2, thang);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                lstdt.add(new Object[]{
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getBigDecimal(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstdt;
    }

    public ArrayList<Object[]> getDoanhThuSPTheoNamChonChien(int nam) {
        ArrayList<Object[]> lstdt = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "                  SANPHAM.tenSanPham,\n"
                    + "                    SUM(CASE WHEN hoadon.trangthai = 1 THEN thanhtien ELSE 0 END) AS doanh_thu_thanh_cong, \n"
                    + "                     SUM(CASE WHEN hoadon.trangthai = 1 THEN ChiTietHoaDon.soLuong ELSE 0 END) AS so_don_thanh_cong\n"
                    + "               FROM\n"
                    + "                  HOADON\n"
                    + "               JOIN CHITIETHOADON ON HOADON.ID = CHITIETHOADON.idHoaDon\n"
                    + "                JOIN ChiTietSanPham ON CHITIETHOADON.idChiTietSanPham = ChiTietSanPham.ID\n"
                    + "              JOIN SANPHAM ON SANPHAM.ID = ChiTietSanPham.idSanPham\n"
                    + "               WHERE\n"
                    + "                    YEAR(ngaymua) = ?\n"
                    + "              GROUP BY\n"
                    + "                  SANPHAM.tenSanPham";
            Connection con = DBConnection.getConnection();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ptsm.setInt(1, nam);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                lstdt.add(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstdt;
    }

    public ArrayList<Integer> getNamChien() {
        ArrayList<Integer> lstNam = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT YEAR(ngaymua) as [YEAR] FROM HOADON WHERE NGAYTAO IS NOT NULL ORDER BY [YEAR] DESC";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lstNam.add(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return lstNam;
    }

    public ArrayList<Integer> getThangChien() {
        ArrayList<Integer> lstThang = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Month(ngaymua) as [month] FROM HOADON WHERE NGAYTAO IS NOT NULL ORDER BY [month] DESC";
            Connection con = DBConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lstThang.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstThang;
    }

    public static void main(String[] args) {

    }
}
