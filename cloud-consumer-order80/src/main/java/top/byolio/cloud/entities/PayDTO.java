package top.byolio.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClassName: PayDTO
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/21 8:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable {
    private Integer id;
    private String payNo;
    private String orderNo;
    private Integer userId;
    private BigDecimal amount;
}
