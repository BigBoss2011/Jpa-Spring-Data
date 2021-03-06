package com.jee.tp.serveruser.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor

@Table(name = "toy")
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Toy {
    @Id @GeneratedValue
    private Long code;
    @Column(name = "Name")
    private @NonNull String name;
    private @NonNull String type;
    private Integer minAge;
    private Integer maxAge;
    private Double price;
    @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private Set<customerLikes> ToysLikedbyCust=new HashSet<>();



    public Toy(@NonNull String name, @NonNull String type, Integer minAge, Integer maxAge, Double price) {
        this.name = name;
        this.type = type;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.price = price;
    }

    public Toy(@NonNull String name, @NonNull String type, Integer minAge, Integer maxAge, Double price, customerLikes... toysLiked) {
        this.name = name;
        this.type = type;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.price = price;
        for(customerLikes cl : toysLiked) cl.setToy(this);
        this.ToysLikedbyCust = Stream.of(toysLiked).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return code.equals(toy.code) &&
                name.equals(toy.name) &&
                type.equals(toy.type) &&
                Objects.equals(minAge, toy.minAge) &&
                Objects.equals(maxAge, toy.maxAge) &&
                Objects.equals(price, toy.price) &&
                Objects.equals(ToysLikedbyCust, toy.ToysLikedbyCust);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Toy{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", price=" + price +
                '}';
    }

    public String getType() {
        return this.type;
    }
}