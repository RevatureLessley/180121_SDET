
DROP TABLE messages;
DROP TABLE uploaded;
DROP TABLE claimsRe;
DROP TABLE scoringformts;
DROP TABLE event_types;
DROP TABLE workers;
DROP TABLE depts;
DROP TABLE adminstat;

-- CREATE TABLES
CREATE TABLE adminstat(
    admin_statid NUMBER(8),
    stat_type VARCHAR2(40),
    CONSTRAINT admin_stat_pk PRIMARY KEY (admin_statid)
);

CREATE TABLE depts (
    dep_id NUMBER(8),
    dep_name VARCHAR(30),
    CONSTRAINT dep_id_pk PRIMARY KEY (dep_id)
);

CREATE TABLE workers(
    u_id NUMBER(9),
    w_id NUMBER(9),
    w_super NUMBER(9) NOT NULL,
    w_department NUMBER(8) NOT NULL,
    w_adminstat NUMBER(2) NOT NULL,
    w_avalreim NUMBER(9,2) DEFAULT 1000.00,
    w_fname VARCHAR2(30) NOT NULL,
    w_lname VARCHAR2(30) NOT NULL,
    w_email VARCHAR2(60),
    username VARCHAR2(40) NOT NULL,
    password VARCHAR2(20) NOT NULL,
    CONSTRAINT w_id_pk PRIMARY KEY (w_id),
    CONSTRAINT w_adminstat_fk FOREIGN KEY (w_adminstat)
    REFERENCES adminstat (admin_statid) ON DELETE CASCADE,
    CONSTRAINT w_department_fk FOREIGN KEY (w_department)
    REFERENCES depts (dep_id) ON DELETE CASCADE
);


CREATE TABLE event_types(
    event_id NUMBER(9),
    event_name VARCHAR2(50) NOT NULL,
    event_cover_rate NUMBER(3,2) NOT NULL,
    CONSTRAINT event_id_pk PRIMARY KEY (event_id)
);

CREATE TABLE scoringformts(
    formtid NUMBER(9),
    format_type VARCHAR2(50) NOT NULL,
    format_req VARCHAR2(250) NOT NULL,
    CONSTRAINT scoringformts_pk PRIMARY KEY(formtid)
);

CREATE TABLE claimsRe(
    claimreid NUMBER(9),
    claim_event_id NUMBER(9),
    claim_cost NUMBER(9,2),
    claim_projreimb NUMBER(9,2),
    claim_w_id NUMBER(9),
    grade_formid NUMBER(9),
    claim_grade NUMBER(3),
    claim_passgrade NUMBER(3) DEFAULT 75,
    claim_reason VARCHAR2(300),
    claim_date DATE,
    timenowis TIMESTAMP(5) DEFAULT SYSTIMESTAMP,
	claim_urgent NUMBER(1),
    approv_stat NUMBER(1), 
    claim_approved NUMBER(1) DEFAULT 2,
    CONSTRAINT claimreid_pk PRIMARY KEY (claimreid),
    CONSTRAINT claim_w_id_fk FOREIGN KEY (claim_w_id)
    REFERENCES workers (w_id) ON DELETE CASCADE,
    CONSTRAINT claim_event_id_fk FOREIGN KEY (claim_event_id)
    REFERENCES event_types(event_id) ON DELETE CASCADE,
    CONSTRAINT grade_formid_fk FOREIGN KEY (grade_formid)
    REFERENCES scoringformts(formtid) ON DELETE CASCADE
    );

CREATE TABLE uploaded(
    upload_id NUMBER(9),
    up_reim_id NUMBER(9),
    upload_file BLOB,
    upfilename VARCHAR(55),
    CONSTRAINT upload_id_pk PRIMARY KEY(upload_id),
    CONSTRAINT up_reim_id_fk FOREIGN KEY(up_reim_id)
    REFERENCES claimsRe(claimreid) ON DELETE CASCADE
);

CREATE TABLE messages ( 
    mess_id NUMBER(9),
    reim_id NUMBER(9),
    addbywork_id NUMBER(9),
    message VARCHAR(500),
    CONSTRAINT mess_id_pk PRIMARY KEY(mess_id),
    CONSTRAINT reim_id_fk FOREIGN KEY(reim_id)
    REFERENCES claimsRe(claimreid),
    CONSTRAINT addbywork_id_fk FOREIGN KEY(addbywork_id)
    REFERENCES workers(w_id) ON DELETE CASCADE
);

-- id generate
DROP SEQUENCE u_id_seq;
CREATE SEQUENCE u_id_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE cid_seq;
CREATE SEQUENCE cid_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE mess_seq;
CREATE SEQUENCE mess_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE uploaded_seq;
CREATE SEQUENCE uploaded_seq
    START WITH 1
    INCREMENT BY 1;

