/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.pricer.impl.rate;

import java.time.LocalDate;

import com.opengamma.strata.basics.index.PriceIndex;
import com.opengamma.strata.finance.rate.InflationMonthlyRateObservation;
import com.opengamma.strata.pricer.rate.PriceIndexProvider;
import com.opengamma.strata.pricer.rate.RateObservationFn;
import com.opengamma.strata.pricer.rate.RatesProvider;
import com.opengamma.strata.pricer.sensitivity.PointSensitivityBuilder;

/**
 * Rate observation implementation for a price index. 
 * <p>
 * The pay-off for a unit notional is {@code (Index_End / Index_Start - 1)}, where
 * start index value and end index value are simply returned by {@code RatesProvider}.
 */
public class ForwardInflationMonthlyRateObservationFn
    implements RateObservationFn<InflationMonthlyRateObservation> {

  /**
   * Default instance.
   */
  public static final ForwardInflationMonthlyRateObservationFn DEFAULT =
      new ForwardInflationMonthlyRateObservationFn();

  /**
   * Creates an instance.
   */
  public ForwardInflationMonthlyRateObservationFn() {
  }

  //-------------------------------------------------------------------------
  @Override
  public double rate(InflationMonthlyRateObservation observation, LocalDate startDate, LocalDate endDate,
      RatesProvider provider) {
    PriceIndex index = observation.getIndex();
    double indexStart = provider.data(PriceIndexProvider.class).inflationIndexRate(
        index, observation.getReferenceStartMonth(), provider);
    double indexEnd = provider.data(PriceIndexProvider.class).inflationIndexRate(
        index, observation.getReferenceEndMonth(), provider);
    return indexEnd / indexStart - 1.0d;
  }

  @Override
  public PointSensitivityBuilder rateSensitivity(InflationMonthlyRateObservation observation, LocalDate startDate,
      LocalDate endDate, RatesProvider provider) {
    PriceIndex index = observation.getIndex();
    double indexStartInv = 1.0 / provider.data(PriceIndexProvider.class).inflationIndexRate(
        index, observation.getReferenceStartMonth(), provider);
    double indexEnd = provider.data(PriceIndexProvider.class).inflationIndexRate(
        index, observation.getReferenceEndMonth(), provider);
    PointSensitivityBuilder indexStartSensitivity = provider.data(PriceIndexProvider.class)
        .inflationIndexRateSensitivity(index, observation.getReferenceStartMonth(), provider);
    PointSensitivityBuilder indexEndSensitivity = provider.data(PriceIndexProvider.class)
        .inflationIndexRateSensitivity(index, observation.getReferenceEndMonth(), provider);
    indexEndSensitivity = indexEndSensitivity.multipliedBy(indexStartInv);
    indexStartSensitivity = indexStartSensitivity.multipliedBy(-indexEnd * indexStartInv * indexStartInv);
    return indexStartSensitivity.combinedWith(indexEndSensitivity);
  }

}
