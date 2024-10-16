/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.User;
import com.dtt.services.ReportMissingService;
import com.dtt.services.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ThienTu
 */

@Controller
@RequestMapping("/report-missing")
public class ReportMissingController {

    @Autowired
    private ReportMissingService reportMissingService;

    @Autowired
    private UserService userService;

    // Confirm report missing by ID
    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
    public String confirmReportMissing(@PathVariable("id") int id, RedirectAttributes redirectAttributes, Principal p) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER")) {
            return "redirect:/";
        }
        try {
            reportMissingService.confirmReportMissingById(id);
            redirectAttributes.addFlashAttribute("message", "Xác nhận báo thiếu thành công.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Xác nhận báo thiếu thất bại.");
        }
        return "redirect:/report-missing/list";
    }

    // Reject report missing by ID
    @RequestMapping(value = "/reject/{id}", method = RequestMethod.GET)
    public String rejectReportMissing(@PathVariable("id") int id, RedirectAttributes redirectAttributes, Principal p) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER")) {
            return "redirect:/";
        }
        try {
            reportMissingService.rejectReportMissingById(id);
            redirectAttributes.addFlashAttribute("message", "Từ chối báo thiếu thành công.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Từ chối báo thiếu thất bại.");
        }
        return "redirect:/report-missing/list";
    }

    @GetMapping("/list")
    public String listReports(Model model, Principal p) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER")) {
            return "redirect:/";
        }
        model.addAttribute("reports", reportMissingService.getReportMissings());
        return "report-missing-list";
    }

}
