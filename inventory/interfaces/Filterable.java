package inventory.interfaces;

import java.util.List;
import java.math.BigDecimal;

public interface Filterable<T> {
    List<T> filterByName(String name);
    List<T> filterByPriceRange(BigDecimal min, BigDecimal max);
}
