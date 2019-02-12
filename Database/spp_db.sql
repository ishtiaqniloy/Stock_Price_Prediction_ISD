-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2019 at 01:16 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `Company`
--

CREATE TABLE `Company` (
  `Trade_Code` varchar(20) NOT NULL,
  `Company Name` varchar(100) NOT NULL,
  `Authorized Capital` varchar(100) NOT NULL,
  `Paidup Capital` varchar(100) NOT NULL,
  `Outstanding Share Number` varchar(100) NOT NULL,
  `Sector` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Company Enlistment`
--

CREATE TABLE `Company Enlistment` (
  `Enlist_Id` int(20) NOT NULL,
  `Trade_Code` varchar(20) NOT NULL,
  `St_Code` varchar(20) NOT NULL,
  `Initial Price` varchar(20) NOT NULL,
  `Last Trading Price` varchar(20) NOT NULL,
  `Enlist_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Market History`
--

CREATE TABLE `Market History` (
  `St_Code` varchar(20) NOT NULL,
  `Date` date NOT NULL,
  `Closing index` float NOT NULL,
  `Total Trade` float NOT NULL,
  `Total Volume` float NOT NULL,
  `Total value` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Market Prediction`
--

CREATE TABLE `Market Prediction` (
  `St_Code` varchar(20) NOT NULL,
  `Date` date NOT NULL,
  `Future Index` float NOT NULL,
  `Future Total Trade` float NOT NULL,
  `Future Total Volume` float NOT NULL,
  `Future Total value` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Share History`
--

CREATE TABLE `Share History` (
  `Enlist_Id` int(20) NOT NULL,
  `Date` date NOT NULL,
  `Closing Price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Share Prediction`
--

CREATE TABLE `Share Prediction` (
  `Enlist_Id` int(20) NOT NULL,
  `Date` date NOT NULL,
  `Future Closing Price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Stock Exchange`
--

CREATE TABLE `Stock Exchange` (
  `St_Code` varchar(20) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Last Updated Index` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `User_ID` varchar(100) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Phone Number` varchar(30) NOT NULL,
  `Email_ID` varchar(30) NOT NULL,
  `Current Investment` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User Share`
--

CREATE TABLE `User Share` (
  `User_ID` varchar(100) NOT NULL,
  `Enlist_Id` int(20) NOT NULL,
  `Number of shares` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Company`
--
ALTER TABLE `Company`
  ADD PRIMARY KEY (`Trade_Code`);

--
-- Indexes for table `Company Enlistment`
--
ALTER TABLE `Company Enlistment`
  ADD PRIMARY KEY (`Enlist_Id`),
  ADD KEY `gets enlisted` (`Trade_Code`),
  ADD KEY `enlisted at` (`St_Code`);

--
-- Indexes for table `Market History`
--
ALTER TABLE `Market History`
  ADD PRIMARY KEY (`St_Code`,`Date`);

--
-- Indexes for table `Market Prediction`
--
ALTER TABLE `Market Prediction`
  ADD PRIMARY KEY (`St_Code`,`Date`);

--
-- Indexes for table `Share History`
--
ALTER TABLE `Share History`
  ADD PRIMARY KEY (`Enlist_Id`,`Date`);

--
-- Indexes for table `Share Prediction`
--
ALTER TABLE `Share Prediction`
  ADD PRIMARY KEY (`Enlist_Id`,`Date`);

--
-- Indexes for table `Stock Exchange`
--
ALTER TABLE `Stock Exchange`
  ADD PRIMARY KEY (`St_Code`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`User_ID`);

--
-- Indexes for table `User Share`
--
ALTER TABLE `User Share`
  ADD KEY `is acquired` (`Enlist_Id`),
  ADD KEY `buys` (`User_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Company Enlistment`
--
ALTER TABLE `Company Enlistment`
  ADD CONSTRAINT `enlisted at` FOREIGN KEY (`St_Code`) REFERENCES `Stock Exchange` (`St_Code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `gets enlisted` FOREIGN KEY (`Trade_Code`) REFERENCES `Company` (`Trade_Code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Market History`
--
ALTER TABLE `Market History`
  ADD CONSTRAINT `has 2` FOREIGN KEY (`St_Code`) REFERENCES `Stock Exchange` (`St_Code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Market Prediction`
--
ALTER TABLE `Market Prediction`
  ADD CONSTRAINT `is obtained` FOREIGN KEY (`St_Code`) REFERENCES `Stock Exchange` (`St_Code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Share History`
--
ALTER TABLE `Share History`
  ADD CONSTRAINT `has` FOREIGN KEY (`Enlist_Id`) REFERENCES `Company Enlistment` (`Enlist_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Share Prediction`
--
ALTER TABLE `Share Prediction`
  ADD CONSTRAINT `obtains` FOREIGN KEY (`Enlist_Id`) REFERENCES `Company Enlistment` (`Enlist_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `User Share`
--
ALTER TABLE `User Share`
  ADD CONSTRAINT `buys` FOREIGN KEY (`User_ID`) REFERENCES `User` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `is acquired` FOREIGN KEY (`Enlist_Id`) REFERENCES `Company Enlistment` (`Enlist_Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
