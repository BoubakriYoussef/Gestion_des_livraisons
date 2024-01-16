package com.example.livraison_transcend.controller;

import com.example.livraison_transcend.entity.Produit;
import com.example.livraison_transcend.repository.ProduitRepository;
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
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping(path = "/produit")
    public String produit(Model model,
                         @RequestParam(name="page",defaultValue="0")int page,
                         @RequestParam(name="size",defaultValue="5")int size,
                         @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Produit> pageproduits=produitRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("produit",pageproduits.getContent());
        model.addAttribute("pages",new int[pageproduits.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "produit";
    }

    @GetMapping("/deleteProduit")
    public String deleteProduit(Long id,String keyword,int page){
        produitRepository.deleteById(id);
        return "redirect:/produit?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formProduit")
    public String formProduit(Model model){
        model.addAttribute("produit",new Produit());
        return "formProduit";
    }

    @PostMapping(path="/saveProduit")
    public String saveProduit(Model model, @Valid Produit produit, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formProduit";
        produitRepository.save(produit);
        return "redirect:/produit";
    }

    @GetMapping("/editProduit")
    public String editProduit(Model model, Long id){
        Produit produit = produitRepository.findById(id).orElse(null);
        if(produit==null) throw new RuntimeException("Produit Introuvable");
        model.addAttribute("produit",produit);
        return"editProduit";
    }

}
