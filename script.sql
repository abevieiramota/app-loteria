with a as (
	select 
		concurso as id_resultado,
		data, 
		format('%05d', p1) as p1,
		format('%05d', p2) as p2,
		format('%05d', p3) as p3,
		format('%05d', p4) as p4,
		format('%05d', p5) as p5
	from lotefe 
), b as (
	select
		id_resultado,
		data,
		substr(p1, 2, 4) as p1,
		substr(p2, 2, 4) as p2,
		substr(p3, 2, 4) as p3,
		substr(p4, 2, 4) as p4,
		substr(p5, 2, 4) as p5,
		substr(p1, 2, 1) || substr(p2, 2, 1) || substr(p3, 2, 1) || substr(p4, 2, 1) as p6,
		substr(p1, 3, 1) || substr(p2, 3, 1) || substr(p3, 3, 1) || substr(p4, 3, 1) as p7,
		substr(p1, 4, 1) || substr(p2, 4, 1) || substr(p3, 4, 1) || substr(p4, 4, 1) as p8,
		substr(p1, 5, 1) || substr(p2, 5, 1) || substr(p3, 5, 1) || substr(p4, 5, 1) as p9
	from a
)
insert into resultado
select 
	*,
	substr(format('%05d', substr(p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9, -4, 4)), 2, 4) as p10,
	0,
	0
from b
;

delete from resultado where true;