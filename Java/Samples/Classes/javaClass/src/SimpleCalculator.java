public class SimpleCalculator {
    // write your code here
    private double firstNumber;
    private double secondNumber;

    public double getFirstNumber() {
        return this.firstNumber;
    }

    public void setFirstNumber(double number) {
        this.firstNumber = number;
    }

    public double getSecondNumber() {
        return this.secondNumber;
    }


    public void setSecondNumber(double number) {
        this.secondNumber = number;
    }

    public double getAdditionResult() {
        return this.firstNumber + this.secondNumber;
    }

    public double getSubtractionResult() {
        return this.firstNumber - this.secondNumber;
    }

    public double getMultiplicationResult() {
        return this.firstNumber * this.secondNumber;
    }

    public double getDivisionResult() {
        if (secondNumber == 0) return 0;
        return this.firstNumber / this.secondNumber;
    }
}
