package com.example.ncapasparcialii.dtos.request;

import com.example.ncapasparcialii.models.entity.MagicProvider;
import com.example.ncapasparcialii.models.enums.MagicType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMagicArticleRequest {
    @NotBlank(message = "Magic article name cannot be empty")
    private String name;

    @NotNull(message = "Magic article type cannot be empty")
    private MagicType type;

    @NotNull(message = "Magic article price is required")
    @Positive(message = "Magic article price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Invalid price format")
    private BigDecimal price;

    @NotNull(message = "Magic provider id is required")
    private UUID providerId;
}
