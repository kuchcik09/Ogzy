package org.pojos;

public class PlanZajecDTO {

    private final Object czas;
    private Object pn;
    private Object wt;
    private Object sr;
    private Object czw;
    private Object pt;
    private Object sb;
    private Object nd;

    public PlanZajecDTO(Object czas) {
        this.czas = czas;
    }

    public Object getCzas() {
        return czas;
    }

    public Object getPn() {
        return pn;
    }

    public Object getWt() {
        return wt;
    }

    public Object getSr() {
        return sr;
    }

    public Object getCzw() {
        return czw;
    }

    public Object getPt() {
        return pt;
    }

    public Object getSb() {
        return sb;
    }

    public Object getNd() {
        return nd;
    }

    public void setPn(Object pn) {
        this.pn = pn;
    }

    public void setWt(Object wt) {
        this.wt = wt;
    }

    public void setSr(Object sr) {
        this.sr = sr;
    }

    public void setCzw(Object czw) {
        this.czw = czw;
    }

    public void setPt(Object pt) {
        this.pt = pt;
    }

    public void setSb(Object sb) {
        this.sb = sb;
    }

    public void setNd(Object nd) {
        this.nd = nd;
    }

}
