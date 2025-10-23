package top.byolio.cloud.controller;

import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.byolio.cloud.entities.Pay;
import top.byolio.cloud.entities.PayDTO;
import top.byolio.cloud.service.PayService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: PayController
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/21 9:32
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("pay")
@Tag(name = "支付服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("add")
    @Operation(summary = "新增", description = "新增支付流水方法, json串做参数")
    public String addPay(@RequestBody Pay pay){
        int rows = payService.add(pay);
        return "成功插入" + rows + "条";
    }

    @DeleteMapping("del/{id}")
    @Operation(summary = "删除", description = "删除支付流水的方法")
    public String deletePay(@PathVariable("id") Integer id) {
        int rows = payService.delete(id);
        return "成功删除" + rows + "条";
    }

    @PutMapping("update")
    @Operation(summary = "修改", description = "修改支付流水的方法")
    public String updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int rows = payService.update(pay);
        return "成功更新" + rows + "条";
    }

    @GetMapping("get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水的方法")
    public PayDTO getById(@PathVariable("id") Integer id){
        Pay pay = payService.getById(id);
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(pay,payDTO);
        return payDTO;          // spring restful
    }

    @GetMapping("get")
    @Operation(summary = "查询全部流水", description = "查询支付流水的方法")
    public List<PayDTO> getAllPay(){
        List<Pay> pays = payService.getAll();
        pays.forEach(System.out::println);
        List<PayDTO> payDTOs = pays.stream().map(pay -> {
            PayDTO payDTO = new PayDTO();
            BeanUtils.copyProperties(pay,payDTO);
            return payDTO;
        }).collect(Collectors.toList());
        return payDTOs;
    }


}
