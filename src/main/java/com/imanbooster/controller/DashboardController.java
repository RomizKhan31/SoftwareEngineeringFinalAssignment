package com.imanbooster.controller;

import com.imanbooster.domain.ImanTest;
import com.imanbooster.domain.User;
import com.imanbooster.repository.ImanTestRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private ImanTestRepository imanTestRepository;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<ImanTest> tests = imanTestRepository.findByUserOrderByDateTakenDesc(user);
        if (!tests.isEmpty()) {
            model.addAttribute("currentScore", tests.get(0).getScore());
        }
        model.addAttribute("tests", tests);
        String motivation = (String) session.getAttribute("motivation");
        if (motivation != null) {
            model.addAttribute("motivation", motivation);
            session.removeAttribute("motivation");
        }
        return "dashboard";
    }
}
