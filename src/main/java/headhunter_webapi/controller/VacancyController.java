package headhunter_webapi.controller;

import headhunter_webapi.service.vacancySerivce.IVacancyService;
import headhunter_webapi.service.vacancySerivce.VacancyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vacancy")
@SecurityRequirement(name="Bearer token")
@Tag(name = "VacancyController")
public class VacancyController {

    private final IVacancyService _vacancyService;

    public VacancyController(IVacancyService vacancyService){
        _vacancyService=vacancyService;
    }
}
