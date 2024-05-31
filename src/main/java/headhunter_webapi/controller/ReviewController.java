package headhunter_webapi.controller;

import headhunter_webapi.service.reviewService.IReviewService;
import headhunter_webapi.service.reviewService.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/review")
@SecurityRequirement(name="Bearer token")
@Tag(name = "ReviewController")
public class ReviewController {

    private final IReviewService _reviewService;

    public ReviewController(IReviewService reviewService){
        _reviewService=reviewService;
    }
}
