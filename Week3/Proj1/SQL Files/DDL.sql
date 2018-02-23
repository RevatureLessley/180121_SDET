CREATE TABLE reimbursement (
    username varchar2(100),
    submitDate date,
    startDate date,
    CONSTRAINT username_fk FOREIGN KEY (username)
        REFERENCES EMPLOYEE (username) ON DELETE CASCADE
);


CREATE TABLE TOYEAR (
    username varchar(100) not null unique,
    maximum number(9) default 1000,
    pending number(9) default 0,
    awarded number(9) default 0,

    CONSTRAINT usr_fk FOREIGN KEY (username)
    REFERENCES EMPLOYEE (username) ON DELETE CASCADE
);

ALTER TABLE REIMBURSEMENT
ADD urgent number(9); -- 0: not urgent, 1 - urgent, 2: < 1 week -> rejected;

