package nephology;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by PLGrubskiM on 2017-02-12.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}