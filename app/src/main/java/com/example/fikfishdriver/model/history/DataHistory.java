package com.example.fikfishdriver.model.history;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHistory implements Parcelable {
    @SerializedName("id_shipping")
    @Expose
    private String idShipping;
    @SerializedName("id_order")
    @Expose
    private String idOrder;
    @SerializedName("id_courier")
    @Expose
    private String idCourier;
    @SerializedName("is_delayed")
    @Expose
    private String isDelayed;
    @SerializedName("receiver")
    @Expose
    private String receiver;
    @SerializedName("date_shipping")
    @Expose
    private String dateShipping;
    @SerializedName("date_received")
    @Expose
    private String dateReceived;
    @SerializedName("status_shipping")
    @Expose
    private String statusShipping;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("id_cart")
    @Expose
    private String idCart;
    @SerializedName("date_order")
    @Expose
    private String dateOrder;
    @SerializedName("image_payment")
    @Expose
    private String imagePayment;
    @SerializedName("shipping_costs")
    @Expose
    private String shippingCosts;
    @SerializedName("additional_costs")
    @Expose
    private String additionalCosts;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("id_users")
    @Expose
    private String idUsers;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("is_order")
    @Expose
    private String isOrder;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("id_ikan")
    @Expose
    private String idIkan;
    @SerializedName("nama_ikan")
    @Expose
    private String namaIkan;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("id_jenis")
    @Expose
    private String idJenis;
    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longtitude")
    @Expose
    private String longtitude;
    @SerializedName("id_kurir")
    @Expose
    private String idKurir;
    @SerializedName("courier_name")
    @Expose
    private String courierName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("id_wilayah")
    @Expose
    private String idWilayah;

    public String getIdShipping() {
        return idShipping;
    }

    public void setIdShipping(String idShipping) {
        this.idShipping = idShipping;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdCourier() {
        return idCourier;
    }

    public void setIdCourier(String idCourier) {
        this.idCourier = idCourier;
    }

    public String getIsDelayed() {
        return isDelayed;
    }

    public void setIsDelayed(String isDelayed) {
        this.isDelayed = isDelayed;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDateShipping() {
        return dateShipping;
    }

    public void setDateShipping(String dateShipping) {
        this.dateShipping = dateShipping;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getStatusShipping() {
        return statusShipping;
    }

    public void setStatusShipping(String statusShipping) {
        this.statusShipping = statusShipping;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getImagePayment() {
        return imagePayment;
    }

    public void setImagePayment(String imagePayment) {
        this.imagePayment = imagePayment;
    }

    public String getShippingCosts() {
        return shippingCosts;
    }

    public void setShippingCosts(String shippingCosts) {
        this.shippingCosts = shippingCosts;
    }

    public String getAdditionalCosts() {
        return additionalCosts;
    }

    public void setAdditionalCosts(String additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdIkan() {
        return idIkan;
    }

    public void setIdIkan(String idIkan) {
        this.idIkan = idIkan;
    }

    public String getNamaIkan() {
        return namaIkan;
    }

    public void setNamaIkan(String namaIkan) {
        this.namaIkan = namaIkan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(String idJenis) {
        this.idJenis = idJenis;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdWilayah() {
        return idWilayah;
    }

    public void setIdWilayah(String idWilayah) {
        this.idWilayah = idWilayah;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idShipping);
        dest.writeString(this.idOrder);
        dest.writeString(this.idCourier);
        dest.writeString(this.isDelayed);
        dest.writeString(this.receiver);
        dest.writeString(this.dateShipping);
        dest.writeString(this.dateReceived);
        dest.writeString(this.statusShipping);
        dest.writeString(this.number);
        dest.writeString(this.orderId);
        dest.writeString(this.idCart);
        dest.writeString(this.dateOrder);
        dest.writeString(this.imagePayment);
        dest.writeString(this.shippingCosts);
        dest.writeString(this.additionalCosts);
        dest.writeString(this.status);
        dest.writeString(this.idProduct);
        dest.writeString(this.idUsers);
        dest.writeString(this.quantity);
        dest.writeString(this.date);
        dest.writeString(this.isOrder);
        dest.writeString(this.username);
        dest.writeString(this.fullName);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.noHp);
        dest.writeString(this.role);
        dest.writeString(this.idIkan);
        dest.writeString(this.namaIkan);
        dest.writeString(this.harga);
        dest.writeString(this.foto);
        dest.writeString(this.deskripsi);
        dest.writeString(this.idJenis);
        dest.writeString(this.usersId);
        dest.writeString(this.tglLahir);
        dest.writeString(this.photo);
        dest.writeString(this.jenisKelamin);
        dest.writeString(this.alamat);
        dest.writeString(this.latitude);
        dest.writeString(this.longtitude);
        dest.writeString(this.idKurir);
        dest.writeString(this.courierName);
        dest.writeString(this.phone);
        dest.writeString(this.idWilayah);
    }

    public DataHistory() {
    }

    protected DataHistory(Parcel in) {
        this.idShipping = in.readString();
        this.idOrder = in.readString();
        this.idCourier = in.readString();
        this.isDelayed = in.readString();
        this.receiver = in.readString();
        this.dateShipping = in.readString();
        this.dateReceived = in.readString();
        this.statusShipping = in.readString();
        this.number = in.readString();
        this.orderId = in.readString();
        this.idCart = in.readString();
        this.dateOrder = in.readString();
        this.imagePayment = in.readString();
        this.shippingCosts = in.readString();
        this.additionalCosts = in.readString();
        this.status = in.readString();
        this.idProduct = in.readString();
        this.idUsers = in.readString();
        this.quantity = in.readString();
        this.date = in.readString();
        this.isOrder = in.readString();
        this.username = in.readString();
        this.fullName = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.noHp = in.readString();
        this.role = in.readString();
        this.idIkan = in.readString();
        this.namaIkan = in.readString();
        this.harga = in.readString();
        this.foto = in.readString();
        this.deskripsi = in.readString();
        this.idJenis = in.readString();
        this.usersId = in.readString();
        this.tglLahir = in.readString();
        this.photo = in.readString();
        this.jenisKelamin = in.readString();
        this.alamat = in.readString();
        this.latitude = in.readString();
        this.longtitude = in.readString();
        this.idKurir = in.readString();
        this.courierName = in.readString();
        this.phone = in.readString();
        this.idWilayah = in.readString();
    }

    public static final Parcelable.Creator<DataHistory> CREATOR = new Parcelable.Creator<DataHistory>() {
        @Override
        public DataHistory createFromParcel(Parcel source) {
            return new DataHistory(source);
        }

        @Override
        public DataHistory[] newArray(int size) {
            return new DataHistory[size];
        }
    };
}
