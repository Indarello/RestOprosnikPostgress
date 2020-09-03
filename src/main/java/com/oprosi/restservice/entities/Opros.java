package com.oprosi.restservice.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Opros
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime createdat;

    @NotNull
    private LocalDateTime endat;

    @NotNull
    private boolean active;

    public Opros()
    {
    }

    public Opros(long id)
    {
        this.id = id;
    }

    public Opros(String title, LocalDateTime createdat, LocalDateTime endat, boolean active)
    {
        this.title = title;
        this.createdat = createdat;
        this.endat = endat;
        this.active = active;
    }
}
