package com.example.fikfishdriver.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fikfishdriver.model.users.Users;
import com.example.fikfishdriver.model.users.UsersData;

public class SystemDataLocal {
    private SharedPreferences sharedPreferences;
    private Context context;
    private static final String KEY_USER = "user";
    private static final String KEY_ADDR = "address";
    private static final String KEY_ADDR_SAVE = "address_save";
    private UsersData usersData;

    public SystemDataLocal(Context context,UsersData usersData) {
        this.context = context;
        this.usersData = usersData;
    }

    public SystemDataLocal(Context context) {
        this.context = context;
    }

    public void setSessionLogin(){
        sharedPreferences  = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",usersData.getUsername());
        editor.putString("full_name",usersData.getFullName());
        editor.putString("password",usersData.getPassword());
        editor.putString("email",usersData.getEmail());
        editor.putString("photo",usersData.getFoto());
        editor.putString("id_kurir",usersData.getIdKurir());
        editor.putString("no_hp",usersData.getPhone());
        editor.putBoolean("login",true);
        editor.apply();
    }

    public void editAllSessionLogin(String id_kurir,String username,String full_name,String password,String email,String photo,String no_hp){
        sharedPreferences  = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id_kurir",id_kurir);
        editor.putString("username",username);
        editor.putString("full_name",full_name);
        editor.putString("password",password);
        editor.putString("email",email);
        editor.putString("photo",photo);
        editor.putString("no_hp",no_hp);
        editor.apply();
    }

    public Users getLoginData(){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        String id_kurir = sharedPreferences.getString("id_kurir","");
        String username = sharedPreferences.getString("username","");
        String full_name = sharedPreferences.getString("full_name","");
        String email = sharedPreferences.getString("email","");
        String password = sharedPreferences.getString("password","");
        String photo = sharedPreferences.getString("photo","");
        String no_hp = sharedPreferences.getString("no_hp","");
        return new Users(id_kurir,username,email,full_name,password,photo,no_hp);
    }

    public boolean getCheckLogin(){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login",false);
    }

    public void destroySession(){
        sharedPreferences = context.getSharedPreferences(KEY_USER,Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
}
