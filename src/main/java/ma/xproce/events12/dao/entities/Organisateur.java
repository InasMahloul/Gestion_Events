package ma.xproce.events12.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.xproce.events12.dao.entities.Event;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Organisateur {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String telephone;

    @ManyToMany(mappedBy = "organisateurs")
    private List<Event> events = new ArrayList<>();
}
