// Custom Exception for Invalid Dimension
class InvalidDimensionException extends Exception {
    public InvalidDimensionException(String message) {
        super(message);
    }
}

// Custom Exception for Dimension Mismatch
class DimensionMismatchException extends Exception {
    public DimensionMismatchException(String message) {
        super(message);
    }
}
