create database UngDungBanGiaySneaker
use UngDungBanGiaySneaker
Create table ChucVu (
 id Uniqueidentifier primary key default newid(),
 maChucVu varchar(20) unique,
 tenChucVu varchar(20) default null,
 ngayTao date default null,
 ngaySua date default null,
 trangThai int default 0
)

Create TABLE NhanVien(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maNhanVien VARCHAR(20) UNIQUE,
hoVaTen NVARCHAR(50) DEFAULT NULL,
ngaySinh DATE DEFAULT NULL,
gioiTinh NVARCHAR(25) DEFAULT NULL,
diaChi NVARCHAR(200) DEFAULT NULL,
sdt VARCHAR(10) DEFAULT NULL,
email VARCHAR(100) DEFAULT NULL,
matKhau VARCHAR(20),
IDCV UNIQUEIDENTIFIER,
Hinh VARCHAR(Max),
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
)
CREATE TABLE MauSac(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
)
CREATE TABLE ChatLieu(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
)
CREATE TABLE DanhMuc(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
)
CREATE TABLE Size(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
)
CREATE TABLE KhachHang(
ID UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maKhachHang VARCHAR(20) UNIQUE,
loaiKhachHang NVARCHAR(50) DEFAULT NULL,
tenKhachHang NVARCHAR(50) DEFAULT NULL,
diaChi NVARCHAR(200) DEFAULT NULL,
gioiTinh NVARCHAR(50) DEFAULT NULL,
email VARCHAR(50) DEFAULT NULL,
sdt VARCHAR(50) DEFAULT NULL,
ngaySinh DATE DEFAULT NULL,
ngayThamGia DATE DEFAULT NULL,
tichDiem INT DEFAULT 0,
diemEXP INT DEFAULT 0,
ngayTao DATE DEFAULT NULL,
ngaySua DATE DEFAULT NULL,
trangThai INT DEFAULT 0
)

CREATE TABLE ChiTietSanPham(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
idSanPham UNIQUEIDENTIFIER,
hang UNIQUEIDENTIFIER,
Size UNIQUEIDENTIFIER,
DanhMuc UNIQUEIDENTIFIER,
ChatLieu UNIQUEIDENTIFIER,
MauSac UNIQUEIDENTIFIER,
idKhuyenMai UNIQUEIDENTIFIER,
giaNhap DECIMAL(20,0) DEFAULT 0,
giaBan DECIMAL(20,0) DEFAULT 0,
QR int IDENTITY(20012003,1),
HinhAnh VARCHAR(max),
SoLuong int,
MoTa NVARCHAR(100),
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
)
CREATE TABLE SanPham(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maSanPham VARCHAR(20) UNIQUE,
tenSanPham NVARCHAR(30),
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TrangThai INT DEFAULT 0
)

CREATE TABLE HoaDon(
ID UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maHoaDon VARCHAR(20) UNIQUE,
idNhanVien UNIQUEIDENTIFIER,
idKhachHang UNIQUEIDENTIFIER,
ngayMua DATE DEFAULT NULL,
thanhTien DECIMAL(20,0) DEFAULT 0,
idKhuyenMai UNIQUEIDENTIFIER,
GhiChu NVARCHAR(100) DEFAULT NULL,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
TienKhachDua DECIMAL(20,0) DEFAULT 0,
TienThua DECIMAL(20,0) DEFAULT 0,
TongTienGiam DECIMAL(20,0) DEFAULT 0,
TrangThai INT DEFAULT 0
)


CREATE TABLE ChiTietHoaDon(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
idHoaDon UNIQUEIDENTIFIER,
idChiTietSanPham UNIQUEIDENTIFIER,
soLuong INT DEFAULT NULL,
donGia DECIMAL(20,0) DEFAULT 0,
ngayBan Date  DEFAULT NULL,
ngayTao DATE DEFAULT NULL,
ngaySua DATE DEFAULT NULL,
trangThai INT DEFAULT 0
)


CREATE TABLE KhuyenMai(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maKhuyenMai VARCHAR(20) UNIQUE NULL,
tenKhuyenMai NVARCHAR(20) NULL,
giaTri DECIMAL(20,0) DEFAULT 0,
dieuKienGiam DECIMAL(20,0) DEFAULT 0,
ngayBatDau DATE DEFAULT NULL,
ngayKetThuc DATE DEFAULT NULL,
hinhThucApDung bit,
soLuong INT,
ngayTao DATE DEFAULT NULL,
ngaySua DATE DEFAULT NULL,
trangThai INT DEFAULT 0		
)

