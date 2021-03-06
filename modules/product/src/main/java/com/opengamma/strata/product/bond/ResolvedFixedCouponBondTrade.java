/*
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.bond;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.joda.beans.Bean;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaBean;
import org.joda.beans.MetaProperty;
import org.joda.beans.gen.BeanDefinition;
import org.joda.beans.gen.ImmutableDefaults;
import org.joda.beans.gen.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.ReferenceData;
import com.opengamma.strata.product.PortfolioItemInfo;
import com.opengamma.strata.product.ResolvedTrade;
import com.opengamma.strata.product.TradeInfo;

/**
 * A trade in a fixed coupon bond, resolved for pricing.
 * <p>
 * This is the resolved form of {@link FixedCouponBondTrade} and is the primary input to the pricers.
 * Applications will typically create a {@code ResolvedFixedCouponBondTrade} from a {@code FixedCouponBondTrade}
 * using {@link FixedCouponBondTrade#resolve(ReferenceData)}.
 * <p>
 * It is also the resolved form of {@link FixedCouponBondPosition} for those cases where a position is held.
 * The position is priced as though the trade is fully settled.
 * <p>
 * A {@code ResolvedFixedCouponBondTrade} is bound to data that changes over time, such as holiday calendars.
 * If the data changes, such as the addition of a new holiday, the resolved form will not be updated.
 * Care must be taken when placing the resolved form in a cache or persistence layer.
 * 
 * <h4>Price</h4>
 * Strata uses <i>decimal prices</i> for bonds in the trade model, pricers and market data.
 * For example, a price of 99.32% is represented in Strata by 0.9932.
 */
