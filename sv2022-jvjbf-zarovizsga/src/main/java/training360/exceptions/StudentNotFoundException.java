package training360.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class StudentNotFoundException extends AbstractThrowableProblem {
        public StudentNotFoundException(long id) {
            super(URI.create("/student/student-not-found"),
                    "Student not found!",
                    Status.NOT_FOUND,
                    String.format("Student not found with id: %d",id));
        }
}
