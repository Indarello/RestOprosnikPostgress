package com.oprosi.restservice.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Vopros
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "opros_id")
    private Opros opros;

    private long poradok;

    @NotNull
    private String description;

    public Vopros()
    {
    }

    public Vopros(Opros opros, long poradok, String description)
    {
        this.opros = opros;
        this.poradok = poradok;
        this.description = description;
    }
}
