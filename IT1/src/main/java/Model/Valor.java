/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Carla
 */
public class Valor {

    private float valorR;
    private float valorD;
    private float valorO;

    public Valor(float valorR, float valorD, float valorO) {
        this.valorR = valorR;
        this.valorD = valorD;
        this.valorO = valorO;
       
    }

    public float getValorR() {
        return valorR;
    }

    public void setValorR(float valorR) {
        this.valorR = valorR;
    }

    public float getValorD() {
        return valorD;
    }

    public void setValorD(float valorD) {
        this.valorD = valorD;
    }

    public float getValorO() {
        return valorO;
    }

    public void setValorO(float valorO) {
        this.valorO = valorO;
    }

  
}
