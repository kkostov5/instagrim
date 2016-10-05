/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

public class Profile {

    String Firstname = null;
    String Lastname = null;
    String email = null;
    Pic image = new Pic();
    public void profile() {

    }

    public void setFirstname(String name) {
        this.Firstname = name;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setLastname(String name) {
        this.Lastname = name;
    }

    public String getLastname() {
        return Lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setPic(Pic user) {
        this.image = user;
    }
    public Pic getPic() {
        return image;
    }
}
