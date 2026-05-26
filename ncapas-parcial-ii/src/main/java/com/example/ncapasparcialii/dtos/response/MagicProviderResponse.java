package com.example.ncapasparcialii.dtos.response;

import com.example.ncapasparcialii.models.enums.MagicType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MagicProviderResponse {
    private UUID id;
    private String name;
    private MagicType type;
}
