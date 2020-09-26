package com.nextlevel.monitoring.backend.service.taharokat.service;

import com.nextlevel.monitoring.SahamEnt;
import com.nextlevel.monitoring.SahamInfoEnt;
import com.nextlevel.monitoring.SahamInfoRepository;
import com.nextlevel.monitoring.SahamRepository;
import com.nextlevel.monitoring.backend.service.taharokat.dto.TaharokatSahamDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaharokatSahamServiceImpl implements TaharokatSahamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaharokatSahamServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SahamInfoRepository sahamInfoRepository;
    @Autowired
    private SahamRepository sahmRepository;


    @Override
    public Page<TaharokatSahamDto> findTaharokatSahamDtos(Pageable pageable) {
        List<SahamEnt> allSahm = sahmRepository.findAll();
        List<TaharokatSahamDto> allSahmSahamInfoEnts = new ArrayList<>();

        for (SahamEnt sahamEnt : allSahm) {
            try{
                int maxDate = sahamInfoRepository.findMaxDate(sahamEnt);

            TaharokatSahamDto taharokatSahamDto = new TaharokatSahamDto();
            taharokatSahamDto.setSahmName(sahamEnt.getName());
            List<SahamInfoEnt> bySahm = sahamInfoRepository.findBySahmAndMiladiDate(sahamEnt, maxDate);

            for (SahamInfoEnt sahamInfoEnt : bySahm) {
                try {
                    float a1 = (float)sahamInfoEnt.getHajmKharidarHaghighi() / sahamInfoEnt.getTedadKharidarHaghighi();
                    float a2 = (float)sahamInfoEnt.getHajmforoushandehHaghighi() / sahamInfoEnt.getTedadforoushandehHaghighi();
                    float v = a1 / a2;
                    float res = (float)Math.round(v * 100) / 100;
                    taharokatSahamDto.getKharidarBForooshandehList().add(res);

                } catch (Exception e) {
                    taharokatSahamDto.getKharidarBForooshandehList().add((float) 0);
                }
                try {
                    float a1 = (float)sahamInfoEnt.getHajmKharidarHaghighi() / sahamInfoEnt.getTedadKharidarHaghighi();
                    float v1 = a1 * (float)sahamInfoEnt.getGheymatPaayani() / 1000000;
                    taharokatSahamDto.getSaranehKharidList().add((float) Math.round(v1));

                } catch (Exception e) {
                    taharokatSahamDto.getSaranehKharidList().add((float) 0);

                }

            }
            allSahmSahamInfoEnts.add(taharokatSahamDto);
            }catch (Exception ignored){

            }
        }
        List<TaharokatSahamDto> taharokatSahamDtos = allSahmSahamInfoEnts.subList(pageable.getPageNumber() * pageable.getPageSize(),
                (pageable.getPageNumber() + 1) * pageable.getPageSize());
        return new PageImpl<>(taharokatSahamDtos, pageable, allSahmSahamInfoEnts.size());

    }

}
