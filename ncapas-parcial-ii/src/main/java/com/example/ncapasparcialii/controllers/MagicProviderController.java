package com.example.ncapasparcialii.controllers;

import com.example.ncapasparcialii.dtos.request.CreateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.request.CreateMagicProviderRequest;
import com.example.ncapasparcialii.dtos.request.UpdateMagicArticleRequest;
import com.example.ncapasparcialii.dtos.request.UpdateMagicProviderRequest;
import com.example.ncapasparcialii.dtos.response.GeneralResponse;
import com.example.ncapasparcialii.models.enums.MagicType;
import com.example.ncapasparcialii.repositories.MagicArticleRepository;
import com.example.ncapasparcialii.services.impl.MagicProviderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class MagicProviderController {
    private final MagicProviderServiceImpl magicProviderService;

    @PostMapping
    public ResponseEntity<GeneralResponse> createProvider(@Valid @RequestBody CreateMagicProviderRequest request) {
        return buildResponse(
                "Provider created successfully",
                HttpStatus.CREATED,
                magicProviderService.createMagicProvider(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getProviderById(@PathVariable UUID id) {
        return buildResponse(
                "Provider found",
                HttpStatus.OK,
                magicProviderService.getMagicProviderById(id)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<GeneralResponse> updateProvider(@PathVariable UUID id, @Valid @RequestBody UpdateMagicProviderRequest request) {
        return buildResponse(
                "Updated provider",
                HttpStatus.OK,
                magicProviderService.updateMagicProvider(id, request)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GeneralResponse> deleteProvider(@PathVariable UUID id) {
        magicProviderService.deleteMagicProviderById(id);

        return buildResponse(
                "Provider deleted",
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
