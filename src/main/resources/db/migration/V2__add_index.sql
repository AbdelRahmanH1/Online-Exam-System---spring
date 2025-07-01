CREATE INDEX idx_exam_created_by ON exam(created_by);
CREATE INDEX idx_question_exam_id ON question(exam_id);
CREATE INDEX idx_choices_question_id ON question_choices(question_id);
CREATE INDEX idx_attempt_exam_id ON exam_attempt(exam_id);
CREATE INDEX idx_attempt_student_id ON exam_attempt(student_id);
CREATE INDEX idx_answer_attempt_id ON student_answer(attempt_id);