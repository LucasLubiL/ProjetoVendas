CREATE DATABASE vendas_empresa;
USE vendas_empresa;

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- TABELAS --

/*Tabela criada para registrar as informações dos clientes*/
CREATE TABLE cliente(

    id_cliente int auto_increment not null PRIMARY KEY,
    nome_cliente varchar(100) not null,
    cpf varchar (14) not null,
    endereco varchar(100) not null,
    telefone varchar(15)

);

/*Tabela criada para o registro de informações de produtos e a quantidade daquele produto*/
CREATE TABLE produto(
    
    id_prod int auto_increment not null PRIMARY KEY,
    nome varchar(50) not null,
    preco double not null,
    quant int not null,
    marca varchar(50),
    categoria varchar(50) not null,
    tam_camisa varchar(3),
    tam_calca varchar(3),
    tam_calcado int,
    usado boolean default false

);

/*Tabela criada para registrar os diferentes tipos de pagamento disponíveis*/
CREATE TABLE pagamento(
    
    id_pag int auto_increment not null PRIMARY KEY,
    nome_tipo varchar(50) not null,
    usado boolean default false

);

/*Tabela criada para fazer os registros das vendas realizadas*/
CREATE TABLE venda(

    id_venda int auto_increment not null PRIMARY KEY,
    id_cliente int default 000000,
    quant_prod int not null,
    valor double not null,
    id_pag int not null,
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Emitido', 'Cancelado') DEFAULT 'Emitido',
    FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY(id_pag) REFERENCES pagamento(id_pag)
    
);

/*Tabela criada para guardar de forma individual os produtos presentes nas vendas*/
CREATE TABLE venda_itens(
    
    id_venda_item int auto_increment not null PRIMARY KEY,
    id_venda int not null,
    id_prod int not null,
    preco double not null,
    preco_total double not null,
    quant_itens int not null,
    FOREIGN KEY(id_venda) REFERENCES venda(id_venda),
    FOREIGN KEY(id_prod) REFERENCES produto(id_prod)
    
);

/*Tabela criada para o registro de informações de funcionários*/
CREATE TABLE funcionario(

    id_func int auto_increment not null PRIMARY KEY,
    nome_func varchar(100) not null,
    cpf varchar (14) not null,
    endereco varchar(100) not null,
    salario double not null,
    cargo varchar(100) not null,
    data_nasc date,
    telefone varchar(15)

);

