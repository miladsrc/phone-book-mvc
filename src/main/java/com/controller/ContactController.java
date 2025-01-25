package com.controller;

@Controller
@RequestMapping("/user")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private AuthService authService;

    @GetMapping("/contacts")
    public String viewContacts(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Contact> contacts = contactService.getContactsForUser(user);
        model.addAttribute("contacts", contacts);
        return "contact-list";
    }

    @PostMapping("/contacts/add")
    public String addContact(@RequestParam String name, @RequestParam String phoneNumber, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        contactService.addContact(user, name, phoneNumber);
        return "redirect:/user/contacts";
    }

    @PostMapping("/contacts/delete")
    public String deleteContact(@RequestParam Long contactId) {
        contactService.deleteContact(contactId);
        return "redirect:/user/contacts";
    }
}
