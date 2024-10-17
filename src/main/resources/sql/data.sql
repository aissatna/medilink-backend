CREATE EXTENSION IF NOT EXISTS unaccent;

--patients
TRUNCATE TABLE public.patients;

INSERT INTO public.patients (first_name, last_name, gender, medical_number, birth_date, address, phone, email)
VALUES
    ('John', 'Doe', 'M', '123456789', '1985-02-15', '123 Main St, Springfield', '0555123456', 'john.doe@example.com'),
    ('Jane', 'Smith', 'F', '234567890', '1990-06-22', '456 Oak Ave, Springfield', '0555567890', 'jane.smith@example.com'),
    ('Michael', 'Johnson', 'M', '345678901', '1978-11-03', '789 Pine Rd, Springfield', '0555901234', 'michael.johnson@example.com'),
    ('Emily', 'Williams', 'F', '456789012', '1995-07-18', '101 Maple St, Springfield', '0555345678', 'emily.williams@example.com'),
    ('David', 'Brown', 'M', '567890123', '1982-04-09', '202 Elm St, Springfield', '0555789012', 'david.brown@example.com'),
    ('Sarah', 'Jones', 'F', '678901234', '1988-12-30', '303 Birch Ln, Springfield', '0555234567', 'sarah.jones@example.com'),
    ('James', 'Garcia', 'M', '789012345', '1981-05-12', '404 Cedar St, Springfield', '0555678901', 'james.garcia@example.com'),
    ('Linda', 'Martinez', 'F', '890123456', '1992-09-27', '505 Willow Dr, Springfield', '0555123457', 'linda.martinez@example.com'),
    ('Robert', 'Davis', 'M', '901234567', '1976-08-19', '606 Aspen Ave, Springfield', '0555567892', 'robert.davis@example.com'),
    ('Patricia', 'Lopez', 'F', '012345678', '1989-03-05', '707 Hickory St, Springfield', '0555901235', 'patricia.lopez@example.com'),
    ('Anna', 'Lee', 'F', '123456780', '1987-01-23', '808 Magnolia Blvd, Springfield', '0555345679', 'anna.lee@example.com'),
    ('Mark', 'Wilson', 'M', '234567801', '1991-03-14', '909 Redwood Dr, Springfield', '0555789013', 'mark.wilson@example.com'),
    ('Sophia', 'Taylor', 'F', '345678902', '1994-08-30', '1010 Cypress Ct, Springfield', '0555234568', 'sophia.taylor@example.com'),
    ('Chris', 'Evans', 'M', '456789013', '1983-11-10', '1111 Sycamore St, Springfield', '0555678902', 'chris.evans@example.com'),
    ('Olivia', 'White', 'F', '567890124', '1996-05-25', '1212 Fir Ln, Springfield', '0555123458', 'olivia.white@example.com'),
    ('Henry', 'Clark', 'M', '678901245', '1979-09-15', '1313 Cherry St, Springfield', '0555567893', 'henry.clark@example.com'),
    ('Isabella', 'Lewis', 'F', '789012346', '1993-10-07', '1414 Poplar Dr, Springfield', '0555901236', 'isabella.lewis@example.com'),
    ('William', 'Harris', 'M', '890123457', '1986-01-19', '1515 Dogwood Ct, Springfield', '0555345680', 'william.harris@example.com'),
    ('Mia', 'Walker', 'F', '901234568', '1997-06-05', '1616 Chestnut St, Springfield', '0555789014', 'mia.walker@example.com'),
    ('Alexander', 'Young', 'M', '012345679', '1984-04-11', '1717 Ash Ln, Springfield', '0555234569', 'alexander.young@example.com');

-- Adjust the sequence for the patients table
SELECT SETVAL(pg_get_serial_sequence('patients', 'id'), (SELECT MAX(id) FROM public.patients));


