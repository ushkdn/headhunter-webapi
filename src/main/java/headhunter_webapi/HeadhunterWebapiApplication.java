package headhunter_webapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HeadhunterWebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeadhunterWebapiApplication.class, args);
	}


	@Operation(
			description = """
                    GET method to obtain information.\n
                    Request body: nothing\n
                    Response body: hello""",
			summary = "This is a test GET method to obtain information",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200"
					),
					@ApiResponse(
							description = "Something went wrong!",
							responseCode = "500"
					)
			}
	)
	@GetMapping
	public String hello(){
		return "hello";
	}
}
