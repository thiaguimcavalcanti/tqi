Teste para Avaliação Técnica - Desenvolvimento Java Web
======

## Problema proposto:
Uma grande empresa de notícias lançará uma campanha para a escolha do nome do mascote para representar a copa do mundo no Brasil. Você foi contratado para desenvolver um aplicativo web responsável por receber e contabilizar os votos dos internautas.

## Links:

+ [http://tqitest.elasticbeanstalk.com/] (http://tqitest.elasticbeanstalk.com/)

## Tecnologias utilizadas:

- Java 6
- Hibernate 4.1.7
- Spring 3.1.2
- JSF 2.1.13
- Primefaces 3.4
- Banco de dados Mysql
- Junit 4.11
- Design Patterns
- Spring MVC
- Annotations
- Servidor de aplicação Tomcat 7
- Maven

## Configurações

* Crie o banco de dados da aplicação:
 
```
mysql> CREATE DATABASE `tqi`;
```

* Crie o banco de dados de teste - Utilizado pelo JUnit:

```
mysql> CREATE DATABASE `tqi_test`;
```

* Rode o servidor (as tabelas do banco de dados da aplicação serão geradas).

* Popule os mascotes na tabela Mascot:

```
mysql> INSERT INTO `Mascot` VALUES 
(1,'União das palavras amizade e jubilo, que está ligado ao tupi-guarani, em que jubi significa amarelo, cor predominante no mascote.','Amijubi'),
(2,'Uma mistura de futebol e ecologia. O nome busca incentivar o cuidado das pessoas com o meio ambiente.','Fuleco'),
(3,'Mistura da cor azul com ecologia, que busca também incentivar cuidados relacionados à ecologia.','Zuzeco');
```

* Cadastre as configurações da aplicação na tabela AppConfiguration. A coluna ReleaseDateVotePage se refere à data de liberação da tela de resultados:

```
mysql> INSERT INTO `AppConfiguration` VALUES (1,'2013-03-10 00:00:00','2013-03-10 00:00:00','TQI','2013-03-11 00:00:00');
```

* A aplicação está pronta para ser utilizada.
  - Página de votação: http://tqitest.elasticbeanstalk.com/index.xhtml ou [http://localhost:8080/tqi/index.xhtml] (http://localhost:8080/tqi/index.xhtml)
  - Página de resultados: http://tqitest.elasticbeanstalk.com/results.xhtml ou [http://localhost:8080/tqi/results.xhtml] (http://localhost:8080/tqi/results.xhtml)


## Autor

Thiago Cavalcanti Reis

+ [http://br.linkedin.com/in/thiagocavalcantireis/] (http://br.linkedin.com/in/thiagocavalcantireis/)