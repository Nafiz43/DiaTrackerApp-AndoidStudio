package com.example.idp;


public class history {
    String glu;
    String dat;
    String tim;
    String type;
    public history()
    {

    }

    public history(String glu, String dat, String tim, String type) {
        this.glu = glu;
        this.dat = dat;
        this.tim = tim;
        this.type = type;
    }

    public String getGlu() {
        return glu;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
