package com.example.ncapasparcialii.dtos.request;

import com.example.ncapasparcialii.models.enums.MagicType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMagicProviderRequest {
    @NotBlank(message = "Magic provider name cannot be empty")
    private String name;

    @NotNull(message = "Magic provider type cannot be empty")
    private MagicType type;
}
