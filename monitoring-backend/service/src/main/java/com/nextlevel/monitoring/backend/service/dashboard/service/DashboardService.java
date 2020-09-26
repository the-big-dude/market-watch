package com.nextlevel.monitoring.backend.service.dashboard.service;

import com.nextlevel.monitoring.backend.service.dashboard.dto.EventDto;

import org.springframework.data.domain.Page;

public interface DashboardService {

    Long getLogEventsCount(String client, String searchText, long fromDate, long toDate);

    // TODO pass the parameters as a dto object, for example SearchParameter
    Page<EventDto> findLogEvents(int pageNumber, int pageSize, String client, String searchText, long fromDate,
            long toDate, String sortColumn, String sortMode);
}
