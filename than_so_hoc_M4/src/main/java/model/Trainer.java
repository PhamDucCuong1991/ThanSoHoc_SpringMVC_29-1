package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trainer {
    @Id
    private int id;
    private String name;
    private int age;
    private String img;
    private String phoneNumber;
    private String address;

    public Trainer() {
    }

    public Trainer(int id, String name, int age, String img, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.img = img;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
