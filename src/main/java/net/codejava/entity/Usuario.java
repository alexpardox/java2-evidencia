/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity class for Usuario (User)
 */
@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password; // Should be stored encrypted

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false)
    private Double height;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Usuario() {
        this.createdAt = LocalDate.now(); // Default current date
    }

    public Usuario(String fullName, String username, String password, Integer age, String gender, Double height) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.createdAt = LocalDate.now();
        
        // Validate constraints
        validateAge(age);
        validateGender(gender);
        validateHeight(height);
    }

    // Validation methods
    private void validateAge(Integer age) {
        if (age != null && (age < 15 || age > 110)) {
            throw new IllegalArgumentException("La edad debe estar entre 15 y 110 años");
        }
    }

    private void validateGender(String gender) {
        if (gender != null && !gender.equals("M") && !gender.equals("F") && !gender.equals("Otro")) {
            throw new IllegalArgumentException("El género debe ser 'M', 'F', o 'Otro'");
        }
    }

    private void validateHeight(Double height) {
        if (height != null && (height < 1.0 || height > 2.5)) {
            throw new IllegalArgumentException("La estatura debe estar entre 1.0m y 2.5m");
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        validateAge(age);
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        validateGender(gender);
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        validateHeight(height);
        this.height = height;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", createdAt=" + createdAt +
                '}';
    }
}
