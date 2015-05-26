package com.multigames.buracoaberto;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Region;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import static com.multigames.buracoaberto.ClasseCarta.TEMPO;

public class BuracoAberto extends Activity {

    public FrameLayout root;
    public float factor;
    public static final boolean DEBUGMODE = false;
    //public static final boolean somCartaLigado = false;
    public static final int ESPADAS = 1;
    //public static final int COPAS = 2;
    //public static final int PAUS = 3;
    public static final int OUROS = 4;
    public static final int AS = 1;
    //public static final int DOIS = 2;
    public static final int TRES = 3;
    //public static final int QUATRO = 4;
    //public static final int CINCO = 5;
    //public static final int SEIS = 6;
    public static final int SETE = 7;
    //public static final int OITO = 8;
    //public static final int NOVE = 9;
    //public static final int DEZ = 10;
    //public static final int VALETE = 11;
    //public static final int DAMA = 12;
    public static final int REI = 13;

    public static final int VERMELHO = 1;
    public static final int AZUL = 2;

    public static final int MAXCARTAS = 104;
    public static final int UMBARALHO = 52;
    public static final int NUMCARTAS = 13;
    //public static final int NUMNAIPES = 4;
    //public static final int NUMCORES = 2;
    public static final int CARTASMORTO = 11;
    public static final int CARTASJOG = 11;
    //public static final int JOGADORES = 4;
    //public static final int MAOMAX = MAXCARTAS - (2 * CARTASMORTO) - ((JOGADORES - 1) * CARTASJOG);
    //public static final int MAXLIXO = MAXCARTAS - (JOGADORES * CARTASJOG);
    //public static final int MAXMONTE = MAXCARTAS - (2 * CARTASMORTO) - (JOGADORES * CARTASJOG);

    public static final int JOG1 = 0;
    public static final int JOG2 = 1;
    public static final int JOG3 = 2;
    public static final int JOG4 = 3;
    public static final int MONTE = 4;
    public static final int LIXO = 5;
    public static final int MORTO1 = 6;
    public static final int MORTO2 = 7;

    public static final int MESA1 = 8;
    public static final int MESA2 = 9;

    public static final int BAIXO = 5;
    public static final int MEDIO = 10;
    public static final int ALTO = 15;

    //public static final int CANASTRA_LIMPA = 200;
    //public static final int CANASTRA_SUJA = 100;
    //public static final int CANASTRA_REAL = 1000;
    //public static final int CANASTRA_SEMIREAL = 500;
    //public static final int BATIDA = 100;
    //public static final int PEGOUMORTO = 100;

    public ClasseCarta[] baralho = new ClasseCarta[104];
    public ClasseMao[] mao;
    public ClasseMesa[] mesa;

    public boolean ja_comprou = false;
    public float vez = -1;
    public float oldvez = -1;
    public boolean jogo_acabou = false;

    public String[] naipes = {"espadas", "copas", "paus", "ouros"};
    //public String[] cores = {"Vermelho", "Azul"};
    public String[] cartas = {"e ás", "e dois", "e três", "e quatro", "e cinco", "e seis", "e sete", "e oito", "e nove", "e dez", "e valete", "a dama", "e rei"};
    public int[] nomes = {R.mipmap.c1e,R.mipmap.c2e,R.mipmap.c3e,R.mipmap.c4e,R.mipmap.c5e,R.mipmap.c6e,R.mipmap.c7e,R.mipmap.c8e,R.mipmap.c9e,R.mipmap.c10e,R.mipmap.c11e,R.mipmap.c12e,R.mipmap.c13e,
            R.mipmap.c1c,R.mipmap.c2c,R.mipmap.c3c,R.mipmap.c4c,R.mipmap.c5c,R.mipmap.c6c,R.mipmap.c7c,R.mipmap.c8c,R.mipmap.c9c,R.mipmap.c10c,R.mipmap.c11c,R.mipmap.c12c,R.mipmap.c13c,
            R.mipmap.c1p,R.mipmap.c2p,R.mipmap.c3p,R.mipmap.c4p,R.mipmap.c5p,R.mipmap.c6p,R.mipmap.c7p,R.mipmap.c8p,R.mipmap.c9p,R.mipmap.c10p,R.mipmap.c11p,R.mipmap.c12p,R.mipmap.c13p,
            R.mipmap.c1o,R.mipmap.c2o,R.mipmap.c3o,R.mipmap.c4o,R.mipmap.c5o,R.mipmap.c6o,R.mipmap.c7o,R.mipmap.c8o,R.mipmap.c9o,R.mipmap.c10o,R.mipmap.c11o,R.mipmap.c12o,R.mipmap.c13o};
    public final Region lixo = new Region(), mesadejogos = new Region();
    public int cartaLixo = 0;
    public int naipeLixo = 0;
    ClasseCarta botPegouDoLixo;
    //private static final AudioClip SUA_VEZ = new AudioClip(BuracoAberto.class.getResource("/buracoaberto/sua_vez.wav").toString());
    //private static final AudioClip CARTA = new AudioClip(BuracoAberto.class.getResource("/buracoaberto/carta.wav").toString());
    //private static final AudioClip APLAUSO = new AudioClip(BuracoAberto.class.getResource("/buracoaberto/aplauso.wav").toString());
    //private static final AudioClip CHORO = new AudioClip(BuracoAberto.class.getResource("/buracoaberto/choro.wav").toString());
    //private static final AudioClip LIMPA = new AudioClip(BuracoAberto.class.getResource("/buracoaberto/som_limpa.wav").toString());
    //private static final AudioClip SUJA = new AudioClip(BuracoAberto.class.getResource("/buracoaberto/som_suja.wav").toString());
    public Timer masterListener;
    public float screenWidth, screenHeight;
    public float screenCenterX;
    public float screenCenterY;
    public TextView lblMesa1, lblMesa2;
    public boolean smallRes = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup.LayoutParams prm = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        root = new FrameLayout(this);
        root.setBackgroundColor(Color.rgb(0, 127, 0));
        root.setLayoutParams(prm);
        int w = getWindowManager().getDefaultDisplay().getWidth();
        int h = getWindowManager().getDefaultDisplay().getHeight();
        root.layout(0, 0, w, h);
        root.bringToFront();
        //root.setForegroundGravity(Gravity.FILL);
        float ratio = (float) w / (float) h;

        if (ratio < 0.75f) {
            //mantem largura
            factor = (float)w/240f;
        }
        else {
            //mantem altura
            factor = (float)h/320f;
        }
        //System.out.println(factor);
        //System.out.println(w);
        //System.out.println(h);

