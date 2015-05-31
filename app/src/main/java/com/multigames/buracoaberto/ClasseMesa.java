package com.multigames.buracoaberto;

/**
 * Created by Taciana on 24/05/2015.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        import java.util.ArrayList;
        import android.graphics.PointF;
/**
 *
 * @author Leonardo Lara Rodrigues
 */
public class ClasseMesa {
    ArrayList<ClasseMao> maos;
    public float posInitX, posInitY, deltaX, deltaY;
    public float nextPosX, nextPosY, posMax;
    public boolean pegouMorto, bateu;
    public ClasseMesa (){
        this.posInitX=0.0f;
        this.posInitY=0.0f;
        this.deltaX=0.0f;
        this.deltaY=0.0f;
        this.nextPosX=0.0f;
        this.nextPosY=0.0f;
        this.posMax=0.0f;
        this.pegouMorto=false;
        this.bateu=false;
        this.maos = new ArrayList<ClasseMao>();
    }

    public void setPosInit (float pX, float pY, float dX, float dY) {
        this.posInitX = pX;
        this.posInitY = pY;
        this.deltaX = dX;
        this.deltaY = dY;
    }

    public PointF posMao (int mao_mesa, float lgCarta) {
        PointF pos = new PointF(this.posInitX, this.posInitY);
        if ((mao_mesa >= this.maos.size())
                || (mao_mesa == 0)
                || this.maos.isEmpty()) {
            return pos;
        }
        float espacoH = lgCarta * (2f/7f); //espaço horizontal entre os jogos da mesa
        float altura = lgCarta * (10f/7f);
        float espacoV = altura / 10f; //espaço vertical entre os jogos da mesa
        pos = new PointF(
                this.maos.get(mao_mesa-1).posInitX
                        + this.maos.get(mao_mesa-1).largura(lgCarta)
                        + espacoH,
                this.maos.get(mao_mesa-1).posInitY); //posição da mão anterior + largura + espaço = posição da mão atual
        if (pos.x + this.maos.get(mao_mesa).largura(lgCarta) > this.posMax){ // se passar do limite, ajusta
            pos = new PointF(this.posInitX, pos.y + altura + espacoV);
        }
        return pos;
    }

    public int somaPontos() {
        int pontos = 0;
        for (ClasseMao m:this.maos){
            if (m.real()) pontos += 1000;
            else if (m.semireal()) pontos += 500;
            else if (m.limpa()) pontos += 200;
            else if (m.suja()) pontos += 100;
            pontos += m.somaPontos();
        }
        if (!this.pegouMorto) pontos -= 100;
        if (this.bateu) pontos += 100;
        return pontos;
    }

    public boolean temLimpa() {
        boolean tem = false;
        for (ClasseMao m:this.maos){
            if (m.limpa()) return true;
        }
        return tem;
    }
    /*
    public void esvazia() {
        for (int m=0;m<this.maos.size();m++){
            this.maos.get(m).esvazia();
            this.maos.remove(m);
        }
    }
    */
}