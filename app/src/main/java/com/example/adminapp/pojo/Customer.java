package com.example.adminapp.pojo;

public class Customer {
    String name;

    public String getFcmid() {
        return fcmid;
    }

    public void setFcmid(String fcmid) {
        this.fcmid = fcmid;
    }

    String fcmid;

    public Customer(String name, String telephone, String nic, String gender, String email, String image_path,String fcmid) {
        this.name = name;
        this.telephone = telephone;
        Nic = nic;
        this.gender = gender;
        this.email = email;
        this.image_path = image_path;
        this.fcmid = fcmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Customer() {
    }

    String telephone;
    String Nic;
    String gender;
    String email;
    String image_path;



}
