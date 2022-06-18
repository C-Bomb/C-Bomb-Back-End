package com.fireprohibition.CBomb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fireprohibition.CBomb.domain.chat.ChatParticipantRepository;
import com.fireprohibition.CBomb.domain.chat.ChatRoom;
import com.fireprohibition.CBomb.domain.chat.ChatRoomRepository;
import com.fireprohibition.CBomb.domain.movie.ScreeningMovie;
import com.fireprohibition.CBomb.domain.movie.ScreeningMovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;
	private final ScreeningMovieRepository screeningMovieRepository;

	public List<ChatRoom> findByScreeningMovie(Long screeningMovieId) {
		return chatRoomRepository.findByScreeningMovie(screeningMovieRepository.findById(screeningMovieId).get());
	}

	public ChatRoom findById(Long id) {
		return chatRoomRepository.findById(id).get();
	}

	public ScreeningMovie findScreeningMovieById(Long id) {
		return chatRoomRepository.findById(id).get().getScreeningMovie();
	}

	@Transactional
	public Long createChatRoom(Long screeningMovieId) {
		ScreeningMovie screeningMovie = screeningMovieRepository.findById(screeningMovieId).get();
		ChatRoom chatRoom = new ChatRoom(4, screeningMovie);
		return chatRoomRepository.save(chatRoom).getId();
	}

	@Transactional
	public void removeChatRoom(Long chatRoomId) {
		chatRoomRepository.deleteById(chatRoomId);
	}

}
