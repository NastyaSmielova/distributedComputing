/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author pc
 */
public class cityDTO {
    private String name;

    public cityDTO(String name, Integer idCI, Integer count, Integer isCap, Integer idCO) {
        this.name = name;
        this.idCI = idCI;
        this.count = count;
        this.isCap = isCap;
        this.idCO = idCO;
    }
    private Integer idCI;
    private Integer count;
    private Integer isCap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdCI() {
        return idCI;
    }

    public void setIdCI(Integer idCI) {
        this.idCI = idCI;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIsCap() {
        return isCap;
    }

    public void setIsCap(Integer isCap) {
        this.isCap = isCap;
    }

    public Integer getIdCO() {
        return idCO;
    }

    public void setIdCO(Integer idCO) {
        this.idCO = idCO;
    }
    private Integer idCO;
    
}
