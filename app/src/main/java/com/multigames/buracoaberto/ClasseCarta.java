package com.multigames.buracoaberto;

        import android.view.animation.Animation;
        import android.view.animation.AnimationSet;
        import android.view.animation.Interpolator;
        import android.view.animation.TranslateAnimation;
        import android.view.animation.ScaleAnimation;
        import android.graphics.Bitmap;
        import android.content.Context;
        import android.widget.ImageView;

/**
 *
 * @author Leonardo Lara Rodrigues
 */
public class ClasseCarta extends ImageView {
    public final static long TEMPO = 1000L;
    public final static int JOG2 = 1, JOG3 = 2, JOG4 = 3;
    public int carta;
    public int naipe;
    public int cor;
    public int valor;
    public boolean selecionada;
    public float angulo;
    public float posX, posY;
    public Bitmap frente;
    public Bitmap verso;
    public boolean praCima;
    public int zOrder;
    public int descarte;
    public AnimationSet anim;

    public ClasseCarta(Context context) {
        super(context);
        anim = new AnimationSet(false);
        praCima = false;
        selecionada = false;
        angulo = 0f;
        posX = 0f;
        posY = 0f;
    }

    public int ordemNaipe(){
        return naipe*100+carta*3+cor;
    }

    /*public void zeraDescarte () {
        this.descarte = 0;
    }*/

    public void defineAngulo (int j){
        if (j==JOG2){
            if (this.angulo==0){
                this.angulo=-90;
            }
        }
        else if (j==JOG3){
            if (this.angulo==0){
                this.angulo=180;
            }
        }
        else if (j==JOG4){
            if (this.angulo==0){
                this.angulo=90;
            }
        }
        else {
            if (this.angulo!=0){
                this.angulo=0;
            }
        }
    }

    public int ordemCanastra (ClasseMao ordem){
        if (ordem!=null){
            for (int k=0;k<ordem.cartas.size();k++){
                if ((this.carta==ordem.cartas.get(k).carta)&&(this.naipe==ordem.cartas.get(k).naipe)&&(this.cor==ordem.cartas.get(k).cor)){
                    return k;
                }
            }
        }
        return -1;
    }

    public void fxToggleSelect() {
        this.selecionada = !this.selecionada;
        if (this.selecionada){
            posY = posY - (float)Math.rint(10.0 * Math.cos(Math.toRadians((float)this.angulo)));
            posX = posX + (float)Math.rint(10.0 * Math.sin(Math.toRadians((float)this.angulo)));
        } else {
            posY = posY + (float)Math.rint(10.0 * Math.cos(Math.toRadians((float)this.angulo)));
            posX = posX - (float)Math.rint(10.0 * Math.sin(Math.toRadians((float)this.angulo)));
        }
        TranslateAnimation translate = new TranslateAnimation(this.getTranslationX(),this.getTranslationY(),posX,posY);
        translate.setDuration(200L);
        translate.setFillAfter(true);
        translate.cancel();
        this.anim.addAnimation(translate);
    }

    /*public ScaleAnimation fxViraPraBaixo () {
        return fxViraPraBaixo(0L);
    }*/

    public void fxViraPraBaixo (long t) {
        if (t==0L) t=TEMPO;
        if (this.praCima) {
            ScaleAnimation viraCarta = new ScaleAnimation(1.0f,1.0f,0.0f,1.0f);
            viraCarta.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float v) {
                    if ((v > 0.5f) && praCima) {
                        setImageBitmap(verso);
                        praCima = false;
                    }
                    if (v < 0.5) return v * 2f;
                    else return 2f - 2f * v; // rampa sobe e desce
                }
            });
            viraCarta.setDuration(t);
            viraCarta.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    praCima = false;
                    anim.getAnimations().clear();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            viraCarta.setFillAfter(false);
            viraCarta.cancel();
            this.anim.addAnimation(viraCarta);
        }
    }

    public void fxViraPraCima () {
        this.fxViraPraCima(0L);
    }
    public void fxViraPraCima (long t) {
        if (t==0) t=TEMPO;
        if (!this.praCima) {
            ScaleAnimation viraCarta = new ScaleAnimation(1.0f,1.0f,0.0f,1.0f);
            viraCarta.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float v) {
                    if ((v > 0.5f) && !praCima) {
                        setImageBitmap(frente);
                        praCima = false;
                    }
                    if (v < 0.5) return v * 2f;
                    else return 2f - 2f * v;
                }
            });
            viraCarta.setDuration(t);
            viraCarta.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    praCima = true;
                    anim.getAnimations().clear();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            viraCarta.setFillAfter(false);
            viraCarta.cancel();
            this.anim.addAnimation(viraCarta);
        }
    }

    public void play() {
        if (!this.anim.getAnimations().isEmpty()) {
            this.anim.cancel();
            this.anim.setDuration(TEMPO);
            this.setAnimation(anim);
            this.getAnimation().start();
        }
    }
}


