-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2021 at 10:35 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `investigators`
--

-- --------------------------------------------------------

--
-- Table structure for table `investigatorss`
--

CREATE TABLE `investigatorss` (
  `InvestID` int(11) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `ContactNumber` varchar(10) NOT NULL,
  `Location` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `investigatorss`
--

INSERT INTO `investigatorss` (`InvestID`, `FirstName`, `LastName`, `Email`, `ContactNumber`, `Location`) VALUES
(2, 'yamitha', 'uluwita', 'yamithu@gmail.com', '0115689745', 'Matale'),
(3, 'yamitha', 'ayantha', 'ish@gmail.com', '0715698756', 'Matale');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `investigatorss`
--
ALTER TABLE `investigatorss`
  ADD PRIMARY KEY (`InvestID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `investigatorss`
--
ALTER TABLE `investigatorss`
  MODIFY `InvestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
