delete from resultado;

INSERT INTO resultado
("data", premio1, premio2, premio3, premio4, premio5, premio6, premio7, premio8, premio9, premio10, id_turno, id_loteria)
select 
	"data", 
	format('%04d', premio1),
	format('%04d', premio2),
	format('%04d', premio3),
	format('%04d', premio4),
	format('%04d', premio5),
	format('%04d', premio6),
	format('%04d', premio7),
	format('%04d', premio8),
	format('%04d', premio9),
	format('%04d', premio10),
	id_turno, 
	0
from lotece_csv l
inner join turno t on t.label = l.turno
;
