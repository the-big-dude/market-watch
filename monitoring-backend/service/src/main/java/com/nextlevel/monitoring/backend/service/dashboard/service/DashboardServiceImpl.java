package com.nextlevel.monitoring.backend.service.dashboard.service;

import com.nextlevel.monitoring.backend.config.Configuration;
import com.nextlevel.monitoring.backend.core.rest.RestHelper;
import com.nextlevel.monitoring.backend.core.rest.RestResponsePage;
import com.nextlevel.monitoring.backend.service.dashboard.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DashboardServiceImpl implements DashboardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Configuration configuration;

    @Override
    public Page<EventDto> findLogEvents(int pageNumber, int pageSize, String client, String searchText, long fromDate,
            long toDate, String sortColumn, String sortMode) {
        LOGGER.debug(
                "findLogEvents pageNumber:{} , pageSize:{} , client:{} , "
                        + "searchText:{} , fromDate:{} , toDate:{} , sortColumn:{} , sortMode:{} ",
                pageNumber, pageSize, client, searchText, fromDate, toDate, sortColumn, sortMode);
        try {

            final HttpHeaders headers = RestHelper.createHttpHeaders();
            final HttpEntity<String> request = new HttpEntity<>(headers);

            String reportingUrl = String.format(configuration.getReportingUrl(), client);
            reportingUrl += "?reportFrom=" + getDateString(fromDate) + "&reportUntil=" + getDateString(toDate)
                    + "&page=" + pageNumber + "&size=" + pageSize + searchText;

            LOGGER.debug("Retrieving the logs from server on url {}", reportingUrl);

            ResponseEntity<RestResponsePage<EventDto>> response = restTemplate.exchange(reportingUrl, HttpMethod.GET,
                    request, new ParameterizedTypeReference<RestResponsePage<EventDto>>() {
                    });
            final Page<EventDto> result = (Page<EventDto>) response.getBody();
            return result;
        } catch (Exception e) {
            LOGGER.warn("Failed to retrieve logs!", e);
            return null;
        }
    }

    private String getDateString(long date) {
        // TODO use java 8 DateTimeFormatter, SimpleDateFormat is a error-prone class
        String pattern = "yyyy-MM-dd'T'HH:mm:ssZ";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String result = dateFormat.format(new Date(date));
        result = result.substring(0, 19);
        return result;
    }

    @Override
    public Long getLogEventsCount(String client, String searchText, long fromDate, long toDate) {
        LOGGER.debug(
            "getLogEventsCount  client:{} , "
                    + "searchText:{} , fromDate:{} , toDate:{} ",
             client, searchText, fromDate, toDate);
        try {
            final HttpHeaders headers = RestHelper.createHttpHeaders();
            final HttpEntity<String> request = new HttpEntity<>(headers);
            String reportingUrl = String.format(configuration.getReportingUrl(), client);
            reportingUrl += "?reportFrom=" + getDateString(fromDate) + "&reportUntil=" + getDateString(toDate)
                    + "&page=1&size=1" + searchText;
            LOGGER.debug("Retrieving the logs from server on url {}", reportingUrl);
            ResponseEntity<RestResponsePage<EventDto>> response = restTemplate.exchange(reportingUrl, HttpMethod.GET,
                    request, new ParameterizedTypeReference<RestResponsePage<EventDto>>() {
                    });
            final PageImpl<EventDto> page = response.getBody();
            if (page == null || page.getContent() == null) {
                return 0L;
            }
            return page.getTotalElements();
        } catch (Exception e) {
            LOGGER.warn("Failed to retrieve logs count!", e);
            return 0L;
        }
    }

}
