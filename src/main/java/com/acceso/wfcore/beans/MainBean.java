package com.acceso.wfcore.beans;

public abstract class MainBean {

    protected String beanName = "MainBean";
    protected String titleName = "Bean";
    protected boolean open = false;

    /**
     * Carga inicial...
     *
     * @return
     */
    public abstract String load();

    public String getBeanName() {
        return beanName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public abstract String defaultAction();

}
