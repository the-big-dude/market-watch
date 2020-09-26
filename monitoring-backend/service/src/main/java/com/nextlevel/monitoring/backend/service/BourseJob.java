package com.nextlevel.monitoring.backend.service;

import com.nextlevel.monitoring.SahamEnt;
import com.nextlevel.monitoring.SahamInfoEnt;
import com.nextlevel.monitoring.SahamInfoRepository;
import com.nextlevel.monitoring.SahamRepository;
import com.nextlevel.monitoring.backend.config.BourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class BourseJob {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BourceConfiguration bourceConfiguration;

    @Autowired
    SahamInfoRepository sahamInfoRepository;

    @Autowired
    SahamRepository sahamRepository;

    @Autowired
    private SahamRepository sahmRepository;

    public BourseJob() {
    }

    @PostConstruct
    void postConstruct(){
        if(!bourceConfiguration.getRunJob().equals("true")) return;

        Thread t = new Thread(() -> {
            while(true){
                getInfoAndSaveIntoDB();
                try {
                    Thread.sleep(900000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void getInfoAndSaveIntoDB() {
        List<SahamEnt> allSahm = sahmRepository.findAll();

        long t1 = System.currentTimeMillis();
        for (SahamEnt sahamEnt : allSahm) {
            try {
                int lastDate = sahamInfoRepository.findMaxDate(sahamEnt);
                int lastTime = sahamInfoRepository.lastInfoReceived(sahamEnt, lastDate);
                String url = "http://www.tsetmc.com/tsev2/data/instinfodata.aspx?i=" + sahamEnt.getCode() + "&c=44+";
                ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

                if (forEntity.getStatusCode() == HttpStatus.OK) {
                    saveIntoDB(forEntity.getBody(), sahamEnt.getCode(), lastDate, lastTime);
                    System.out.println("Saved: " + sahamEnt);
                }
            } catch (Exception e) {
                System.out.println("Error on: " + sahamEnt);
//                e.printStackTrace();
            }
        }
        long t2 = System.currentTimeMillis();

        System.out.println("Time for loading all Sahms is:" + (t2-t1));
    }

    private void saveIntoDB(String body, long sahmCode, int lastDate, int lastTime) {
        body = body.substring(body.indexOf(",A ,") + 4);
        String[] rows = body.split(";");
        SahamInfoEnt sahamInfoEnt = new SahamInfoEnt();
        //row1
        int index = 0;
        String[] split = rows[0].split(",");
        sahamInfoEnt.setAkharinMoamele(convertToInt(split[index++]));
        sahamInfoEnt.setGheymatPaayani(convertToInt(split[index++]));
        sahamInfoEnt.setAvalinGheymat(convertToInt(split[index++]));
        sahamInfoEnt.setGheymatDiruz(convertToInt(split[index++]));
        sahamInfoEnt.setBishtarinGheymat(convertToInt(split[index++]));
        sahamInfoEnt.setKamtarinGheymat(convertToInt(split[index++]));
        sahamInfoEnt.setTedadMoamelat(convertToInt(split[index++]));
        sahamInfoEnt.setHajmMoamelat(convertToLong(split[index++]));
        sahamInfoEnt.setArzeshMoamelat(convertToLong(split[index++]));
//        sahamInfoEnt.setWHAT(convertToInt(split[index++]));
        index++;
        sahamInfoEnt.setMiladiDate(convertToInt(split[index++]));
        sahamInfoEnt.setTime(convertToInt(split[index++]));

        //============================= row 5 ===========================================
        String[] split2 = rows[4].split(",");
        int index2 = 0;
        sahamInfoEnt.setHajmKharidarHaghighi(convertToInt(split2[index2++]));
        sahamInfoEnt.setHajmKharidarHoghoghi(convertToInt(split2[index2++]));
        index2++;
        sahamInfoEnt.setHajmforoushandehHaghighi(convertToInt(split2[index2++]));
        sahamInfoEnt.setHajmforoushandehHoghoghi(convertToInt(split2[index2++]));
        sahamInfoEnt.setTedadKharidarHaghighi(convertToInt(split2[index2++]));
        sahamInfoEnt.setTedadKharidarHoghoghi(convertToInt(split2[index2++]));
        index2++;
        sahamInfoEnt.setTedadforoushandehHaghighi(convertToInt(split2[index2++]));
        sahamInfoEnt.setTedadforoushandehHoghoghi(convertToInt(split2[index2++]));
//=====================================
        sahamInfoEnt.setSahm(sahamRepository.findByCode(sahmCode));
        if(sahamInfoEnt.getMiladiDate() != lastDate ||
                sahamInfoEnt.getTime() != lastTime){
            sahamInfoRepository.save(sahamInfoEnt);
        }
        else{
            System.out.println("Duplicated data for : " + sahmCode);
        }
    }

    private int convertToInt(String s) {
        try{
            return Integer.parseInt(s);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    private long convertToLong(String s) {
        try{
            return Long.parseLong(s);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1L;
        }
    }
}
