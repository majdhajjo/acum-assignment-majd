INSERT INTO students (name, email, address, phone, major, enrollment_date) VALUES
    ('Alex Smith', 'alex@example.com', '123 St', '555-1234', 'Computer Science', '2024-09-01'),
    ('Ameer H', 'ameer@example.com', '456 St', '555-5678', 'Mathematics', '2024-09-02'),
    ('Cristiano Ronaldo', 'ronaldo@example.com', '789 St', '555-8765', 'Biology', '2024-09-03'),
    ('Rami D', 'rami@example.com', '321 St', '555-4321', 'Chemistry', '2024-09-04'),
    ('Jude B', 'jude@example.com', '654 St', '555-9876', 'Physics', '2024-09-05');


INSERT INTO courses (course_name, description, hours, max_size) VALUES
    ('Computer Science', 'An introduction to the fundamentals of computer science.', '3', 30),
    ('Calculus I', 'A study of limits, derivatives, and integrals.', '4', 25),
    ('Biology 101', 'An overview of basic biological concepts.', '3', 35),
    ('General Chemistry', 'An introduction to chemical principles and reactions.', '4', 30),
    ('Physics I', 'An introduction to classical mechanics.', '3', 30);

INSERT INTO lectures (lecture_name, course_id, study, lecture_time, day_of_week) VALUES
    ('Introduction to Computer Science - Lecture 1', (SELECT course_id FROM courses LIMIT 1), 'Computer Science', '10:00:00', 'Monday'),
    ('Calculus I - Lecture 1', (SELECT course_id FROM courses LIMIT 1 OFFSET 1), 'Mathematics', '11:00:00', 'Tuesday'),
    ('Biology 101 - Lecture 1', (SELECT course_id FROM courses LIMIT 1 OFFSET 2), 'Biology', '09:00:00', 'Wednesday'),
    ('General Chemistry - Lecture 1', (SELECT course_id FROM courses LIMIT 1 OFFSET 3), 'Chemistry', '01:00:00', 'Thursday'),
    ('Physics I - Lecture 1', (SELECT course_id FROM courses LIMIT 1 OFFSET 4), 'Physics', '03:00:00', 'Friday');


INSERT INTO student_courses (student_id, course_id) VALUES
((SELECT student_id FROM students WHERE name = 'Alex Smith' LIMIT 1), (SELECT course_id FROM courses WHERE course_name = 'Computer Science' LIMIT 1)),
((SELECT student_id FROM students WHERE name = 'Ameer H' LIMIT 1), (SELECT course_id FROM courses WHERE course_name = 'Calculus I' LIMIT 1)),
((SELECT student_id FROM students WHERE name = 'Cristiano Ronaldo' LIMIT 1), (SELECT course_id FROM courses WHERE course_name = 'Biology 101' LIMIT 1)),
((SELECT student_id FROM students WHERE name = 'Rami D' LIMIT 1), (SELECT course_id FROM courses WHERE course_name = 'General Chemistry' LIMIT 1)),
((SELECT student_id FROM students WHERE name = 'Jude B' LIMIT 1), (SELECT course_id FROM courses WHERE course_name = 'Physics I' LIMIT 1));

