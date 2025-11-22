package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.service.AccountService;

/**
 * @ClassName: AccountController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/22 17:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping("/decrease")
    public ResultData decrease(@RequestParam("userId") Long userId,
                               @RequestParam("money") Long money) {
        accountService.decrease(userId, money);
        return ResultData.success("userId: "  + userId + ", money: " + money);
    }
}
