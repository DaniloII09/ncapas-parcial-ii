package com.example.ncapasparcialii.dtos.request;

import com.example.ncapasparcialii.models.enums.MagicType;
import jakarta.validation.constraints.Digits;
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
public class UpdateMagicArticleRequest {

    private String name;

    private MagicType type;

    @Positive(message = "Magic article price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Invalid price format")
    private BigDecimal price;

    private UUID providerId;
}
