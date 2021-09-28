package com.traker.coronavirus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.traker.coronavirus.model.LocationStats;
import com.traker.coronavirus.service.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService service;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = service.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",service.getAllStats());
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}

}
