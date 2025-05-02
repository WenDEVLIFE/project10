-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2025 at 10:42 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projector_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `borrowing_table`
--

CREATE TABLE `borrowing_table` (
  `borrow_id` int(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `projector_name` varchar(255) NOT NULL,
  `year_and_course` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrowing_table`
--

INSERT INTO `borrowing_table` (`borrow_id`, `student_id`, `name`, `projector_name`, `year_and_course`) VALUES
(1, '2345-3423-23423', 'Doe', 'EBSON', '3ED YEAR bsit');

-- --------------------------------------------------------

--
-- Table structure for table `issue_projector`
--

CREATE TABLE `issue_projector` (
  `projector_id` int(255) NOT NULL,
  `projector_name` varchar(255) NOT NULL,
  `issue` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `issue_projector`
--

INSERT INTO `issue_projector` (`projector_id`, `projector_name`, `issue`) VALUES
(1, 'EPSON', 'issue'),
(2, 'EPSON', 'issue');

-- --------------------------------------------------------

--
-- Table structure for table `log_table`
--

CREATE TABLE `log_table` (
  `log_id` int(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `log_table`
--

INSERT INTO `log_table` (`log_id`, `description`) VALUES
(1, 'Projector Test added successfully.'),
(2, 'Projector EBSON borrowed by student Doe (2345-3423-23423) for course 3ED YEAR bsit'),
(3, 'Projector EBSON returned by student Doe (2345-3423-23423) for course 3rd Year BSIT'),
(4, 'Issue logged: issue'),
(5, 'Issue logged: issue');

-- --------------------------------------------------------

--
-- Table structure for table `projector_table`
--

CREATE TABLE `projector_table` (
  `projector_id` int(255) NOT NULL,
  `projector_name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `projector_table`
--

INSERT INTO `projector_table` (`projector_id`, `projector_name`, `status`) VALUES
(1, 'EBSON', 'Available'),
(2, 'Dell Projector', 'Available'),
(3, 'Test', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `request_table`
--

CREATE TABLE `request_table` (
  `request_id` int(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `return_table`
--

CREATE TABLE `return_table` (
  `return_id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  `year_and_course` varchar(255) NOT NULL,
  `projector_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `return_table`
--

INSERT INTO `return_table` (`return_id`, `name`, `student_id`, `year_and_course`, `projector_name`) VALUES
(1, 'Doe', '2345-3423-23423', '3rd Year BSIT', 'EBSON');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `user_id` int(255) NOT NULL,
  `student_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`user_id`, `student_id`) VALUES
(1, '3453543-3553-34523');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`) VALUES
(1, 'DoeUser', 'doe', 'Student'),
(2, 'Admin', 'admin', 'Admin'),
(3, 'Staff', 'staff', 'Staff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `borrowing_table`
--
ALTER TABLE `borrowing_table`
  ADD PRIMARY KEY (`borrow_id`);

--
-- Indexes for table `issue_projector`
--
ALTER TABLE `issue_projector`
  ADD PRIMARY KEY (`projector_id`);

--
-- Indexes for table `log_table`
--
ALTER TABLE `log_table`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `projector_table`
--
ALTER TABLE `projector_table`
  ADD PRIMARY KEY (`projector_id`);

--
-- Indexes for table `request_table`
--
ALTER TABLE `request_table`
  ADD PRIMARY KEY (`request_id`);

--
-- Indexes for table `return_table`
--
ALTER TABLE `return_table`
  ADD PRIMARY KEY (`return_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `borrowing_table`
--
ALTER TABLE `borrowing_table`
  MODIFY `borrow_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `issue_projector`
--
ALTER TABLE `issue_projector`
  MODIFY `projector_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `log_table`
--
ALTER TABLE `log_table`
  MODIFY `log_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `projector_table`
--
ALTER TABLE `projector_table`
  MODIFY `projector_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `request_table`
--
ALTER TABLE `request_table`
  MODIFY `request_id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `return_table`
--
ALTER TABLE `return_table`
  MODIFY `return_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
