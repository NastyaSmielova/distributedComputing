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
public class countryDTO {
    private Integer idCO;
    private String name;

    public Integer getIdCO() {
        return idCO;
    }

    public void setIdCO(Integer idCO) {
        this.idCO = idCO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public countryDTO(Integer idCO, String name) {
        this.idCO = idCO;
        this.name = name;
    }
    
}
