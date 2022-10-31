package dev.fdifrison.server.resource;

import dev.fdifrison.server.enumeration.Status;
import dev.fdifrison.server.model.Response;
import dev.fdifrison.server.model.Server;
import dev.fdifrison.server.service.implementation.ServerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/server")
public class ServerController {

    private final ServerServiceImpl serverService;

    public ServerController(ServerServiceImpl serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.findAll(10)))
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ip}")
    public ResponseEntity<Response> pingServer(@PathVariable String ip) throws IOException {
        Server server = serverService.ping(ip);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServersById(@PathVariable Long id) {
//        Response response = new Response();
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.get(id)))
                        .message("Server retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    // The path has to be exactly the one defined in the ServerServiceImpl to set the server image
    // an image has to be returned as a byte array
    public byte[] getServerImage(@PathVariable String fileName) throws IOException, URISyntaxException {
        // TODO CHECK.. may not work
        return Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("static/images/" + fileName).toURI()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.get(id)))
                        .message("Server deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    // @RequestBody means we are going send the body in the request
    // @Valid we are going to check for the validation we specified in the Response (i.e. IP cannot be null)
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        // we use .ok and not .created because otherwise we send a response
        // to the user since .created requires a URI
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


}
