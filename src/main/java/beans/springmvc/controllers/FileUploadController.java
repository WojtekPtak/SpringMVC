package beans.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController  {

    @RequestMapping(value = "/uploadUsers", method = RequestMethod.GET)
    public String getImageView() {
        return "file";
    }

    @RequestMapping(value = "/uploadUsers", method = RequestMethod.POST)
    public ModelAndView uploadFile(MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView("file");

        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        FileOutputStream f = new FileOutputStream(path.substring(0, path.length() - 1) + file.getOriginalFilename());
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();

        modelAndView.getModel()
                .put("message", "File uploaded successfully!");
        return modelAndView;
    }

}
