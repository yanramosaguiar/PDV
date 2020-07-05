-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 16-Jun-2018 às 03:43
-- Versão do servidor: 5.6.35
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pdv`
--
CREATE DATABASE IF NOT EXISTS `pdv` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pdv`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--
-- Criação: 31-Maio-2018 às 22:56
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `codcli` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(35) NOT NULL,
  `bonus` int(11) NOT NULL,
  `perfil` varchar(1) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`codcli`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `cliente`:
--

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codcli`, `nome`, `bonus`, `perfil`, `status`) VALUES
(1, 'Mário', 0, 'M', 'A'),
(2, 'Maria', 120, 'P', 'A'),
(3, 'Gertrudes', 8000, 'G', 'A'),
(4, 'Gleydisson', 30, 'M', 'I'),
(5, 'Alan Knuth', 1930, 'G', 'A');

-- --------------------------------------------------------

--
-- Estrutura da tabela `desconto`
--
-- Criação: 31-Maio-2018 às 23:43
--

DROP TABLE IF EXISTS `desconto`;
CREATE TABLE IF NOT EXISTS `desconto` (
  `id_desconto` int(11) NOT NULL AUTO_INCREMENT,
  `codprod` int(11) NOT NULL,
  `percentual` int(11) NOT NULL,
  `qtd_min` int(11) NOT NULL,
  `qtd_max` int(11) NOT NULL,
  PRIMARY KEY (`id_desconto`),
  KEY `fk_codprod_desconto` (`codprod`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `desconto`:
--   `codprod`
--       `produto` -> `codprod`
--

--
-- Extraindo dados da tabela `desconto`
--

INSERT INTO `desconto` (`id_desconto`, `codprod`, `percentual`, `qtd_min`, `qtd_max`) VALUES
(2, 2, 10, 5, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `localidade`
--
-- Criação: 31-Maio-2018 às 22:56
--

DROP TABLE IF EXISTS `localidade`;
CREATE TABLE IF NOT EXISTS `localidade` (
  `codlocal` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(35) NOT NULL,
  `endereco` varchar(80) NOT NULL,
  `telefone` varchar(14) NOT NULL,
  PRIMARY KEY (`codlocal`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `localidade`:
--

--
-- Extraindo dados da tabela `localidade`
--

INSERT INTO `localidade` (`codlocal`, `nome`, `endereco`, `telefone`) VALUES
(1, 'Hell de Janeiro', 'Avenida Perdeu', '171'),
(2, 'É Paulo', 'Rua Augusta', '6969-6969'),
(3, 'Minas GerUAIs', 'Travessa do Pão de Queijo', '2524-2322');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--
-- Criação: 31-Maio-2018 às 22:56
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE IF NOT EXISTS `produto` (
  `codprod` int(11) NOT NULL AUTO_INCREMENT,
  `codlocal` int(11) NOT NULL,
  `descricao` varchar(35) NOT NULL,
  `qtd_estoque` int(11) NOT NULL,
  `preco_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codprod`),
  KEY `fk_codlocal_prod` (`codlocal`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `produto`:
--   `codlocal`
--       `localidade` -> `codlocal`
--

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codprod`, `codlocal`, `descricao`, `qtd_estoque`, `preco_unitario`) VALUES
(1, 1, '\"Orégano\"', 169, '1.71'),
(2, 1, 'Aifone 11', 32, '999.99'),
(3, 2, 'Sapato 42', 50, '89.99'),
(4, 3, 'Pinga da Boa', 8, '39.93'),
(5, 1, 'Café Extremamente Forte', 24, '15.10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--
-- Criação: 31-Maio-2018 às 22:56
--

DROP TABLE IF EXISTS `venda`;
CREATE TABLE IF NOT EXISTS `venda` (
  `codcli` int(11) NOT NULL,
  `codprod` int(11) NOT NULL,
  `codlocal` int(11) NOT NULL,
  `qtd_venda` int(11) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  `data_venda` date NOT NULL,
  PRIMARY KEY (`codcli`,`codprod`,`codlocal`),
  KEY `fk_codprod_venda` (`codprod`),
  KEY `fk_codlocal_venda` (`codlocal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `venda`:
--   `codcli`
--       `cliente` -> `codcli`
--   `codlocal`
--       `localidade` -> `codlocal`
--   `codprod`
--       `produto` -> `codprod`
--

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `desconto`
--
ALTER TABLE `desconto`
  ADD CONSTRAINT `fk_codprod_desconto` FOREIGN KEY (`codprod`) REFERENCES `produto` (`codprod`);

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `fk_codlocal_prod` FOREIGN KEY (`codlocal`) REFERENCES `localidade` (`codlocal`);

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `fk_codcli_venda` FOREIGN KEY (`codcli`) REFERENCES `cliente` (`codcli`),
  ADD CONSTRAINT `fk_codlocal_venda` FOREIGN KEY (`codlocal`) REFERENCES `localidade` (`codlocal`),
  ADD CONSTRAINT `fk_codprod_venda` FOREIGN KEY (`codprod`) REFERENCES `produto` (`codprod`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
