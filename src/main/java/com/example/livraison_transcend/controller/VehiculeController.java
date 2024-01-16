package com.example.livraison_transcend.controller;

import com.example.livraison_transcend.entity.Vehicule;
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
public class VehiculeController{

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @GetMapping(path = "/vehicule")
    public String vehicule(Model model,
                         @RequestParam(name="page",defaultValue="0")int page,
                         @RequestParam(name="size",defaultValue="5")int size,
                         @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Vehicule> pagevehicule=vehiculeRepository.findByImmatriculeContains(keyword, PageRequest.of(page,size));
        model.addAttribute("vehicule",pagevehicule.getContent());
        model.addAttribute("pages",new int[pagevehicule.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "vehicule";
    }

    @GetMapping("/deleteVehicule")
    public String deleteVehicule(Long id,String keyword,int page){
        vehiculeRepository.deleteById(id);
        return "redirect:/vehicule?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formVehicule")
    public String formVehicule(Model model){
        model.addAttribute("vehicule",new Vehicule());
        return "formVehicule";
    }

    @PostMapping(path="/saveVehicule")
    public String saveVehicule(Model model, @Valid Vehicule vehicule, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formVehicule";
        vehiculeRepository.save(vehicule);
        return "redirect:/vehicule";
    }

    @GetMapping("/editVehicule")
    public String editVehicule(Model model, Long id){
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);
        if(vehicule==null) throw new RuntimeException("Vehicule Introuvable");
        model.addAttribute("vehicule",vehicule);
        return"editVehicule";
    }


}
