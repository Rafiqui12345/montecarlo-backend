package com.montecarlo.repository;

import com.montecarlo.entity.ConfiguracionClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfiguracionClubRepository extends JpaRepository<ConfiguracionClub, Long> {

    Optional<ConfiguracionClub> findByActivoTrue();

}