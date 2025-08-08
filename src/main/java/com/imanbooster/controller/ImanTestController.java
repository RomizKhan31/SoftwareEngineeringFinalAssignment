package com.imanbooster.controller;

import com.imanbooster.domain.ImanTest;
import com.imanbooster.domain.Motivation;
import com.imanbooster.domain.User;
import com.imanbooster.repository.ImanTestRepository;
import com.imanbooster.repository.MotivationRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ImanTestController {
    @Autowired
    private ImanTestRepository imanTestRepository;
    @Autowired
    private MotivationRepository motivationRepository;

    @GetMapping("/iman-test")
    public String showTestPage() {
        return "iman-test";
    }

    @PostMapping("/submit-test")
    public String submitTest(HttpServletRequest request, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        // Calculate total score from all answers
        int score = 0;
        for (int i = 1; i <= 5; i++) {
            score += Integer.parseInt(request.getParameter("q" + i));
        }

        // Save latest test
        ImanTest test = new ImanTest();
        test.setScore(score);
        test.setUser(user);
        test.setDateTaken(LocalDateTime.now());
        imanTestRepository.save(test);

        // If score dropped, add motivation (if possible)
        List<ImanTest> tests = imanTestRepository.findByUserOrderByDateTakenDesc(user);
        if (tests.size() > 1 && score < tests.get(1).getScore()) {
            int motivationalRange = (score < 5) ? 1 : (score < 10) ? 2 : 3;
            Motivation motivation = motivationRepository.getRandomMotivationByRange(motivationalRange);
            session.setAttribute("motivation",
                    (motivation != null) ? motivation.getMessage() : "Keep going! You can do better next time."
            );
        }

        return "redirect:/dashboard";
    }
}