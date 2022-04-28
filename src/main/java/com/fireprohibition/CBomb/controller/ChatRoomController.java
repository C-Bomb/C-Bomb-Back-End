package com.fireprohibition.CBomb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fireprohibition.CBomb.domain.theater.Theater;
import com.fireprohibition.CBomb.service.ChatRoomService;
import com.fireprohibition.CBomb.service.ScreeningMovieService;
import com.fireprohibition.CBomb.service.TheaterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

  private final ChatRoomRepository repository;
	private final TheaterService theaterService;
	private final ChatRoomService chatRoomService;
	private final ScreeningMovieService screeningMovieService;

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
  
  //채팅방 목록 조회
    @GetMapping(value = "/chat/rooms")
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list", repository.findAllRooms());

        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/chat/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){

        log.info("# Create Chat Room , name: " + name);
        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(name));
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/chat/room")
    public void getRoom(String roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", repository.findRoomById(roomId));
    }
}