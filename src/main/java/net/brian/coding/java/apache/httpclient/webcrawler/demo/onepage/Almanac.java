package net.brian.coding.java.apache.httpclient.webcrawler.demo.onepage;

/**
 * ����������ʵ����
 * 
 * @author ��Դblog
 * 2016��4��11��
 */
public class Almanac {
    private String solar;        /* ���� e.g.2016�� 4��11�� ����һ */
    private String lunar;        /* ���� e.g. ���� ���³���*/
    private String chineseAra;    /* ��ɵ�֧���귨 e.g.������ �ɳ��� �ﺥ��*/
    private String should;        /* ��e.g. ���� �� ���� ���� ����*/
    private String avoid;        /* �� e.g. ���ã��Ƶ���Σ�գ��ɳ���*/

    public String getSolar() {
        return solar;
    }

    public void setSolar(String date) {
        this.solar = date;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getChineseAra() {
        return chineseAra;
    }

    public void setChineseAra(String chineseAra) {
        this.chineseAra = chineseAra;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getShould() {
        return should;
    }

    public void setShould(String should) {
        this.should = should;
    }

    public Almanac(String solar, String lunar, String chineseAra, String should,
            String avoid) {
        this.solar = solar;
        this.lunar = lunar;
        this.chineseAra = chineseAra;
        this.should = should;
        this.avoid = avoid;
    }
}