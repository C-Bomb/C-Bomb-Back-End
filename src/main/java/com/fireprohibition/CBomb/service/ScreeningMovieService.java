package com.fireprohibition.CBomb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fireprohibition.CBomb.domain.chat.ChatRoom;
import com.fireprohibition.CBomb.domain.chat.ChatRoomRepository;
import com.fireprohibition.CBomb.domain.movie.Movie;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fireprohibition.CBomb.domain.BaseEntity;
import com.fireprohibition.CBomb.domain.movie.ScreeningMovie;
import com.fireprohibition.CBomb.domain.movie.ScreeningMovieRepository;
import com.fireprohibition.CBomb.domain.theater.TheaterRepository;

import lombok.RequiredArgsConstructor;

import static org.quartz.JobBuilder.newJob;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningMovieService extends BaseEntity {
	private final ScreeningMovieRepository screeningMovieRepository;
	private final TheaterRepository theaterRepository;
	private final ChatRoomRepository chatRoomRepository;


	public List<ScreeningMovie> findByTheaterId(Long theaterId) {
		return screeningMovieRepository.findByTheater(theaterRepository.findById(theaterId).get());
	}

	public ScreeningMovie findById(Long screeningMovieId) {
		return screeningMovieRepository.findById(screeningMovieId).get();
	}

	public void saveScreeningMovieService(ScreeningMovie screeningMovie) {
		//job
		long runningTime = (long)screeningMovie.getMovie().getRunningTime();
		LocalDateTime finishTime = screeningMovie.getStartTime().plusMinutes(runningTime);
//		newJob()
		screeningMovieRepository.save(screeningMovie);
	}

	public boolean isFished(Long chatRoomId) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();
		LocalDateTime startTime = chatRoom.getScreeningMovie().getStartTime();
		Long runningTime = Long.valueOf(chatRoom.getScreeningMovie().getMovie().getRunningTime());
		return startTime.plusMinutes(runningTime).isBefore(LocalDateTime.now());
	}
}
