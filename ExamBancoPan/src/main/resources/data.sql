DROP TABLE IF EXISTS cliente;
 
CREATE TABLE cliente (
  cpf VARCHAR(11) NOT NULL PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  cep VARCHAR(8) NOT NULL,
  logradouro VARCHAR(1000) NOT NULL,
  numero INT NOT NULL,
  complemento VARCHAR(1000) NOT NULL,
  bairro VARCHAR(500) NOT NULL,
  municipio VARCHAR(500) NOT NULL,
  estado VARCHAR(2) NOT NULL
);
 
INSERT INTO cliente (cpf, nome, cep, logradouro, numero, complemento, bairro, municipio, estado) VALUES
  ('21250868831', 'JEAN MEIRA', '04349000', 'RUA DAS GRUMIXAMAS', 327, 'AP 103', 'JABAQUARA', 'SAO PAULO', 'SP');
;