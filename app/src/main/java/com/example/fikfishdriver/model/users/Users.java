package com.example.fikfishdriver.model.users;

public class Users {
    private String id_kurir;
    private String username;
    private String email;
    private String full_name;
    private String password;
    private String foto;
    private String phone;

    public Users(String id_kurir, String username, String email, String full_name, String password, String foto, String phone) {
        this.id_kurir = id_kurir;
        this.username = username;
        this.email = email;
        this.full_name = full_name;
        this.password = password;
        this.foto = foto;
        this.phone = phone;
    }

    public String getId_kurir() {
        return id_kurir;
    }

    public void setId_kurir(String id_kurir) {
        this.id_kurir = id_kurir;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