@BeanDefinition(constructorScope = "package")
public final class ResolvedFixedCouponBondTrade
    implements ResolvedTrade, ImmutableBean, Serializable {

  /**
   * The additional information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final PortfolioItemInfo info;
  /**
   * The resolved fixed coupon bond product.
   * <p>
   * The product captures the contracted financial details of the trade.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final ResolvedFixedCouponBond product;
  /**
   * The quantity, indicating the number of bond contracts in the trade.
   * <p>
   * This will be positive if buying and negative if selling.
   */
  @PropertyDefinition
  private final double quantity;
  /**
   * The settlement details of the bond trade.
   * <p>
   * When this class is used to represent a position, this property will be empty.
   */
  @PropertyDefinition(get = "optional")
  private final ResolvedFixedCouponBondSettlement settlement;

  //-------------------------------------------------------------------------
  @ImmutableDefaults
  private static void applyDefaults(Builder builder) {
    builder.info = TradeInfo.empty();
  }

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code ResolvedFixedCouponBondTrade}.
   * @return the meta-bean, not null
   */
  public static ResolvedFixedCouponBondTrade.Meta meta() {
    return ResolvedFixedCouponBondTrade.Meta.INSTANCE;
  }

  static {
    MetaBean.register(ResolvedFixedCouponBondTrade.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ResolvedFixedCouponBondTrade.Builder builder() {
    return new ResolvedFixedCouponBondTrade.Builder();
  }

  /**
   * Creates an instance.
   * @param info  the value of the property, not null
   * @param product  the value of the property, not null
   * @param quantity  the value of the property
   * @param settlement  the value of the property
   */
  ResolvedFixedCouponBondTrade(
      PortfolioItemInfo info,
      ResolvedFixedCouponBond product,
      double quantity,
      ResolvedFixedCouponBondSettlement settlement) {
    JodaBeanUtils.notNull(info, "info");
    JodaBeanUtils.notNull(product, "product");
    this.info = info;
    this.product = product;
    this.quantity = quantity;
    this.settlement = settlement;
  }

  @Override
  public ResolvedFixedCouponBondTrade.Meta metaBean() {
    return ResolvedFixedCouponBondTrade.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the additional information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached.
   * @return the value of the property, not null
   */
  @Override
  public PortfolioItemInfo getInfo() {
    return info;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the resolved fixed coupon bond product.
   * <p>
   * The product captures the contracted financial details of the trade.
   * @return the value of the property, not null
   */
  @Override
  public ResolvedFixedCouponBond getProduct() {
    return product;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the quantity, indicating the number of bond contracts in the trade.
   * <p>
   * This will be positive if buying and negative if selling.
   * @return the value of the property
   */
  public double getQuantity() {
    return quantity;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement details of the bond trade.
   * <p>
   * When this class is used to represent a position, this property will be empty.
   * @return the optional value of the property, not null
   */
  public Optional<ResolvedFixedCouponBondSettlement> getSettlement() {
    return Optional.ofNullable(settlement);
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvedFixedCouponBondTrade other = (ResolvedFixedCouponBondTrade) obj;
      return JodaBeanUtils.equal(info, other.info) &&
          JodaBeanUtils.equal(product, other.product) &&
          JodaBeanUtils.equal(quantity, other.quantity) &&
          JodaBeanUtils.equal(settlement, other.settlement);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(info);
    hash = hash * 31 + JodaBeanUtils.hashCode(product);
    hash = hash * 31 + JodaBeanUtils.hashCode(quantity);
    hash = hash * 31 + JodaBeanUtils.hashCode(settlement);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("ResolvedFixedCouponBondTrade{");
    buf.append("info").append('=').append(info).append(',').append(' ');
    buf.append("product").append('=').append(product).append(',').append(' ');
    buf.append("quantity").append('=').append(quantity).append(',').append(' ');
    buf.append("settlement").append('=').append(JodaBeanUtils.toString(settlement));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedFixedCouponBondTrade}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code info} property.
     */
    private final MetaProperty<PortfolioItemInfo> info = DirectMetaProperty.ofImmutable(
        this, "info", ResolvedFixedCouponBondTrade.class, PortfolioItemInfo.class);
    /**
     * The meta-property for the {@code product} property.
     */
    private final MetaProperty<ResolvedFixedCouponBond> product = DirectMetaProperty.ofImmutable(
        this, "product", ResolvedFixedCouponBondTrade.class, ResolvedFixedCouponBond.class);
    /**
     * The meta-property for the {@code quantity} property.
     */
    private final MetaProperty<Double> quantity = DirectMetaProperty.ofImmutable(
        this, "quantity", ResolvedFixedCouponBondTrade.class, Double.TYPE);
    /**
     * The meta-property for the {@code settlement} property.
     */
    private final MetaProperty<ResolvedFixedCouponBondSettlement> settlement = DirectMetaProperty.ofImmutable(
        this, "settlement", ResolvedFixedCouponBondTrade.class, ResolvedFixedCouponBondSettlement.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "info",
        "product",
        "quantity",
        "settlement");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          return info;
        case -309474065:  // product
          return product;
        case -1285004149:  // quantity
          return quantity;
        case 73828649:  // settlement
          return settlement;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvedFixedCouponBondTrade.Builder builder() {
      return new ResolvedFixedCouponBondTrade.Builder();
    }

    @Override
    public Class<? extends ResolvedFixedCouponBondTrade> beanType() {
      return ResolvedFixedCouponBondTrade.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code info} property.
     * @return the meta-property, not null
     */
    public MetaProperty<PortfolioItemInfo> info() {
      return info;
    }

    /**
     * The meta-property for the {@code product} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ResolvedFixedCouponBond> product() {
      return product;
    }

    /**
     * The meta-property for the {@code quantity} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> quantity() {
      return quantity;
    }

    /**
     * The meta-property for the {@code settlement} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ResolvedFixedCouponBondSettlement> settlement() {
      return settlement;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          return ((ResolvedFixedCouponBondTrade) bean).getInfo();
        case -309474065:  // product
          return ((ResolvedFixedCouponBondTrade) bean).getProduct();
        case -1285004149:  // quantity
          return ((ResolvedFixedCouponBondTrade) bean).getQuantity();
        case 73828649:  // settlement
          return ((ResolvedFixedCouponBondTrade) bean).settlement;
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ResolvedFixedCouponBondTrade}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ResolvedFixedCouponBondTrade> {

    private PortfolioItemInfo info;
    private ResolvedFixedCouponBond product;
    private double quantity;
    private ResolvedFixedCouponBondSettlement settlement;

    /**
     * Restricted constructor.
     */
    private Builder() {
      applyDefaults(this);
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ResolvedFixedCouponBondTrade beanToCopy) {
      this.info = beanToCopy.getInfo();
      this.product = beanToCopy.getProduct();
      this.quantity = beanToCopy.getQuantity();
      this.settlement = beanToCopy.settlement;
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          return info;
        case -309474065:  // product
          return product;
        case -1285004149:  // quantity
          return quantity;
        case 73828649:  // settlement
          return settlement;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          this.info = (PortfolioItemInfo) newValue;
          break;
        case -309474065:  // product
          this.product = (ResolvedFixedCouponBond) newValue;
          break;
        case -1285004149:  // quantity
          this.quantity = (Double) newValue;
          break;
        case 73828649:  // settlement
          this.settlement = (ResolvedFixedCouponBondSettlement) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public ResolvedFixedCouponBondTrade build() {
      return new ResolvedFixedCouponBondTrade(
          info,
          product,
          quantity,
          settlement);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the additional information, defaulted to an empty instance.
     * <p>
     * This allows additional information to be attached.
     * @param info  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder info(PortfolioItemInfo info) {
      JodaBeanUtils.notNull(info, "info");
      this.info = info;
      return this;
    }

    /**
     * Sets the resolved fixed coupon bond product.
     * <p>
     * The product captures the contracted financial details of the trade.
     * @param product  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder product(ResolvedFixedCouponBond product) {
      JodaBeanUtils.notNull(product, "product");
      this.product = product;
      return this;
    }

    /**
     * Sets the quantity, indicating the number of bond contracts in the trade.
     * <p>
     * This will be positive if buying and negative if selling.
     * @param quantity  the new value
     * @return this, for chaining, not null
     */
    public Builder quantity(double quantity) {
      this.quantity = quantity;
      return this;
    }

    /**
     * Sets the settlement details of the bond trade.
     * <p>
     * When this class is used to represent a position, this property will be empty.
     * @param settlement  the new value
     * @return this, for chaining, not null
     */
    public Builder settlement(ResolvedFixedCouponBondSettlement settlement) {
      this.settlement = settlement;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("ResolvedFixedCouponBondTrade.Builder{");
      buf.append("info").append('=').append(JodaBeanUtils.toString(info)).append(',').append(' ');
      buf.append("product").append('=').append(JodaBeanUtils.toString(product)).append(',').append(' ');
      buf.append("quantity").append('=').append(JodaBeanUtils.toString(quantity)).append(',').append(' ');
      buf.append("settlement").append('=').append(JodaBeanUtils.toString(settlement));
      buf.append('}');
      return buf.toString();
    }

  }

  //-------------------------- AUTOGENERATED END --------------------------
}
