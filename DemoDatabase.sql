USE [master]
GO
CREATE DATABASE [StayEase]
GO
ALTER DATABASE [StayEase] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [StayEase].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [StayEase] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [StayEase] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [StayEase] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [StayEase] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [StayEase] SET ARITHABORT OFF 
GO
ALTER DATABASE [StayEase] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [StayEase] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [StayEase] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [StayEase] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [StayEase] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [StayEase] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [StayEase] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [StayEase] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [StayEase] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [StayEase] SET  ENABLE_BROKER 
GO
ALTER DATABASE [StayEase] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [StayEase] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [StayEase] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [StayEase] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [StayEase] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [StayEase] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [StayEase] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [StayEase] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [StayEase] SET  MULTI_USER 
GO
ALTER DATABASE [StayEase] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [StayEase] SET DB_CHAINING OFF 
GO
ALTER DATABASE [StayEase] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [StayEase] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [StayEase] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [StayEase] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [StayEase] SET QUERY_STORE = OFF
GO
USE [StayEase]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Additional_service](
	[add_service_id] [int] IDENTITY(0,1) NOT NULL,
	[add_serviceName] [nvarchar](max) NULL,
	[add_serviceDesc] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[add_service_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[bill_id] [int] IDENTITY(0,1) NOT NULL,
	[date] [date] NULL,
	[total] [float] NULL,
	[status] [int] NULL,
	[user_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[bill_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill_detail](
	[billdetail_id] [int] IDENTITY(0,1) NOT NULL,
	[bill_id] [int] NOT NULL,
	[house_id] [int] NOT NULL,
	[start_date] [date] NULL,
	[end_date] [date] NULL,
	[note] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[billdetail_id] ASC,
	[house_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[cid] [int] IDENTITY(0,1) NOT NULL,
	[userid] [int] NULL,
	[houseid] [int] NULL,
	[comment] [nchar](1000) NULL,
	[date] [date] NULL
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[House](
	[house_id] [int] IDENTITY(0,1) NOT NULL,
	[post_date] [date] NULL,
	[house_name] [nvarchar](max) NULL,
	[review] [nvarchar](max) NULL,
	[house_price] [float] NULL,
	[status] [int] NULL,
	[address] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[loca_id] [int] NOT NULL,
	[menu_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[house_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[House_additional_service](
	[house_add_service_id] [int] IDENTITY(0,1) NOT NULL,
	[add_service_id] [int] NOT NULL,
	[house_id] [int] NOT NULL,
	[add_service_status] [int] NULL,
	[add_service_price] [float] NULL,
 CONSTRAINT [PK_House_additional_service] PRIMARY KEY CLUSTERED 
(
	[house_add_service_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[House_img](
	[img_id] [int] IDENTITY(0,1) NOT NULL,
	[img_link] [nvarchar](MAX) NULL,
	[house_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[img_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Location](
	[loca_id] [int] IDENTITY(0,1) NOT NULL,
	[name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[loca_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Menu](
	[menu_id] [int] IDENTITY(0,1) NOT NULL,
	[name] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[menu_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[role_id] [int] IDENTITY(0,1) NOT NULL,
	[name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[user_id] [int] IDENTITY(0,1) NOT NULL,
	[fullname] [nvarchar](100) NULL,
	[avatar] [nvarchar](MAX) NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[phone] [varchar](10) NULL,
	[status] [int] NULL,
	[role_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_Users]
GO
ALTER TABLE [dbo].[Bill_detail]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Detail] FOREIGN KEY([bill_id])
REFERENCES [dbo].[Bill] ([bill_id])
GO
ALTER TABLE [dbo].[Bill_detail] CHECK CONSTRAINT [FK_Bill_Detail]
GO
ALTER TABLE [dbo].[Bill_detail]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Detail_House] FOREIGN KEY([house_id])
REFERENCES [dbo].[House] ([house_id])
GO
ALTER TABLE [dbo].[Bill_detail] CHECK CONSTRAINT [FK_Bill_Detail_House]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_House] FOREIGN KEY([houseid])
REFERENCES [dbo].[House] ([house_id])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_House]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Users] FOREIGN KEY([userid])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Users]
GO
ALTER TABLE [dbo].[House]  WITH CHECK ADD  CONSTRAINT [FK_house_menu] FOREIGN KEY([menu_id])
REFERENCES [dbo].[Menu] ([menu_id])
GO
ALTER TABLE [dbo].[House] CHECK CONSTRAINT [FK_house_menu]
GO
ALTER TABLE [dbo].[House]  WITH CHECK ADD  CONSTRAINT [FK_Location] FOREIGN KEY([loca_id])
REFERENCES [dbo].[Location] ([loca_id])
GO
ALTER TABLE [dbo].[House] CHECK CONSTRAINT [FK_Location]
GO
ALTER TABLE [dbo].[House_additional_service]  WITH CHECK ADD  CONSTRAINT [FK_House_additional_service_Additional_service1] FOREIGN KEY([add_service_id])
REFERENCES [dbo].[Additional_service] ([add_service_id])
GO
ALTER TABLE [dbo].[House_additional_service] CHECK CONSTRAINT [FK_House_additional_service_Additional_service1]
GO
ALTER TABLE [dbo].[House_additional_service]  WITH CHECK ADD  CONSTRAINT [FK_House_additional_service_House1] FOREIGN KEY([house_id])
REFERENCES [dbo].[House] ([house_id])
GO
ALTER TABLE [dbo].[House_additional_service] CHECK CONSTRAINT [FK_House_additional_service_House1]
GO
ALTER TABLE [dbo].[House_img]  WITH CHECK ADD  CONSTRAINT [FK_House_Img] FOREIGN KEY([house_id])
REFERENCES [dbo].[House] ([house_id])
GO
ALTER TABLE [dbo].[House_img] CHECK CONSTRAINT [FK_House_Img]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Role] FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([role_id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Role]
GO
USE [master]
GO
ALTER DATABASE [StayEase] SET  READ_WRITE 
GO

ALTER TABLE [dbo].[Bill_detail]
ADD [house_add_service_id] [int] NULL,
    [price] [float] NULL,
    [quantity] [int] NULL
GO

ALTER TABLE [dbo].[Bill_detail]  WITH CHECK ADD  CONSTRAINT [FK_Bill_detail_House_additional_service] FOREIGN KEY([house_add_service_id])
REFERENCES [dbo].[House_additional_service] ([house_add_service_id])
GO
ALTER TABLE [dbo].[Bill_detail] CHECK CONSTRAINT [FK_Bill_detail_House_additional_service]
GO


ALTER TABLE [dbo].[Bill_detail]
ADD [house_add_service_id] [int] NULL,
    [price] [float] NULL,
    [quantity] [int] NULL
GO

-- Add foreign key constraint to Bill_detail for house_add_service_id
ALTER TABLE [dbo].[Bill_detail]  WITH CHECK ADD  CONSTRAINT [FK_Bill_detail_House_additional_service] FOREIGN KEY([house_add_service_id])
REFERENCES [dbo].[House_additional_service] ([house_add_service_id])
GO
ALTER TABLE [dbo].[Bill_detail] CHECK CONSTRAINT [FK_Bill_detail_House_additional_service]
GO

-- Add the host_id column to the House table
ALTER TABLE [HouseBooking2].[dbo].[House]
ADD [host_id] INT;

-- Add the foreign key constraint to reference user_id from the Users table
ALTER TABLE [HouseBooking2].[dbo].[House]
ADD CONSTRAINT FK_House_Host
FOREIGN KEY ([host_id]) REFERENCES [HouseBooking2].[dbo].[Users]([user_id]);

-- Add the discount column to the House table
ALTER TABLE [HouseBooking2].[dbo].[House]
ADD [discount] DECIMAL(5, 2);
