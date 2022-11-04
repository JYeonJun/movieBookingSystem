package movieBooking.start;

import movieBooking.start.movie.*;
import movieBooking.start.person.Address;
import movieBooking.start.person.Client;
import movieBooking.start.person.Person;
import movieBooking.start.secreening.Screening;
import movieBooking.start.theater.Seat;
import movieBooking.start.theater.SeatState;
import movieBooking.start.theater.Theater;
import movieBooking.start.ticketing.ReservationSeat;
import movieBooking.start.ticketing.Ticketing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
            movieReservation(em, client1.getId(), screening1.getId());
            movieReservation(em, client2.getId(), screening1.getId());

            // 1) 고객과 직원 생성
            // 실행해 데이터베이스에 레코드 등록 확인
//            createUser();

            // 2) 영화정보 조회1: 영화 레코드의 PK를 이용하여 한 영화에 대한 상세 정보 조회
            // 영화 이름, 개봉일, 감독, 배우, 장르, 러닝타임 (분)
//            showMovieInfo(em, 16L);

            // 3) 영화정보 조회2: 영화와 출연배우를 조회하되 영화에 대한 페이징 (일대다 N + 1과 페이징 동시 해결)
            // 영화는 2건 단위로 페이징
//            showMovieWithActors(em, 1);
//            showMovieWithActors(em, 2);

            // 4) 특정 상영정보 조회
            // 영화 이름, 시작시간, 종료시간, 총 좌석, 가능 좌석
//            showScreenInfo(em, 53L);

            // 5) 예매
            // 새롭게 추가한 회원이 특정 상영 예매
            // 예매 수행 후, 예약 가능 좌석 변경 보이기 (예매 후, showScreenInfo 다시 실행)
//            movieReservation(em, 61L, 53L);

            // 6) 예매정보 (영화 티켓)
            // 영화 이름, 관이름, 좌석, 시작시간
//            showReservationInfo(em, 59L);

            // 7) 예매 취소
            // 예매 취소 시, 예매 내역 상태 변경과 가능 좌석 변경 보이기
//            cancelTicketing(em, 60L);

            // 8) 영속성 전이를 이용한 삭제 (Cascade)
            // 고객을 삭제할 경우, 해당 사용자와 연관된 예매내역 자동 삭제 (DB에서 확인)
