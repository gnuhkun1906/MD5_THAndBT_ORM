package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Pictures;
import ra.service.IPictureService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/picture")
public class PicturesController {
    @Autowired
    private IPictureService pictureService;

    @GetMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView("/picture/listFeedback");
        List<Pictures> list= pictureService.findAll();
        LocalDate date= LocalDate.now();
        mav.addObject("date", date);
        mav.addObject("listFeedback", list);
        return mav;
    }
    public void getTimeNow(){
        LocalDateTime current = LocalDateTime.now();

    }
    @GetMapping("/form")
    public String form(Model model){
        Pictures newPictures= new Pictures();
        model.addAttribute("newFeedback", newPictures);
        return "/picture/comment";
    }
    @PostMapping("/create")
    public String create(@RequestParam("evaluate") String evaluate,
                         @RequestParam("author") String author,
                         @RequestParam("feedback") String feedback){
        Pictures newPicture= new Pictures(Integer.parseInt(evaluate),author,feedback, LocalDate.now() );
        pictureService.save(newPicture);
        return "redirect:/findAll";
    }
    @GetMapping("/like/{id}")
    public String likes(Model model, @PathVariable("id") int countId){
        Pictures pictures = pictureService.findById(countId);
        pictures.setLikes(pictures.getLikes()+1);
        pictureService.save(pictures);
        return "redirect:/picture/findAll";
    }

}







