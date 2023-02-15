USE [master]
GO
/****** Object:  Database [HaLo'sShop]    Script Date: 15/02/2023 6:07:01 CH ******/
CREATE DATABASE [HaLo'sShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'KidStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\KidStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'KidStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\KidStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [HaLo'sShop] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HaLo'sShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HaLo'sShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HaLo'sShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HaLo'sShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HaLo'sShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HaLo'sShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HaLo'sShop] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [HaLo'sShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HaLo'sShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HaLo'sShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HaLo'sShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HaLo'sShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HaLo'sShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HaLo'sShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HaLo'sShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HaLo'sShop] SET  ENABLE_BROKER 
GO
ALTER DATABASE [HaLo'sShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HaLo'sShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HaLo'sShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HaLo'sShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HaLo'sShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HaLo'sShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HaLo'sShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HaLo'sShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HaLo'sShop] SET  MULTI_USER 
GO
ALTER DATABASE [HaLo'sShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HaLo'sShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HaLo'sShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HaLo'sShop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [HaLo'sShop] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [HaLo'sShop] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [HaLo'sShop] SET QUERY_STORE = OFF
GO
USE [HaLo'sShop]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 15/02/2023 6:07:01 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[uid] [int] IDENTITY(1,1) NOT NULL,
	[user] [nvarchar](255) NULL,
	[password] [varchar](255) NULL,
	[isAdmin] [int] NULL,
	[email] [varchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[phoneNumber] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[uid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 15/02/2023 6:07:02 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[bid] [int] IDENTITY(1,1) NOT NULL,
	[uid] [int] NOT NULL,
	[buyDate] [date] NULL,
	[priceTotal] [money] NULL,
	[status] [nvarchar](255) NULL,
	[node] [nvarchar](max) NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[bid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BillProduct]    Script Date: 15/02/2023 6:07:02 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillProduct](
	[bpid] [int] IDENTITY(1,1) NOT NULL,
	[unitPrice] [money] NULL,
	[quantity] [int] NULL,
	[bid] [int] NULL,
	[pid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[bpid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 15/02/2023 6:07:02 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[cid] [int] IDENTITY(1,1) NOT NULL,
	[cName] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[cid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 15/02/2023 6:07:02 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[pid] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](max) NULL,
	[image] [nvarchar](max) NULL,
	[price] [money] NULL,
	[title] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[cateID] [int] NULL,
	[oldPrice] [money] NULL,
	[sumProduct] [int] NULL,
	[presentProduct] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (1, N'Lê Minh Long', N'2IFMGZr/IzCwLDFoI3DSJj6DjCY=', 1, N'leminhlongit@gmail.com', N' Ấp Phú, xã Châu Hòa, huyện Giồng Trôm, tỉnh Bến Tre , Đà Lạt					', N'0374781483')
INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (2, N'La Thị Thúy Hảo', N'zx0W9nuNr+C3SEG7ocJ6gLNlLkM=
', 1, N'lathithuyhao@gmail.com', N'   Số 123, đường Nguyễn Du, phường 4, thành phố Đà Lạt , tỉnh Lâm Đồng																 																 																', N'0364677119')
INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (3, N'Nguyễn Nhật Linh', N'5zcKdzE730jkAjFlX5wg9/peC20=', 0, N'nhatlinh@gmail.com', N'   Số 123, đường Nguyễn Du, phường 4, thành phố Đà Lạt , tỉnh Lâm Đồng																 																 																', N'0343261245')
INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (4, N'Huỳnh Công Đạt', N'3f0zuuMzqOcD0CEb9M8NPhCAzZg=', 0, N'huynhcongdat@gmail.com', N'   Số 123, đường Nguyễn Du, phường 4, thành phố Đà Lạt , tỉnh Lâm Đồng																 																 																', N'0312476121')
INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (5, N'Mai Hữu Lộc', N'+H0SOeDXPrzizUz0o0naoTzyCEg=', 0, N'maihuuloc@gmail.com', N'Quận 1,Thành phố Hồ Chí Minh', N'0343261245')
INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (9, N'Nguyễn Ngọc An', N'2EsmEcpW9r+h3DaX3ssvI8+jMaQ=', 0, N'AnNguyen@gmail.com', N'số 85, Hoàng Ân, thành phố Đà Lạt, tỉnh Lâm Đồng 
																 
																 
																 
																', N'0364718982')
INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (20, N'Thắm Huỳnh đẹp gái', N'/EIXlfWCF7IhhAxIcupm70MiFpA=', 0, N'huynhtham3008@gmail.com', N' Ấp Phú Thuận,xã Châu Hòa, Huyện Giồng Trôm, Tỉnh Bến Tre 
																', N'0374681482')
INSERT [dbo].[Account] ([uid], [user], [password], [isAdmin], [email], [address], [phoneNumber]) VALUES (21, N'LyZy Phan Hoang', N'OtHy/n6QlfYzF8bG4HEfH/lAhFY=', 0, N'20130253@st.hcmuaf.edu.vn', N'34 Hoang Anh, phường 5 thành phố Berlin', N'0367182934')
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Bill] ON 

INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (1, 2, CAST(N'2022-11-27' AS Date), 894400.0000, N'Từ chối', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (2, 2, CAST(N'2022-11-27' AS Date), 1793090.0000, N'Đã duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (3, 2, CAST(N'2022-11-27' AS Date), 925600.0000, N'Đã duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (5, 9, CAST(N'2022-12-04' AS Date), 622960.0000, N'Đã duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (6, 9, CAST(N'2022-12-04' AS Date), 1059890.0000, N'Đã duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (7, 9, CAST(N'2022-12-06' AS Date), 488800.0000, N'Chưa duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (8, 9, CAST(N'2022-12-07' AS Date), 488800.0000, N'Chưa duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (9, 1, CAST(N'2022-12-13' AS Date), 2199080.0000, N'Chưa duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (10, 1, CAST(N'2022-12-19' AS Date), 665600.0000, N'Chưa duyệt', NULL)
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (11, 1, CAST(N'2022-12-19' AS Date), 332800.0000, N'Chưa duyệt', N'Giao nhanh giúp em ạ')
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (12, 3, CAST(N'2022-12-19' AS Date), 332800.0000, N'Chưa duyệt', N'giao nhanh giúp em')
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (13, 3, CAST(N'2022-12-19' AS Date), 2375230.0000, N'Chưa duyệt', N'hé hé')
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (14, 1, CAST(N'2022-12-19' AS Date), 2385500.0000, N'Chưa duyệt', N'Giao nhanh giúp em')
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (15, 1, CAST(N'2022-12-20' AS Date), 954720.0000, N'Chưa duyệt', N'giao')
INSERT [dbo].[Bill] ([bid], [uid], [buyDate], [priceTotal], [status], [node]) VALUES (16, 21, CAST(N'2023-02-10' AS Date), 1432080.0000, N'Chưa duyệt', N'')
SET IDENTITY_INSERT [dbo].[Bill] OFF
GO
SET IDENTITY_INSERT [dbo].[BillProduct] ON 

INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (1, 223600.0000, 4, 1, 10)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (2, 394290.0000, 1, 2, 5)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (3, 364000.0000, 2, 2, 6)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (4, 223600.0000, 3, 2, 10)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (5, 332800.0000, 2, 3, 11)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (6, 260000.0000, 1, 3, 37)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (10, 622960.0000, 1, 5, 46)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (11, 394290.0000, 1, 6, 4)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (12, 332800.0000, 2, 6, 10)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (13, 488800.0000, 1, 7, 44)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (14, 488800.0000, 1, 8, 44)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (15, 488800.0000, 3, 9, 44)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (16, 388700.0000, 1, 9, 3)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (17, 114660.0000, 3, 9, 6)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (18, 332800.0000, 2, 10, 10)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (19, 332800.0000, 1, 11, 10)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (20, 332800.0000, 1, 12, 10)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (21, 488800.0000, 4, 13, 44)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (22, 420030.0000, 1, 13, 1)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (23, 388700.0000, 1, 14, 3)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (24, 332800.0000, 6, 14, 10)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (25, 477360.0000, 2, 15, 45)
INSERT [dbo].[BillProduct] ([bpid], [unitPrice], [quantity], [bid], [pid]) VALUES (26, 477360.0000, 3, 16, 45)
SET IDENTITY_INSERT [dbo].[BillProduct] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([cid], [cName]) VALUES (1, N'Giày thể thao bé trai')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (2, N'Set đồ bé trai')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (3, N'Giày thể thao bé gái')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (4, N'Set đồ bé gái')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (5, N'Giày/Dép bé trai')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (6, N'Giày/Dép bé gái')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (7, N'Áo bé trai')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (8, N'Áo bé gái')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (9, N'Quần bé trai')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (10, N'Quần bé gái')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (11, N'Đầm\Váy')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (12, N'Đồ chơi trẻ em')
INSERT [dbo].[Category] ([cid], [cName]) VALUES (13, N'Đồ cho cả bé gái và bé trai')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (1, N'Giày sandal aleiza màu hồng', N'./image/1.jpg', 420030.0000, N'Giày sandal aleiza màu hồng', N'Giày sandal aleiza màu hồng dành cho bé gái từ 5 đến 10 tuổi', 6, 466700.0000, 54, 26)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (2, N'Set chấm bi vàng mã 2N3123', N'./image/2.jpg', 284700.0000, N'Set chấm bi vàng mÃ 2N3123', N'Set chấm bi vàng mÃ 2N3123 dành cho bé từ 1 đến 3 tuổi', 13, 284700.0000, 54, 0)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (3, N'Giày NIKE trắng bé trai mã GIAY074T', N'./image/3.jpg', 388700.0000, N'Giày NIKE trắng bé trai mã GIAY074T', N'Giày NIKE trắng bé trai mã GIAY074T dành cho bé trai từ 1 đến 4 tuổi', 13, 388700.0000, 54, 30)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (4, N'Giày sandal trắng vani bé gái', N'./image/4.jpg', 394290.0000, N'Giày sandal trắng vani bé gái', N'Giày sandal trắng vani bé gái dành cho bé 1 đến 3 tuổi', 6, 438100.0000, 54, 10)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (5, N'Set đồ tiểu thư mã TT012', N'./image/6.jpg', 364000.0000, N'Set đồ tiểu thư mã TT012', N'Set đồ tiểu thư mã TT012 dành cho bé từ 2 đến 5 tuổi', 4, 364000.0000, 67, 12)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (6, N'Dép gấu bé gái mã D187', N'./image/7.jpg', 114660.0000, N'Dép gấu bé gái mã D187', N'Dép gấu bé gái mã D187 dành cho bé từ 5 đến 10 tuổi', 6, 127400.0000, 67, 12)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (7, N'Giày sandal bé gái mã SD1876', N'./image/8.jpg', 278460.0000, N'Giày sandal bé gái mã SD1876', N'Giày sandal bé gái mã SD1876 dành cho bé gái từ 5 đến 10 tuổi', 6, 309400.0000, 67, 26)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (8, N'Giày sandal bé gái mã SD1776', N'./image/9.jpg', 186030.0000, N'Giày sandal bé gái mã SD1776', N'Giày sandal bé gái mã SD1776 dành cho bé gái từ 2 đến 5 tuổi', 6, 206700.0000, 67, 12)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (9, N'Giày NIKE bé trai mã N1752', N'./image/10.jpg', 223600.0000, N'Giày NIKE bé trai mã N1752', N'Giày NIKE bé trai mã N1752 từ 3 đến 10 tuổi', 1, 279500.0000, 67, 32)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (10, N'Giày NIKE bé trai mã N1849', N'./image/11.jpg', 332800.0000, N'Giày NIKE bé trai mã N1752', N'Giày NIKE bé trai mã N1752 dành cho bé trai từ 3 đến 5 tuổi', 1, 416000.0000, 67, 38)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (11, N'Giày sandal bé gái mã SD1723', N'./image/12.jpg', 333450.0000, N'Giày sandal bé gái mã SD1723', N'Giày sandal bé gái mã SD1723 dành cho bé gái từ 5 đến 10 tuổi', 6, 370500.0000, 67, 12)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (12, N'Chân váy công chúa', N'./image/13.jpg', 154960.0000, N'Chân váy công chúa', N'Chân váy công chúa dành cho bé từ 3 đến 10 tuổi', 11, 193700.0000, 67, 26)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (13, N'Giày gấu bé gái mã GG143', N'./image/14.jpg', 299000.0000, N'Giày gấu bé gái mã GG143', N'Giày gấu bé gái mã GG143 dành cho bé từ 8 tháng đến 2 tuổi', 3, 299000.0000, 67, 38)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (14, N'Set yếm bé gái mã Y162', N'./image/15.jpg', 332800.0000, N'Set yếm bé gái mã Y162', N'Set yếm bé gái mã Y162 dành cho bé từ 2 đến 5 tuổi', 4, 332800.0000, 67, 12)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (15, N'Set yếm mã Y147', N'./image/16.jpg', 227760.0000, N'Set yếm mã Y147', N'Set yếm mã Y147 cho bé từ 8 đến 20 tháng tuổi', 13, 284700.0000, 54, 21)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (16, N'Dép quai ngang style cho bé trai mã QN348', N'./image/17.jpg', 189800.0000, N'Dép quai ngang style cho bé trai mã QN348', N'Dép quai ngang style cho bé trai mã QN348 dành cho bé trai từ 3 đến 5 tuổi', 5, 189800.0000, 54, 26)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (17, N'Giày sandal bé gái mã SD3471', N'./image/18.jpg', 286650.0000, N'Giày sandal bé gái mã SD3471', N'Giày sandal bé gái mã SD3471 dành cho bé từ 5 đến 10 tuổi', 6, 318500.0000, 54, 10)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (18, N'Đầm cherry', N'./image/19.jpg', 205920.0000, N'Đầm cherry', N'Đầm cherry dành cho bé từ 18 tháng đến 3 tuổi', 11, 257400.0000, 54, 30)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (19, N'Đầm công chúa tím ', N'./image/20.jpg', 518960.0000, N'Đầm công chúa tím ', N'Đầm công chúa tím  dành cho bé từ 3 đến 5 tuổi', 11, 648700.0000, 54, 30)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (20, N'Set đồ bé gái mùa hè', N'./image/21.jpg', 505700.0000, N'Set đồ bé gái mùa hè', N'Set đồ bé gái mùa hè dành cho bé từ 1 đến 3 tuổi', 4, 505700.0000, 54, 10)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (21, N'Set đồ sơ sinh mã SS176', N'./image/22.jpeg', 227760.0000, N'Set đồ sơ sinh mã SS176', N'Set đồ sơ sinh mã SS176 dành cho bé sơ sinh', 13, 284700.0000, 54, 21)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (22, N'Giày NIKE mã N935', N'./image/23.jpg', 369200.0000, N'Giày NIKE mã N935', N'Giày NIKE mã N935 dành cho bé từ 18 tháng đến 3 tuổi', 13, 461500.0000, 54, 26)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (23, N'Giày NIKE bé trai mã N878', N'./image/24.jpg', 362960.0000, N'Giày NIKE bé trai mã N878', N'Giày NIKE bé trai mã N878 dành cho bé từ 2 đến 5 tuổi', 1, 453700.0000, 54, 30)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (24, N'Áo mùa hè bé gái', N'./image/25.png', 232830.0000, N'Áo mùa hè bé gái', N'Áo mùa hè bé gái dành cho bé từ 1 đến 3 tuổi', 8, 258700.0000, 65, 25)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (25, N'Giày NIKE flash đèn', N'./image/26.jpg', 395200.0000, N'Giày NIKE flash đèn', N'Giày NIKE flash đèn dành cho bé từ 1 đến 3 tuổi', 13, 494000.0000, 65, 25)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (26, N'Dép gấu 2 màu', N'./image/27.jpg', 133120.0000, N'Dép gấu 2 màu', N'Dép gấu 2 màu dành cho bé từ 3 đến 5 tuổi', 13, 166400.0000, 65, 25)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (27, N'Áo sơ sinh hình thú', N'./image/28.png', 102960.0000, N'Áo sơ sinh hình thú', N'Áo sơ sinh hình thú dành cho trẻ sơ sinh', 13, 128700.0000, 63, 24)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (28, N'Áo hoa cúc nhí bé gái', N'./image/29.png', 115830.0000, N'Áo hoa cúc nhí bé gái', N'Áo hoa cúc nhí bé gái dành cho bé 2 đến 5 tuổi', 8, 128700.0000, 63, 51)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (29, N'Áo kiểu hoa nhí xanh bé gái', N'./image/30.jpg', 150930.0000, N'Áo kiểu hoa nhí xanh bé gái', N'Áo kiểu hoa nhí xanh bé gái dành cho bé từ 1 đến 2 tuổi', 8, 167700.0000, 63, 24)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (30, N'Set đồ hoa mùa hè bé gái mã HH87', N'./image/31.jpg', 442000.0000, N'Set đồ hoa mùa hè bé gái mã HH87', N'Set đồ hoa mùa hè bé gái mã HH87 dành cho bé từ 2 tuổi', 4, 442000.0000, 51, 9)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (31, N'Dép nơ chấm bi', N'./image/32.jpg', 269100.0000, N'Dép nơ chấm bi', N'Dép nơ chấm bi dành cho bé từ 2 tuổi', 6, 299000.0000, 51, 9)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (32, N'Giày NIKE đỏ cổ cao bé trai', N'./image/33.jpg', 405600.0000, N'Giày NIKE đỏ cổ cao bé trai', N'Giày NIKE đỏ cổ cao bé trai dành cho bé từ 3 đến 5 tuổi', 1, 507000.0000, 51, 29)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (33, N'Giày sandal bé gái mã SD342', N'./image/34.jpg', 279630.0000, N'Giày sandal bé gái mã SD342', N'Giày sandal bé gái mã SD342 dành cho bé từ 2 đến 5 tuổi', 6, 310700.0000, 65, 12)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (34, N'Giày sandal bé trai mã SD098', N'./image/35.jpg', 299000.0000, N'Giày sandal bé trai mã SD098', N'Giày sandal bé trai mã SD098 dành cho bé từ 2 đến 5 tuổi', 5, 299000.0000, 65, 31)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (35, N'Giày sandal bé trai mã SD056', N'./image/36.jpg', 299000.0000, N'Giày sandal bé trai mã SD056', N'Giày sandal bé trai mã SD056 dành cho bé từ 2 đến 5 tuổi', 5, 299000.0000, 65, 31)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (36, N'Áo len cụt tay dễ thương', N'./image/37.jpg', 260000.0000, N'Áo len cụt tay dễ thương', N'Áo len cụt tay dễ thương dành cho bé từ 2 đến 5 tuổi', 13, 325000.0000, 65, 25)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (37, N'Áo thun thuyền Titanic bé trai', N'./image/38.jpg', 127400.0000, N'Áo thun thuyền Titanic bé trai', N'Áo thun thuyền Titanic dành cho bé trai từ 2 đến 5 tuổi', 7, 127400.0000, 65, 31)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (38, N'Giày NIKE hoa cúc bé gái', N'./image/39.jpg', 481000.0000, N'Giày NIKE hoa cúc bé gái', N'Giày NIKE hoa cúc bé gái dành cho bé từ 3 đến 5 tuổi', 3, 481000.0000, 65, 37)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (39, N'Set mùa hè hoa nhí bé gái', N'./image/40.jpg', 533000.0000, N'Set mùa hè hoa nhí bé gái', N'Set mùa hè hoa nhí bé gái dành cho bé gái từ 3 đến 5 tuổi có bao gồm mũ', 4, 533000.0000, 60, 11)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (40, N'Giày NIKE flash đèn đủ màu mã N467', N'./image/41.jpg', 477360.0000, N'Giày NIKE flash đèn đủ màu mã N467', N'Giày NIKE flash đèn đủ màu mã N467 dành cho bé từ 1 đến 3 tuổi', 13, 596700.0000, 60, 23)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (41, N'Dép bệt bé gái có nơ', N'./image/42.jpg', 269100.0000, N'Dép bệt bé gái có nơ', N'Dép bệt bé gái có nơ dành cho bé từ 5 đến 10 tuổi', 6, 299000.0000, 60, 11)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (42, N'Set hoàng tử lịch lãm', N'./image/43.jpg', 648700.0000, N'Set hoàn tử lịch lãm', N'Set hoàn tử lịch lãm dành cho bé từ 2 đến 5 tuổi', 2, 648700.0000, 60, 29)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (43, N'Set đồ vét bé trai', N'./image/44.jpg', 689000.0000, N'Set đồ vét bé trai', N'Set đồ vét bé trai dành cho bé từ 2 đến 5 tuổi', 2, 689000.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (44, N'Đầm công chúa nơ đỏ bạch tuyết', N'./image/45.jpg', 488800.0000, N'Đầm công chúa nơ đỏ bạch tuyết', N'Đầm công chúa nơ đỏ bạch tuyết dành cho bé từ 1 đến 3 tuổi', 11, 611000.0000, 60, 48)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (45, N'Đầm công chúa đậu đỏ', N'./image/46.jpg', 477360.0000, N'Đầm công chúa đậu đỏ', N'Đầm công chúa đậu đỏ dành cho bé 2 đến 3 tuổi', 11, 596700.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (46, N'Đầm nữ hoàng đỏ', N'./image/47.jpg', 622960.0000, N'Đầm nữ hoàng đỏ', N'Đầm nữ hoàng đỏ dành cho bé từ 5 đến 10 tuổi', 11, 778700.0000, 60, 48)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (47, N'Đầm công chúa mã CC98', N'./image/48.jpg', 227760.0000, N'Đầm công chúa mã CC98', N'Đầm công chúa mã CC98 dành cho bé từ 1 đến 3 tuổi', 11, 284700.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (48, N'Đầm trắng thanh lịch', N'./image/49.jpg', 206960.0000, N'Đầm trắng thanh lịch', N'Đầm trắng thanh lịch dành cho bé từ 1 đến 3 tuổi', 11, 258700.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (49, N'Đầm công chúa thanh lịch đỏ có nơ', N'./image/50.jpg', 218400.0000, N'Đầm công chúa thanh lịch đỏ có nơ', N'Đầm công chúa thanh lịch đỏ có nơ dành cho bé từ 5 đến 10 tuổi', 11, 273000.0000, 60, 48)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (50, N'Giày công chúa hồng có nơ lấp lánh', N'./image/51.jpg', 244530.0000, N'Giày công chúa hồng có nơ lấp lánh', N'Giày công chúa hồng có nơ lấp lánh dành cho bé từ 3 đến 10 tuổi', 6, 271700.0000, 60, 29)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (51, N'Chân váy công chúa xanh', N'./image/52.jpg', 206960.0000, N'Chân váy công chúa xanh', N'Chân váy công chúa xanh dành cho bé 2 đến 5 tuổi', 11, 258700.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (52, N'Đầm đỏ lấp lánh kiêu sa', N'./image/53.jpg', 134160.0000, N'Đầm đỏ lấp lánh kiêu sa', N'Đầm đỏ lấp lánh kiêu sa dành cho bé 2 đến 5 tuổi có kèm theo túi nơ', 11, 167700.0000, 60, 48)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (53, N'Đầm vàng thanh lịch', N'./image/55.jpg', 186160.0000, N'Đầm vàng thanh lịch', N'Đầm vàng thanh lịch dành cho bé từ 2 đến 3 tuổi', 11, 232700.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (54, N'Giày công chúa kim cương lấp lánh', N'./image/56.jpg', 279630.0000, N'Giày công chúa kim cương lấp lánh', N'Giày công chúa kim cương lấp lánh dành cho bé từ 5 đến 10 tuổi', 6, 310700.0000, 60, 11)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (55, N'Set đồ caro hường bé gái', N'./image/57.png', 388700.0000, N'Set đồ caro hường bé gái', N'Set đồ caro hường bé gái dành cho bé từ 3 đến 5 tuổi', 4, 388700.0000, 60, 11)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (56, N'Đầm hoa nhí công chúa dễ thương', N'./image/58.jpg', 289120.0000, N'Đầm hoa nhí công chúa dễ thương', N'Đầm hoa nhí công chúa dễ thương dành cho bé từ 3 đến 5 tuổi', 11, 361400.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (57, N'Đầm đậu nành sang chảnh', N'./image/59.jpg', 384800.0000, N'Đầm đậu nành sang chảnh', N'Đầm đậu nành sang chảnh dành cho bé từ 2 đến 5 tuổi', 11, 481000.0000, 60, 34)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (58, N'Set đồ sơ sinh chim cánh cụt', N'./image/60.jpg', 405600.0000, N'Set đồ sơ sinh chim cánh cụt', N'Set đồ sơ sinh chim cánh cụt dành cho trẻ sơ sinh', 13, 507000.0000, 60, 23)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (59, N'Set đồ sơ sinh hồ ly', N'./image/61.jpg', 405600.0000, N'Set đồ sơ sinh hồ ly', N'Set đồ sơ sinh hồ ly dành cho bé sơ sinh', 13, 507000.0000, 60, 23)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (60, N'Set nơ vàng bé gái mã v176', N'./image/62.jpg', 388700.0000, N'Set nơ vàng bé gái mã v176', N'Set nơ vàng bé gái mã v176 dành cho bé từ 2 đến 5 tuổi', 4, 388700.0000, 69, 13)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (61, N'Đồ chơi cua trong suốt', N'./image/54.jpg', 110500.0000, N'Đồ chơi cua trong suốt', N'Đồ chơi cua trong suốt dành cho trẻ từ 18 tháng tuổi trở lên trở lên', 12, 110500.0000, 69, 39)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (62, N'Đầm tiểu thư đậu nành dễ thương', N'./image/64.jpg', 227760.0000, N'Đầm tiểu thư đậu nành dễ thương', N'Đầm tiểu thư đậu nành dễ thương dành cho bé từ 5 đến 10 tuổi', 11, 284700.0000, 69, 39)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (63, N'Giày sandal cho bé mã SD956', N'./image/65.jpg', 232830.0000, N'Giày sandal cho bé mã SD956', N'Giày sandal cho bé mã SD956 dành cho bé gái từ 2 đến 5 tuổi', 6, 258700.0000, 69, 13)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (64, N'Đồ chơi láp ráp phát triển trí não', N'./image/63.jpg', 453700.0000, N'Đồ chơi láp ráp phát triển trí não', N'Đồ chơi láp ráp phát triển trí não dành cho trẻ từ 18 tháng tuổi trở lên', 12, 453700.0000, 69, 39)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (65, N'Giày cao cổ trắng sang chảnh', N'./image/66.jpg', 698490.0000, N'Giày cao cổ trắng sang chảnh', N'Giày cao cổ trắng sang chảnh dành cho bé từ 5 đến 10 tuổi', 6, 776100.0000, 69, 13)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (66, N'Đầm công chúa mùa xuân', N'./image/67.jpg', 726960.0000, N'Đầm công chúa mùa xuân', N'Đầm công chúa mùa xuân dành cho bé gái từ 5 đến 10 tuổi', 11, 908700.0000, 69, 39)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (67, N'Giày công chúa hường lấp lánh có nơ mã NO198', N'./image/68.jpg', 326430.0000, N'Giày công chúa hường lấp lánh có nơ mã NO198', N'Giày công chúa hường lấp lánh có nơ mã NO198 dành cho bé từ 5 đến 10 tuổi', 6, 362700.0000, 69, 33)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (68, N'Dép cá sáu cho bé trai', N'./image/69.jpg', 258700.0000, N'Dép cá sáu cho bé trai', N'Dép cá sáu cho bé trai dành cho bé từ 2 đến 5 tuổi', 5, 258700.0000, 69, 26)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (69, N'Dép thỏ hồng bé gái', N'./image/70.jpg', 232830.0000, N'Dép thỏ hồng bé gái', N'Dép thỏ hồng bé gái dành cho bé từ 2 đến 5 tuổi', 6, 258700.0000, 69, 13)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (70, N'Giày NIKE cổ cao trắng đen mã N782', N'./image/71.jpg', 388700.0000, N'Giày NIKE cổ cao trắng đen mã N782', N'Giày NIKE cổ cao trắng đen mã N782 dành cho bé 3 đến 5 tuổi', 13, 388700.0000, 69, 39)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (71, N'Giày hoàng tử bé', N'./image/72.jpg', 509600.0000, N'Giày hoàng tử bé', N'Giày hoàng tử bé dành cho bé từ 3 đến 10 tuổi', 1, 637000.0000, 69, 33)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (72, N'Giày tiểu thư hồng dễ thương', N'./image/73.jpg', 648700.0000, N'Giày tiểu thư hồng dễ thương', N'Giày tiểu thư hồng dễ thương dành cho bé từ 3 đến 10 tuổi', 3, 648700.0000, 69, 39)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (73, N'Dép gà hồng bé gái dễ thương', N'./image/74.png', 232830.0000, N'Dép gà hồng bé gái dễ thương', N'Dép gà hồng bé gái dễ thương dành cho bé từ 3 đến 5 tuổi', 6, 258700.0000, 69, 13)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (74, N'Giày NIKE flash đủ màu sám mã N961', N'./image/75.jpg', 388700.0000, N'Giày NIKE flash đủ màu sám mã N961', N'Giày NIKE flash đủ màu sám mã N961 dành cho bé trai từ 2 đến 5 tuổi', 13, 388700.0000, 69, 39)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (75, N'Giày hạt đậu đỏ cổ cao bé trai', N'./image/76.png', 466700.0000, N'Giày hạt đậu đỏ cổ cao bé trai', N'Giày hạt đậu đỏ cổ cao bé trai dành cho bé từ 5 đến 10 tuổi', 5, 466700.0000, 62, 24)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (76, N'Dép hạt long châu thanh lịch', N'./image/77.jpg', 292500.0000, N'Dép hạt long châu thanh lịch', N'Dép hạt long châu thanh lịch dành cho bé từ 5 đến 10 tuổi', 6, 325000.0000, 62, 11)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (77, N'Áo sơ mi bút chì bé trai', N'./image/78.jpg', 336700.0000, N'Áo sơ mi bút chì bé trai', N'Áo sơ mi bút chì bé trai dành cho bé từ 3 đến 5 tuổi', 7, 336700.0000, 62, 50)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (78, N'Áo sơ mi nơ bé trai', N'./image/79.jpg', 310700.0000, N'Áo sơ mi nơ bé trai', N'Áo sơ mi nơ bé trai dành cho bé từ 3 đến 5 tuổi', 7, 310700.0000, 62, 11)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (79, N'Sưt đồ yếm bé trai lịch lãm mã YLL12', N'./image/80.jpg', 635700.0000, N'Sưt đồ yếm bé trai lịch lãm mã YLL12', N'Sưt đồ yếm bé trai lịch lãm mã YLL12 dành cho bé từ 2 đến 5 tuổi', 2, 635700.0000, 62, 35)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (80, N'Giày NIKE trắng mã NIK18', N'./image/81.jpg', 622960.0000, N'Giày NIKE trắng mã NIK18', N'Giày NIKE trắng mã NIK18 dành cho bé từ 5 đến 10 tuổi', 13, 778700.0000, 62, 24)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (81, N'Đầm công chúa hoa đỏ cam', N'./image/82.jpg', 383760.0000, N'Đầm công chúa hoa đỏ cam', N'Đầm công chúa hoa đỏ cam dành cho bé từ 3 đến 10 tuổi', 11, 479700.0000, 62, 35)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (82, N'Xe lắc', N'./image/83.jpg', 448500.0000, N'Xe lắc', N'Xe lắc dành cho bé từ 2 tuổi trở lên', 12, 448500.0000, 62, 35)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (83, N'Bò sữa nhồi bông cao 50cm', N'./image/84.jpg', 674700.0000, N'Bò sữa nhồi bông 50cm', N'Bò sữa nhồi bông 50cm', 12, 674700.0000, 66, 37)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (84, N'Hổ nhồi bông 20 cm ', N'./image/85.jpg', 414700.0000, N'Hổ nhồi bông 20cm', N'Hổ nhồi bông 20cm', 12, 414700.0000, 66, 53)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (85, N'Gầu bông We bare bear đứng thẳng 20cm', N'./image/86.jpg', 460200.0000, N'Gầu bông We bare bear đứng thẳng 20cm', N'Gầu bông We bare bear đứng thẳng 20cm', 12, 460200.0000, 66, 32)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (86, N'Quần kaki thỏ hồng', N'./image/87.jpg', 455000.0000, N'Quần kaki thỏ hồng', N'Quần kaki thỏ hồng dành cho bé từ 5 đến 10 tuổi', 10, 455000.0000, 66, 37)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (87, N'Quần legging công chúa ống loe bé gái', N'./image/88.jpg', 507000.0000, N'Quần legging công chúa ống loe bé gái', N'Quần legging công chúa ống loe bé gái dành cho bé từ 5 đến 10 tuổi', 10, 507000.0000, 53, 30)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (88, N'Quần short đen bé trai', N'./image/89.jpg', 325000.0000, N'Quần short đen bé trai', N'Quần short đen bé trai từ 5 đến 10 tuổi', 9, 325000.0000, 53, 42)
INSERT [dbo].[Product] ([pid], [name], [image], [price], [title], [description], [cateID], [oldPrice], [sumProduct], [presentProduct]) VALUES (89, N'Quần short cao cấp bé trai', N'./image/90.jpg', 518700.0000, N'Quần short cao cấp bé trai', N'Quần short cao cấp bé trai dành cho bé từ 5 đến 10 tuổi', 13, 518700.0000, 53, 30)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([uid])
REFERENCES [dbo].[Account] ([uid])
GO
ALTER TABLE [dbo].[BillProduct]  WITH CHECK ADD FOREIGN KEY([pid])
REFERENCES [dbo].[Product] ([pid])
GO
ALTER TABLE [dbo].[BillProduct]  WITH CHECK ADD  CONSTRAINT [FK_BillProduct_Bill] FOREIGN KEY([bid])
REFERENCES [dbo].[Bill] ([bid])
GO
ALTER TABLE [dbo].[BillProduct] CHECK CONSTRAINT [FK_BillProduct_Bill]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([cateID])
REFERENCES [dbo].[Category] ([cid])
GO
USE [master]
GO
ALTER DATABASE [HaLo'sShop] SET  READ_WRITE 
GO
