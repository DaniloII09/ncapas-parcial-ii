package com.example.ncapasparcialii.repositories;

import com.example.ncapasparcialii.models.entity.MagicProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MagicProviderRepository extends JpaRepository<MagicProvider, UUID> {

}
