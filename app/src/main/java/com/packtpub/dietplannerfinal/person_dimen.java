package com.packtpub.dietplannerfinal;


/**
 * Created by archi on 26-11-2017.
 */

public class person_dimen {
    private String user_id;
    private String weight;
    private String height;
    private  String sex;
    private String age;
    private String life_sty;
    private String _aim;
    private String p;

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public person_dimen(String user_id, String weight, String height, String sex, String age, String life_sty, String _aim, String p) {
        this._aim = _aim;
        this.height = height;
        this.life_sty = life_sty;
        this.sex = sex;
        this.age=age;
        this.user_id = user_id;
        this.weight = weight;
        this.p=p;
    }

    public String get_aim() {
        return _aim;
    }

    public void set_aim(String _aim) {
        this._aim = _aim;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLife_sty() {
        return life_sty;
    }

    public void setLife_sty(String life_sty) {
        this.life_sty = life_sty;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
