package beans.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonExceptionController implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exc) {
        ModelAndView modelAndView = new ModelAndView("exception");
/*        if (exc instanceof IllegalStateException) {
        }*/

        modelAndView.getModel().put("message", exc.getLocalizedMessage());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<B>Exception class:</B> %s</BR>", exc.getClass().getSimpleName()));
        sb.append(String.format("<B>Request URI:</B> %s</BR>", request.getRequestURI()));
        sb.append(String.format("<B>Status:</B> %d</BR>", response.getStatus()));
        modelAndView.getModel().put("description", sb.toString());

        // TODO: use it in exception.ftl to back to previous page not to homepage!
        String referer = request.getHeader("Referer");
        modelAndView.getModel().put("referer", referer);
        return modelAndView;
    }
}