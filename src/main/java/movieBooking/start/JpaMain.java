package movieBooking.start;

import movieBooking.start.movie.*;
import movieBooking.start.person.Address;
import movieBooking.start.person.Client;
import movieBooking.start.person.Person;
import movieBooking.start.secreening.Screening;
import movieBooking.start.theater.Seat;
import movieBooking.start.theater.SeatState;
import movieBooking.start.theater.Theater;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class JpaMain {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("movieBooking");

    public static void main(String[] args) {

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
            LocalDateTime currentTime = LocalDateTime.now();
            Movie movie1 = new Movie(currentTime, currentTime,"그 시절 우리가 좋아했던 소녀", LocalDate.of(2012, 8, 22), director1, MovieGenre.ROMANCE, 107);
            Movie movie2 = new Movie(currentTime, currentTime, "너의 췌장을 먹고 싶어", LocalDate.of(2017, 10, 25), director2, MovieGenre.ROMANCE, 115);
            Movie movie3 = new Movie(currentTime, currentTime, "나의 소녀시대", LocalDate.of(2015, 8, 13), director3, MovieGenre.ROMANCE, 134);
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

            // 상영관 2개
            Theater theater1 = new Theater("1상영관", 1);
            Theater theater2 = new Theater("2상영관", 2);
            em.persist(theater1);
            em.persist(theater2);

            // 좌석 - 각 상영관 마다 2행 5열 10개씩
            for (int i = 1; i < 3; i++) {
                for (int j = 1; j < 6; j++) {
                    Seat seat = new Seat(i, j, SeatState.AVAILABLE, theater1);
                    em.persist(seat);
                }
            }

            for (int i = 1; i < 3; i++) {
                for (int j = 1; j < 6; j++) {
                    Seat seat = new Seat(i, j, SeatState.AVAILABLE, theater2);
                    em.persist(seat);
                }
            }

            // 상영
            // 각 상영관은 하나의 영화만 상영한다.
            // 각 상영관은 하루에 최소 2개 이상의 상영 스케줄을 가진다.
            Screening screening1 = new Screening(movie1, LocalTime.of(12, 0), theater1);
            Screening screening2 = new Screening(movie1, LocalTime.of(18, 0), theater1);
            em.persist(screening1);
            em.persist(screening2);

            Screening screening3 = new Screening(movie2, LocalTime.of(8, 0), theater2);
            Screening screening4 = new Screening(movie2, LocalTime.of(20, 0), theater2);
            em.persist(screening3);
            em.persist(screening4);

            // 고객 2명
            LocalDateTime createdDate = LocalDateTime.now();
            LocalDate birthDate = LocalDate.of(1999, 1, 1);
            Address address1 = new Address("city", "street", "zipcode");
            Client client1 = new Client(createdDate, createdDate, "홍길동", birthDate, address1, 0);
            em.persist(client1);

            LocalDateTime createdDate1 = LocalDateTime.now();
            LocalDate birthDate1 = LocalDate.of(1999, 1, 2);
            Address address2 = new Address("city1", "street1", "zipcode1");
            Client client2 = new Client(createdDate1, createdDate1, "배트맨", birthDate1, address2, 0);
            em.persist(client2);

            // 예매
            // 최초 사용자 2명은 1상영관의 첫 번째 상영을 각각 예매하며 각각 2자리를 지정했다.
            // 1상영관의 첫 번째 상영의 남은 자리는 6개이며 새롭게 추가한 사용자가 1상영관의 첫 번째 상영을 예매하는 시나리오를 가정
//            makeBooking(em, client1, theater1);



            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    static void movieReservation(Long userPK, long ScreenPK, List<String> seats) {

    }

    // 고객, 상영관 선택
    /*static void makeBooking(EntityManager em, Client client, Theater theater){

        Scanner scanner = new Scanner(System.in);

        // 상영관으로 상영 보여주기
        showScreening(em, theater);
        System.out.print("\n원하는 상영의 id를 입력하시오: ");
        int screeningSelection = scanner.nextInt();

        Screening findScreening = em.find(Screening.class, (long) screeningSelection);
        Long theaterId = findScreening.getTheater().getId();

        // 현재 남은 좌석 보여주기
        showSeats(em, theaterId, findScreening.getTheater().getName());
        System.out.println("원하는 좌석을 입력하세요. (입력 종료: 0, 0)");
        while (true) {
            System.out.print("행: ");
            int lineNum = scanner.nextInt();

            System.out.print("열: ");
            int columnNum = scanner.nextInt();

            if(lineNum == 0 || columnNum == 0)
                break;

            // 좌석 상태 변경
            Seat findSeat = em.createQuery("select s from Seat s join s.theater t on t.id = :tid where s.lineNumber = :lineNum and s.columnNumber = :columnNum", Seat.class)
                    .setParameter("tid", theaterId)
                    .setParameter("lineNum", lineNum)
                    .setParameter("columnNum", columnNum)
                    .getSingleResult();

            findSeat.changeSeatState(SeatState.UNAVAILABLE);

            // 예약 생성
        }
    }*/

    static void showScreening(EntityManager em, Theater theater) {
        List<Screening> screeningList = em.createQuery("select s from Screening s join s.theater t on t.id = :tid order by s.startTime", Screening.class)
                .setParameter("tid", theater.getId()).getResultList();

        System.out.println("========== " + theater.getName() + " 상영 목록 ==========");
        for (Screening screening : screeningList) {
            System.out.println(screening);
        }
    }

    static void showSeats(EntityManager em, Long theaterId, String theaterName) {
        Theater findTheater = em.createQuery("select t from Theater t join fetch t.seats where t.id = :tid", Theater.class)
                .setParameter("tid", theaterId).getSingleResult();

        List<Seat> seats = findTheater.getSeats();

        System.out.println("\n========== " + theaterName + " 좌석 목록 ==========");
        for (Seat seat : seats) {
            if(seat.getLineNumber() == 1 && seat.getColumnNumber() == 1){
                System.out.print("1행: ");
            }

            if(seat.getLineNumber() == 2 && seat.getColumnNumber() == 1){
                System.out.println();
                System.out.print("2행: ");
            }

            if(seat.getSeatState() == SeatState.AVAILABLE)
                System.out.print("O");
            else System.out.print("X");
        }
        System.out.println();
    }
}
