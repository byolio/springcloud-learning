package top.byolio.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EmpowerController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/18 16:12
 * @Version 1.0
 */
@RestController
@Slf4j
public class EmpowerController {
    @GetMapping("/empower")
    public String empower(){
        return "sentinel empower";
    }
}
