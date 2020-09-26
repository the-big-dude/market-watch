package com.nextlevel.monitoring.backend.service.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDtoPage {

    private List<EventDto> events;
    private Long total;

    public EventDtoPage() {
        super();
    }

    public EventDtoPage(List<EventDto> events, Long total) {
        super();
        this.events = events;
        this.total = total;
    }

    public List<EventDto> getEvents() {
        return events;
    }

    public Long getTotal() {
        return total;
    }

}
