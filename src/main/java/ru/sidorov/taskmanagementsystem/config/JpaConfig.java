package ru.sidorov.taskmanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing      // Включает аудит JPA
public class JpaConfig {
}