-- trigger
CREATE OR REPLACE TRIGGER uid_insert
BEFORE INSERT ON workers
FOR EACH ROW
BEGIN
    IF :new.u_id IS NULL THEN
    SELECT u_id_seq.NEXTVAL INTO :new.u_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER claimid_insert
BEFORE INSERT ON claimsRe
FOR EACH ROW
BEGIN
    IF :new.claimreid IS NULL THEN
    SELECT cid_seq.NEXTVAL INTO :new.claimreid FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER uploadedid_insert
BEFORE INSERT ON uploaded
FOR EACH ROW
BEGIN
    IF :new.upload_id IS NULL THEN
    SELECT uploaded_seq.NEXTVAL INTO :new.upload_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER messageid_insert
BEFORE INSERT ON messages
FOR EACH ROW
BEGIN
    IF :new.mess_id IS NULL THEN
    SELECT mess_seq.NEXTVAL INTO :new.mess_id FROM dual;
    END IF;
END;
/
    COMMIT;

--values
INSERT INTO adminstat VALUES(1, 'worker');
INSERT INTO adminstat VALUES(2, 'supervisor');
INSERT INTO adminstat VALUES(3, 'department head');
INSERT INTO adminstat VALUES(4, 'benefits coordinator');

INSERT INTO scoringformts VALUES(1, 'score', 'Complete and pass a quantifiably scored final');
INSERT INTO scoringformts VALUES(2, 'presentation', 'Give an overview of the major takeaways from this event');

INSERT INTO event_types VALUES(1, 'university courses', 0.80);
INSERT INTO event_types VALUES(2, 'seminars', 0.60);
INSERT INTO event_types VALUES(3, 'certification preparation classes', 0.75);
INSERT INTO event_types VALUES(4, 'certification', 1.00);
INSERT INTO event_types VALUES(5, 'technical training', 0.90);
INSERT INTO event_types VALUES(6, 'other', 0.30);

INSERT INTO depts VALUES(1, 'development');
INSERT INTO depts VALUES(2, 'sales');
INSERT INTO depts VALUES(3, 'finance');
INSERT INTO depts VALUES(4, 'research');
INSERT INTO depts VALUES(5, 'legal');
INSERT INTO depts VALUES(6, 'operations');
INSERT INTO depts VALUES(7, 'benefits');
-- w_id, w_fname, w_lname, w_super, w_department, w_adminstat, w_avalreim, w_email, username, password
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(1, 2, 1, 1, 1000, 'Jawaun', 'Brown', 'jawauntb@yahoo.com', 'juan', 'don');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(2, 3, 1, 2, 1000, 'Marina', 'TBrown', 'mltb@yahoo.com', 'mari', 'wifey');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(3, 0, 1, 3, 1000, 'Marina', 'Tassi', 'love@yahoo.com', 'malo', 'solo');

INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(4, 5, 2, 2, 1000, 'Louise', 'Tassi', 'lulu@yahoo.com', 'clastas', 'lurema');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(5, 0, 2, 3, 1000, 'Jake', 'Zommerc', 'meek@yahoo.com', 'jake', 'statefarm');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(6, 4, 2, 1, 900, 'Nigel', 'Farage', 'curbcrash@yahoo.com', 'nigel', 'nickel');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(7, 4, 2, 1, 1000, 'Sona', 'Mathur', 'mathurs@yahoo.com', 'soma', 'dee');

INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(8, 0, 5, 3, 1000, 'Peter', 'Griffin', 'jke@gmail.com', 'peter', 'petey');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(9, 8, 5, 2, 1000, 'Hater', 'Who', 'htb@yahoo.com', 'lame', 'ask');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(10, 9, 5, 1, 800, 'Erwin', 'Einstein', 'eee@gmail.com', 'eshim', 'hi');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(11, 9, 5, 1, 1000, 'Doug', 'Fresh', 'niikooo@yahoo.com', 'dougger', 'low');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(12, 9, 5, 1, 1000, 'Karen', 'Pritzker', 'kplee@gmail.com', 'kplee', '1758');

INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(13, 16, 7, 2, 1000, 'Joel', 'Campo', 'sam@yahoo.com', 'joel', 'jordan');
INSERT INTO workers(w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(14, 13, 7, 4, 1000, 'Julia', 'Vlock', 'iam@yahoo.com', 'julia', 'yurman');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(15, 13, 7, 4, 500, 'Raheem', 'Jackson', 'poopybutt@yahoo.com', 'rah', 'heem');
INSERT INTO workers (w_id, w_super, w_department, w_adminstat, w_avalreim, w_fname, w_lname, w_email, username, password) VALUES(16, 0, 7, 3, 1000, 'Robyn', 'Wood', 'hershey@yahoo.com', 'robyn', 'lady');


COMMIT;