CREATE TABLE hang(
 idHang UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
 maHang varchar(20) unique null,
 tenHang nvarchar(30) null,
 ngayTao DATE DEFAULT NULL,
ngaySua DATE DEFAULT NULL,
trangThai INT DEFAULT 0		
)

CREATE TABLE trahang(
 id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
 maTraHang varchar(20) unique null,
 idNhanVien UNIQUEIDENTIFIER NULL,
 idChiTietSanPham  UNIQUEIDENTIFIER null,
 idKhachHang  UNIQUEIDENTIFIER null,
 idHoaDonChiTiet UNIQUEIDENTIFIER null,
 lyDo nvarChar(max),
 ghiChu nvarChar(max),
 ngayTraHang DATE DEFAULT NULL,
 trangThai INT DEFAULT 0	
)



select * from trahang
alter table trahang add foreign key (idNhanVien) references nhanvien(id)
alter table trahang add foreign key (idKhachHang) references KhachHang(id)
alter table trahang add foreign key (idChiTietSanPham) references ChiTietSanPham(id)
alter table trahang add foreign key (idHoaDonChiTiet) references ChiTietHoaDon(id)
alter table nhanvien add foreign key (idcv) references chucvu(id)
alter table hoadon add foreign key ( idNhanVien) references nhanvien(id)
alter table hoadon add foreign key ( idKhachHang) references khachhang(id)
alter table hoadon add foreign key ( idKhuyenMai) references khuyenmai(id)
alter table chitiethoadon add foreign key ( idHoaDon) references hoadon(id)
alter table chitietsanpham add foreign key (idSanPham) references sanpham(id)
alter table chitietsanpham add foreign key (hang) references hang(idhang)
alter table chitietsanpham add foreign key (size) references size(id)
alter table chitietsanpham add foreign key (danhMuc) references danhMuc(id)
alter table chitietsanpham add foreign key (chatLieu) references chatlieu(id)
alter table chitietsanpham add foreign key (mausac) references mausac(id)
alter table chitietsanpham add foreign key (idKhuyenMai) references khuyenmai(id)


ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FK_ChiTietHoaDon_HoaDon FOREIGN KEY (idHoaDon) REFERENCES HoaDon(ID) ON DELETE CASCADE;


INSERT INTO ChucVu (maChucVu, tenChucVu, ngayTao, ngaySua, trangThai)
VALUES
    ('CV001', 'Quản lý', '2024-01-07', '2024-01-07', 1),
    ('CV002', 'Nhân viên bán hàng', '2024-01-07', NULL, 1);
   Select * from NhanVien
INSERT INTO NhanVien (maNhanVien, hoVaTen, ngaySinh, gioiTinh, diaChi, sdt, email, matKhau, Hinh, NgayTao, NgaySua, TrangThai)
VALUES
    ('NV001', N'Nguyễn Văn Quang', '2002-01-01', 'Nam', 'Phu an, TP.Thanh Hoa', '0123456789', 'nva@example.com', '123', 'avatar1.jpg', '2024-01-07', '2024-01-07', 1),
    ('NV002', N'Trần Thị Binh', '2003-05-15', N'Nữ', 'Nam Tu Liem Ha Noi', '0987654321', 'ttb@example.com', '123', 'avatar2.jpg', '2024-01-07', '2024-01-07', 1),
    ('NV003', N'Lê Văn Nam', '1999-08-08', 'Nam', 'Cau Giay Ha Noi', '0369852147', 'lvc@example.com', '123', 'avatar3.jpg', '2024-01-07', '2024-01-07', 1);

INSERT INTO MauSac (Ma, Ten, NgayTao, NgaySua, TrangThai)
VALUES
    ('MS001', N'Đen', '2024-01-07', '2024-01-07', 1),
    ('MS002', N'Trắng', '2024-01-07', NULL, 1),
    ('MS003', N'Đỏ', '2024-01-07', NULL, 1);

INSERT INTO ChatLieu (Ma, Ten, NgayTao, NgaySua, TrangThai)
VALUES
    ('CL001', N'Da tổng hợp', '2024-01-07', '2024-01-07', 1),
    ('CL002', N'Vải', '2024-01-07', NULL, 1),
    ('CL003', N'Nỉ', '2024-01-07', NULL, 1);

