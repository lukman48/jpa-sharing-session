package com.sharingsession.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

 
@Embeddable
public class EmployeeIdentity implements Serializable {
    @NotNull
    @Size(max = 20)
    private String employeeId;

    @NotNull
    @Size(max = 20)
    private String companyId;

    public EmployeeIdentity() {

    }

    public EmployeeIdentity(String employeeId, String companyId) {
        this.employeeId = employeeId;
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

 
}
