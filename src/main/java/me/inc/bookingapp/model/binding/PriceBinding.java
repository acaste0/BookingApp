package me.inc.bookingapp.model.binding;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class PriceBinding {

    @Min(value = 1,message = "Minimum value 1")
    private BigDecimal min;
    @Min(value = 1,message = "Minimum value 1")
    private BigDecimal max;

    public BigDecimal getMin() {
        return min;
    }

    public PriceBinding setMin(BigDecimal min) {
        this.min = min;
        return this;
    }

    public BigDecimal getMax() {
        return max;
    }

    public PriceBinding setMax(BigDecimal max) {
        this.max = max;
        return this;
    }
}
