--CREATE TABLE `tb_user` (
  --`id` bigint(20) NOT NULL AUTO_INCREMENT,
  --`authorize_data` bit(1) DEFAULT NULL,
  --`birth` date DEFAULT NULL,
  --`dt_cadastro` date DEFAULT NULL,
  --`email` varchar(50) DEFAULT NULL,
  --`experimental_groups` int(11) DEFAULT NULL,
  --`gender` char(1) DEFAULT NULL,
  --`name` varchar(100) DEFAULT NULL,
  --`password` tinyblob,
  --`pesquisa_enviada` bit(1) DEFAULT NULL,
  --`phone` varchar(255) DEFAULT NULL,
  --`prefered_language` varchar(255) DEFAULT NULL,
  --`receive_emails` bit(1) DEFAULT NULL,
  --`recover_code` int(11) DEFAULT NULL,
  --`token` varchar(100) DEFAULT NULL,
  --PRIMARY KEY (`id`),
  --UNIQUE KEY `email` (`email`)
--) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 |


ALTER TABLE tb_user
ADD COLUMN token VARCHAR(100);

--DESAFIOS
DROP TABLE IF EXISTS `tb_challenge`;
CREATE TABLE `tb_challenge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `date_creation` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `tb_challenge_user`;
CREATE TABLE `tb_challenge_user` (
  `id_challenge` int(11) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `date` DATE DEFAULT NULL
);

ALTER TABLE tb_challenge_user ADD CONSTRAINT fk_challenge_user_challenge FOREIGN KEY(id_challenge) REFERENCES tb_challenge(id);
ALTER TABLE tb_challenge_user ADD CONSTRAINT fk_challenge_user_user FOREIGN KEY(id_user) REFERENCES tb_user(id);
ALTER TABLE tb_challenge_user ADD CONSTRAINT pk_challenge_user PRIMARY KEY CLUSTERED (id_challenge, id_user);

--DICAS
DROP TABLE IF EXISTS `tb_tip`;
CREATE TABLE `tb_tip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `date_creation` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_tip_user` (
  `id_tip` int(11) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `like_tip` boolean NOT NULL,
  `date` DATE DEFAULT NULL
);

ALTER TABLE tb_tip_user ADD CONSTRAINT fk_tip_user_tip FOREIGN KEY(id_tip) REFERENCES tb_tip(id);
ALTER TABLE tb_tip_user ADD CONSTRAINT fk_tip_user_user FOREIGN KEY(id_user) REFERENCES tb_user(id);
ALTER TABLE tb_tip_user ADD CONSTRAINT pk_tip_user PRIMARY KEY CLUSTERED (id_tip, id_user);


CREATE TABLE `tb_tip_user_recommendation` (
  `id_tip` int(11) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `date` DATE DEFAULT NULL
);

ALTER TABLE tb_tip_user_recommendation ADD CONSTRAINT fk_tip_user_recommendation_tip FOREIGN KEY(id_tip) REFERENCES tb_tip(id);
ALTER TABLE tb_tip_user_recommendation ADD CONSTRAINT fk_tip_user_recommendation_user FOREIGN KEY(id_user) REFERENCES tb_user(id);
ALTER TABLE tb_tip_user_recommendation ADD CONSTRAINT pk_tip_user_recommendation PRIMARY KEY CLUSTERED (id_tip, id_user);


--CIGARROS
CREATE TABLE `tb_cigarette` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) NOT NULL ,
  `pack_cigarettes_price` DOUBLE DEFAULT NULL,
  `economized` DOUBLE DEFAULT NULL,
  `spent` DOUBLE DEFAULT NULL,
  `num_cigarette` int(11) DEFAULT NULL,
  `date_creation` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE tb_cigarette ADD CONSTRAINT fk_tip_cigarette_user FOREIGN KEY(id_user) REFERENCES tb_user(id);


--DICAS
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 1', 'A fissura dura em média de 2 a 5 minutos. Nesses momentos beba um copo de água ou escove os dentes.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 2', 'Escovar os dentes após comer ou beber algo que você associa ao cigarro pode diminuir sua vontade de fumar.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 3', 'Quando sentir vontade de acender um cigarro coma alimentos de baixo de teor calórico como por exemplo, frutas, cenouras, salsão, pepino, etc.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 4', 'Durante o processo de cessação é comum ocorrer episódios de recaída, mas não desanime, continue firme no seu propósito de parar de fumar.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 5', 'Comer frutas ou alimentos de baixas calorias ajudam nos momentos de fissura e a não ganhar peso.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 6', 'Parar de fumar aumenta a expectativa e a qualidade de vida.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 7', 'Tenha sempre perto de você garrafas de água e quando tiver vontade de fumar tome alguns goles da água.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 8', 'Ao parar de fumar você se sentirá melhor fisicamente e terá mais disposição para realizar exercícios físicos.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 9', 'Ao deixar de fumar você reduz as chances de desenvolver doenças do coração, doenças pulmonares e cânceres.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 10', 'Se além de você existe outro fumante em casa, peça para que ele evite fumar perto de você e proponha a essa pessoa uma tentativa de parar. Um poderá dar suporte ao outro.' , 5, NOW());
INSERT tb_tip (title, description, value, date_creation) VALUES ('Dica 11', 'Guarde o dinheiro que você gasta com cigarros e ao final de um ano você poderá investir algo prazeroso, como uma viagem, por exemplo.' , 5, NOW());
