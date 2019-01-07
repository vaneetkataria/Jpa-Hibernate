DELETE FROM `testdb`.`vehicle` ;
delete from `testdb`.`id_proof_tbl`;
delete from `testdb`.`instructor` ; 

INSERT INTO `testdb`.`instructor`
(`id`,`name`,`address`,`birth_date_time`,`birth_date_time_zone_offset`,`created_date`,`day_off_time`,`day_start_time`,`father_name`,`mother_name`,`photo`,`monthly_salary`,`updated_date`,`version`)
VALUES(1,'Vaneet Kataria' , 'K block , 6th Lane , Plot no : 1 , second floor , Vatika , Sector 83 , Gurgaon , 122004','1990-12-30 03:27:30.000000001', 19800, now() , '19:00:00' , '11:00:00' ,'Naresh Kataria','Neelam Kataria',null, 120000.00 ,now(), 0);
INSERT INTO `testdb`.`instructor`
(`id`,`name`,`address`,`birth_date_time`,`birth_date_time_zone_offset`,`created_date`,`day_off_time`,`day_start_time`,`father_name`,`mother_name`,`photo`,`monthly_salary`,`updated_date`,`version`)
VALUES(2,'Rashmi Kataria','#1074 , ward:22 , Kaziwara , Ambala city , 134003 ','1993-09-19 14:27:30.000000001', 19800, now() , '16:00:00' , '09:00:00' ,'Naresh Kataria','Neelam Kataria',null, 19000.00 ,now(), 0); 
INSERT INTO `testdb`.`instructor`
(`id`,`name`,`address`,`birth_date_time`,`birth_date_time_zone_offset`,`created_date`,`day_off_time`,`day_start_time`,`father_name`,`mother_name`,`photo`,`monthly_salary`,`updated_date`,`version`)
VALUES(3, 'Neelam Kataria' ,'#1074 , ward:22 , Kaziwara , Ambala city , 134003 ','1970-12-30 03:27:30.000000001', 19800, now() , '23:00:00' , '08:00:00' ,'Amarnath','Rajrani',null, 10000.00 ,now(), 0);
INSERT INTO `testdb`.`instructor`
(`id`,`name`,`address`,`birth_date_time`,`birth_date_time_zone_offset`,`created_date`,`day_off_time`,`day_start_time`,`father_name`,`mother_name`,`photo`,`monthly_salary`,`updated_date`,`version`)
VALUES(4, 'Naresh Kataria' , '#1074 , ward:22 , Kaziwara , Ambala city , 134003 ','1970-08-15 16:30:30.000000001', 19800, now() , '20:00:00' , '08:00:00' ,'Ram Swroop','Rajrani',null, 100000.00 ,now(), 0);
INSERT INTO `testdb`.`instructor`
(`id`,`name`,`address`,`birth_date_time`,`birth_date_time_zone_offset`,`created_date`,`day_off_time`,`day_start_time`,`father_name`,`mother_name`,`photo`,`monthly_salary`,`updated_date`,`version`)
VALUES(5,'Mansi Kataria', 'K block , 6th Lane , Plot no : 1 , second floor , Vatika , Sector 83 , Gurgaon , 122004','1991-01-30 15:30:30.000000001', 19800, now() , '14:00:00' , '06:00:00' ,'Girish Arora','Ekta Arora',null, 28500.00 ,now(), 0);


