package com.nextlevel.monitoring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "sahm")
public class SahamEnt extends BaseDataEnt {

    String name;
    String sharh;
    long code;

    @OneToMany(
            mappedBy = "sahm"
            , fetch = FetchType.LAZY
            , cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
            , orphanRemoval = true
    )
    @JsonManagedReference
    private List<SahamInfoEnt> sahamInfoEntList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getSharh() {
        return sharh;
    }

    public void setSharh(String desc) {
        this.sharh = desc;
    }

    @Override
    public String toString() {
        return "SahmEnt["+this.getId()+"," +code +"," + name +"]" ;
    }
}
