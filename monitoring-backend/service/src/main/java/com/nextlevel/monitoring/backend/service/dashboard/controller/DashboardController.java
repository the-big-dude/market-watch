package com.nextlevel.monitoring.backend.service.dashboard.controller;

import com.nextlevel.monitoring.backend.service.dashboard.dto.EventDto;
import com.nextlevel.monitoring.backend.service.dashboard.dto.EventDtoPage;
import com.nextlevel.monitoring.backend.service.dashboard.service.DashboardService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(produces = "application/json")
    public EventDtoPage getLogEvents(Pageable pageable, @RequestParam("client") String client,
            @RequestParam("searchText") String searchText, @RequestParam("fromDate") Long fromDate,
            @RequestParam("toDate") Long toDate, @RequestParam("sortColumn") String sortColumn,
            @RequestParam("sortMode") String sortMode) {

        LOGGER.debug(
                "DashboardController.getEvents pageNumber:{} , pageSize:{} , client:{} , "
                        + "searchText:{} , fromDate:{} , toDate:{} , sortColumn:{} , sortMode:{} ",
                pageable.getPageNumber(), pageable.getPageSize(), client, searchText, fromDate, toDate, sortColumn,
                sortMode);

        Page<EventDto> page = dashboardService.findLogEvents(pageable.getPageNumber(), pageable.getPageSize(), client,
                searchText, fromDate, toDate, sortColumn, sortMode);
        assert page != null;
        LOGGER.debug("getEvents size:{}", page.getContent() == null ? 0 : page.getContent().size());
        return new EventDtoPage(page.getContent(), new Long(page.getTotalElements()));

    }

    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[] { new FmtDate(" dd/MM/yyyy HH:mm:ss"), // date
                new Optional(), // logLevel
                new Optional(), // deviceId
                new Optional(), // eventId
                new Optional(), // outcome
                new Optional() // messageFull
        };

        return processors;
    }

    @GetMapping(value = "/downloadCSV")
    public byte[] downloadFile(@RequestParam("fromIndex") Long fromIndex, @RequestParam("toIndex") Long toIndex,
            @RequestParam("client") String client, @RequestParam("searchText") String searchText,
            @RequestParam("fromDate") Long fromDate, @RequestParam("toDate") Long toDate,
            @RequestParam("sortColumn") String sortColumn, @RequestParam("sortMode") String sortMode) {

        LOGGER.debug(
                "DashboardController.getEvents fromIndex:{} , toIndex:{} , client:{} , "
                        + "searchText:{} , fromDate:{} , toDate:{} , sortColumn:{} , sortMode:{} ",
                fromIndex, toIndex, client, searchText, fromDate, toDate, sortColumn, sortMode);
        try {
            final Long count = dashboardService.getLogEventsCount(client, searchText, fromDate, toDate);
            Page<EventDto> page = dashboardService.findLogEvents(0, count.intValue(), client, searchText, fromDate,
                    toDate, sortColumn, sortMode);
            List<EventDto> list = page.getContent().subList(fromIndex.intValue() - 1, toIndex.intValue());
            String[] header = { "date", "logLevel", "deviceId", "eventId", "outcome", "messageFull" };
            String[] headerTitle = { "Datum", " Log-Level", "Gateway-ID", " Event-ID", " Ergebnis", "Meldung" };
            StringWriter writer = new StringWriter();
            ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
            csvWriter.writeHeader(headerTitle);
            for (EventDto eventDto : list) {
                if (eventDto != null && eventDto.getMessageFull() != null && !eventDto.getMessageFull().contains(",")) {
                    // The "write" method of csvWriter adds " char if there is comma in the string.
                    // So here we add " to all other strings to make them look alike.
                    final String messageFull = '"' + eventDto.getMessageFull() + '"';
                    eventDto.setMessageFull(messageFull);
                }
                csvWriter.write(eventDto, header, getProcessors());
            }
            csvWriter.close();// FIXME if not get executed?!
            String originalString = writer.toString();
            if (StringUtils.isBlank(originalString)) {
                LOGGER.error("no data for export.");
                throw new RuntimeException("no data for export.");
            }
            // when we added " char at the start and end of every messageFull, csvWrite
            // converts them to """.
            // So here we replace """ with ".
            String replacedString = originalString.replaceAll("\"\"\"", "\"");
            return replacedString.getBytes("UTF-8");
        } catch (Exception e) {
            LOGGER.error("Downloading CSV File failed!", e);
            throw new RuntimeException("Downloading CSV File failed!", e);
        }
    }

    @GetMapping("/count")
    public Long getCount(@RequestParam("client") String client, @RequestParam("searchText") String searchText,
            @RequestParam("fromDate") Long fromDate, @RequestParam("toDate") Long toDate) {
        LOGGER.trace("calling getCount");
        return dashboardService.getLogEventsCount(client, searchText, fromDate, toDate);
    }

}
