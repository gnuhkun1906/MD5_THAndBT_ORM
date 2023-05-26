package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Song;
import ra.service.ISongService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("song")
public class SongController {
    @Autowired
    private ISongService songService;
    @GetMapping("/findAll")
    public ModelAndView getAllSong(){
        ModelAndView mav=new ModelAndView("song/listAllSong");
        List<Song> list = songService.findAll();
        mav.addObject("listSong", list);
        return mav;
    }
    @GetMapping("/formCreate")
    public String showFormCreate(Model model){
        Song newSong = new Song();
        model.addAttribute("newSong", newSong);
        return "song/createSong";
    }

    @PostMapping("/create")
    public String createSong(@RequestParam("name") String name,
                             @RequestParam("kindOfMusic") String kind,
                             @RequestParam("file") MultipartFile fileLoad) throws IOException {
        String pathFileUpload = "D:\\TH_BT_MD5_SpringBoot\\ThaoTacCSDL_ORM_TH\\src\\main\\resources\\assets\\audio\\";
        File file = new File(pathFileUpload);
        if (file.exists()) {
            file.mkdirs();
        }
        String imgUrl = fileLoad.getOriginalFilename();
        FileCopyUtils.copy(fileLoad.getBytes(), new File(pathFileUpload + File.separator + imgUrl));
        Song newSong= new Song(name,kind,imgUrl);
        songService.save(newSong);
        return "redirect:findAll";
    }









}