        mao = new ClasseMao[8];
        mesa = new ClasseMesa[2];
        lblMesa1 = new TextView(getApplicationContext());
        lblMesa2 = new TextView(getApplicationContext());
        //se o Bot pega a única carta do lixo, ela é memorizada pois não poderá ser descartada.
        botPegouDoLixo = new ClasseCarta(getApplicationContext());
        botPegouDoLixo.carta = 0;
        botPegouDoLixo.naipe = 0;
        inicializaCartas();
        inicializaControles();
        jogo_acabou = false;
        if (smallRes) {
            screenWidth = 240f*factor;
            System.out.println("width: " + screenWidth);
            screenHeight = 320f*factor;
            System.out.println("height:" + screenHeight);
        } else {
            screenWidth = 1366f;//Screen.getPrimary().getVisualBounds().getWidth();
            screenHeight = 768f;//Screen.getPrimary().getVisualBounds().getHeight();
        }
        setContentView(root,prm);
        jogo();
    }

    private void inicializaCartas(){
        Bitmap vermelho = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.crm), 70, 100, true);
        Bitmap azul = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.czl), 70, 100, true);
        int i;

        for (i=0;i<8;i++) mao[i] = new ClasseMao();
        for (i=0;i<2;i++) mesa[i] = new ClasseMesa();

        if (smallRes) {
            mao[JOG1].setPosInit(10f*factor, 285f*factor, 8f*factor, 0f*factor);
            mao[JOG2].setPosInit(243f*factor, 7f*factor, 0f*factor, 8f*factor);
            mao[JOG3].setPosInit(10f*factor, -45f*factor, 8f*factor, 0f*factor);
            mao[JOG4].setPosInit(-36f*factor, 10f*factor, 0f*factor, 8f*factor);
            mao[MONTE].setPosInit(10f * factor, 120f*factor, 0f*factor, 0f*factor);
            mao[LIXO].setPosInit(50f*factor, 120f*factor, 8f*factor, 0f*factor);
            mao[MORTO1].setPosInit(-30f*factor, -40f*factor, 0f*factor, 0f*factor);
            mao[MORTO2].setPosInit(-25f*factor, -45f*factor, 0f*factor, 0f*factor);
            mesa[0].setPosInit(10f*factor, 175f*factor, 4f*factor, 0f*factor);
            mesa[1].setPosInit(10f*factor, 10f*factor, 4f*factor, 0f*factor);
            mesa[0].posMax = 229f*factor;
            mesa[1].posMax = 229f*factor;
            screenCenterX = 103f*factor;
            screenCenterY = 123f*factor;
        } else {
            mao[JOG1].setPosInit(383f, 606f, 15f, 0f);
            mao[JOG2].setPosInit(1281f, 150f, 0f, 15f);
            mao[JOG3].setPosInit(383f, -54f, 15f, 0f);
            mao[JOG4].setPosInit(15f, 150f, 0f, 15f);
            mao[MONTE].setPosInit(283f, 276f, 7f, 0f);
            mao[LIXO].setPosInit(683f, 276f, 15f, 0f);
            mao[MORTO1].setPosInit(1083f, -54f, 1f, 0f);
            mao[MORTO2].setPosInit(1183f, -54f, 1f, 0f);
            mesa[0].setPosInit(283f, 386f, 10f, 0f);
            mesa[1].setPosInit(283f, 56f, 10f, 0f);
            mesa[0].posMax = 1083f;
            mesa[1].posMax = 1083f;
            screenCenterX = 648f;
            screenCenterY = 291f;
        }

        for (int cor=VERMELHO;cor<=AZUL;cor++){
            for (int naipe=ESPADAS;naipe<=OUROS;naipe++){
                for (int ct=AS;ct<=REI;ct++){
                    i = (ct-1) + NUMCARTAS * (naipe-1) + UMBARALHO * (cor-1);
                    baralho[i] = new ClasseCarta(getApplicationContext());
                    baralho[i].setTop((int)screenCenterY);
                    System.out.println(screenCenterX);
                    System.out.println((int)screenCenterX);
                    baralho[i].setLeft((int)screenCenterX);
                    //baralho[i].setTranslationX((float)(screenWidth-larguraCarta()/2f)+larguraCarta()/2f);
                    //baralho[i].setTranslationY((float)Math.random()*(screenHeight-alturaCarta()/2f)+alturaCarta()/2f);
                    //baralho[i].layout((int)Math.random()*(int)(screenWidth-larguraCarta()),(int)Math.random()*(int)(screenHeight-alturaCarta()),(int)(35f*factor),(int)(50f*factor));
                    if (ct==AS) baralho[i].valor = ALTO;
                    else if ((ct>=TRES) && (ct<=SETE)) baralho[i].valor = BAIXO;
                    else baralho[i].valor = MEDIO;
                    baralho[i].carta = ct;
                    baralho[i].naipe = naipe;
                    baralho[i].cor = cor;
                    baralho[i].selecionada = false;
                    baralho[i].zOrder = i+2;
                    System.out.println(i);
                    baralho[i].frente = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),nomes[i%52]),70,100,true);
                    if (cor==VERMELHO) baralho[i].verso = vermelho;
                    else baralho[i].verso = azul;
                    baralho[i].setImageBitmap(baralho[i].verso);
                    baralho[i].setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View ct) {
                            carta_click(ct);
                        }
                    });
                }
            }
        }

        for (i=0;i<MAXCARTAS;i++){
            mao[MONTE].cartas.add(baralho[i]);
        }


    }

    private void play(final int k){
        TranslateAnimation pausa = new TranslateAnimation(0f,0f,0f,0f);
        pausa.setDuration(10L);
        pausa.initialize(10,10,20,20);
        pausa.setFillAfter(true);
        pausa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mao[MONTE].cartas.get(k).posX = (float) Math.rint(mao[MONTE].posInitX + mao[MONTE].deltaX * (float) k);
                mao[MONTE].cartas.get(k).posY = (float) Math.rint(mao[MONTE].posInitY + mao[MONTE].deltaY * (float) k);
                mao[MONTE].cartas.get(k).setAnimation(arrumaCarta(mao[MONTE].cartas.get(k), true, 200L, true));
                mao[MONTE].cartas.get(k).getAnimation().start();
                if (DEBUGMODE) {
                    mao[MONTE].cartas.get(k).setAnimation(mao[MONTE].cartas.get(k).fxViraPraCima());
                    mao[MONTE].cartas.get(k).getAnimation().start();
                }
                if (k < 103) {
                    play(k + 1);
                } else {

                    AnimationSet anim = new AnimationSet(false);
                    anim.addAnimation(distribui(20L));
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mao[LIXO].cartas.get(mao[LIXO].cartas.size()-1).setAnimation(transfereCarta(MONTE, mao[MONTE].cartas.size() - 1, LIXO));
                            mao[LIXO].cartas.get(mao[LIXO].cartas.size()-1).getAnimation().setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    vez = 0f;
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                }
                            });
                            mao[LIXO].cartas.get(mao[LIXO].cartas.size()-1).getAnimation().start();
                            //só aparecem os contornos depois de distribuir as cartas
                            //lixo.setStyle("-fx-border-color: #AAAAAA;");
                            //mesadejogos.setStyle("-fx-border-color: #AAAAAA;");
                            //lblMesa1.setStyle("-fx-border-color: #AAAAAA;");
                            //lblMesa2.setStyle("-fx-border-color: #AAAAAA;");
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        pausa.start();
    }

    private void jogo () {
        //System.out.println("jogo()");

        embaralha();
        corta();
        embaralha();

        for (int k=mao[MONTE].cartas.size()-1;k>=0;k--){
            mao[MONTE].cartas.get(k).bringToFront();
        }
        lblMesa1.bringToFront();
        lblMesa2.bringToFront();
        updateZOrder();

        //play(0); //põe o monte na mesa, dsitribui as cartas e joga a primeira no lixo.

        masterListener = new Timer();
        masterListener.schedule(new TimerTask() {
            @Override
            public void run() {
                AnimationSet anim;
                if ((vez == -1f) && (oldvez == -1f) && jogo_acabou) {
                    terminaJogo();
                } else if (((oldvez == -1f) || (oldvez == 3.3f)) && (vez == 0f)) {
                    oldvez = 0f;
                } else if ((oldvez == 0f) && (vez == 1.1f)) {
                    oldvez = 1.1f;
                    anim = botCompra(JOG2);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 1.2f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 1.1f) && (vez == 1.2f)) {
                    //passou a vez para o jogador 2 descer
                    oldvez = 1.2f;
                    anim = botDesce(JOG2);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 1.3f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 1.2f) && (vez == 1.3f)) {
                    //passou a vez para o jogador 2 descartar
                    oldvez = 1.3f;
                    anim = botDescarta(JOG2);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 2.1f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 1.3f) && (vez == 2.1f)) {
                    //passou a vez para o jogador 3 comprar
                    oldvez = 2.1f;
                    anim = botCompra(JOG3);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 2.2f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 2.1f) && (vez == 2.2f)) {
                    //passou a vez para o jogador 3 descer
                    oldvez = 2.2f;
                    anim = botDesce(JOG3);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 2.3f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 2.2f) && (vez == 2.3f)) {
                    //passou a vez para o jogador 3 descartar
                    oldvez = 2.3f;
                    anim = botDescarta(JOG3);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 3.1f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 2.3f) && (vez == 3.1f)) {
                    //passou a vez para o jogador 4 comprar
                    oldvez = 3.1f;
                    anim = botCompra(JOG4);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 3.2f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 3.1f) && (vez == 3.2f)) {
                    //passou a vez para o jogador 4 descer
                    oldvez = 3.2f;
                    anim = botDesce(JOG4);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            vez = 3.3f;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                } else if ((oldvez == 3.2f) && (vez == 3.3f)) {
                    //passou a vez para o jogador 2 descartar
                    oldvez = 3.3f;
                    anim = botDescarta(JOG4);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (jogo_acabou) {
                                terminaJogo();
                            } else {
                                vez = 0f;
                                //SUA_VEZ.start();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    anim.start();
                }
            }
        }, 0L, 1000L);
        //cria um "listener" a cada 1.0s para sincronizar o jogo com as animações
    }

    /*private void mesadejogos_click(){
        //System.out.println("mesadejogos_click()");
        AnimationSet t;
        if (vez==0){
            t = desceSelecionadas(JOG1,-1);
            if (t!=null) t.start();
        }
        if (jogo_acabou){
            terminaJogo();
        }
    }*/

    /*private void lixo_click(){
        //System.out.println("lixo_click");
        if (vez!=0) return;
        if (!ja_comprou){
            ja_comprou=true;
            if (mao[LIXO].cartas.size()==1){ // se só tem uma carta sozinha, memoriza-a, não pode ser o descarte
                cartaLixo = mao[LIXO].cartas.get(0).carta;
                naipeLixo = mao[LIXO].cartas.get(0).naipe;
            }
            AnimationSet pt = new AnimationSet(false);
            while (!mao[LIXO].cartas.isEmpty()){
                pt.addAnimation(transfereCarta(LIXO, mao[LIXO].cartas.size() - 1, JOG1));
            }
            pt.start();
        } else {
            descarta();
        }
    }*/

    private void carta_click(View clickedView){
        System.out.println("carta_click");
        System.out.println(vez);
        if (vez!=0) return; //não está na sua vez
        AnimationSet t = new AnimationSet(false);
        ClasseCarta ct = (ClasseCarta)clickedView;
        System.out.println(ct.carta);
        int k, iCt=-1, iMao=-1;
        //boolean t;
        k=mao[MONTE].cartas.size();
        if (k==MAXCARTAS) return; //se ainda não distribuiu as cartas, não faz nada

        //IDENTIFICAÇÃO DA CARTA CLICADA
        if (k>=1){ //se ainda tem pelo menos uma carta no monte, verifica se clicou no monte
            if (ct.equals(mao[MONTE].cartas.get(k-1))){ //se clicou no topo do monte
                iCt=k-1; //então iCt é o índice da carta...
                iMao=MONTE; //...da mão cujo índice é iMao.
            }
        }
        if (iCt==-1){ //se ainda não achou a carta clicada...
            for (k=0;k<mao[LIXO].cartas.size();k++){ //...procura no LIXO
                if (ct.equals(mao[LIXO].cartas.get(k))){
                    //System.out.println("Carta do Lixo clicada");
                    iCt=k;
                    iMao=LIXO;
                }
            }
        }
        if (iCt==-1){
            for (k=0;k<mao[JOG1].cartas.size();k++){ //procura nas cartas do jogador
                if (ct.equals(mao[JOG1].cartas.get(k))){
                    iCt=k;
                    iMao=JOG1;
                }
            }
        }
        if (iCt==-1){
            for (int m=0;m<mesa[0].maos.size();m++){ //procura na MESA1 (mesa[0])
                for (k=0;k<mesa[0].maos.get(m).cartas.size();k++){
                    if (ct.equals(mesa[0].maos.get(m).cartas.get(k))){
                        iCt=k;
                        iMao=100+m; //adicionando 100, quer dizer que clicou na mesa (iMao-100 = índice da mão)
                    }
                }
            }
        }

        //COM A DEFINIÇÃO DE QUAL CARTA FOI CLICADA, EFETUA AS AÇÕES
        if ((iMao==MONTE)&&!ja_comprou){ //compra do monte
            ja_comprou=true;
            transfereCarta(MONTE,mao[MONTE].cartas.size()-1,JOG1).start();
        }
        else if((iMao==LIXO)&&!ja_comprou){ //pega tudo do lixo
            ja_comprou=true;
            if (mao[LIXO].cartas.size()==1){ // se só tem uma carta sozinha, memoriza-a, não pode ser o descarte
                cartaLixo = mao[LIXO].cartas.get(0).carta;
                naipeLixo = mao[LIXO].cartas.get(0).naipe;
            }
            AnimationSet pt = new AnimationSet(false);
            while (!mao[LIXO].cartas.isEmpty()){
                pt.addAnimation(transfereCarta(LIXO, mao[LIXO].cartas.size() - 1, JOG1));
            }
            t.addAnimation(pt);
            t.start();
        }
        else if((iMao==LIXO)&&ja_comprou){ //descarta
            descarta();
        }
        else if ((iMao==JOG1)&&ja_comprou){ //(de)seleciona
            mao[JOG1].cartas.get(iCt).fxToggleSelect().start();
        }
        else if (iMao>=100){
            t=desceSelecionadas(JOG1,iMao-100);
            if (t!=null){
                t.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (jogo_acabou) terminaJogo();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                t.start();
            } else {
                if (jogo_acabou) terminaJogo();
            }
        }
    }

    public AnimationSet arrumaCarta (final ClasseCarta ct, boolean movimento, long t, final boolean toFront) {
        if (t==0L) t=TEMPO;
        if (!movimento) t=1L;
        AnimationSet transition = new AnimationSet(false);
        TranslateAnimation translate = new TranslateAnimation(ct.getTranslationX(),ct.getTranslationY(),ct.posX,ct.posY);
        translate.setDuration(t);
        RotateAnimation rotate = new RotateAnimation (ct.getRotation(),ct.angulo);
        rotate.setDuration(t);
        rotate.setInterpolator(new android.view.animation.Interpolator() {
            @Override
            public float getInterpolation(float v) {
                if (v > 0.5) {
                    if (root.indexOfChild(ct) != ct.zOrder) {
                        if (!toFront) {
                            root.removeView(ct);
                            root.addView(ct, ct.zOrder);
                            //updateZOrder();
                        } else {
                            root.removeView(ct);
                            root.addView(ct, MAXCARTAS + 1); //zOrder das cartas vai de 2 a 105
                        }
                    }
                }
                return v;
            }
        });
        transition.addAnimation(translate);
        transition.addAnimation(rotate);
        return transition;
    }
    public AnimationSet arrumaCarta (ClasseCarta ct, boolean movimento){
        return arrumaCarta(ct,movimento,0L,false);
    }
    /*public AnimationSet arrumaCarta (ClasseCarta ct){
        return arrumaCarta(ct,true,0L,false);
    }*/
    public AnimationSet arrumaCarta (ClasseCarta ct, boolean movimento, long t){
        return arrumaCarta(ct,movimento,t,false);
    }

    public void updateZOrder() {
        for (int m=0;m<8;m++){
            //this.mao[m].tarja.bringToFront();
        }
        for (int ms=0;ms<2;ms++){
            if (!this.mesa[ms].maos.isEmpty()){
                for (int m=0;m<this.mesa[ms].maos.size();m++){
                    mesa[ms].maos.get(m).tarja.bringToFront();
                }
            }
        }
        for (int k=0;k<104;k++){
            baralho[k].zOrder = root.indexOfChild(baralho[k]);
            //System.out.println(root.getChildren().indexOf(baralho[k]));
        }
    }

    private AnimationSet transfereCarta(int origem, int index, int destino) {
        return transfereCarta(origem,index,destino,-1,0L,null);
    }
    private AnimationSet transfereCarta(int origem, int index, int destino, long tempo) {
        return transfereCarta(origem,index,destino,-1,tempo,null);
    }
    private AnimationSet transfereCarta(int origem, int index, int destino, int mao_mesa, ClasseMao ordem) {
        return transfereCarta(origem,index,destino,mao_mesa,0L,ordem);
    }
    private AnimationSet transfereCarta(int origem, int index, int destino, int mao_mesa, long tempo, ClasseMao ordem) {
        //System.out.println("transfereCarta()"+origem+" "+destino+" "+mao_mesa);
        AnimationSet seqtransition = new AnimationSet(false);
        ClasseCarta ctOrigem = mao[origem].cartas.get(index);
        ClasseMao maoDestino;
        int ms = destino - 8;
        if ((destino==MESA1)||(destino==MESA2)){
            maoDestino = mesa[ms].maos.get(mao_mesa);
        }
        else {
            maoDestino = mao[destino];
        }

        ctOrigem.selecionada=false; //ISSO É IMPORTANTE
        //ctOrigem.local = destino; //ISSO NÃO É IMPORTANTE
        ctOrigem.posX = (float)Math.rint(maoDestino.getNextPos("X")); //isso é pro morto e pro lixo
        ctOrigem.posY = (float)Math.rint(maoDestino.getNextPos("Y")); //isso também

        //Define o angulo que a carta deverá ficar
        ctOrigem.defineAngulo(destino);

        if ((destino==JOG1)||(destino==JOG2)||(destino==JOG3)||(destino==JOG4)) {
            //considera que a mão do jogador 1 está sempre organizada por naipe
            int lugar = ctOrigem.ordemNaipe();
            int indiceLugar = 0;
            if (!maoDestino.cartas.isEmpty()){
                indiceLugar = maoDestino.cartas.size();
                for (int k=0;k<maoDestino.cartas.size();k++){ //Define a posição da carta em relação às outras cartas do jogador
                    if (lugar < maoDestino.cartas.get(k).ordemNaipe()){
                        //índice do lugar na lista de cartas
                        indiceLugar = k;
                        break;
                    }
                }
                if (indiceLugar>=maoDestino.cartas.size()){
                    setZOrder(ctOrigem,maoDestino.cartas.get(maoDestino.cartas.size()-1).zOrder+1);
                } else {
                    setZOrder(ctOrigem,maoDestino.cartas.get(indiceLugar));
                }
            }

            maoDestino.cartas.add(indiceLugar,ctOrigem); //coloca a carta no lugar correto
            mao[origem].cartas.remove(index); //remove a carta da mão antiga

            //Faz a animação
            AnimationSet pt = new AnimationSet(false);
            if (!DEBUGMODE){
                if ((destino!=JOG1)&&(origem==LIXO)){
                    pt.addAnimation( //cria uma animação em paralelo
                            maoDestino.cartas.get(indiceLugar).fxViraPraBaixo(tempo)
                    ); //da carta virando pra baixo
                }
                if ((destino==JOG1)&&((origem==MONTE)||(origem==MORTO1)||(origem==MORTO2))){
                    pt.addAnimation( //cria uma animação em paralelo
                            maoDestino.cartas.get(indiceLugar).fxViraPraCima(tempo)
                    ); //da carta virando pra cima
                }
            }
            int tamanho = maoDestino.cartas.size();
            for (int k=0;k<tamanho;k++){
                //Define a nova posição de todas as cartas da mão, abrindo espaço para a carta que vai entrar

                if (destino==JOG3){
                    maoDestino.cartas.get(k).posX = (float)Math.rint(screenCenterX - maoDestino.deltaX * (k - ((float)tamanho/2)));
                    maoDestino.cartas.get(k).posY = (float)Math.rint(maoDestino.posInitY);
                }
                else if (destino==JOG2){
                    maoDestino.cartas.get(k).posX = (float)Math.rint(maoDestino.posInitX);
                    maoDestino.cartas.get(k).posY = (float)Math.rint(screenCenterY - maoDestino.deltaY * (k - ((float)tamanho/2)));
                }
                else if (destino==JOG4){
                    maoDestino.cartas.get(k).posX = (float)Math.rint(maoDestino.posInitX);
                    maoDestino.cartas.get(k).posY = (float)Math.rint(screenCenterY + maoDestino.deltaY * (k - ((float)tamanho/2)));
                }
                else if (destino==JOG1){
                    if (smallRes){
                        maoDestino.cartas.get(k).posX = (float)Math.rint(screenCenterX + maoDestino.deltaX * (k - ((float)tamanho/2)));
                        maoDestino.cartas.get(k).posY = (float)Math.rint(maoDestino.posInitY);
                    } else {
                        maoDestino.cartas.get(k).posX = (float)Math.rint(screenCenterX + calculaX(k,tamanho));
                        maoDestino.cartas.get(k).posY = (float)Math.rint(maoDestino.posInitY - calculaY(k,tamanho));
                        maoDestino.cartas.get(k).angulo = calculaAngulo(k,tamanho)*180f/(float)Math.PI;
                    }
                }
                pt.addAnimation(
                        arrumaCarta(maoDestino.cartas.get(k),true,tempo)    //e todas se movendo para o lugar correto
                );
            }
            /*if (somCartaLigado){
                Timeline tl = new Timeline();
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(0.0),playCarta));
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(600),stopCarta));
                pt.getChildren().add(tl);
            }*/

            seqtransition.addAnimation(pt); //adiciona à animação já existente
        }

        else if (destino==LIXO){
            maoDestino.cartas.add(ctOrigem);
            mao[origem].cartas.remove(index);

            AnimationSet pt = new AnimationSet(false);
            //move as cartas da mão de origem que estavam à direita da carta removida, para a posição correta fechando o espaço
            int tamanho = mao[origem].cartas.size();
            if (origem!=MONTE){
                for (int k=0;k<tamanho;k++){
                    if (origem==JOG3){
                        mao[origem].cartas.get(k).posX = (float)Math.rint(screenCenterX - mao[origem].deltaX * (k - ((float)tamanho/2)));
                        mao[origem].cartas.get(k).posY = (float)Math.rint(mao[origem].posInitY);
                    }
                    else if (origem==JOG2){
                        mao[origem].cartas.get(k).posX = (float)Math.rint(mao[origem].posInitX);
                        mao[origem].cartas.get(k).posY = (float)Math.rint(screenCenterY - mao[origem].deltaY * (k - ((float)tamanho/2)));
                    }
                    else if (origem==JOG4){
                        mao[origem].cartas.get(k).posX = (float)Math.rint(mao[origem].posInitX);
                        mao[origem].cartas.get(k).posY = (float)Math.rint(screenCenterY + mao[origem].deltaY * (k - ((float)tamanho/2)));
                    }
                    else if (origem==JOG1){
                        if (smallRes) {
                            mao[origem].cartas.get(k).posX = (float)Math.rint(screenCenterX + mao[origem].deltaX * (k - ((float)tamanho/2)));
                            mao[origem].cartas.get(k).posY = (float)Math.rint(mao[origem].posInitY);
                        } else {
                            mao[origem].cartas.get(k).posX = (float)Math.rint(screenCenterX + calculaX(k,tamanho));
                            mao[origem].cartas.get(k).posY = (float)Math.rint(mao[origem].posInitY - calculaY(k,tamanho));
                            mao[origem].cartas.get(k).angulo = calculaAngulo(k,tamanho)*180f/(float)Math.PI;
                        }
                    }
                    pt.addAnimation(arrumaCarta(mao[origem].cartas.get(k),true,tempo));
                }
            }

            //leva a carta para o lixo
            if (!DEBUGMODE){
                if (origem!=JOG1){
                    pt.addAnimation(maoDestino.cartas.get(maoDestino.cartas.size()-1)
                            .fxViraPraCima(tempo));
                }
            }
            //audio
            /*
            if (somCartaLigado){
                Timeline tl = new Timeline();
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(0.0),playCarta));
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(600),stopCarta));
                pt.getChildren().add(tl);
            }
            */

            seqtransition.addAnimation(pt);
            seqtransition.addAnimation(arrumaCarta(maoDestino.cartas.get(maoDestino.cartas.size()-1),true,tempo,true));
        }

        else if ((destino==MESA1)||(destino==MESA2)) {
            PointF pos;
            //considera que a mão da mesa está sempre organizada por naipe
            int lugar = ctOrigem.ordemCanastra(ordem);
            if (lugar==-1) lugar=0;
            int indiceLugar = 0;
            if (!maoDestino.cartas.isEmpty()){
                indiceLugar = maoDestino.cartas.size();
                for (int k=0;k<maoDestino.cartas.size();k++){ //Define a posição da carta na mesa
                    if (lugar < maoDestino.cartas.get(k).ordemCanastra(ordem)){
                        //índice do lugar na lista de cartas
                        indiceLugar = k;
                        break;
                    }
                }
                if (indiceLugar>=maoDestino.cartas.size()){
                    setZOrder(ctOrigem,maoDestino.cartas.get(maoDestino.cartas.size()-1).zOrder+1);
                } else {
                    setZOrder(ctOrigem,maoDestino.cartas.get(indiceLugar));
                }
            }

            maoDestino.cartas.add(indiceLugar,ctOrigem); //coloca a carta no lugar correto
            mao[origem].cartas.remove(index); //remove a carta da mão antiga

            AnimationSet pt = new AnimationSet(false);
            //move as cartas da mão de origem que estavam à direita da carta removida, para a posição correta fechando o espaço
            int tamanho = mao[origem].cartas.size();
            for (int k=0;k<tamanho;k++){
                if (origem==JOG3){
                    mao[origem].cartas.get(k).posX = (float)Math.rint(screenCenterX - mao[origem].deltaX * (k - ((float)tamanho/2)));
                    mao[origem].cartas.get(k).posY = (float)Math.rint(mao[origem].posInitY);
                }
                else if (origem==JOG2){
                    mao[origem].cartas.get(k).posX = (float)Math.rint(mao[origem].posInitX);
                    mao[origem].cartas.get(k).posY = (float)Math.rint(screenCenterY - mao[origem].deltaY * (k - ((float)tamanho/2)));
                }
                else if (origem==JOG4){
                    mao[origem].cartas.get(k).posX = (float)Math.rint(mao[origem].posInitX);
                    mao[origem].cartas.get(k).posY = (float)Math.rint(screenCenterY + mao[origem].deltaY * (k - ((float)tamanho/2)));
                }
                else if (origem==JOG1){
                    if (smallRes) {
                        mao[origem].cartas.get(k).posX = (float)Math.rint(screenCenterX + mao[origem].deltaX * (k - ((float)tamanho/2)));
                        mao[origem].cartas.get(k).posY = (float)Math.rint(mao[origem].posInitY);
                    } else {
                        mao[origem].cartas.get(k).posX = (float)Math.rint(screenCenterX + calculaX(k,tamanho));
                        mao[origem].cartas.get(k).posY = (float)Math.rint(mao[origem].posInitY - calculaY(k,tamanho));
                        mao[origem].cartas.get(k).angulo = calculaAngulo(k,tamanho)*180f/(float)Math.PI;
                    }
                }
                pt.addAnimation(arrumaCarta(mao[origem].cartas.get(k), true, tempo));
            }

            //Se a carta estava virada pra baixo,
            if (!DEBUGMODE){
                if ((origem==JOG2)||(origem==JOG3)||(origem==JOG4)){
                    pt.addAnimation(
                            maoDestino.cartas.get(indiceLugar).fxViraPraCima(tempo)
                    ); //faz a animação da carta virando pra cima
                }
            }

            pos = mesa[ms].posMao(mesa[ms].maos.indexOf(maoDestino),larguraCarta());
            maoDestino.setPosInit(pos.x, pos.y, 10f, 0f);
            for (int k=0;k<maoDestino.cartas.size();k++) {
                //Define a posição das cartas do jogo para abrir espaço para a carta que vai entrar
                maoDestino.cartas.get(k).posX = (float)Math.rint(maoDestino.posInitX + maoDestino.deltaX * k);
                maoDestino.cartas.get(k).posY = (float)Math.rint(maoDestino.posInitY);
                pt.addAnimation(
                        arrumaCarta(maoDestino.cartas.get(k),true,tempo)    //e todas se movendo para o lugar correto
                );
            }

            //audio
            /*
            if (somCartaLigado){
                Timeline tl = new Timeline();
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(0.0),playCarta));
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(600),stopCarta));
                pt.getChildren().add(tl);
            }
            */

            //move as mãos da direita para dar espaço
            if ((mao_mesa + 1) < mesa[ms].maos.size()){
                for (int mm=mao_mesa+1;mm<mesa[ms].maos.size();mm++){
                    pos = mesa[ms].posMao(mm,larguraCarta());
                    mesa[ms].maos.get(mm).setPosInit(pos.x, pos.y, smallRes?5:10, 0);
                    for (int crt=0; crt < mesa[ms].maos.get(mm).cartas.size(); crt++) {
                        mesa[ms].maos.get(mm).cartas.get(crt).posX = (float)Math.rint(mesa[ms].maos.get(mm).posInitX + mesa[ms].maos.get(mm).deltaX * crt);
                        mesa[ms].maos.get(mm).cartas.get(crt).posY = (float)Math.rint(mesa[ms].maos.get(mm).posInitY + mesa[ms].maos.get(mm).deltaY * crt);
                        pt.addAnimation(arrumaCarta(mesa[ms].maos.get(mm).cartas.get(crt),true));
                    }
                    pt.addAnimation(mesa[ms].maos.get(mm).fxMoveTarja(larguraCarta()));
                }
            }

            seqtransition.addAnimation(pt);

        }

        else if ((destino==MORTO1)||(destino==MORTO2)||(destino==MONTE)){
            maoDestino.cartas.add(ctOrigem);
            mao[origem].cartas.remove(index);
            AnimationSet pt = new AnimationSet(false);
/*
            //audio
            if (somCartaLigado){
                Timeline tl = new Timeline();
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(0.0),playCarta));
                tl.getKeyFrames().add(new KeyFrame(Duration.millis(600),stopCarta));
                pt.getChildren().add(tl);
            }
*/
            if (DEBUGMODE && (destino==MONTE)){
                pt.addAnimation(
                        maoDestino.cartas.get(maoDestino.cartas.size() - 1).fxViraPraCima(tempo)
                ); //faz a animação da carta virando pra baixo
            }

            maoDestino.cartas.get(maoDestino.cartas.size()-1).posX = (float)Math.rint(maoDestino.posInitX);
            maoDestino.cartas.get(maoDestino.cartas.size()-1).posY = (float)Math.rint(maoDestino.posInitY);
            maoDestino.posInitX += maoDestino.deltaX;
            pt.addAnimation(
                    arrumaCarta(maoDestino.cartas.get(maoDestino.cartas.size() - 1), true, tempo, true)    //e todas se movendo para o lugar correto
            );

            seqtransition.addAnimation(pt);
        }
        return seqtransition;
    }

    private void setZOrder(ClasseCarta ctOrigem, int zOrder){
        if (zOrder - ctOrigem.zOrder > 1){
            for (int k=0;k<104;k++){
                if ((baralho[k].zOrder > ctOrigem.zOrder) && (baralho[k].zOrder < zOrder)){
                    baralho[k].zOrder--;
                }
            }
            ctOrigem.zOrder = zOrder - 1;
        }
        else if (ctOrigem.zOrder - zOrder > 0){
            int temp = zOrder;
            for (int k=0;k<104;k++){
                if ((baralho[k].zOrder >= zOrder) && (baralho[k].zOrder < ctOrigem.zOrder)){
                    baralho[k].zOrder++;
                }
            }
            ctOrigem.zOrder = temp;
        }
    }
    private void setZOrder(ClasseCarta ctOrigem, ClasseCarta ctDestino){
        setZOrder(ctOrigem,ctDestino.zOrder);
    }

    private void embaralha() {
        //System.out.println("embaralha()");
        int j,k;
        for (k=0;k<MAXCARTAS*2;k++){
            j=(int)Math.floor(Math.random()*MAXCARTAS);
            if (j>MAXCARTAS-1) j=MAXCARTAS-1;
            mao[MONTE].enviaProFinal(j);
        }
    }

    private void corta() {
        //System.out.println("corta()");
        int j;
        j=(int)Math.floor(Math.random()*MAXCARTAS);
        if (j>MAXCARTAS-1) j=MAXCARTAS-1;
        for (int k=0;k<j;k++){
            mao[MONTE].enviaProFinal(k);
        }
    }

    private AnimationSet distribui(Long tempo) {
        //System.out.println("distribui()");
        long t;
        AnimationSet seqtransition = new AnimationSet(false);
        int k,j;
        for (k=0;k<CARTASMORTO;k++){
            for (j=MORTO1;j<=MORTO2;j++){
                seqtransition.addAnimation(transfereCarta(MONTE, mao[MONTE].cartas.size() - 1, j, tempo));
            }
        }
        for (k=0;k<CARTASJOG;k++){
            for (j=JOG1;j<=JOG4;j++){
                if (j==JOG1) t=20L; else t=1L;
                seqtransition.addAnimation(transfereCarta(MONTE, mao[MONTE].cartas.size() - 1, j, tempo * t));
            }
        }
        return seqtransition;
    }

    private void descarta() {
        //System.out.println("descarta()");
        int index=0, s=0,k;
        for (k=0;k<mao[JOG1].cartas.size();k++){
            if (mao[JOG1].cartas.get(k).selecionada){
                s++;
                index=k;
            }
        }
        if (s==1){
            if (!((mao[JOG1].cartas.get(index).carta==cartaLixo)&&(mao[JOG1].cartas.get(index).naipe==naipeLixo))){
                cartaLixo = 0; //carta válida, pode esquecer a carta única que estava no lixo antes da jogada
                naipeLixo = 0;
                mao[JOG1].cartas.get(index).selecionada = false;
                ja_comprou = false; //permite compra na próxima jogada
                AnimationSet t = new AnimationSet(false);
                t.addAnimation(transfereCarta(JOG1, index, LIXO));
                t.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        AnimationSet tempseq = verificaSeAcabou(JOG1, MESA1);
                        if (tempseq != null) {
                            tempseq.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    if (jogo_acabou) {
                                        oldvez = -1f;
                                        vez = -1f;
                                    } else {
                                        vez = 1.1f;
                                    }

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            tempseq.start();
                        } else {
                            if (jogo_acabou) {
                                oldvez = -1f;
                                vez = -1f;
                            } else {
                                vez = 1.1f;
                            }
                        }
                    }

                @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                t.start();
            } else {
                mao[JOG1].cartas.get(index).fxToggleSelect().start(); //se for a mesma carta que estava sozinha no lixo, não pode descartar
            }
        }
    }

    private AnimationSet botCompra(int jog) {
        //System.out.println("Bot()");

        ////////////////////////////////////
        //Comprando...
        ////////////////////////////////////
        int ms;
        boolean serveProBot = false;
        if (jog==JOG3) ms=0; else ms=1;
        AnimationSet botsplay = new AnimationSet(false);
        botPegouDoLixo.carta = 0;
        botPegouDoLixo.naipe = 0;
        for (ClasseCarta ct:mao[LIXO].cartas) { //se serve na mesa ou se é coringa
            serveProBot = serveNaMesa(ct,ms);
            if (serveProBot) break;
        }
        if (!serveProBot) {
            for (ClasseCarta ct:mao[LIXO].cartas) { //se uma carta serve na mão (talvez redundante com o 'if' de baixo)
                serveProBot = serveNaMao(ct,jog);
                if (serveProBot) break;
            }
        }
        if (!serveProBot) {
            ClasseMao mao_temp = new ClasseMao(); //se o lixo combinado com a mão tem jogo válido
            for (ClasseCarta ct:mao[LIXO].cartas) {
                mao_temp.cartas.add(ct);
            }
            for (ClasseCarta ct:mao[jog].cartas) {
                mao_temp.cartas.add(ct);
            }
            serveProBot = mao_temp.temJogoValido();
        }
        if (!serveProBot) {
            if (mao[LIXO].temJogoValido()) serveProBot = true; //se tem jogo formado no lixo
        }
        int kLixo[] = {8, 8, 8, 7, 6, 5, 4, 4, 4};
        if ((int)Math.floor(Math.random() * kLixo[Math.min(mao[jog].cartas.size(),8)]) < mao[LIXO].cartas.size() || serveProBot) {
            if (mao[LIXO].cartas.size()==1){
                botPegouDoLixo.carta = mao[LIXO].cartas.get(0).carta;
                botPegouDoLixo.naipe = mao[LIXO].cartas.get(0).naipe;
            }
            AnimationSet pt = new AnimationSet(false);
            while (!mao[LIXO].cartas.isEmpty()){
                pt.addAnimation(transfereCarta(LIXO, 0, jog));
            }
            botsplay.addAnimation(pt);
        }
        else {
            botsplay.addAnimation(transfereCarta(MONTE, mao[MONTE].cartas.size()-1, jog));
        }
        return botsplay;
    }

    private AnimationSet botDesce(int jog) {
        //////////////////////////
        //Descendo...
        //////////////////////////

        //////////////////
        //a fazer:
        //priorizar em qual jogo descer
        //////////////////

        AnimationSet descida;
        AnimationSet botsplay = new AnimationSet(false);
        ClasseMao mao_temp, mtmp;
        boolean serviu, fazdenovo, podedescer;
        int ms, np;

        if (jog==JOG3) ms=0;
        else ms=1;

        for(ClasseMao cMao:mesa[ms].maos){
            cMao.anim = new AnimationSet(false);
        }

        fazdenovo = true;
        while (fazdenovo){ //sempre que desce um jogo ou uma carta, refaz o procedimento
            fazdenovo = false;
            //DESCE CARTAS QUE SERVEM
            serviu = true;
            while (serviu) { //sempre que servir uma carta, verifica a mão novamente pra encontrar outra carta que sirva
                serviu = false;
                if (!mesa[ms].maos.isEmpty()){ //se não tem carta na mesa, as cartas da mão não vão servir em lugar nenhum
                    if (mao[jog].cartas.size() == mao[jog].contaCarta("2")) { //só sobrou coringa na mão
                        System.out.println("[Jogador " + (jog+1) + "] Só sobrou coringa.");
                        for (int ct = mao[jog].cartas.size()-1; ct>=0; ct--) {
                            for (int t = 3; t < 15; t++) { //procura o menor jogo em que o coringa sirva
                                //procura o menor jogo em que o coringa sirva
                                for (ClasseMao iMao:mesa[ms].maos) {
                                    if (iMao.cartas.size() == t) {
                                        mao_temp = new ClasseMao();
                                        for (ClasseCarta crt:iMao.cartas){
                                            //mão temporária com o jogo da mesa
                                            mao_temp.cartas.add(crt);
                                        }
                                        mao_temp.cartas.add(mao[jog].cartas.get(ct));
                                        if (mao_temp.jogoValido().valido){
                                            mao[jog].cartas.get(ct).selecionada = true;
                                            descida = desceSelecionadas(jog,mesa[ms].maos.indexOf(iMao));
                                            if (descida!=null){
                                                System.out.println("[Jogador " + (jog+1) + "] Vou jogar esse coringa nesse joguinho que só tem " + t + " cartas.");
                                                iMao.anim.addAnimation(descida);
                                                serviu = true;
                                                atualizaPontos();
                                            }
                                            if (serviu) break;
                                        }
                                    }
                                }
                                if (serviu) break;
                            }
                            if (serviu) break;
                        }
                    }

                    //procura cartas que servem (mesmo naipe de coringa)
                    for (int m = 0; m < mesa[ms].maos.size(); m++){ //de jogo em jogo da mesa... (falta definir prioridade)
                        for (int k = mao[jog].cartas.size()-1; k >= 0; k--){ //...verifica cada carta da mão pra ver se serve
                            if (mesa[ms].maos.get(m).naipe() == mao[jog].cartas.get(k).naipe) { //mas só olha as do mesmo naipe do jogo da mesa
                                podedescer = true;
                                mao_temp = new ClasseMao();
                                for (ClasseCarta crt:mesa[ms].maos.get(m).cartas){
                                    //mão temporária com o jogo da mesa
                                    mao_temp.cartas.add(crt);
                                }
                                mao_temp.cartas.add(mao[jog].cartas.get(k)); //adiciona a carta à mão temporária
                                if  (
                                        (
                                                (
                                                        mao_temp.contaCarta("2") > 1
                                                ) //dois coringas no jogo
                                                        &&
                                                        (
                                                                mao[jog].cartas.get(k).carta == 2
                                                        )
                                        ) //e descendo coringa
                                                &&
                                                (
                                                        (
                                                                mao[jog].cartas.size() > 2
                                                        ) //bastante carta na mão
                                                                ||
                                                                (
                                                                        mao[jog].cartas.size() - mao[jog].contaCarta("2") > 2
                                                                )
                                                )
                                        ) { //ou está cheio de coringa
                                    //evita descer coringa em jogo que já tem coringa, exceto se for pra bater ou se está cheio de coringa na mão
                                    System.out.println("[Jogador " + (jog+1) + "] Não vou sujar esse jogo de " + this.naipes[mao[jog].cartas.get(k).naipe-1] + " agora.");
                                    podedescer = false;
                                }

                                if (mao_temp.jogoValido().valido){ //verifica se o jogo vai ficar válido
                                    if ((mesa[ms].maos.get(m).limpa())
                                            //&&(mao[jog].cartas.size()>=2)
                                            &&(mao_temp.suja())){
                                        System.out.println("[Jogador " + (jog+1) + "] Não quero sujar uma canastra limpa.");
                                        podedescer=false;
                                    }
                                    if (mao_temp.limpa()
                                            || (podedescer
                                            && !(mao[jog].cartas.size()<=2
                                            && (mesa[ms].pegouMorto || (mao[MORTO1].cartas.isEmpty() && mao[MORTO2].cartas.isEmpty()))
                                            && !mesa[ms].temLimpa()
                                    )
                                    )
                                            ) {
                                        //não pode descer se for bater e não tiver limpa, a não ser que o jogo onde vai descer vai virar uma limpa
                                        mao[jog].cartas.get(k).selecionada = true;
                                        descida = desceSelecionadas(jog,m);
                                        if (descida!=null){
                                            System.out.println("[Jogador " + (jog+1) + "] Ess" + (this.cartas[mao_temp.cartas.get(mao_temp.cartas.size()-1).carta - 1]) + " de " +  (this.naipes[mao_temp.cartas.get(mao_temp.cartas.size()-1).naipe - 1]) + " serviu.");
                                            mesa[ms].maos.get(m).anim.addAnimation(descida);
                                            serviu = true;
                                            atualizaPontos();
                                        }
                                    }
                                }
                            }
                            if (serviu) break;
                        }//next carta da mão
                        if (serviu) break;
                    }//next jogo da mesa
                }//end if mesa[ms].maos.isEmpty()
            }//end while serviu

            //DESCE JOGOS

            //se só sobrou um jogo válido na mão, bate
            if (mao[jog].jogoValido().valido){
                for (ClasseCarta cc:mao[jog].cartas){
                    cc.selecionada=true;
                }
                descida = desceSelecionadas(jog,-1);
                if (descida!=null){
                    System.out.println("[Jogador " + (jog+1) + "] Olha, ficou só um jogo na minha mão, desci e bati.");
                    mesa[ms].maos.get(mesa[ms].maos.size()-1).anim.addAnimation(descida);
                    fazdenovo = true;
                }
            }

            //procura jogos de 3 cartas pra descer
            for (np=ESPADAS;np<=OUROS;np++){
                mtmp = new ClasseMao(); //sub-mão temporária com cartas do mesmo naipe
                for (ClasseCarta c:mao[jog].cartas) {
                    if (c.naipe==np) mtmp.cartas.add(c);
                }
                if (mtmp.cartas.size()>=3){ //se não tem nem 3 cartas desse naipe, não precisa tentar descer
                    //System.out.println("tem pelo menos 3 cartas do mesmo naipe");
                    for (int k1=0;k1<mtmp.cartas.size()-2;k1++){
                        for (int k2=k1+1;k2<mtmp.cartas.size()-1;k2++){
                            for (int k3=k2+1;k3<mtmp.cartas.size();k3++){
                                mao_temp = new ClasseMao(); //sub-mão de mtmp com combinação de 3 cartas
                                mao_temp.cartas.add(mtmp.cartas.get(k1));
                                mao_temp.cartas.add(mtmp.cartas.get(k2));
                                mao_temp.cartas.add(mtmp.cartas.get(k3));
                                //não pode descer se for bater e não tiver limpa
                                if ((!(mao_temp.contaCarta("2")==2 && mao[MONTE].cartas.size()>5))&&mao_temp.jogoValido().valido&&(!((mao[jog].cartas.size()-mao_temp.cartas.size())<=1&&(mesa[ms].pegouMorto||(mao[MORTO1].cartas.isEmpty()&&mao[MORTO2].cartas.isEmpty())&&!mesa[ms].temLimpa())))){
                                    //System.out.println("jogo valido e vai sobrar mais de 1 carta na mão");
                                    for (ClasseCarta c:mao_temp.cartas){
                                        for (ClasseCarta cc:mao[jog].cartas){
                                            if (c.equals(cc)){
                                                cc.selecionada=true;
                                                //System.out.print("selecionou pra descer");
                                            }
                                        }
                                    }
                                    descida = desceSelecionadas(jog,-1);
                                    if (descida!=null){
                                        System.out.println("[Jogador " + (jog+1) + "] Desci um jogo de " +  this.naipes[np - 1] + ".");
                                        mesa[ms].maos.get(mesa[ms].maos.size()-1).anim.addAnimation(descida);
                                        fazdenovo = true;
                                    }
                                }
                                if (fazdenovo) break;
                            } //next k3
                            if (fazdenovo) break;
                        } //next k2
                        if (fazdenovo) break;
                    } //next k1
                }
                if (fazdenovo) break;
            } //next np
        } //end while fazdenovo
        for (ClasseMao cMao:mesa[ms].maos){
            if (cMao.anim!=null && !cMao.anim.getAnimations().isEmpty()){
                botsplay.addAnimation(cMao.anim);
                cMao.anim = new AnimationSet(false);
            }
        }
        AnimationSet tempseq = verificaSeAcabou(jog,ms);
        if (tempseq!=null) {
            botsplay.addAnimation(tempseq);
        }
        return botsplay;
    }

    private AnimationSet botDescarta(int jog) {
        //////////////////////////
        //Descartando...
        //////////////////////////
        //boolean descartou = false, serve_adv;
        int k, ms = 1, tentativas = 0;
        AnimationSet tempseq;
        AnimationSet botsplay = new AnimationSet(false);
        if (jog==JOG3) ms=0; //jogador 3 é seu parceiro, desce na mesma mesa que você
        mao[jog].zeraDescarte();
        int menor = 5000;
        for (k=0; k < mao[jog].cartas.size(); k++) {
            ClasseCarta ct = mao[jog].cartas.get(k);
            if (ct.carta == 2) ct.descarte += 150;
            if (serveProAdversario(jog,k)) ct.descarte += ct.valor * 2;
            ct.descarte += (daCanastraProAdversario(jog,k));
            if (ct.carta==botPegouDoLixo.carta && ct.naipe==botPegouDoLixo.naipe) ct.descarte +=10000;
            if (mao[jog].temRepetida(ct)) ct.descarte -= 20;
            if (serveNaMesa(ct,ms)) ct.descarte += ct.valor;
            if (serveNaMao(ct,jog)) ct.descarte += 5;
            if (ct.descarte < menor) menor = ct.descarte;
        }
        ClasseMao mao_temp = new ClasseMao();
        for (k=0; k < mao[jog].cartas.size(); k++) {
            if (mao[jog].cartas.get(k).descarte == menor) {
                mao_temp.cartas.add(mao[jog].cartas.get(k));
            }
        }
        if (mao_temp.cartas.isEmpty()) {
            k = (int)Math.floor(Math.random()*mao[jog].cartas.size());
            if (k<0) k=0;
            if (k>mao[jog].cartas.size()-1) k = mao[jog].cartas.size()-1;
            tentativas++;
        } else {
            k = (int)Math.floor(Math.random()*mao_temp.cartas.size());
            if (k<0) k=0;
            if (k>mao_temp.cartas.size()-1) k = mao_temp.cartas.size()-1;
            k = mao[jog].cartas.indexOf(mao_temp.cartas.get(k));
        }

        System.out.println("[Jogador " + (jog+1) + "] Depois de " + (tentativas+1) + " tentativa(s), descartei ess" + cartas[mao[jog].cartas.get(k).carta - 1] + " de " + naipes[mao[jog].cartas.get(k).naipe - 1] + ".");
        mao[jog].deSeleciona();
        mao[jog].zeraDescarte();
        botsplay.addAnimation(transfereCarta(jog,k,LIXO));
        tempseq = verificaSeAcabou(jog,ms); //vê se acabou o jogo, senão pega o morto
        if (tempseq!=null){
            botsplay.addAnimation(tempseq); //faz a animação do morto
        }
        return botsplay;
    }

    private AnimationSet desceSelecionadas(int j, int iMao) {
        //System.out.println("desceSelecionadas()"+j+" "+m+" "+juntajogo);
        boolean eraSuja = false;
        boolean eraLimpa = false;
        boolean descendoCoringa = false;
        boolean descendoAs = false;
        AnimationSet tempseq;
        AnimationSet jPlay = new AnimationSet(false);
        ClasseMao mao_temp = new ClasseMao();
        int ms, k, limpas;
        if ((j==JOG1)||(j==JOG3)) ms=0; else ms=1;
        if (iMao!=-1){
            eraLimpa = mesa[ms].maos.get(iMao).limpa();
            eraSuja = mesa[ms].maos.get(iMao).suja();
        }
        boolean vaiDescerNaUnicaLimpa=false, desceu=false, podedescer=false;
        for (k=mao[j].cartas.size()-1;k>=0;k--){
            if (mao[j].cartas.get(k).selecionada){
                mao_temp.cartas.add(mao[j].cartas.get(k));
                //System.out.println("selecionou pra descer");
            }
        }
        if (mao_temp.cartas.isEmpty()){
            //System.out.println("mao vazia");
            return null;
        }
        if (mao_temp.cartas.size()==1) {
            if (mao_temp.cartas.get(0).carta == 2) descendoCoringa = true;
            if (mao_temp.cartas.get(0).carta == 1) descendoAs = true;
        }
        if (mao[j].cartas.size()-mao_temp.cartas.size()<=1) { //se vai sobrar 1 ou nenhuma carta na mão
            //se não pegou morto, mas ainda tem morto
            if (!mesa[ms].pegouMorto&&((!mao[MORTO1].cartas.isEmpty())||(!mao[MORTO2].cartas.isEmpty()))){
                podedescer=true; //pode descer
            }
            else { //se vai bater (morto: já pegou e não tem mais, já pegou e tem, ou ainda não pegou mas não tem => vai bater)
                if (mesa[ms].temLimpa()) { //tem que ter limpa
                    limpas=0;
                    for (ClasseMao mm:mesa[ms].maos){
                        if ((mesa[ms].maos.indexOf(mm)!=iMao)&&(mm.limpa())){
                            limpas++;
                        }
                    }
                    if (limpas==0) vaiDescerNaUnicaLimpa=true;
                    podedescer=true;
                }
            }
        }
        else {
            podedescer=true;
        }
        if (iMao>-1){
            for (ClasseCarta crk:mesa[ms].maos.get(iMao).cartas){
                mao_temp.cartas.add(crk);
            }
            if (j!=JOG1){
                if (vaiDescerNaUnicaLimpa && !mao_temp.limpa()) { //por favor não suje a única limpa
                    //System.out.println("vai descer na limpa");
                    System.out.println("[Jogador " + (j+1) + "] Não vou sujar a única limpa.");
                    podedescer=false;
                }
            }
        }
        if (!podedescer&&mao_temp.jogoValido().valido&&mao_temp.limpa()){
            podedescer=true;
        }
        if (podedescer && iMao==-1 && j!=JOG1 && !mao_temp.limpa()){ //lógica para não separar jogos
            for (int m=0; m<mesa[ms].maos.size(); m++){
                if (mao_temp.naipe()==mesa[ms].maos.get(m).naipe()){
                    ClasseMao mMao = mao_temp.jogoValido().ordem;
                    int ctMaoMin = mMao.cartas.get(0).carta;
                    if (ctMaoMin == 2) ctMaoMin = mMao.cartas.get(1).carta - 1;
                    int ctMesaMax = mesa[ms].maos.get(m).cartas.get(mesa[ms].maos.get(m).cartas.size()-1).carta;
                    if (ctMesaMax == 1) ctMesaMax = 14; //Ás no final
                    if (ctMesaMax == 2) ctMesaMax = mesa[ms].maos.get(m).cartas.get(mesa[ms].maos.get(m).cartas.size()-2).carta + 1; //carta que estaria no lugar do coringa
                    //menor da mão - maior da mesa >=1 e <=2
                    if ((ctMaoMin - ctMesaMax) >= 2 && (ctMaoMin - ctMesaMax) <= 3) {
                        System.out.println("[Jogador " + (j+1) + "] Não vou separar esse jogo de " + naipes[mMao.naipe()-1] + ".");
                        podedescer = false;
                    }
                    int ctMaoMax = mMao.cartas.get(mMao.cartas.size()-1).carta;
                    if (ctMaoMax == 1) ctMaoMax = 14;
                    if (ctMaoMax == 2) ctMaoMax = mMao.cartas.get(mMao.cartas.size()-2).carta + 1;
                    int ctMesaMin = mesa[ms].maos.get(m).cartas.get(0).carta;
                    if (ctMesaMin == 2) ctMesaMin = mesa[ms].maos.get(m).cartas.get(1).carta - 1; //carta que estaria no lugar do coringa
                    //menor da mesa - maior da mão >=1 e <=2
                    if ((ctMesaMin - ctMaoMax) >= 2 && (ctMesaMin - ctMaoMax) <= 3) {
                        System.out.println("[Jogador " + (j+1) + "] Não vou separar esse jogo de " + naipes[mMao.naipe()-1] + ".");
                        podedescer = false;
                    }
                }
            }
            if ((!podedescer) && (mao[MONTE].cartas.size() <= 6 || mao[j].cartas.size()<=4)) {
                System.out.println("[Jogador " + (j+1) + "] Se bem que está no final do jogo (ou da minha mão), vou separar sim.");
                podedescer = true;
            }
        }
        if (podedescer && iMao==-1 && j!=JOG1) { // não suja alto no começo do jogo
            if (mao_temp.contaCarta("2") == 1) {
                if ((mao_temp.cartas.get(0).carta > 8) || (mao_temp.cartas.get(1).carta > 8) || (mao_temp.cartas.get(2).carta > 8) || (mao_temp.jogoValido().ordem.converte().contains("A23"))) {
                    System.out.println("[Jogador " + (j+1) + "] Não vou sujar alto ou prender o ás nesse jogo de " + naipes[mao_temp.naipe()-1] + ".");
                    podedescer = false;
                }
            }
            if ((!podedescer) && (mao[MONTE].cartas.size() <= 6 || mao[j].cartas.size()<=4)) {
                System.out.println("[Jogador " + (j+1) + "] Se bem que está no final do jogo (ou da minha mão), vou sujar sim.");
                podedescer = true;
            }
        }

        if ((iMao > -1) && (descendoCoringa) && (j!=JOG1)) { //está descendo coringa
            int menor = 15;
            for (ClasseCarta ct:mesa[ms].maos.get(iMao).cartas) {
                if ((ct.carta >= 3) && (ct.carta < menor)) {
                    menor = ct.carta;
                }
            }
            if (menor >= 7) { //está sujando jogo alto
                podedescer = false;
                System.out.println("[Jogador " + (j+1) + "] Não vou sujar alto ou prender o ás nesse jogo de " + naipes[mao_temp.naipe()-1] + ".");
            }
            if ((!podedescer) && (mao[MONTE].cartas.size() <= 6 || mao[j].cartas.size()<=4)) {
                System.out.println("[Jogador " + (j+1) + "] Se bem que está no final do jogo (ou da minha mão), vou sujar sim.");
                podedescer = true;
            }
        }

        if ((iMao > -1) && (descendoAs) && (j!=JOG1)) { //está descendo Ás
            int menor = 15;
            for (ClasseCarta ct:mesa[ms].maos.get(iMao).cartas) {
                if ((ct.carta >= 3) && (ct.carta < menor)) { //procura a menor carta, sem contar AS e 2.
                    menor = ct.carta;
                }
            }
            if ((menor == 3) && mao_temp.cartas.size() < 6) { //está prendendo ás em jogo pequeno
                podedescer = false;
                System.out.println("[Jogador " + (j+1) + "] Não vou prender o ás nesse jogo de " + naipes[mao_temp.naipe()-1] + ".");
            }
            if ((!podedescer) && (mao[MONTE].cartas.size() <= 6 || mao[j].cartas.size()<=4)) {
                System.out.println("[Jogador " + (j+1) + "] Se bem que está no final do jogo (ou da minha mão), vou prender sim.");
                podedescer = true;
            }
        }

        if (podedescer){
            //System.out.println("pode descer");
            ClasseOrdem ord = mao_temp.jogoValido();
            if (ord.valido){
                //System.out.println("jogo valido");
                desceu=true;
                AnimationSet pt = new AnimationSet(false);
                if (iMao>-1) {
                    //ordena o jogo da mesa antes de receber as novas cartas - O PROBLEMA ESTÁ AQUI ???
                    pt.addAnimation(ordenaJogo(ms,iMao,ord.ordem));
                } else {
                    mesa[ms].maos.add(new ClasseMao());
                    iMao = mesa[ms].maos.size()-1;
                    root.addView(mesa[ms].maos.get(iMao).tarja); //adiciona a tarja ao cenário, inicialmente invisível.
                    PointF pos = mesa[ms].posMao(iMao,larguraCarta());
                    mesa[ms].maos.get(iMao).setPosInit(pos.x, pos.y, 10, 0);
                }
                //transfere as novas cartas
                while (mao[j].contaSelecionadas()>0){
                    for (ClasseCarta crk:mao[j].cartas){
                        if (crk.selecionada){
                            pt.addAnimation(transfereCarta(j,mao[j].cartas.indexOf(crk),ms+8,iMao,ord.ordem));
                            break;
                        }
                    }
                }
                if (!eraLimpa && mesa[ms].maos.get(iMao).limpa()){
                    //fez uma limpa!!!
                    //Timeline tl = new Timeline();
                    //tl.getKeyFrames().add(new KeyFrame(Duration.millis(0),playLimpa));
                    //tl.getKeyFrames().add(new KeyFrame(Duration.millis(2500),stopLimpa)); //1854
                    //pt.getChildren().add(tl);
                    pt.addAnimation(mesa[ms].maos.get(iMao).fxMostraTarja("LIMPA",larguraCarta()));
                }
                else if (!eraSuja && mesa[ms].maos.get(iMao).suja()){
                    //fez uma suja!!!
                    //Timeline tl = new Timeline();
                    //tl.getKeyFrames().add(new KeyFrame(Duration.millis(0.0),playSuja));
                    //tl.getKeyFrames().add(new KeyFrame(Duration.millis(2000),stopSuja)); //1416
                    //pt.getChildren().add(tl);
                    pt.addAnimation(mesa[ms].maos.get(iMao).fxMostraTarja("SUJA",larguraCarta()));
                }
                else if (eraLimpa && mesa[ms].maos.get(iMao).limpa()){
                    pt.addAnimation(mesa[ms].maos.get(iMao).fxMoveTarja(larguraCarta()));
                }
                else if (eraSuja && mesa[ms].maos.get(iMao).suja()){
                    pt.addAnimation(mesa[ms].maos.get(iMao).fxMoveTarja(larguraCarta()));
                }
                jPlay.addAnimation(pt);
                if (mao[j].cartas.isEmpty()){
                    tempseq = verificaSeAcabou(j,ms);
                    if (tempseq!=null){
                        jPlay.addAnimation(tempseq);
                    }
                }
                atualizaPontos();
            } else {
                for (ClasseCarta crt:mao[j].cartas) {
                    if (crt.selecionada) {
                        if (j==JOG1) crt.fxToggleSelect().start();
                    } else {
                        crt.selecionada = false;
                    }
                }
            }
        } else {
            for (ClasseCarta crt:mao[j].cartas) {
                if (crt.selecionada) {
                    if (j==JOG1) crt.fxToggleSelect().start();
                    else crt.selecionada = false;
                }
            }
        }
        //System.out.println(desceu);
        if (desceu){
            return jPlay;
        }
        else {
            return null;
        }
    }

    private AnimationSet ordenaJogo(int ms, int m, ClasseMao maoOrdem){

        ClasseMao maoDestino = mesa[ms].maos.get(m);
        ClasseMao ordem = new ClasseMao();
        ordem.cartas.addAll(maoOrdem.cartas);

        //remove tudo de 'ordem' que não está em 'maoDestino'
        for (int ctOrdem = ordem.cartas.size()-1;ctOrdem>=0;ctOrdem--){
            boolean remove=true;
            for (int ctMao = maoDestino.cartas.size()-1;ctMao>=0;ctMao--){
                if ((ordem.cartas.get(ctOrdem).carta==maoDestino.cartas.get(ctMao).carta)&&(ordem.cartas.get(ctOrdem).naipe==maoDestino.cartas.get(ctMao).naipe)&&(ordem.cartas.get(ctOrdem).cor==maoDestino.cartas.get(ctMao).cor)){
                    remove=false;
                }
            }
            if (remove) ordem.cartas.remove(ctOrdem);
        }

        //ordena 'maoDestino' como em ordem
        for (int ctOrdem = 0; ctOrdem < ordem.cartas.size(); ctOrdem++){
            for (int ctMao = 0; ctMao<maoDestino.cartas.size();ctMao++){
                if ((ordem.cartas.get(ctOrdem).carta==maoDestino.cartas.get(ctMao).carta)&&(ordem.cartas.get(ctOrdem).naipe==maoDestino.cartas.get(ctMao).naipe)&&(ordem.cartas.get(ctOrdem).cor==maoDestino.cartas.get(ctMao).cor)){
                    ClasseCarta ct = maoDestino.cartas.remove(ctMao);
                    maoDestino.cartas.add(ctOrdem,ct);
                }
            }
        }

        //organiza o zOrder
        for (int ctMao = 0; ctMao<maoDestino.cartas.size();ctMao++){
            int menor = 1000;
            int iMenor = -1;
            for (int k = ctMao; k<maoDestino.cartas.size();k++){
                if (maoDestino.cartas.get(k).zOrder < menor){
                    menor = maoDestino.cartas.get(k).zOrder;
                    iMenor = k;
                }
            }
            maoDestino.cartas.get(iMenor).zOrder = maoDestino.cartas.get(ctMao).zOrder;
            maoDestino.cartas.get(ctMao).zOrder = menor;
        }

        AnimationSet pt = new AnimationSet(false);
        for (int k=0;k<maoDestino.cartas.size();k++){
            //Define a posição das cartas da mão para abrir espaço para a carta que vai entrar
            maoDestino.cartas.get(k).posX = (float)Math.rint(maoDestino.posInitX+maoDestino.deltaX*k);
            maoDestino.cartas.get(k).posY = (float)Math.rint(maoDestino.posInitY+maoDestino.deltaY*k);
            pt.addAnimation(
                    arrumaCarta(maoDestino.cartas.get(k), true)    //e todas se movendo para o lugar correto
            );
        }
        return pt;
    }

    private void terminaJogo() {
        //System.out.println("terminaJogo()");
        masterListener.cancel();
        String resultado;
        int[] pt={mesa[0].somaPontos()-mao[JOG1].somaPontos()-mao[JOG3].somaPontos(),mesa[1].somaPontos()-mao[JOG2].somaPontos()-mao[JOG4].somaPontos()};
        if (pt[0]>=pt[1]){
            //APLAUSO.play();
            resultado = "Ganhamos!!!";
        }
        else {
            //CHORO.play();
            resultado = "Perdemos!";
        }
        Region mensagem = new Region();
        mensagem.set(smallRes ? 0 : 341, smallRes ? 0 : 192, (smallRes ? 0 : 341) + (smallRes ? 240 : 683), (smallRes ? 0 : 192) + (smallRes ? 320 : 384));
        //mensagem.setStyle("-fx-background-color: #333333;-fx-background-radius: 5.0;");
        //mensagem.setOpacity(0.5);
        TextView texto = new TextView(null);
        resultado = resultado + "\nNosso jogo: " + pt[0] + "\nJogo deles: " + pt[1];
        if (smallRes){
            texto.setTextSize(8f);
            //texto.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 8pt; -fx-opacity:0.8;");
        } else {
            texto.setTextSize(20f);
            //texto.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 20pt; -fx-opacity:0.8;");
        }

        texto.setText(resultado);
        texto.setTranslationX(smallRes?10:500);
        texto.setTranslationY(smallRes?10:300);
        texto.setWidth(smallRes?220:400);
        texto.setHeight(smallRes?300:200);

        //root.addView(mensagem);
        root.addView(texto);
        //System.out.println("Acabou: " + pt[0] + " pra nós e " + pt[1] + " pra eles.");
        //reiniciaJogo();
    }

    private AnimationSet verificaSeAcabou(int j, int m) {
        //System.out.println("verificaSeAcabou()"+j+" "+m);
        AnimationSet seq = null;
        AnimationSet seq1 = null;
        AnimationSet seq2 = null;
        if (m==MESA1) m=0;
        if (m==MESA2) m=1;
        if (mao[j].cartas.isEmpty()) {
            if (mesa[m].pegouMorto) {
                mesa[m].bateu = true;
                jogo_acabou = true;
                seq1 = null;
            }
            else {
                seq1 = pegaMorto(j);
            }
        }
        if ((!jogo_acabou)&&(mao[MONTE].cartas.isEmpty())) {
            seq2 = pegaMorto(MONTE);
        }
        if (seq1==null&&seq2==null) seq = null;
        if (seq1==null&&seq2!=null) seq = seq2;
        if (seq1!=null&&seq2==null) seq = seq1;
        if (seq1!=null&&seq2!=null) {
            seq.addAnimation(seq1);
            seq.addAnimation(seq2);
        }
        return seq;
    }

    private AnimationSet pegaMorto(int j) {
        //System.out.println("pegaMorto()"+j);
        if (mao[MORTO1].cartas.isEmpty()&&mao[MORTO2].cartas.isEmpty()) {
            jogo_acabou = true;
            return null;
        }
        else {
            AnimationSet botsplay = new AnimationSet(false);
            int k,m;
            if (!mao[MORTO1].cartas.isEmpty()){
                m=MORTO1;
            }
            else {
                m=MORTO2;
            }
            //mao[m].ordenaNaipe();
            //botsplay = new AnimationSet (botsplay,mao[m].arruma(false));
            AnimationSet pt = new AnimationSet(false);
            for (k=CARTASMORTO-1;k>=0;k--) {
                pt.addAnimation(transfereCarta(m, k, j));
            }
            if ((j==JOG1)||(j==JOG3)) mesa[0].pegouMorto = true;
            if ((j==JOG2)||(j==JOG4)) mesa[1].pegouMorto = true;
            botsplay.addAnimation(pt);
            return botsplay;
        }
    }

    private void atualizaPontos(){
        lblMesa1.setText(Integer.toString(mesa[0].somaPontos()));
        lblMesa2.setText(Integer.toString(mesa[1].somaPontos()));
    }

    private float calculaAngulo(int k, int n){
        if (n==1){
            return 0f;
        } else {
            float theta = 0.7699f * (float)n * (float)n - 16.402f * (float)n + 135.18f;
            theta = (float)Math.min(theta, Math.min((float)n*5f,30f));
            return ((-theta/2)+(float)k*(theta/((float)n-1f)))*(float)Math.PI/180f;
        }
    }

    private float calculaX(int k, int n){
        float R = 1.7776f * (float)n * (float)n + 5.3827f * (float)n + 32.687f;
        R = (float)Math.max(R, 100f);
        return R * (float)Math.sin(calculaAngulo(k,n));
    }

    private float calculaY(int k, int n){
        float R = 1.7776f * (float)n * (float)n + 5.3827f * (float)n + 32.687f;
        R = Math.max(R, 100f);
        return R * ((float)Math.cos(calculaAngulo(k,n)) - 1f);
    }

    /*
    private float corrigeX(int k, int n){
        float theta0 = -(0.7714 * n * n - 16.409 * n + 135.21)/2.0;
        float deltatheta = calculaAngulo(k, n);
        float R = 1.7766 * n * n + 5.4018 * n + 32.649;
        return R * Math.sin(theta0 + deltatheta);
    }

    private float corrigeY(int k, int n){
        float theta0 = -(0.7714 * n * n - 16.409 * n + 135.21)/2.0;
        float deltatheta = calculaAngulo(k, n);
        float R = 1.7766 * n * n + 5.4018 * n + 32.649;
        return R * Math.cos(theta0 + deltatheta);

    }
    */

    /*
    private void reiniciaJogo() {
        int i;
        for (i=JOG1;i<=MORTO2;i++) {
            mao[i].esvazia();
        }
        for (i=MESA1;i<=MESA2;i++) {
            for (int m=0;m<mesa[i-8].maos.size();m++) {
                root.getChildren().removeAll(mesa[i-8].maos.get(m).tarja);
            }
            mesa[i-8].esvazia();
            mesa[i-8].bateu=false;
            mesa[i-8].pegouMorto=false;
        }
        atualizaPontos();
        vez = -1;
        oldvez = -1;
        inicializaCartas();
        jogo();
    }
    */

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        //System.out.println("main()"+args);
        launch(args);
    }*/

    private boolean serveProAdversario(int jog, int k) {
        if (k==-1) return false;
        int ms;
        boolean serve_adv = false;
        if (jog == JOG3) ms = 0; else ms = 1;
        for (int m=0;m<mesa[1-ms].maos.size();m++){ //1-ms é a mesa do adversário
            if ((mesa[1-ms].maos.get(m).naipe() == mao[jog].cartas.get(k).naipe) || (mao[jog].cartas.get(k).carta == 2)){ //se é do mesmo naipe ou é coringa
                ClasseMao mao_temp = new ClasseMao();
                for (int ct=mesa[1-ms].maos.get(m).cartas.size()-1;ct>=0;ct--){
                    mao_temp.cartas.add(mesa[1-ms].maos.get(m).cartas.get(ct));
                }
                mao_temp.cartas.add(mao[jog].cartas.get(k));
                if (mao_temp.jogoValido().valido){ //...e serve para o adversário...
                    serve_adv=true; //...não descarta e refaz o loop.
                    break;
                }
            }
        } //next m
        return serve_adv;
    }

    private int daCanastraProAdversario(int jog, int k) {
        int pontos = 0, maxpontos = 0;
        if (k==-1) return 0;
        if (!serveProAdversario(jog,k)) return 0;
        int ms;
        if (jog == JOG3) ms = 0; else ms = 1;
        if (mesa[1-ms].maos.isEmpty()) return 0;
        for (int m=0;m<mesa[1-ms].maos.size();m++){ //1-ms é a mesa do adversário
            if ((mesa[1-ms].maos.get(m).naipe() == mao[jog].cartas.get(k).naipe) || (mao[jog].cartas.get(k).carta == 2)){ //se é do mesmo naipe ou é coringa
                ClasseMao mao_temp = new ClasseMao();
                for (int ct=mesa[1-ms].maos.get(m).cartas.size()-1;ct>=0;ct--){
                    mao_temp.cartas.add(mesa[1-ms].maos.get(m).cartas.get(ct));
                }
                if (mao_temp.real()) pontos -= 1000;
                else if (mao_temp.semireal()) pontos -= 500;
                else if (mao_temp.limpa()) pontos -= 200;
                else if (mao_temp.suja()) pontos -= 100;
                mao_temp.cartas.add(mao[jog].cartas.get(k));
                if (mao_temp.jogoValido().valido){ //...e serve para o adversário...
                    if (mao_temp.real()) pontos += 1000;
                    else if (mao_temp.semireal()) pontos += 500;
                    else if (mao_temp.limpa()) pontos += 200;
                    else if (mao_temp.suja()) pontos += 100;
                }
                if (pontos<0) pontos = 0;
            }
            if (pontos>maxpontos) maxpontos = pontos;
        } //next m
        return maxpontos;
    }

    private boolean serveNaMesa(ClasseCarta ct, int ms) {
        boolean serve = false;
        if (ct.carta==2){
            return true;
        }
        for (int m=0;m<mesa[ms].maos.size();m++){ //ms é a mesa do Bot
            if (mesa[ms].maos.get(m).naipe()==ct.naipe){ //se é do mesmo naipe...
                ClasseMao mao_temp = new ClasseMao();
                for (int crt=mesa[ms].maos.get(m).cartas.size()-1;crt>=0;crt--){
                    mao_temp.cartas.add(mesa[ms].maos.get(m).cartas.get(crt));
                }
                mao_temp.cartas.add(ct);
                if (mao_temp.jogoValido().valido){ //...e serve para o bot...
                    serve=true; //...não descarta e refaz o loop.
                }
            }
            if (serve) break;
        }
        return serve;
    }

    private boolean serveNaMao(ClasseCarta ct, int jog) {
        ClasseMao mao_temp = new ClasseMao();
        for (ClasseCarta crt:mao[jog].cartas) {
            mao_temp.cartas.add(crt);
        }
        if (!mao_temp.cartas.contains(ct)) mao_temp.cartas.add(ct);
        return mao_temp.temJogoValido();
    }
