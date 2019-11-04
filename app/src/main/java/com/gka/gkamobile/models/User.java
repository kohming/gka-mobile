package com.gka.gkamobile.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    private String fullName;

    @ColumnInfo(name = "user_name")
    @SerializedName("user_name")
    private String username;

    @ColumnInfo(name = "mobile_phone")
    @SerializedName("mobile_phone")
    private String mobilePhone;

    @ColumnInfo(name = "district_id")
    @SerializedName("district_id")
    private int districtId;

    @ColumnInfo(name = "sub_district_id")
    @SerializedName("sub_district_id")
    private int subDistrictId;

    @ColumnInfo(name = "city_id")
    @SerializedName("city_id")
    private int cityId;

    @ColumnInfo(name = "member_code")
    @SerializedName("member_code")
    private String memberCode;

    @ColumnInfo(name = "baptist_name")
    @SerializedName("baptist_name")
    private String baptistName;

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    private String gender;

    @ColumnInfo(name = "birthdate")
    @SerializedName("birthdate")
    private String birthdate;


    @ColumnInfo(name = "place_of_birth")
    @SerializedName("place_of_birth")
    private String place_of_birth;

    @ColumnInfo(name = "address")
    @SerializedName("address")
    private String address;

    @ColumnInfo(name = "marital_status")
    @SerializedName("marital_status")
    private String maritalStatus;

    @ColumnInfo(name = "member_status")
    @SerializedName("member_status")
    private String memberStatus;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getSubDistrictId() {
        return subDistrictId;
    }

    public void setSubDistrictId(int subDistrictId) {
        this.subDistrictId = subDistrictId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getBaptistName() {
        return baptistName;
    }

    public void setBaptistName(String baptistName) {
        this.baptistName = baptistName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", districtId=" + districtId +
                ", subDistrictId=" + subDistrictId +
                ", cityId=" + cityId +
                ", memberCode='" + memberCode + '\'' +
                ", baptistName='" + baptistName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", place_of_birth='" + place_of_birth + '\'' +
                ", address='" + address + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", memberStatus='" + memberStatus + '\'' +
                '}';
    }
}
