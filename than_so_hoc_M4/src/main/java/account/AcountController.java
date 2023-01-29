package account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AcountController {
    private static final String EMAIL_REGEX = "^[A-Z_.a-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static Pattern pattern;
    private Matcher matcher;
    @Autowired
    HttpSession session;

    public AcountController() {
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    @GetMapping("login")
    String getIndex() {
        return "/login";
    }

    @PostMapping("/login")
    String validateEmail(@RequestParam("email") String email, String password, Model model) {
        boolean isValid = validate(email);
        if (isValid && password.equals("Thuydung88")) {
            session.setAttribute("email", email);
            session.setAttribute("name", password);
            return "redirect:/index.html";
        }
        model.addAttribute("message", "Sai thông tin tài khoản");
        return "login";
    }
    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("email");
        session.invalidate();
        return "redirect:/index.html";
    }
    private boolean validate(String regex) {
        return regex.matches(EMAIL_REGEX);
    }
}