/*Tabela criada para guardar as informações relacionadas as operações realizadas dentro do banco de dados, adição, exclusão ou modificação de informações*/
CREATE TABLE registro_log(
     
    id_log int auto_increment not null PRIMARY KEY,
    tabela_alt varchar(20) not null,
    acao varchar(100) not null,
    data_alt TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- TRIGGERS INSERT --

/*Trigger responsável por atualizar a tabela 'registro_log' na inserção dentro da tabela 'venda_itens' e que muda a variavel 'usada' do produto 
que foi incluido em uma venda para assim, saber se o produto ja foi usado em alguma venda e impossibilitar a exclusão das informações dele*/
DELIMITER //
CREATE TRIGGER log_insert_venda_itens
AFTER INSERT ON venda_itens
   FOR EACH ROW
   BEGIN
     update produto set usado = true
     where new.id_prod = produto.id_prod;
     insert into registro_log (tabela_alt, acao)
     values ('venda_itens', 'inserção de item');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na inserção dentro da tabela 'venda' e que muda a variavel 'usada' do pagamento 
que foi usado em uma venda para assim, saber se o tipo de pagamento ja foi usado em alguma venda e impossibilitar a exclusão das informações dele*/
DELIMITER //
CREATE TRIGGER log_insert_venda
AFTER INSERT ON venda
   FOR EACH ROW
   BEGIN
     update pagamento set usado = true
     where new.id_pag = pagamento.id_pag;
     insert into registro_log (tabela_alt, acao)
     values ('venda', 'inserção de venda');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na inserção dentro da tabela 'produto'*/
DELIMITER //
CREATE TRIGGER log_insert_produto
AFTER INSERT ON produto
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('produto', 'inserção de produto');
   END //
DELIMITER ;

/* Trigger responsável por atualizar a tabela 'registro_log' na inserção dentro da tabela 'funcionario'*/
DELIMITER //
CREATE TRIGGER log_insert_funcionario
AFTER INSERT ON funcionario
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('funcionario', 'inserção de funcionario');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na inserção dentro da tabela 'pagamento'*/
DELIMITER //
CREATE TRIGGER log_insert_pagamento
AFTER INSERT ON pagamento
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('pagamento', 'inserção de forma de pagamento');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na inserção dentro da tabela 'cliente'*/
DELIMITER //
CREATE TRIGGER log_insert_cliente
AFTER INSERT ON cliente
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('cliente', 'inserção de cliente');
   END //
DELIMITER ;

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- TRIGGERS UPDATE --

/*Trigger responsável por atualizar a tabela 'registro_log' na mudança de informações dentro da tabela 'funcionario'*/
DELIMITER //
CREATE TRIGGER log_update_funcionario
AFTER UPDATE ON funcionario
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('funcionario', 'alteração em funcionario');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na mudança de informações dentro da tabela 'cliente'*/
DELIMITER //
CREATE TRIGGER log_update_cliente
AFTER UPDATE ON cliente
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('cliente', 'alteração em cliente');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na mudança de informações dentro da tabela 'pagamento'*/
DELIMITER //
CREATE TRIGGER log_update_pagamento
AFTER UPDATE ON pagamento
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('pagamento', 'alteração em pagamento');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na mudança de informações dentro da tabela 'produto'*/
DELIMITER //
CREATE TRIGGER log_update_produto
AFTER UPDATE ON produto
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('produto', 'alteração em produto');
   END //
DELIMITER ;

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- TRIGGER DELETE --

/*Trigger responsável por atualizar a tabela 'registro_log' na exclusão de informações dentro da tabela 'funcionario'*/
DELIMITER //
CREATE TRIGGER log_delete_funcionario
AFTER DELETE ON funcionario
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('funcionario', 'delete de funcionario');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na exclusão de informações dentro da tabela 'produto'*/
DELIMITER //
CREATE TRIGGER log_delete_produto
AFTER DELETE ON produto
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('produto', 'delete de produto');
   END //
DELIMITER ;

/*Trigger responsável por atualizar a tabela 'registro_log' na exclusão de informações dentro da tabela 'pagamento'*/
DELIMITER //
CREATE TRIGGER log_delete_pagamento
AFTER DELETE ON pagamento
   FOR EACH ROW
   BEGIN
     insert into registro_log (tabela_alt, acao)
     values ('pagamento', 'delete de pagamento');
   END //
DELIMITER ;

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- TRIGGER/PROCEDURE ATT --

/*Trigger responsável por atualizar o estoque na realização de uma venda*/
DELIMITER //
CREATE TRIGGER att_venda
AFTER INSERT ON venda_itens
   FOR EACH ROW
   BEGIN
     update produto
        set quant = quant - new.quant_itens
        where produto.id_prod = new.id_prod;
   END //
DELIMITER ;

/*Procedure responsável por atualizar o estoque em produtos ja registrados, com a inserção de quantidade de produtos a se adicionar como uma variavel de entrada*/
DELIMITER //
CREATE PROCEDURE att_estoque(in id_produto int,in quant_p int)
    BEGIN 
       update produto
          set produto.quant = produto.quant + quant_p
          where produto.id_prod = id_produto;
    END // 
DELIMITER ;

/*Procedure responsável por realizar a exclusão de informaçoes de um produto com base no id que foi dado como entrada
mas que checa se aquele produto ja foi vendido alguma vez para só permitir a exclusão se o produto nunca foi usado*/
DELIMITER //
CREATE PROCEDURE delete_in_ID_produto(in id_produto int)
    BEGIN 
	declare seUsou boolean;
	if exists (select 1 from produto where produto.id_prod = id_produto) then
		
	   select produto.usada into seUsou 
	   from produto 
	   where id_produto = produto.id_prod;

	   if seUsou is false then
	      delete  from produto where produto.id_prod = id_produto;
	   end if;

       end if;
    END // 
DELIMITER ;

/*Procedure responsável por realizar a exclusão de informaçoes de um pagamento com base no id que foi dado como entrada
mas que checa se aquele pagamento ja foi usado em alguma venda para só permitir a exclusão se ele nunca foi usado*/
DELIMITER //
CREATE PROCEDURE delete_in_ID_pagamento(in id_pagamento int)
    BEGIN 
	declare seUsou boolean;
	if exists (select 1 from pagamento where pagamento.id_pag = id_pagamento) then
        
	     select pagamento.usada into seUsou 
	     from pagamento
	     where id_pagamento = pagamento.id_pag;
            
	     if seUsou is false then
		 delete  from pagamento where pagamento.id_pag = id_pagamento;
	     end if;
            
       end if;
    END // 
DELIMITER ;

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- RELATÓRIOS --

/*Relatório de vendas que exibe informações das vendas com base em datas de entrada, e assim exibe as vendas que
foram realizadas no intervalo entre as datas, mas apenas das vendas com status 'Emitido'*/
DELIMITER //
CREATE PROCEDURE relatorio_vendas(in data_v date, in data_v2 date)
    BEGIN 
       select v.id_venda, c.nome_cliente, v.quant_prod, v.valor, p.nome_tipo from venda v
       JOIN cliente c ON c.id_cliente = v.id_cliente
       JOIN pagamento p ON p.id_pag = v.id_pag
       where (data_venda BETWEEN data_v AND data_v2) AND v.status like 'Emitido';
    END // 
DELIMITER ;

/*Relatório de vendas que exibe informações das vendas com base em datas de entrada,e assim exibe as vendas
que foram realizadas no intervalo entre as datas, mas apenas das vendas com status 'Cancelado'*/
DELIMITER //
CREATE PROCEDURE relatorio_vendas_canceladas(in data_v date, in data_v2 date)
    BEGIN 
       select v.id_venda, c.nome_cliente, v.quant_prod, v.valor, p.nome_tipo from venda v
       JOIN cliente c ON c.id_cliente = v.id_cliente
       JOIN pagamento p ON p.id_pag = v.id_pag
       where (data_venda BETWEEN data_v AND data_v2) AND v.status like 'Cancelado';
    END // 
DELIMITER ;

/*View para criar relatorio que mostra todas as informaçoes de todos produtos*/
CREATE VIEW relatorio_produto AS
   select * from produto;
   
/*View para criar relatório de pagamento, com os tipos de pagamento e seus ids e também 
quantas vezes esse tipo de pagamento foi usado em vendas*/
CREATE VIEW relatorio_venda_pag AS
   select p.id_pag,p.nome_tipo, COUNT(v.id_venda) from pagamento p
   JOIN venda v ON v.id_pag = p.id_pag
   GROUP BY p.nome_tipo;

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- PAPÉIS --

/*Papel de Administrador e suas permissões, que no caso são todas para esse banco de dados*/
CREATE ROLE Adm;
GRANT ALL ON vendas_empresa.* TO Adm;

/*Papel de Gerente e suas pemissões, que não incluem apenas a criação e exclusão de estruturas no banco*/
CREATE ROLE Gerente;
GRANT select, insert, update, delete ON vendas_empresa.* TO Gerente;

/*Papel de Caixa e suas permissões, que inclui inserções nas tabelas venda e venda_itens na realização de vendas */ 
CREATE ROLE Caixa;
GRANT insert ON vendas_empresa.venda TO Caixa;
GRANT insert ON vendas_empresa.venda_itens TO Caixa;

/*Papel de Estoquista e suas permissões, que inclui inserção e modificações de informações em produto 
e uso da procedure att_estoque para adição de quantidades de um produto ja registrado*/
CREATE ROLE Estoquista;  
GRANT select, insert, update ON vendas_empresa.produto TO Estoquista;
GRANT EXECUTE ON PROCEDURE vendas_empresa.att_estoque TO Estoquista;

/*---------------------------------------------------------------------------------------------------------------------------------------------*/

-- POVOAMENTO DO BANCO DE DADOS --

/*Inserção de dados na tabela cliente*/
insert into cliente (nome_cliente, cpf, endereco, telefone) 
values ('João Silva', '123.456.789-00', 'Rua das flores, 123', '(11) 91234-5678');

insert into cliente (nome_cliente, cpf, endereco, telefone) 
values ('Maria Oliveira', '987.654.321-99', 'Av. Brasil, 456', '(21) 99876-5432');

insert into cliente (nome_cliente, cpf, endereco, telefone) 
values ('Carlos Souza', '456.789.123-11', 'Praça central, 789', '(31) 93456-7890');

insert into cliente (nome_cliente, cpf, endereco, telefone) 
values ('Ana Costa', '321.654.987-22', 'Alameda dos Ipês, 101', '(41) 98765-4321');

insert into cliente (nome_cliente, cpf, endereco) 
values ('Luiza Barros', '111.222.333-44', 'Estrada velha, 999');

/*Inserção de dados na tabela produto*/

               -- Parte superior do corpo: top --
insert into produto (nome, preco, quant, marca, categoria, tam_camisa) 
values ('jaqueta de couro', 299.90, 20, 'Armani', 'top', 'M');

insert into produto (nome, preco, quant, marca, categoria, tam_camisa) 
values ('camisa social', 159.90, 50, 'Dudalina', 'top', 'G');

insert into produto (nome, preco, quant, marca, categoria, tam_camisa) 
values ('blusa de lã', 89.90, 30, 'Zara', 'top', 'P');

              -- Parte inferior do corpo: bottom --
insert into produto (nome, preco, quant, marca, categoria, tam_calca) 
values ('calça jeans', 199.90, 25, 'Levis', 'bottom', '40');

insert into produto (nome, preco, quant, marca, categoria, tam_calca) 
values ('short esportivo', 69.90, 40, 'Adidas', 'bottom', '38');

insert into produto (nome, preco, quant, marca, categoria, tam_calca) 
values ('saia plissada', 119.90, 20, 'C&A', 'bottom', '36');

                      -- Calçados: calçado --
insert into produto (nome, preco, quant, marca, categoria, tam_calcado) 
values ('tênis esportivo', 249.90, 30, 'Nike', 'calçado', 42);

insert into produto (nome, preco, quant, marca, categoria, tam_calcado) 
values ('sandália rasteira', 79.90, 15, 'Arezzo', 'calçado', 38);

insert into produto (nome, preco, quant, marca, categoria, tam_calcado) 
values ('bota de couro', 349.90, 10, 'Timberland', 'calçado', 43);


/*Inserções na tabela de pagamentos*/
insert into pagamento (nome_tipo) values ('cartão de crédito');

insert into pagamento (nome_tipo) values ('cartão de débito');

insert into pagamento (nome_tipo) values ('boleto bancário');

insert into pagamento (nome_tipo) values ('transferência bancária');

insert into pagamento (nome_tipo) values ('pix');

insert into pagamento (nome_tipo) values ('dinheiro');

insert into pagamento (nome_tipo) values ('bitcoin');


/*Inserções na tabela funcionario*/
insert into funcionario (nome_func, cpf, endereco, salario, cargo, data_nasc, telefone) 
values ('João Silva', '123.456.789-00', 'Rua das flores, 123', 5000.00, 'Administrador do sistema', '1985-05-15', '(11) 91234-5678');

insert into funcionario (nome_func, cpf, endereco, salario, cargo, data_nasc, telefone) 
values ('Maria Oliveira', '987.654.321-99', 'Av. brasil, 456', 7000.00, 'Gerente', '1990-07-22', '(21) 99876-5432');

insert into funcionario (nome_func, cpf, endereco, salario, cargo, data_nasc, telefone) 
values ('Carlos Souza', '456.789.123-11', 'Praça central, 789', 2500.00, 'Estoquista', '1992-03-10', '(31) 93456-7890');

insert into funcionario (nome_func, cpf, endereco, salario, cargo, data_nasc, telefone) 
values ('Ana Costa', '321.654.987-22', 'Alameda dos ipês, 101', 2800.00, 'Caixa', '1988-11-30', '(41) 98765-4321');

insert into funcionario (nome_func, cpf, endereco, salario, cargo, data_nasc, telefone) 
values ('Luiza Barros', '111.222.333-44', 'Estrada velha, 999', 2800.00, 'Caixa', '1995-01-17', '(61) 98543-2109');


/*Inserções na tabela venda e venda_itens*/

            -- Venda do cliente João Silva --
insert into venda (id_cliente, quant_prod, valor, id_pag) 
values (1, 2, 389.70, 1);  

  -- Itens da venda de João Silva: 2 Camisas Sociais e 1 Short Esportivo --
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (1, 2, 159.90, 319.80, 2);  
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (1, 5, 69.90, 69.90, 1);  

           -- Venda do cliente Maria Oliveira --
insert into venda (id_cliente, quant_prod, valor, id_pag) 
values (2, 2, 589.70, 5);  

-- Itens da venda de Maria Oliveira: 1 Blusa de Lã e 2 Tênis Esportivos --
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (2, 3, 89.90, 89.90, 1);  
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (2, 7, 249.90, 499.80, 2);  

          -- Venda do cliente Carlos Souza --
insert into venda (id_cliente, quant_prod, valor, id_pag) 
values (3, 2, 379.80, 6);  

-- Itens da venda de Carlos Souza: 1 Jaqueta de Couro e 1 Sandália Rasteira --
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (3, 1, 299.90, 299.90, 1);  
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (3, 8, 79.90, 79.90, 1);  

            -- Venda do cliente Ana Costa --
insert into venda (id_cliente, quant_prod, valor, id_pag) 
values (4, 2, 419.80, 3);  

  -- Itens da venda de Ana Costa: 1 Short Esportivo e 1 Bota de Couro --
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (4, 5, 69.90, 69.90, 1);  
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (4, 9, 349.90, 349.90, 1);  

           -- Venda do cliente Luiza Barros --
insert into venda (id_cliente, quant_prod, valor, id_pag) 
values (5, 3, 319.70, 2);  

-- Itens da venda de Luiza Barros: 1 Camisa Social e 2 Sandálias Rasteiras
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (5, 2, 159.90, 159.90, 1);  
insert into venda_itens (id_venda, id_prod, preco, preco_total, quant_itens) 
values (5, 8, 79.90, 159.80, 2);  

-- selects para visualizar as informações(apagar depois)
select * from cliente;
select * from produto;
select * from pagamento;
select * from venda;
select * from venda_itens;
select * from funcionario;
select * from registro_log;