//            removeClient(em, 57L);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    // 1) 고객과 직원 생성
    static Long createUser() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        Client client = new Client();

        try {
            tx.begin();

            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDate birthDate = LocalDate.of(2000, 2, 17);
            Address address = new Address("city", "street", "zipcode");

            client.setCreatedDate(currentDateTime);
            client.setLastModifiedDate(currentDateTime);
            client.setMileage(0);
            client.setName("정연준");
            client.setAddress(address);
            client.setBirthDate(birthDate);

            em.persist(client);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        return client.getId();
    }

    // 2) 영화정보 조회1: 영화 레코드의 PK를 이용하여 한 영화에 대한 상세 정보 조회
    // 영화 이름, 개봉일, 감독, 배우, 장르, 러닝타임 (분)
    static void showMovieInfo(EntityManager em, Long movieId) {

        Movie findMovie = em.createQuery("select m from Movie m join fetch m.actors where m.id = :mid", Movie.class)
                .setParameter("mid", movieId).getSingleResult();
        System.out.println("\n========== 영화정보 조회1 ==========");
        System.out.println("영화 제목 = " + findMovie.getTitle());
        System.out.println("개봉일 = " + findMovie.getReleaseDate());
        System.out.println("감독 = " + findMovie.getDirector().getName());
        System.out.println("배우");
        List<MovieActor> actors = findMovie.getActors();
        for (MovieActor actor : actors) {
            if(actor.getMovieActorState() == MovieActorState.LEAD)
                System.out.println("-" + actor.getActor().getName() + " (주연)");
            else System.out.println("-" + actor.getActor().getName() + " (조연)");
        }
        System.out.println("장르 = " + findMovie.getMovieGenre());
        System.out.println("러닝타임 (분) = " + findMovie.getRunningTime());
        System.out.println("=================================\n");
    }

    // 3) 영화정보 조회2: 영화와 출연배우를 조회하되 영화에 대한 페이징 (일대다 N + 1과 페이징 동시 해결)
    // 영화는 2건 단위로 페이징
    static void showMovieWithActors(EntityManager em, int pageNum){

        List<Movie> findMovies = em.createQuery("select m from Movie m", Movie.class)
                .setFirstResult(2 * (pageNum - 1))
                .setMaxResults(2)
                .getResultList();

        System.out.println("\n========== 영화정보 조회2 페이지("+ pageNum +") ==========");
        for (Movie findMovie : findMovies) {
            System.out.println("\n영화 제목 = " + findMovie.getTitle());
            System.out.println("배우");
            List<MovieActor> actors = findMovie.getActors();
            for (MovieActor actor : actors) {
                if(actor.getMovieActorState() == MovieActorState.LEAD)
                    System.out.println("-" + actor.getActor().getName() + " (주연)");
                else System.out.println("-" + actor.getActor().getName() + " (조연)");
            }
        }
        System.out.println("=================================\n");
    }

    // 4) 특정 상영정보 조회
    // 영화 이름, 시작시간, 종료시간, 총 좌석, 가능 좌석
    static void showScreenInfo(EntityManager em, Long screenPK) {

        Screening findScreen = em.createQuery("select s from Screening s join fetch s.movie join fetch s.theater where s.id = :sid", Screening.class)
                .setParameter("sid", screenPK)
                .getSingleResult();

        System.out.println("영화 제목 = " + findScreen.getMovie().getTitle());
        System.out.println("시작시간 = " + findScreen.getStartTime());
        System.out.println("종료시간 = " + findScreen.getEndTime());
        System.out.println("총 좌석");
        showSeats(em, screenPK);
    }

    static void showSeats(EntityManager em, Long screenPK) {
        Screening findScreen = em.find(Screening.class, screenPK);

        Theater findTheater = em.createQuery("select t from Theater t join fetch t.seats where t.id = :tid", Theater.class)
                .setParameter("tid", findScreen.getTheater().getId())
                .getSingleResult();

        List<Seat> seats = findTheater.getSeats();

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

        System.out.println("가능 좌석");
        for (Seat seat : seats) {
            if(seat.getSeatState() == SeatState.AVAILABLE)
                System.out.println("(" + seat.getLineNumber() + ", " + seat.getColumnNumber() + ")");
        }
    }

    // 5) 예매
    // 새롭게 추가한 회원이 특정 상영 예매
    // 예매 수행 후, 예약 가능 좌석 변경 보이기 (예매 후, showScreenInfo 다시 실행)
    static Long movieReservation(EntityManager em, Long userPK, Long screenPK) {

        Scanner scanner = new Scanner(System.in);
        Screening findScreen = em.find(Screening.class, screenPK);
        Theater findTheater = findScreen.getTheater();

        Client findClient = em.find(Client.class, userPK);

        showScreenInfo(em, screenPK);

        // 예약 생성
        Ticketing ticketing = new Ticketing(findScreen, findClient);
        em.persist(ticketing);

        System.out.println("원하는 좌석을 입력하세요. (종료: 0 입력)");
        while (true) {
            System.out.print("행: ");
            int lineNum = scanner.nextInt();

            if(lineNum == 0)
                break;

            System.out.print("열: ");
            int columnNum = scanner.nextInt();

            if (columnNum == 0)
                break;

            // 좌석 상태 변경
            Seat findSeat = em.createQuery("select s from Seat s join s.theater t on t.id = :tid where s.lineNumber = :lineNum and s.columnNumber = :columnNum", Seat.class)
                    .setParameter("tid", findTheater.getId())
                    .setParameter("lineNum", lineNum)
                    .setParameter("columnNum", columnNum)
                    .getSingleResult();

            if(findSeat.getSeatState() == SeatState.UNAVAILABLE){
                System.out.println("예매가 불가능한 좌석입니다.");
                continue;
            }

            findSeat.changeSeatState(SeatState.UNAVAILABLE);
            findSeat.setTicketing(ticketing);
        }

        showSeats(em, screenPK);

        return ticketing.getId();
    }

    // 6) 예매정보 (영화 티켓)
    // 영화 이름, 관이름, 좌석, 시작시간
    static void showReservationInfo(EntityManager em, Long ticketingId) {
        Ticketing findTicketing = em.createQuery("select t from Ticketing t join fetch t.reservationSeats where t.id = :tid", Ticketing.class)
                .setParameter("tid", ticketingId)
                .getSingleResult();

        Long screeningId = findTicketing.getScreening().getId();

        Screening findScreening = em.createQuery("select s from Screening s join fetch s.movie join fetch s.theater where s.id = :sid", Screening.class)
                .setParameter("sid", screeningId)
                .getSingleResult();

        System.out.println("영화 이름 = " + findScreening.getMovie().getTitle());
        System.out.println("관이름 = " + findScreening.getTheater().getName());
        System.out.println("좌석");
        List<Seat> reservationSeats = findTicketing.getReservationSeats();
        for (Seat reservationSeat : reservationSeats) {
            System.out.println("(" + reservationSeat.getLineNumber() + ", " + reservationSeat.getColumnNumber() + ")");
        }
        System.out.println("시작시간 = " + findScreening.getStartTime());
    }

    // 7) 예매 취소
    // 예매 취소 시, 예매 내역 상태 변경과 가능 좌석 변경 보이기
    static void cancelTicketing(EntityManager em, Long ticketingId) {
        Ticketing findTicketing = em.createQuery("select t from Ticketing t join fetch t.screening join fetch t.reservationSeats where t.id = :tid", Ticketing.class)
                .setParameter("tid", ticketingId)
                .getSingleResult();

        findTicketing.cancel();

        List<Seat> reservationSeats = findTicketing.getReservationSeats();

        System.out.println("========== 변경 전 ==========");
        showSeats(em, findTicketing.getScreening().getId());
        
        for (Seat reservationSeat : reservationSeats) {
            reservationSeat.changeSeatState(SeatState.AVAILABLE);
            reservationSeat.setTicketingNull();
        }

        System.out.println("\n========== 변경 후 ==========");
        showSeats(em, findTicketing.getScreening().getId());
    }

    // 8) 영속성 전이를 이용한 삭제 (Cascade)
    // 고객을 삭제할 경우, 해당 사용자와 연관된 예매내역 자동 삭제 (DB에서 확인)
    static void removeClient(EntityManager em, Long clientId) {
        Client findClient = em.createQuery("select c from Client c join fetch c.ticketingList where c.id = :cid", Client.class)
                .setParameter("cid", clientId)
                .getSingleResult();

        List<Ticketing> ticketingList = findClient.getTicketingList();
        for (Ticketing ticketing : ticketingList) {
            cancelTicketing(em, ticketing.getId());
        }

        em.remove(findClient);
    }
}