INSERT INTO DanhMuc (Ma, Ten, NgayTao, NgaySua, TrangThai)
VALUES
    ('DM001', N'Giày nam', '2024-01-07', '2024-01-07', 1),
    ('DM002', N'Giày nữ', '2024-01-07', NULL, 1)

INSERT INTO Size (Ma, Ten, NgayTao, NgaySua, TrangThai)
VALUES
    ('SIZE001', '39', '2024-01-07', '2024-01-07', 1),
    ('SIZE002', '40', '2024-01-07', NULL, 1),
    ('SIZE003', '41', '2024-01-07', NULL, 1);

 INSERT INTO KhachHang (maKhachHang, loaiKhachHang, tenKhachHang, diaChi, gioiTinh, email, sdt, ngaySinh, ngayThamGia, tichDiem, diemEXP, ngayTao, ngaySua, trangThai)
VALUES
    ('KH001', 'Thân thiết', N'Nguyễn Thị Dung', 'HaNoi', N'Nữ', 'ntd@example.com', '0369852147', '1992-03-15', '2023-01-07', 100, 50, '2024-01-07', '2024-01-07', 1),
    ('KH002', 'VIP', N'Lê Văn Em',  'HaNoi', 'Nam', 'lve@example.com', '0987654321', '1985-08-22', '2022-01-07', 500, 200, '2024-01-07', '2024-01-07', 1),
    ('KH003', 'Thường', N'Trần Thị Phương',  'HaNoi', N'Nữ', 'tft@example.com', '0123456789', '1998-12-05', '2021-01-07', 20, 10, '2024-01-07', '2024-01-07', 1);
  
INSERT INTO SanPham (maSanPham, tenSanPham, NgayTao, NgaySua, TrangThai)
VALUES
    ('SP001', 'Sneaker Nam 001', '2024-01-07', '2024-01-07', 1),
    ('SP002', 'Sneaker Nữ 002', '2024-01-07', NULL, 1),
    ('SP003', 'Phụ kiện 003', '2024-01-07', NULL, 1);

INSERT INTO HoaDon (maHoaDon, ngayMua, thanhTien, GhiChu, NgayTao, NgaySua, TrangThai)
VALUES
    ('HD001', '2024-01-07', 2500000,  'Giao hàng nhanh', '2024-01-07', '2024-01-07', 1),
    ('HD002', '2024-01-07', 1800000,  NULL, '2024-01-07', NULL, 1),
    ('HD003', '2024-01-07', 1200000, 'Giao hàng tiết kiệm', '2024-01-07', NULL, 1);


INSERT INTO hang (maHang, tenHang, ngayTao, ngaySua, trangThai)
VALUES
    ('HANG001', 'Adidas', '2024-01-07', '2024-01-07', 1),
    ('HANG002', 'Nike', '2024-01-07', NULL, 1),
    ('HANG003', 'Puma', '2024-01-07', NULL, 1);


	select * from HoaDon
	select * from KhachHang
	select * from NhanVien
SELECT HoaDon.*
FROM    HoaDon


SELECT HoaDon.ID, HoaDon.maHoaDon, HoaDon.idNhanVien, HoaDon.idKhachHang, HoaDon.ngayMua, HoaDon.thanhTien, HoaDon.idKhuyenMai, NhanVien.hoVaTen
FROM     HoaDon INNER JOIN
                  NhanVien ON HoaDon.idNhanVien = NhanVien.Id


Select * from HoaDon
select * from ChiTietHoaDon
select * from ChiTietSanPham
Select * from NhanVien
SELECT ID FROM HoaDon WHERE maHoaDon = 'HD45633'
SELECT * FROM ChiTietHoaDon WHERE idHoaDon ='C2180C2E-BA28-484C-B848-F2FA9F69B6AA' 

SELECT Id, idHoaDon, idChiTietSanPham, soLuong, donGia, ngayBan, ngayTao, ngaySua, trangThai
FROM     ChiTietHoaDon where idHoaDon = 'C2180C2E-BA28-484C-B848-F2FA9F69B6AA'

