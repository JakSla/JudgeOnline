package pl.ejaksla.onlinejudge.presentation;

import java.util.StringJoiner;
import static com.google.common.base.Preconditions.checkArgument;

public class EvaluationResponseDTO {
    /**
     * Response status
     */
    public enum Status {
        OK,
        ERROR
    }

    private Status status;
    private String message;

    /**
     * @param status  the status, required
     * @param message the message, required
     */
    EvaluationResponseDTO(final Status status, final String message) {
        checkArgument(status != null, "status is required");
        checkArgument(message != null, "message is required");
        this.status = status;
        this.message = message;
    }

    /**
     * @return the status, not null
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return the message, not null
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EvaluationResponseDTO.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("message='" + message + "'")
                .toString();
    }
}
