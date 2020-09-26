package com.nextlevel.monitoring.backend.service.taharokat.service;

import com.nextlevel.monitoring.backend.service.taharokat.dto.TaharokatSahamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaharokatSahamService {
    Page<TaharokatSahamDto> findTaharokatSahamDtos(Pageable pageable);
}
