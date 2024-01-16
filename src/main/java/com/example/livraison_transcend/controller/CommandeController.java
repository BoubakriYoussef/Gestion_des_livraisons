package com.example.livraison_transcend.controller;

import com.example.livraison_transcend.entity.Client;
import com.example.livraison_transcend.entity.Commande;
import com.example.livraison_transcend.repository.ClientRepository;
import com.example.livraison_transcend.repository.CommandeRepository;
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
public class CommandeController {
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProduitRepository produitRepository;


    @GetMapping(path = "/commande")
        public String commande(Model model,
                            @RequestParam(name="page",defaultValue="0")int page,
                            @RequestParam(name="size",defaultValue="5")int size,
                            @RequestParam(name="keyword",defaultValue="")String keyword){
            Page<Commande> pagecommandes=commandeRepository.findByIntituleCommandeContains(keyword, PageRequest.of(page,size));
            model.addAttribute("commande",pagecommandes.getContent());
            model.addAttribute("pages",new int[pagecommandes.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("keyword",keyword);
            return "commande";
        }

        @GetMapping("/deleteCommande")
        public String deleteCommande(Long id,String keyword,int page){
            commandeRepository.deleteById(id);
            return "redirect:/commande?page="+page+"&keyword="+keyword;
        }

        @GetMapping("/formCommande")
        public String formCommande(Model model){
            model.addAttribute("commande",new Commande());
            model.addAttribute("clients", clientRepository.findAll());
            model.addAttribute("produits", produitRepository.findAll());
            return "formCommande";
        }

        @PostMapping(path="/saveCommande")
        public String saveCommande(Model model, @Valid Commande commande, BindingResult bindingResult,@RequestParam(name="id_client", required=false) Long idClient){
            if(bindingResult.hasErrors()) return "formCommande";
            commandeRepository.save(commande);
            return "redirect:/commande";
        }

        @GetMapping("/editCommande")
        public String editCommande(Model model, Long id){
            Commande commande = commandeRepository.findById(id).orElse(null);
            if(commande==null) throw new RuntimeException("Commande Introuvable");
            model.addAttribute("commande",commande);
            return"editCommande";
        }
}


