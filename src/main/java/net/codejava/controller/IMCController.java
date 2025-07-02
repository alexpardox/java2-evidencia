package net.codejava.controller;

import net.codejava.entity.Usuario;
import net.codejava.entity.Records;
import net.codejava.services.UserService;
import net.codejava.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/imc")
public class IMCController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordsService recordsService;

    // Página de registro
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    // Procesar registro
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("usuario") Usuario usuario, 
                                RedirectAttributes redirectAttributes) {
        try {
            // Validaciones específicas antes de guardar
            if (usuario.getAge() < 15 || usuario.getAge() > 110) {
                redirectAttributes.addFlashAttribute("error", "La edad debe estar entre 15 y 110 años");
                return "redirect:/imc/register";
            }
            
            if (usuario.getHeight() < 1.0 || usuario.getHeight() > 2.5) {
                redirectAttributes.addFlashAttribute("error", "La estatura debe estar entre 1.0m y 2.5m");
                return "redirect:/imc/register";
            }
            
            // Verificar si el username ya existe
            if (userService.existsByUsername(usuario.getUsername())) {
                redirectAttributes.addFlashAttribute("error", "El nombre de usuario ya existe");
                return "redirect:/imc/register";
            }

            // Guardar usuario
            userService.save(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario registrado exitosamente. Ya puedes iniciar sesión.");
            return "redirect:/imc/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar usuario: " + e.getMessage());
            return "redirect:/imc/register";
        }
    }

    // Página de login
    @GetMapping("/login")
    public String showLoginForm() {
        return "imc-login";
    }

    // Procesar login
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        
        if (userService.validateUser(username, password)) {
            Usuario usuario = userService.findByUsername(username).get();
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/imc/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/imc/login";
        }
    }

    // Formulario para nueva medición de IMC
    @GetMapping("/form")
    public String showIMCForm(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para acceder al cálculo de IMC");
            return "redirect:/imc/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("record", new Records());
        return "imc-form";
    }

    // Procesar nueva medición
    @PostMapping("/calculate")
    public String calculateIMC(@RequestParam("weight") Double weight,
                             HttpSession session,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para calcular su IMC");
            return "redirect:/imc/login";
        }

        try {
            // Validar que el peso sea mayor a 0
            if (weight <= 0) {
                redirectAttributes.addFlashAttribute("error", "La masa corporal debe ser mayor a 0");
                return "redirect:/imc/form";
            }
            
            // Crear nuevo registro con el peso ingresado y la altura del usuario
            Records record = new Records(usuario.getId().intValue(), weight, usuario.getHeight());
            recordsService.save(record);

            // Agregar información del resultado
            String categoria = getIMCCategory(record.getImc());
            redirectAttributes.addFlashAttribute("success", 
                String.format("IMC calculado exitosamente: %.2f (%s)", record.getImc(), categoria));
            
            return "redirect:/imc/history";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al calcular IMC: " + e.getMessage());
            return "redirect:/imc/form";
        }
    }

    // Página de historial
    @GetMapping("/history")
    public String showHistory(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para ver su historial");
            return "redirect:/imc/login";
        }

        List<Records> records = recordsService.findByUserIdOrderByMeasuredAtDesc(usuario.getId().intValue());
        model.addAttribute("usuario", usuario);
        model.addAttribute("records", records);
        
        return "imc-history";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/imc/login";
    }

    // Método auxiliar para categorizar el IMC
    private String getIMCCategory(Double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
}
