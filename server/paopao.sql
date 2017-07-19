-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-11-30 15:55:10
-- 服务器版本： 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `paopao`
--

-- --------------------------------------------------------

--
-- 表的结构 `account_tbl`
--

CREATE TABLE IF NOT EXISTS `account_tbl` (
  `id` int(11) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `playerUid` bigint(20) DEFAULT NULL,
  `debug` varchar(64) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `account_tbl`
--

INSERT INTO `account_tbl` (`id`, `uid`, `username`, `password`, `playerUid`, `debug`) VALUES
(1, 10000, 'administrator', '1234567890', 7781966333932666880, '调试'),
(2, 10001, 'a', 'a', 4605983393282457600, '[Account:a]'),
(3, 10002, 'b', 'b', 3269937677350469632, '[Account:b]'),
(4, 10003, 'c', 'c', 3745979501313458177, NULL),
(5, 10088, 'x', 'x', NULL, 'auto-create'),
(6, 4314385956717723648, 'asdf', 'asdf', 2440797894417907713, 'auto-create');

-- --------------------------------------------------------

--
-- 表的结构 `player_tbl`
--

CREATE TABLE IF NOT EXISTS `player_tbl` (
  `id` int(11) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `debug` varchar(32) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `player_tbl`
--

INSERT INTO `player_tbl` (`id`, `uid`, `name`, `level`, `debug`) VALUES
(1, 2223439567090089984, '海绵打宝宝', 0, NULL),
(2, 6906096328031469569, '海绵打宝宝2', 0, NULL),
(3, 7903771355579875328, '海绵打宝宝', 0, NULL),
(4, 4287214340157997056, '海绵打宝宝3', 0, NULL),
(5, 7759173622035644417, '海绵打宝宝2', 0, NULL),
(6, 7781966333932666880, '海宝', 15, NULL),
(7, 4605983393282457600, 'iamA', 16, NULL),
(9, 3269937677350469632, '2B', 0, 'v0'),
(10, 3745979501313458177, '3C', 0, 'v0'),
(11, 2440797894417907713, 'asdf', 0, 'v0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account_tbl`
--
ALTER TABLE `account_tbl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `player_tbl`
--
ALTER TABLE `player_tbl`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account_tbl`
--
ALTER TABLE `account_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `player_tbl`
--
ALTER TABLE `player_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