/*
    private AnimationSet animacao(int ms) {
        AnimationSet botsplay = new AnimationSet(false);
        for (ClasseMao cMao:mesa[ms].maos){
            if (cMao.anim!=null && !cMao.anim.getAnimations().isEmpty()){
                botsplay.addAnimation(cMao.anim);
                cMao.anim = new AnimationSet(false);
            }
        } // adiciona as animações na variável de retorno
        return botsplay; //se acabou o jogo, retorna, senão faz mais um loop
    }
*/

    /*
        EventHandler<ActionEvent> playCarta = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                CARTA.play();
            }
        };

        EventHandler<ActionEvent> stopCarta = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                CARTA.stop();
            }
        };

        EventHandler<ActionEvent> playSuaVez = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                SUA_VEZ.play();
            }
        };
        EventHandler<ActionEvent> stopSuaVez = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                SUA_VEZ.stop();
            }
        };

        EventHandler<ActionEvent> playChoro = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                CHORO.play();
            }
        };

        EventHandler<ActionEvent> playAplauso = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                APLAUSO.play();
            }
        };

        EventHandler<ActionEvent> playLimpa = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                LIMPA.play();
            }
        };

        EventHandler<ActionEvent> playSuja = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                SUJA.play();
            }
        };

        EventHandler<ActionEvent> stopLimpa = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                LIMPA.stop();
            }
        };

        EventHandler<ActionEvent> stopSuja = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                SUJA.stop();
            }
        };
    */
    private void inicializaControles() {
        if (smallRes){
            lixo.set(50,120,50+180,120+50);
            mesadejogos.set(10, 175, 10 + 220, 175 + 105);
            lblMesa1.setTranslationX(210f);
            lblMesa1.setTranslationY(0f);
            lblMesa2.setTranslationX(210f);
            lblMesa2.setTranslationY(300f);
            lblMesa1.setWidth(30);
            lblMesa1.setHeight(20);
            lblMesa2.setWidth(30);
            lblMesa2.setHeight(20);
        } else {
            lixo.set(683, 276, 683 + 400, 276 + 100);
            mesadejogos.set(283, 386, 283 + 800, 386 + 210);
            lblMesa1.setTranslationX(230f);
            lblMesa1.setTranslationY(386f);
            lblMesa2.setTranslationX(230f);
            lblMesa2.setTranslationY(56f);
            lblMesa1.setWidth(40);
            lblMesa1.setHeight(20);
            lblMesa2.setWidth(40);
            lblMesa2.setHeight(20);
        }
        /*
        lixo.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent event) {
                event.consume();
                lixo_click();
            }
        });
        */
        //root.getChildren().add(lixo); //lixo zOrder 0
        /*
        mesadejogos.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent event) {
                event.consume();
                mesadejogos_click();
            }
        });
        */
        //root.getChildren().add(mesadejogos); //mesa zOrder 1
        for (int k=0;k<mao[MONTE].cartas.size();k++){
            mao[MONTE].cartas.get(k).zOrder=k+2; //vai de 2 a 105
            root.addView(mao[MONTE].cartas.get(k), (int) (larguraCarta()), (int) (alturaCarta()));
        }
        root.addView(lblMesa1,lblMesa1.getWidth(),lblMesa1.getHeight());
        root.addView(lblMesa2,lblMesa2.getWidth(),lblMesa2.getHeight());
    }

    public float larguraCarta() {
        if (smallRes) return 35f*factor; else return 70f;
    }

    public float alturaCarta() {
        if (smallRes) return 50f*factor; else return 100f;
    }
}