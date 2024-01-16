package com.example.livraison_transcend.controller;

import com.example.livraison_transcend.entity.Livraison;
import com.example.livraison_transcend.entity.Livreur;
import com.example.livraison_transcend.repository.CommandeRepository;
import com.example.livraison_transcend.repository.LivraisonRepository;
import com.example.livraison_transcend.repository.LivreurRepository;
import com.example.livraison_transcend.repository.VehiculeRepository;
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
public class LivraisonController {

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;


    @Autowired
    private LivreurRepository livreurRepository;



    @GetMapping(path = "/livraison")
    public String livraison(Model model,
                         @RequestParam(name="page",defaultValue="0")int page,
                         @RequestParam(name="size",defaultValue="5")int size,
                         @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Livraison> pagelivraisons=livraisonRepository.findByIntituleContains(keyword, PageRequest.of(page,size));
        model.addAttribute("livraison",pagelivraisons.getContent());
        model.addAttribute("pages",new int[pagelivraisons.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "livraison";
    }

    @GetMapping("/deleteLivraison")
    public String deleteLivraison(Long id,String keyword,int page){
        livraisonRepository.deleteById(id);
        return "redirect:/livraison?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formLivraison")
    public String formLivraison(Model model){
        model.addAttribute("livraison",new Livraison());
        model.addAttribute("commandes", commandeRepository.findAll());
        model.addAttribute("vehicules", vehiculeRepository.findAll());
        model.addAttribute("livreurs", livreurRepository.findAll());
        return "formLivraison";
    }

    @PostMapping(path="/saveLivraison")
    public String saveLivraison(Model model, @Valid Livraison livraison, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formLivraison";
        livraisonRepository.save(livraison);
        return "redirect:/livraison";
    }

    @GetMapping("/editLivraison")
    public String editLivraison(Model model, Long id){
        Livraison livraison = livraisonRepository.findById(id).orElse(null);
        if(livraison==null) throw new RuntimeException("Livraison Introuvable");
        model.addAttribute("livraison",livraison);
        return"editLivraison";
    }
}
