package trainers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import model.Trainer;

import java.io.File;

@Controller
@RequestMapping("/trainersList")
public class TrainersService {

    @Autowired
   TrainersController trainersController;

    @PostMapping("")
    public String showListProduct(Model model) {
        model.addAttribute("trainersList", trainersController.getAll());
        return "/HTML_TrainerList/trainersList";
    }

    @GetMapping("")
    public String search(@RequestParam(value = "name", defaultValue = "") String name, Model model){
        model.addAttribute("trainersList", trainersController.findListTrainerByName(name));
        return "/HTML_TrainerList/trainersList";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        trainersController.delete(id);
        return "redirect:/trainersList";
    }

    @GetMapping("/create")
    public String showCreate() {
        return "/HTML_TrainerList/create";
    }

    @PostMapping("/create")
    public String create(Trainer trainer, @RequestParam MultipartFile upImg) {
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:/MODUL_4/than_so_hoc_M4/src/main/webapp/WEB-INF/IMG/" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        trainer.setImg("/img/" + nameFile);
        trainersController.save(trainer);
        return "redirect:/trainersList";
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam int id, Model model) {
        Trainer trainer = trainersController.findTrainerById(id);
        model.addAttribute("trainer",trainer);
        return "/HTML_TrainerList/edit";
    }

    @PostMapping("/edit")
    public String edit( @RequestParam MultipartFile upImg,  Trainer trainer ) {
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:/MODUL_4/than_so_hoc_M4/src/main/webapp/WEB-INF/IMG/" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        trainer.setImg("/img/" + nameFile);
        trainersController.edit(trainer);
        return "redirect:/trainersList";
    }
}
