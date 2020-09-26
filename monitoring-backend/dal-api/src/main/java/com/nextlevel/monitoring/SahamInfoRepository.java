package com.nextlevel.monitoring;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SahamInfoRepository extends PagingAndSortingRepository<SahamInfoEnt, Long>{

    List<SahamInfoEnt> findBySahmAndMiladiDate(SahamEnt sahamEnt, int date);

    @Query("select max(s.miladiDate) from SahamInfoEnt s  where s.sahm = ?1 ")
    int findMaxDate(SahamEnt sahamEnt);

    @Query("select max(s.time) from SahamInfoEnt s  where s.sahm = ?1 and s.miladiDate = ?2")
    int lastInfoReceived(SahamEnt sahamEnt, int miladiDate);
}
