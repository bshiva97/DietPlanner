package com.packtpub.dietplannerfinal;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by balbina on 26.06.17.
 */
/*
@DatabaseTable(tableName = "ingredients")
public class Ingredient {
    @DatabaseField(id = true)
    private String name;

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}*/
public class Ingredient {
    private int _id;
    private String productname;
    private String password;

    public Ingredient(int id, String productname,String password) {
        this._id = id;
        this.productname = productname;
        this.password=password;
    }

    public int get_id() {
        return _id;
    }
    public String getproductname() {
        return productname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setproductname(String productname) {
        this.productname = productname;
    }
}