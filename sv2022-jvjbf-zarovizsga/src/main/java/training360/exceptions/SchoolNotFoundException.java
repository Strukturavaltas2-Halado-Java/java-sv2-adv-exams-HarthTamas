package training360.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class SchoolNotFoundException extends AbstractThrowableProblem {
    public SchoolNotFoundException(long id) {
        super(URI.create("/schools/school-not-found"),
                "School not found!",
                Status.NOT_FOUND,
                String.format("School not found with id: %d",id));
    }
}
