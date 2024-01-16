package com.example.livraison_transcend.controller;

import com.example.livraison_transcend.repository.ClientRepository;
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
import com.example.livraison_transcend.entity.Client;


@Controller
public class ClientController {
    @Autowired

    private ClientRepository clientRepository;

    @GetMapping(path = "/client")
    public String client(Model model,
                         @RequestParam(name="page",defaultValue="0")int page,
                         @RequestParam(name="size",defaultValue="5")int size,
                         @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Client> pageclients=clientRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("client",pageclients.getContent());
        model.addAttribute("pages",new int[pageclients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "client";
    }

    @GetMapping("/deleteClient")
    public String deleteClient(Long id,String keyword,int page){
        clientRepository.deleteById(id);
        return "redirect:/client?page"+page+"&keyword="+keyword;
    }

    @GetMapping("/formClient")
    public String formClient(Model model){
        model.addAttribute("client",new Client());
        return "formClient";
    }

    @PostMapping(path="/saveClient")
    public String saveClient(Model model, @Valid Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formClient";
        clientRepository.save(client);
        return "redirect:client";
    }

    @GetMapping("/editClient")
    public String editClient(Model model, Long id){
        Client client = clientRepository.findById(id).orElse(null);
        if(client==null) throw new RuntimeException("Client Introuvable");
        model.addAttribute("client",client);
        return"editClient";
    }
}
