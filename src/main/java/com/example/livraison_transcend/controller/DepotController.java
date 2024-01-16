package com.example.livraison_transcend.controller;

import com.example.livraison_transcend.entity.Depot;
import com.example.livraison_transcend.entity.Produit;
import com.example.livraison_transcend.repository.DepotRepository;
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
public class DepotController {

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private ProduitRepository   produitRepository;

    @GetMapping(path = "/depot")
    public String depot(Model model,
                          @RequestParam(name="page",defaultValue="0")int page,
                          @RequestParam(name="size",defaultValue="5")int size,
                          @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Depot> pagedepots=depotRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("depot",pagedepots.getContent());
        model.addAttribute("pages",new int[pagedepots.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "depot";
    }

    @GetMapping("/deleteDepot")
    public String deleteDepot(Long id,String keyword,int page){
        depotRepository.deleteById(id);
        return "redirect:/depot?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formDepot")
    public String formDepot(Model model){
        model.addAttribute("depot",new Depot());
        model.addAttribute("produits", produitRepository.findAll());
        return "formDepot";
    }

    @PostMapping(path="/saveDepot")
    public String saveDepot(Model model, @Valid Depot depot, BindingResult bindingResult, @RequestParam(name="id_produit", required=false) Long idProduit){
        if(bindingResult.hasErrors()) return "formDepot";
        Produit produit = produitRepository.findById(idProduit).orElse(null);
        depot.setProduit(produit);
        depotRepository.save(depot);
        return "redirect:/depot";
    }

    @GetMapping("/editDepot")
    public String editDepot(Model model, Long id){
        Depot depot = depotRepository.findById(id).orElse(null);
        if(depot==null) throw new RuntimeException("Depot Introuvable");
        model.addAttribute("depot",depot);
        return"editDepot";
    }
}
