--
-- Database: `SNDTmgluQz`
--

CREATE DATABASE SNDTmgluQz;
USE SNDTmgluQz;
-- --------------------------------------------------------

--
-- Table structure for table `Beer`
--

CREATE TABLE `Beer` (
  `id` int(11) NOT NULL primary key auto_increment,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `alcoholContent` float NOT NULL,
  `price` float NOT NULL,
  `breweryId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Beer`
--

INSERT INTO `Beer` (`id`, `name`, `alcoholContent`, `price`, `breweryId`) VALUES
(1, 'Leffe Blonde', 6.6, 2.2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Brewery`
--

CREATE TABLE `Brewery` (
  `id` int(11) NOT NULL primary key auto_increment,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Brewery`
--

INSERT INTO `Brewery` (`id`, `name`) VALUES
(1, 'Abbaye de Leffe');

-- --------------------------------------------------------

--
-- Table structure for table `Wholesaler`
--

CREATE TABLE `Wholesaler` (
  `id` int(11) NOT NULL primary key auto_increment,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Wholesaler`
--

INSERT INTO `Wholesaler` (`id`, `name`) VALUES
(1, 'GeneDrinks');

-- --------------------------------------------------------

--
-- Table structure for table `WholesalerStock`
--

CREATE TABLE `WholesalerStock` (
  `wholesalerId` int(11) NOT NULL,
  `beerId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Beer`
--
ALTER TABLE `Beer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `breweryId` (`breweryId`);

--
-- Indexes for table `Brewery`
--
ALTER TABLE `Brewery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Wholesaler`
--
ALTER TABLE `Wholesaler`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `WholesalerStock`
--
ALTER TABLE `WholesalerStock`
  ADD PRIMARY KEY (`wholesalerId`,`beerId`),
  ADD KEY `beerId` (`beerId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Beer`
--
ALTER TABLE `Beer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `Brewery`
--
ALTER TABLE `Brewery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `Wholesaler`
--
ALTER TABLE `Wholesaler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Beer`
--
ALTER TABLE `Beer`
  ADD CONSTRAINT `Beer_ibfk_1` FOREIGN KEY (`breweryId`) REFERENCES `Brewery` (`id`);

--
-- Constraints for table `WholesalerStock`
--
ALTER TABLE `WholesalerStock`
  ADD CONSTRAINT `WholesalerStock_ibfk_1` FOREIGN KEY (`beerId`) REFERENCES `Beer` (`id`),
  ADD CONSTRAINT `WholesalerStock_ibfk_2` FOREIGN KEY (`wholesalerId`) REFERENCES `Wholesaler` (`id`);
COMMIT;
