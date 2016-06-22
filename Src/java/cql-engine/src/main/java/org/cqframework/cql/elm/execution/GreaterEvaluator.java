package org.cqframework.cql.elm.execution;

import org.cqframework.cql.execution.Context;
import org.cqframework.cql.runtime.Interval;

import java.math.BigDecimal;

/**
 * Created by Bryn on 5/25/2016.
 */
public class GreaterEvaluator extends Greater {

    @Override
    public Object evaluate(Context context) {
        Object left = getOperand().get(0).evaluate(context);
        Object right = getOperand().get(1).evaluate(context);

        if (left == null || right == null) {
            return null;
        }

        else if (left instanceof Integer) {
            return Integer.compare((Integer)left, (Integer)right) > 0;
        }

        else if (left instanceof BigDecimal) {
            return ((BigDecimal)left).compareTo((BigDecimal)right) > 0;
        }

        else if (left instanceof String) {
            return ((String)left).compareTo((String)right) > 0;
        }

        // Uncertainty comparison
        else if (left instanceof Interval && right instanceof Integer) {
          Integer leftStart = (Integer)((Interval)left).getStart();
          Integer leftEnd = (Integer)((Interval)left).getEnd();

          if (leftStart > (Integer)right) { return true; }
          else if ((Integer)right >= leftStart && (Integer)right <= leftEnd) { return null; }
          else { return false; }
        }

        // TODO: Finish implementation
        // >(Quantity, Quantity)
        // >(DateTime, DateTime)
        // >(Time, Time)

        return false;
    }
}
