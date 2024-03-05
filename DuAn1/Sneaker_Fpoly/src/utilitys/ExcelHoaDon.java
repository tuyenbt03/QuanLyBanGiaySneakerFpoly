/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitys;

/**
 *
 * @author DELL
 */
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.BaseFont;
import java.io.OutputStream;

import java.awt.Desktop;
import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.HDCT_CTSP;
import model.HoaDon;

public class ExcelHoaDon {

    public final String url = getClass().getClassLoader().getResource("font/unicode.ttf").getPath();
    String pathUnicode = URLDecoder.decode(url, StandardCharsets.UTF_8);

    public void inHoaDon(HoaDon hoaDon, List<HDCT_CTSP> hdct_ctsp, String pathFile) {

        try {
            String path = pathFile + "\\" + "hoa_don" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            File file = new File(path);
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columnWith[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(new ExcelHoaDon().pathUnicode, BaseFont.IDENTITY_H);
            Table table = new Table(columnWith);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new Cell().add("Giày Sneaker Poly").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER)
            );
            table.addCell(new Cell().add("Mã hóa đơn: " + hoaDon.getMaHoaDon()).setTextAlignment(TextAlignment.RIGHT.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));
            document.add(table);
            float colWidth[] = {80, 230, 200, 200};

            // Thông tin khách hàng đặt hàng
            Table khachHangTable = new Table(colWidth);
            khachHangTable.setFont(font);
            khachHangTable.addCell(new Cell(0, 4).add("Thông tin khách hàng").setBold().setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add(hoaDon.getIdKhachHang().getTenKhachHang()).setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new Cell().add(hoaDon.getIdKhachHang().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new Cell().add("Email:").setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add(hoaDon.getIdKhachHang().getEmail()).setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new Cell().add(hoaDon.getIdKhachHang().getDiaChi()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            document.add(khachHangTable);
//            //Thông tin người tạo hóa đơn
            Table nguoiDungTable = new Table(colWidth);
            nguoiDungTable.setFont(font);
            nguoiDungTable.addCell(new Cell(0, 4).add("Thông tin người tạo hóa đơn").setBold().setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add(hoaDon.getIdNhanVien().getHoVaTen()).setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            nguoiDungTable.addCell(new Cell().add(hoaDon.getIdNhanVien().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            nguoiDungTable.addCell(new Cell().add("Email:").setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add(hoaDon.getIdNhanVien().getEmail()).setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add("Chức vụ:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            boolean chucVu = true;
            if (hoaDon.getIdNhanVien().getIdCV().getMaChucVu().equals("CV1")) {
                chucVu = true;
            } else if (hoaDon.getIdNhanVien().getIdCV().getMaChucVu().equals("CV2")) {
                chucVu = false;
            }
            nguoiDungTable.addCell(new Cell().add(chucVu ? "Nhân viên" : "Quản lý").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            document.add(nguoiDungTable);
//            //thông tin giảm giá
            Table phieuGiamGiaTable = new Table(colWidth);
            if (hoaDon.getIdKhuyenMai() != null) {
                phieuGiamGiaTable.setFont(font);
                phieuGiamGiaTable.addCell(new Cell(0, 4).add("Thông tin phiếu giảm").setBold().setBorder(Border.NO_BORDER));

                phieuGiamGiaTable.addCell(new Cell().add("Mã phiếu:").setBorder(Border.NO_BORDER));
                phieuGiamGiaTable.addCell(new Cell().add(hoaDon.getIdKhuyenMai().getMaKhuyenMai()).setBorder(Border.NO_BORDER));
                phieuGiamGiaTable.addCell(new Cell().add("Giá trị:").setBorder(Border.NO_BORDER));
                phieuGiamGiaTable.addCell(new Cell().add(String.valueOf(hoaDon.getIdKhuyenMai().getGiaTri()) + "%").setBorder(Border.NO_BORDER));
            }
            document.add(phieuGiamGiaTable);
//            //hoa don chi tiet
            float itemColWidth[] = {20, 190, 190, 190, 190, 190, 190, 190, 190};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Hãng").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Size").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Chất liệu").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Màu").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Danh Mục").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ngày bán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giá bán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
//            //Thông tin hóa đơn chi tiết
            int index = 1;
            DecimalFormat df = new DecimalFormat("#,###");

            for (HDCT_CTSP ct : hdct_ctsp) {
                itemTable.addCell(new Cell().add(String.valueOf(index++)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getTenSanPham()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getTenHang()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getSize()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getChatLieu()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getMauSac()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getTenDanhMuc()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getNgayBan().toString()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(ct.getDonGia()) + " VND").setBorder(Border.NO_BORDER));
            }
            document.add(itemTable);

            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4).add("Lưu ý: Quý khách hãy giữ lại hóa đơn,\nNếu sản phẩm gặp vấn đề gì có thể trả hàng trong vòng 3 ngày,\n chỉ thực hiện trả hàng cho những sản phẩm không áp dụng khuyến mại.\nNhững sản phẩm được đánh dấu (*) ở giá bán là những sản phẩm đã có giảm giá khuyến mại").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4).add("Trường cao đẳng FPT Polytechnich, P.Trịnh Văn Bô,\nP.Phương Canh, Q.Nam Từ Liêm, TP.Hà Nội").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
            Table customer3 = new Table(colWidth1);

            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4).add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0702230722").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);
            if (!Desktop.isDesktopSupported()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
            document.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }


    public static void main(String[] args) {
        System.out.println(new ExcelHoaDon().pathUnicode);
       
    }
}
