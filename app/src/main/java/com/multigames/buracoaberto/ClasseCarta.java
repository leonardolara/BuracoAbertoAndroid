package com.multigames.buracoaberto;

        import android.view.animation.Animation;
        import android.view.animation.Interpolator;
        import android.view.animation.Transformation;
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
    public final static int TEMPO = 1;
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

    public ClasseCarta(Context context) {
        super(context);
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

    public TranslateAnimation fxToggleSelect() {
        float oldPosX = posX;
        float oldPosY = posY;
        this.selecionada = !this.selecionada;
        if (this.selecionada){
            posY = posY - (float)Math.rint(10.0 * Math.cos(Math.toRadians((float)this.angulo)));
            posX = posX + (float)Math.rint(10.0 * Math.sin(Math.toRadians((float)this.angulo)));
        } else {
            posY = posY + (float)Math.rint(10.0 * Math.cos(Math.toRadians((float)this.angulo)));
            posX = posX - (float)Math.rint(10.0 * Math.sin(Math.toRadians((float)this.angulo)));
        }
        TranslateAnimation translate = new TranslateAnimation(oldPosX,oldPosY,posX,posY);
        return translate;
    }

    public ScaleAnimation fxViraPraBaixo () {
        return fxViraPraBaixo(0L);
    }

    public ScaleAnimation fxViraPraBaixo (long t) {
        if (t==0L) t=TEMPO;
        if (this.praCima) {
            Transformation tr = new Transformation();
            ScaleAnimation timeline = new ScaleAnimation(1.0f,1.0f,0.0f,1.0f);
            timeline.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float v) {
                    if ((v > 0.5f) && praCima) {
                        setImageBitmap(verso);
                        praCima = false;
                    }
                    if (v < 0.5) return v * 2f;
                    else return 2f - 2f * v;
                }
            });
            timeline.setDuration(t);
            timeline.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    praCima = false;
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            return timeline;
        }
        else {
            return null;
        }
    }

    public ScaleAnimation fxViraPraCima () {
        return fxViraPraCima(0L);
    }
    public ScaleAnimation fxViraPraCima (long t) {
        if (t==0) t=TEMPO;
        if (!this.praCima) {
            Transformation tr = new Transformation();
            ScaleAnimation timeline = new ScaleAnimation(1.0f,1.0f,0.0f,1.0f);
            timeline.setInterpolator(new Interpolator(){
                @Override
                public float getInterpolation(float v) {
                    if ((v>0.5f)&&praCima) {
                        setImageBitmap(frente);
                        praCima = false;
                    }
                    if (v<0.5) return v * 2f; else return 2f - 2f * v;
                }
            });
            timeline.setDuration(t);
            timeline.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    praCima = true;
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            return timeline;
        }
        else {
            return null;
        }
    }
}


