select * from instructor i cross join vehicle v order by i.id;
select * from instructor i join vehicle v order by i.id;
select * from instructor i natural join vehicle v order by i.id;
select * from instructor i left outer join vehicle v on i.id = v.instructor_id order by i.id;
select * from instructor i right outer join vehicle v on i.id = v.instructor_id order by i.id;
select * from instructor i join vehicle v on i.id = v.instructor_id order by i.id;
select * from instructor i left outer join vehicle v on i.id = v.instructor_id 
union
select * from instructor i right outer join vehicle v on i.id = v.instructor_id ;
select * from instructor i inner join vehicle v on i.id = v.instructor_id;
select * from instructor i , vehicle v where i.id = v.instructor_id;










