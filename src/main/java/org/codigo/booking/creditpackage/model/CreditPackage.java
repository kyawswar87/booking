package org.codigo.booking.creditpackage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int creditPoint;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private Country country;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
