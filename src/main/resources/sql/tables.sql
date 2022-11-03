CREATE TABLE IF NOT EXISTS person (
    person_id bigint GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT 50) PRIMARY KEY,
    login varchar(50) NOT NULL UNIQUE,
    password text NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    about varchar(2000),
    role varchar(50),
    img text
    );


CREATE TABLE IF NOT EXISTS employee (
    employee_id bigint GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT 50) PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    git varchar(50),
    linkedin varchar(50),
    person_id bigint REFERENCES person(person_id) ON DELETE CASCADE UNIQUE
    );

CREATE TABLE IF NOT EXISTS employer (
    employer_id bigint GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT 50) PRIMARY KEY,
    company_name varchar(50) NOT NULL UNIQUE,
    employees_number int,
    person_id bigint REFERENCES person(person_id) ON DELETE CASCADE UNIQUE
    );

CREATE TABLE IF NOT EXISTS resume (
    resume_id bigint GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT 50) PRIMARY KEY,
    job_title varchar(50) NOT NULL,
    salary_expectations int CHECK(salary_expectations > 0) NOT NULL,
    description varchar(2000) NOT NULL,
    creation_datetime timestamptz NOT NULL,
    pdf_resume text,
    employee_id bigint REFERENCES employee(employee_id) ON DELETE CASCADE UNIQUE,
    experience int DEFAULT 0 CHECK(experience >= 0),
    is_active BOOLEAN DEFAULT true
    );


CREATE TABLE IF NOT EXISTS vacancy (
    vacancy_id bigint GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT 50) PRIMARY KEY,
    title varchar(50) NOT NULL,
    salary int CHECK(salary > 0),
    creation_datetime timestamptz NOT NULL,
    description varchar(2000) NOT NULL,
    experience int DEFAULT 0 CHECK(experience >= 0),
    employer_id bigint REFERENCES employer(employer_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS chat (
    chat_id bigint GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT 50) PRIMARY KEY,
    employer_id bigint REFERENCES employer(employer_id) ON DELETE CASCADE NOT NULL,
    employee_id bigint REFERENCES employee(employee_id) ON DELETE CASCADE NOT NULL,
    vacancy_id bigint REFERENCES vacancy(vacancy_id) ON DELETE CASCADE NOT NULL UNIQUE,
    creation_datetime timestamptz NOT NULL,
    UNIQUE(employer_id, employee_id)

    );

CREATE TABLE IF NOT EXISTS message(
    message_id bigint GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT 50) PRIMARY KEY,
    from_whom varchar(50) NOT NULL,
    creation_datetime timestamptz NOT NULL,
    message_body varchar(500) NOT NULL
    );
