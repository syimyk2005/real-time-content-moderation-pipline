package streamguard.ingestservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentDto(
        String userId,

        @NotBlank
        @Size(max = 100, message = "Text should not exceed 100.")
        String text
) {}
