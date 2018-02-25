create table tuition(
    e_id number(10),
    t_id number (10) PRIMARY KEY,
    start_date date,
    end_date date,
    location varchar2(100),
    description varchar2(200),
    cost number(8,2),
    grading_formate varchar2(100),
    event_type varchar2(100),
    attachment blob,
    constraint e_id_t_fk foreign key (e_id)REFERENCES employee (e_id) ON DELETE CASCADE
);

create or replace TRIGGER T_SEQ_TRIGGER 
BEFORE INSERT ON TUITION
FOR EACH ROW
BEGIN 
    IF :NEW.T_ID IS NULL THEN
    SELECT E_SEQ.NEXTVAL INTO :NEW.T_ID FROM DUAL;
    END IF;
END;    

INSERT INTO TUITION(E_ID, START_DATE, END_DATE, LOCATION, description, cost, grading_formate, event_type)
values((select e_id FROM LOGIN where e_username = 'HARISH'), 
current_date,current_date,'newyork','onlinecourse', 100.5, 'PASSING_GRADE','UNIVERSITY_COURSE');

create or replace PROCEDURE INSERT_TUITION(USERNAME IN VARCHAR2,S_DATE IN DATE,E_DATE IN DATE,LOC IN VARCHAR2,
DES IN VARCHAR2, CO IN NUMBER, G_FORMATE IN VARCHAR2, E_TYPE IN VARCHAR2, Attach IN BLOB)
IS 
BEGIN
--fill employee table
INSERT INTO TUITION(E_ID, START_DATE, END_DATE, LOCATION, description, cost, grading_formate, event_type, attachment)
values((select e_id FROM LOGIN where e_username = USERNAME), 
s_date,e_date,loc,des,co, g_formate,e_type,Attach);
END;

delete tuition where e_id = 1020;

call INSERT_TUITION('HARISH',current_date,current_date,'newyork','onlinecourse', 100.5, 'PASSING_GRADE','UNIVERSITY_COURSE');

drop table reimbursement;
create table reimbursement(
    e_id number(10) PRIMARY KEY,
    pending_reimbursement number (8,2) NOT NULL,
    awarded_reimbursement number (8,2) NOT NULL,  
    constraint e_id_re_fk foreign key (e_id) REFERENCES employee (e_id) ON DELETE CASCADE
);

UPDATE EMPLOYEE
SET E_TITLE = 'HEAD' where e_first_name = 'PETAR';


update tuition set approval = 3  where e_id = 1020; 

insert into REIMBURSEMENT values((select e_id from login where e_username='PETAR'), 0, 0);

drop table approval;
create table approval(
    t_id number(10) PRIMARY KEY,
    status number(10),
    constraint t_id_fk FOREIGN key (t_id) references tuition(t_id) on delete cascade
);

ALTER TABLE tuition
  ADD projected_reimbursement number(10);
  
UPDATE TUITION
SET APPROVAL = 3 WHERE E_ID = ();

delete tuition where t_id = 1045;

CREATE OR REPLACE FORCE VIEW "TUTION"."ALLTUITION" ("T_ID","E_USERNAME", "START_DATE", "END_DATE", "LOCATION", "DESCRIPTION", "COST", "GRADING_FORMATE", "EVENT_TYPE", "ATTACHMENT","APPROVAL","PROJECTED") AS 
  select t_id, e_username, start_date, end_date, location, description, cost, grading_formate, event_type, attachment, approval, projected_reimbursement 
from login natural inner join TUITION;

select * from alltuition;

create or replace view ALLREIMBURSEMENT as
select e_username, pending_reimbursement, awarded_reimbursement from login natural inner join reimbursement;

create or replace PROCEDURE DELETE_TUITION_TUITIONID(ID IN NUMBER)
IS 
BEGIN
DELETE TUITION
WHERE T_ID = (SELECT E_ID FROM LOGIN WHERE E_USERNAME = USERNAME);
END;

delete tuition where e_id = '1002';
commit;

create or replace PROCEDURE SELECT_SUB_EMPLOYEE(USERNAME IN VARCHAR2)
IS 
BEGIN
select e_username from login where e_id = (select e_id from employee 
where e_supervisior = (select e_id from login where e_username = 'USERNAME'));
END;

update tuition set approval = 0 , description = 'approved' where t_id = 1060;
commit;