INSERT INTO `testdb`.`id_proof_tbl`(`id`,`address`,`created_date`,`father_name`,`is_foreigner`,`mother_name`,`name`,`proof_sequence_no`,`sex`,`updated_date`,`version` ,`instructor_id`)
VALUES(1,'4545/3 , Pankhon wali gali , Near Hari Mandir , Ambala city.',now(),'Naresh Kumar Kataria', false , 'Neelam Kumari Kataria','Vaneet Kumar','676051523510','M',now(),0 , 1);
INSERT INTO `testdb`.`id_proof_tbl`(`id`,`address`,`created_date`,`father_name`,`is_foreigner`,`mother_name`,`name`,`proof_sequence_no`,`sex`,`updated_date`,`version` ,`instructor_id`)
VALUES(2,'4545/3 , Pankhon wali gali , Near Hari Mandir , Ambala city.',now(),'Naresh Kumar Kataria',false ,'Neelam Kumari Kataria','Rashmi Kataria','676051523511','F',now(),0, 2);
INSERT INTO `testdb`.`id_proof_tbl`(`id`,`address`,`created_date`,`father_name`,`is_foreigner`,`mother_name`,`name`,`proof_sequence_no`,`sex`,`updated_date`,`version`,`instructor_id`)
VALUES(3,'4545/3 , Pankhon wali gali , Near Hari Mandir , Ambala city.',now(),'Amarnath Arora',false ,'RajRani Arora','Neelam Kataria','676051523512','F',now(),0, 3);
INSERT INTO `testdb`.`id_proof_tbl`(`id`,`address`,`created_date`,`father_name`,`is_foreigner`,`mother_name`,`name`,`proof_sequence_no`,`sex`,`updated_date`,`version`,`instructor_id`)
VALUES(4,'4545/3 , Pankhon wali gali , Near Hari Mandir , Ambala city.',now(),'Ram Swaroop Kataria',false ,'Rajrani Kataria','Naresh Kataria','676051523513','M',now(),0, 4);
INSERT INTO `testdb`.`id_proof_tbl`(`id`,`address`,`created_date`,`father_name`,`is_foreigner`,`mother_name`,`name`,`proof_sequence_no`,`sex`,`updated_date`,`version`,`instructor_id`)
VALUES(5,'B/36 , Subhash colony , Amabala Cantt',now(),'Girish Kumar Arora',false ,'Ekta Arora','Mansi Kataria','676051523514','F',now(),0, 5);



INSERT INTO `testdb`.`vehicle`(`id`,`creation_date`,`purchased_date_time`,`purchased_date_zone_offset`,`updated_date`,`vechicle_type`,`vehicle_number`,`instructor_id`)
VALUES(1,now(),'2005-12-12 08:57:30.000001',19800,now(),'TWO_WHEELER' ,'HRO1AD 3476',1);
INSERT INTO `testdb`.`vehicle`(`id`,`creation_date`,`purchased_date_time`,`purchased_date_zone_offset`,`updated_date`,`vechicle_type`,`vehicle_number`,`instructor_id`)
VALUES(2,now(),'2006-12-12 08:57:30.000001',19800,now(),'FOUR_WHEELER','HRO1AD 3477',1);
INSERT INTO `testdb`.`vehicle`(`id`,`creation_date`,`purchased_date_time`,`purchased_date_zone_offset`,`updated_date`,`vechicle_type`,`vehicle_number`,`instructor_id`)
VALUES(3,now(),'2007-12-12 08:57:30.000001',19800,now(),'TWO_WHEELER','HRO1AD 3478',2);
INSERT INTO `testdb`.`vehicle`(`id`,`creation_date`,`purchased_date_time`,`purchased_date_zone_offset`,`updated_date`,`vechicle_type`,`vehicle_number`,`instructor_id`)
VALUES(4,now(),'2008-12-12 08:57:30.000001',19800,now(),'EIGHT_WHEELER','HRO1AD 3479',2);
INSERT INTO `testdb`.`vehicle`(`id`,`creation_date`,`purchased_date_time`,`purchased_date_zone_offset`,`updated_date`,`vechicle_type`,`vehicle_number`,`instructor_id`)
VALUES(5,now(),'2009-12-12 08:57:30.000001',19800,now(),'THREE_WHEELER','HRO1AD 3480',3);
INSERT INTO `testdb`.`vehicle`(`id`,`creation_date`,`purchased_date_time`,`purchased_date_zone_offset`,`updated_date`,`vechicle_type`,`vehicle_number`,`instructor_id`)
VALUES(6,now(),'2010-12-12 08:57:30.000001',19800,now(),'SIX_WHEELER','HRO1AD 3481',3);








