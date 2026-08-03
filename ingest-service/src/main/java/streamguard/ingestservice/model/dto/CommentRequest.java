package streamguard.ingestservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest(
        String userId,
        @NotBlank
        @Size(max = 100, message = "Text should not exceed 100.")
        String text
) {}
