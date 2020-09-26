package com.nextlevel.monitoring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "saham_info")
public class SahamInfoEnt extends BaseDataEnt {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_sahm")
    SahamEnt sahm;

    int AkharinMoamele;
    int gheymatPaayani;
    int avalinGheymat;
    int gheymatDiruz;
    int bishtarinGheymat;
    int kamtarinGheymat;
    int tedadMoamelat;
    long hajmMoamelat;
    long arzeshMoamelat;
    int shamsiDate;
    int miladiDate;
    int time;
    int orderKharid1;
    int orderKharid2;
    int orderKharid3;
    int orderKharid4;
    int orderKharid5;
    int HajmKharid1;
    int HajmKharid2;
    int HajmKharid3;
    int HajmKharid4;
    int HajmKharid;
    int tedadKharidar1;
    int tedadKharidar2;
    int tedadKharidar3;
    int tedadKharidar4;
    int tedadKharidar5;
    int orderForoush1;
    int orderForoush2;
    int orderForoush3;
    int orderForoush4;
    int orderForoush5;
    int hajmForoush1;
    int hajmForoush2;
    int hajmForoush3;
    int hajmForoush4;
    int hajmForoush5;
    int tedadforoush1;
    int tedadforoush2;
    int tedadforoush3;
    int tedadforoush4;
    int tedadforoush5;
    int tedadKharidarHaghighi;
    int hajmKharidarHaghighi;
    int arzeshKharidarHaghighi;
    int tedadKharidarHoghoghi;
    int hajmKharidarHoghoghi;
    int arzeshKharidarHoghoghi;
    int tedadforoushandehHaghighi;
    int hajmforoushandehHaghighi;
    int arzeshforoushandehHaghighi;
    int tedadforoushandehHoghoghi;
    int hajmforoushandehHoghoghi;
    int arzeshforoushandehHoghoghi;
    int gheymatMojazMin;
    int gheymatMojazMax;
    int hajmMabna;
    int arzeshBazar;
    int tedadSaham;

    public int getAkharinMoamele() {
        return AkharinMoamele;
    }

    public void setAkharinMoamele(int akharinMoamele) {
        AkharinMoamele = akharinMoamele;
    }

    public int getGheymatPaayani() {
        return gheymatPaayani;
    }

    public void setGheymatPaayani(int gheymatPaayani) {
        this.gheymatPaayani = gheymatPaayani;
    }

    public int getAvalinGheymat() {
        return avalinGheymat;
    }

    public void setAvalinGheymat(int avalinGheymat) {
        this.avalinGheymat = avalinGheymat;
    }

    public int getGheymatDiruz() {
        return gheymatDiruz;
    }

    public void setGheymatDiruz(int gheymatDiruz) {
        this.gheymatDiruz = gheymatDiruz;
    }

    public int getBishtarinGheymat() {
        return bishtarinGheymat;
    }

    public void setBishtarinGheymat(int bishtarinGheymat) {
        this.bishtarinGheymat = bishtarinGheymat;
    }

    public int getKamtarinGheymat() {
        return kamtarinGheymat;
    }

    public void setKamtarinGheymat(int kamtarinPaayani) {
        this.kamtarinGheymat = kamtarinPaayani;
    }

    public int getTedadMoamelat() {
        return tedadMoamelat;
    }

    public void setTedadMoamelat(int tedadMoamelat) {
        this.tedadMoamelat = tedadMoamelat;
    }

    public long getHajmMoamelat() {
        return hajmMoamelat;
    }

    public void setHajmMoamelat(long hajmMoamelat) {
        this.hajmMoamelat = hajmMoamelat;
    }

    public long getArzeshMoamelat() {
        return arzeshMoamelat;
    }

    public void setArzeshMoamelat(long arzeshMoamelat) {
        this.arzeshMoamelat = arzeshMoamelat;
    }

    public int getShamsiDate() {
        return shamsiDate;
    }

    public void setShamsiDate(int shamsiDate) {
        this.shamsiDate = shamsiDate;
    }

    public int getMiladiDate() {
        return miladiDate;
    }

