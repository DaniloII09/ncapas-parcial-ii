package com.example.ncapasparcialii.services;

import com.example.ncapasparcialii.dtos.request.CreateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.request.UpdateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.response.MagicArticleResponse;
import com.example.ncapasparcialii.models.enums.MagicType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MagicArticleService {
    MagicArticleResponse createMagicArticle(CreateMagicArticleRequest request);
    List<MagicArticleResponse> getAllMagicArticles();
    MagicArticleResponse getMagicArticleById(UUID id);
    List<MagicArticleResponse> getMagicArticleByTypeAndProviderIdAndMaxPrice(MagicType type, UUID providerId, BigDecimal maxPrice);
    MagicArticleResponse updateMagicArticle(UUID id, UpdateMagicArticleRequest request);
    void deleteMagicArticleById(UUID id);

}
