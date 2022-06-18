package com.fireprohibition.CBomb.web.controller;

import com.fireprohibition.CBomb.domain.chat.ChatRoom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fireprohibition.CBomb.service.ChatRoomService;
import com.fireprohibition.CBomb.service.MessageService;
import com.fireprohibition.CBomb.service.ScreeningMovieService;
import com.fireprohibition.CBomb.service.TheaterService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

	private final TheaterService theaterService;
	private final ChatRoomService chatRoomService;
	private final ScreeningMovieService screeningMovieService;
	private final MessageService messageService;

	@GetMapping("/theater/{theaterId}/{screeningMovieId}/chatRooms")
	public String chatRooms(
			@PathVariable("theaterId") Long theaterId,
			@PathVariable("screeningMovieId") Long screeningMovieId,
			Model model) {
		model.addAttribute("theater", theaterService.findById(theaterId));
		model.addAttribute("screeningMovie", screeningMovieService.findById(screeningMovieId));
        model.addAttribute("chatRooms", chatRoomService.findByScreeningMovie(screeningMovieId));
		return "chatList";
	}
  
	@PostMapping("/theater/{theaterId}/{screeningMovieId}/chatRooms")
	public String createChatRoom(
			@PathVariable("theaterId") Long theaterId,
			@PathVariable("screeningMovieId") Long screeningMovieId) {
		Long chatRoomId = chatRoomService.createChatRoom(screeningMovieId);
		return "redirect:/theater/" + theaterId + "/" + screeningMovieId + "/chatRooms";
	}

	@GetMapping("/theater/{theaterId}/{screeningMovieId}/chatRooms/{chatRoomId}")
	public String detailChatRoom(@PathVariable Long chatRoomId, Model model) {
		ChatRoom chatRoom = chatRoomService.findById(chatRoomId);
		if (screeningMovieService.isFished(chatRoomId)) {
			return "redirect:"+chatRoomId+"/evaluation";
		}
		model.addAttribute("room", chatRoom);
		model.addAttribute("lastMessages", messageService.findByChatRoom(chatRoomId));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		model.addAttribute("userName", name);
		return "chatRoom";
	}
}
