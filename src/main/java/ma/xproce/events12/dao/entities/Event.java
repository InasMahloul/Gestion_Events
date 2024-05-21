package ma.xproce.events12.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Event{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String startDateTime;
    private String endDateTime;
    private String location;


    @OneToMany(mappedBy = "event")
    private List<Participant> participants = new ArrayList<>();

    @ManyToMany
    private List<Organisateur> organisateurs = new ArrayList<>();


}



