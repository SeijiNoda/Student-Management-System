create table Aluno_ed (
ra smallint primary key,
nomeAluno varchar(40) not null
)

create table Disciplina_ed (
codDisciplina int primary key,
nomeDisciplina varchar(40) not null
)

create table Matricula_ed (
ra smallint not null,
codDisciplina int not null
primary key (ra, codDisciplina)
constraint fk_ra02 foreign key (ra)
references Aluno_ed(ra),
constraint fk_codDisciplina02 foreign key (codDisciplina)
references Disciplina_ed(codDisciplina)
)

create table Resultado_ed (
ra smallint not null,
codDisciplina int not null,
nota float not null,
frequencia float not null
primary key (ra, codDisciplina)
constraint fk_ra03 foreign key (ra)
references Aluno_ed(ra),
constraint fk_codDisciplina03 foreign key (codDisciplina)
references Disciplina_ed(codDisciplina)
)

drop table Aluno_ed
drop table Disciplina_ed
drop table Matricula_ed
drop table Resultado_ed

select * from Aluno_ed
select * from Disciplina_ed
select * from Matricula_ed
select * from Resultado_ed

delete from Resultado_ed where ra = 19191
insert into Matricula_ed values(19191, 1)

insert into Aluno_ed values(19162, 'Antônio Hideto Borges Kotsubo')
insert into Aluno_ed values(19190, 'Matheus Seiji Luna Noda')
insert into Aluno_ed values(19192, 'Nícolas Maisonnette Duarte')

insert into Disciplina_ed values (1, 'Estrutura de Dados I')
insert into Disciplina_ed values (2, 'Arquitetura Orientada a Serviços')
insert into Disciplina_ed values (3, 'Desenvolvimento para a Internet III')

insert into Matricula_ed values (19162, 2)
insert into Matricula_ed values (19190, 3)
insert into Matricula_ed values (19192, 2)

create trigger insert_tg
on Resultado_ed
FOR INSERT
as
BEGIN 
	declare 
	@ra smallint,
	@disc int,
	@del int

	select @ra = RA, @disc = codDisciplina from inserted

	delete from Matricula_ed where RA = @ra and codDisciplina = @disc

	set @del = (select count(*) from Matricula_ed where RA = @ra and codDisciplina = @disc);
	if @del < 1
	BEGIN 
		RAISERROR (N'Sem matricula para deletar.', 16, 1);
	END
END 
