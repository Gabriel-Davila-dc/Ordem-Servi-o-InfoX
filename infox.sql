-- comentários
-- a liha de baixo cria um banco de dados
create database dbinfox;

-- o bloco de instruções a baixo cria uma tabela
CREATE TABLE tbusuarios(
iduser int primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);

-- o comendo a baixo descreve a tabela
describe tbusuarios;


-- a liha abaixo insere dados na tabela (CRUD)
-- create -> insert
insert into tbusuarios (iduser,usuario,fone,login,senha)
values(3,'Bill Gates','7788-7788','Bill','123456');

-- a linha abixo exibe os dados da tabela (CRUD)
select * from tbusuarios;

-- a linha abaixo modifica dados na tabela (CRUD)
-- update -> update
update tbusuarios set fone='8888-8888' where iduser=2;


-- a linha abaixo apaga um registro da tabela (CRUD)
-- delete -> delete
delete from tbusuarios
 where iduser=3;
 
 drop table tbclientes;
 
 create table tbclientes(
 idcli int primary key auto_increment,
 nomecli varchar(50) not null,
 endcli varchar(50),
 fonecli varchar(50) not null,
 emailcli varchar(50)
 );
 
 desc tbclientes;
 
 insert into tbclientes (nomecli,endcli,fonecli,emailcli)
 values('Linus Torvalds','Rua Tux , 2015','9999-9999','linux@gmail.com');
 
 select * from tbclientes; 
 
 create table tbos(
 os int primary key auto_increment,
 data_os timestamp default current_timestamp,
 equipamento varchar(150) not null,
 defeito varchar(150) not null,
 servico varchar(150),
 tecnico varchar(30),
 valor decimal(10,2),
 idcli int not null,
 foreign key(idcli) references tbclientes(idcli)
 );
 
 desc tbos;
 
 insert into tbos (equipamento, defeito, servico, tecnico, valor, idcli)
 values ('noteboook','não liga','trocar a fonte','zé',87.50,1);
 
 select * from tbos;
 
 -- o código abaixo trás informções de duas tabelas
 
 select O.os,equipamento,defeito,servico,valor,
 C.nomecli,fonecli
 from tbos as O
 inner join tbclientes as C
 on(O.idcli = C.idcli);