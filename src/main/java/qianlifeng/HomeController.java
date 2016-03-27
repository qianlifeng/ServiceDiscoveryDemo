package qianlifeng;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.management.ManagementFactory;

@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String index(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "you need login";
        }
        String user = session.getAttribute("user").toString();
        return "welcome:" + user + ", from instance " + ManagementFactory.getRuntimeMXBean().getName();
    }

    @RequestMapping("/login")
    public String getLoginPage(HttpSession session) {
        return "forward:/loginPage.html";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String doLogout(HttpSession session) {
        session.removeAttribute("user");
        return "logout successfully";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpSession session, @RequestParam("user") String user) {
        session.setAttribute("user", user);
        return "redirect:/";
    }
}
