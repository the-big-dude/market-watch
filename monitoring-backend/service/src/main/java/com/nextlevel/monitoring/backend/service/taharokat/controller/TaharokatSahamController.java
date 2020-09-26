package com.nextlevel.monitoring.backend.service.taharokat.controller;

import com.nextlevel.monitoring.backend.service.taharokat.dto.TaharokatSahamDto;
import com.nextlevel.monitoring.backend.service.taharokat.service.TaharokatSahamService;
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
@RequestMapping("/api/taharokat")
public class TaharokatSahamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaharokatSahamController.class);

    @Autowired
    private TaharokatSahamService taharokatSahamService;

    @GetMapping(produces = "application/json")
    public Page<TaharokatSahamDto> getTharokat(Pageable pageable) {


        Page<TaharokatSahamDto> page = taharokatSahamService.findTaharokatSahamDtos(pageable);
        assert page != null;
        return page;

    }

//    @GetMapping(value = "/downloadCSV")
//    public byte[] downloadFile(@RequestParam("fromIndex") Long fromIndex, @RequestParam("toIndex") Long toIndex,
//            @RequestParam("client") String client, @RequestParam("searchText") String searchText,
//            @RequestParam("fromDate") Long fromDate, @RequestParam("toDate") Long toDate,
//            @RequestParam("sortColumn") String sortColumn, @RequestParam("sortMode") String sortMode) {
//
//        LOGGER.debug(
//                "DashboardController.getEvents fromIndex:{} , toIndex:{} , client:{} , "
//                        + "searchText:{} , fromDate:{} , toDate:{} , sortColumn:{} , sortMode:{} ",
//                fromIndex, toIndex, client, searchText, fromDate, toDate, sortColumn, sortMode);
//        try {
//            final Long count = taharokatSahamService.getLogEventsCount(client, searchText, fromDate, toDate);
//            Page<TaharokatSahamDto> page = taharokatSahamService.findLogEvents(0, count.intValue(), client, searchText, fromDate,
//                    toDate, sortColumn, sortMode);
//            List<TaharokatSahamDto> list = page.getContent().subList(fromIndex.intValue() - 1, toIndex.intValue());
//            String[] header = { "date", "logLevel", "deviceId", "eventId", "outcome", "messageFull" };
//            String[] headerTitle = { "Datum", " Log-Level", "Gateway-ID", " Event-ID", " Ergebnis", "Meldung" };
//            StringWriter writer = new StringWriter();
//            ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
//            csvWriter.writeHeader(headerTitle);
//            for (TaharokatSahamDto TaharokatSahamDto : list) {
//                if (TaharokatSahamDto != null && TaharokatSahamDto.getMessageFull() != null && !TaharokatSahamDto.getMessageFull().contains(",")) {
//                    // The "write" method of csvWriter adds " char if there is comma in the string.
//                    // So here we add " to all other strings to make them look alike.
//                    final String messageFull = '"' + TaharokatSahamDto.getMessageFull() + '"';
//                    TaharokatSahamDto.setMessageFull(messageFull);
//                }
//                csvWriter.write(TaharokatSahamDto, header, getProcessors());
//            }
//            csvWriter.close();// FIXME if not get executed?!
//            String originalString = writer.toString();
//            if (StringUtils.isBlank(originalString)) {
//                LOGGER.error("no data for export.");
//                throw new RuntimeException("no data for export.");
//            }
//            // when we added " char at the start and end of every messageFull, csvWrite
//            // converts them to """.
//            // So here we replace """ with ".
//            String replacedString = originalString.replaceAll("\"\"\"", "\"");
//            return replacedString.getBytes("UTF-8");
//        } catch (Exception e) {
//            LOGGER.error("Downloading CSV File failed!", e);
//            throw new RuntimeException("Downloading CSV File failed!", e);
//        }
//    }
//
//    @GetMapping("/count")
//    public Long getCount(@RequestParam("client") String client, @RequestParam("searchText") String searchText,
//            @RequestParam("fromDate") Long fromDate, @RequestParam("toDate") Long toDate) {
//        LOGGER.trace("calling getCount");
//        return taharokatSahamService.getLogEventsCount(client, searchText, fromDate, toDate);
//    }

}
