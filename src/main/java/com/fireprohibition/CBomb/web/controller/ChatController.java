package com.fireprohibition.CBomb.web.controller;

import com.fireprohibition.CBomb.domain.chat.ChatRoomRepository;
import com.fireprohibition.CBomb.domain.chat.MessageType;
import com.fireprohibition.CBomb.domain.movie.ScreeningMovie;
import com.fireprohibition.CBomb.service.ScreeningMovieService;
import com.fireprohibition.CBomb.web.dto.ChatMessageDto;
import com.fireprohibition.CBomb.service.MessageService;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ScreeningMovieService screeningMovieService;
	private final ChatRoomRepository chatRoomRepository;

	private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
	private final MessageService messageService;

	//Client가 SEND할 수 있는 경로
	//stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
	//"/pub/chat/enter"
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatMessageDto message) {
		if (screeningMovieService.isFished(message.getRoomId())) {
			message.setMessage("삭제된 채팅방 입니다.");
			message.setType(MessageType.DELETED_ROOM);
			template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
		}
		if (MessageType.ENTER.equals(message.getType())) {
			message.setMessage(message.getWriter() + "님이 접속하였습니다.");
		}
		messageService.save(message, MessageType.ENTER);
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}

	@MessageMapping(value = "/chat/message")
	public void message(ChatMessageDto message) {
		if (screeningMovieService.isFished(message.getRoomId())) {
			message.setMessage("삭제된 채팅방 입니다.");
			message.setType(MessageType.DELETED_ROOM);
			template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
		}
		messageService.save(message, MessageType.TALK);
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}
}
