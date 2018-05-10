package com.packtpub.dietplannerfinal;

/**
 * Created by archi on 10-10-2017.
 */

public class EatEntry {
    String id;
    String food_item;
    String calo_Intake;

    public EatEntry(String calo_Intake, String food_item, String id) {
        this.calo_Intake = calo_Intake;
        this.food_item = food_item;
        this.id = id;
    }

    public String getCalo_Intake() {
        return calo_Intake;
    }

    public void setCalo_Intake(String calo_Intake) {
        this.calo_Intake = calo_Intake;
    }

    public String getFood_item() {
        return food_item;
    }

    public void setFood_item(String food_item) {
        this.food_item = food_item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
