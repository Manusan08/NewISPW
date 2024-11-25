package it.uniroma2.ispw.enums;
public enum Coupon {
    NO_COUPON,
    FIRST_COUPON,
    COUPON_5;

    public static Coupon fromString(String couponName) throws IllegalArgumentException {
        return switch (couponName) {
            case "NoCoupon" -> NO_COUPON;
            case "FirstCoupon" -> FIRST_COUPON;
            case "Coupon5" -> COUPON_5;
            default -> throw new IllegalArgumentException("invalid coupon name");
        };
    }

}