package com.example.ncapasparcialii.dtos.response;

import com.example.ncapasparcialii.models.enums.MagicType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicArticleResponse {
    private UUID id;
    private String name;
    private MagicType type;
    private BigDecimal price;
    private UUID providerId;
    private String providerName;
}
