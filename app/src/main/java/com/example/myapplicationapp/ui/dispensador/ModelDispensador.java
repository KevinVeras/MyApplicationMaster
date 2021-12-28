package com.example.myapplicationapp.ui.dispensador;

public class ModelDispensador {

    String Horario1,Horario2,Horario3,Horario4;
    String CantidadAgua,CantidadComida;
    String Automatico,Programable;

    ModelDispensador() {
    }

    public ModelDispensador(String horario1, String horario2, String horario3,
                            String horario4, String cantidadAgua, String cantidadComida,
                            String automatico, String programable) {
        Horario1 = horario1;
        Horario2 = horario2;
        Horario3 = horario3;
        Horario4 = horario4;
        CantidadAgua = cantidadAgua;
        CantidadComida = cantidadComida;
        Automatico = automatico;
        Programable = programable;
    }

    public String getHorario1() {
        return Horario1;
    }

    public void setHorario1(String horario1) {
        Horario1 = horario1;
    }

    public String getHorario2() {
        return Horario2;
    }

    public void setHorario2(String horario2) {
        Horario2 = horario2;
    }

    public String getHorario3() {
        return Horario3;
    }

    public void setHorario3(String horario3) {
        Horario3 = horario3;
    }

    public String getHorario4() {
        return Horario4;
    }

    public void setHorario4(String horario4) {
        Horario4 = horario4;
    }

    public String getCantidadAgua() {
        return CantidadAgua;
    }

    public void setCantidadAgua(String cantidadAgua) {
        CantidadAgua = cantidadAgua;
    }

    public String getCantidadComida() {
        return CantidadComida;
    }

    public void setCantidadComida(String cantidadComida) {
        CantidadComida = cantidadComida;
    }

    public String getAutomatico() {
        return Automatico;
    }

    public void setAutomatico(String automatico) {
        Automatico = automatico;
    }

    public String getProgramable() {
        return Programable;
    }

    public void setProgramable(String programable) {
        Programable = programable;
    }
}
