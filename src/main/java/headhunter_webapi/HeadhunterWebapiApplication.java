package headhunter_webapi;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class HeadhunterWebapiApplication {

	//TODO: db relationships & complete entities

	public static void main(String[] args) {
		SpringApplication.run(HeadhunterWebapiApplication.class, args);
	}

//
//	@Operation(
//			description = """
//                    GET method to obtain information.\n
//                    Request body: nothing\n
//                    Response body: hello""",
//			summary = "This is a test GET method to obtain information",
//			responses = {
//					@ApiResponse(
//							description = "Success",
//							responseCode = "200"
//					),
//					@ApiResponse(
//							description = "Something went wrong!",
//							responseCode = "500"
//					)
//			}
//	)
//	@GetMapping
//	public String hello(){
//		return "hello";
//	}
}
