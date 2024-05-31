package headhunter_webapi.controller;

import headhunter_webapi.service.resumeService.IResumeService;
import headhunter_webapi.service.resumeService.ResumeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/resume")
@SecurityRequirement(name="Bearer token")
@Tag(name = "ResumeController")
public class ResumeController {

    private final IResumeService _resumeService;

    public ResumeController(IResumeService resumeService){
        _resumeService =resumeService;
    }
}
