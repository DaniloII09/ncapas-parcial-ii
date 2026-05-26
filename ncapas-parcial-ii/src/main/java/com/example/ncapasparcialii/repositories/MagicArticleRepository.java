package com.example.ncapasparcialii.repositories;

import com.example.ncapasparcialii.models.entity.MagicArticle;
import com.example.ncapasparcialii.models.enums.MagicType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MagicArticleRepository extends JpaRepository<MagicArticle, UUID> {
    boolean existsByNameIgnoreCase(String name);
    List<MagicArticle> findByTypeAndProviderIdAndPriceLessThanEqual(MagicType type, UUID providerId, BigDecimal price);
}
