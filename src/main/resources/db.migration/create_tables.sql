CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255),
    phone VARCHAR(20),
    major VARCHAR(100),
    enrollment_date DATE NOT NULL
);

CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(255) NOT NULL,
    description TEXT,
    hours VARCHAR(10),
    max_size INT
);

CREATE TABLE lectures (
    lecture_id INT PRIMARY KEY AUTO_INCREMENT,
    lecture_name VARCHAR(255) NOT NULL,
    course_id INT,
    study VARCHAR(100),
    lecture_time TIME,
    day_of_week VARCHAR(10),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

CREATE TABLE student_courses (
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);