SELECT ChiTietSanPham.Id, SanPham.tenSanPham AS TenSanPham, Hang.tenHang AS
TenHang, Size.Ten AS TenSize, DanhMuc.Ten AS TenDanhMuc, ChatLieu.Ten AS TenChatLieu,
MauSac.Ten AS TenMauSac, ChiTietSanPham.giaNhap, ChiTietSanPham.giaBan, ChiTietSanPham.QR,
ChiTietSanPham.HinhAnh, ChiTietSanPham.SoLuong, ChiTietSanPham.MoTa, ChiTietSanPham.NgayTao,
ChiTietSanPham.NgaySua, ChiTietSanPham.TrangThai FROM ChiTietSanPham JOIN SanPham ON ChiTietSanPham.idSanPham = 
SanPham.Id JOIN Hang ON ChiTietSanPham.hang = Hang.idHang JOIN Size ON ChiTietSanPham.Size = Size.Id JOIN DanhMuc
ON ChiTietSanPham.DanhMuc = DanhMuc.Id JOIN ChatLieu ON ChiTietSanPham.ChatLieu = ChatLieu.Id JOIN MauSac ON ChiTietSanPham.MauSac = MauSac.Id


SELECT ChiTietHoaDon.Id, ChiTietHoaDon.idHoaDon, ChiTietHoaDon.idChiTietSanPham, ChiTietHoaDon.soLuong, ChiTietHoaDon.donGia, ChiTietHoaDon.ngayBan, ChiTietHoaDon.ngayTao, ChiTietHoaDon.ngaySua, ChiTietHoaDon.trangThai, ChiTietSanPham.Id, SanPham.tenSanPham AS TenSanPham, Hang.tenHang AS TenHang, Size.Ten AS TenSize, DanhMuc.Ten AS TenDanhMuc, ChatLieu.Ten AS TenChatLieu, MauSac.Ten AS TenMauSac, ChiTietSanPham.giaBan
FROM ChiTietHoaDon 
JOIN ChiTietSanPham ON ChiTietHoaDon.idChiTietSanPham = ChiTietSanPham.Id 
JOIN SanPham ON ChiTietSanPham.idSanPham = SanPham.Id 
JOIN Hang ON ChiTietSanPham.hang = Hang.idHang 
JOIN Size ON ChiTietSanPham.Size = Size.Id 
JOIN DanhMuc ON ChiTietSanPham.DanhMuc = DanhMuc.Id 
JOIN ChatLieu ON ChiTietSanPham.ChatLieu = ChatLieu.Id 
JOIN MauSac ON ChiTietSanPham.MauSac = MauSac.Id where idHoaDon = 'C2180C2E-BA28-484C-B848-F2FA9F69B6AA'

Select * from SanPham

SELECT Id, idSanPham, hang, Size, DanhMuc, ChatLieu, MauSac, idKhuyenMai, giaNhap, giaBan, QR, HinhAnh, SoLuong, MoTa, NgayTao
FROM     ChiTietSanPham where idSanPham = 'EF1502AA-5DB5-407E-B7DB-519DA92F72C0';

UPDATE ChiTietHoaDon
SET   soLuong = 1,donGia = 30000 where Id = '8AC011C1-77F8-47DB-9B90-F3585AC36BAA'


select * from HoaDon
select * from ChiTietHoaDon
select * from ChiTietSanPham


SELECT idChiTietSanPham FROM ChiTietHoaDon WHERE idHoaDon = '0D74363A-8014-4A83-9C8B-AF99FC8720F2'

UPDATE ChiTietHoaDon
SET  soLuong = 10 where id ='6F96511E-E059-41E1-BB17-DF8CE7F52D9C'

SELECT soLuong FROM ChiTietHoaDon where idChiTietSanPham ='97CC5BF2-9F9D-4CDA-A372-34547F0014B9';
SELECT idChiTietSanPham FROM ChiTietHoaDon where id ='F2F99031-F81F-4A14-99A5-69B3B632ACC4';

SELECT idChiTietSanPham FROM ChiTietHoaDon WHERE id = '225387A0-4D81-4DBF-8B7B-ADF84C02DA65'



select * from HoaDon
select * from ChiTietSanPham
select * from KhuyenMai
select * from ChiTietHoaDon

select * from ChiTietHoaDon as cthd
join ChiTietSanPham as ctsp
on cthd.idChiTietSanPham = ctsp.Id
where cthd.idChiTietSanPham =  '3242B4BB-E15F-42DE-89F0-ADA1C6337732'

