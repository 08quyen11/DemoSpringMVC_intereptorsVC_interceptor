
package demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/a")
    public String home(Model model) {
        model.addAttribute("message", "Chào mừng đến với MVC");
        return "index"; 
    }
}