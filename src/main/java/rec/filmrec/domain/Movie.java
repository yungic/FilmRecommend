package rec.filmrec.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movie")

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "movie")
    private Long id;

    @Column(name="movie_name")
    private String movieName;

    @Column(name="movie_information")
    private String movieInformation;

}
