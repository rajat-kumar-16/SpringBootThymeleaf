package com.example.demo.Services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Repository.TutorialRepo;
import com.example.demo.models.tutorial;

@Service
public class TutorialService {
	
	@Autowired
	private TutorialRepo tutrepo;
	public String getAll(Model model, String keyword) {
		try {
	      List<tutorial> tutorials = new ArrayList<tutorial>();

	      if (keyword == null) {
	    	  tutrepo.findAll().forEach(tutorials::add);
	      } else {
	    	 
	    	  tutrepo.findByTitleContainingIgnoreCase(keyword).forEach(tutorials::add);
	        model.addAttribute("keyword", keyword);
	      }
	      model.addAttribute("tutorials", tutorials);
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
		}

		return "tutorials";
	}
	public String addTutorial(Model model) {
		tutorial tutorial = new tutorial();
	    tutorial.setPublished(true);

	    model.addAttribute("tutorial", tutorial);
	    model.addAttribute("pageTitle", "Create new Tutorial");
	    return "tutorial_form";
	}
	
	public String saveTutorial(tutorial  tutorial, RedirectAttributes redirectAttributes) {
		try {
			tutrepo.save(tutorial);

		    redirectAttributes.addFlashAttribute("message", "The Tutorial has been saved successfully!");
		} catch (Exception e) {
		    redirectAttributes.addAttribute("message", e.getMessage());
		}
	    return "redirect:/tutorials";
	}
	
	public String editTutorial(Integer id, Model model, RedirectAttributes redirectAttributes) {
	    try {
	      tutorial tutorial = tutrepo.findById(id).get();

	      model.addAttribute("tutorial", tutorial);
	      model.addAttribute("pageTitle", "Edit Tutorial (ID: " + id + ")");

	      return "tutorial_form";
	    } catch (Exception e) {
	      redirectAttributes.addFlashAttribute("message", e.getMessage());

	      return "redirect:/tutorials";
	    }
	}
	
	public String updateTutorialPublishedStatus(Integer id, boolean published,
	      Model model, RedirectAttributes redirectAttributes) {
	    try {
	    	
	    	tutrepo.updatePublishedStatus(id, published);
	    	
	      String status = published ? "published" : "disabled";
	      String message = "The Tutorial id=" + id + " has been " + status;

	      redirectAttributes.addFlashAttribute("message", message);
	    } catch (Exception e) {
	    	System.out.println(e);
	    	redirectAttributes.addFlashAttribute("message", e.getMessage());
	    }

	    return "redirect:/tutorials";
	}
	
	public String deleteTutorial(Integer id, Model model, RedirectAttributes redirectAttributes) {
	    try {
	      tutrepo.deleteById(id);

	      redirectAttributes.addFlashAttribute("message", "The Tutorial with id=" + id + " has been deleted successfully!");
	    } catch (Exception e) {
	      redirectAttributes.addFlashAttribute("message", e.getMessage());
	    }
	    return "redirect:/tutorials";
	}
	
}
