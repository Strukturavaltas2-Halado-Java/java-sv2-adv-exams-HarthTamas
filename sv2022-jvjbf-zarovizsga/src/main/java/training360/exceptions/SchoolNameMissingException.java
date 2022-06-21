package training360.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class SchoolNameMissingException extends AbstractThrowableProblem {
    public SchoolNameMissingException() {
        super(URI.create("/schools/school-must-have-name"),
                "School must have name!",
                Status.BAD_REQUEST,
                String.format("School must have name!"));
    }
}

