package com.OAuth2.clientApp.GoogleOAuth2.Controller;

import com.OAuth2.clientApp.GoogleOAuth2.Service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ConsumerController {

    private final MessageService messageService;

    public ConsumerController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/ui")
    public String showMessage(Model model) {
        String message = messageService.fetchMessageFromServiceA();
        model.addAttribute("msg", message != null ? message : "Service A not responding.");
        return "message"; // Renders templates/message.html
    }
}
