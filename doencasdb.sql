create database doencas;

use doencas;

CREATE TABLE `tbDoencas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `sintomas` text NOT NULL,
  `prevencao` text NOT NULL,
  PRIMARY KEY(id));


CREATE TABLE `tbLogin`(
 `idLogin` int(11) NOT NULL AUTO_INCREMENT,
 `nomeLogin` varchar(100) NOT NULL,
 `email` varchar(50) NOT NULL,
 `senha` varchar(12) NOT NULL,
 PRIMARY KEY(idLogin));



INSERT INTO `tbDoencas` (`nome`, `sintomas`, `prevencao`) VALUES
('AIDS', 'Os sintomas da AIDS incluem perda de peso, febre ou sudorese noturna, fadiga e infecções recorrentes.', 'prevençâo  é o uso do preservativo'),
('COVID-19', 'dores, congestão nasal, dor de cabeça, conjuntivite, dor de garganta, diarreia, perda de paladar ou olfato, erupção cutânea na pele ou descoloração dos dedos das mãos ou dos pés.', 'Lave com frequência as mãos até a altura dos punhos, com água e sabão, ou então higienize com álcool em gel 70%.
Ao tossir ou espirrar, cubra nariz e boca com lenço ou com o braço, e não com as mãos.
Evite tocar olhos, nariz e boca com as mãos não lavadas.
Ao tocar, lave sempre as mãos como já indicado.
Mantenha uma distância mínima de cerca de 2 metros de qualquer pessoa tossindo ou espirrando.');



