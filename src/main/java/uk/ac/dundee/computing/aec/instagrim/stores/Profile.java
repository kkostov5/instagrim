/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.Set;

public class Profile {

    private String Firstname = null;
    private String Lastname = null;
    private String email = null;
    private java.util.UUID image = null;
    private String username = null;
    private Set<String> following = null;
    
    public void profile() {

    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
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
    public void setPic(java.util.UUID image) {
        this.image = image;
    }
    public String getPic() {
        if(image == null)return null;
        else return image.toString();
    }
    public void setFollowing(Set<String> following) {
        this.following = following;
    }
    public Set<String> getFollowing() {
        return following;
    }
}
