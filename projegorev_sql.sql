-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Anamakine: localhost
-- Üretim Zamanı: 11 Temmuz 2014 saat 18:35:34
-- Sunucu sürümü: 5.0.51
-- PHP Sürümü: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Veritabanı: `projegorev5`
-- 

-- --------------------------------------------------------

-- 
-- Tablo yapısı: `tbl_icerik`
-- 

CREATE TABLE `tbl_icerik` (
  `id` int(11) NOT NULL auto_increment,
  `baslik` varchar(225) NOT NULL,
  `detay` text NOT NULL,
  `resim` varchar(225) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- 
-- Tablo döküm verisi `tbl_icerik`
-- 

INSERT INTO `tbl_icerik` VALUES (2, 'İçerik', 'Deneme', '');

-- --------------------------------------------------------

-- 
-- Tablo yapısı: `tbl_kullanici`
-- 

CREATE TABLE `tbl_kullanici` (
  `id` int(11) NOT NULL auto_increment,
  `adi` varchar(225) NOT NULL,
  `soyadi` varchar(225) NOT NULL,
  `email` varchar(225) NOT NULL,
  `sifre` varchar(225) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

-- 
-- Tablo döküm verisi `tbl_kullanici`
-- 

INSERT INTO `tbl_kullanici` VALUES (5, 'Ender', 'Can', 'endercan34@gmail.com', '');
INSERT INTO `tbl_kullanici` VALUES (3, 'Emre', 'Can', 'emrecan@gmail.com', '12345');
INSERT INTO `tbl_kullanici` VALUES (4, 'Yunus', 'Can', 'yunuscan@hotmail.com', '12345');
INSERT INTO `tbl_kullanici` VALUES (6, 'Kadir', 'Can', 'kadircn@gmail.com', '12345');
