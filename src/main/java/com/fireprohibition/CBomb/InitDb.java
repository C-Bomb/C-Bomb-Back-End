package com.fireprohibition.CBomb;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import com.fireprohibition.CBomb.service.ScreeningMovieService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fireprohibition.CBomb.domain.chat.ChatParticipant;
import com.fireprohibition.CBomb.domain.chat.ChatRoom;
import com.fireprohibition.CBomb.domain.movie.Movie;
import com.fireprohibition.CBomb.domain.movie.MovieRepository;
import com.fireprohibition.CBomb.domain.movie.ScreeningMovie;
import com.fireprohibition.CBomb.domain.movie.ScreeningMovieRepository;
import com.fireprohibition.CBomb.domain.theater.Theater;
import com.fireprohibition.CBomb.domain.theater.TheaterRepository;
import com.fireprohibition.CBomb.domain.user.Role;
import com.fireprohibition.CBomb.domain.user.UserRepository;
import com.fireprohibition.CBomb.domain.valueType.Address;
import com.fireprohibition.CBomb.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDb {

	private final InitService initService;

	@PostConstruct
	public void init() {
		initService.setTheaters();
		initService.setMovies();
		initService.setScreeningMovies();
		initService.setUsers();
		initService.setChatRooms();
		initService.setAdmin();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	public static class InitService {

		private final EntityManager em;
		private final MovieRepository movieRepository;
		private final TheaterRepository theaterRepository;
		private final RegisterService registerService;
		private final UserRepository userRepository;
		private final ScreeningMovieRepository screeningMovieRepository;
		private final ScreeningMovieService screeningMovieService;

		public void setTheaters() {
			Theater theater1 = Theater.builder()
					.name("CGV 광주용봉")
					.address(new Address("광주광역시", "북구", "문화소통로 170"))
					.contactNumber("1544-1122")
					.build();
			em.persist(theater1);

			Theater theater2 = Theater.builder()
					.name("메가박스 전대")
					.address(new Address("광주광역시", "북구", "우치로 60"))
					.contactNumber("1544-0070")
					.build();
			em.persist(theater2);
		}

		public void setMovies() {
			Movie movie1 = Movie.builder()
					.name("니 부모 얼굴이 보고싶다.")
					.runningTime(111)
					.openDate(LocalDate.of(2022, 4, 27))
					.infoLink("https://movie.naver.com/movie/bi/mi/basic.naver?code=159812")
					.posterPath("/images/mother_face.jpg")
					.build();
			em.persist(movie1);

			Movie movie2 = Movie.builder()
					.name("신기한 동물들과 덤블도어의 비밀")
					.runningTime(142)
					.openDate(LocalDate.of(2022, 4, 13))
					.infoLink("https://movie.naver.com/movie/bi/mi/basic.naver?code=164122")
					.posterPath("/images/magic.jpg")
					.build();
			em.persist(movie2);

			Movie movie3 = Movie.builder()
					.name("공기살인")
					.runningTime(108)
					.openDate(LocalDate.of(2022, 4, 22))
					.infoLink("https://movie.naver.com/movie/bi/mi/basic.naver?code=196362")
					.posterPath("/images/air_kill.jpg")
					.build();
			em.persist(movie3);

			Movie movie4 = Movie.builder()
					.name("서울괴담")
					.runningTime(123)
					.openDate(LocalDate.of(2022, 4, 27))
					.infoLink("https://movie.naver.com/movie/bi/mi/basic.naver?code=215791")
					.posterPath("/images/soul.jpg")
					.build();
			em.persist(movie4);

			Movie movie5 = Movie.builder()
					.name("2분걸리는 영화")
					.runningTime(2)
					.openDate(LocalDate.of(2022, 4, 27))
					.infoLink("https://movie.naver.com/movie/bi/mi/basic.naver?code=215791")
					.posterPath("/images/soul.jpg")
					.build();
			em.persist(movie5);
		}

		;

		public void setScreeningMovies() {
			ScreeningMovie screeningMovie1 = ScreeningMovie.builder()
					.movie(movieRepository.findByName("니 부모 얼굴이 보고싶다.").get())
					.startTime(LocalDateTime.of(2022, 4, 28, 16, 30))
					.endTime(LocalDateTime.of(2022, 4, 28, 18, 31))
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.theaterNumber(1)
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie1);

			ScreeningMovie screeningMovie2 = ScreeningMovie.builder()
					.movie(movieRepository.findByName("니 부모 얼굴이 보고싶다.").get())
					.startTime(LocalDateTime.of(2022, 4, 28, 18, 50))
					.endTime(LocalDateTime.of(2022, 4, 28, 20, 51))
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.theaterNumber(1)
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie2);

			ScreeningMovie screeningMovie3 = ScreeningMovie.builder()
					.movie(movieRepository.findByName("니 부모 얼굴이 보고싶다.").get())
					.startTime(LocalDateTime.of(2022, 4, 28, 21, 10))
					.endTime(LocalDateTime.of(2022, 4, 28, 23, 11))
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.theaterNumber(1)
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie3);

			ScreeningMovie screeningMovie4 = ScreeningMovie.builder()
					.movie(movieRepository.findByName("니 부모 얼굴이 보고싶다.").get())
					.startTime(LocalDateTime.of(2022, 4, 28, 15, 20))
					.endTime(LocalDateTime.of(2022, 4, 28, 17, 21))
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.theaterNumber(6)
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie4);

			ScreeningMovie screeningMovie5 = ScreeningMovie.builder()
					.startTime(LocalDateTime.of(2022, 4, 28, 17, 40))
					.endTime(LocalDateTime.of(2022, 4, 28, 18, 41))
					.theaterNumber(6)
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.movie(movieRepository.findByName("니 부모 얼굴이 보고싶다.").get())
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie5);

			ScreeningMovie screeningMovie6 = ScreeningMovie.builder()
					.startTime(LocalDateTime.of(2022, 4, 28, 20, 00))
					.endTime(LocalDateTime.of(2022, 4, 28, 22, 01))
					.theaterNumber(6)
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.movie(movieRepository.findByName("니 부모 얼굴이 보고싶다.").get())
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie6);

			ScreeningMovie screeningMovie7 = ScreeningMovie.builder()
					.startTime(LocalDateTime.of(2022, 4, 28, 17, 20))
					.endTime(LocalDateTime.of(2022, 4, 28, 19, 52))
					.theaterNumber(4)
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.movie(movieRepository.findByName("신기한 동물들과 덤블도어의 비밀").get())
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie7);

			ScreeningMovie screeningMovie8 = ScreeningMovie.builder()
					.startTime(LocalDateTime.of(2022, 4, 28, 20, 10))
					.endTime(LocalDateTime.of(2022, 4, 28, 22, 42))
					.theaterNumber(4)
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.movie(movieRepository.findByName("신기한 동물들과 덤블도어의 비밀").get())
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie8);

			ScreeningMovie screeningMovie9 = ScreeningMovie.builder()
					.movie(movieRepository.findByName("신기한 동물들과 덤블도어의 비밀").get())
					.startTime(LocalDateTime.of(2022, 4, 28, 18, 10))
					.endTime(LocalDateTime.of(2022, 4, 28, 20, 42))
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.theaterNumber(7)
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie9);

			ScreeningMovie screeningMovie10 = ScreeningMovie.builder()
					.startTime(LocalDateTime.of(2022, 4, 28, 21, 0))
					.endTime(LocalDateTime.of(2022, 4, 28, 23, 32))
					.theaterNumber(7)
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.movie(movieRepository.findByName("신기한 동물들과 덤블도어의 비밀").get())
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie10);

			ScreeningMovie screeningMovie11 = ScreeningMovie.builder()
					.startTime(LocalDateTime.of(2022, 4, 28, 18, 40))
					.endTime(LocalDateTime.of(2022, 4, 28, 20, 38))
					.theaterNumber(5)
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.movie(movieRepository.findByName("공기살인").get())
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie11);

			ScreeningMovie screeningMovie12 = ScreeningMovie.builder()
					.startTime(LocalDateTime.now())
					.endTime(LocalDateTime.of(2022, 4, 28, 20, 38))
					.theaterNumber(5)
					.theater(theaterRepository.findByName("메가박스 전대").get())
					.movie(movieRepository.findByName("2분걸리는 영화").get())
					.build();
			screeningMovieService.saveScreeningMovieService(screeningMovie12);

		}

		public void setUsers() {
			registerService.joinUser("userA", "userA");
			registerService.joinUser("userB", "userB");
			registerService.joinUser("userC", "userC");
			registerService.joinUser("userD", "userD");
			registerService.joinUser("userE", "userE");
		}

		public void setChatRooms() {
			ChatRoom chatRoom = ChatRoom.builder()
					.maxParticipant(4)
					.screeningMovie(screeningMovieRepository.findFirstByOrderByIdDesc())
					.build();
			em.persist(chatRoom);
			ChatParticipant userA = ChatParticipant.builder()
					.chatRoom(chatRoom)
					.user(userRepository.findByUsername("userA").get())
					.build();
			em.persist(userA);
			ChatParticipant userB = ChatParticipant.builder()
					.chatRoom(chatRoom)
					.user(userRepository.findByUsername("userB").get())
					.build();
			em.persist(userB);
			ChatParticipant userC = ChatParticipant.builder()
					.chatRoom(chatRoom)
					.user(userRepository.findByUsername("userB").get())
					.build();
			em.persist(userC);

			ChatRoom testChatRoom = ChatRoom.builder()
					.maxParticipant(4)
					.screeningMovie(screeningMovieRepository.findFirstByOrderByIdDesc())
					.build();
			em.persist(testChatRoom);
			ChatParticipant testUserA = ChatParticipant.builder()
					.chatRoom(testChatRoom)
					.user(userRepository.findByUsername("userA").get())
					.build();
			em.persist(testUserA);
			ChatParticipant testUserB = ChatParticipant.builder()
					.chatRoom(testChatRoom)
					.user(userRepository.findByUsername("userB").get())
					.build();
			em.persist(testUserB);
			ChatParticipant testUserC = ChatParticipant.builder()
					.chatRoom(testChatRoom)
					.user(userRepository.findByUsername("userB").get())
					.build();
			em.persist(testUserC);
		}

		public void setAdmin() {
			registerService.joinUser("admin", "admin", Role.ADMIN);
		}
	}
}
