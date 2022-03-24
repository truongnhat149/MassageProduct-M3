CREATE DATABASE IF NOT EXISTS `online_store` /*!40100 DEFAULT CHARACTER SET utf8 */;
 USE
    `online_store`;

    /*!40101
SET
    @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
    /*!40101
SET
    @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
    /*!40101
SET
    @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
    /*!40101
SET NAMES
    utf8 */;
    /*!40103
SET
    @OLD_TIME_ZONE = @@TIME_ZONE */;
    /*!40103
SET
    TIME_ZONE = '+00:00' */;
    /*!40014
SET
    @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS,
    UNIQUE_CHECKS = 0 */;
    /*!40014
SET
    @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS,
    FOREIGN_KEY_CHECKS = 0 */;
    /*!40101
SET
    @OLD_SQL_MODE = @@SQL_MODE,
    SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
    /*!40111
SET
    @OLD_SQL_NOTES = @@SQL_NOTES,
    SQL_NOTES = 0 */;
    --

    -- Table structure for table `chi_muc_gio_hang`
    --

DROP TABLE IF EXISTS
    `chi_muc_gio_hang`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `chi_muc_gio_hang`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `so_luong` INT(11) NOT NULL,
    `ma_gio_hang` BIGINT(20) DEFAULT NULL,
    `ma_san_pham` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY(`id`),
    KEY `FK49lmmclnjgb7eck20lwhv0cks`(`ma_gio_hang`),
    KEY `FKkd69a7wiulr4xgohxl0rlhth4`(`ma_san_pham`),
    CONSTRAINT `FK49lmmclnjgb7eck20lwhv0cks` FOREIGN KEY(`ma_gio_hang`) REFERENCES `gio_hang`(`id`),
    CONSTRAINT `FKkd69a7wiulr4xgohxl0rlhth4` FOREIGN KEY(`ma_san_pham`) REFERENCES `san_pham`(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `chi_muc_gio_hang`
    --

LOCK TABLES
    `chi_muc_gio_hang` WRITE;
    /*!40000
ALTER TABLE
    `chi_muc_gio_hang` DISABLE KEYS */;
INSERT INTO `chi_muc_gio_hang`
VALUES(10, 7, 1, 64),(11, 1, 1, 54),(12, 1, 1, 60),(13, 2, 1, 56),(14, 1, 1, 62),(15, 1, 2, 88),(16, 1, 2, 85);
/*!40000
ALTER TABLE
    `chi_muc_gio_hang` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `chi_tiet_don_hang`
    --

DROP TABLE IF EXISTS
    `chi_tiet_don_hang`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `chi_tiet_don_hang`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `don_gia` BIGINT(20) NOT NULL,
    `so_luong` INT(11) NOT NULL,
    `ma_don_hang` BIGINT(20) DEFAULT NULL,
    `ma_san_pham` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY(`id`),
    KEY `FK9wl3houbUK bxpixsut6uvojhy`(`ma_don_hang`),
    KEY `FK3ry84nmdxgoarx53qjxd671tk`(`ma_san_pham`),
    CONSTRAINT `FK3ry84nmdxgoarx53qjxd671tk` FOREIGN KEY(`ma_san_pham`) REFERENCES `san_pham`(`id`),
    CONSTRAINT `FK9wl3houbUK bxpixsut6uvojhy` FOREIGN KEY(`ma_don_hang`) REFERENCES `don_hang`(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `chi_tiet_don_hang`
    --

LOCK TABLES
    `chi_tiet_don_hang` WRITE;
    /*!40000
ALTER TABLE
    `chi_tiet_don_hang` DISABLE KEYS */;
INSERT INTO `chi_tiet_don_hang`
VALUES(1, 1398000000, 2, 32, 62),(2, 2599000000, 1, 32, 57),(3, 2249000000, 1, 32, 54);
/*!40000
ALTER TABLE
    `chi_tiet_don_hang` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `danh_muc`
    --

DROP TABLE IF EXISTS
    `danh_muc`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `danh_muc`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `ten_danh_muc` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `danh_muc`
    --

LOCK TABLES
    `danh_muc` WRITE;
    /*!40000
ALTER TABLE
    `danh_muc` DISABLE KEYS */;
INSERT INTO `danh_muc`
VALUES(1, 'Máy Massage'),
(2, 'Massage USUK '),
(3, 'massage toàn thân'),
(4, 'massage Liệu trình'),
(5, 'THIẾT BỊ massage'),
(6, 'massage Hongkong'),
(7, 'massage Indo'),
(8, 'massage VietNam'),
(9, 'massage Japan');
/*!40000
ALTER TABLE
    `danh_muc` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `don_hang`
    --

DROP TABLE IF EXISTS
    `don_hang`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `don_hang`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `dia_chi_nhan` VARCHAR(255) DEFAULT NULL,
    `ghi_chu` VARCHAR(255) DEFAULT NULL,
    `ho_ten_nguoi_nhan` VARCHAR(255) DEFAULT NULL,
    `ngay_dat_hang` DATETIME DEFAULT NULL,
    `ngay_giao_hang` DATETIME DEFAULT NULL,
    `ngay_nhan_hang` DATETIME DEFAULT NULL,
    `sdt_nhan_hang` VARCHAR(255) DEFAULT NULL,
    `trang_thai_don_hang` VARCHAR(255) DEFAULT NULL,
    `ma_nguoi_dat` BIGINT(20) DEFAULT NULL,
    `ma_shipper` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY(`id`),
    KEY `FKnwjiboxao1uvw1siemkvs1jb9`(`ma_nguoi_dat`),
    KEY `FKgndcrlvetoudr3jaif9b7ay37`(`ma_shipper`),
    CONSTRAINT `FKgndcrlvetoudr3jaif9b7ay37` FOREIGN KEY(`ma_shipper`) REFERENCES `nguoi_dung`(`id`),
    CONSTRAINT `FKnwjiboxao1uvw1siemkvs1jb9` FOREIGN KEY(`ma_nguoi_dat`) REFERENCES `nguoi_dung`(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 33 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `don_hang`
    --

LOCK TABLES
    `don_hang` WRITE;
    /*!40000
ALTER TABLE
    `don_hang` DISABLE KEYS */;
INSERT INTO `don_hang`
VALUES(
    31,
    'bd',
    'asdf',
    'aaa',
    '2018-12-01 14:38:26',
    NULL,
    NULL,
    'dsf',
    'Đang chờ duyệt',
    NULL,
    NULL
),(
    32,
    'fadf',
    'asdf',
    'aaa',
    '2018-12-05 21:58:24',
    NULL,
    NULL,
    '13',
    'created',
    2,
    NULL
);
/*!40000
ALTER TABLE
    `don_hang` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `gio_hang`
    --

DROP TABLE IF EXISTS
    `gio_hang`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `gio_hang`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `tong_tien` VARCHAR(255) DEFAULT NULL,
    `ma_nguoi_dung` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY(`id`),
    KEY `FKitverect56puwr47y7tyvy6er`(`ma_nguoi_dung`),
    CONSTRAINT `FKitverect56puwr47y7tyvy6er` FOREIGN KEY(`ma_nguoi_dung`) REFERENCES `nguoi_dung`(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `gio_hang`
    --

LOCK TABLES
    `gio_hang` WRITE;
    /*!40000
ALTER TABLE
    `gio_hang` DISABLE KEYS */;
INSERT INTO `gio_hang`
VALUES(1, NULL, 2),(2, NULL, 1);
/*!40000
ALTER TABLE
    `gio_hang` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `hang_san_xuat`
    --

DROP TABLE IF EXISTS
    `hang_san_xuat`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `hang_san_xuat`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `ten_hang_san_xuat` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 11 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `hang_san_xuat`
    --

LOCK TABLES
    `hang_san_xuat` WRITE;
    /*!40000
ALTER TABLE
    `hang_san_xuat` DISABLE KEYS */;
INSERT INTO `hang_san_xuat`
VALUES(2, 'Thailand'),(3, 'US'),(4, 'UK '),(5, 'JP'),(6, 'Indo'),(7, 'Malay'),(8, 'VN'),(9, 'KOREA'),(10, 'Cambodia');
/*!40000
ALTER TABLE
    `hang_san_xuat` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `lien_he`
    --

DROP TABLE IF EXISTS
    `lien_he`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `lien_he`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `email_lien_he` VARCHAR(255) DEFAULT NULL,
    `ngay_lien_he` DATETIME DEFAULT NULL,
    `ngay_tra_loi` DATETIME DEFAULT NULL,
    `noi_dung_lien_he` VARCHAR(255) DEFAULT NULL,
    `noi_dung_tra_loi` VARCHAR(255) DEFAULT NULL,
    `trang_thai` VARCHAR(255) DEFAULT NULL,
    `ma_nguoi_tra_loi` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY(`id`),
    KEY `FK6jm47uh7f94pc3wix0duvedde`(`ma_nguoi_tra_loi`),
    CONSTRAINT `FK6jm47uh7f94pc3wix0duvedde` FOREIGN KEY(`ma_nguoi_tra_loi`) REFERENCES `nguoi_dung`(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `lien_he`
    --

LOCK TABLES
    `lien_he` WRITE;
    /*!40000
ALTER TABLE
    `lien_he` DISABLE KEYS */;
    /*!40000
ALTER TABLE
    `lien_he` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `nguoi_dung`
    --

DROP TABLE IF EXISTS
    `nguoi_dung`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `nguoi_dung`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `dia_chi` VARCHAR(255) DEFAULT NULL,
    `email` VARCHAR(255) DEFAULT NULL,
    `ho_ten` VARCHAR(255) DEFAULT NULL,
    `password` VARCHAR(255) DEFAULT NULL,
    `so_dien_thoai` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `nguoi_dung`
    --

LOCK TABLES
    `nguoi_dung` WRITE;
    /*!40000
ALTER TABLE
    `nguoi_dung` DISABLE KEYS */;
INSERT INTO `nguoi_dung`
VALUES(
    1,
    NULL,
    'admin@gmail.com',
    'LPNT',
    '$2a$10$/VFMNUPBKNVRMjxPFCYKZ.lKahoLQda0EaAxdqoun1w3DqwNLa2me',
    '123456789'
),(
    2,
    NULL,
    'member@gmail.com',
    NULL,
    '$2a$10$j7Upgupou72GBmUK z0G6pOATk3wlCAgaoFCEqAhSvLToD/V/1wlpu',
    NULL
)
-- ,(
--     3,
--     NULL,
--     'shipper@gmail.com',
--     NULL,
--     '$2a$10$u2B29HDxuWVYY3fUJ8R2qunNzXngfxij5GpvlFAEtIz3JpK/WFXF2',
--     NULL
-- )
;
/*!40000
ALTER TABLE
    `nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `nguoidung_vaitro`
    --

DROP TABLE IF EXISTS
    `nguoidung_vaitro`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `nguoidung_vaitro`(
    `ma_nguoi_dung` BIGINT(20) NOT NULL,
    `ma_vai_tro` BIGINT(20) NOT NULL,
    PRIMARY KEY(`ma_nguoi_dung`, `ma_vai_tro`),
    KEY `FKig6jxd861mqv02a8pn68r43fr`(`ma_vai_tro`),
    CONSTRAINT `FKig6jxd861mqv02a8pn68r43fr` FOREIGN KEY(`ma_vai_tro`) REFERENCES `vai_tro`(`id`),
    CONSTRAINT `FKocavcnspu1wcvp2w0s4usfgbf` FOREIGN KEY(`ma_nguoi_dung`) REFERENCES `nguoi_dung`(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `nguoidung_vaitro`
    --

LOCK TABLES
    `nguoidung_vaitro` WRITE;
    /*!40000
ALTER TABLE
    `nguoidung_vaitro` DISABLE KEYS */;
INSERT INTO `nguoidung_vaitro`
VALUES(1, 1),(1, 2),(2, 2),(4, 2),(3, 3);
/*!40000
ALTER TABLE
    `nguoidung_vaitro` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `san_pham`
    --

DROP TABLE IF EXISTS
    `san_pham`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `san_pham`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `don_gia` BIGINT(20) NOT NULL,
    `don_vi_ban` INT(11) NOT NULL,
    `don_vi_kho` INT(11) NOT NULL,
    `ten_san_pham` VARCHAR(255) DEFAULT NULL,
    `thiet_ke` VARCHAR(255) DEFAULT NULL,
    `thong_tin_bao_hanh` VARCHAR(255) DEFAULT NULL,
    `thong_tin_chung` VARCHAR(255) DEFAULT NULL,
    `ma_danh_muc` BIGINT(20) DEFAULT NULL,
    `ma_hang_sx` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY(`id`),
    KEY `FKqss6n6gtx6lhb7flcka9un18t`(`ma_danh_muc`),
    KEY `FKchvjvgjnq8lbt9mjtyfn5pksq`(`ma_hang_sx`),
    CONSTRAINT `FKchvjvgjnq8lbt9mjtyfn5pksq` FOREIGN KEY(`ma_hang_sx`) REFERENCES `hang_san_xuat`(`id`),
    CONSTRAINT `FKqss6n6gtx6lhb7flcka9un18t` FOREIGN KEY(`ma_danh_muc`) REFERENCES `danh_muc`(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 144 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `san_pham`
    --

LOCK TABLES
    `san_pham` WRITE;
    /*!40000
ALTER TABLE
    `san_pham` DISABLE KEYS */;
INSERT INTO `san_pham`
VALUES(
    3,

    2399000000,
    0,
    100,

    'Máy Massage Thailand',
    '',
    '12 tháng',
    '',
    1,
    2
),(
    4,

    2899000000,
    0,
    100,

    'Máy Massage ',
    '',
    '12 tháng',
    '',
    1,
    2
),(
    5,

    3399000000,
    0,
    150,

    'Máy Massage Thailand',
	null,
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    2
),(
    6,

    3399000000,
    0,
    200,

    ' Máy Massage Thailand ',
    '',
    '12 tháng',
    '',
    1,
    2
),(
    7,

    4499000000,
    0,
    100,

    ' Máy Massage Thailand ',
    '',
    '12 tháng',
    '',
    1,
    2
),(
    8,

    4999000000,
    0,
    150,

    ' Máy Massage Thailand ',
    '',
    '12 tháng',
    '',
    1,
    2
),(
    9,

    5999000000,
    0,
    200,

    ' Máy Massage Thailand ',
    '',
    '12 tháng',
    '',
    1,
    2
),(
    10,

    6999000000,
    0,
    120,

    'Máy Massage Thailand',
    '',
    '12 tháng',
    '',
    1,
    2
),(
    11,

    549000000,
    0,
    100,

    'US E402NA-GA034T',
    '',
    '12 tháng',
    '',
    1,
    3
),(
    12,

    549000000,
    0,
    200,

    'US E406SA-BV001T',
    '',
    '12 tháng',
    '',
    1,
    3
),(
    13,

    549000000,
    0,
    200,

    'US E406SA-BV043T',
    '',
    '12 tháng',
    '',
    1,
    3
),(
    14,

    669000000,
    0,
    120,

    'US X507MA-BR208T N4000',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    3
),(
    15,

    729000000,
    0,
    110,

    'US X541NA-GO012T N4200',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    3
),(
    16,

    899000000,
    0,
    130,

    'US X541UA-XX272T 6100U',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    3
),(
    17,

    899000000,
    0,
    200,

    'US X541UA-GO1345',
    '',
    '12 tháng',
    'tinh tế đến từng chi tiết',
    1,
    3
),(
    18,

    1049000000,
    0,
    300,

    'US X541UJ-DM544T',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    3
),(
    19,

    599000000,
    0,
    300,

    'UK AS A315-31-C8GB',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    20,

    60900000000,
    0,
    100,

    'UK A315-32-C9A4 ',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    21,

    89900000000,
    0,
    100,

    'UK E5-476-33BQ',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    22,

    899000000,
    0,
    50,

    'UK A315-51-3932',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    23,

    939000000,
    0,
    300,

    'UK AS A315-51-325E',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    24,

    999000000,
    0,
    130,

    'UK A315-51-364W',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    25,

    1069000000,
    0,
    120,

    'UK E5-575G-39QW',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    26,

    1699000000,
    0,
    130,

    'UK SF314',
    '',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    4
),(
    27,

    699000000,
    0,
    110,

    'Japan  N3552',
    'Thiết kế gọn gàng để di chuyển',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    5
),(
    28,

    999000000,
    0,
    100,

    'Japan N3567C',
    'Thiết kế gọn gàng để di chuyển',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    5
),(
    29,

    999000000,
    0,
    100,

    'Japan   N3467',
    'Thiết kế gọn gàng để di chuyển',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    5
),(
    30,

    1089000000,
    0,
    120,

    'Japan 3568',
    'Thiết kế gọn gàng để di chuyển',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    5
),(
    31,

    1119000000,
    0,
    90,
    'Japan N3567',
    'Thiết kế gọn gàng để di chuyển',
    '12 tháng',
    'Thiết kế hoàn mỹ tinh tế và sang trọng',
    1,
    5
);
/*!40000
ALTER TABLE
    `san_pham` ENABLE KEYS */;
UNLOCK TABLES
    ;
    --

    -- Table structure for table `vai_tro`
    --

DROP TABLE IF EXISTS
    `vai_tro`;
    /*!40101
SET
    @saved_cs_client = @@character_set_client */;
    /*!40101
SET
    character_set_client = utf8 */;
CREATE TABLE `vai_tro`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `ten_vai_tro` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8;
/*!40101 SET
    character_set_client = @saved_cs_client */;
    --

    -- Dumping data for table `vai_tro`
    --

LOCK TABLES
    `vai_tro` WRITE;
    /*!40000
ALTER TABLE
    `vai_tro` DISABLE KEYS */;
INSERT INTO `vai_tro`
VALUES(1, 'ROLE_ADMIN'),(2, 'ROLE_MEMBER')
-- ,(3, 'ROLE_SHIPPER')
;
/*!40000
ALTER TABLE
    `vai_tro` ENABLE KEYS */;
UNLOCK TABLES
    ;
    /*!40103
SET
    TIME_ZONE = @OLD_TIME_ZONE */;
    /*!40101
SET
    SQL_MODE = @OLD_SQL_MODE */;
    /*!40014
SET
    FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
    /*!40014
SET
    UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
    /*!40101
SET
    CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
    /*!40101
SET
    CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
    /*!40101
SET
    COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
    /*!40111
SET
    SQL_NOTES = @OLD_SQL_NOTES */;
  
    -- fjx bug số lượng
    ALTER TABLE `chi_tiet_don_hang` 
CHANGE `so_luong` `soluong` INT(11) NULL DEFAULT NULL;

-- fjx email
ALTER TABLE nguoi_dung
ADD UNIQUE (email);