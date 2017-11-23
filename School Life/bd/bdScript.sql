create database if not exists school_life;
use school_life;
 
create table if not exists professor(
idProfessor int auto_increment primary key,
nome varchar (120) not null,
email varchar (100)
);
 
create table if not exists materia(
idMateria int auto_increment primary key,
nome varchar (120) not null,
 
idProfessorFK int not null
);
 
create table if not exists tipo_atividade(
idtipo_atividade int auto_increment primary key,
nome varchar (60) not null unique
);
 
create table if not exists atividade(
idAtividade  int auto_increment primary key,
data_entrega date not null,
nome varchar (120) not null,
etapa varchar (1) not null,
prioridade varchar (5) not null,
pontuacao int not null,
situacao varchar(30) not null,
 
idTipo_AtividadeFK int not null,
 
idMateriaFK int not null,
 
idProfessorFK int not null
);