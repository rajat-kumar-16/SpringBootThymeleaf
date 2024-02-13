package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.Services.TutorialService;
import com.example.demo.models.tutorial;

@Controller
@RequestMapping("/tutorials")
public class tutorialController {
	@Autowired
	private TutorialService tserv;
	
	@GetMapping("")
	public String getAll(Model model, @Param("keyword") String keyword) {
	    return tserv.getAll(model,keyword);
	}
	@GetMapping("/new")
	public String addTutorial(Model model) {
	   return tserv.addTutorial(model);
	}
	@PostMapping("/save")
	public String saveTutorial(tutorial tutorial, RedirectAttributes redirectAttributes) {
		return tserv.saveTutorial(tutorial, redirectAttributes);
	}
	@GetMapping("/{id}")
	public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		return tserv.editTutorial(id, model, redirectAttributes);
	}
	@GetMapping("/delete/{id}")
	public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		return tserv.deleteTutorial(id, model, redirectAttributes);
	}
	@GetMapping("/{id}/published/{status}")
	public String updateTutorialPublishedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean published,
	    Model model, RedirectAttributes redirectAttributes) {
		
	    return tserv.updateTutorialPublishedStatus(id, published, model, redirectAttributes);
	}

}
