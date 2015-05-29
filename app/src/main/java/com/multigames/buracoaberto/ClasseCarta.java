package com.multigames.buracoaberto;

        import android.animation.Animator;
        import android.animation.AnimatorListenerAdapter;
        import android.animation.AnimatorSet;
        import android.animation.Keyframe;
        import android.animation.ObjectAnimator;
        import android.animation.PropertyValuesHolder;
        import android.animation.ValueAnimator;
        import android.view.animation.Interpolator;
        import android.graphics.Bitmap;
        import android.content.Context;
        import android.widget.ImageView;

        import java.util.ArrayList;

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

    public ClasseCarta(Context context) {
        super(context);
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

    public AnimatorSet fxToggleSelect(float factor) {
        this.selecionada = !this.selecionada;
        if (this.selecionada){
            posY = posY - (float)Math.rint(10.0 * factor * Math.cos(Math.toRadians((float)this.angulo)));
            posX = posX + (float)Math.rint(10.0 * factor * Math.sin(Math.toRadians((float)this.angulo)));
        } else {
            posY = posY + (float)Math.rint(10.0 * factor * Math.cos(Math.toRadians((float)this.angulo)));
            posX = posX - (float)Math.rint(10.0 * factor * Math.sin(Math.toRadians((float)this.angulo)));
        }
        ValueAnimator translate = ObjectAnimator.ofFloat(this,"y",this.getY(),posY);
        translate.setDuration(200L);
        AnimatorSet aSet = new AnimatorSet();
        aSet.playTogether(translate);
        return aSet;
    }

    public AnimatorSet fxViraPraBaixo (long t) {
        ArrayList<Animator> animators = new ArrayList<Animator>();
        if (t==0L) t=TEMPO;
        if (this.praCima) {
            Keyframe kf0 = Keyframe.ofFloat(0f, 1f);
            Keyframe kf1 = Keyframe.ofFloat(.5f, 0f);
            Keyframe kf2 = Keyframe.ofFloat(1f, 1f);
            PropertyValuesHolder cardFlip = PropertyValuesHolder.ofKeyframe("scaleX", kf0, kf1, kf2);
            ObjectAnimator viraCarta = ObjectAnimator.ofPropertyValuesHolder(this, cardFlip);
            viraCarta.setDuration(t);
            viraCarta.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float v) {
                    if ((v > 0.5f) && praCima) {
                        setImageBitmap(verso);
                        praCima = false;
                    }
                    return v;
                }
            });
            animators.add(viraCarta);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        return animatorSet;
    }

    /*public AnimatorSet fxViraPraCima () {
         return fxViraPraCima(0L);
    }*/
    public AnimatorSet fxViraPraCima (long t) {
        ArrayList<Animator> animators = new ArrayList<Animator>();
        if (t==0) t=TEMPO;
        if (!this.praCima) {
            Keyframe kf0 = Keyframe.ofFloat(0f, 1f);
            Keyframe kf1 = Keyframe.ofFloat(.5f, 0f);
            Keyframe kf2 = Keyframe.ofFloat(1f, 1f);
            PropertyValuesHolder cardFlip = PropertyValuesHolder.ofKeyframe("scaleX", kf0, kf1, kf2);
            ObjectAnimator viraCarta = ObjectAnimator.ofPropertyValuesHolder(this, cardFlip);
            viraCarta.setDuration(t);
            viraCarta.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float v) {
                    if ((v > 0.5f) && !praCima) {
                        setImageBitmap(frente);
                        praCima = true;
                    }
                    return v;
                }
            });
            animators.add(viraCarta);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        return animatorSet;
    }
}


