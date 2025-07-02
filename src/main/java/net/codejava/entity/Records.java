package net.codejava.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "records")
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // Optionally, you can add a proper foreign key relationship
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id", insertable = false, updatable = false)
    // private Usuario usuario;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double imc;

    @Column(name = "measured_at")
    private LocalDate measuredAt;

    public Records() {
        this.measuredAt = LocalDate.now(); // Default current date
    }

    public Records(Integer userId, Double weight, Double height) {
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.imc = calculateIMC(weight, height);
        this.measuredAt = LocalDate.now();
    }

    // Method to calculate IMC (BMI)
    private Double calculateIMC(Double weight, Double height) {
        if (weight != null && height != null && weight > 0 && height > 0) {
            return weight / (height * height);
        }
        return null;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        if (weight != null && weight <= 0) {
            throw new IllegalArgumentException("La masa corporal debe ser mayor a 0");
        }
        this.weight = weight;
        // Recalculate IMC when weight changes
        if (this.height != null) {
            this.imc = calculateIMC(weight, this.height);
        }
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        if (height != null && (height < 1.0 || height > 2.5)) {
            throw new IllegalArgumentException("La estatura debe estar entre 1.0m y 2.5m");
        }
        this.height = height;
        // Recalculate IMC when height changes
        if (this.weight != null) {
            this.imc = calculateIMC(this.weight, height);
        }
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public LocalDate getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(LocalDate measuredAt) {
        this.measuredAt = measuredAt;
    }

    // Method to manually recalculate IMC
    public void recalculateIMC() {
        this.imc = calculateIMC(this.weight, this.height);
    }

    @Override
    public String toString() {
        return "Records{" +
                "id=" + id +
                ", userId=" + userId +
                ", weight=" + weight +
                ", height=" + height +
                ", imc=" + imc +
                ", measuredAt=" + measuredAt +
                '}';
    }
}
