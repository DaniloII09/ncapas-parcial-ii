package com.example.ncapasparcialii.services.impl;

import com.example.ncapasparcialii.dtos.request.CreateMagicProviderRequest;
import com.example.ncapasparcialii.dtos.request.UpdateMagicProviderRequest;
import com.example.ncapasparcialii.dtos.response.MagicProviderResponse;
import com.example.ncapasparcialii.exceptions.BusinessRuleException;
import com.example.ncapasparcialii.exceptions.EntityNotFoundException;
import com.example.ncapasparcialii.models.entity.MagicProvider;
import com.example.ncapasparcialii.repositories.MagicArticleRepository;
import com.example.ncapasparcialii.repositories.MagicProviderRepository;
import com.example.ncapasparcialii.services.MagicProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MagicProviderServiceImpl implements MagicProviderService {
    private final MagicProviderRepository magicProviderRepository;
    private final MagicArticleRepository magicArticleRepository;

    @Override
    public MagicProviderResponse createMagicProvider(CreateMagicProviderRequest request) {
        MagicProvider magicProvider = MagicProvider
                .builder()
                .name(request.getName())
                .type(request.getType())
                .build();

        return toResponse(magicProviderRepository.save(magicProvider));
    }

    @Override
    public List<MagicProviderResponse> getAllMagicProviders() {
        return magicProviderRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public MagicProviderResponse getMagicProviderById(UUID id) {
        MagicProvider magicProvider = magicProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Magic provider", id));
        return toResponse(magicProvider);
    }

    @Override
    public MagicProviderResponse updateMagicProvider(UUID id, UpdateMagicProviderRequest request) {
        MagicProvider magicProvider = magicProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Magic provider", id));

        magicProvider.setName(request.getName());
        magicProvider.setType(request.getType());

        return toResponse(magicProviderRepository.save(magicProvider));
    }

    @Override
    public void deleteMagicProviderById(UUID id) {
        MagicProvider magicProvider = magicProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Magic provider", id));

        if(magicArticleRepository.findByProviderId(id) != null) {
            throw new BusinessRuleException("Provider has articles, delete the articles first or reasign them");
        }

        magicProviderRepository.delete(magicProvider);
    }

    private MagicProviderResponse toResponse(MagicProvider magicProvider) {
        return MagicProviderResponse.builder()
                .id(magicProvider.getId())
                .name(magicProvider.getName())
                .type(magicProvider.getType())
                .build();
    }
}
