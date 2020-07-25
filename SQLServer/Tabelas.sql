create table Aluno_ed (
ra smallint primary key,
nomeAluno varchar(40) not null
)

create table Disciplina_ed (
codDisciplina int primary key,
nomeDisciplina varchar(40) not null
)

create table Matricula_ed (
codMatricula int primary key,
ra smallint not null,
codDisciplina int not null
constraint fk_ra02 foreign key (ra)
references Aluno_ed(ra),
constraint fk_codDisciplina02 foreign key (codDisciplina)
references Disciplina_ed(codDisciplina)
)

create table Resultado_ed (
codResultado int primary key,
ra smallint not null,
codDisciplina int not null,
nota float not null,
frequencia float not null
constraint fk_ra03 foreign key (ra)
references Aluno_ed(ra),
constraint fk_codDisciplina03 foreign key (codDisciplina)
references Disciplina_ed(codDisciplina)
)


select * from Aluno_ed
select * from Disciplina_ed
select * from Matricula_ed
select * from Resultado_ed

insert into Aluno_ed values(19162, 'Antônio Hideto Borges Kotsubo')
insert into Aluno_ed values(19190, 'Matheus Seiji Luna Noda')
insert into Aluno_ed values(19192, 'Nícolas Maisonnette Duarte')

insert into Disciplina_ed values (1, 'Estrutura de Dados I')
insert into Disciplina_ed values (2, 'Arquitetura Orientada a Serviços')
insert into Disciplina_ed values (3, 'Desenvolvimento para a Internet III')

insert into Matricula_ed values (1, 19162, 2)
insert into Matricula_ed values (2, 19190, 3)
insert into Matricula_ed values (3, 19192, 1)