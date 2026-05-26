package com.example.ncapasparcialii.services.impl;

import com.example.ncapasparcialii.dtos.request.CreateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.request.UpdateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.response.MagicArticleResponse;
import com.example.ncapasparcialii.exceptions.BusinessRuleException;
import com.example.ncapasparcialii.exceptions.DuplicateEntityException;
import com.example.ncapasparcialii.exceptions.EntityNotFoundException;
import com.example.ncapasparcialii.models.entity.MagicArticle;
import com.example.ncapasparcialii.models.entity.MagicProvider;
import com.example.ncapasparcialii.models.enums.MagicType;
import com.example.ncapasparcialii.repositories.MagicArticleRepository;
import com.example.ncapasparcialii.repositories.MagicProviderRepository;
import com.example.ncapasparcialii.services.MagicArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MagicArticleServiceImpl implements MagicArticleService {
    private final MagicArticleRepository magicArticleRepository;
    private final MagicProviderRepository magicProviderRepository;

    @Override
    public MagicArticleResponse createMagicArticle(CreateMagicArticleRequest request) {
        if(magicArticleRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateEntityException("Magic article", "name", request.getName());
        }

        MagicProvider magicProvider = magicProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new BusinessRuleException("A provider is required to create an article"));

        if (request.getType() != magicProvider.getType()) {
            throw new BusinessRuleException("The article type is required to be the same as provider type");
        }

        MagicArticle magicArticle = MagicArticle.builder()
                .name(request.getName())
                .type(request.getType())
                .price(request.getPrice())
                .provider(magicProvider)
                .build();

        return toResponse(magicArticleRepository.save(magicArticle));
    }

    @Override
    public List<MagicArticleResponse> getAllMagicArticles() {
        return magicArticleRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public MagicArticleResponse getMagicArticleById(UUID id) {
        MagicArticle magicArticle = magicArticleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Magic article", id));

        return toResponse(magicArticle);
    }

    @Override
    public List<MagicArticleResponse> getMagicArticleByTypeAndProviderIdAndMaxPrice(MagicType type, UUID providerId, BigDecimal maxPrice) {
        return magicArticleRepository
                .findByTypeAndProviderIdAndPriceLessThanEqual(type, providerId, maxPrice)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public MagicArticleResponse updateMagicArticle(UUID id, UpdateMagicArticleRequest request) {
        MagicArticle magicArticle = magicArticleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Magic article", id));

        MagicProvider magicProvider = magicProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new BusinessRuleException("A provider is required to update an article"));

        if (request.getType() != magicProvider.getType()) {
            throw new BusinessRuleException("The article type is required to be the same as provider type");
        }

        magicArticle.setName(request.getName());
        magicArticle.setType(request.getType());
        magicArticle.setPrice(request.getPrice());
        magicArticle.setProvider(magicProvider);
        return toResponse(magicArticleRepository.save(magicArticle));
    }

    @Override
    public void deleteMagicArticleById(UUID id) {
        MagicArticle magicArticle = magicArticleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Magic article", id));

        magicArticleRepository.delete(magicArticle);
    }

    private MagicArticleResponse toResponse(MagicArticle magicArticle) {
        return MagicArticleResponse.builder()
                .id(magicArticle.getId())
                .name(magicArticle.getName())
                .type(magicArticle.getType())
                .price(magicArticle.getPrice())
                .providerId(magicArticle.getProvider().getId())
                .providerName(magicArticle.getProvider().getName())
                .build();
    }
}
