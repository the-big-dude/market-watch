package com.nextlevel.monitoring;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SahamRepository extends PagingAndSortingRepository<SahamEnt, Long>{

    SahamEnt findByCode(long code);

    List<SahamEnt> findAll();

}
