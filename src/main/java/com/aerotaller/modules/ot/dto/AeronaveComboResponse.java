package com.aerotaller.modules.ot.dto;

public class AeronaveComboResponse
{

    private Integer idAeronave;
    private String matricula;

    public AeronaveComboResponse()
    {
    }

    public AeronaveComboResponse(Integer idAeronave, String matricula)
    {
        this.idAeronave = idAeronave;
        this.matricula = matricula;
    }

    public Integer getIdAeronave()
    {
        return idAeronave;
    }

    public void setIdAeronave(Integer idAeronave)
    {
        this.idAeronave = idAeronave;
    }

    public String getMatricula()
    {
        return matricula;
    }

    public void setMatricula(String matricula)
    {
        this.matricula = matricula;
    }
}