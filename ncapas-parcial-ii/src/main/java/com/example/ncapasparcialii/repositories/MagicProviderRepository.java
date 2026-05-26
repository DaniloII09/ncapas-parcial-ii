package com.example.ncapasparcialii.repositories;

import com.example.ncapasparcialii.models.entity.MagicProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MagicProviderRepository extends JpaRepository<MagicProvider, UUID> {

}
