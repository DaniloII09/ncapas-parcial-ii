package com.example.ncapasparcialii.services;

import com.example.ncapasparcialii.dtos.request.CreateMagicProviderRequest;
import com.example.ncapasparcialii.dtos.request.UpdateMagicProviderRequest;
import com.example.ncapasparcialii.dtos.response.MagicProviderResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MagicProviderService {
    MagicProviderResponse createMagicProvider(CreateMagicProviderRequest request);
    List<MagicProviderResponse> getAllMagicProviders();
    MagicProviderResponse getMagicProviderById(UUID id);
    MagicProviderResponse updateMagicProvider(UUID id, UpdateMagicProviderRequest request);
    void deleteMagicProviderById(UUID id);
}
