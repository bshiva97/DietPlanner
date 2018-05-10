package com.packtpub.dietplannerfinal;

/**
 * Created by archi on 22-09-2017.
 */


public class UserEntry  {
    String id;
    String userfirstname;
    String userlastname;
    String userpassword;


    public UserEntry(String id, String userfirstname, String userlastname, String userpassword) {
        this.id = id;
        this.userfirstname = userfirstname;
        this.userlastname = userlastname;
        this.userpassword = userpassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}


