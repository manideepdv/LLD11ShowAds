package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "users")
public class User extends BaseModel{
    private String name;
    private String email;
    @OneToMany(fetch = FetchType.EAGER) // FetchType is important in this case study
    private List<Preference> preferences;
}
