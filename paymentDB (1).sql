-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 06:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `patient`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `pid` int(11) NOT NULL,
  `pname` varchar(30) NOT NULL,
  `ano` varchar(20) NOT NULL,
  `adate` varchar(20) NOT NULL,
  `amount` double(5,2) NOT NULL,
  `cname` varchar(20) NOT NULL,
  `cardno` varchar(20) NOT NULL,
  `expmonth` varchar(20) NOT NULL,
  `expyear` varchar(20) NOT NULL,
  `cvv` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`pid`, `pname`, `ano`, `adate`, `amount`, `cname`, `cardno`, `expmonth`, `expyear`, `cvv`) VALUES
(31, 'Dewduni', 'A020', '2020.9.7', 400.00, 'master', '12345', 'may', '2029', '678'),
(32, 'gimhani', 'A030', '2010.6.9', 400.00, 'visa', '45667', 'april', '2025', '789'),
(35, 'Chethi', 'A020', '2020.7.8', 400.00, 'paypal', '345788', 'may', '2023', '456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
