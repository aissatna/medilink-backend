CREATE EXTENSION IF NOT EXISTS unaccent;

-- *** Cabinets
INSERT INTO public.cabinets (id, name, address)
VALUES
    (1, 'Health Hub', '123 First St, Springfield'),
    (2, 'Wellness Center', '456 Second St, Springfield');
SELECT SETVAL(pg_get_serial_sequence('cabinets', 'id'), (SELECT MAX(id) FROM public.cabinets));

-- *** Patients
-- Patients for Cabinet A
INSERT INTO public.patients (first_name, last_name, gender, medical_number, birth_date, address, phone, email, cabinet_id)
VALUES
    ('John', 'Doe', 'M', '123456789', '1985-02-15', '123 Main St, Springfield', '0123456789', 'john.doe@example.com', 1),
    ('Jane', 'Smith', 'F', '234567890', '1990-06-22', '456 Oak Ave, Springfield', '0234567890', 'jane.smith@example.com', 1),
    ('Michael', 'Johnson', 'M', '345678901', '1978-11-03', '789 Pine Rd, Springfield', '0345678901', 'michael.johnson@example.com', 1),
    ('Emily', 'Williams', 'F', '456789012', '1995-07-18', '101 Maple St, Springfield', '0456789012', 'emily.williams@example.com', 1),
    ('David', 'Brown', 'M', '567890123', '1982-04-09', '202 Elm St, Springfield', '0567890123', 'david.brown@example.com', 1),
    ('Sarah', 'Jones', 'F', '678901234', '1988-12-30', '303 Birch Ln, Springfield', '0678901234', 'sarah.jones@example.com', 1),
    ('James', 'Garcia', 'M', '789012345', '1981-05-12', '404 Cedar St, Springfield', '0789012345', 'james.garcia@example.com', 1),
    ('Linda', 'Martinez', 'F', '890123456', '1992-09-27', '505 Willow Dr, Springfield', '0890123456', 'linda.martinez@example.com', 1),
    ('Robert', 'Davis', 'M', '901234567', '1976-08-19', '606 Aspen Ave, Springfield', '0901234567', 'robert.davis@example.com', 1),
    ('Patricia', 'Lopez', 'F', '012345678', '1989-03-05', '707 Hickory St, Springfield', '0123456789', 'patricia.lopez@example.com', 1);

-- Patients for Cabinet B
INSERT INTO public.patients (first_name, last_name, gender, medical_number, birth_date, address, phone, email, cabinet_id)
VALUES
    ('Anna', 'Lee', 'F', '123456780', '1987-01-23', '808 Magnolia Blvd, Springfield', '0123456780', 'anna.lee@example.com', 2),
    ('Mark', 'Wilson', 'M', '234567801', '1991-03-14', '909 Redwood Dr, Springfield', '0234567891', 'mark.wilson@example.com', 2),
    ('Sophia', 'Taylor', 'F', '345678902', '1994-08-30', '1010 Cypress Ct, Springfield', '0345678902', 'sophia.taylor@example.com', 2),
    ('Chris', 'Evans', 'M', '456789013', '1983-11-10', '1111 Sycamore St, Springfield', '0456789013', 'chris.evans@example.com', 2),
    ('Olivia', 'White', 'F', '567890124', '1996-05-25', '1212 Fir Ln, Springfield', '0567890124', 'olivia.white@example.com', 2),
    ('Henry', 'Clark', 'M', '678901245', '1979-09-15', '1313 Cherry St, Springfield', '0678901235', 'henry.clark@example.com', 2),
    ('Isabella', 'Lewis', 'F', '789012346', '1993-10-07', '1414 Poplar Dr, Springfield', '0789012346', 'isabella.lewis@example.com', 2),
    ('William', 'Harris', 'M', '890123457', '1986-01-19', '1515 Dogwood Ct, Springfield', '0890123457', 'william.harris@example.com', 2),
    ('Mia', 'Walker', 'F', '901234568', '1997-06-05', '1616 Chestnut St, Springfield', '0901234568', 'mia.walker@example.com', 2),
    ('Alexander', 'Young', 'M', '012345679', '1984-04-11', '1717 Ash Ln, Springfield', '0123456781', 'alexander.young@example.com', 2);

SELECT SETVAL(pg_get_serial_sequence('patients', 'id'), (SELECT MAX(id) FROM public.patients));
-- *** Users
-- Users for Cabinet A
INSERT INTO public.users (id, first_name, last_name, gender, email, phone_number, password, role, photo_url, cabinet_id)
VALUES
    -- Director User
    (1, 'John', 'Doe', 'M', 'john.doe@example.com', '0123456789', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'DIRECTOR', 'users/1/avatar/avatar.gif', 1),
    -- Secretary User
    (2, 'Jane', 'Smith', 'F', 'jane.smith@example.com', '0123456790', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'SECRETARY', 'users/2/avatar/avatar.gif', 1),
    -- Nurse Users
    (3, 'Emily', 'Johnson', 'F', 'emily.johnson@example.com', '0123456791', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'NURSE', 'users/3/avatar/avatar.gif', 1),
    (4, 'Michael', 'Brown', 'M', 'michael.brown@example.com', '0123456792', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'NURSE', 'users/4/avatar/avatar.gif', 1);

-- Users for Cabinet B
INSERT INTO public.users (id, first_name, last_name, gender, email, phone_number, password, role, photo_url, cabinet_id)
VALUES
    -- Director User
    (5, 'Olivia', 'Williams', 'F', 'olivia.williams@example.com', '0123456793', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'DIRECTOR', 'users/5/avatar/avatar.gif', 2),
    -- Secretary User
    (6, 'James', 'Miller', 'M', 'james.miller@example.com', '0123456794', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'SECRETARY', 'users/6/avatar/avatar.gif', 2),
    -- Nurse Users
    (7, 'Sophie', 'Garcia', 'F', 'sophie.garcia@example.com', '0123456795', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'NURSE', 'users/7/avatar/avatar.gif', 2),
    (8, 'Daniel', 'Rodriguez', 'M', 'daniel.rodriguez@example.com', '0123456796', '$2a$10$ZUUo1.cbkpMBgLx8Fwr5MubQRf0UnXPMxkwbyImCBAoFA/gMDUEye', 'NURSE', 'users/8/avatar/avatar.gif', 2);

SELECT SETVAL(pg_get_serial_sequence('users', 'id'), (SELECT MAX(id) FROM public.users));
