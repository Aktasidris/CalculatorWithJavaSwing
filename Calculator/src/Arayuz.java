import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
public class Arayuz extends JFrame{
    String sayi1="";
    String sayi2="";
    String sayi3="";
    double sonuc;
    String opt2="";
    String opt1="";
    //dizi static çünkü kullanıldığı metod static
    static String []dizi={"+","-","*","/"};

    public void Hesapla(double x,String operator,double y){
        switch (operator) {
            case "+" -> sonuc = x + y;
            case "-" -> sonuc = x - y;
            case "*" -> sonuc = x * y;
            case "/" -> sonuc = x / y;
        }
    }
    public boolean SonIndexKontrol(String text){
        char sonindex=text.charAt(text.length()-1);
        boolean islem_mi=false;
        for(String i:dizi){
            //i.charAt(0) dizideki değer
            if(i.charAt(0)==sonindex){
                islem_mi=true;
                break;
            }
        }return islem_mi;
    }
    public boolean SonIndexKontrol(String text,String btntxt){
        char sonindex=text.charAt(text.length()-1);
        boolean islem_mi=false;
        for(String i:dizi){
            if(i.charAt(0)==sonindex){
                if(text.length()>=1){
                    if(sonindex=='-'&& !Objects.equals(btntxt, "-")){
                        //-* || -/ || -+ önlemek için bişey yapma
                        StringBuilder sb=new StringBuilder(text);
                        text=sb.deleteCharAt(text.length()-1).toString();
                        textbox.setText(text+btntxt);

                    }else if(sonindex=='-'&& btntxt.equals("-")){
                        //-- ise bişey yapma
                    }
                }else{
                    islem_mi=true;
                }
            }
        }return islem_mi;
    }
    public void SonindexGuncelle(String deger,String btntext){
        StringBuilder sb=new StringBuilder(deger);
        deger=sb.deleteCharAt(deger.length()-1).toString();
        textbox.setText(deger+btntext);
    }
    //2+2+2+=2+4+
    //2*2+2*=4+2*
    //2*2*2-=2*4-
    //2+2*2/=2+4/
    public void OnceligeGoreIslemYap(String sayi1,String sayi2,String sayi3,String opt1,String opt2){
        if (opt1.equals("*")||opt1.equals("/")){
            Hesapla(Double.parseDouble(sayi1),opt1,Double.parseDouble(sayi2));
            sayi1=Double.toString(sonuc);
            opt1=opt2;
            sayi2=sayi3;
            if(opt2.equals("")){
                textbox.setText(sayi1+opt1);
            }else{
                textbox.setText(sayi1+opt1+sayi2);
            }
            opt2="";
            sayi3="";
        }else if(opt2.equals("*")||opt2.equals("/")){
            Hesapla(Double.parseDouble(sayi2),opt2,Double.parseDouble(sayi3));
            sayi2=Double.toString(sonuc);
            opt2="";
            sayi3="";
            textbox.setText(sayi1+opt1+sayi2);
        }else if(opt2.equals("")&&!opt1.equals("")){
            Hesapla(Double.parseDouble(sayi1),opt1,Double.parseDouble(sayi2));
            sayi1=Double.toString(sonuc);
            sayi2="";
            opt1="";
            textbox.setText(sayi1);
        }else if(!opt2.equals("")&&!opt1.equals("")) {
            Hesapla(Double.parseDouble(sayi2),opt2,Double.parseDouble(sayi3));
            sayi2=Double.toString(sonuc);
            sayi3="";
            opt2="";
            textbox.setText(sayi1+opt1+sayi2);
            Hesapla(Double.parseDouble(sayi1),opt1,Double.parseDouble(sayi2));
            sayi1=Double.toString(sonuc);
            sayi2="";
            opt1="";
            textbox.setText(sayi1);
        }
    }
    public void Tiklandi(String deger,String btntext){
        /*try{*/
        if(deger.equals("")){
            if(btntext.equals("-")) {
                textbox.setText(btntext);
            }
        }else {
            if(opt2.equals("")){
                if (opt1.equals("")){
                    if(SonIndexKontrol(deger,btntext)){
                        SonindexGuncelle(deger,btntext);
                    }else{
                        sayi1=deger;
                        opt1=btntext;
                        textbox.setText(deger+btntext);
                    }
                }else{
                    if(SonIndexKontrol(deger,btntext)){
                        SonindexGuncelle(deger,btntext);
                    }else{
                        String[] sayilar = textbox.getText().split("[+*/-]");
                        sayi2=sayilar[1];
                        sayilar[1]="";
                        opt2=btntext;
                        textbox.setText(deger+btntext);
                    }
                }
            }else{
                if(SonIndexKontrol(deger,btntext)){
                    SonindexGuncelle(deger,btntext);
                }else{
                    String[] sayilar=textbox.getText().split("[+*/-]");
                    sayi3=sayilar[2];
                    sayilar[2]="";
                    OnceligeGoreIslemYap(sayi1,sayi2,sayi3,opt1,opt2);

                }
            }
        }
            /*
            if(deger.length()>0){
                    char sonindex=deger.charAt(deger.length()-1);
                    boolean islem_mi=false;
                    //++ olmasına engel olmak için
                    for (char i:dizi){
                        if(sonindex==i){
                            islem_mi=true;
                            break;
                        }
                    }
                    //if ++ ise son değer güncelle değilse sayi+||sayi-||sayi*||sayi/ olur
                    if(islem_mi){
                        //son deger nokta ise sadece '.' ekle değilse sayi1 e değer ata ve opt tut
                        if(btntext.charAt(0)!='.'){
                            StringBuffer sb=new StringBuffer(deger);
                            deger=sb.deleteCharAt(deger.length()-1).toString();
                            sayi1=textbox.getText();
                            textbox.setText(deger+btntext);
                            //son operatör güncelle
                            opt=btntext.charAt(0);
                        }else{
                            textbox.setText(deger+btntext);
                        }
                        //sayi1+ formatı ise ve opt yoksa atamaları yap
                        //opt varsa sayi1 ve sayi2 ile işlem yap
                    }else{
                        //if içinde sayi1 e atama işlemi
                        if(opt==' '){
                            textbox.setText(sayi1+btntext);
                            sayi1=textbox.getText();
                            opt=btntext.charAt(btntext.length()-1);
                        }
                        //atama yapıldıysa işlem yap
                       /* else{
                            String[] sayilar = textbox.getText().split("[+*-/]+");
                            Hesapla(Double.parseDouble(sayilar[0]),opt,Double.parseDouble(sayilar[1]));
                            textbox.setText(sonuc+btntext);
                            opt=btntext.charAt(btntext.length()-1);
                            sayi1=Double.toString(sonuc);
                        }
                    }
            }else{
            }
            */
        /*}catch(Exception e){System.out.println("Hata"+e);}*/
    }
    Arayuz() {
        add(panel);
        setSize(300,450);
        //formda carpıya basınca programı bitirir
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //sayı1+sayi2*sayi3
        buttonarti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deger=textbox.getText();
                String btntxt=buttonarti.getText();
                Tiklandi(deger,btntxt);
            }
        });
        buttoneksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deger=textbox.getText();
                String btntxt=buttoneksi.getText();
                Tiklandi(deger,btntxt);
            }
        });
        buttonbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deger=textbox.getText();
                String btntxt=buttonbol.getText();
                Tiklandi(deger,btntxt);
            }
        });
        buttoncarp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deger=textbox.getText();
                String btntxt=buttoncarp.getText();
                Tiklandi(deger,btntxt);
            }
        });
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a1Button.getText());
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a2Button.getText());
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a3Button.getText());
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a4Button.getText());
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a5Button.getText());
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a6Button.getText());
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a7Button.getText());
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a8Button.getText());
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a9Button.getText());
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText(textbox.getText()+a0Button.getText());
            }
        });
        //hesaplama işlemleri
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText("");
                sayi1="";
                sayi2="";
                sayi3="";
                opt1="";
                opt2="";
            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deger=textbox.getText();
                StringBuilder sb=new StringBuilder(deger);
                if(deger.length()>0 && !SonIndexKontrol(deger) ){
                    sb.deleteCharAt(deger.length()-1);
                    textbox.setText(sb.toString());
                }else if(!opt2.equals("") && opt2.charAt(0)==deger.charAt(-1) ){
                    opt2="";
                    sb.deleteCharAt(deger.length()-1);
                    textbox.setText(sb.toString());
                }else if(!opt1.equals("") && opt1.charAt(0)==deger.charAt(-1) ){
                    opt1="";
                    sb.deleteCharAt(deger.length()-1);
                    textbox.setText(sb.toString());
                }
            }
        });
        //eşittir buttonuna tıklanabilecek durumlar
        //2+
        //2+2(işlem yap) opt1 boş değil son index kontrol işlem değil ise split et sayi2 atama yap hesapla
        //2+2+(işlem yap) opt2 boş değil sonindexkontrol() işlem ise split et sayi2 atama yap hesapla() sayi1 ve sayı2
        //değilse split et sayı3 ata OnceliğeGöreİslemYap()
        //2+2+2
        buttonesit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deger=textbox.getText();
                if(!opt1.equals("")&&!SonIndexKontrol(deger)){
                    String[] sayilar = deger.split("[+*/-]");
                    sayi2=sayilar[1];
                    Hesapla(Double.parseDouble(sayi1),opt1,Double.parseDouble(sayi2));
                    sayi1=Double.toString(sonuc);
                    opt1="";
                    sayi2="";
                    textbox.setText(sayi1);
                } else if (!opt2.equals("")&&!SonIndexKontrol(deger)){
                    String[] sayilar = deger.split("[+*/-]");
                    sayi3=sayilar[2];
                    OnceligeGoreIslemYap(sayi1,sayi2,sayi3,opt1,opt2);
                }
            }
        });
    }
    JTextField textbox;
    private JButton a1Button,a2Button,a3Button,a4Button,a5Button,a6Button,a7Button,a8Button,a9Button,a0Button;
    private JButton cButton,delButton;
    private JButton buttoneksi,buttonarti,buttonbol,buttoncarp,buttonesit;
    private JPanel panel;
}
