package org.cqframework.cql.elm.execution;

import org.cqframework.cql.execution.Context;
import org.cqframework.cql.runtime.DateTime;

/**
* Created by Chris Schuler on 6/22/2016
*/
public class DateFromEvaluator extends DateFrom {

  @Override
  public Object evaluate(Context context) {
    Object operand = getOperand().evaluate(context);

    if (operand instanceof DateTime) {
      return ((DateTime)operand).getPartial();
    }
    throw new IllegalArgumentException(String.format("Cannot DateFrom arguments of type '%s'.", operand.getClass().getName()));
  }
}
