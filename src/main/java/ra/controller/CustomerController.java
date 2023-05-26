package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Customer;
import ra.service.ICustomerService;

import javax.xml.ws.WebEndpoint;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/")
    public ModelAndView listCustomer(){
        ModelAndView mav=new ModelAndView("/customer/list");
        mav.addObject("customer", customerService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public String showFormCreate(Model model){
        Customer customer=new Customer();
        model.addAttribute("formCreate",customer);
        return "customer/add";
    }
    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("formCreate") Customer customer){
            customerService.save(customer);
            return "redirect:/";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        Customer customer=customerService.findById(id);
        model.addAttribute("detail", customer);
        return "customer/detail";
    }

    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id){
        customerService.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id){
        ModelAndView mav=new ModelAndView("customer/edit");
        Customer customer=customerService.findById(id);
        mav.addObject("customerEdit",customer);
        return mav;
    }
    @PostMapping("/edit/customer")
    public String updateCus(@ModelAttribute("customerEdit") Customer customer){
        customerService.update(customer);
        return "redirect:/";
    }


















}
