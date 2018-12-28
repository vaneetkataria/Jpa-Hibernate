update instructor set monthly_salary = 130000 where id = 1; 
update instructor set monthly_salary = 150000 where id in (1 , 2); 
update instructor set monthly_salary = 170000 where id in 
(select id from  (select * from instructor i where i.father_name like '%Naresh%')
as j);
update instructor set monthly_salary = 200000 where id in (
select id from (select id from instructor where father_name like '%Naresh%' and monthly_salary > 50000) as j);
delete from instructor where id = 5;
delete from instructor where id in (3 , 4);
delete from instructor where id in (
select id from  
(select id from instructor where father_name like '%Naresh%') as j);
delete from instructor where id in (select id from (select id from instructor where father_name like '%Naresh%' and monthly_salary > 20000)as j);
