package com.example.livraison_transcend.controller;

import com.example.livraison_transcend.entity.Client;
import com.example.livraison_transcend.entity.Livreur;
import com.example.livraison_transcend.repository.LivreurRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LivreurController {

    @Autowired
    private LivreurRepository livreurRepository;

    @GetMapping(path = "/livreur")
    public String livreur(Model model,
                         @RequestParam(name="page",defaultValue="0")int page,
                         @RequestParam(name="size",defaultValue="5")int size,
                         @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Livreur> pagelivreurs =livreurRepository.findByUsernameContains(keyword, PageRequest.of(page,size));
        model.addAttribute("livreur",pagelivreurs.getContent());
        model.addAttribute("pages",new int[pagelivreurs.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "livreur";
    }

    @GetMapping("/deleteLivreur")
    public String deleteLivreur(Long id,String keyword,int page){
        livreurRepository.deleteById(id);
        return "redirect:/livreur?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formLivreur")
    public String formLivreur(Model model){
        model.addAttribute("livreur",new Livreur());
        return "formLivreur";
    }

    @PostMapping(path="/saveLivreur")
    public String saveLivreur(Model model, @Valid Livreur livreur, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formLivreur";
        livreurRepository.save(livreur);
        return "redirect:/livreur";
    }

    @GetMapping("/editLivreur")
    public String editLivreur(Model model, Long id){
        Livreur livreur = livreurRepository.findById(id).orElse(null);
        if(livreur==null) throw new RuntimeException("Livreur Introuvable");
        model.addAttribute("livreur",livreur);
        return"editLivreur";
    }
}