    public void setMiladiDate(int miladiDate) {
        this.miladiDate = miladiDate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getOrderKharid1() {
        return orderKharid1;
    }

    public void setOrderKharid1(int orderKharid1) {
        this.orderKharid1 = orderKharid1;
    }

    public int getOrderKharid2() {
        return orderKharid2;
    }

    public void setOrderKharid2(int orderKharid2) {
        this.orderKharid2 = orderKharid2;
    }

    public int getOrderKharid3() {
        return orderKharid3;
    }

    public void setOrderKharid3(int orderKharid3) {
        this.orderKharid3 = orderKharid3;
    }

    public int getOrderKharid4() {
        return orderKharid4;
    }

    public void setOrderKharid4(int orderKharid4) {
        this.orderKharid4 = orderKharid4;
    }

    public int getOrderKharid5() {
        return orderKharid5;
    }

    public void setOrderKharid5(int orderKharid5) {
        this.orderKharid5 = orderKharid5;
    }

    public int getHajmKharid1() {
        return HajmKharid1;
    }

    public void setHajmKharid1(int hajmKharid1) {
        HajmKharid1 = hajmKharid1;
    }

    public int getHajmKharid2() {
        return HajmKharid2;
    }

    public void setHajmKharid2(int hajmKharid2) {
        HajmKharid2 = hajmKharid2;
    }

    public int getHajmKharid3() {
        return HajmKharid3;
    }

    public void setHajmKharid3(int hajmKharid3) {
        HajmKharid3 = hajmKharid3;
    }

    public int getHajmKharid4() {
        return HajmKharid4;
    }

    public void setHajmKharid4(int hajmKharid4) {
        HajmKharid4 = hajmKharid4;
    }

    public int getHajmKharid() {
        return HajmKharid;
    }

    public void setHajmKharid(int hajmKharid) {
        HajmKharid = hajmKharid;
    }

    public int getTedadKharidar1() {
        return tedadKharidar1;
    }

    public void setTedadKharidar1(int tedadKharidar1) {
        this.tedadKharidar1 = tedadKharidar1;
    }

    public int getTedadKharidar2() {
        return tedadKharidar2;
    }

    public void setTedadKharidar2(int tedadKharidar2) {
        this.tedadKharidar2 = tedadKharidar2;
    }

    public int getTedadKharidar3() {
        return tedadKharidar3;
    }

    public void setTedadKharidar3(int tedadKharidar3) {
        this.tedadKharidar3 = tedadKharidar3;
    }

    public int getTedadKharidar4() {
        return tedadKharidar4;
    }

    public void setTedadKharidar4(int tedadKharidar4) {
        this.tedadKharidar4 = tedadKharidar4;
    }

    public int getTedadKharidar5() {
        return tedadKharidar5;
    }

    public void setTedadKharidar5(int tedadKharidar5) {
        this.tedadKharidar5 = tedadKharidar5;
    }

    public int getOrderForoush1() {
        return orderForoush1;
    }

    public void setOrderForoush1(int orderForoush1) {
        this.orderForoush1 = orderForoush1;
    }

    public int getOrderForoush2() {
        return orderForoush2;
    }

    public void setOrderForoush2(int orderForoush2) {
        this.orderForoush2 = orderForoush2;
    }

    public int getOrderForoush3() {
        return orderForoush3;
    }

    public void setOrderForoush3(int orderForoush3) {
        this.orderForoush3 = orderForoush3;
    }

    public int getOrderForoush4() {
        return orderForoush4;
    }

    public void setOrderForoush4(int orderForoush4) {
        this.orderForoush4 = orderForoush4;
    }

    public int getOrderForoush5() {
        return orderForoush5;
    }

    public void setOrderForoush5(int orderForoush5) {
        this.orderForoush5 = orderForoush5;
    }

    public int getHajmForoush1() {
        return hajmForoush1;
    }

    public void setHajmForoush1(int hajmForoush1) {
        this.hajmForoush1 = hajmForoush1;
    }

    public int getHajmForoush2() {
        return hajmForoush2;
    }

    public void setHajmForoush2(int hajmForoush2) {
        this.hajmForoush2 = hajmForoush2;
    }

    public int getHajmForoush3() {
        return hajmForoush3;
    }

    public void setHajmForoush3(int hajmForoush3) {
        this.hajmForoush3 = hajmForoush3;
    }

    public int getHajmForoush4() {
        return hajmForoush4;
    }

    public void setHajmForoush4(int hajmForoush4) {
        this.hajmForoush4 = hajmForoush4;
    }

    public int getHajmForoush5() {
        return hajmForoush5;
    }

    public void setHajmForoush5(int hajmForoush5) {
        this.hajmForoush5 = hajmForoush5;
    }

    public int getTedadforoush1() {
        return tedadforoush1;
    }

    public void setTedadforoush1(int tedadforoush1) {
        this.tedadforoush1 = tedadforoush1;
    }

    public int getTedadforoush2() {
        return tedadforoush2;
    }

    public void setTedadforoush2(int tedadforoush2) {
        this.tedadforoush2 = tedadforoush2;
    }

    public int getTedadforoush3() {
        return tedadforoush3;
    }

    public void setTedadforoush3(int tedadforoush3) {
        this.tedadforoush3 = tedadforoush3;
    }

    public int getTedadforoush4() {
        return tedadforoush4;
    }

    public void setTedadforoush4(int tedadforoush4) {
        this.tedadforoush4 = tedadforoush4;
    }

    public int getTedadforoush5() {
        return tedadforoush5;
    }

    public void setTedadforoush5(int tedadforoush5) {
        this.tedadforoush5 = tedadforoush5;
    }

    public int getTedadKharidarHaghighi() {
        return tedadKharidarHaghighi;
    }

    public void setTedadKharidarHaghighi(int tedadKharidarHaghighi) {
        this.tedadKharidarHaghighi = tedadKharidarHaghighi;
    }

    public int getHajmKharidarHaghighi() {
        return hajmKharidarHaghighi;
    }

    public void setHajmKharidarHaghighi(int hajmKharidarHaghighi) {
        this.hajmKharidarHaghighi = hajmKharidarHaghighi;
    }

    public int getArzeshKharidarHaghighi() {
        return arzeshKharidarHaghighi;
    }

    public void setArzeshKharidarHaghighi(int arzeshKharidarHaghighi) {
        this.arzeshKharidarHaghighi = arzeshKharidarHaghighi;
    }

    public int getTedadKharidarHoghoghi() {
        return tedadKharidarHoghoghi;
    }

    public void setTedadKharidarHoghoghi(int tedadKharidarHoghoghi) {
        this.tedadKharidarHoghoghi = tedadKharidarHoghoghi;
    }

    public int getHajmKharidarHoghoghi() {
        return hajmKharidarHoghoghi;
    }

    public void setHajmKharidarHoghoghi(int hajmKharidarHoghoghi) {
        this.hajmKharidarHoghoghi = hajmKharidarHoghoghi;
    }

    public int getArzeshKharidarHoghoghi() {
        return arzeshKharidarHoghoghi;
    }

    public void setArzeshKharidarHoghoghi(int arzeshKharidarHoghoghi) {
        this.arzeshKharidarHoghoghi = arzeshKharidarHoghoghi;
    }

    public int getTedadforoushandehHaghighi() {
        return tedadforoushandehHaghighi;
    }

    public void setTedadforoushandehHaghighi(int tedadforoushandehHaghighi) {
        this.tedadforoushandehHaghighi = tedadforoushandehHaghighi;
    }

    public int getHajmforoushandehHaghighi() {
        return hajmforoushandehHaghighi;
    }

    public void setHajmforoushandehHaghighi(int hajmforoushandehHaghighi) {
        this.hajmforoushandehHaghighi = hajmforoushandehHaghighi;
    }

    public int getArzeshforoushandehHaghighi() {
        return arzeshforoushandehHaghighi;
    }

    public void setArzeshforoushandehHaghighi(int arzeshforoushandehHaghighi) {
        this.arzeshforoushandehHaghighi = arzeshforoushandehHaghighi;
    }

    public int getTedadforoushandehHoghoghi() {
        return tedadforoushandehHoghoghi;
    }

    public void setTedadforoushandehHoghoghi(int tedadforoushandehHoghoghi) {
        this.tedadforoushandehHoghoghi = tedadforoushandehHoghoghi;
    }

    public int getHajmforoushandehHoghoghi() {
        return hajmforoushandehHoghoghi;
    }

    public void setHajmforoushandehHoghoghi(int hajmforoushandehHoghoghi) {
        this.hajmforoushandehHoghoghi = hajmforoushandehHoghoghi;
    }

    public int getArzeshforoushandehHoghoghi() {
        return arzeshforoushandehHoghoghi;
    }

    public void setArzeshforoushandehHoghoghi(int arzeshforoushandehHoghoghi) {
        this.arzeshforoushandehHoghoghi = arzeshforoushandehHoghoghi;
    }

    public int getGheymatMojazMin() {
        return gheymatMojazMin;
    }

    public void setGheymatMojazMin(int gheymatMojazMin) {
        this.gheymatMojazMin = gheymatMojazMin;
    }

    public int getGheymatMojazMax() {
        return gheymatMojazMax;
    }

    public void setGheymatMojazMax(int gheymatMojazMax) {
        this.gheymatMojazMax = gheymatMojazMax;
    }

    public int getHajmMabna() {
        return hajmMabna;
    }

    public void setHajmMabna(int hajmMabna) {
        this.hajmMabna = hajmMabna;
    }

    public int getArzeshBazar() {
        return arzeshBazar;
    }

    public void setArzeshBazar(int arzeshBazar) {
        this.arzeshBazar = arzeshBazar;
    }

    public int getTedadSaham() {
        return tedadSaham;
    }

    public void setTedadSaham(int tedadSaham) {
        this.tedadSaham = tedadSaham;
    }

    public SahamEnt getSahm() {
        return sahm;
    }

    public void setSahm(SahamEnt sahm) {
        this.sahm = sahm;
    }
}
