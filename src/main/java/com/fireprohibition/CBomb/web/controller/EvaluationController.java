package com.fireprohibition.CBomb.web.controller;

import com.fireprohibition.CBomb.domain.chat.ChatRoomRepository;
import com.fireprohibition.CBomb.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EvaluationController {
    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/theater/{theaterId}/{screeningMovieId}/chatRooms/{chatRoomId}/evaluation")
    public String evaluationPage(@PathVariable Long chatRoomId) {
        try {
            chatRoomRepository.findById(chatRoomId).orElseThrow();
        } catch (Exception e) {
            return "evalEnd";
        }
        return "mannerEval";
    }

    @PostMapping("/theater/{theaterId}/{screeningMovieId}/chatRooms/{chatRoomId}/evaluation")
    public String postEvaluation(@PathVariable Long chatRoomId) {
        chatRoomService.removeChatRoom(chatRoomId);
        return "evalEnd";
    }
}
