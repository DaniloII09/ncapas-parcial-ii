package com.example.ncapasparcialii.dtos.request;

import com.example.ncapasparcialii.models.enums.MagicType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMagicProviderRequest {
    private String name;
    private MagicType type;
}
