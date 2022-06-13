-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2022 at 03:42 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `original food indonesia`
--

-- --------------------------------------------------------

--
-- Table structure for table `makanan`
--

CREATE TABLE `makanan` (
  `Menu Makanan` varchar(30) NOT NULL,
  `Asal Provinsi` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `makanan`
--

INSERT INTO `makanan` (`Menu Makanan`, `Asal Provinsi`) VALUES
('Ayam Betutu', 'Bali'),
('Bika Ambon', 'Sumatra Utara'),
('Pempek ', 'Sumatera Selatan'),
('Gudeg', 'Daerah Istimewa Yogyakarta'),
('Gulai Ikan Patin', 'Jambi'),
('Kue Serabi', 'Jawa Barat'),
('Lumpia', 'Jawa Tengah'),
('Mie Aceh', 'Aceh'),
('Rendang', 'Sumatera Barat'),
('Soto Banjar', 'Kalimantan Selatan');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
