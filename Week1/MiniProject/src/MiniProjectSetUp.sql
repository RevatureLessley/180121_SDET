-- TABLE CREATION
DROP TABLE admins;
DROP TABLE users;
DROP TABLE beverage_brands;

CREATE TABLE admins(
    admin_id number(3),
    username varchar2(20) NOT NULL,
    p_word varchar(50) NOT NULL,
    CONSTRAINT admins_pk PRIMARY KEY (admin_id)
);

CREATE TABLE users(
    user_id number(6),
    username varchar(20) NOT NULL,
    p_word varchar(50) NOT NULL,
    currency decimal(38,2),
    last_login timestamp(6),
    login_streak number(9),
    account_approved number(1),
    banned number(1),
    is_admin number(1),
    beverage_id number(4),
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE beverage_brands (
    brand_id number(6),
    brand_name varchar2(30),
    bev_type varchar2(20),
    CONSTRAINT beverage_pk PRIMARY KEY (brand_id)
);

ALTER TABLE users
MODIFY last_login DATE;
ALTER TABLE users
MODIFY login_streak NUMBER(9) DEFAULT 0;
ALTER TABLE users
ADD CONSTRAINT bev_id_fk FOREIGN KEY(beverage_id) 
REFERENCES beverage_brands (brand_id) ON DELETE CASCADE;

-- POPULATE TABLES
INSERT INTO users (username, p_word, currency, last_login, login_streak, beverage_id)
VALUES ('kirk', 'password', 300, CURRENT_DATE - 1, 5, 108);
INSERT INTO users (username, p_word, currency, last_login, login_streak, beverage_id)
VALUES ('dana', 'password', 3000, NULL, 0, 99);
INSERT INTO users (username, p_word, currency, last_login, login_streak, beverage_id)
VALUES ('amanda', 'password', 1000, CURRENT_DATE - 1, 5, 110);
INSERT INTO users (username, p_word, currency, last_login, login_streak, beverage_id)
VALUES ('chloe', 'password', 5000, CURRENT_DATE - 1, 5, 103);
INSERT INTO users (username, p_word, currency, last_login, login_streak, beverage_id)
VALUES ('joshua', 'password', 10000, NULL, 0, 105);
INSERT INTO users (username, p_word, currency, last_login, login_streak, beverage_id)
VALUES ('ethan', 'password', 50, NULL, 0, 102);

INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Gold Peak', 'tea');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Tazo', 'tea');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Starbucks', 'iced coffee');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Honest Tea', 'iced tea');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Arizona', 'iced tea');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Snapple', 'iced tea');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('The Republice of Tea', 'iced tea');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Harney & Sons', 'iced tea');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Real Beanz', 'iced coffee');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Grumpy Cat', 'iced coffee');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Dunkin Donuts', 'iced coffee');
INSERT INTO beverage_brands (brand_name, bev_type) VALUES('Hi Ball Energy', 'iced coffee');

-- SEQUENCES
DROP SEQUENCE admins_seq;
CREATE SEQUENCE admins_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE users_seq;
CREATE SEQUENCE users_seq
    START WITH 50
    INCREMENT BY 1;
    
DROP SEQUENCE bbrands_seq;
CREATE SEQUENCE bbrands_seq
    START WITH 99
    INCREMENT BY 1;
    
-- TRIGGERS
CREATE OR REPLACE TRIGGER ad_seq_trig
BEFORE INSERT ON admins
FOR EACH ROW
BEGIN
    IF :new.admin_id IS NULL THEN
    SELECT admins_seq.NEXTVAL INTO :new.admin_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER user_seq_trig
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    IF :new.user_id IS NULL THEN
    SELECT users_seq.NEXTVAL INTO :new.user_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER bbrands_seq_trig
BEFORE INSERT ON beverage_brands
FOR EACH ROW
BEGIN
    IF :new.brand_id IS NULL THEN
    SELECT bbrands_seq.NEXTVAL INTO :new.brand_id FROM dual;
    END IF;
END;
/

--PROCEDURES
CREATE OR REPLACE PROCEDURE insert_admin(in_username IN varchar2, in_p_word IN varchar2)
IS
BEGIN
    INSERT INTO admins (username, p_word) VALUES (in_username, in_p_word);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insert_into_users(in_username IN varchar2, in_p_word IN varchar2)
IS
BEGIN
    INSERT INTO users (username, p_word, currency, account_approved, banned) 
    VALUES (in_username, in_p_word, 0, 0, 0);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE approve_user(in_username IN VARCHAR2)
IS
BEGIN
    UPDATE users SET account_approved = 1 WHERE username = in_username;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE ban_un_user(in_username IN VARCHAR2, in_option IN NUMBER)
IS
BEGIN
    UPDATE users SET banned = in_option WHERE username = in_username;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE remove_user(in_username IN VARCHAR2, in_option IN NUMBER)
IS
BEGIN
    UPDATE users SET banned = in_option WHERE username = in_username;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE login_streak(in_username IN VARCHAR2)
IS
BEGIN
    UPDATE users SET login_streak =
    (CASE WHEN TO_CHAR(last_login + 1, 'MM-DD-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-DD-YYYY') THEN (login_streak + 1)
    WHEN TO_CHAR(last_login, 'MM-DD-YYYY') = TO_CHAR(CURRENT_DATE, 'MM-DD-YYYY') THEN login_streak
    ELSE 0
    END) WHERE username = in_username;
    UPDATE users SET last_login = CURRENT_DATE WHERE username = in_username;
    COMMIT;
END;
/

UPDATE users SET login_streak = 2 WHERE username = 'bobby';
UPDATE users SET last_login = (CURRENT_DATE - 1) WHERE username = 'bobby';
UPDATE users SET login_streak = 4 WHERE username = 'ruth';

commit;