package movieBooking.start;

import movieBooking.start.movie.*;
import movieBooking.start.person.Address;
import movieBooking.start.person.Client;
import movieBooking.start.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movieBooking");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            /* 사전 데이터 */
            // 영화 당 감독 한 명 씩
            Director director1 = new Director("구파도", LocalDate.of(1978, 8, 25), "대만");
            Director director2 = new Director("츠키카와 쇼", LocalDate.of(1982, 8, 5), "일본");
            Director director3 = new Director("프랭키 첸", LocalDate.of(1974, 9, 21), "대만");
            em.persist(director1);
            em.persist(director2);
            em.persist(director3);

            // 주연 배우 각 영화 당 2명씩
            // [그 시절 우리가 좋아했던 소녀]
            Actor star1 = new Actor("커전둥", LocalDate.of(1991, 6, 18), 183.0, "소니 뮤직");
            Actor star2 = new Actor("천옌시", LocalDate.of(1983, 5, 31), 160.0, "데카 레코드");
            em.persist(star1);
            em.persist(star2);

            // [너의 췌장을 먹고 싶어]
            Actor star3 = new Actor("하마베 미나미", LocalDate.of(2000, 8, 29), 156.0, "도호 예능");
            Actor star4 = new Actor("키타무라 타쿠미", LocalDate.of(1997, 11, 3), 177.0, "스타더스트 프로모션");
            em.persist(star3);
            em.persist(star4);

            // [나의 소녀시대]
            Actor star5 = new Actor("왕다루", LocalDate.of(1991, 5, 29), 181.0, "없음");
            Actor star6 = new Actor("송운화", LocalDate.of(1992, 10, 21), 165.0, "자작나무");
            em.persist(star5);
            em.persist(star6);

            // 조연 배우 각 영화 당 2명씩
            // [그 시절 우리가 좋아했던 소녀]
            Actor supporting1 = new Actor("차이창셴", LocalDate.of(1988, 8, 2), 162.0, "이린");
            Actor supporting2 = new Actor("학소문", LocalDate.of(1990, 1, 4), 170.0, "에이스팩토리");
            em.persist(supporting1);
            em.persist(supporting2);

            // [너의 췌장을 먹고 싶어]
            Actor supporting3 = new Actor("오구리 슌", LocalDate.of(1982, 12, 26), 184.0, "트라이스톤");
            Actor supporting4 = new Actor("오오토모 카렌", LocalDate.of(1999, 10, 9), 162.0, "켄온");
            em.persist(supporting3);
            em.persist(supporting4);

            // [나의 소녀시대]
            Actor supporting5 = new Actor("리위시", LocalDate.of(1993, 3, 31), 182.0, "오드");
            Actor supporting6 = new Actor("천차오언", LocalDate.of(1979, 4, 4), 164.0, "없음");
            em.persist(supporting5);
            em.persist(supporting6);

            // 영화 3건 (그 시절 우리가 좋아했던 소녀, 너의 췌장을 먹고 싶어, 나의 소녀시대)
            Movie movie1 = new Movie("그 시절 우리가 좋아했떤 소녀", LocalDate.of(2012, 8, 22), director1, MovieGenre.ROMANCE, 107);
            Movie movie2 = new Movie("너의 췌장을 먹고 싶어", LocalDate.of(2017, 10, 25), director2, MovieGenre.ROMANCE, 115);
            Movie movie3 = new Movie("나의 소녀시대", LocalDate.of(2015, 8, 13), director3, MovieGenre.ROMANCE, 134);
            em.persist(movie1);
            em.persist(movie2);
            em.persist(movie3);

            MovieActor movieActor1 = new MovieActor(movie1, star1, MovieActorState.LEAD);
            MovieActor movieActor2 = new MovieActor(movie1, star2, MovieActorState.LEAD);
            MovieActor movieActor3 = new MovieActor(movie1, supporting1, MovieActorState.SUPPORTING);
            MovieActor movieActor4 = new MovieActor(movie1, supporting2, MovieActorState.SUPPORTING);
            em.persist(movieActor1);
            em.persist(movieActor2);
            em.persist(movieActor3);
            em.persist(movieActor4);

            MovieActor movieActor5 = new MovieActor(movie2, star3, MovieActorState.LEAD);
            MovieActor movieActor6 = new MovieActor(movie2, star4, MovieActorState.LEAD);
            MovieActor movieActor7 = new MovieActor(movie2, supporting3, MovieActorState.SUPPORTING);
            MovieActor movieActor8 = new MovieActor(movie2, supporting4, MovieActorState.SUPPORTING);
            em.persist(movieActor5);
            em.persist(movieActor6);
            em.persist(movieActor7);
            em.persist(movieActor8);

            MovieActor movieActor9 = new MovieActor(movie3, star5, MovieActorState.LEAD);
            MovieActor movieActor10 = new MovieActor(movie3, star6, MovieActorState.LEAD);
            MovieActor movieActor11 = new MovieActor(movie3, supporting5, MovieActorState.SUPPORTING);
            MovieActor movieActor12 = new MovieActor(movie3, supporting6, MovieActorState.SUPPORTING);
            em.persist(movieActor9);
            em.persist(movieActor10);
            em.persist(movieActor11);
            em.persist(movieActor12);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
