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
    private String plano;
    private Date dataInicio;
    private Date dataFim;
    private LocalTime HoraEntrada;
    private LocalTime HoraSaida;
    private int id_viatura;
    private int id_lugar;
    private int id_cliente;

    static int totalID = 0;

    public Reservar(String plano, Date dataInicio, Date dataFim, LocalTime HoraEntrada, LocalTime HoraSaida, int id_viatura, int id_lugar, int id_cliente) {
        this.id = ++totalID;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.HoraEntrada = HoraEntrada;
        this.HoraSaida = HoraSaida;
        this.id_viatura = id_viatura;
        this.id_lugar = id_lugar;
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
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

    public int getId_cliente(){
        return this.id_cliente;
    }

    public void setId_cliente(int id_cliente){
        this.id_cliente = id_cliente;
    }
}