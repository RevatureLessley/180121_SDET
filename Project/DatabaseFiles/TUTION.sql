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