Select * from ChiTietSanPham 
SELECT * FROM ChiTietSanPham WHERE idSanPham = 'EF1502AA-5DB5-407E-B7DB-519DA92F72C0' AND hang = '8EEEB8A4-17CF-43CF-AD53-188C5E080252' AND Size = '31CC6329-1128-4D30-B972-479AF774F319' AND DanhMuc = '0C32EEB4-BC04-46F4-93D9-0143B5136422' AND ChatLieu = 'C6EB2BD8-F264-4B8D-BB1A-03C697DC4A5E' AND MauSac = '8611AF39-B533-43F5-B6A8-23A999D023C9'
Select * from NhanVien


SELECT ISNULL(SUM(donGia), 0) AS TotalDonGia FROM ChiTietHoaDon WHERE idHoaDon = '5970A18D-D02A-486B-93E9-435DDCF4C2DC' AND trangThai = 0
Select * from KhachHang
UPDate KhachHang set trangThai = 1 where id = ?;
SELECT maHoaDon, idNhanVien, ngayMua, thanhTien, GhiChu
FROM     HoaDon where idKhachHang = '2CDACBD8-902F-4A1F-B74D-072FD3248895'


UPDATE HoaDon
SET          TienKhachDua =?, TienThua =?, TrangThai = 1

SELECT HoaDon.maHoaDon,NhanVien.hoVaTen,KhachHang.tenKhachHang,HoaDon.ngayMua,HoaDon.TrangThai
FROM HoaDon
LEFT JOIN
    NhanVien ON HoaDon.idNhanVien = NhanVien.ID
LEFT JOIN
    KhachHang ON HoaDon.idKhachHang = KhachHang.ID
WHERE
    HoaDon.TrangThai = 0

	Select * from khachHang
	SELECT KhachHang.sdt FROM HoaDon 
                     JOIN KhachHang ON HoaDon.idKhachHang = KhachHang.ID 
                     WHERE HoaDon.ID = 'DB0A2CA2-7622-4CD1-8EEA-4A786D42F511'

	Select * from KhuyenMai

INSERT INTO ChiTietSanPham
                  (idSanPham, hang, Size, DanhMuc, ChatLieu, MauSac, idKhuyenMai, giaNhap, giaBan, SoLuong, MoTa, HinhAnh, TrangThai)
VALUES ('F03F846D-D5FA-4345-9455-24A6905A1127','88F6CFB5-64B6-4249-9E54-BA5B9A8510BA','022A662E-05CE-4117-866A-02ABEAF51225','5D67CCCA-924E-4C16-AA7E-4061A4BBC4CB','904FCD42-90FD-4062-A37F-19F758B24AB4','E7BC7FE6-FB28-454A-B03F-25E6EA88770B',Null,200000,250000,10,Null,'ok',1),
       ('1C976480-2258-434F-918B-6B7A744884BD','024DDF0A-2CC7-48DB-B458-BC0E80980E74','F8A34054-4C50-4F79-80D6-4285206C73BA','5D67CCCA-924E-4C16-AA7E-4061A4BBC4CB','904FCD42-90FD-4062-A37F-19F758B24AB4','E7BC7FE6-FB28-454A-B03F-25E6EA88770B',Null,300000,350000,10,Null,'pk',1),
	   ('F03F846D-D5FA-4345-9455-24A6905A1127','34D7A852-9052-4059-A1E6-E0876DA5343C','E2B8C949-983E-458B-9246-57E85DD13F1B','88C1B78B-5031-4E52-8FE9-E6F93DC80A11','F39D5FD2-B55E-494C-B9CE-7449E38C2C4E','E2994990-F5C2-4188-8BAC-69764F5E3781',Null,400000,450000,10,Null,'ok',1)

Sel
Delete  from NhanVien  
UPDATE HoaDon
SET          idNhanVien ='BA661C47-987D-44E1-B281-48AE08EF7BBB' where id = 'AD7DA7B5-B08A-4FA1-A935-18768FA90471'
UPDATE ChiTietHoaDon
SET     trangThai = 1 where idHoaDon = '0C5C5FBB-E9DE-4763-8662-D91C08F8C503';

SELECT cthd.*, csp.*
FROM ChiTietHoaDon cthd
JOIN ChiTietSanPham csp ON cthd.idChiTietSanPham = csp.Id
WHERE cthd.Id = 'DC360513-5DA3-40CC-8A6C-03A209941DFE';

Select * from ChiTietHoaDon
