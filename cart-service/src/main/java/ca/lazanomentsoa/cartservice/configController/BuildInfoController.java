package ca.lazanomentsoa.cartservice.configController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class BuildInfoController {

    @Value("${build.name:default}")
    private String buildName;


    @GetMapping("/build-info")
    public String getBuildInfo(){
        return "Build Info in Cart-service: " + buildName;
    }
}
