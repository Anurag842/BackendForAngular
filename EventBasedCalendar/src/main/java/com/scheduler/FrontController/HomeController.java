package com.scheduler.FrontController;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daos.ClientDao;
import com.daos.EventDao;
import com.entities.Client;
import com.entities.Events;



@RestController
public class HomeController {
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	EventDao eventDao;
	
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public ModelAndView Print(ModelMap map){
		System.out.println("I m in Preform");
		
		ModelAndView mv=new ModelAndView("HomePage");
		Client cObj=new Client();
		mv.addObject("cObj",cObj);   
		return mv;
	}
	
	@RequestMapping(value="/submitOnDb" , method=RequestMethod.POST)
	public ResponseEntity<String> submitClient(@RequestBody Client cObj){
	
//		ModelAndView mv;
//		if(result.hasErrors())
//		{
//			mv=new ModelAndView("HomePage");
//			mv.addObject("msg","There is some problem..Please fill the Details");
//			return mv;
//		}
		
//		try{
		System.out.println("I m here");
		System.out.print("Client Name : "+cObj.getClientName());
			Client clientNew=null;
			//if(msg!="")
			int flag=0;
			List<Client>  clientList=clientDao.getAllClient();
			for(Client c:clientList)
			{
				System.out.println("I m in loop");
				if((c.getClientName().equals(cObj.getClientName()))/*&&(c.getArrivalDate().equals(cObj.getArrivalDate()))&&(c.getDeptDate().equals(cObj.getDeptDate()))&&(c.getAgenda().equals(cObj.getAgenda()))&&(c.getAccount().equals(cObj.getAccount()))*/)
				{
					System.out.println("I m in If");
						int cIdPrevious=c.getClientId();
						clientNew=clientDao.getClientById(cIdPrevious);
						flag=1;
						break;
				
				}
				
			}
			
			if(flag==0) {
				clientDao.addClient(cObj);
			}
			
			return new ResponseEntity<String>("Done",HttpStatus.OK);
			
//		String eventData=cObj.getEventData();
//		
//		
//		
//		String events[]=eventData.split("\n");
//		for(String event:events){
//			String events2[]=event.split(",");
//			Events eObj=new Events();
//			if(flag==1)
//			eObj.setCli(clientNew);
//			else
//				eObj.setCli(cObj);
//				
//			eObj.setTitle(events2[0]);
//			
//			
//			//System.out.println("start Date and Time"+events2[1]);
//			//System.out.println("end Date and Time"+events2[2]);
//			
//			events2[1]=events2[1].replace("T"," ");
//			events2[2]=events2[2].replace("T"," ");
//			
//			System.out.println("start Date and Time"+events2[1]);
//			System.out.println("end Date and Time"+events2[2]);
//			
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
//			Date startDateTime =sdf.parse(events2[1]);
//			Date endDateTime =sdf.parse(events2[2]);
//			
//			eObj.setStart(startDateTime);
//			
//			
//			eObj.setEnd(endDateTime);
//			
//			
//			
//			//eObj.setPlace(events2[3]);
//			//eObj.setStartTimeDate(events2[1]);
//			//eObj.setEndTimeDate(events2[2]);
//			eventDao.addEvent(eObj);
//			
//		}
//		}catch(Exception e){
//			e.printStackTrace();
//			mv=new ModelAndView("HomePage");
//			clientDao.delClient(cObj.getClientId());
//			mv.addObject("msg","There is some problem..Please fill the Event Details");
//			return mv;
//		}
//		mv=new ModelAndView("HomePage");
//		mv.addObject("msg","Client Itinerary Successfully");
//		return mv;
//		
	}
	
	@RequestMapping(value="/view" , method=RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody List<Client> viewClient(ModelMap map){
			List<Client>  clientList=clientDao.getAllClient();
			for(Client client:clientList)
			{
				Date arrvdt=client.getArrivalDate();
				Date deptdt=client.getDeptDate();
				DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
				String strDate1=dateformat.format(arrvdt);
				String strDate2=dateformat.format(deptdt);
				client.setStringarrive(strDate1);
				client.setStringdepart(strDate2);
			
				
			}
			map.addAttribute("clients",clientList);
			return clientList;
		
	}
	
//	@RequestMapping(value="/viewClientEvent/{clientId}" , method=RequestMethod.GET)
//	public ModelAndView viewClientEvent(@PathVariable int clientId,ModelMap map){
//			System.out.println(clientId);
//			
//			
//			Client c=clientDao.viewClient(clientId);
//			
//			
//			ModelAndView mv=new ModelAndView("EventView"); 
//			mv.addObject("clientName",c.getClientName());  
//			mv.addObject("clientId",c.getClientId()); 
//			List<Events> eventList=eventDao.viewAllEventsById(clientId);
//			for(Events event:eventList){
//			System.out.println("Event"+event);
//			}
//			mv.addObject("EventsList", eventList);
//			
//			return mv;
//		
//	}
	@RequestMapping(value="/deleteEvent/{eventId}/{clientId}",method=RequestMethod.GET)
	public ModelAndView deleteEvent(@PathVariable int eventId,ModelMap map,@PathVariable int clientId)
	{
		eventDao.deleteEvent(eventId);
		//map.addAttribute("msg", "Event deleted");
		Client c=clientDao.viewClient(clientId);
		ModelAndView mv=new ModelAndView("EventView");
		mv.addObject("clientName",c.getClientName());
		List<Events> eventList=eventDao.viewAllEventsById(clientId);
		mv.addObject("EventsList", eventList);
		
		return mv;
	}
	
	@Autowired
	HttpSession session;

	@RequestMapping(value="/viewClientEvent/{clientId}" , method=RequestMethod.GET)
	public String viewClientEvent(@PathVariable int clientId){
		System.out.println("Hello 1");
		session.setAttribute("clientId",clientId);
		
		return "cal";
	}
	
	@RequestMapping(value="/viewAllEvents" , method=RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody List<Events> viewAllEvents(){
		
		int clientId=(Integer)session.getAttribute("clientId");
		List<Events> eventList=eventDao.viewAllEventsById(clientId);
	
		return eventList;
	}
}
