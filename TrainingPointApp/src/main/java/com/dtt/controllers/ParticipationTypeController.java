/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.ParticipationType;
import com.dtt.pojo.User;
import com.dtt.services.ParticipationTypeService;
import com.dtt.services.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ThienTu
 */

@Controller
@RequestMapping("/participation-type")
public class ParticipationTypeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipationTypeService participationTypeService;

    @RequestMapping("/list")
    String list(Model model, Principal p) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER") || user.getUserRole().equals("ROLE_ASSISTANT")) {
            return "redirect:/";
        }
        model.addAttribute("participationTypes", participationTypeService.getParticipationTypes());
        return "participationTypes";
    }

    @GetMapping("/add")
    public String createView(Model model) {
        model.addAttribute("participationType", new ParticipationType());
        return "addParticipationType";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute(value = "participationType") @Valid ParticipationType s,
            BindingResult rs, Principal p) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER") || user.getUserRole().equals("ROLE_ASSISTANT")) {
            return "redirect:/";
        }
        if (!rs.hasErrors()) {
            try {
                this.participationTypeService.addOrUpdate(s);

                return "redirect:/participation-type/list";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                // Optionally add the exception message to the model to show the error on the page
                rs.rejectValue("name", "error.participationType", "There was an error processing your request");
            }
        }
        return "addParticipationType";
    }

    @GetMapping("/update/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id, Principal p) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER") || user.getUserRole().equals("ROLE_ASSISTANT")) {
            return "redirect:/";
        }
        model.addAttribute("participationType", this.participationTypeService.getParticipationTypeById(id));

        return "addParticipationType";
    }

    @GetMapping("/remove/{id}")
    public String remove(Model model, @PathVariable(value = "id") int id, Principal p) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER") || user.getUserRole().equals("ROLE_ASSISTANT")) {
            return "redirect:/";
        }

        this.participationTypeService.delete(this.participationTypeService.getParticipationTypeById(id));
        return "redirect:/participation-type/list";
    }
}
