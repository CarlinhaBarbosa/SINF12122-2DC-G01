/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Carla
 */
public class Reservar {
    private int id;
    private String tipo;
    private Data dataInicio;
    private Data dataFim;
    private LocalTime HoraEntrada;
    private LocalTime HoraSaida;
    private int id_viatura;
    private int id_lugar;

    static int totalID = 0;

    public Reservar(int id, String tipo, Data dataInicio, Data dataFim, LocalTime HoraEntrada, LocalTime HoraSaida, int id_viatura, int id_lugar) {
        this.id = ++totalID;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.HoraEntrada = HoraEntrada;
        this.HoraSaida = HoraSaida;
        this.id_viatura = id_viatura;
        this.id_lugar = id_lugar;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTipoReservaConvertido(String tipo) {
        int result = 0;
        switch (tipo) {
            case "Tuition with lot reservation":
                result = 1;
                break;
            case "Tuition without lot reservation":
                result = 2;
                break;
            case "Daily":
                result = 3;
                break;
            case "Occasional Ticket":
                result = 4;
                break;
        }
        return result;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    public LocalTime getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(LocalTime HoraEntrada) {
        this.HoraEntrada = HoraEntrada;
    }

    public LocalTime getHoraSaida() {
        return HoraSaida;
    }

    public void setHoraSaida(LocalTime HoraSaida) {
        this.HoraSaida = HoraSaida;
    }

    public int getId_viatura() {
        return id_viatura;
    }

    public void setId_viatura(int id_viatura) {
        this.id_viatura = id_viatura;
    }

    public int getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(int id_lugar) {
        this.id_lugar = id_lugar;
    }
    public Date getDataInicioConvertida(Data dataInicio) throws ParseException {
        String dataInicioParaConversao = Integer.toString(dataInicio.getDia()) + "/" + Integer.toString(dataInicio.getMes()) + "/" + Integer.toString(dataInicio.getAno());
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formatter1.parse(dataInicioParaConversao);
        return date1;
    }

    public Date getDataFimConvertida(Data dataFim) throws ParseException {
        String dataInicioParaConversao = Integer.toString(dataFim.getDia()) + "/" + Integer.toString(dataFim.getMes()) + "/" + Integer.toString(dataFim.getAno());
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formatter1.parse(dataInicioParaConversao);
        return date1;
    }

}
