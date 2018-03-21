package cjwl.cjb.org.entity;

/**
 * 人物信息
 * Created by jimbai on 2018/3/18.
 */

public class PersonInfo {

    private int gender;
    private String tel;
    private String address;
    private String name;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
