CREATE EXTENSION IF NOT EXISTS unaccent;

--patients
TRUNCATE TABLE public.patients;

INSERT INTO public.patients (first_name, last_name, gender, medical_number, birth_date, address, phone, email)
VALUES
    ('John', 'Doe', 'M', '123456789', '1985-02-15', '123 Main St, Springfield', '555-1234', 'john.doe@example.com'),
    ('Jane', 'Smith', 'F', '234567890', '1990-06-22', '456 Oak Ave, Springfield', '555-5678', 'jane.smith@example.com'),
    ('Michael', 'Johnson', 'M', '345678901', '1978-11-03', '789 Pine Rd, Springfield', '555-9012', 'michael.johnson@example.com'),
    ('Emily', 'Williams', 'F', '456789012', '1995-07-18', '101 Maple St, Springfield', '555-3456', 'emily.williams@example.com'),
    ('David', 'Brown', 'M', '567890123', '1982-04-09', '202 Elm St, Springfield', '555-7890', 'david.brown@example.com'),
    ('Sarah', 'Jones', 'F', '678901234', '1988-12-30', '303 Birch Ln, Springfield', '555-2345', 'sarah.jones@example.com'),
    ('James', 'Garcia', 'M', '789012345', '1981-05-12', '404 Cedar St, Springfield', '555-6789', 'james.garcia@example.com'),
    ('Linda', 'Martinez', 'F', '890123456', '1992-09-27', '505 Willow Dr, Springfield', '555-1235', 'linda.martinez@example.com'),
    ('Robert', 'Davis', 'M', '901234567', '1976-08-19', '606 Aspen Ave, Springfield', '555-5679', 'robert.davis@example.com'),
    ('Patricia', 'Lopez', 'F', '012345678', '1989-03-05', '707 Hickory St, Springfield', '555-9013', 'patricia.lopez@example.com'),
    ('Anna', 'Lee', 'F', '123456780', '1987-01-23', '808 Magnolia Blvd, Springfield', '555-3457', 'anna.lee@example.com'),
    ('Mark', 'Wilson', 'M', '234567801', '1991-03-14', '909 Redwood Dr, Springfield', '555-7891', 'mark.wilson@example.com'),
    ('Sophia', 'Taylor', 'F', '345678902', '1994-08-30', '1010 Cypress Ct, Springfield', '555-2346', 'sophia.taylor@example.com'),
    ('Chris', 'Evans', 'M', '456789013', '1983-11-10', '1111 Sycamore St, Springfield', '555-6780', 'chris.evans@example.com'),
    ('Olivia', 'White', 'F', '567890124', '1996-05-25', '1212 Fir Ln, Springfield', '555-1236', 'olivia.white@example.com'),
    ('Henry', 'Clark', 'M', '678901245', '1979-09-15', '1313 Cherry St, Springfield', '555-6781', 'henry.clark@example.com'),
    ('Isabella', 'Lewis', 'F', '789012346', '1993-10-07', '1414 Poplar Dr, Springfield', '555-7892', 'isabella.lewis@example.com'),
    ('William', 'Harris', 'M', '890123457', '1986-01-19', '1515 Dogwood Ct, Springfield', '555-1237', 'william.harris@example.com'),
    ('Mia', 'Walker', 'F', '901234568', '1997-06-05', '1616 Chestnut St, Springfield', '555-3458', 'mia.walker@example.com'),
    ('Alexander', 'Young', 'M', '012345679', '1984-04-11', '1717 Ash Ln, Springfield', '555-5678', 'alexander.young@example.com');

-- Adjust the sequence for the patients table
SELECT SETVAL(pg_get_serial_sequence('patients', 'id'), (SELECT MAX(id) FROM public.patients));


select * from patients where medical_number = '789012346';
