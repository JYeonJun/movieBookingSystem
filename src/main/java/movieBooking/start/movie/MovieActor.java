package movieBooking.start.movie;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MovieActor {

    @Id
    @GeneratedValue
    @Column(name = "MOVIE_ACTOR_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTOR_ID")
    private Actor actor;

    @Enumerated(EnumType.STRING)
    private MovieActorState movieActorState;

    /* 연관관계 편의 메서드 */
    public void setMovie(Movie movie) {
        if (this.movie != null) {
            this.movie.getActors().remove(this);
        }
        this.movie = movie;
        movie.getActors().add(this);
    }

    public void setActor(Actor actor) {
        if (this.actor != null) {
            this.actor.getProductionCasts().remove(this);
        }
        this.actor = actor;
        actor.getProductionCasts().remove(this);
    }
}
