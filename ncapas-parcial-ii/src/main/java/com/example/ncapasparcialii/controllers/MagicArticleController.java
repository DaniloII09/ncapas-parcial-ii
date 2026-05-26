package com.example.ncapasparcialii.controllers;

import com.example.ncapasparcialii.dtos.request.CreateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.request.UpdateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.response.GeneralResponse;
import com.example.ncapasparcialii.models.enums.MagicType;
import com.example.ncapasparcialii.services.impl.MagicArticleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/artefacts")
@RequiredArgsConstructor
public class MagicArticleController {
    private final MagicArticleServiceImpl magicArticleService;

    @PostMapping
    public ResponseEntity<GeneralResponse> createArticle(@Valid @RequestBody CreateMagicArticleRequest request) {
        return buildResponse(
                "Article created successfully",
                HttpStatus.CREATED,
                magicArticleService.createMagicArticle(request)
        );
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getArticlesByFilter(
            @RequestParam MagicType category,
            @RequestParam UUID provider,
            @RequestParam BigDecimal maxPrice
    ) {
        return buildResponse(
                "Articles found",
                HttpStatus.OK,
                magicArticleService.getMagicArticleByTypeAndProviderIdAndMaxPrice(category, provider, maxPrice)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getArticleById(@PathVariable UUID id) {
        return buildResponse(
                "Article found",
                HttpStatus.OK,
                magicArticleService.getMagicArticleById(id)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<GeneralResponse> updateArticle(@PathVariable UUID id, @Valid @RequestBody UpdateMagicArticleRequest request) {
        return buildResponse(
                "Updated article",
                HttpStatus.OK,
                magicArticleService.updateMagicArticle(id, request)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GeneralResponse> deleteArticle(@PathVariable UUID id) {
        magicArticleService.deleteMagicArticleById(id);

        return buildResponse(
                "Article deleted",
                HttpStatus.OK,
                null
        );
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status)
                .body(GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build());
    }
}
